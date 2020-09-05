package ru.geekbrains.dz;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.dz.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        List<Goods> goods = em.createQuery("SELECT c FROM Goods c", Goods.class).getResultList();

        em.getTransaction().begin();
        if (customers.isEmpty()) { // заполняем дефолтовых кастомеров если пусто
            System.out.println("Customer list is empty, adding customers to database...");
            String[] customerNames = new String[]{"Alex", "David", "Alisa"};

            for (String customerName : customerNames) {
                Customer c = new Customer(null, customerName);
                em.persist(c);
                System.out.println("Adding new customer: " + customerName);
            }
        }

        if (goods.isEmpty()) { // заполняем дефолтовыми продуктами если пусто
            System.out.println("Goods list is empty, adding goods to database...");
            Goods[] goodsToAdd = new Goods[]{
                    new Goods(null, "MacBook Air 13\"", "1000"),
                    new Goods(null, "Lenovo T420 14\"", "700"),
                    new Goods(null, "Lenovo T430 14\"", "900"),
                    new Goods(null, "Lenovo T435 14\"", "930")
            };
            for (Goods value : goodsToAdd) {
                System.out.println("Adding new goods: " + value.toString());
                em.persist(value);
            }
        }
        em.getTransaction().commit();

        // Обнавляем колекции юзеров и товаров
        customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        goods = em.createQuery("SELECT c FROM Goods c", Goods.class).getResultList();

        em.getTransaction().begin();
        System.out.println("Customers make orders...");
        makeOrder(customers.get(0), goods.get(1));
        makeOrder(customers.get(1), goods.get(2));
        makeOrder(customers.get(2), goods.get(0));
        em.getTransaction().commit();

        System.out.println("Clients list by goods...");
        for (Goods g : goods) {
            if (!g.getGoodsBuyers().isEmpty()) {
                System.out.println(g.getName() + " has been ordered by:");
                for (int j = 0; j < g.getGoodsBuyers().size(); j++) {
                    System.out.println(g.getGoodsBuyers().get(j).getName());
                }
            } else {
                System.out.println(g.getName() + " have not been ordered");
            }
        }

        System.out.println("Removing goods...");

        em.getTransaction().begin();
        removeGoods(em, goods.get(3));
        removeGoods(em, goods.get(2));
        em.getTransaction().commit();


        System.out.println("Removing customers...");

        em.getTransaction().begin();
        removeCustomer(em, customers.get(2));
        em.getTransaction().commit();

        em.close();

    }

    private static void removeGoods(EntityManager em, Goods goods) {
        System.out.println("Removing goods: " + goods.getName() );
        em.remove(goods);
    }

    private static void removeCustomer(EntityManager em, Customer customer) {
        System.out.println("Removing customer: " + customer.getName() );
        em.remove(customer);
    }

    private static void makeOrder(Customer c, Goods g){
        c.setCustomerOrder(g);
        g.setGoodsBuyer(c);
        System.out.println("Customer " + c.getName() + " is ordering " + g.getName() + " for " + g.getCost() + " eur.");
    }
}
