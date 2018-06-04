package com.springboot.service;

import java.util.HashMap;

import net.sf.json.JSONObject;

public interface OpenSecretService {
	public  HashMap<String, Object> saveOpenSecret(JSONObject param);
}
