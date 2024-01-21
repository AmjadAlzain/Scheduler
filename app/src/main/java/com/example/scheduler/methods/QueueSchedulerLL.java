package com.example.scheduler.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QueueSchedulerLL<E> {
    LinkedListScheduler<E> queue;
    public QueueSchedulerLL() {
        queue = new LinkedListScheduler<>();
    }

    public void enqueue(E element) {
        queue.addLast(element);
    }
    public E dequeue(){
        return queue.removeFirst();
    }
    public E peek(){
        return queue.head.element;
    }
    public int size(){
        return queue.size;
    }




    public long executeTasks() {
        long startTime = System.nanoTime();
        while (!queue.isEmpty()) {
            Task task = (Task)queue.removeFirst();
            task.execute();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public long parseTasks(InputStream filename) throws IOException {
        long startTime = System.nanoTime();
        QueueSchedulerLL<Task> tasks = new QueueSchedulerLL<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            tasks.enqueue(new Task(parts[0], parts[1], parts[2]));
        }
        long endTime = System.nanoTime();
        System.out.println("parse time is: "+(endTime-startTime));
        return endTime-startTime;
    }
}
