package com.example.scheduler.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StackScheduler<E> {

    //TODO: implement the array class without using the built-in ArrayList class
    Task stack[];
    int size;
    int capacity;
    public StackScheduler() {
        this.size = 0;
        this.capacity = 5;
        this.stack = new Task[capacity];

    }
    public void push(E e){
        if(size == capacity){
            Task[] temp = new Task[capacity*2];
            for(int i = 0; i < size; i++){
                temp[i] = stack[i];
            }
            stack = temp;
            capacity = capacity*2;
        }
        stack[size] = (Task) e;
        size++;

    }
    public E pop(){
        if(isEmpty()){
            return null;
        }
        Task temp = stack[size-1];
        stack[size-1] = null;
        size--;
        return (E)temp;
    }
    public E peek(){
        if(size == 0){
            return null;
        }
        return (E)stack[size-1];
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }



    public long executeTasks() {
        long startTime = System.nanoTime();
        while (!(size == 0)) {
            Task task = (Task) pop();
            task.execute();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public long parseTasks(InputStream filename) throws IOException {
        long startTime = System.nanoTime();
        StackScheduler<Task>  tasks = new StackScheduler<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            tasks.push(new Task(parts[0], parts[1], parts[2]));
        }
        long endTime = System.nanoTime();
        System.out.println("parse time is: "+(endTime-startTime));
        return endTime-startTime;
    }

}