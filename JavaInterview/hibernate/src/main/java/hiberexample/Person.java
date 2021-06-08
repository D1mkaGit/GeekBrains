package hiberexample;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

public class Person {

    @Id
    private String id;

    @ManyToMany(mappedBy = "workingPlaces")
    private Collection<Company> companies;

}
