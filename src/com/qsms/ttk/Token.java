package com.qsms.ttk;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Token {

	private static final String accesskey = "796771fd341e2d24bdcd8215f2fd6f5ae4853921";// 系统生成的accesskey
	private static final String secretKey = "a8a8cc6463ad68348ff975b01a0bec8f10a309fd";// 系统生成的secretKey

	public static String createToken(Map<String, Object> param) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(param);
		String base64param = Base64Util.base64(json.getBytes()).trim();
		String sign = HmacUtil.hmac_sha1(base64param, secretKey);
		String token = accesskey + ":" + sign + ":" + base64param;
		return token;
	}

	/**
	 * 创建TOKEN
	 * 
	 * @param deadlineTime
	 * @param albumId
	 * @return
	 */
	public static String createToken(long deadlineTime, long albumId) {
		String json = "{\"deadline\":%s ,\"album\":\"%s\",\"returnBody\":\"\"}";
		json = String.format(json, deadlineTime, albumId);
		String base64param = Base64Util.base64(json.getBytes()).trim();
		String sign = HmacUtil.hmac_sha1(base64param, secretKey);
		String token = accesskey + ":" + sign + ":" + base64param;
		return token;
	}

	/**
	 * 创建TOKEN
	 * 
	 * @param deadlineTime
	 * @param albumId
	 * @return
	 */
	public static String createToken(long deadlineTime) {
		String json = "{\"deadline\":%s}";
		json = String.format(json, deadlineTime);
		String base64param = Base64Util.base64(json.getBytes()).trim();
		String sign = HmacUtil.hmac_sha1(base64param, secretKey);
		String token = accesskey + ":" + sign + ":" + base64param;
		return token;
	}

	/**
	 * 创建TOKEN
	 * 
	 * @param deadlineTime
	 * @param albumId
	 * @param returnJson
	 * @return
	 */
	public static String createToken(long deadlineTime, long albumId, String returnJson) {
		String json = "{\"deadline\":%s ,\"album\":\"%s\",\"returnBody\":%s}";
		json = String.format(json, deadlineTime, albumId, returnJson);
		String base64param = Base64Util.base64(json.getBytes()).trim();
		String sign = HmacUtil.hmac_sha1(base64param, secretKey);
		String token = accesskey + ":" + sign + ":" + base64param;
		return token;
	}

	/**
	 * 创建TOKEN
	 * 
	 * @param deadlineTime
	 * @param albumId
	 * @param returnJson
	 * @return
	 */
	public static String createToken(String accesskey, String secretKey, long deadlineTime, long albumId, String returnJson) {
		String json = "{\"deadline\":%s ,\"album\":\"%s\",\"returnBody\":\"%s\"}";
		json = String.format(json, deadlineTime, albumId, returnJson);
		String base64param = Base64Util.base64(json.getBytes()).trim();
		String sign = HmacUtil.hmac_sha1(base64param, secretKey);
		String token = accesskey + ":" + sign + ":" + base64param;
		return token;
	}
}
