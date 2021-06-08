package hiberexample;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType. SEQUENCE)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "category")
    private List<Product> product;

    public Category() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

