package com.zxs.house.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zxs.house.entity.Subway;

/**
 * Created by zxs.
 */
public interface SubwayRepository extends CrudRepository<Subway, Long>{
    List<Subway> findAllByCityEnName(String cityEnName);
}
