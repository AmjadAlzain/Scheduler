package com.example.scheduler.methods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QueueSchedulerLL<E> {
    QueueLL<Task> taskQueueLL;
    long executionTime, avgExec;
    public QueueSchedulerLL() {
        this.executionTime = 0;
        this.taskQueueLL = new QueueLL<>();
    }

    public void addTask(Task task){
        taskQueueLL.enqueue(task);
    }


    public long executeTasks(String[][] tasks) {
        long temp = 0, startTime, endTime;
        int index = 0;
        while (!taskQueueLL.isEmpty()) {
            startTime = System.nanoTime();
            Task task = (Task)taskQueueLL.dequeue();
            task.execute();
            tasks[index][0] = task.getName();
            tasks[index][1] = task.getOutput();
            tasks[index][2] = String.valueOf(task.getQueueReponseTime());
            endTime = System.nanoTime();
            temp = (endTime - startTime);
            tasks[index][3] = String.valueOf(temp);
            setExecutionTime(getExecutionTime() + temp);
            index++;
        }
        return getExecutionTime();
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

//    public long parseTasks(InputStream filename) throws IOException {
//        long startTime = System.nanoTime();
//        QueueSchedulerLL<Task> tasks = new QueueSchedulerLL<>();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(filename));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            String[] parts = line.split(" ");
//            tasks.enqueue(new Task(parts[0], parts[1], parts[2]));
//        }
//        long endTime = System.nanoTime();
//        System.out.println("parse time is: "+(endTime-startTime));
//        return endTime-startTime;
//    }

    public double getAvgExec() {
        return (getExecutionTime()/22);
    }
}
