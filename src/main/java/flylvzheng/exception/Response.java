/**
 * 
 */
package flylvzheng.exception;


import java.io.Serializable;

/**
 * 响应参数实体
 * 
 */
public class Response<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4250719891313555820L;
	/**
	 * 返回结果集
	 */
	private T result;
	/**
	 * 返回消息
	 */
	private String msg;
	/**
	 * 响应码
	 */
	private Integer code;

	/**
	 *  "success": false, "failed": true
	 *  下面两个构造方法
	 * @return
	 */
	public boolean isSuccess() {
		return Code.SUCCESSED == this.code;
	}
	public boolean isFailed() {
		return !this.isSuccess();
	}
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public T result() {
		return result;
	}

}
