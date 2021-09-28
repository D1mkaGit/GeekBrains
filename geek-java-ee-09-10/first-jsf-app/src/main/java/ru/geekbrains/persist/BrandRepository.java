package ru.geekbrains.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class BrandRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Brand> findAll() {
        return em.createQuery("from Brand ", Brand.class)
                .getResultList();
    }

    public Optional<Brand> findById(long id) {
        return Optional.ofNullable(em.find(Brand.class, id));
    }

    public Brand getReference(Long id) {
        return em.getReference(Brand.class, id);
    }

    @TransactionAttribute
    public Brand save(Brand brand) {
        if (brand.getId() == null) {
            em.persist(brand);
            return brand;
        }
        return em.merge(brand);
    }

    @TransactionAttribute
    public void delete(long id) {
        em.createQuery("delete from Brand where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public long count() {
        return em.createQuery("select count(*) from Brand ", Long.class)
                .getSingleResult();
    }
}
