package lesson_2.dayofweek;

/**
 * Требуется реализовать enum DayOfWeek, который будет представлять дни недели.
 * С его помощью необходимо решить задачу определения кол-ва рабочих часов до конца недели по заднному текущему дню.
 * <p>
 * Возвращает кол-во оставшихся рабочих часов до конца
 * недели по заданному текущему дню. Считается, что
 * текущий день ещё не начался, и рабочие часы за него
 * должны учитываться.
 */

public class DayOfWeekMain {

    public static void main(String[] args) {
        // проверка всех дней недели через цикл
        for (DayOfWeek d : DayOfWeek.values()) {
            new DayOfWeekMain().getWorkingHours(d);
        }

        // тут можно получить по одному дню, указав его "enum"
        /*new DayOfWeekMain().getWorkingHours(MONDAY);*/
    }

    private void getWorkingHours(DayOfWeek dayOfWeek) {
        int numOfWorkingHoursPerDay = 8;
        int numOfWorkingDays = 5;
        if (dayOfWeek.ordinal() < numOfWorkingDays)
            System.out.println("Сегодня " + dayOfWeek.getRus() + ". Осталось "
                    + (numOfWorkingDays - dayOfWeek.ordinal()) * numOfWorkingHoursPerDay +
                    " рабочих часов до конца недели.");
        else System.out.println(dayOfWeek.getRus() + " - это выходной день.");
    }
}
