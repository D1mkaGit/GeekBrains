package ru.geekbrains.service;

import ru.geekbrains.ProductServiceRemote;
import ru.geekbrains.dto.ProductRemoteDto;
import ru.geekbrains.persist.BrandRepository;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.rest.ProductResource;
import ru.geekbrains.service.dto.ProductDto;

import javax.ejb.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Remote(ProductServiceRemote.class)
@Local({ProductService.class, ProductResource.class})
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductResource {

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

    public ProductDto findByIdOrException(long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
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

    public ProductDto update(ProductDto productDto) {
        if (productDto.getId() == null) {
            throw new RuntimeException("Id shouldn't be null for new Product");
        }
        Product saved = this.save(productDto);
        return new ProductDto(saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getCategory().getId(),
                saved.getCategory().getName(),
                saved.getBrand().getId(),
                saved.getBrand().getName());
    }

    public ProductDto insert(ProductDto productDto) {
        if (productDto.getId() != null) {
            throw new RuntimeException("Id should be null for new Product");
        }
        Product saved = this.save(productDto);
        return new ProductDto(saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getCategory().getId(),
                saved.getCategory().getName(),
                saved.getBrand().getId(),
                saved.getBrand().getName());
    }

    @TransactionAttribute
    public void delete(long id) {
        productRepository.delete(id);
    }

    public long count() {
        return productRepository.count();
    }

    static ProductDto convert(Product prod) {
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
