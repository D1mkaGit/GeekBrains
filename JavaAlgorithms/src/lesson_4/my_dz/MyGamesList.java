package lesson_4.my_dz;

public class MyGamesList {
    public static void main(String[] args) {
        MyTwoSideLinkList<String> list = new MyTwoSideLinkList<>();
        list.insertFirst("WOW");
        list.insertFirst("Linage2");
        list.insertFirst("Starcraft");
        list.insertLast("Final Fantasy");

        System.out.println("Выводим предзаполенный список:");
        list.display();
        System.out.println("Удаление элементов списка");

        System.out.println("Удаляем первый элемент "+list.deleteFirst().getItem());
        System.out.println("Удаляем элементс именем: "+list.delete("Linage2").getItem());
        System.out.println("Удаляем последний элемент "+list.deleteLast().getItem());
        System.out.println("Выводим оставшиеся элементы списка:");
        list.display();
    }
}
