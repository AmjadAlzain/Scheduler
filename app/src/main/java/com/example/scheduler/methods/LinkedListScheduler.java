package com.example.scheduler.methods;


public class LinkedListScheduler {
    private long executionTime;
    private LinkedList<Task> taskList;
    public LinkedListScheduler(){
        this.executionTime = 0;
        this.taskList = new LinkedList<>();
    }

    public void addTask(Task task) {
        taskList.addFirst(task);
    }

    public void executeTasks() {
        long temp = 0, startTime, endTime;
        while (taskList.getSize() != 0) {
            startTime = System.nanoTime();
            Task task = taskList.removeFirst();
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
