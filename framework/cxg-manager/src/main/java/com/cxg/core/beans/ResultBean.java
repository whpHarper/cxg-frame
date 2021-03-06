package com.cxg.core.beans;

/**
 * @ClassName: ResultBean
* @Description: 结果返回
* @author th
* @date 2016年12月31日
 */
public class ResultBean {

	private int code;
	
	private String message;
	
	private Object responseBody;
	
	private boolean result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	public String toString(){
		String str = "ResultBean:";
		str += "code = " + code + ",";
		str += "message = " + message == null ? "null" : message + ",";
		str += "result = " + result;
		return str;
	}
}
