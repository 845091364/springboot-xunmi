package com.springboot.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mapper.UserSecretMapper;
import com.springboot.model.OpenSecret;
import com.springboot.service.OpenSecretService;

import net.sf.json.JSONObject;

@Service
public class OpenSecretServiceImpl implements OpenSecretService {
	@Autowired
	private UserSecretMapper userSecretMapper;

	@Override
	public HashMap<String, Object> saveOpenSecret(JSONObject param) {
		HashMap<String, Object> map = new HashMap<>();
		OpenSecret op = new OpenSecret();
		op.setPicId(param.getInt("picId"));
		op.setSecretId(param.getInt("secretId"));
		op.setUserId(param.getInt("userId"));
		userSecretMapper.insertOpenSecret(op);
		map.put("code", 200);
		map.put("msg", "保存成功");
		return map;
	}

}
