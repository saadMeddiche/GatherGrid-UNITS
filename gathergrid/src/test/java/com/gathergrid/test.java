package com.gathergrid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class test {

    public static void main1(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(12);
        numbers.add(8);
        numbers.add(2);
        numbers.add(23);

        // ==================================
        Iterator<Integer> it = numbers.iterator();
        while (it.hasNext()) {
            if (it.next() < 10)
                it.remove();
        }
        // ==================================

        System.out.println(numbers);
    }

    public static void main2(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(12);
        numbers.add(8);
        numbers.add(2);
        numbers.add(23);

        // ==================================
        for (int i = 0; i < numbers.size();) {
            if (numbers.get(i) < 10) {
                numbers.remove(i);
            } else {
                i++;
            }
        }
        // ==================================

        System.out.println(numbers);
    }

}

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void removeLessThan(int threshold) {
        while (head != null && head.data < threshold) {
            head = head.next;
        }

        Node current = head;
        while (current != null && current.next != null) {
            if (current.next.data < threshold) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        // Add some random elements to the linked list
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(20)); // Generates random numbers between 0 and 19
        }

        System.out.println("Original List:");
        list.display();

        // Remove elements less than 10
        list.removeLessThan(10);

        System.out.println("List after removing elements less than 10:");
        list.display();
    }
}
