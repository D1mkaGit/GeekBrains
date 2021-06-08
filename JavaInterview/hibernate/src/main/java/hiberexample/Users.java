package hiberexample;

import javax.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator")
    @SequenceGenerator(name = "sequence-generator",
            sequenceName = "user_sequence", initialValue = 4)
    private long id;

    @Column
    private String name;

    public Users() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // standard constructor, getters, setters
}

