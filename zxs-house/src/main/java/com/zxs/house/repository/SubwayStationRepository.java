package com.zxs.house.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zxs.house.entity.SubwayStation;

/**
 * Created by zxs.
 */
public interface SubwayStationRepository extends CrudRepository<SubwayStation, Long> {
    List<SubwayStation> findAllBySubwayId(Long subwayId);
}
