package com.example.scheduler.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class LinkedListScheduler<E> {
    // actula implementation of linked list class
    Node<E> head;
    Node<E> tail;
    int size;

    public LinkedListScheduler() {
        head = null;
        tail = null;
        size = 0;
    }
    public void addFirst(E element){
        Node<E> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        size++;
        if(tail == null){
            tail = head;
        }
    }
    public void addLast(E element){
        Node<E> newNode = new Node<>(element);
        if(tail == null){
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }
    public void add(int index, E element){
        if(index == 0){
            addFirst(element);
        }
        else if(index >= size){
            addLast(element);
        }
        else{
            Node<E> current = head;
            for(int i = 1; i < index; i++){
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(element);
            (current.next).next = temp;
            size++;
        }
    }
    public E removeFirst(){
        if(size == 0){
            return null;
        }
        else{
            Node<E> temp = head;
            head = head.next;
            size--;
            if(head == null){
                tail = null;
            }
            return temp.element;
        }
    }
    public E removeLast(){
        if(size == 0){
            return null;
        }
        else if(size == 1){
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        }
        else{
            Node<E> current = head;
            for(int i = 0; i < size - 2; i++){
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }
    public E remove(int index){
        if(index < 0 || index >= size){
            return null;
        }
        else if(index == 0){
            return removeFirst();
        }
        else if(index == size - 1){
            return removeLast();
        }
        else{
            Node<E> previous = head;
            for(int i = 1; i < index; i++){
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public long executeTasks() {
        long startTime = System.nanoTime();
        while (!isEmpty()) {
            Task task = (Task)removeLast();
            task.execute();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public long parseTasks(InputStream filename) throws IOException {
        long startTime = System.nanoTime();
        LinkedListScheduler<Task>  tasks = new LinkedListScheduler<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            tasks.addLast(new Task(parts[0], parts[1], parts[2]));
        }
        long endTime = System.nanoTime();
        System.out.println("parse time is: "+(endTime-startTime));
        return endTime-startTime;

    }
}
class Node<E>{
    E element;
    Node<E> next;

    public Node(E element) {
        this.element = element;
    }
}