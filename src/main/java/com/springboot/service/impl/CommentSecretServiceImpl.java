package com.springboot.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mapper.CommentSecretMapper;
import com.springboot.model.CommentSecret;
import com.springboot.service.CommentSecretService;

import net.sf.json.JSONObject;

@Service
public class CommentSecretServiceImpl implements CommentSecretService{
	@Autowired
	private CommentSecretMapper commentSecretMapper;
	@Override
	public HashMap<String, Object> saveComment(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CommentSecret  commentSecret   =  new CommentSecret();
		commentSecret.setContent(param.getString("content"));
		commentSecret.setUserId(param.getInt("userId"));
		commentSecret.setSecretId(param.getInt("secretId"));
		commentSecretMapper.insert(commentSecret);
		map.put("success", 1);
		return map;
	}
	@Override
	public HashMap<String, Object> getComment(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("comment", commentSecretMapper.findAll(param.getInt("secretId")));
		map.put("success", 1);
		return map;
	}

}
