package com.seatadvisor.executor.strategy;

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
import com.seatadvisor.executor.engine.Executor;
import com.seatadvisor.executor.exceptions.TaskExecutionException;
import com.seatadvisor.executor.models.BasicTask;

public class TimedExecutionStrategyTest {

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

	@Test
	public void testAddTask() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTask() {
		fail("Not yet implemented");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testExecuteTask() {
		Task task = new BasicTask(mockStrategy); // Change this to be an mock object
		try {
			when(mockStrategy.execute()).thenReturn("success");
		} catch (TaskExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.addTask(task);
		executor.executeTask(task.getId());
		String expected = "success";
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // Give two seconds for execution to complete
		TaskResult<String> result = executor.getTaskResult(task.getId()); // Check the execution result
		Assert.assertEquals(expected, result.getResult());
	}
	
	@Test
	public void testExecuteTasks() {
		fail("Not yet implemented");
	}

}
