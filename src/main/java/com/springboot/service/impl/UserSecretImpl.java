package com.springboot.service.impl;

import com.springboot.mapper.CommentSecretMapper;
import com.springboot.mapper.UserMapper;
import com.springboot.mapper.UserSecretMapper;
import com.springboot.model.OpenSecret;
import com.springboot.model.Questions;
import com.springboot.model.User;
import com.springboot.model.UserAttention;
import com.springboot.model.UserSecret;
import com.springboot.service.UserSecretService;
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
	private UserMapper userMapper;
	@Autowired
	private CommentSecretMapper commentSecretMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public HashMap<String, Object> addUserSecret(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		UserSecret record = new UserSecret();
		record.setContent(param.getString("content"));
		record.setUserId(Integer.valueOf(param.getInt("userId")));
		this.userSecretMapper.insert(record);
		this.userMapper.updatePublishCount(param.getInt("userId"));
		map.put("success", 1);
		return map;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public HashMap<String, Object> getUserSecret(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		User user = new User();
		OpenSecret openSecret = new OpenSecret();
		openSecret.setUserId(param.getInt("userId"));
		List<OpenSecret> os = this.userSecretMapper.getOpenSecret(openSecret);
		UserSecret us = this.userSecretMapper.getUserSecret(os);
		openSecret.setSecretId(us.getId());
		openSecret.setUserId(Integer.valueOf(param.getInt("userId")));
		user.setId(us.getUserId());
		this.userSecretMapper.insertOpenSecret(openSecret);
		UserAttention at = new UserAttention();
		at.setAttentionId(us.getUserId());
		at.setUserId(Integer.valueOf(param.getInt("userId")));
		this.userMapper.updateOpenCount(param.getInt("userId"));
		map.put("attention", Integer.valueOf(this.userMapper.findUserAttention(at) == null ? 0 : 1));
		map.put("user", this.userMapper.selectByKey(user));
		map.put("data", us);
		map.put("success", 1);
		return map;
	}

	public HashMap<String, Object> getQuestions() {
		HashMap<String, Object> map = new HashMap();
		List<Questions> qsList = this.userSecretMapper.getQuestions();
		for (Questions qs : qsList) {
			qs.setQuestionsAnswer(this.userSecretMapper.getAnswer(qs.getId().intValue()));
		}
		map.put("data", qsList);
		map.put("success", 1);
		return map;
	}

	@Override
	public HashMap<String, Object> getTop(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
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
		HashMap<String, Object> map = new HashMap();
		map.put("data", userSecretMapper.getOpenSecretAll(param.getInt("userId")));
		map.put("success", 1);
		return map;
	}

	@Override
	public HashMap<String, Object> getBySecretId(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		User user = new User();
		UserSecret us = this.userSecretMapper.getBySecretId(param.getInt("secretId"));
		user.setId(us.getUserId());
		map.put("user", this.userMapper.selectByKey(user));
		map.put("data", us);
		map.put("success", 1);
		return map;
	}

	@Override
	public HashMap<String, Object> deleteBySecretId(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		userSecretMapper.deleteBySecretId(param.getInt("secretId"));
		userSecretMapper.deleteByOpenSecret(param.getInt("secretId"));
		map.put("success", 1);
		return map;
	}
}
