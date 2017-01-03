package com.ticketsrus.executor.engine;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ticketsrus.executor.api.ExecutionStrategy;
import com.ticketsrus.executor.api.Task;
import com.ticketsrus.executor.api.TaskResult;
import com.ticketsrus.executor.exceptions.TaskExecutionException;
import com.ticketsrus.executor.models.BasicTask;
import com.ticketsrus.executor.models.BasicTaskResult;

public class ExecutorTest {

	private Executor executor;
	private ExecutionStrategy mockStrategy;
	
	@Before
	public void setUp() throws Exception {
		this.executor = new Executor();
		mockStrategy = Mockito.mock(ExecutionStrategy.class);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testExecuteTaskSuccess() {
		Task task = new BasicTask(mockStrategy); // Use mock object for strategy
		TaskResult<String> expected = new BasicTaskResult("success");
		try {
			when(mockStrategy.execute()).thenReturn(expected); // Set expected on mock object
		} catch (TaskExecutionException e) {
			fail(e.getMessage());
		}
		executor.addTask(task);
		executor.executeTask(task.getId()); // Execute the task
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // Wait two seconds for execution thread to come back with a result
		TaskResult<String> result = executor.getTaskResult(task.getId()); 
		Assert.assertEquals(expected, result); // Check the execution result
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testExecuteTaskFailure() {
		Task task = new BasicTask(mockStrategy); // Change this to be an mock object
		TaskExecutionException expected = new TaskExecutionException("failure");
		
		try {
			when(mockStrategy.execute()).thenThrow(expected);
		} catch (TaskExecutionException e) {
			fail(e.getMessage());
		}
		
		executor.addTask(task);
		executor.executeTask(task.getId());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // Give two seconds for execution to complete
		
		TaskResult<Exception> result = executor.getTaskResult(task.getId()); // Check the execution result
		Assert.assertEquals(expected, result.getResult());
		Assert.assertTrue(result.isError());
	}
	
	@Test
	public void testExecuteTaskIsComplete() {
		Task task = new BasicTask(mockStrategy); // Change this to be an mock object
		try {
			when(mockStrategy.execute()).thenReturn(new BasicTaskResult("success"));
		} catch (TaskExecutionException e) {
			fail(e.getMessage());
		}
		executor.addTask(task);
		Assert.assertFalse(executor.isComplete(task.getId()));
		executor.executeTask(task.getId());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // Give two seconds for execution to complete
		Assert.assertTrue(executor.isComplete(task.getId()));
	}

}
