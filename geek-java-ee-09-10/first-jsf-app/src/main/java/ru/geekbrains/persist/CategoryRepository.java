package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.*;

@ApplicationScoped
@Named
public class CategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() {
        if (this.count() == 0) {
            try {
                ut.begin();
                this.save(new Category(null, "Category 1"));
                this.save(new Category(null, "Category 2"));
                this.save(new Category(null, "Category 3"));
                ut.commit();
            } catch (Exception ex) {
                try {
                    ut.rollback();
                } catch (SystemException exx) {
                    throw new RuntimeException(exx);
                }
            }
        }
    }

    public List<Category> findAll() {
        return em.createQuery("from Category ", Category.class)
                .getResultList();
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    public Category getReference(Long id) {
        return em.getReference(Category.class, id);
    }

    @Transactional
    public Category save(Category category) {
        if (category.getId() == null) {
            em.persist(category);
            return category;
        }
        return em.merge(category);
    }

    public void delete(long id) {
        em.createQuery("delete from Category where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public long count() {
        return em.createQuery("select count(*) from Category ", Long.class)
                .getSingleResult();
    }
}
