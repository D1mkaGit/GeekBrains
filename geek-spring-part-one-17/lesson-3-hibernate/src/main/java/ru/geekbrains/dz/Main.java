package ru.geekbrains.dz;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.dz.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        List<Goods> goods = em.createQuery("SELECT g FROM Goods g", Goods.class).getResultList();
        List<Order> orders;

        em.getTransaction().begin();
        if (customers.isEmpty()) { // заполняем дефолтовых кастомеров если пусто
            System.out.println("Customer list is empty, adding customers to database...");
            String[] customerNames = new String[]{"Alex", "David", "Alisa", "Max"};

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

        em.close();

        // Начинаем покупки
        em = emFactory.createEntityManager();

        // Обнавляем колекции юзеров и товаров
        customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        goods = em.createQuery("SELECT c FROM Goods c", Goods.class).getResultList();

        em.getTransaction().begin();
        System.out.println("Customers make orders...");
        makeOrder(em, customers.get(0), goods.get(1));
        makeOrder(em, customers.get(1), goods.get(2));
        makeOrder(em, customers.get(2), goods.get(0));
        em.getTransaction().commit();


        Scanner in = new Scanner(System.in);
        printListOfCustomers(customers);
        System.out.println("Please type name of one of the customers to show orders (if you want to skip showing type /x): ");
        String customerNameToShow = in.nextLine();
        Customer customerToShow = null;
        boolean haveOrders = false;
        while (true) {
            if (customerNameToShow.equalsIgnoreCase("/x")) break;
            for (Customer c : customers) {
                if (customerNameToShow.equals(c.getName())) {
                    customerToShow = c;
                    orders = em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
                    for (Order order : orders) {
                        if (order.getCustomer().equals(customerToShow)) {
                            System.out.println(customerNameToShow + " have been ordered " + order.getGoods().getName() + " for " + order.getCustomer_price() + " eur");
                            haveOrders = true;
                        }
                    }
                    break;
                }
            }
            if (customerToShow != null) break;
            System.out.println("Incorrect customer name please try again (if you want skip deletion type /x): ");
            customerNameToShow = in.nextLine();
        }
        if (!haveOrders && !customerNameToShow.equalsIgnoreCase("/x")) System.out.println(customerNameToShow + " have no orders.");
        pauseCmd(in);

        System.out.println("Clients list by goods...");
        // берем последние данные по ордерам из базы
        orders = em.createQuery("SELECT o FROM Order o", Order.class).getResultList();

        List<String> orderedGoods = new ArrayList<>();
        for (Order order : orders) {
            int goodsIdInOrder = order.getGoods().getId();
            int customerIdInOrder = order.getCustomer().getId();
            String gName = null;
            String cName = null;

            for (Goods g : goods) {
                if (goodsIdInOrder == g.getId()) {
                    gName = g.getName();
                    if (!orderedGoods.contains(g.getName())) orderedGoods.add(g.getName());
                }
            }
            for (Customer customer : customers) {
                if (customer.getId().equals(customerIdInOrder))
                    cName = customer.getName();
            }
            if (gName != null) {
                System.out.println(gName + " has been ordered by: " + cName + " and cost was " + order.getCustomer_price() + " eur");
            }
        }
        for (Goods g : goods) {
            if (!orderedGoods.contains(g.getName())) System.out.println(g.getName() + " have not been ordered");
        }

        printListOfGoods(goods);
        System.out.println("Please type name of one of the goods to show orders (if you want to skip showing type /x): ");
        String goodsNameToShow = in.nextLine();
        Goods goodsToShow = null;
        boolean haveClientOrders = false;
        while (true) {
            if (goodsNameToShow.equalsIgnoreCase("/x")) break;
            for (Goods g : goods) {
                if (goodsNameToShow.equals(g.getName())) {
                    goodsToShow = g;
                    orders = em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
                    for (Order order : orders) {
                        if (order.getGoods().equals(goodsToShow)) {
                            System.out.println(goodsNameToShow + " have been ordered  by " + order.getCustomer().getName() + " for " + order.getCustomer_price() + " eur");
                            haveClientOrders = true;
                        }
                    }
                    break;
                }
            }
            if (goodsToShow != null) break;
            System.out.println("Incorrect goods name please try again (if you want skip deletion type /x): ");
            goodsNameToShow = in.nextLine();
        }
        if (!haveClientOrders && !goodsNameToShow.equalsIgnoreCase("/x")) System.out.println(goodsNameToShow + " have not been ordered by anybody.");
        pauseCmd(in);

        em.close();

        // начинаем удаление
        em = emFactory.createEntityManager();
        goods = em.createQuery("SELECT g FROM Goods g", Goods.class).getResultList();
        System.out.println("Removing goods...");
        printListOfGoods(goods);
        System.out.println("Please type name of one of the goods to delete (if you want to skip deleting type /x): ");
        String goodsNameToDelete = in.nextLine();
        Goods goodsToDelete = null;
        while (true) {
            for (Goods g : goods) {
                if (goodsNameToDelete.equals(g.getName())) {
                    goodsToDelete = g;
                    break;
                }
                // тут можно набивать list продуктов на удоление, если захотим сделать мульти удаление
            }
            if (goodsToDelete != null || goodsNameToDelete.equalsIgnoreCase("/x")) break;
            System.out.println("Incorrect goods name please try again (if you want skip deletion type /x): ");
            goodsNameToDelete = in.nextLine();
        }

        if (goodsToDelete == null) {
            if (goodsNameToDelete.equalsIgnoreCase("/x")) {
                System.out.println("Skipping deletion");
            }
        } else {
            em.getTransaction().begin();
            removeGoods(em, goodsToDelete);
            em.getTransaction().commit();
        }
        pauseCmd(in);

        customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        System.out.println("Removing customers...");
        printListOfCustomers(customers);
        System.out.println("Please type name of one of the customers to delete (if you want to skip deleting type /x): ");
        String customerNameToDelete = in.nextLine();
        Customer customerToDelete = null;
        while (true) {
            for (Customer c : customers) {
                if (customerNameToDelete.equals(c.getName())) {
                    customerToDelete = c;
                    break;
                }
                // тут можно набивать list кастомаров на удоление, если захотим сделать мульти удаление
            }
            if (customerToDelete != null || customerNameToDelete.equalsIgnoreCase("/x")) break;
            System.out.println("Incorrect customer name please try again (if you want skip deletion type /x): ");
            customerNameToDelete = in.nextLine();
        }
        in.close();

        if (customerToDelete == null) {
            if (customerNameToDelete.equalsIgnoreCase("/x")) {
                System.out.println("Skipping deletion");
            }
        } else {
            em.getTransaction().begin();
            removeCustomer(em, customerToDelete);
            em.getTransaction().commit();
        }

        em.close();
    }

    private static void pauseCmd(Scanner in) {
        System.out.print("Press any key to continue . . . ");
        in.nextLine();
    }

    private static void printListOfGoods(List<Goods> goods) {
        System.out.println("List of goods in db:");
        for (Goods g : goods) {
            System.out.println(g.getName());
        }
    }

    private static void printListOfCustomers(List<Customer> customers) {
        System.out.println("List of customers in db:");
        for (Customer c : customers) {
            System.out.println(c.getName());
        }
    }

    private static void removeGoods(EntityManager em, Goods goods) {
        System.out.println("Removing goods: " + goods.getName());
        em.remove(goods);
    }

    private static void removeCustomer(EntityManager em, Customer customer) {
        System.out.println("Removing customer: " + customer.getName());
        em.remove(customer);
    }

    private static void makeOrder(EntityManager em, Customer c, Goods g) {
        Order o = new Order(null, g, c, g.getCost());
        em.persist(o);
        System.out.println("Customer " + c.getName() + " is ordering " + g.getName() + " for " + g.getCost() + " eur.");
    }
}
