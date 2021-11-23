package com.zxs.permission.controller;

import com.zxs.permission.common.JsonData;
import com.zxs.permission.dto.AclModuleLevelDto;
import com.zxs.permission.param.AclModuleParam;
import com.zxs.permission.service.SysAclModuleService;
import com.zxs.permission.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zxs
 * @Date: 2019/3/14 19:24
 * @Version 1.0
 * @Describe
 */
@Controller
@RequestMapping("/sys/aclModule")
@Slf4j
public class SysAclModuleController {

    @Resource
    private SysAclModuleService sysAclModuleService;

    @Resource
    private SysTreeService sysTreeService;

    @RequestMapping("/acl.page")
    @ResponseBody
    public ModelAndView page(){
        return new ModelAndView("acl");
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveAclModule(AclModuleParam param){
        sysAclModuleService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateAclModule(AclModuleParam param){
        sysAclModuleService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree(){
        List<AclModuleLevelDto> dtoList = sysTreeService.aclModuleTree();
        return JsonData.success(dtoList);
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public JsonData deleteAclModule(@RequestParam("id") int aclModuleId){
        sysAclModuleService.delete(aclModuleId);
        return JsonData.success();
    }
}
