package com.qsms.ttk;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsms.ttk.json.Album;
import com.qsms.ttk.json.AlbumData;
import com.qsms.ttk.json.UpLoadImage;

public class TieTuKuClient {

	private static final String ALBUM_URL = "http://api.tietuku.com/v1/Album";
	private static final String UPLOAD_URL = "http://up.tietuku.com/";

	/**
	 * 
	 * @param file
	 *            ：本地图片文件
	 * @param aid
	 *            ：相册id
	 */
	public static UpLoadImage upload(File file, int aid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deadline", System.currentTimeMillis() + 60);
		params.put("aid", aid);
		params.put("from", "file");
		try {
			String token = Token.createToken(params);
			params.put("Token", token);
			String json = post(UPLOAD_URL, params, file);
			ObjectMapper mapper = new ObjectMapper();
			UpLoadImage upImage = mapper.readValue(json, UpLoadImage.class);
			return upImage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 上传网络图片
	 * 
	 * @param fileUrl
	 *            ：网络图片路径
	 * @param aid
	 * @return
	 */
	public static UpLoadImage upload(String fileUrl, int aid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deadline", System.currentTimeMillis() + 3000);
		params.put("aid", aid);
		params.put("from", "web");
		params.put("fileurl", fileUrl);
		try {
			String token = Token.createToken(params);
			params.put("Token", token);
			String json = post(UPLOAD_URL, params, null);
			ObjectMapper mapper = new ObjectMapper();
			UpLoadImage upImage = mapper.readValue(json, UpLoadImage.class);
			return upImage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取相册列表
	 */
	public static AlbumData listAlbum() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deadline", System.currentTimeMillis() + 60);
		params.put("action", "get");
		try {
			String token = Token.createToken(params);
			params.put("Token", token);
			String json = post(ALBUM_URL, params, null);
			ObjectMapper mapper = new ObjectMapper();
			AlbumData albumData = mapper.readValue(json, AlbumData.class);
			return albumData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 创建相册
	 */
	public static Album addAlbum(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deadline", System.currentTimeMillis() + 60);
		params.put("action", "create");
		params.put("albumname", name);
		try {
			String token = Token.createToken(params);
			params.put("Token", token);
			String json = post(ALBUM_URL, params, null);
			ObjectMapper mapper = new ObjectMapper();
			Album album = mapper.readValue(json, Album.class);
			return album;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 删除相册
	 */
	public static String deleteAlbum(int aid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deadline", System.currentTimeMillis() + 60);
		params.put("action", "delalbum");
		System.out.println("!!!");
		params.put("albumname", aid);
		try {
			String token = Token.createToken(params);
			params.put("Token", token);
			String json = post(ALBUM_URL, params, null);
			ObjectMapper mapper = new ObjectMapper();
			Album album = mapper.readValue(json, Album.class);
			return album.getCode();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String post(String url, Map<String, Object> params, File file) {
		MultipartEntity reqEntity = new MultipartEntity();
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		try {
			if (file != null) {
				FileBody bin = new FileBody(file);
				reqEntity.addPart("file", bin);
			}
			for (String key : params.keySet()) {
				reqEntity.addPart(key, new StringBody(params.get(key) + ""));
			}
			httppost.setEntity(reqEntity);
			HttpResponse response = httpclient.execute(httppost);
			InputStream in = response.getEntity().getContent();
			BufferedReader bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = bf.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) {
		// listAlbum();
		// upload("F:/mm图片/1.jpg");
		addAlbum("test1");
	}
}
