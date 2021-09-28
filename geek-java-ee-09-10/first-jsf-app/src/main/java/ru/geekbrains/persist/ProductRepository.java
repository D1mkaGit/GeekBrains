package ru.geekbrains.persist;

import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Product> findAll() {
        EntityGraph<?> eg = em.getEntityGraph("product-with-category-graph");
        //return em.createQuery("from Product p left join fetch p.category", Product.class)
        return em.createQuery("from Product", Product.class)
                .setHint("javax.persistence.loadgraph", eg)
                .getResultList();
    }

    public List<Product> findAllById(long categoryId) {
        EntityGraph<?> eg = em.getEntityGraph("product-with-category-graph");
        return em.createQuery("from Product p where p.category.id = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .setHint("javax.persistence.loadgraph", eg)
                .getResultList();
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
            return product;
        }
        return em.merge(product);
    }

    public void delete(long id) {
        em.createQuery("delete from Product where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public long count() {
        return em.createQuery("select count(*) from Product", Long.class)
                .getSingleResult();
    }
}
