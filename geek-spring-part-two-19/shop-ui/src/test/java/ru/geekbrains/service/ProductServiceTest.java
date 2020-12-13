package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;
    private Category expectedCategory;
    private Brand expectedBrand;
    private Product expectedProduct;
    private List<Product> expectedProducts;

    @BeforeEach
    public void init() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testFindById() {
        setupExpectedDefaultProductWithCategoryAndBrand();

        when(productRepository.findById(eq(1L)))
                .thenReturn(Optional.of(expectedProduct));

        Optional<ProductRepr> opt = productService.findById(1L);

        assertTrue(opt.isPresent());
        assertEquals(expectedProduct.getId(), opt.get().getId());
        assertEquals(expectedProduct.getName(), opt.get().getName());
    }

    @Test
    public void testFindByCategoryId() {
        setupExpectedDefaultProductWithCategoryAndBrand();

        when(productRepository.findByCategoryId(eq(1L)))
                .thenReturn(List.of(expectedProduct));

        List<ProductRepr> lst = productService.findByCategoryId(1L);

        assertTrue(lst.size() > 0);
        assertEquals(expectedProduct.getId(), lst.get(0).getId());
        assertEquals(expectedProduct.getName(), lst.get(0).getName());
    }

    @Test
    public void testFindByBrandyId() {
        setupExpectedDefaultProductWithCategoryAndBrand();

        when(productRepository.findByBrandId(eq(1L)))
                .thenReturn(List.of(expectedProduct));

        List<ProductRepr> lst = productService.findByBrandId(1L);

        assertTrue(lst.size() > 0);
        assertEquals(expectedProduct.getId(), lst.get(0).getId());
        assertEquals(expectedProduct.getName(), lst.get(0).getName());
    }

    @Test
    public void testFindAll() {
        expectedProducts = new ArrayList<>();
        setupProductInProductsList(1L, "Test product1");
        setupProductInProductsList(2L, "Test product2");

        when(productRepository.findAll())
                .thenReturn(expectedProducts);

        List<ProductRepr> lst = productService.findAll();

        assertEquals(lst.size(), 2);

    }

    @Test
    public void testProductsByDistinctCategoryId() {
        expectedProducts = new ArrayList<>();
        addProductToProductsListWithSpecificBrandAndSpecificCategory(
                1L, "Cat1", 1L, "Brand1", 1L, "Prod1");
        addProductToProductsListWithSpecificBrandAndSpecificCategory(
                1L, "Cat1", 1L, "Brand1", 2L, "Prod2");
        addProductToProductsListWithSpecificBrandAndSpecificCategory(
                2L, "Cat2", 2L, "Brand2", 3L, "Prod3");

        when(productRepository.findAll())
                .thenReturn(expectedProducts);
        List<Long> lst = productService.findDistinctCategoryId();

        assertEquals(lst.size(), 2);
        assertEquals(expectedProducts.get(0).getCategory().getId(), lst.get(0).longValue());
        assertEquals(expectedProducts.get(2).getCategory().getId(), lst.get(1).longValue());
    }

    @Test
    public void testProductsByDistinctBrandId() {
        expectedProducts = new ArrayList<>();
        addProductToProductsListWithSpecificBrandAndSpecificCategory(
                1L, "Cat1", 1L, "Brand1", 1L, "Prod1");
        addProductToProductsListWithSpecificBrandAndSpecificCategory(
                1L, "Cat1", 2L, "Brand2", 2L, "Prod2");
        addProductToProductsListWithSpecificBrandAndSpecificCategory(
                2L, "Cat2", 1L, "Brand1", 3L, "Prod3");

        when(productRepository.findAll())
                .thenReturn(expectedProducts);

        List<Long> lst = productService.findDistinctBrandId();

        assertEquals(lst.size(), 2);
        assertEquals(expectedProducts.get(0).getBrand().getId(), lst.get(0).longValue());
        assertEquals(expectedProducts.get(1).getBrand().getId(), lst.get(1).longValue());
    }


    private void setupExpectedDefaultProductWithCategoryAndBrand() {
        setupExpectedSpecifiedProductWithCategoryAndBrand(
                1L, "Category name",
                1L, "Brand name",
                1L, "Product name");
    }

    private void setupExpectedSpecifiedProductWithCategoryAndBrand(
            Long catId, String catName,
            Long brandId, String brandName,
            Long prodId, String prodName) {
        setupSpecificCategory(catId, catName);

        setupSpecificBrand(brandId, brandName);

        setupSpecificProduct(prodId, prodName);
    }

    private void setupSpecificProduct(Long prodId, String prodName) {
        expectedProduct = new Product();
        expectedProduct.setId(prodId);
        expectedProduct.setName(prodName);
        expectedProduct.setCategory(expectedCategory);
        expectedProduct.setBrand(expectedBrand);
        expectedProduct.setPictures(new ArrayList<>());
        expectedProduct.setPrice(new BigDecimal(12345));
    }

    private void setupProductInProductsList(Long prodId, String prodName) {
        setupExpectedDefaultProductWithCategoryAndBrand();
        setupSpecificProduct(prodId, prodName);
        expectedProducts.add(expectedProduct);
    }

    private void addProductToProductsListWithSpecificBrandAndSpecificCategory(
            Long catId, String catName,
            Long brandId, String brandName,
            Long prodId, String prodName) {
        setupSpecificCategory(catId, catName);
        setupSpecificBrand(brandId, brandName);
        setupSpecificProduct(prodId, prodName);
        expectedProducts.add(expectedProduct);
    }

    private void setupSpecificBrand(Long brandId, String brandName) {
        expectedBrand = new Brand();
        expectedBrand.setId(brandId);
        expectedBrand.setName(brandName);
    }

    private void setupSpecificCategory(Long catId, String catName) {
        expectedCategory = new Category();
        expectedCategory.setId(catId);
        expectedCategory.setName(catName);
    }

}
