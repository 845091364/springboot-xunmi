package com.springboot.service.impl;

import com.springboot.exception.GlobalException;
import com.springboot.mapper.UserMapper;
import com.springboot.model.User;
import com.springboot.model.UserAttention;
import com.springboot.result.CodeMsg;
import com.springboot.service.UserService;
import com.springboot.utils.SHA1;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisCluster jedisCluster;

	public List<User> getUsers() {
		return this.userMapper.getUsers();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public HashMap<String, Object> addUser(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		try {
			User u = new User();
			u.setName("wangwu");
			u.setPassword("123456");
			this.userMapper.insertSelective(u);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(CodeMsg.ACCESS_LIMIT_REACHED);
		}
		return map;
	}

	public HashMap<String, Object> sendRegister(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		map.put("success", Integer.valueOf(0));
		if (param.containsKey("phone")) {
			User record = new User();
			record.setPhone(param.getString("phone"));
			if (this.userMapper.selectByKey(record) == null) {
				map.put("success", 1);
			} else {
				map.put("tips", "手机号已经被注册啦!");
			}
		} else {
			map.put("tips", "数据格式错误");
		}
		return map;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public HashMap<String, Object> validRegister(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		int success = 0;
		boolean isValid = true;
		User u = new User();

		u.setPhone(param.getString("phone"));
		u.setPassword(SHA1.sha1(param.getString("password")));
		this.userMapper.insert(u);
		if (isValid) {
			success = 1;
		}
		map.put("user", u);
		map.put("success", success);
		return map;
	}

	public HashMap<String, Object> login(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		int success = 0;
		User u = new User();
		u.setPhone(param.getString("phone"));
		u.setPassword(SHA1.sha1(param.getString("password")));
		User user = this.userMapper.selectByKey(u);
		if (user != null) {
			success = 1;
			map.put("user", user);
		}
		map.put("success", success);
		return map;
	}

	public HashMap<String, Object> getAttention(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		map.put("data", this.userMapper.getUserAttention(param.getString("userId")));
		map.put("success", 1);
		return map;
	}

	public HashMap<String, Object> updateUser(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		User user = new User();
		user.setId(Integer.valueOf(param.getInt("id")));
		if (param.containsKey("name")) {
			user.setName(param.getString("name"));
		}
		if (param.containsKey("description")) {
			user.setDescription(param.getString("description"));
		}
		if (param.containsKey("sex")) {
			user.setSex(Integer.valueOf(param.getInt("sex")));
		}
		if (param.containsKey("birthday")) {
			user.setBirthday(param.getString("birthday"));
		}
		this.userMapper.updateByPrimaryKey(user);
		map.put("success", 1);
		return map;
	}

	public HashMap<String, Object> updateUserPassword(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		User user = new User();
		if (param.containsKey("id")) {
			user.setId(Integer.valueOf(param.getInt("id")));
		}
		if (param.containsKey("name")) {
			user.setName(param.getString("name"));
		}
		if (param.containsKey("description")) {
			user.setDescription(param.getString("description"));
		}
		if (param.containsKey("sex")) {
			user.setSex(Integer.valueOf(param.getInt("sex")));
		}
		if (param.containsKey("phone")) {
			user.setPhone(param.getString("phone"));
		}
		if (param.containsKey("password")) {
			user.setPassword(SHA1.sha1(param.getString("password")));
		}
		this.userMapper.updateByPrimaryKey(user);
		map.put("success", 1);
		return map;
	}

	public HashMap<String, Object> validRegisterTWO(JSONObject param) {
		return null;
	}

	public HashMap<String, Object> addAttention(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		UserAttention at = new UserAttention();
		at.setAttentionId(Integer.valueOf(param.getInt("attentionId")));
		at.setUserId(Integer.valueOf(param.getInt("userId")));
		if (param.getInt("type") == 1) {
			this.userMapper.insertUserAttention(at);
		}
		if (param.getInt("type") == 2) {
			this.userMapper.deleteUserAttention(at);
		}
		map.put("success", 1);
		return map;
	}

	public HashMap<String, Object> findAttention(JSONObject param) {
		HashMap<String, Object> map = new HashMap();
		UserAttention at = new UserAttention();
		at.setAttentionId(Integer.valueOf(param.getInt("attentionId")));
		at.setUserId(Integer.valueOf(param.getInt("userId")));
		map.put("user", this.userMapper.findUserAttention(at));
		map.put("success", 1);
		return map;
	}
}
