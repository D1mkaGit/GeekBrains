package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persist.repo.BrandRepository;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.model.LineItem;

import java.util.List;

@RequestMapping("/cart")
@Controller
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public CartController(CartService cartService,
                          ProductService productService,
                          CategoryRepository categoryRepository,
                          BrandRepository brandRepository){
        this.cartService = cartService;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public String cartPage(Model model) {
        List<Long> catIds = productService.findDistinctCategoryId();
        List<Long> brandIds = productService.findDistinctBrandId();
        model.addAttribute("brands", brandRepository.findByIdIn(brandIds));
        model.addAttribute("categories", categoryRepository.findByIdIn(catIds));
        model.addAttribute("activePage", "Cart");

        model.addAttribute("lineItems", cartService.getLineItems());
        model.addAttribute("subTotal", cartService.getSubTotal());
        return "cart";
    }

    @PostMapping
    public String updateCart(LineItem lineItem) {
        lineItem.setProductRepr(productService.findById(lineItem.getProductId())
                .orElseThrow(IllegalArgumentException::new));
        cartService.updateCart(lineItem);
        return "redirect:/cart";
    }

    @DeleteMapping
    public String deleteLineItem(LineItem lineItem) {
        cartService.removeProduct(lineItem);
        return "redirect:/cart";
    }
}
