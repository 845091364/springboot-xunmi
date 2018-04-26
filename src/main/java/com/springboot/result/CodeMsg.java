package com.springboot.result;

/**
 * @author liuyi
 *
 * @Date 2018年1月30日
 */
public class CodeMsg {

	private int code;
	private String msg;

	// 通用的错误码
	public static final CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static final CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
	public static final CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
	public static final CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法");
	public static final CodeMsg ACCESS_LIMIT_REACHED = new CodeMsg(500104, "访问太频繁！");

	private CodeMsg() {
	}

	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code, message);
	}

	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}

}
