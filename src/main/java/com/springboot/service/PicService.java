package com.springboot.service;

import java.util.HashMap;

import net.sf.json.JSONObject;

public interface PicService {
	public  HashMap<String, Object> selectPicByUserId(JSONObject param);
}
