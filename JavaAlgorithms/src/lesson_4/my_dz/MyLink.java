package lesson_4.my_dz;

public class MyLink<Item> {
    private final Item item;
    private MyLink<Item> next;

    public MyLink(Item item) {
        this.item = item;
    }

    public void display(){
        System.out.println(item);
    }

    public Item getItem() {
        return item;
    }

    public MyLink<Item> getNext() {
        return next;
    }

    public void setNext(MyLink<Item> next) {
        this.next = next;
    }
}
