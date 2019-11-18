package lesson_2;

public class FactorialException extends Exception {
    private int number;

    public FactorialException(String msg, int number) {
        super(msg);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

class Factorial {
    public static int getFactorial(int num) throws FactorialException {
        int res = 1;

        if (num < 1) throw new FactorialException("Число не может быть отрицательным", num);

        for (int i = 1; i <= num; i++) {
            res *= i;
        }
        return res;
    }
}


class MainFactorial {
    public static void main(String[] args) {

        String str = "1sdf";

        int a = Integer.parseInt(str);

//        try {
//            int res = Factorial.getFactorial(-10);
//        } catch (FactorialException e) {
//          //  System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
    }
}