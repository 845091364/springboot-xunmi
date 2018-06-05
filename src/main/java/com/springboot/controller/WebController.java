package com.springboot.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequestMapping("signinXunMi")
	public String signinXunMi() {
		return "signinXunMi";
	}
	@RequestMapping(value = "pic", method = RequestMethod.GET)
	@ResponseBody
	public List<Pic> picList(){
		
		List<Pic>  picList= picService.picList(0,10);
		return	picList;
	}
	@RequestMapping(value = "login",method=RequestMethod.PUT)
	public String login(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name="password",required = false) String password){
		if(username.equals("xunmi")&&password.equals("xunmi123")) {
			return "index";
		}else {
			return "signinXunMi";
		}
	}
}
