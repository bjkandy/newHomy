package common;

import java.util.regex.Matcher;



import java.util.regex.*;
public class ServiceUtils {
	
	/**
	 * 检查传入的字符串是否格式正确的电话号码
	 * 
	 * @param target
	 *            待检查的字符串
	 */
	public static Boolean telephoneLegal(String telephone) {
		if (null == telephone || "".equals(telephone))
			return false;
		
		// 目前国内手机号码段只有13/15/18开头，且各号码段也有限制（例如18开头只有180/185/186/187/188/189段，15开头只有154段没有）
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(telephone);
		return m.matches();
	}
	
	/**
	 * 检查用户名命名是否合法（如长度不合规定、存在不合法字符等）
	 * 
	 * @param userName
	 *            被检查的用户名
	 * @param 用户名是否合法
	 */
	public static Boolean userNameLegal(String userName) {
		if (null == userName || "".equals(userName))
			return false;
		
		// 用户名限制规则：长度4~18字符，字母开头，只能包含大小写字母、数字、下划线_
		Pattern p = Pattern.compile("^[a-zA-Z][A-Za-z0-9_]{3,17}$");
		Matcher m = p.matcher(userName);
		return m.matches();
	}
	
	/**
	 * 检查传入的字符串是否格式正确的邮件地址
	 * 
	 * @param target
	 *            待检查的字符串
	 */
	public static Boolean emailLegal(String email) {
		if (null == email || "".equals(email))
			return false;
		String emailLowCase = email.toLowerCase();
		
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); // 简单匹配
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(emailLowCase);
		return m.matches();
	}
	

	


}
