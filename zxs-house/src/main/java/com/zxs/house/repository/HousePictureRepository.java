package com.zxs.house.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zxs.house.entity.HousePicture;

/**
 * Created by zxs.
 */
public interface HousePictureRepository extends CrudRepository<HousePicture, Long> {
    List<HousePicture> findAllByHouseId(Long id);
}
