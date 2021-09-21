package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
@Named
public class CategoryRepository {

    private final Map<Long, Category> categoryMap = new HashMap<>();

    private final AtomicLong identity = new AtomicLong();

    @PostConstruct
    public void init() {
        this.save(new Category(null, "Category 1"));
        this.save(new Category(null, "Category 2"));
        this.save(new Category(null, "Category 3"));
        this.save(new Category(null, "Категрия 4"));
        this.save(new Category(null, "Категрия 5"));
    }

    public List<Category> findAll() {
        return new ArrayList<>(categoryMap.values());
    }

    public Optional<Category> findById(long id) {
        return Optional.ofNullable(categoryMap.get(id));
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            category.setId(identity.incrementAndGet());
        }
        return categoryMap.put(category.getId(), category);
    }

    public void delete(long id) {
        categoryMap.remove(id);
    }
}
