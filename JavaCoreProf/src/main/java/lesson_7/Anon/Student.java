package lesson_7.Anon;

@XTable(name = "students")
public class Student {
    @XField
    int id;

    @XField
    String name;

    @XField
    int score;

    @XField
    String email;

    public Student( int id, String name, int score, String email ) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.email = email;
    }
}