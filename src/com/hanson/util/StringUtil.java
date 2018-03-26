package com.hanson.util;

import org.apache.commons.lang.StringUtils;


public class StringUtil {
    public static boolean isChinese(String key){  
        if(key != null && !"".equals(key) && !"".equals(key.trim())){  
            byte []bytes = key.getBytes();     
            int i = bytes.length;//iΪ�ֽڳ���     
            int j = key.length();//jΪ�ַ�����     
            if(i != j){  
                return true;   
            }  
        }         
        return false;  
    }  
	/**
	 * �ж��Ƿ�Ϊ����
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
	* @Description: TODO(������һ�仰�����������������)
	* @param @param param
	* @param @return    �趨�ļ�
	* @return boolean    ��������
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
	 * �滻��β{}
	 * @param str ��ʽ:{a,b,c}
	 * @return resStr ��ʽ:a,b,c 
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