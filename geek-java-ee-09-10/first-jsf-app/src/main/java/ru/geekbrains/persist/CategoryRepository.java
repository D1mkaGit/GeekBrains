package ru.geekbrains.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class CategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Category> findAll() {
        return em.createQuery("from Category ", Category.class)
                .getResultList();
    }

    public Optional<Category> findById(long id) {
        return Optional.ofNullable(em.find(Category.class, id));
    }

    public Category getReference(Long id) {
        return em.getReference(Category.class, id);
    }

    @TransactionAttribute
    public Category save(Category category) {
        if (category.getId() == null) {
            em.persist(category);
            return category;
        }
        return em.merge(category);
    }

    @TransactionAttribute
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
