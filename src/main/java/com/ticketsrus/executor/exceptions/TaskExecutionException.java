package com.ticketsrus.executor.exceptions;

/**
 * This exception is used to signify something went wrong while <code>TaskExecution</code> was in progress
 * @author drem
 */
public class TaskExecutionException extends Exception {
	private static final long serialVersionUID = 3417881362154205357L;
	
	public TaskExecutionException(String message) {
		super(message);
	}
}
