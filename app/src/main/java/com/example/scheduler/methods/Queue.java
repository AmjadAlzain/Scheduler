package com.example.scheduler.methods;

public class Queue<E> {
    private E[] queue;
    private int capacity, headIndex, tail, size;


    public Queue() {
        capacity = 5;
        queue = (E[]) new Object[capacity];
        size = 0;
        headIndex = 0;

    }

    @SuppressWarnings("unchecked")
    public Queue(int capacity) {
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

//    public void display() {
//        if (!isEmpty()) {
//            for (int i = head; i < tail; i++) {
//                System.out.print(arr[i] + " ");
//            }
//            System.out.println();
//        } else {
//            System.out.println("Nothing to display");
//        }
//    }
}
