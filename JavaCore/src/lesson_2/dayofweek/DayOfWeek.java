package lesson_2.dayofweek;

//* Требуется реализовать enum DayOfWeek, который будет представлять дни недели.

public enum DayOfWeek {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскрескенье");

    private final String rus;

    DayOfWeek(String rus) {
        this.rus = rus;
    }

    public String getRus() {
        return rus;
    }
}
