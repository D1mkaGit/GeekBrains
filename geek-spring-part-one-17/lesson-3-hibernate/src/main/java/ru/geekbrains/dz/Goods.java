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

    @OneToMany(
            mappedBy = "goods",
            cascade = CascadeType.ALL
    )
    private List<OrderItem> goodsOrderItems;

    public Goods() {
    }

    public Goods(Integer id, String name, String cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.goodsOrderItems = new ArrayList<>();
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

    public List<OrderItem> getGoodsOrders() {
        return goodsOrderItems;
    }

    public void setGoodsOrders(List<OrderItem> orderItems) {
        this.goodsOrderItems = orderItems;
    }

    @Override
    public String toString() {
        return getId() + " " + getName() + " " + getCost();
    }
}
