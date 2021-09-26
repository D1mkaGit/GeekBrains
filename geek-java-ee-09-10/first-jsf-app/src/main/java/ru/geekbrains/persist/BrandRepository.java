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
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named
public class BrandRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() {
        if (this.count() == 0) {
            try {
                ut.begin();
                this.save(new Brand(null, "Band 1"));
                this.save(new Brand(null, "Brand 2"));
                this.save(new Brand(null, "Brand 3"));
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

    @Transactional
    public Brand save(Brand brand) {
        if (brand.getId() == null) {
            em.persist(brand);
            return brand;
        }
        return em.merge(brand);
    }

    @Transactional
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
