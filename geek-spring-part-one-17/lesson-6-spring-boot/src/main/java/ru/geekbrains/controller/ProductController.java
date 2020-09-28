package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.ArrayUtils;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.repo.ProductSpecification;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String allProducts(Model model,
                              @RequestParam(value = "minP", required = false) BigDecimal minP,
                              @RequestParam(value = "maxP", required = false) BigDecimal maxP,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size,
                              @RequestParam("sort") Optional<String> sort,
                              @RequestParam(value = "desc", required = false, defaultValue = "false") boolean desc
    ) {
        logger.info("Filtering by min price: {} max price: {}", minP, maxP);

        String currentSort = sort.orElse("id");
        String[] sortItems = {"id", "title", "cost"};

        if (!ArrayUtils.contains(sortItems, currentSort)) currentSort = "id";

        int productsCountPerPage = 5;
        int currentPage = page.orElse(1);

        Specification<Product> spec = ProductSpecification.trueLiteral();

        Sort.Direction sortDirection = ASC;
        if (desc) sortDirection = DESC;

            PageRequest pageRequest = PageRequest.of(currentPage - 1, size.orElse(productsCountPerPage), Sort.by(sortDirection, currentSort));

        if (minP != null && !minP.equals("")) {
            spec = spec.and(ProductSpecification.minPrice(minP));
        }

        if (maxP != null && !maxP.equals("")) {
            spec = spec.and(ProductSpecification.maxPrice(maxP));
        }

        int totalPages = productRepository.findAll(spec, pageRequest).getTotalPages();
        if (totalPages < 1) totalPages = 1; // если тотал пэйджес будет 0, пойдут баги
        if (currentPage > totalPages) {
            currentPage = totalPages;
            pageRequest = PageRequest.of(currentPage - 1, size.orElse(productsCountPerPage), Sort.by(sortDirection, currentSort)); // поправка текущей страницы в реквесте
        }

        model.addAttribute("productsPage", productRepository.findAll(spec, pageRequest));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        return "products";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product", id));
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
