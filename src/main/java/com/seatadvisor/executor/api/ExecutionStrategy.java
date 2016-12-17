package com.seatadvisor.executor.api;

import java.util.Properties;

import com.seatadvisor.executor.exceptions.TaskExecutionException;

public interface ExecutionStrategy {
	public String getExecutionType();
	public void setProperties(Properties properties);
	public Object execute() throws TaskExecutionException;
}
