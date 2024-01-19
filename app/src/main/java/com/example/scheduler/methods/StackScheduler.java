package com.example.scheduler.methods;

public class StackScheduler {
    private long executionTime;
    private Stack<Task> taskStack;
    public StackScheduler(){
        this.executionTime = 0;
        this.taskStack  = new Stack<>(100);
    }

    public void addTask(Task task) {
        taskStack.push(task);
    }

    public void executeTasks() {
        long temp = 0, startTime, endTime;
        while (!taskStack.isEmpty()) {
            startTime = System.nanoTime();
            Task task = taskStack.pop();
            if (task != null) {
                task.execute();
            }
            endTime = System.nanoTime();
            temp = (endTime - startTime) / 1000000;
            setExecutionTime(getExecutionTime() + temp);
        }
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}
