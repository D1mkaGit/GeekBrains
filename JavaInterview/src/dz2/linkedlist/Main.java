package dz2.linkedlist;

import static dz2.linkedlist.MyLinkedList.*;

public class Main {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        for (int i = 1; i < 5; i++) {
            insertLast(list,i);
        }
        printList(list);

        deleteByKey(list, 1);
        printList(list);

        deleteAtPosition(list, 0);
        printList(list);

        insertFirst(list,10);
        printList(list);

        deleteFirst(list);
        printList(list);

        deleteLast(list);
        printList(list);

        insertAtPosition(list,11,0);
        printList(list);

        MyArrayList arrayList = new MyArrayList();

        for (int i = 0; i < 5; i++) {
            arrayList.add(i);
        }
        printArrayList(arrayList);

        arrayList.remove(0);
        printArrayList(arrayList);

    }

    private static void printArrayList(MyArrayList arrayList) {
        System.out.print("MyArrayList: ");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();
    }
}
