package com.ticketsrus.executor.strategy;

import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ticketsrus.executor.api.ExecutionStrategy;
import com.ticketsrus.executor.api.Task;
import com.ticketsrus.executor.api.TaskResult;
import com.ticketsrus.executor.models.BasicTask;
import com.ticketsrus.executor.engine.Executor;

public class TimedExecutionStrategyTest {

	private Executor executor;
	private ExecutionStrategy strategy;
	
	@Before
	public void setUp() throws Exception {
		this.executor = new Executor();
		this.strategy = new BasicTimedExecutionStrategy();
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testExecuteTaskWithDefaultTime() {
		Task task = new BasicTask(strategy);
		executor.addTask(task);
		executor.executeTask(task.getId());
		String expected = "Timed Task Executed Successfully!";
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
	public void testExecuteTaskWithFiveSecondTime() {
		Properties props = new Properties();
		props.setProperty("sleep", "5000"); // Have execution wait 5 seconds before starting
		strategy.setProperties(props);
		Task task = new BasicTask(strategy);
		executor.addTask(task);
		executor.executeTask(task.getId());
		String expected = "Timed Task Executed Successfully!";
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // Give three seconds before checking
		TaskResult<String> result = executor.getTaskResult(task.getId()); // Get the execution result
		Assert.assertNull(result); // Result was timed at 5 seconds...should be null since result is not ready
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // Give three seconds before checking
		result = executor.getTaskResult(task.getId()); 
		Assert.assertEquals(expected, result.getResult()); // Check the execution result
	}

}
