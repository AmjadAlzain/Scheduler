package com.example.scheduler.methods;

public class Stack<T> {

    private int max;
    private int top;
    private T arr[];

    public Stack(int n) {
        arr = (T[]) new Object[n];
        this.max = n;
        top = 0;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public T peek() {
        if (!isEmpty()) {
            return arr[top - 1];
        } else {
            return null;
        }
    }

    public void push(T element) {
        if (top < max) {
            arr[top] = element;
            top++;
        } else {
            System.out.println("Stack is full!");
        }
    }

    public void pushMany(String elements) {
        String[] tokens = elements.split(",");

        for (int i = 0; i < tokens.length && top < max; i++) {
            T temp = (T) tokens[i];
            arr[top] = temp;
            top++;
        }

        if (top >= max) {
            System.out.println("Stack is full!");
        }
    }

    public T pop() {
        if (!isEmpty()) {
            T temp = peek();
            arr[top - 1] = null;
            top--;
            return temp;
        } else {
            return null;
        }
    }

    public void popAll() {
        while (!isEmpty()) {
            T temp = pop();
            System.out.println("Removing " + temp + " ..");
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        for (int i = 0; i < top; i++) {
            str.append(arr[i]);

            if (i < top - 1) {
                str.append(", ");
            }
        }
        str.append("] ");
        return str.toString();
    }
}
