package com.seatadvisor.executor.engine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.seatadvisor.executor.api.Task;
import com.seatadvisor.executor.api.TaskResult;
import com.seatadvisor.executor.models.BasicTaskResult;

public class Executor {

	private Map<String, Task> tasks;
	private Map<String, TaskResult> taskResults;
	
	public Executor() {
		this.tasks = new HashMap<String, Task>();
		this.taskResults = new HashMap<String, TaskResult>(); 
	}
	
	public void addTask(Task task) {
		if (StringUtils.isEmpty(task.getId())) {
			System.out.println("Could not add task: Task does not have an id");
			return;
		}
		
		if (tasks.get(task.getId()) != null) {
			System.out.println("Warning: Overwriting task with id " + task.getId());
		}
		tasks.put(task.getId(), task);
	}
	
	public void executeTasks() {
		for (String taskId: tasks.keySet()) {
			executeTask(taskId);
		}
	}
	
	public void executeTask(String taskId) {
		Task task = tasks.get(taskId);
		if (task == null) {
			System.out.println("Could not execute task: Task with id " + taskId + " not found");
			return;
		} 
		
		new TaskExecutionThread(task).start();
		
	}
	
	public TaskResult getTaskResult(String taskId) {
		return taskResults.get(taskId);
	}
	
	public Boolean isComplete(String taskId) {
		// If the task is complete, the task result will not be null
		return taskResults.get(taskId) != null;
	}
	
	public Collection<TaskResult> getTaskResults() {
		return taskResults.values();
	}
	
	public Collection<Task> getTasks() {
		return tasks.values();
	}
	
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
					System.out.println(e.getMessage());
					return;
				}
			}
			Object result = execution.getResult();
			TaskResult<String> taskResult = new BasicTaskResult((String)result, execution.isError());
			taskResults.put(task.getId(), taskResult);
		}
		
	}
}
