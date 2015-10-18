package com.qsms.util;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import net.sourceforge.pinyin4j.PinyinHelper;

public class Helper {

	/**
	 * 
	 * @param str
	 * @param mode
	 *            mode=1全拼模式；其他为简拼模式
	 * @return
	 */
	public static String converPinYin(String str, int mode) {
		str = delSESpaceStr(str);
		char py[] = str.trim().toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (py[i] >= 'A' && py[i] <= 'Z' || py[i] >= 'a' && py[i] <= 'z'
					|| py[i] >= '0' && py[i] <= '9') {
				sb.append(py[i]);
			} else {
				try {
					String[] pinyin = PinyinHelper
							.toHanyuPinyinStringArray(py[i]);
					if (mode == 1) {// 全拼模式
						sb.append(pinyin[0].substring(0, pinyin[0].length() - 1));
					} else {
						sb.append(pinyin[0].substring(0, 1));
					}
				} catch (Exception e) {
					sb.append(py[i]);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Email校验
	 * 
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email) {
		boolean isValid = false;
		try {
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return isValid;
	}

	/**
	 * 获取目录文件总大小
	 * 
	 * @param file
	 * @return
	 */
	public static double getDirSize(File file) {
		if (file.exists()) {// 判断文件是否存在
			if (file.isDirectory()) {// 如果是目录则递归计算其内容的总大小
				File[] children = file.listFiles();
				double size = 0;
				for (File f : children) {
					size += getDirSize(f);
				}
				return size;
			} else {// 如果是文件则直接返回其大小,以“兆”为单位
				double size = (double) file.length() / 1024 / 1024;
				BigDecimal b = new BigDecimal(size);
				return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();// 保留2位小数
			}
		} else {
			System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
			return 0.0;
		}
	}

	/**
	 * 获取当前的日期字串
	 * 
	 * @return yyyyMMdd (如20150527)
	 */
	public static String genDateString() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}

	public static String generatorShortID() {
		return Long.toHexString(System.currentTimeMillis()).toUpperCase() + "-"
				+ getRandomString(4);
	}

	public static String generatorID() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss-");
		Date date = new Date();
		String dateStr = sdf.format(date);
		String id = dateStr + getRandomString(8);
		return id;
	}

	/**
	 * 获取Email验证码
	 * 
	 * @return
	 */
	public static String emailCode() {
		return getRandomString(6);
	}

	/**
	 * 获取指定长度的随机串
	 * 
	 * @param length
	 * @return
	 */
	private static String getRandomString(int length) {
		String base = "ABCDEFGHIJKLMNOPQURSTUVWXYZ";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 随机获取指定范围内的数字
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomRange(int min, int max) {
		Random random = new Random();
		int result = random.nextInt(max) % (max - min + 1) + min;
		return result;
	}

	/**
	 * 删除前后空白字符串
	 */
	private static String delSESpaceStr(String str) {
		String beforeSttring = str;
		if (str.startsWith(" ")) {
			beforeSttring = str.replaceFirst("( )+", "");
		}
		StringBuffer sb = new StringBuffer(beforeSttring);
		while (sb.toString().endsWith(" ")) {
			sb.deleteCharAt(sb.toString().lastIndexOf(" "));
		}
		return sb.toString();
	}

	/**
	 * 从富文本中获取图片URL
	 * 
	 * @param content
	 * @return
	 */
	public static List<String> imagelist(String content) {
		if (content == null || "".equals(content)) {
			return new ArrayList<String>();
		}
		List<String> list = new ArrayList<String>();
		String[] imgs = content.replaceAll("\"", "").replaceAll("'", "")
				.split("<img(\\s)+");
		for (String img : imgs) {
			if (img.contains("src")) {
				int start = img.indexOf("src");
				start = img.indexOf("=", start);
				int end = img.indexOf(" ", start + 10);
				int lgend = img.indexOf(">", start);
				if (end > lgend) {
					end = img.indexOf(">", start);
				}
				if (end == -1) {
					end = img.indexOf(">", start);
				}
				String url = img.substring(start + 1, end);
				list.add(url.trim());
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(randomRange(5, 1000));
	}
}
