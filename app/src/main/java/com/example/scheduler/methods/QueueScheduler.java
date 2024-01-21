package com.example.scheduler.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QueueScheduler<E> {
    private E[] queue;
    private int size;
    private int capacity;
    private int headIndex;

    public QueueScheduler() {
        capacity = 5;
        queue = (E[]) new Object[capacity];
        size = 0;
        headIndex = 0;

    }

    @SuppressWarnings("unchecked")
    public QueueScheduler(int capacity) {
        this.capacity = capacity;
        queue = (E[]) new Object[capacity];
        size = 0;
        headIndex = 0;
    }

    public void enqueue(E e) {
        if (size == capacity) {
            increaseCapacity();
        }
        int insertIndex = (headIndex + size) % capacity;
        queue[insertIndex] = e;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        E item = queue[headIndex];
        queue[headIndex] = null;
        headIndex = (headIndex + 1) % capacity;
        size--;
        return item;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[headIndex];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void increaseCapacity() {
        int newCapacity = 2 * capacity;
        @SuppressWarnings("unchecked")
        E[] newQueue = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(headIndex + i) % capacity];
        }
        queue = newQueue;
        headIndex = 0;
        capacity = newCapacity;
    }
    public long executeTasks() {
        long startTime = System.nanoTime();
        while (!(size == 0)) {
            Task task = (Task) dequeue();
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
