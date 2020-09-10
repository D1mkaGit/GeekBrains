package ru.geekbrains.dz;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL
    )
    private List<OrderItem> customerOrderItems;

    public Customer() {
    }

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.customerOrderItems = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderItem> getCustomerOrders() {
        return customerOrderItems;
    }

    public void setCustomerOrders(List<OrderItem> customerOrderItems) {
        this.customerOrderItems = customerOrderItems;
    }
}
