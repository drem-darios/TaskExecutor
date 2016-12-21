package com.seatadvisor.executor.strategy;

import java.util.Properties;

import com.seatadvisor.executor.api.ExecutionStrategy;
import com.seatadvisor.executor.api.TaskResult;
import com.seatadvisor.executor.exceptions.TaskExecutionException;
import com.seatadvisor.executor.models.BasicTaskResult;

/**
 * A very basic <code>ExecutionStrategy</code> that just returns a <code>TaskResult</code> to let the caller
 * know the execution was successful. 
 * @author drem
 */
public class BasicExecutionStrategy implements ExecutionStrategy {

	private static final String EXECUTION_TYPE = "BasicTaskExecution";
	
	public void setProperties(Properties properties) {
	}

	public TaskResult<String> execute() throws TaskExecutionException {
		return new BasicTaskResult("Task Executed Successfully!");
	}

	public String getExecutionType() {
		return EXECUTION_TYPE;
	}
}
