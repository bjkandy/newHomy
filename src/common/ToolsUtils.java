package common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ToolsUtils {
	
	/**
	 * 判断字符是否空白 null和""返回true，其他返回false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (null == str)
			return true;
		return "".equals(str);
	}
	
	
	/**
	 * 判断字符是否非空白 null和""返回false，其他返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
	
	/**
	 * 手机号码格式校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		// String regex = "^1[3|4|5|8][0-9]{9}$";
		String regex = "^1[0-9]{10}$";
		return str.matches(regex);
	}
	
	/**
	 * 固定电话号码格式校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) {
		// String regex = "^1[3|4|5|8][0-9]{9}$";
		String regex = "^0\\d{2,3}-?\\d{7,8}$"; 
		return str.matches(regex);
	}
	
	
	/**
	 * 邮箱格式校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
		return str.matches(regex);
	}
	
	/**
	 * 身份证格式校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isIdCard(String str) {
		String regex = "(^\\d{15}$)|(^\\d{17}([0-9]|X|x)$)";
		return str.matches(regex);
	}
	
	/**
	 * 中文校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		String regex = "[\u4e00-\u9fa5]*";
		return str.matches(regex);
	}
	
	/**
	 * 生成随机文件命名
	 * @param k
	 * @return
	 */
	public static String getNo(int k) {
		return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	}
    
    public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static String getRandom(int i) {
		Random jjj = new Random();
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}
	
}
