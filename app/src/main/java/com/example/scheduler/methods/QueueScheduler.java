package com.example.scheduler.methods;


public class QueueScheduler {

    private long executionTime;
    private Queue<Task> queue;
    public QueueScheduler(){
        this.executionTime = 0;
        this.queue = new Queue<>(100);
    }
    public void addTask(Task task) {
        queue.enqueue(task);
    }

    public void executeTasks() {
        long  temp = 0, startTime, endTime;
        while (!queue.isEmpty()) {
            startTime = System.nanoTime();
            Task task = queue.dequeue();
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
