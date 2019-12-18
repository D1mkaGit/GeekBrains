package lesson_1;

interface Operationable {
    int calculate(int x, int y);
}

interface Printable {
    void print(String s);
}


/////////////////
//class LambdaApp {
//
//    public static void main(String[] args) {
//
//        Operationable op = new Operationable(){
//
//            public int calculate(int x, int y){
//
//                return x + y;
//            }
//        };
//        int z = op.calculate(20, 10);
//        System.out.println(z); // 30
//    }
//}
//
//interface Operationable{
//    int calculate(int x, int y);
//}


///////// Терминальные лямбда-выражения. ничего не возвращают

public class Lamba {
    public static void main(String[] args) {

        Operationable operation;
        operation = (x, y) -> x + y;

        int result = operation.calculate(10, 20);
        System.out.println(result); //30
    }
}

class LambdaApp1 {

    // Блоки кода в лямбда-выражениях
    Operationable operation = (int x, int y) -> {

        if (y == 0)
            return 0;
        else
            return x / y;
    };

    public static void main(String[] args) {

//        Printable printer = s->System.out.println(s);
//        printer.print("Hello Java!");

        //

        LambdaApp1 lambdaApp1 = new LambdaApp1();
        lambdaApp1.test();
    }

    public void test() {
        System.out.println(operation.calculate(20, 10));
    }
}

