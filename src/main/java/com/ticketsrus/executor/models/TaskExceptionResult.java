package com.ticketsrus.executor.models;

import com.ticketsrus.executor.api.TaskResult;

/**
 * This <class>TaskExceptionResult</class> is used to store Exceptions as a result.
 *
 * @author drem
 *
 */
public class TaskExceptionResult implements TaskResult<Exception> {

	private Exception result;
	private Boolean isError;

	/**
	 * Creates a new TaskExceptionResult and sets isError to true by default
	 */
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
