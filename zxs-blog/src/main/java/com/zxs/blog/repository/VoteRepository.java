package com.zxs.blog.repository;

import com.zxs.blog.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
 
}
