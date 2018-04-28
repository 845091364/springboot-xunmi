package com.springboot.service;

import com.springboot.model.User;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONObject;

public interface UserService {
	public List<User> getUsers();

	public HashMap<String, Object> addUser(JSONObject paramJSONObject);

	public HashMap<String, Object> sendRegister(JSONObject paramJSONObject);

	public HashMap<String, Object> validRegister(JSONObject paramJSONObject);

	public HashMap<String, Object> validRegisterTWO(JSONObject paramJSONObject);

	public HashMap<String, Object> login(JSONObject paramJSONObject);

	public HashMap<String, Object> getAttention(JSONObject paramJSONObject);

	public HashMap<String, Object> updateUser(JSONObject paramJSONObject);

	public HashMap<String, Object> updateUserPassword(JSONObject paramJSONObject);

	public HashMap<String, Object> findAttention(JSONObject paramJSONObject);

	public HashMap<String, Object> addAttention(JSONObject paramJSONObject);
	
	public HashMap<String, Object> finUserById(JSONObject paramJSONObject);
	
}
