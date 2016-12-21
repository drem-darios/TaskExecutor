package com.seatadvisor.executor.strategy;

import java.util.Properties;

import com.seatadvisor.executor.api.ExecutionStrategy;
import com.seatadvisor.executor.api.TaskResult;
import com.seatadvisor.executor.exceptions.TaskExecutionException;
import com.seatadvisor.executor.models.BasicTaskResult;

/**
 * A basic timed execution which sleeps for a configurable amount of time before returning a <code>TaskResult</code>
 * Defaults to 1000 milliseconds if the "sleep" property is not provided 
 * @author drem
 *
 */
public class BasicTimedExecutionStrategy implements ExecutionStrategy {

	private static final String EXECUTION_TYPE = "BasicTimedExecution";
	private Properties properties = new Properties();
	public String getExecutionType() {
		return EXECUTION_TYPE;
	}
	
	/**
	 * Expects a "sleep" property to configure how long before the result is set (in milliseconds)
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public TaskResult<String> execute() throws TaskExecutionException {
		Integer sleepTime = Integer.valueOf(properties.getProperty("sleep", "1000"));
		try {
			Thread.sleep(sleepTime);
			return new BasicTaskResult("Timed Task Executed Successfully!");
		} catch (InterruptedException e) {
			throw new TaskExecutionException(e.getMessage());
		}
	}

}
