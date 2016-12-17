package com.seatadvisor.executor.models;

import com.seatadvisor.executor.api.ExecutionStrategy;
import com.seatadvisor.executor.impl.AbstractTask;

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
