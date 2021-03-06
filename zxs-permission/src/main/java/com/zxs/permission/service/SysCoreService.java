package com.zxs.permission.service;

import com.google.common.collect.Lists;
import com.zxs.permission.beans.CacheKeyConstants;
import com.zxs.permission.common.RequestHolder;
import com.zxs.permission.dao.SysAclMapper;
import com.zxs.permission.dao.SysRoleAclMapper;
import com.zxs.permission.dao.SysRoleUserMapper;
import com.zxs.permission.model.SysAcl;
import com.zxs.permission.model.SysUser;
import com.zxs.permission.util.JsonMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: zxs
 * @Date: 2019/3/15 16:15
 * @Version 1.0
 * @Describe
 */
@Service
public class SysCoreService {

    @Resource
    private SysAclMapper sysAclMapper;

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    @Resource
    private SysRoleAclMapper sysRoleAclMapper;

    @Resource
    private SysCacheService sysCacheService;

    public List<SysAcl> getCurrentUserAclList(){
        int userId = RequestHolder.getCurrentUser().getId();
        return getUserAclList(userId);
    }

    public List<SysAcl> getRoleAclList(int roleId){
        List<Integer> roleIdList = sysRoleAclMapper.getAclIdListByRoleIdList(Lists.newArrayList(roleId));
        if(CollectionUtils.isEmpty(roleIdList)){
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(roleIdList);
    }

    public List<SysAcl> getUserAclList(int userId){
        if(isSuperAdmin()){
            return sysAclMapper.getAll();
        }
        List<Integer> userRoleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if(CollectionUtils.isEmpty(userRoleIdList)){
            return Lists.newArrayList();
        }

        List<Integer> roleAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(userRoleIdList);
        if(CollectionUtils.isEmpty(roleAclIdList)){
            return Lists.newArrayList();
        }

        return sysAclMapper.getByIdList(roleAclIdList);
    }

    public boolean isSuperAdmin(){
        SysUser sysUser = RequestHolder.getCurrentUser();
        if(sysUser.getMail().contains("admin")){
            return true;
        }
        return false;
    }

    public boolean hasUrlAcl(String url){
        if(isSuperAdmin()){
            return true;
        }
        List<SysAcl> aclList = sysAclMapper.getByUrl(url);
        if(CollectionUtils.isEmpty(aclList)){
            return true;
        }

        List<SysAcl> userAclList = getCurrentUserAclListFromCache();
        Set<Integer> userAclIdSet = userAclList.stream()
                .map(acl -> acl.getId())
                .collect(Collectors.toSet());

        boolean hasValidAcl = false;
        for(SysAcl acl:aclList){
            if(acl==null || acl.getStatus()!=1){
                continue;
            }
            hasValidAcl = true;
            if(userAclIdSet.contains(acl.getId())){
                return true;
            }
        }
        if(!hasValidAcl){
            return true;
        }
        return false;
    }

    public List<SysAcl> getCurrentUserAclListFromCache() {
        int userId = RequestHolder.getCurrentUser().getId();
        String cacheValue = sysCacheService.getFromCache(CacheKeyConstants.USER_ACLS, String.valueOf(userId));
        if (StringUtils.isBlank(cacheValue)) {
            List<SysAcl> aclList = getCurrentUserAclList();
            if (CollectionUtils.isNotEmpty(aclList)) {
                sysCacheService.saveCache(JsonMapper.object2String(aclList), 600, CacheKeyConstants.USER_ACLS, String.valueOf(userId));
            }
            return aclList;
        }
        return JsonMapper.string2Obj(cacheValue, new TypeReference<List<SysAcl>>() {
        });
    }
}
