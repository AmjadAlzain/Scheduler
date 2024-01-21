package com.example.scheduler.methods;

import android.content.Context;

import java.io.IOException;
import java.nio.file.Path;

public class SchedulerApp {
    QueueScheduler queueScheduler;
    StackScheduler stackScheduler;
    LinkedListScheduler linkedListScheduler;
    Task[] tasks;

    public SchedulerApp(){
        this.stackScheduler = new StackScheduler();
        this.linkedListScheduler = new LinkedListScheduler();
        this.queueScheduler = new QueueScheduler();
        this.tasks = new Task[100];
    }

    public void readInput(Context context) {
        tasks = TaskParser.parseTasks(context, "tasks.txt");
    }

    public void loadSchedulers() {

        for (Task task : tasks) {
            queueScheduler.addTask(task);
            stackScheduler.addTask(task);
            linkedListScheduler.addTask(task);
        }
    }

    public long executeQueue(){
        queueScheduler.executeTasks();
        return queueScheduler.getExecutionTime();
    }

    public long executeLinkedList(){
        linkedListScheduler.executeTasks();
        return linkedListScheduler.getExecutionTime();
    }

    public long executeStack(){
        stackScheduler.executeTasks();
        return stackScheduler.getExecutionTime();
    }
}
