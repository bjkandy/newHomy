package common;


public class Result<T> {

    //"结果编码"
	private String code;
    //"结果描述"
	private String msg;
    //	"返回结果"
	private T data;

	public Result() {
	}

	@SuppressWarnings("unchecked")
	public static Result<?> getSuccess(Object data) {
		@SuppressWarnings("rawtypes")
		Result result = new Result();
		result.setCode("0000");
		result.setMsg("success");
		result.setData(data);
		return result;
	}

	public Result(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	@SuppressWarnings("rawtypes")
	public static Result<?> newSuccessResult() {
		return new Result("0000", "Success");
	}

	@SuppressWarnings("rawtypes")
	public static Result<?> newErrorResult(String errcode, String errmsg) {
		return new Result(errcode, errmsg);
	}


	public Boolean isSuccess() {
		return code.equals(ResultConstants.SUCCESS_CODE);
	}

	// public Map<String, Object> withMapData() {
	// Map<String, Object> map = new HashMap<String, Object>();
	// this.data = map;
	// return map;
	// }

	@SuppressWarnings("rawtypes")
	public Result withData(T obj) {
		this.data = obj;
		return this;
	}



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
