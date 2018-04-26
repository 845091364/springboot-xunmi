package com.springboot.controller;


import java.io.File;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.springboot.utils.CacheClass;
import com.springboot.utils.CacheManager;
import com.springboot.utils.ImageUtil;
import com.springboot.utils.ImageUtil.IMAGE_FORMAT;
import com.springboot.utils.Util;
import com.springboot.utils.WebAppConfig;
/**
 * @ClassName:图片上传、下载
 * @Description:获取验证码
 *
 */
@Controller
public class WebImagesController {

	private Logger log = Logger.getLogger(WebImagesController.class);

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/writeImages")
	@ResponseBody
	public Object writeWebImages(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String path = "";
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile((String) iter
							.next());
					Timestamp now = Util.getTimestampNow();
					String filePath = WebAppConfig.getInstance().getByKey(
							"file_path")
							+ File.separator
							+ Util.Date2String(now)
							+ File.separator;
					String fileName = now.getTime() + "_" + Util.getRandNum(8)
							+ "." + IMAGE_FORMAT.JPG.getValue();
					ImageUtil.genFile(file.getBytes(), filePath, fileName);
					path += "webImages?img=" + fileName + ",";
				}
				path = path.equals("") ? "" : path.substring(0,
						path.length() - 1);
			}
			map.put("path", path);
		} catch (Exception e) {
			log.error("upload faild！！", e);
		}
		return map;
	}

	/**
	 * 获取图片
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/webImages")
	public String getWebImages(String img, int w, int h,
			HttpServletResponse response) throws Exception {
		try {
			if (!Util.isEmpty(img) && w > -1 && h > -1) {
				// 拼接参数
				String fileNameArr = Util.getFileType(img);
				String key = img;
				if (w > 0 && h > 0) {
					key = img.replace("." + fileNameArr, "") + "_" + w + "_"
							+ h + "." + fileNameArr;
				}
				CacheManager.getInstance();
				CacheClass cache = CacheManager.getCacheClassInfo(key);
				if (cache == null || cache.getValue() == null) {
					long timOut = Util.getTimestampNow().getTime() + 1000 * 60
							* 60 * 24L;
					cache = new CacheClass(key, ImageUtil.getFileByImg(img, w,
							h), timOut, false);
					CacheManager.putCacheClass(key, cache);
				}
				byte[] byte_ = (byte[]) cache.getValue();
				response.reset();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("image/jpeg");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + key);
				response.addHeader("Content-Length", byte_.length + "");
				OutputStream os = response.getOutputStream();
				os.write(byte_);
				os.close();
			} else {
				//log.error("webImages params errors!!!");
			}
		} catch (Exception e) {
			//log.error("get image faild！！", e);
		}
		return null;
	}
}
