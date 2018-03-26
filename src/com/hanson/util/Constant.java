package com.hanson.util;

public interface Constant {
	
//	public static final String COOKIE_USER_ADMIN = "admin_ticket";
	

	/**
	 * �ֻ��ƥ��
	 */
	static final String MOBILE_FORMAT = "\\d{11}";

	/**
	 * ����ƥ��
	 */
	static final String SUMBER_FORMAT = "^[0-9]*$";

	/**
	 * IDƥ��
	 */
	static final String ID_FORMAT = "\\d+";

	/**
	 * Emailƥ��
	 */
	static final String EMAIL_FORMAT = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	/**
	 * IPƥ��
	 */
	static final String IP_FORMAT = "((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))";

	/**
	 * ͬ��
	 */
	String JSON = "jsonStr";

	public static final String ERROR_CODE = "errorCode";

	/**
	 * ���ڱ�ʶ��ǰ�����Ƿ��ǽӿڵ��ã���������������������á�
	 */
	public static final String IS_API_REQUEST = "is_api_calling_request";
	/**
	 * ��
	 */
	int SC_OK = 0;

	public static final String X_UP_CALL1ING_LINE_ID = "X-up-calling-line-id";

	/**
	 * ������Cookies�еİ汾��Ϣ�����û��ֹ�ѡ��ʱ���
	 */
	String COOKIE_TPL = "MM_VERSION";
	/**
	 * ������Cookies�еĻ�����Ϣ(ID)�����û��ֹ�ѡ��ʱ���
	 */
	String COOKIE_DEVICE = "MM_DEVICE";

	/**
	 * Cookies�е�JSESSIONID
	 */
	String COOKIE_JSESSIONID = "MMSSESSIONID";
	/**
	 * �û���ʶ�������ֵ��ȡUserInfo����
	 */
	String COOKIE_USER = "wap_user";
	/**
	 * �����õ���֤��ʶ
	 */
	String COOKIE_DOWNLOAD_COOKIES = "dCookie";
	/**
	 * ��Ҫ���ص�·��
	 */
	String BACKURL = "backUrl";
	/**
	 * �û��Ƿ��ȡ�˵�¼ʱ�Ķ�����֤�룬��ֵΪ�ֻ�� ��ʾ�û����յ��˶�����֤��
	 */
	String COOKIE_SEND_SMS_CODE_USER = "sms_code_user";
	/**
	 * ִ�е�action���
	 */
	String OP_RESULT = "op_result";
	/**
	 * ִ�е�action���ɹ�״ֵ̬
	 */
	 static final Integer OP_RESULT_SUCCESS = 0;
	 /**
	 * ִ�е�action���ʧ��״ֵ̬
	 */
	 static final Integer OP_RESULT_FAILER = 1;
	 
	 /**
	  * 权限类型
	  */
	 static final String RESOURCE_TYPE = "RESOURCETYPE";
	 
	 /**
	  * 汇美企业id
	  */
	 static final Long HUIMEI_ID = 103L;
}
