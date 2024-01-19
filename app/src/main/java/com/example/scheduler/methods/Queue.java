package com.example.scheduler.methods;

public class Queue<T> {
    private T[] arr;
    private int maxSize, head, tail;

    public Queue(int maxSize) {
        arr = (T[]) new Object[maxSize];
        this.maxSize = maxSize;
        this.head = 0;
        this.tail = 0;
    }

    public boolean isEmpty() {
        return tail == 0;
    }

    public T peek() {
        if (!isEmpty()) {
            return arr[head];
        } else {
            System.out.println("Queue is empty");
            return null;
        }
    }

    public void enqueue(T element) {
        if (tail < maxSize) {
            arr[tail] = element;
            tail++;
        } else {
            System.out.println("Queue is full");
        }
    }

//    public void dequeue() {
//        if (!isEmpty()) {
//            for (int i = 0; i < tail - 1; i++) {
//                arr[i] = arr[i + 1];
//            }
//            arr[tail - 1] = null;
//            tail--;
//        }
//    }

    public T dequeue() {
        if (!isEmpty()) {
            T dequeuedElement = arr[head];
            for (int i = 0; i < tail - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[tail - 1] = null;
            tail--;

            // Update head if the queue is not empty
            if (!isEmpty()) {
                head++;
            }

            return dequeuedElement;
        } else {
            System.out.println("Queue is empty");
            return null; // or throw an exception, depending on your design choice
        }
    }

    public void display() {
        if (!isEmpty()) {
            for (int i = head; i < tail; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        } else {
            System.out.println("Nothing to display");
        }
    }
}
