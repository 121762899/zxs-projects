package com.zxs.diners.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.zxs.commons.model.domain.ResultInfo;
import com.zxs.commons.model.dto.DinersDTO;
import com.zxs.commons.model.vo.ShortDinerInfo;
import com.zxs.commons.utils.ResultInfoUtil;
import com.zxs.diners.service.DinersService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zxs
 * @create 2020-11-25 21:03
 */

@RestController
@Api(tags = "食客相关接口")
public class DinersController {

    @Resource
    private DinersService dinersService;

    @Resource
    private HttpServletRequest request;



    /**
     * 根据 ids 查询食客信息
     *
     * @param ids
     * @return
     */
    @GetMapping("findByIds")
    public ResultInfo<List<ShortDinerInfo>> findByIds(String ids) {
        List<ShortDinerInfo> dinerInfos = dinersService.findByIds(ids);
        return ResultInfoUtil.buildSuccess(request.getServletPath(), dinerInfos);
    }


    /**
     * 注册
     *
     * @param dinersDTO
     * @return
     */
    @PostMapping("register")
    public ResultInfo register(@RequestBody DinersDTO dinersDTO) {
        return dinersService.register(dinersDTO, request.getServletPath());
    }


    /**
     * 校验手机号是否已注册
     *
     * @return
     */
    @GetMapping("checkPhone")
    public ResultInfo checkPhone(String phone) {
        dinersService.checkPhoneIsRegistered(phone);
        return ResultInfoUtil.buildSuccess(request.getServletPath());
    }

    /**
     * 登录
     *
     * @param account  账号
     * @param password 密码
     * @return
     */
    @GetMapping("signin")
    public ResultInfo signIn(String account, String password) {
        return dinersService.signIn(account, password, request.getServletPath());
    }
}
