/**
 * 
 */
package com.zxs.blog.service;

import com.zxs.blog.domain.Authority;
import com.zxs.blog.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl  implements AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public Authority getAuthorityById(Long id) {
		//return authorityRepository.findOne(id);
		return authorityRepository.findById(id).get();
	}

}
