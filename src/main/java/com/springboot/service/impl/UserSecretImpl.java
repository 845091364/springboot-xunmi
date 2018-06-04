package com.springboot.service.impl;

import com.springboot.mapper.CommentSecretMapper;
import com.springboot.mapper.PraiseCommentMapper;
import com.springboot.mapper.UserMapper;
import com.springboot.mapper.UserSecretMapper;
import com.springboot.model.OpenSecret;
import com.springboot.model.PraiseSecret;
import com.springboot.model.User;
import com.springboot.model.UserAttention;
import com.springboot.model.UserSecret;
import com.springboot.service.UserSecretService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSecretImpl implements UserSecretService {
	@Autowired
	private UserSecretMapper userSecretMapper;
	@Autowired
	private PraiseCommentMapper praiseCommentMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CommentSecretMapper commentSecretMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public HashMap<String, Object> addUserSecret(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		UserSecret record = new UserSecret();
		record.setContent(param.getString("content"));
		record.setTitle(param.getString("title"));
		record.setLabel(param.getString("label"));
		record.setPic(param.getString("pic"));
		record.setUserId(Integer.valueOf(param.getInt("userId")));
		record.setPosition(param.getString("psoition"));
		this.userSecretMapper.insert(record);
		this.userMapper.updatePublishCount(param.getInt("userId"));
		map.put("msg", "发布成功");
		map.put("code",200);
		return map;
	}
	public HashMap<String, Object> getUserSecret(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", param.getInt("pageNumber"));
		paramMap.put("pageSize", param.getInt("pageSize"));
		map.put("data", userSecretMapper.getUserSecretByTop(paramMap));
		map.put("code", 200);
		map.put("msg","查询成功");
		return map;
	}

	@Override
	public HashMap<String, Object> getTop(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", userSecretMapper.getTop(param.getInt("type")));
		map.put("success", 1);
		return map;
	}

	@Override
	public HashMap<String, Object> getPublish(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		map.put("data", userSecretMapper.getPublish(param.getInt("userId")));
		map.put("success", 1);
		return map;
	}

	@Override
	public HashMap<String, Object> getOpenSecretAll(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", userSecretMapper.getOpenSecretAll(param.getInt("userId")));
		map.put("success", 1);
		return map;
	}

	@Override
	public HashMap<String, Object> getBySecretId(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserSecret us = this.userSecretMapper.getBySecretId(param.getInt("secretId"));
		PraiseSecret ps = new PraiseSecret();
		ps.setUserId(param.getInt("userId"));
		ps.setSecretId(param.getInt("secretId"));
		map.put("praise", praiseCommentMapper.selectPraiseSecret(ps)==null?0:1);
		map.put("code", 200);
		map.put("data", us);
		map.put("msg", "查询成功");
		return map;
	}

	@Override
	public HashMap<String, Object> deleteBySecretId(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		userSecretMapper.deleteBySecretId(param.getInt("secretId"));
		userSecretMapper.deleteByOpenSecret(param.getInt("secretId"));
		map.put("success", 1);
		return map;
	}
	@Override
	public HashMap<String, Object> saveSecretPraise(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		userSecretMapper.updatePraise(param.getInt("secretId"));
		PraiseSecret ps = new PraiseSecret();
		ps.setSecretId(param.getInt("secretId"));
		ps.setUserId(param.getInt("userId"));
		praiseCommentMapper.insertPraiseSecret(ps);
		map.put("code", 200);
		map.put("msg", "保存成功");
		return map;
	}
}
