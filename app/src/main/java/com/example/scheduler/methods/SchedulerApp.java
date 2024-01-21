package com.example.scheduler.methods;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SchedulerApp {
    QueueScheduler queueScheduler;
    StackScheduler stackScheduler;
    LinkedListScheduler linkedListScheduler;
    QueueSchedulerLL queueLLScheduler;
    long queueResTime, stackResTime, queueLLResTime, linkedListResTime;
    Task[] tasks;
    String[][] stackTasks, queueTasks, linkedListTasks, queueLLTasks;

    public SchedulerApp(){
        this.stackScheduler = new StackScheduler();
        this.linkedListScheduler = new LinkedListScheduler();
        this.queueScheduler = new QueueScheduler();
        this.queueLLScheduler = new QueueSchedulerLL<>();
        this.queueResTime = 0;
        this.stackResTime = 0;
        this.linkedListResTime = 0;
        this.queueLLResTime = 0;
        this.tasks = new Task[100];
        stackTasks = new String[22][6];
        queueTasks = new String[22][6];
        linkedListTasks = new String[22][6];
        queueLLTasks = new String[22][6];
    }

    public void readInput(Context context) {
        tasks = parseTasks(context, "tasks.txt");
    }

    public void loadSchedulers() {
        long queueStart, queueEnd, stackStart, stackEnd;
        long linkedListStart, linkedListEnd, queueLLStart, queueLLEnd;
        int index = 0;
        for (Task task : tasks) {
            queueStart = System.nanoTime();
            queueScheduler.addTask(task);
            queueEnd = System.nanoTime();
            stackStart = System.nanoTime();
            stackScheduler.addTask(task);
            stackEnd = System.nanoTime();
            linkedListStart = System.nanoTime();
            linkedListScheduler.addTask(task);
            linkedListEnd = System.nanoTime();
            queueLLStart = System.nanoTime();
            queueLLScheduler.addTask(task);
            queueLLEnd = System.nanoTime();
            setStackResTime(stackResTime + (stackEnd - stackStart));
            task.setStackExecTime(stackEnd - stackStart);
            setLinkedListResTime(linkedListResTime + (linkedListEnd - linkedListStart));
            task.setLinkedListReponseTime(linkedListEnd - linkedListStart);
            setQueueResTime(queueResTime + (queueEnd - queueStart));
            task.setQueueReponseTime(queueEnd - queueStart);
            setQueueLLResTime(queueLLResTime + (queueLLEnd - queueLLStart));
            task.setQueueLLReponseTime(queueLLEnd - queueLLStart);
        }
    }

    public long executeQueue(){
        queueScheduler.executeTasks(queueTasks);
        return queueScheduler.getExecutionTime();
    }

    public long executeLinkedList(){
        linkedListScheduler.executeTasks(linkedListTasks);
        return linkedListScheduler.getExecutionTime();
    }

    public long executeStack(){
        stackScheduler.executeTasks(stackTasks);
        return stackScheduler.getExecutionTime();
    }

    public long executeQueueLL(){
        queueLLScheduler.executeTasks(queueLLTasks);
        return queueLLScheduler.getExecutionTime();
    }

    public Task[] parseTasks(Context context, String filename) {
        Task[] tasks = new Task[22];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                tasks[index] = new Task(parts[0], parts[1], parts[2]);
                index++;
            }
            return tasks;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public long getQueueResTime() {
        return queueResTime;
    }

    public void setQueueResTime(long queueResTime) {
        this.queueResTime = queueResTime;
    }

    public long getStackResTime() {
        return stackResTime;
    }

    public void setStackResTime(long stackResTime) {
        this.stackResTime = stackResTime;
    }

    public long getQueueLLResTime() {
        return queueLLResTime;
    }

    public void setQueueLLResTime(long queueLLResTime) {
        this.queueLLResTime = queueLLResTime;
    }

    public long getLinkedListResTime() {
        return linkedListResTime;
    }

    public void setLinkedListResTime(long linkedListResTime) {
        this.linkedListResTime = linkedListResTime;
    }
}
