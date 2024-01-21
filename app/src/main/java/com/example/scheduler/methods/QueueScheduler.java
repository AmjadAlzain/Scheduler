package com.example.scheduler.methods;


public class QueueScheduler {

    private long executionTime, avgExec;
    private Queue<Task> queue;
    public QueueScheduler(){
        this.executionTime = 0;
        this.queue = new Queue<>(100);
    }
    public void addTask(Task task) {
        queue.enqueue(task);
    }

    public String[][] executeTasks(String[][] tasks) {
        long  temp = 0, startTime, endTime;
        int index = 0;
        while (!queue.isEmpty()) {
            startTime = System.nanoTime();
            Task task = queue.dequeue();
            if (task != null) {
                task.execute();
                tasks[index][0] = task.getName();
                tasks[index][1] = task.getOutput();
            }
            endTime = System.nanoTime();
            temp = (endTime - startTime);
            tasks[index][2] = String.valueOf(temp);
            setExecutionTime(getExecutionTime() + temp);
            index++;
        }
        return tasks;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public double getAvgExec() {
        return (getExecutionTime()/22);
    }
}
