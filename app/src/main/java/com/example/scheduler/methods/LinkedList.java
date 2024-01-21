package com.example.scheduler.methods;

public class LinkedList<E extends Comparable<E>> {

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

    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        size++;
    }

    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

//    public Node<E> removeFirst() {
//        if (head == null) {
//            System.out.println("Error: Head is null");
//            return null;
//        } else {
//            Node<E> currentHead = head;
//            head = head.next;
//            size--;
//            return currentHead;
//        }
//    }

    public E removeFirst() {
        if (head == null) {
            System.out.println("Error: Head is null. List is empty.");
            return null;
        } else {
            E removedElement = head.value;
            head = head.next;
            size--;
            return removedElement;
        }
    }


    public String toString() {
        StringBuilder str = new StringBuilder("[size=" + this.size + "]");
        Node<E> current = head;

        while (current != null) {
            str.append(" >> ");
            str.append(current.value.toString());
            current = current.next;
        }

        return str.toString();
    }

    public boolean contains(E data) {
        Node<E> current = head;

        while (current != null) {
            if (current.value.equals(data)) {
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

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public LinkedList<E> combine(LinkedList<E> list1, LinkedList<E> list2) {
        Node<E> currentNode1 = list1.head;
        Node<E> currentNode2 = list2.head;
        LinkedList<E> list3 = new LinkedList<>();

        while (currentNode1 != null && currentNode2 != null) {
            if (currentNode1.value.compareTo(currentNode2.value) < 0) {
                list3.addLast(currentNode1.value);
                currentNode1 = currentNode1.next;
            } else {
                list3.addLast(currentNode2.value);
                currentNode2 = currentNode2.next;
            }
        }

        while (currentNode1 != null) {
            list3.addLast(currentNode1.value);
            currentNode1 = currentNode1.next;
        }

        while (currentNode2 != null) {
            list3.addLast(currentNode2.value);
            currentNode2 = currentNode2.next;
        }

        return list3;
    }

    private static class Node<E extends Comparable<E>> {
        protected E value;
        protected Node<E> next;

        public Node(E value) {
            this.value = value;
            this.next = null;
        }
    }

}
