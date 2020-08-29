package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persistance.Product;
import ru.geekbrains.persistance.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String allProducts(Model model) {
        List<Product> allProducts = productRepository.getAllProducts();
        model.addAttribute("products", allProducts);
        return "products";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {
        productRepository.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String openAddProduct(Model model) {
        model.addAttribute("product", new Product(productRepository.getNextId(), "", "0"));
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addNewProduct(Product product) {
        productRepository.addProduct(product.getTitle(), product.getCost());
        return "redirect:/products";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") int id) {
        productRepository.removeProductById(id);
        return "redirect:/products";
    }

}
