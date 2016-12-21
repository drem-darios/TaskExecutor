package com.seatadvisor.executor.api;

/**
 * The result of a task being executed. This is templated to support different types of
 * result objects to be return (i.e. String vs Exception)
 * @author drem
 */
public interface TaskResult<T> {

	/**
	 * The actual result of the task that was executed
	 */
	public T getResult();
	/**
	 * A flag to signify if the task execution resulted in an error
	 */
	public Boolean isError(); 
}
