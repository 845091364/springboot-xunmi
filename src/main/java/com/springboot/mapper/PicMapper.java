package com.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springboot.model.Pic;

public interface PicMapper {
	
	  public  Pic selectPicByUserId(Integer userId);
	  
	  public List<Pic> picList(@Param("pageNumber")Integer pageNumber, @Param("pageSize")Integer pageSize);
	  
	  public  void savePic(Pic pic);
}
