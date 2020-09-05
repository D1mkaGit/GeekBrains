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

    @ManyToMany
    @JoinTable(
            name = "customer_orders",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id")
    )
    private List<Goods> customerOrders;


    public Customer() {
    }

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.customerOrders = new ArrayList<>();
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

    public List<Goods> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<Goods> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public void setCustomerOrder(Goods customerOrder) {
        this.customerOrders.add(customerOrder);
    }
}
