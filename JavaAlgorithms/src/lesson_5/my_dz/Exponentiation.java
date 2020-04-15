package lesson_5.my_dz;

import java.math.BigDecimal;

//1. Написать программу по возведению числа в степень с помощью рекурсии.
public class Exponentiation {
    private double number;
    private boolean isNegative;
    private boolean isFirstRun = true;

    private double resetFirstRunAndIsNegative( double number ) {
        isFirstRun = true;
        isNegative = false;
        return number;
    }

    public double calculate( double number, int exponent ) {
        if (number == 0) return resetFirstRunAndIsNegative(0);
        if (exponent == 0) return resetFirstRunAndIsNegative(1);
        if (exponent < 0) {
            this.isNegative = true;
            int negativeValue = -1;
            exponent *= negativeValue;
        }
        if (isFirstRun) {
            this.number = number;
            isFirstRun = false;
        }

        if (exponent == 1) {
            if (isNegative) this.number = 1 / this.number;
            return resetFirstRunAndIsNegative(this.number);
        }
        this.number *= number;
        return calculate(number, exponent - 1);
    }

    public void executeCalculationAndDisplay( double number, int exponent ) {
        double rawResult = calculate(number, exponent);
        String strResult = Double.toString(rawResult);
        if (new BigDecimal(rawResult).scale() == 0) {
            strResult = String.valueOf((int) rawResult);
        }
        System.out.println((number + " в " + exponent + " степени = " + strResult));
    }

    public static void main( String[] args ) {
        Exponentiation exp = new Exponentiation();
        exp.executeCalculationAndDisplay(5, 0);
        exp.executeCalculationAndDisplay(5, 1);
        exp.executeCalculationAndDisplay(2, 2);
        exp.executeCalculationAndDisplay(-5, 1);
        exp.executeCalculationAndDisplay(-5, -1);
        exp.executeCalculationAndDisplay(5, -3);
        exp.executeCalculationAndDisplay(-5, -3);
    }
}
