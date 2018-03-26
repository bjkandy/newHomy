package com.hanson.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String valueToString(Object value){
    	try {
    		return objectMapper.writeValueAsString(value);
		} catch (Exception e) {
		}
		return null;
    }
    
    public static <T>  Object jsonToObject(String jacksonValue, Class<?> T) throws JsonParseException, JsonMappingException, IOException{
        return objectMapper.readValue(jacksonValue, T);
    }
    

    public static <T> T readValue (String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    /**
	 * Json 字符串 转换成Collection对象
	 * @param <U>
	 * @param <T>
	 * @param json
	 * @param uClazz
	 * @param tClazz
	 * @return
	 *//*
	public static <U extends Collection<T>,T> U readValue(String json, Class<U> uClazz, Class<T> tClazz){
		try {
			return objectMapper.readValue(json, TypeFactory.collectionType(uClazz,tClazz));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
//    /**
//	 * json 字符串 转换成 List对象
//	 * @param <T>
//	 * @param json
//	 * @param tClazz
//	 * @return
//	 */
//	public static <T> List<T> readListValue(String json, Class<T> tClazz){
//		return readValue(json,tClazz);
//	}
	
	public static <T> Object readValue (String json, TypeReference<T> ref) {
		try {
			return objectMapper.readValue(json, ref);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    /**
     * convert list object to JSON string.
     * @param obj
     * @param field
     * @return JSON String.
     */
    public static String listToJson(Object obj, String field) {
		String str = null;
		try {
			str = objectMapper.writeValueAsString(obj);
			String[] fs = null;
			if (field == null || field.isEmpty()) {
				return str;
			} else {
				fs = field.split(",");
			}
			JSONArray results = new JSONArray();
			JSONArray array = new JSONArray(str);
			for (int i = 0; array != null && i < array.length(); i++) {
				JSONObject jobj = array.getJSONObject(i);
				JSONObject result = new JSONObject();
				for (int j = 0; j < fs.length; j++) {
					if (!jobj.isNull(fs[j])) {
						result.put(fs[j], jobj.getString(fs[j]));
					} else {
						result.put(fs[j], "");
					}
				}
				results.put(result);
			}
			return results.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
    
	public static String getJsonFromObject(Object t) {
		try {
			return objectMapper.writeValueAsString(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}