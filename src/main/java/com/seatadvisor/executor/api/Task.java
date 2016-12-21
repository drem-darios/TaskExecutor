package com.seatadvisor.executor.api;

/**
 * 
 * @author drem
 *
 */
public interface Task {
	/**
	 * A unique way to identify this task
	 */
	public String getId();
	/**
	 * Flag to tell signify if this task should block future tasks or run asynchronously
	 */
	public Boolean isSynchronous();
	/**
	 * The strategy pattern used to execute this task
	 */
	public ExecutionStrategy getExecutionStrategy();
	
}
