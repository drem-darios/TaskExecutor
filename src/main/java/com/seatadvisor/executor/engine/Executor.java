package com.seatadvisor.executor.engine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.seatadvisor.executor.api.Task;
import com.seatadvisor.executor.api.TaskResult;

/**
 * The handler of executing a series of <code>Task</code> objects. This also provides <code>TaskResult</code> objects once a
 * task has completed
 * @author drem
 *
 */
public class Executor {
	private static Logger logger = Logger.getLogger(Executor.class.getName());
	/**
	 * Maps a task id (key) to the task itself (value) This is used for quick lookup of tasks based on id
	 */
	private Map<String, Task> tasks;
	/**
	 * Maps a task id (key) to the result (value) of the execution of that task
	 */
	private Map<String, TaskResult> taskResults;

	/**
	 * Creates a new instance of an <code>Executor</code> with empty tasks and task results
	 */
	public Executor() {
		this.tasks = new HashMap<String, Task>();
		this.taskResults = new HashMap<String, TaskResult>();
	}

	/**
	 * Adds a task to the list of tasks to execute
	 */
	public void addTask(Task task) {
		if (StringUtils.isEmpty(task.getId())) {
			logger.severe("Could not add task: Task does not have an id");
			return;
		}

		if (tasks.get(task.getId()) != null) {
			logger.warning("Warning: Overwriting task with id " + task.getId());
		}
		tasks.put(task.getId(), task);
	}

	/**
	 * Executes all current tasks and stores the task results for retrieval later. This is
	 * synchronized to prevent multiple threads from executing the task list while task execution
	 * is in progress.
	 */
	public synchronized void executeTasks() {
		for (String taskId: tasks.keySet()) {
			executeTask(taskId);
		}
	}

	/**
	 * Executes a single task matching the task id provided. This is synchronized to prevent multiple
	 * threads from executing a task while task execution is in progress.
	 */
	public synchronized void executeTask(String taskId) {
		Task task = tasks.get(taskId);
		if (task == null) {
			logger.warning("Could not execute task: Task with id " + taskId + " not found");
			return;
		}

		new TaskExecutionThread(task).start();
	}

	/**
	 * Returns the <code>TaskResult</code> for the executed task matching the task id provided 
	 * or null if there is no task result for the given task id
	 */
	public TaskResult getTaskResult(String taskId) {
		return taskResults.get(taskId);
	}
	
	/**
	 * Checks if a task has completed executing. If true, a <code>TaskResult</code> object is available
	 */
	public Boolean isComplete(String taskId) {
		// If the task is complete, the task result will not be null
		return taskResults.get(taskId) != null;
	}

	/**
	 * Gets the list of task results available
	 */
	public Collection<TaskResult> getTaskResults() {
		return taskResults.values();
	}

	/**
	 * Gets a list of tasks that were added to the <code>Executor</code>
	 */
	public Collection<Task> getTasks() {
		return tasks.values();
	}

	/**
	 * Executes the <code>Task</code> based on its <code>ExecutionStrategy</code>
	 * Task execution results are stored for retrieval. 
	 */
	private class TaskExecutionThread extends Thread {

		private Task task;
		public TaskExecutionThread(Task task) {
			this.task = task;
		}

		@Override
		public void run() {
			TaskExecution execution = new TaskExecution(task.getExecutionStrategy());
			execution.executeTask();
			while (execution.isRunning()) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					logger.severe(e.getMessage());
					return;
				}
			}
			TaskResult taskResult = execution.getResult();
			taskResults.put(task.getId(), taskResult);
		}

	}
}
