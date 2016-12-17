package com.seatadvisor.executor.strategy.factory;

import java.util.HashMap;
import java.util.Map;

import com.seatadvisor.executor.api.ExecutionStrategy;

public class ExecutionStrategyFactory {

	private static Map<String, ExecutionStrategy> strategies = new HashMap<String, ExecutionStrategy>();
	
//	static {
//		BasicExecutionStrategy basicStrategy = new BasicExecutionStrategy();
//		strategies.put(basicStrategy.getExecutionType(), basicStrategy);
//	}
	
	public static void addExecutionStrategy(ExecutionStrategy strategy) {
		strategies.put(strategy.getExecutionType(), strategy);
	}
	
	public static ExecutionStrategy getExecutionStrategy(String executionType) {
		return strategies.get(executionType);
	}
}
