package com.seatadvisor.executor.api;

public interface Task {

	public String getId();
	public Boolean isSynchronous();
	public ExecutionStrategy getExecutionStrategy();
	
}
