package com.ticketsrus.executor.api;

import java.util.Properties;

import com.ticketsrus.executor.exceptions.TaskExecutionException;

/**
 * Strategy pattern used to define how a task should be executed. 
 * @author drem
 *
 */
public interface ExecutionStrategy {
	/**
	 * The type of execution. This can be used in a factory pattern to get the
	 * type of execution without caring of underlying implementation.
	 */
	public String getExecutionType();
	/**
	 * Properties needed to execute this strategy
	 */
	public void setProperties(Properties properties);
	/**
	 * Invokes the execution. Throws a <codE>TaskExecutionException</code> if there was an issue
	 * executing this strategy
	 */
	public TaskResult execute() throws TaskExecutionException;
}
