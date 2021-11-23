package com.zxs.house.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zxs.house.entity.HouseDetail;

/**
 * Created by zxs.
 */
public interface HouseDetailRepository extends CrudRepository<HouseDetail, Long>{
    HouseDetail findByHouseId(Long houseId);

    List<HouseDetail> findAllByHouseIdIn(List<Long> houseIds);
}
