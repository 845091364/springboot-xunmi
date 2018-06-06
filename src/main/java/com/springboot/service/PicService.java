package com.springboot.service;

import java.util.HashMap;
import java.util.List;

import com.springboot.model.Pic;

import net.sf.json.JSONObject;

public interface PicService {
	public  HashMap<String, Object> selectPicByUserId(JSONObject param);
	
	List<Pic> picList(Integer pageNumber,Integer pageSize);
	
	public void   savePic(Pic pic);
}
