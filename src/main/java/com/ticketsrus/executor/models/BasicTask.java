package com.ticketsrus.executor.models;

import com.ticketsrus.executor.api.ExecutionStrategy;
import com.ticketsrus.executor.impl.AbstractTask;

/**
 * This is a basic task which is defined by its execution strategy.
 * 
 * @author drem
 *
 */
public class BasicTask extends AbstractTask {

	public BasicTask(ExecutionStrategy strategy, Boolean isSynchronous) {
		super(strategy, isSynchronous);
	}
	
	public BasicTask(ExecutionStrategy strategy) {
		super(strategy);
	}

}
