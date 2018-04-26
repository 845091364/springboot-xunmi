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
		map.put("success", 1);
		return map;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public HashMap<String, Object> getUserSecret(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		OpenSecret openSecret;
		UserSecret us;
		OpenSecret os;
		User user = new User();
		while(true){
			openSecret = new OpenSecret();
			us = this.userSecretMapper.getUserSecret();
			openSecret.setSecretId(us.getId());
			openSecret.setUserId(Integer.valueOf(param.getInt("userId")));
			os = this.userSecretMapper.getOpenSecret(openSecret);
			if(os == null){
				user.setId(us.getUserId());
				this.userSecretMapper.insertOpenSecret(openSecret);
				break;
			}
			continue;
		}
		UserAttention at = new UserAttention();
		at.setAttentionId(us.getUserId());
		at.setUserId(Integer.valueOf(param.getInt("userId")));
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
		map.put("data", userSecretMapper.getTop());
		map.put("success", 1);
		return map;
	}
}
