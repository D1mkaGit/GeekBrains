package lesson_2.exceptions;

public class ExceptionsMain {
    /**
     * 3. В методе main() вызвать полученный метод,
     * обработать возможные исключения MySizeArrayException и MyArrayDataException и вывести результат расчета.
     */

    public static void main(String[] args) {

        try {
            // correct array of symbols (not for numbers check)
            /*System.out.println("***MyArrayDataException***");
            new CheckExceptions().checkArray(new String[][]{
            {"1", "2", "3", "4"},
            {"1", "2", "/", "d"},
            {"1", "2", "e", "f"},
            {"1", "2", "g", "f"}});*/

            // incorrect array 1
            /*System.out.println("***MyArraySizeException***");
            new CheckExceptions().checkArray(new String[][]{
            {"1", "2", "3", "4"},
            {"1", "2", "c", "d"},
            {"1", "2", "e", "f"}
            });*/

            // incorrect array 2
            /*System.out.println("***MyArraySizeException**");
            new CheckExceptions().checkArray(new String[][]{
            {"1", "2", "3", "4"},
            {"1", "2", "c", "d"},
            {"1", "2", "e", "f"},
            {"1","2"}});*/

            System.out.println("***Корректный массив с суммой***");
            new CheckExceptions().checkArray(new String[][]{
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"}
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
