package com.seatadvisor.executor.engine;

/**
 * The various states a <code>TaskExecution</code> can be in
 */
public enum TaskState {

	ERROR,
	WAITING,
	READY,
	RUNNING,
	PAUSED,
	COMPLETE
}
