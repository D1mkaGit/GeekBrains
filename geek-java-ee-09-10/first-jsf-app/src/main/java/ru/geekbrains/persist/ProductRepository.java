package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
@Named
public class ProductRepository {

    private final Map<Long, Product> productMap = new HashMap<>();

    private final AtomicLong identity = new AtomicLong();

    @PostConstruct
    public void init() {
        this.save(new Product(null, "Product 1", new BigDecimal(100)));
        this.save(new Product(null, "Product 2", new BigDecimal(200)));
        this.save(new Product(null, "Product 3", new BigDecimal(300)));
        this.save(new Product(null, "Продукт 4", new BigDecimal(300)));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        return productMap.put(product.getId(), product);
    }

    public void delete(long id) {
        productMap.remove(id);
    }
}
