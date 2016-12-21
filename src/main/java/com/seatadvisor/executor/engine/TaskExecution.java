package com.seatadvisor.executor.engine;

import com.seatadvisor.executor.api.ExecutionStrategy;
import com.seatadvisor.executor.api.TaskResult;
import com.seatadvisor.executor.exceptions.TaskExecutionException;
import com.seatadvisor.executor.models.TaskExceptionResult;

/**
 * A unit of execution of a <code>Task</code>. A task is executed based on its
 * <code>ExecutionStrategy</code>. The <code>TaskState</code> is used to maintain the state
 * of the task execution. A task starts at the READY state. While execution is in progress the execution
 * state will be RUNNING. Once completed, the execution state will either be in the COMPLETE state or the ERROR state.  
 *
 */
public class TaskExecution {
	private TaskResult result;
	private TaskState state;
	private ExecutionStrategy executionStrategy;

	public TaskExecution(ExecutionStrategy executionStrategy) {
		this.executionStrategy = executionStrategy;
		this.state = TaskState.READY;
	}

	public void executeTask() {
		if (state == TaskState.READY) {
			state = TaskState.RUNNING;
		} else {
			System.out.println("Could not execute task: Task is not ready");
			return;
		}

		try {
			result = executionStrategy.execute();
			state = TaskState.COMPLETE;
		} catch(TaskExecutionException e) {
			result = new TaskExceptionResult(e); // if there was an error, set the result to be an exception result
			state = TaskState.ERROR;
		}
	}

	public Boolean isRunning() {
		return state == TaskState.RUNNING;
	}

	public Boolean isComplete() {
		return state == TaskState.COMPLETE;
	}

	public Boolean isError() {
		return state == TaskState.ERROR;
	}

	public TaskResult getResult() {
		return result;
	}
}
