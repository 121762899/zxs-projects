package com.zxs.blog.service;

import com.zxs.blog.domain.Comment;
import com.zxs.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.blog.service.CommentService#removeComment(java.lang.Long)
	 */
	@Override
	@Transactional
	public void removeComment(Long id) {
		commentRepository.deleteById(id);
	}
	@Override
	public Comment getCommentById(Long id) {
		return commentRepository.findById(id).get();
	}

}
