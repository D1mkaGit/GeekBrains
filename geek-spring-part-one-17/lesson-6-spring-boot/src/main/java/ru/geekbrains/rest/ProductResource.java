package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.NotFoundException;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/v1/product")
@RestController
public class ProductResource {

    private final ProductRepository productRepository;

    @Autowired
    public ProductResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/all", produces = "application/json")
    List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public Product findById(@PathVariable("id") Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Unable to show Product, because Product", id));
    }

    @PostMapping(consumes = "application/json")
    public Product createProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Id found in the created request");
        }
        productRepository.save(product);
        return product;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Product updateProduct(@RequestBody Product product) {
        if (product.getTitle().equals(productRepository.findById(product.getId()).get().getTitle()) &&
                product.getCost().equals(productRepository.findById(product.getId()).get().getCost())) {
            throw new IllegalArgumentException("Product is not changed");
        }
        productRepository.save(product);
        return product;
    }

    @DeleteMapping(path = "/{id}/id", produces = "application/json")
    public void deleteById(@PathVariable("id") Integer id) {
        if (!productRepository.findById(id).isPresent()) {
            throw new NotFoundException("Unable to DELETE Product, because Product", id);
        }
        productRepository.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException ex) {
        return new ResponseEntity<>(
                ex.getNotFoundEntity() +
                        " with ID:" + ex.getNotFoundId() +
                        " was Not Found!",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> emptyResultDataAccessExceptionHandler(EmptyResultDataAccessException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST);
    }

}
