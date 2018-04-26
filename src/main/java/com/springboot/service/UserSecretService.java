package com.springboot.service;

import java.util.HashMap;
import net.sf.json.JSONObject;

public interface UserSecretService {
	public HashMap<String, Object> addUserSecret(JSONObject paramJSONObject);

	public HashMap<String, Object> getUserSecret(JSONObject paramJSONObject);

	public HashMap<String, Object> getQuestions();
	
	public  HashMap<String, Object> getTop(JSONObject param);
}
