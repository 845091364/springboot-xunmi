package com.springboot.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.result.CodeMsg;
import com.springboot.result.Result;

/**
 * @author liuyi
 *
 * @Date 2018年1月30日
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
		e.printStackTrace();
		if (e instanceof GlobalException) {
			GlobalException ex = (GlobalException) e;
			return Result.error(ex.getCodeMsg());
		} else {
			return Result.error(CodeMsg.SERVER_ERROR);
		}
	}
}
