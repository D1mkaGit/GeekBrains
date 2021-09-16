package ru.geekbrains.listener;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();

        productRepository.save(new Product(null, "Product 1", new BigDecimal(100)));
        productRepository.save(new Product(null, "Product 2", new BigDecimal(200)));
        productRepository.save(new Product(null, "Product 3", new BigDecimal(300)));
        productRepository.save(new Product(null, "Продукт 4", new BigDecimal(300)));

        sce.getServletContext().setAttribute("productRepository", productRepository);
    }
}
