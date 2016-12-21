package com.seatadvisor.executor.strategy.factory;

import java.util.HashMap;
import java.util.Map;

import com.seatadvisor.executor.api.ExecutionStrategy;

/**
 * Factory pattern that allows callers to register a new <code>ExecutionStrategy</code> and returns an
 * <code>ExecutionStrategy</code> provided the execution type
 * @author drem
 *
 */
public class ExecutionStrategyFactory {

	private static Map<String, ExecutionStrategy> strategies = new HashMap<String, ExecutionStrategy>();
	
//	static {
//		BasicExecutionStrategy basicStrategy = new BasicExecutionStrategy();
//		strategies.put(basicStrategy.getExecutionType(), basicStrategy);
//	}
	
	/**
	 * Adds the execution strategy provided to the factory. If a strategy already exists with the same
	 * execution type, it will be overwritten
	 */
	public static void addExecutionStrategy(ExecutionStrategy strategy) {
		strategies.put(strategy.getExecutionType(), strategy);
	}
	
	/**
	 * Returns the <code>ExecutionStrategy</code> with the provided execution type or null if there does
	 * not exist an execution strategy registered with the given execution type
	 */
	public static ExecutionStrategy getExecutionStrategy(String executionType) {
		return strategies.get(executionType);
	}
}
