package com.springboot.mapper;

import java.util.List;

import com.springboot.model.CommentSecret;

public interface CommentSecretMapper {
	int insert(CommentSecret CommentSecret);
	
	List<CommentSecret> findAll(Integer secretId);
}
