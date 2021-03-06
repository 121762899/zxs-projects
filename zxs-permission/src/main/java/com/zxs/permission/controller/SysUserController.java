package com.zxs.permission.controller;

import com.google.common.collect.Maps;
import com.zxs.permission.beans.PageQuery;
import com.zxs.permission.beans.PageResult;
import com.zxs.permission.common.JsonData;
import com.zxs.permission.model.SysUser;
import com.zxs.permission.param.UserParam;
import com.zxs.permission.service.SysRoleService;
import com.zxs.permission.service.SysTreeService;
import com.zxs.permission.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: zxs
 * @Date: 2019/3/14 13:32
 * @Version 1.0
 * @Describe
 */
@Controller
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysTreeService sysTreeService;
    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping("/noAuth.page")
    public ModelAndView noAuth() {
        return new ModelAndView("noAuth");
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveUser(UserParam param){
        sysUserService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateUser(UserParam param){
        sysUserService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData page(@RequestParam("deptId") int deptId, PageQuery pageQuery){
        PageResult<SysUser> sysUserPageResult = sysUserService.getPageByDeptId(deptId,pageQuery);
        return JsonData.success(sysUserPageResult);
    }

    @RequestMapping("/acls.json")
    @ResponseBody
    public JsonData acls(@RequestParam("userId") int userId){
        Map<String,Object> map = Maps.newHashMap();
        map.put("acls",sysTreeService.userAclTree(userId));
        map.put("roles",sysRoleService.getRoleListByUserId(userId));
        return JsonData.success(map);
    }
}
