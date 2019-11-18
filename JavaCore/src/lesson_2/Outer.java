package lesson_2;

public class Outer {
    private int outerVar;

    void outerTest() {
        //System.out.println(innerVar);
        System.out.println(outerVar);
    }

    class Inner {
        private int innerVar;

        public Inner(int innerVar) {
            this.innerVar = innerVar;
        }

        void innerTest() {
            System.out.println(innerVar);
            System.out.println(outerVar);
        }
    }
}

class MainOuterInner {
    public static void main(String[] args) {
        Outer.Inner inner = new Outer().new Inner(1);
    }
}