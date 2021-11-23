package com.zxs.restaurants.controller;


import com.zxs.restaurants.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.zxs.commons.model.domain.ResultInfo;
import com.zxs.commons.model.pojo.Restaurant;
import com.zxs.commons.utils.ResultInfoUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class RestaurantController {

    @Resource
    private RestaurantService restaurantService;
    @Resource
    private HttpServletRequest request;

    /**
     * 根据餐厅 ID 查询餐厅数据
     *
     * @param restaurantId
     * @return
     */
    @GetMapping("detail/{restaurantId}")
    public ResultInfo<Restaurant> findById(@PathVariable Integer restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        return ResultInfoUtil.buildSuccess(request.getServletPath(), restaurant);
    }

}
