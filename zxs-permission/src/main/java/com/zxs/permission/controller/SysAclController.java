package com.zxs.permission.controller;

import com.google.common.collect.Maps;
import com.zxs.permission.beans.PageQuery;
import com.zxs.permission.common.JsonData;
import com.zxs.permission.model.SysRole;
import com.zxs.permission.param.AclParam;
import com.zxs.permission.service.SysAclService;
import com.zxs.permission.service.SysRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: zxs
 * @Date: 2019/3/14 19:24
 * @Version 1.0
 * @Describe
 */
@Controller
@RequestMapping("/sys/acl")
public class SysAclController {

    @Resource
    private SysAclService sysAclService;

    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveAclModule(AclParam param) {
        sysAclService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateAclModule(AclParam param) {
        sysAclService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData list(@RequestParam("aclModuleId") Integer aclModuleId, PageQuery pageQuery) {
        return JsonData.success(sysAclService.getPageByAclModuleId(aclModuleId, pageQuery));
    }

    @RequestMapping("/acls.json")
    @ResponseBody
    public JsonData acls(@RequestParam("aclId") int aclId){
        Map<String,Object> map = Maps.newHashMap();
        List<SysRole> roleList = sysRoleService.getRoleListByAclId(aclId);
        map.put("roles",roleList);
        map.put("users",sysRoleService.getUserListByRoleList(roleList));
        return JsonData.success(map);
    }
}
