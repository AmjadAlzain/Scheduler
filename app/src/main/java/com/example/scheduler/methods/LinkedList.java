package com.example.scheduler.methods;

public class LinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
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


    public String toString() {
        StringBuilder str = new StringBuilder("[size=" + this.size + "]");
        Node<E> current = head;

        while (current != null) {
            str.append(" >> ");
            str.append(current.element.toString());
            current = current.next;
        }

        return str.toString();
    }

    public boolean contains(E data) {
        Node<E> current = head;

        while (current != null) {
            if (current.element.equals(data)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public void clear() {
        head = tail = null;
        System.out.println("The list is cleared.");
        size = 0;
    }

    public E getHead() {
        return head.element;
    }

    public Node<E> getTail() {
        return tail;
    }

    public LinkedList<E> combine(LinkedList<E> list1, LinkedList<E> list2) {
        Node<E> currentNode1 = list1.head;
        Node<E> currentNode2 = list2.head;
        LinkedList<E> list3 = new LinkedList<>();

        while (currentNode1 != null && currentNode2 != null) {
            list3.addLast(currentNode1.element);
            currentNode1 = currentNode1.next;

        }

        while (currentNode1 != null) {
            list3.addLast(currentNode1.element);
            currentNode1 = currentNode1.next;
        }

        while (currentNode2 != null) {
            list3.addLast(currentNode2.element);
            currentNode2 = currentNode2.next;
        }

        return list3;
    }

    private static class Node<E> {
        protected E element;
        protected Node<E> next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

}
