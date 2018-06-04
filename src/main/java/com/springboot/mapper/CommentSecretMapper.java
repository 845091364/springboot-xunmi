package com.springboot.mapper;

import java.util.HashMap;
import java.util.List;

import com.springboot.model.CommentSecret;

public interface CommentSecretMapper {
	int insert(CommentSecret CommentSecret);
	
	List<HashMap<String, Object>> selectCommentToUserToUser(Integer commentId);
	
	int insertCommentUserToUser(CommentSecret CommentSecret);
	
	int insertCommentUser (CommentSecret CommentSecret);
	 
	int saveCommentPraise (Integer commentId);
	
	int updateComment(Integer commentId);
	
	List<CommentSecret> findAll(Integer secretId);
}
