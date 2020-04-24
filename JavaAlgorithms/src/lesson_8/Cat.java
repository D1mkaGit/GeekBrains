package lesson_8;

import java.util.ArrayList;
import java.util.List;

public class Cat {

    Number test() {
        return 1;
    }

}

class SuperCat extends Cat {

    @Override
    Integer test() {
        return 1;
    }

}

class MainG {
    public static void main(String[] args) {
        List<Cat> cats = new ArrayList<>();
    }
}