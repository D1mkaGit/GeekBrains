package lesson_6.dz;

import java.util.stream.IntStream;

//2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
// Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
// идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
// иначе в методе необходимо выбросить RuntimeException.
// Написать набор тестов для этого метода (по 3-4 варианта входных данных). Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
public class ArrayHandler {
    public int[] cutArray( int[] intArray ) {
        int[] newIntArray;
        int numberToCut = 4;
        if (IntStream.of(intArray).anyMatch(x -> x == 4)) {
            int lastNumberPos = 0;
            for (int i = 0; i < intArray.length; i++) {
                if (intArray[i] == numberToCut) lastNumberPos = i + 1;
            }
            newIntArray = new int[intArray.length - lastNumberPos];
            if (intArray.length - lastNumberPos >= 0)
                System.arraycopy(intArray, lastNumberPos, newIntArray, 0, intArray.length - lastNumberPos);
        } else {
            throw new RuntimeException("В массиве нет цифры " + numberToCut);
        }
        return newIntArray;
    }

    //3. Написать метод, который проверяет состав массива из чисел 1 и 4.
    // Если в нем нет хоть одной четверки или единицы, то метод вернет false;
    // Написать набор тестов для этого метода (по 3-4 варианта входных данных).

    public boolean checkArray( int[] intArray ) {
        return IntStream.of(intArray).anyMatch(x -> x == 2) && IntStream.of(intArray).anyMatch(x -> x == 4);
    }

}
