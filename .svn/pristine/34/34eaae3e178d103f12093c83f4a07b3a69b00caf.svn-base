package com.hanson.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

/**
 * json参数key值
 * 
 * @author lanxiangru
 * 
 */
public class JsonUtils {
	
	public static final String ERRORCODE = "errorCode";

	public static final String MESSAGE = "message";

    public static final String SUCCESS_ERRCODE = "000000";// 成功错误码
	
	public static final String ERRCODE_EXCEPTION = "999999";
    public static final String STORE_EXIST="33333";
    public static final String STORE_EXIST_MESSAGE="此登录名已经存在";
	public static final String MESSAGE_SUCCESS = "操作成功.";
	public static final String  OPERATOR_STATUS="111111";
	public static final String EXIST_STATUS="22222";
	public static final String MESSAGE_OUTPHOTO="此功能尚未开通";
    public static final String MESSAGE_EXIST="此功能已经开通,产品复制完成.";
	public static final String MESSAGE_EXCEPTION = "系统内部错误.";
	
	
	/**
     * 
     * @Title: renderSuccess
     * @Description: 返回操作成功
     * @throws
     */
	public static void storeexist(JSONObject jo){
		jo.put(ERRORCODE, STORE_EXIST);
		jo.put(MESSAGE, STORE_EXIST_MESSAGE);
	}
	public static void renderSuccess(JSONObject jo) {
        jo.put(ERRORCODE, SUCCESS_ERRCODE);
        jo.put(MESSAGE, MESSAGE_SUCCESS);
    }
	public static void renderoutphot(JSONObject jo){
		jo.put(ERRORCODE, OPERATOR_STATUS);
		jo.put(MESSAGE,MESSAGE_OUTPHOTO);
	}
	public static void renderexist(JSONObject jo){
		jo.put(ERRORCODE, EXIST_STATUS);
		jo.put(MESSAGE,MESSAGE_EXIST);
	}
	/**
     * 
     * @Title: renderException
     * @Description: 返回系统内部错误
     * @throws
     */
	public static void renderException(JSONObject jo) {
        jo.put(ERRORCODE, ERRCODE_EXCEPTION);
        jo.put(MESSAGE, MESSAGE_EXCEPTION);
    }
    

	/**
	 * 初始化json对象
	 */
	public static JSONObject initInstance(JSONObject json) {
		if (null == json) {
			json = new JSONObject();
		} else {
			json.clear();
		}
		return json;
	}
	
	/**
	 * 允许entity上出现双向映射, 过滤空属性
	 * @param o
	 * @return
	 */
	public static JSONObject parseCircleObject(Object o) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setJsonPropertyFilter(new NotNullPropertyFilter());
		return JSONObject.fromObject(o, jsonConfig);
	}
	
	/**
	 * jsonObject
	 * @param o
	 * @return
	 */
	public static JSONObject parseNotNullObject(Object o) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new NotNullPropertyFilter());
		return JSONObject.fromObject(o, jsonConfig);
	}
	
	/**
	 * jsonArray
	 * @param o
	 * @return
	 */
	public static JSONArray parseNotNullArray(Object o) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new NotNullPropertyFilter());
		return JSONArray.fromObject(o, jsonConfig);
	}
	
	/**
	 * jsonArray
	 * 过滤某些字段
	 * @param o
	 * @param fields
	 * @return
	 */
	public static JSONArray parseNotNullArray(Object o,String...fields) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(fields);
		jsonConfig.setJsonPropertyFilter(new NotNullPropertyFilter());
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONArray.fromObject(o, jsonConfig);
	}
	
	public static class NotNullPropertyFilter implements PropertyFilter {

		@Override
		public boolean apply(Object arg0, String arg1, Object arg2) {
			// TODO Auto-generated method stub
			return arg2 == null;
		}
		
	}
	
	/**
	 * 过滤某些字段
	 * @param o
	 * @param fields
	 * @return
	 */
	public static JSONObject parseCircleObject(Object o, String...fields) {
//		JsonConfig jsonConfig = new JsonConfig();
//		MyPropertyFilter pf = new MyPropertyFilter();
//		pf.setFielterFields(fields);
//		pf.setAllowNullFields(true);
//		jsonConfig.setJsonPropertyFilter(pf);
//		return JSONObject.fromObject(o, jsonConfig);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(fields);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setJsonPropertyFilter(new NotNullPropertyFilter());
		return JSONObject.fromObject(o, jsonConfig);
	}
	
	
}
