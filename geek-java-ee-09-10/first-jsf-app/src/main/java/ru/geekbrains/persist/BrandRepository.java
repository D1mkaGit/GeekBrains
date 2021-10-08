package ru.geekbrains.persist;

import ru.geekbrains.rest.BrandResource;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
public class BrandRepository implements BrandResource{

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Brand> findAll() {
        return em.createQuery("from Brand ", Brand.class)
                .getResultList();
    }

    public Optional<Brand> findById(long id) {
        return Optional.ofNullable(em.find(Brand.class, id));
    }

    public Brand findByIdOrException(long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
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

    public Brand insert(Brand brand) {
        if (brand.getId() != null) {
            throw new RuntimeException("Id should be null for new Brand");
        }
        Brand saved = this.save(brand);
        return new Brand(
                saved.getId(),
                saved.getName()
        );
    }

    public Brand update(Brand brand) {
        if (brand.getId() == null) {
            throw new RuntimeException("Id shouldn't be null for new Brand");
        }
        Brand saved = this.save(brand);
        return new Brand(
                saved.getId(),
                saved.getName()
        );
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
