package ru.geekbrains.service;

import ru.geekbrains.persist.Brand;
import ru.geekbrains.service.dto.BrandDto;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<BrandDto> findAll();

    Optional<BrandDto> findById(long id);

    Brand save(BrandDto brandDto);

    void delete(long id);

}
