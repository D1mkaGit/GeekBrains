package ru.geekbrains.dz;

import javax.persistence.*;

@Entity
@Table(name = "customer_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column
    private String customer_price;

    public Order() {
    }

    public Order(Integer id, Goods goods, Customer customer, String customer_price) {
        this.id = id;
        this.goods = goods;
        this.customer = customer;
        this.customer_price = customer_price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomer_price() {
        return customer_price;
    }

    public void setCustomer_price(String customer_price) {
        this.customer_price = customer_price;
    }
}
