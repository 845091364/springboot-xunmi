package com.springboot.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicBorders.FieldBorder;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.model.FileBootStrap;
import com.springboot.model.Pic;
import com.springboot.service.PicService;
import com.springboot.utils.ImageUtil;

import net.sf.json.JSONObject;

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
	@RequestMapping(value = "login",method=RequestMethod.POST)
	public void login(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name="password",required = false) String password,HttpServletResponse response) throws IOException{
		if(username.equals("xunmi")&&password.equals("xunmi123")) {
			response.sendRedirect("index");
		}else {
			response.sendRedirect("/signinXunMi.html?tips=账号或密码错误");
		}
	}
	@RequestMapping(value = "/fileUpload",method=RequestMethod.POST)
	@ResponseBody
	public String fileUpload(HttpServletRequest request) {
		 FileBootStrap f = new FileBootStrap();
		 f.setUrl(ImageUtil.uploadMoreFile(request).get("url").toString());
		 return JSONObject.fromObject(f).toString();
	}
	@RequestMapping(value = "/picAdd",method=RequestMethod.POST)
	@ResponseBody
	public void picAdd(@RequestParam(name = "pic", required = false) String pic) {
		String[] pics=pic.split(",");
		Pic  p=  new Pic();
		p.setOldPic(pics[0]);
		p.setPic(pics[1]);
		picService.savePic(p);
	}
}
