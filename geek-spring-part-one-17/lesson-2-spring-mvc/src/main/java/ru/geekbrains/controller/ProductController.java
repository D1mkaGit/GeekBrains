package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String allProducts(Model model,
                              @RequestParam(value = "minP", required = false) BigDecimal minP,
                              @RequestParam(value = "maxP", required = false) BigDecimal maxP,
                              @RequestParam(value = "pageN", required = false, defaultValue = "1") Integer pageN) {
        Page<Product> allProducts;
        int realPageNumber = 0;
        int productsCountPerPage = 5;
        int numOfPages;

        if (pageN != null && pageN > 0) realPageNumber = pageN - 1;
        else pageN = 1;

        if (minP != null && maxP != null) {
            allProducts = productRepository.findByCostBetween(minP, maxP, PageRequest.of(realPageNumber, productsCountPerPage));
        } else {
            if (minP != null && !minP.equals("")) {
                allProducts = productRepository.findByCostGreaterThan(minP, PageRequest.of(realPageNumber, productsCountPerPage));
            } else if (maxP != null && !maxP.equals("")) {
                allProducts = productRepository.findByCostLessThan(maxP, PageRequest.of(realPageNumber, productsCountPerPage));
            } else {
                allProducts = productRepository.findAll(PageRequest.of(realPageNumber, productsCountPerPage));
            }
        }
        numOfPages = (int) Math.ceil((float) allProducts.getTotalElements() / productsCountPerPage);

        // TODO придумать как перекидывать на последнюю стнаицу, т.к. получаем количество стнаиц только после филтрации,
        //  а для отрисовки результатов, фильтра, количество страниц уже должно быть известно

        model.addAttribute("page", pageN);
        model.addAttribute("numOfPages", numOfPages);
        model.addAttribute("products", allProducts);
        return "products";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "product";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String openAddProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") int id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

}
