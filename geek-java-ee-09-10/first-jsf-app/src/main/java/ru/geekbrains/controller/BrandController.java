package ru.geekbrains.controller;

import ru.geekbrains.persist.Brand;
import ru.geekbrains.persist.BrandRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class BrandController implements Serializable {

    @EJB
    private BrandRepository brandRepository;

    private Brand brand;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public String editBrand(Brand brand) {
        this.brand = brand;
        return "brand_form.xhtml?faces-redirect=true";
    }

    public String addBrand() {
        this.brand = new Brand();
        return "brand_form.xhtml?faces-redirect=true";
    }

    public String saveBrand() {
        brandRepository.save(brand);
        return "brand.xhtml?faces-redirect=true";
    }

    public void deleteBrand(Brand brand) {
        brandRepository.delete(brand.getId());
    }
}
