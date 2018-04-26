package com.springboot.exception;

import com.springboot.result.CodeMsg;

/**
 * @author liuyi
 *
 * @Date 2018年1月30日
 */
public class GlobalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private CodeMsg codeMsg;

	public GlobalException(CodeMsg codeMsg) {
		super(codeMsg.toString());
		this.codeMsg = codeMsg;
	}

	public CodeMsg getCodeMsg() {
		return codeMsg;
	}

}
