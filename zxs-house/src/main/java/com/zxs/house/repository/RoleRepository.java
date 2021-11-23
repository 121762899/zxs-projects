package com.zxs.house.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zxs.house.entity.Role;

/**
 * 角色数据DAO
 * Created by zxs.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findRolesByUserId(Long userId);
}
