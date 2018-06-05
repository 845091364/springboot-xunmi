package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.model.Pic;
import com.springboot.service.PicService;

@Controller
public class WebController {
	@Autowired
	private PicService picService;
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	@RequestMapping(value = "pic", method = RequestMethod.GET)
	@ResponseBody
	public List<Pic> picList(){
		
		List<Pic>  picList= picService.picList(0,10);
		return	picList;
	}
}
