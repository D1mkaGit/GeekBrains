package dz2.linkedlist;


public class MyLinkedList<T> {
    Node head;

    public static MyLinkedList insertLast(MyLinkedList list, Object data) {
        Node new_node = new Node(data);
        if (list.head == null) {
            list.head = new_node;
        } else {
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node;
        }
        return list;
    }

    public static MyLinkedList insertFirst(MyLinkedList list, int data) {
        Node new_node = new Node(data);
        if (list.head != null) {
            new_node.next = list.head;
        }
        list.head = new_node;
        return list;
    }

    public static MyLinkedList insertAtPosition(MyLinkedList list, int data, int index) {
        Node currNode = list.head, prev = null;
        Node new_node = new Node(data);
        if (list.head == null) {
            list.head = new_node;
        } else {
            int counter = 0;
            while (currNode != null) {
                if (counter == index) {
                    new_node.next = currNode;
                    if (prev == null) list.head = new_node;
                    else prev.next = new_node;
                    System.out.println("Item " + data + " was inserted into " + index + " position");
                    break;
                } else {
                    prev = currNode;
                    currNode = currNode.next;
                    counter++;
                }
            }
            if (currNode == null) {
                System.out.println(index + " position element not found");
            }
        }
        return list;
    }

    public static void printList(MyLinkedList list) {
        Node currNode = list.head;
        System.out.print("MyLinkedList: ");
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    public static MyLinkedList deleteFirst(MyLinkedList list) {
        if (list.head == null) {
            System.out.println("List is empty");
            return list;
        } else {
            list.head = list.head.next;
            System.out.println("First item from the list was deleted");
        }
        return list;
    }

    public static MyLinkedList deleteLast(MyLinkedList list) {
        if (list.head == null) {
            System.out.println("List is empty");
            return list;
        } else {
            Node prev = list.head;
            Node last = list.head;
            while (last.next != null) {
                prev = last;
                last = last.next;
            }
            prev.next = null;
            System.out.println("Last item from the list was deleted");
        }
        return list;
    }

    public static MyLinkedList deleteByKey(MyLinkedList list, Object key) {
        Node currNode = list.head;
        Node prev;
        if (currNode != null && currNode.data.equals(key)) {
            list.head = currNode.next;
            System.out.println(key + " found and deleted");
            return list;
        }
        while (currNode != null) {
            prev = currNode;
            currNode = currNode.next;
            if (currNode.data == key) {
                prev.next = currNode.next;
                System.out.println(key + " found and deleted");
                return list;
            }
            if (currNode.next == null)
                System.out.println(key + " not found");
        }
        return list;
    }

    public static MyLinkedList deleteAtPosition(MyLinkedList list, int index) {
        Node currNode = list.head, prev = null;
        if (index == 0 && currNode != null) {
            list.head = currNode.next;
            System.out.println(index + " position element deleted");
            return list;
        }
        int counter = 0;
        while (currNode != null) {
            if (counter == index) {
                prev.next = currNode.next;
                System.out.println(index + " position element deleted");
                break;
            } else {
                prev = currNode;
                currNode = currNode.next;
                counter++;
            }
        }
        if (currNode == null) {
            System.out.println(index + " position element not found");
        }
        return list;
    }
}

class Node<T> {
    final T data;
    Node next;

    public Node(T data) {
        this.data = data;
        next = null;
    }
}
