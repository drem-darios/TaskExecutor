# TaskExecutor
Simple Executor that takes in Tasks and executes them based on their execution strategy

## Problem Statement
Implement a simple framework for concurrent processing. Your framework should include a class called Executor that enables concurrent execution of implementations of a Task interface. Also implement the Task interface.

Clients of the Executor class should be able to add Tasks to be executed and check if a specific Task has finished executing. A Task is finished if it has completed processing (successful) or has failed due to an error (failed). If a Task is successful, clients should be able to retrieve the return value of the Task, if any. If a Task is failed, clients should be able to retrieve the error that caused the failure.
