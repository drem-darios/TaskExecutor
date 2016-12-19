package com.seatadvisor.executor.models;

import com.seatadvisor.executor.api.TaskResult;

/**
 * This <class>TaskExceptionResult</class> is used to store Exceptions as a result.
 *
 * @author drem
 *
 */
public class TaskExceptionResult implements TaskResult<Exception> {

	private Exception result;
	private Boolean isError;

	public TaskExceptionResult(Exception exception) {
		this.result = exception;
		this.isError = true;
	}

	public Exception getResult() {
		return result;
	}

	public Boolean isError() {
		return isError;
	}
}
