package com.hanson.util;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanson.util.Constant;
import com.hanson.util.JsonUtil;

public class Util {

	
	public static String rcode(int length) {
		String[] number = new String[] { "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
				"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4",
				"5", "6", "7", "8", "9" };

		Random rnd = new Random();
		int p = number.length - 1;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int j = rnd.nextInt(p) + 1;
			sb.append(number[j]);
		}

		return sb.toString();
	}

	/**
	 * ���������
	 * 
	 * @param length
	 *            ����
	 * @return ��ɵ������
	 */
	public static String vcode(int length) {
		String[] number = new String[] { "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9" };

		Random rnd = new Random();
		int p = number.length - 1;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int j = rnd.nextInt(p) + 1;
			sb.append(number[j]);
		}

		return sb.toString();
	}

	/**
	 * �Ƿ�Html4��View
	 * 
	 * @param request
	 * @return boolean
	 */
	public static boolean isHtml(HttpServletRequest request) {
		String host = request.getServerName();
		if (host.matches("^www\\.[a-z|\\.|\\-|1-9]+")) {
			return true;
		}

		return false;
	}

	/**
	 * �����������ȡIP��ַ��ͨ��������ͷ����X-Real-IP֧�ִ��?�������������
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getRemoteAddr();

		String result = request.getHeader("X-Real-IP");
		if (result != null && ip.startsWith("192.168")) {
			ip = result;
		}
		return ip;
	}

	/**
	 * ����ʽΪ8000�ļ۸� ת��Ϊ8.00
	 * 
	 * @param price
	 * @return
	 */
	public static String formatPrice(String price) {
		if (price == null || !price.matches(Constant.ID_FORMAT)) {
			return "0.00";
		}
		float fee = Float.valueOf(price);
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
		return df.format(fee / 1000);
	}

	/**
	 * �滻xml���ܽ����������ַ�
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static String xmlReplace(String xmlStr) {
		if (xmlStr == null || xmlStr.trim().isEmpty()) {
			return null;
		}

		if (xmlStr.indexOf("&nbsp;") > 0) {
			xmlStr = xmlStr.replaceAll("&nbsp;", " ");
		}

		if (xmlStr.indexOf("&amp;") > 0) {
			xmlStr = xmlStr.replaceAll("&amp;", " ");
		}

		if (xmlStr.indexOf("&") > 0) {
			xmlStr = xmlStr.replaceAll("&", " ");
		}

		return xmlStr;
	}

	/**
	 * �ж��Ƿ�Ϊ�գ���֧��List �� String��
	 * 
	 * @param object
	 * @return
	 */
	public static boolean notNull(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof List) {
			List list = (List) object;
			return list != null && !(list.isEmpty());
		}
		if (object instanceof String) {
			String str = (String) object;
			return str != null && !(str.trim().isEmpty())
					&& !(str.equalsIgnoreCase("null"));
		}
		return true;
	}

	/**
	 * �ж��Ƿ�Ϊ�գ���֧��List �� String��
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isNull(Object object) {
		return !notNull(object);
	}
	/**
	 * ��Է�ҳ��ѯ��list��ݽ��ƴ�ӳ�json�����ǰ��
	 * @param response
	 * @param totalRows
	 * @param list
	 */
	public static void responseToJson(HttpServletResponse response, int totalRows, List list ){
		Map map = new HashMap();
		map.put("total", totalRows);
		map.put("rows", list);
		String listJson = JsonUtil.getJsonFromObject(map);
		responseToJson(response, listJson);
	}
	/**
	 * ���result���ǰ��
	 * @param response
	 * @param result
	 */
	public static void responseToJson(HttpServletResponse response, String result){
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
