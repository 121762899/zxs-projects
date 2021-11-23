package com.zxs.restaurants.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.zxs.commons.constant.RedisKeyConstant;
import com.zxs.commons.model.pojo.Restaurant;
import com.zxs.commons.utils.AssertUtil;
import com.zxs.restaurants.mapper.RestaurantMapper;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zxs
 * @create 2021-05-18 11:34
 */

@Service
@Slf4j
public class RestaurantService {

    @Resource
    private RestaurantMapper mapper;

    @Resource
    private RedisTemplate redisTemplate;

    public Restaurant findById(Integer id) {
        AssertUtil.isTrue(id == null,"请选择查询的餐厅");
        // 先查询缓存
        String key = RedisKeyConstant.restaurants.getKey()+id;
        LinkedHashMap entries = (LinkedHashMap)redisTemplate.opsForHash().entries(key);
        Restaurant restaurant = null;
        // 缓存中不存在
        if (entries == null || entries.isEmpty()) {
            log.info("缓存失效了，查询数据库：{}",id);
            // 再查询数据库
            restaurant = mapper.findById(id);
            if (restaurant == null) {
                // 设置一个null，并设置过期时间；TODO 能不能简化为一步操作
                redisTemplate.opsForHash().putAll(key,null);
                redisTemplate.expire(key,60,TimeUnit.SECONDS);

            }else {
                // 数据库中存在，将其放入缓存
                redisTemplate.opsForHash().putAll(key,BeanUtil.beanToMap(restaurant));
            }

        }else {
            // 缓存中存在
            restaurant = BeanUtil.mapToBean(entries, Restaurant.class, false,null);
        }

        return restaurant;
    }
}
