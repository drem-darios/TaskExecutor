package com.seatadvisor.executor.api;

public interface TaskResult<T> {

	public T getResult();
	public Boolean isError(); 
}
