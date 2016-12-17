package com.seatadvisor.executor.engine;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.seatadvisor.executor.api.ExecutionStrategy;
import com.seatadvisor.executor.api.Task;
import com.seatadvisor.executor.api.TaskResult;
import com.seatadvisor.executor.exceptions.TaskExecutionException;
import com.seatadvisor.executor.models.BasicTask;

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
		Task task = new BasicTask(mockStrategy); // Change this to be an mock object
		String expected = "success";
		try {
			when(mockStrategy.execute()).thenReturn(expected);
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
		TaskResult<String> result = executor.getTaskResult(task.getId()); // Check the execution result
		Assert.assertEquals(expected, result.getResult());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testExecuteTaskFailure() {
		Task task = new BasicTask(mockStrategy); // Change this to be an mock object
		String expected = "failure";
		
		try {
			when(mockStrategy.execute()).thenThrow(new TaskExecutionException(expected));
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
		
		TaskResult<String> result = executor.getTaskResult(task.getId()); // Check the execution result
		Assert.assertEquals(expected, result.getResult());
		Assert.assertTrue(result.isError());
	}
	
	@Test
	public void testExecuteTaskIsComplete() {
		Task task = new BasicTask(mockStrategy); // Change this to be an mock object
		try {
			when(mockStrategy.execute()).thenReturn("success");
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