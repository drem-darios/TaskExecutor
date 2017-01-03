package com.ticketsrus.executor.impl;

import java.util.UUID;

import com.ticketsrus.executor.api.ExecutionStrategy;
import com.ticketsrus.executor.api.Task;

/**
 * This is the base of a basic task which is defined by its execution strategy.
 * 
 * @author drem
 *
 */
public abstract class AbstractTask implements Task {

	private String taskId;
	private ExecutionStrategy executionStrategy;
	private Boolean isSynchronous;
	
	public AbstractTask(ExecutionStrategy strategy, Boolean isSynchronous) {
		this.taskId = UUID.randomUUID().toString(); // Create a unique id for this task
		this.executionStrategy = strategy;
		this.isSynchronous = isSynchronous;
	}
	
	public AbstractTask(ExecutionStrategy strategy) {
		this(strategy, false);
	}
	
	public String getId() {
		return taskId;
	}

	public Boolean isSynchronous() {
		return isSynchronous;
	}
	
	public ExecutionStrategy getExecutionStrategy() {
		return executionStrategy;
	}

}
