package ru.geekbrains.service;

import ru.geekbrains.ProductServiceRemote;
import ru.geekbrains.dto.ProductRemoteDto;
import ru.geekbrains.persist.BrandRepository;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.service.dto.ProductDto;

import javax.ejb.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Remote(ProductServiceRemote.class)
@Local(ProductService.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @EJB
    private BrandRepository brandRepository;

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductServiceImpl::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findByCategoryId(long categoryId) {
        return productRepository.findAllById(categoryId).stream()
                .map(ProductServiceImpl::convert)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> findById(long id) {
        return productRepository.findById(id)
                .map(ProductServiceImpl::convert);
    }

    @TransactionAttribute
    public Product save(ProductDto productDto) {
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                categoryRepository.getReference(productDto.getCategoryId()),
                brandRepository.getReference(productDto.getBrandId())
        );
        return productRepository.save(product);
    }

    @TransactionAttribute
    public void delete(long id) {
        productRepository.delete(id);
    }

    public long count() {
        return productRepository.count();
    }

    private static ProductDto convert(Product prod) {
        return new ProductDto(
                prod.getId(),
                prod.getName(),
                prod.getPrice(),
                prod.getCategory() != null ? prod.getCategory().getId() : null,
                prod.getCategory() != null ? prod.getCategory().getName() : null,
                prod.getBrand() != null ? prod.getBrand().getId() : null,
                prod.getBrand() != null ? prod.getBrand().getName() : null
        );
    }

    @Override
    public List<ProductRemoteDto> findAllRemote() {
        return productRepository.findAll().stream()
                .map(prod -> new ProductRemoteDto(
                        prod.getId(),
                        prod.getName(),
                        prod.getPrice(),
                        prod.getCategory() != null ? prod.getCategory().getId() : null,
                        prod.getCategory() != null ? prod.getCategory().getName() : null,
                        prod.getBrand() != null ? prod.getBrand().getId() : null,
                        prod.getBrand() != null ? prod.getBrand().getName() : null
                ))
                .collect(Collectors.toList());
    }
}
