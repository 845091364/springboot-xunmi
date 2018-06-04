package com.springboot.mapper;

import com.springboot.model.Pic;

public interface PicMapper {
	
	  public  Pic selectPicByUserId(Integer userId);
}
