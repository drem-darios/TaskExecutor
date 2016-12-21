package com.seatadvisor.executor.models;

import com.seatadvisor.executor.api.TaskResult;

/**
 * This <class>BasicTaskResult</class> is used to store String values as a result.
 * 
 * @author drem
 *
 */
public class BasicTaskResult implements TaskResult<String> {

	private String result;
	private Boolean isError;
	
	/**
	 * Creates a BasicTaskResult and sets isError to false by default
	 */
	public BasicTaskResult(String result) {
		this(result, false);
	}
	
	public BasicTaskResult(String result, Boolean isError) {
		this.result = result;
		this.isError = isError;
	}
	
	public String getResult() {
		return result;
	}

	public Boolean isError() {
		return isError;
	}
	
	public void setIsError(Boolean isError) {
		this.isError = isError;
	}
}
