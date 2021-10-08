package ru.geekbrains.service;

import ru.geekbrains.persist.Brand;
import ru.geekbrains.persist.BrandRepository;
import ru.geekbrains.rest.BrandResource;
import ru.geekbrains.service.dto.BrandDto;


import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class BrandServiceImpl implements BrandService, BrandResource {

    @EJB
    private BrandRepository brandRepository;

    public List<BrandDto> findAll() {
        return brandRepository.findAll().stream()
                .map(BrandServiceImpl::convert)
                .collect(Collectors.toList());
    }

    public Optional<BrandDto> findById(long id) {
        return brandRepository.findById(id)
                .map(BrandServiceImpl::convert);
    }

    public BrandDto findByIdOrException(long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @TransactionAttribute
    public Brand save(BrandDto brandDto) {
        Brand brand = new Brand(
                brandDto.getId(),
                brandDto.getName()
        );
        return brandRepository.save(brand);
    }

    public BrandDto update(BrandDto brandDto) {
        if (brandDto.getId() == null) {
            throw new RuntimeException("Id shouldn't be null for new Brand");
        }
        Brand saved = this.save(brandDto);
        return new BrandDto(
                saved.getId(),
                saved.getName()
        );
    }

    public BrandDto insert(BrandDto brandDto) {
        if (brandDto.getId() != null) {
            throw new RuntimeException("Id should be null for new Brand");
        }
        Brand saved = this.save(brandDto);
        return new BrandDto(
                saved.getId(),
                saved.getName()
        );
    }

    @TransactionAttribute
    public void delete(long id) {
        brandRepository.delete(id);
    }

    private static BrandDto convert(Brand brand) {
        return new BrandDto(
                brand.getId(),
                brand.getName()
        );
    }
}
