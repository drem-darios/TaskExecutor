package com.seatadvisor.executor.engine;

import com.seatadvisor.executor.api.ExecutionStrategy;
import com.seatadvisor.executor.api.Task;
import com.seatadvisor.executor.api.TaskResult;
import com.seatadvisor.executor.exceptions.TaskExecutionException;
import com.seatadvisor.executor.models.BasicTaskResult;

public class TaskExecution {
	private Object result;
	private TaskState state;
	private ExecutionStrategy executionStrategy;

	public TaskExecution(ExecutionStrategy executionStrategy) {
		this.executionStrategy = executionStrategy;
		this.state = TaskState.READY;
	}

	public void executeTask() throws TaskExecutionException {
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
			state = TaskState.ERROR;
			throw e;
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

	public Object getResult() {
		return result;
	}
}
