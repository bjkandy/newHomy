package common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;




@ControllerAdvice
public class BaseExceptionHandlerAdvice {

	final Logger logger = LoggerFactory.getLogger(BaseExceptionHandlerAdvice.class);
	@ExceptionHandler
	@ResponseBody
	public Result<?> exceptionHandler(Exception ex) {

		Result<?> result;
		if (ex instanceof ServiceCommonException) {
			ServiceCommonException e = (ServiceCommonException) ex;
			logger.error("4001:"+e.msg);
			e.printStackTrace();
			result = Result.newErrorResult(e.code, e.msg);
		} else {
			logger.error("4001:"+ex.getMessage());
			ex.printStackTrace();
			result = Result.newErrorResult("4001", "服务器异常.....");
			
		}

		return result;
	}
}

