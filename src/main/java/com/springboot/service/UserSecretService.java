package com.springboot.service;

import java.util.HashMap;
import net.sf.json.JSONObject;

public interface UserSecretService {
	public HashMap<String, Object> addUserSecret(JSONObject paramJSONObject);

	public HashMap<String, Object> getUserSecret(JSONObject paramJSONObject);

	public  HashMap<String, Object> getTop(JSONObject param);
	
	public  HashMap<String, Object> saveSecretPraise(JSONObject param);
	
	public  HashMap<String, Object> getPublish(JSONObject param);
	
	public  HashMap<String, Object> getOpenSecretAll(JSONObject param);
	
	public  HashMap<String, Object> getBySecretId(JSONObject param);
	
	public  HashMap<String, Object> deleteBySecretId(JSONObject param);
	
}
