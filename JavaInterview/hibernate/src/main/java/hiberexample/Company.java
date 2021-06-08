package hiberexample;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import java.util.Collection;

@Entity
public class Company {

    @Id
    private String id;

    @Version
    private long version;

    private String name;

    @ManyToMany(mappedBy = "workingPlaces")
    private Collection<Person> workers;

    public Company() {
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Person> getWorkers() {
        return workers;
    }

    public void setWorkers(Collection<Person> workers) {
        this.workers = workers;
    }
}


