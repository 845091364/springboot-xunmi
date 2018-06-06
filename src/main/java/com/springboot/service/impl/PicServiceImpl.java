package com.springboot.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mapper.PicMapper;
import com.springboot.model.Pic;
import com.springboot.service.PicService;

import net.sf.json.JSONObject;
@Service
public class PicServiceImpl implements PicService{
	@Autowired
	private PicMapper picMapper;
	@Override
	public HashMap<String, Object> selectPicByUserId(JSONObject param) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("msg","查询成功");
		map.put("code",200);
		map.put("data", picMapper.selectPicByUserId(param.getInt("userId")));
		return map;
	}
	@Override
	public List<Pic> picList(Integer pageNumber, Integer pageSize) {
		
		return picMapper.picList(pageNumber, pageSize);
	}
	@Override
	public void savePic(Pic pic) {
		picMapper.savePic(pic);
	}

}
