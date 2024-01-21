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

    public String[][] executeTasks(String[][] tasks) {
        long temp = 0, startTime, endTime;
        int index = 0;
        while (!taskStack.isEmpty()) {
            startTime = System.nanoTime();
            Task task = taskStack.pop();
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
