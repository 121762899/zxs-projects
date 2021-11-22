package com.zxs.blog.repository;

import com.zxs.blog.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
