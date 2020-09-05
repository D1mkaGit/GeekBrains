package ru.geekbrains.dz;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String cost;

    @ManyToMany
    @JoinTable(
            name = "customer_orders",
            joinColumns = @JoinColumn(name = "goods_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> goodsBuyers;

    public Goods() {
    }

    public Goods(Integer id, String name, String cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.goodsBuyers = new ArrayList<>();
    }

    public Goods(Integer id, String name, String cost, List<Customer> goodsBuyers) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.goodsBuyers = goodsBuyers;
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public List<Customer> getGoodsBuyers() {
        return goodsBuyers;
    }

    public void setGoodsBuyers(List<Customer> goodsBuyers) {
        this.goodsBuyers = goodsBuyers;
    }

    public void setGoodsBuyer(Customer goodsBuyer) {
        this.goodsBuyers.add(goodsBuyer);
    }

    @Override
    public String toString() {
        return getId() + " " + getName() + " " + getCost();
    }
}
