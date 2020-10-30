package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.repo.BrandRepository;
import ru.geekbrains.repo.CategoryRepository;
import ru.geekbrains.service.ProductService;

@Controller
public class ProductController {

    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    private CategoryRepository categoryRepository;

    private BrandRepository brandRepository;

    @Autowired
    public ProductController(ProductService productService, CategoryRepository categoryRepository,
                              BrandRepository brandRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping ("/products")
    public String adminProductsPage(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/product/{id}/edit")
    public String adminEditProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", productService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "product_form";
    }

    @DeleteMapping("/product/{id}/delete")
    public String adminDeleteProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Products");
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/product/create")
    public String adminCreateProduct(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", new ProductRepr());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "product_form";
    }

    @PostMapping("/product")
    public String adminUpsertProduct(Model model, RedirectAttributes redirectAttributes, ProductRepr product) {
        model.addAttribute("activePage", "Products");

        try {
            productService.save(product);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating product", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (product.getId() == null) {
                return "redirect:/product/create";
            }
            return "redirect:/product/" + product.getId() + "/edit";
        }
        return "redirect:/products";
    }
}
