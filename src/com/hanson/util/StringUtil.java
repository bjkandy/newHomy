package com.hanson.util;

import org.apache.commons.lang.StringUtils;


public class StringUtil {
    public static boolean isChinese(String key){  
        if(key != null && !"".equals(key) && !"".equals(key.trim())){  
            byte []bytes = key.getBytes();     
            int i = bytes.length;//i为字节长度     
            int j = key.length();//j为字符长度     
            if(i != j){  
                return true;   
            }  
        }         
        return false;  
    }  
	/**
	 * 判断是否为数字
	 * 
	 */
	public static boolean isDigit(String param) {
		char params[] = param.toCharArray();
		for (int i = 0; i < params.length; i++) {
			if (!Character.isDigit(params[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**
	* @Title: isNullStr
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param param
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean isNullStr(String param){
		if(null == param || "".equals(param)){
			return true;
		}
		return false;
	}
	public static String[] split(String str,String separatorChars){
		if(null != str && !"".equals(str)){
			if(str.indexOf("{") == 0){
				str = StringUtils.substring(str, 1);
			}
			if(str.indexOf("}") == str.length()-1){
				str = StringUtils.substring(str, 0,str.length()-1);
			}
		}
		return StringUtils.split(str, separatorChars);
	}
	
	/**
	 * 替换首尾{}
	 * @param str 格式:{a,b,c}
	 * @return resStr 格式:a,b,c 
	 */
	public static String replaceQuat(String str){
		if(null != str && !"".equals(str)){
			if(str.indexOf("{") == 0){
				str = StringUtils.substring(str, 1);
			}
			if(str.indexOf("}") == str.length()-1){
				str = StringUtils.substring(str, 0,str.length()-1);
			}
		}
		return str;
	}
}