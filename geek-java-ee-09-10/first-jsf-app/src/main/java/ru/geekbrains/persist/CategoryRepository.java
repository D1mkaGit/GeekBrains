package ru.geekbrains.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityGraph;
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

    public List<Category> findAllWithProducts() {
        EntityGraph<?> eg = em.getEntityGraph("category-with-products-graph");
        return em.createQuery("from Category", Category.class)
                .setHint("javax.persistence.loadgraph", eg)
                .getResultList();
    }

    public Optional<Category> findById(long id) {
        return Optional.ofNullable(em.find(Category.class, id));
    }

    public Category findByIdOrException(long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
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

    public Category insert(Category category) {
        if (category.getId() != null) {
            throw new RuntimeException("Id should be null for new Category");
        }
        Category saved = this.save(category);
        return new Category(
                saved.getId(),
                saved.getName()
        );
    }

    public Category update(Category category) {
        if (category.getId() == null) {
            throw new RuntimeException("Id shouldn't be null for new Category");
        }
        Category saved = this.save(category);
        return new Category(
                saved.getId(),
                saved.getName()
        );
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
