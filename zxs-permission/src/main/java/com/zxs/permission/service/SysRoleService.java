package com.zxs.permission.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.zxs.permission.common.RequestHolder;
import com.zxs.permission.dao.SysRoleAclMapper;
import com.zxs.permission.dao.SysRoleMapper;
import com.zxs.permission.dao.SysRoleUserMapper;
import com.zxs.permission.dao.SysUserMapper;
import com.zxs.permission.exception.ParamException;
import com.zxs.permission.model.SysRole;
import com.zxs.permission.model.SysUser;
import com.zxs.permission.param.RoleParam;
import com.zxs.permission.util.BeanValidator;
import com.zxs.permission.util.IpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zxs
 * @Date: 2019/3/15 14:52
 * @Version 1.0
 * @Describe
 */
@Service
public class SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    @Resource
    private SysRoleAclMapper sysRoleAclMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysLogService sysLogService;

    public void save(RoleParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getName(),param.getId())){
            throw new ParamException("角色名称已经存在");
        }

        SysRole role = SysRole.builder()
                .name(param.getName())
                .type(param.getType())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();

        role.setOperator(RequestHolder.getCurrentUser().getUsername());//TODO
        role.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        role.setOperateTime(new Date());
        sysRoleMapper.insertSelective(role);
        sysLogService.saveRoleLog(null,role);
    }

    public void update(RoleParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("角色名称已经存在");
        }
        SysRole before = sysRoleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的角色不存在");

        SysRole after = SysRole.builder().id(param.getId()).name(param.getName()).status(param.getStatus()).type(param.getType())
                .remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());
        sysRoleMapper.updateByPrimaryKeySelective(after);
        sysLogService.saveRoleLog(before,after);
    }

    public List<SysRole> getAll() {
        return sysRoleMapper.getAll();
    }

    private boolean checkExist(String name, Integer id) {
        return sysRoleMapper.countByName(name, id) > 0;
    }

    public List<SysRole> getRoleListByUserId(int userId){
        List<Integer> roleIdListByUserId = sysRoleUserMapper.getRoleIdListByUserId(userId);

        if(CollectionUtils.isEmpty(roleIdListByUserId)){
            return Lists.newArrayList();
        }
        return sysRoleMapper.getByIdList(roleIdListByUserId);
    }

    public List<SysRole> getRoleListByAclId(int aclId) {
        List<Integer> roleIdListByAclId = sysRoleAclMapper.getRoleIdListByAclId(aclId);

        if(CollectionUtils.isEmpty(roleIdListByAclId)){
            return Lists.newArrayList();
        }
        return sysRoleMapper.getByIdList(roleIdListByAclId);
    }

    public List<SysUser> getUserListByRoleList(List<SysRole> roleList) {
        if(CollectionUtils.isEmpty(roleList)){
            return Lists.newArrayList();
        }
        List<Integer> roleIdList = roleList.stream()
                .map(role -> role.getId())
                .collect(Collectors.toList());
        List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleIdList(roleIdList);
        if(CollectionUtils.isEmpty(userIdList)){
            return Lists.newArrayList();
        }
        return sysUserMapper.getByIdList(userIdList);
    }
}
