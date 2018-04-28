package com.springboot.utils;


import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;


/**
 * http工具类
 * 
 * @author pc
 *
 */
public class HttpClientTools {

	protected static Logger logger = Logger.getLogger(HttpClientTools.class);

	/**
	 * @Title: postHttp
	 * @Description: 无头部参数post请求
	 * @author ydfeng
	 * @param url
	 * @param nameValuePairMap
	 * @return
	 * @throws
	 */
	public static String postHttp(String url,
			Map<String, NameValuePair> nameValuePairMap) {
		NameValuePair[] nvps = nameValueMapToArray(nameValuePairMap);
		return postHttp(url, nvps);
	}

	/**
	 * @Title: postHttp
	 * @Description: 有头部参数post请求
	 * @author ydfeng
	 * @param url
	 * @param data
	 * @param header
	 * @return
	 * @throws
	 */
	public static String postHttp(String url, Map<String, NameValuePair> data,
			List<Header> header) {
		NameValuePair[] nvps = nameValueMapToArray(data);
		return postHttp(url, nvps, header);
	}

	/**
	 * 公共方法 post http 请求，接受返回的结果xml文档
	 * 
	 * @param url
	 *            webservice地址
	 * @param paras
	 *            传递的参数
	 * @return Document 对象
	 */
	public static String postHttp(String url, NameValuePair[] parasInput) {
		String result = null;
		PostMethod postMethod = new PostMethod(url);
		try {
			// 重新构造参数数组，是为了加入公共参数：随机数，以防缓存影响执行结果
			NameValuePair[] paras = null;
			if (parasInput != null) {
				paras = new NameValuePair[parasInput.length + 1];
				paras[0] = new NameValuePair("httpRand", ""
						+ Util.getActicleTime());
				for (int i = 0; i < parasInput.length; i++) {
					paras[i + 1] = parasInput[i];
				}
			}
			boolean showLog = Boolean.parseBoolean(WebAppConfig.getInstance()
					.getByKey("showLog"));
			if (showLog) {
				printLog(paras);
			}// 打印所传递的参数，以便调试
			HttpClient client = new HttpClient();
			client.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			postMethod.setRequestBody(paras);// 绑定参数
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {// 如果执行成功
				result = postMethod.getResponseBodyAsString();
			} else {
				logger.error("请求出错,url:" + url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();// 释放连接
		}
		return result;
	}

	/**
	 * @Title: postHttp
	 * @author ydfeng
	 * @param url
	 * @param parasInput
	 * @param header
	 * @return
	 * @throws
	 */
	public static String postHttp(String url, NameValuePair[] parasInput,
			List<Header> header) {
		String result = null;
		PostMethod postMethod = new PostMethod(url);
		try {
			// 重新构造参数数组，是为了加入公共参数：随机数，以防缓存影响执行结果
			NameValuePair[] paras = null;
			if (parasInput != null) {
				paras = new NameValuePair[parasInput.length + 1];
				paras[0] = new NameValuePair("httpRand", "" + UUID.randomUUID());
				for (int i = 0; i < parasInput.length; i++) {
					paras[i + 1] = parasInput[i];
				}
			}
			boolean showLog = Boolean.parseBoolean(WebAppConfig.getInstance()
					.getByKey("showLog"));
			if (showLog) {
				printLog(paras);
			}// 打印所传递的参数，以便调试
			HttpClient client = new HttpClient();
			client.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			if (header != null && !header.isEmpty()) {
				for (Header h : header) {
					postMethod.setRequestHeader(h);// 绑定头部
				}
			}
			postMethod.setRequestBody(paras);// 绑定参数
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {// 如果执行成功
				result = postMethod.getResponseBodyAsString();
			} else {
				logger.error("请求出错,url:" + url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();// 释放连接
		}
		return result;
	}

	private static NameValuePair[] nameValueMapToArray(
			Map<String, NameValuePair> map) {
		if (map == null)
			return new NameValuePair[0];
		NameValuePair[] nvps = new NameValuePair[map.size()];
		Iterator<String> it = map.keySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			String key = (String) it.next();
			nvps[i] = (NameValuePair) map.get(key);
			i++;
		}
		return nvps;
	}

	/**
	 * 打印传递的参数
	 * 
	 * @param paras
	 */
	private static void printLog(NameValuePair[] paras) {
		String paraString = "";
		if (paras != null) {
			for (int i = 0; i < paras.length; i++) {
				if (paras[i] != null) {
					if (i != 0)
						paraString += "&";
					paraString += paras[i].getName() + "="
							+ paras[i].getValue();
				}
			}
		}
		logger.info("post-参数：" + paraString);
	}

	public static void main(String[] args) {
		
		try {
			Map<String, NameValuePair> nameValuePairMap = new HashMap<String, NameValuePair>();
			NameValuePair data = new NameValuePair(
					"data",
					"aa006a4746ee9368f48ef597fcca2e77");
			NameValuePair query = new NameValuePair("queryid", "112");  
			nameValuePairMap.put("queryid", query);
			nameValuePairMap.put("data", data);
			logger.info(HttpClientTools.postHttp( "http://localhost:8080/interface", nameValuePairMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}
