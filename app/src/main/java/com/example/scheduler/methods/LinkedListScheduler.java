package com.example.scheduler.methods;


public class LinkedListScheduler<E> {
    private long executionTime;
    private LinkedList<Task> taskList;
    public LinkedListScheduler(){
        this.executionTime = 0;
        this.taskList = new LinkedList<>();
    }

    public void addTask(Task task) {
        taskList.addFirst(task);
    }

    public String[][] executeTasks(String[][] tasks) {
        long temp = 0, startTime, endTime;
        int index = 0;
        while (taskList.getSize() != 0) {
            startTime = System.nanoTime();
            Task task = taskList.removeFirst();
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
