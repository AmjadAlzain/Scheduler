package com.example.scheduler.methods;

public class QueueLL<E>{
    LinkedList<E> queueLL;
    public QueueLL(){
        super();
        queueLL = new LinkedList<>();
    }
    public void enqueue(E element) {
        queueLL.addLast(element);
    }
    public E dequeue(){
        return queueLL.removeFirst();
    }
    public E peek(){
        return queueLL.getHead();
    }
    public int size(){
        return queueLL.getSize();
    }
    public boolean isEmpty(){
        return queueLL.getSize() == 0;
    }

}
