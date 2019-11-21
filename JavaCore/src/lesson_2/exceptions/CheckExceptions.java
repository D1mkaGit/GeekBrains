package lesson_2.exceptions;

/**
 * 1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4,
 * при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 * 2. Далее метод должен пройтись по всем элементам массива,
 * преобразовать в int, и просуммировать.
 * Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
 * должно быть брошено исключение MyArrayDataException – с детализацией, в какой именно ячейке лежат неверные данные.
 */

public class CheckExceptions {

    public void checkArray(String[][] arrToCheck) throws MyArrayDataException, MyArraySizeException {
        int sumOfInts = 0;
        int arrDim2 = 4;
        int arrDim1 = 4;
        String arraySizeExceptionMessage = "массив должнен быть двумерный и строковый, размером " +
                arrDim1 + "x" + arrDim2;

        if (arrToCheck.length != arrDim1)
            throw new MyArraySizeException(arraySizeExceptionMessage); // проверяем на длинну строки
        for (int i = 0; i < arrToCheck.length; i++) {
            for (int j = 0; j < arrToCheck[i].length; j++) {
                if (arrToCheck[i].length != arrDim1 || arrToCheck[j].length != arrDim2)
                    throw new MyArraySizeException(arraySizeExceptionMessage); //проверяем на длинну столбцы
                try {
                    sumOfInts += Integer.parseInt(arrToCheck[i][j]);
                } catch (NumberFormatException numFormEx) {
                    throw new MyArrayDataException("в строке: " + i + " колонке: " + j + " находится " +
                            "не число, место этого там: " + numFormEx.getMessage().split(": ")[1]);
                    // убираем часть английского месседжа оставляем только проблемный стринг
                }
            }
        }
        System.out.println("Сумма чисел в масиве: " + sumOfInts);
    }
}

class MyArrayDataException extends Exception {

    public MyArrayDataException(String msg) {
        super(msg);
    }
}

class MyArraySizeException extends Exception {

    public MyArraySizeException(String msg) {
        super(msg);
    }
}

