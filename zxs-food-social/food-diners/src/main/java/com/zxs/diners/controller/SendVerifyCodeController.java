package com.zxs.diners.controller;

import com.zxs.diners.service.SendVerifyCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zxs.commons.model.domain.ResultInfo;
import com.zxs.commons.utils.ResultInfoUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zxs
 * @create 2020-11-28 15:51
 */


@RestController
public class SendVerifyCodeController {
    @Resource
    private SendVerifyCodeService sendVerifyCodeService;
    @Resource
    private HttpServletRequest request;

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @GetMapping("send")
    public ResultInfo send(String phone) {
        sendVerifyCodeService.send(phone);
        return ResultInfoUtil.buildSuccess(request.getServletPath(), "发送成功");
    }
}
