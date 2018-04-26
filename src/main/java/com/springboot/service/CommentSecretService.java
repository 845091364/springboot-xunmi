package com.springboot.service;

import java.util.HashMap;
import net.sf.json.JSONObject;

public interface CommentSecretService {
	public HashMap<String, Object> saveComment(JSONObject paramJSONObject);
	
	public HashMap<String, Object> getComment(JSONObject paramJSONObject);
}
