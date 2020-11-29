package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.exceptions.NotFoundException;
import ru.geekbrains.persist.repo.BrandRepository;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.security.UserAuthService;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@ComponentScan(basePackages = "ru.geekbrains.chat")
public class MainSiteController {

    private final ProductService productService;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final UserAuthService userAuthService;
    private final CartService cartService;

    @Autowired
    public MainSiteController(ProductService productService,
                              CategoryRepository categoryRepository,
                              BrandRepository brandRepository,
                              UserService userService,
                              UserAuthService userAuthService, CartService cartService) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.userService = userService;
        this.userAuthService = userAuthService;
        this.cartService = cartService;
    }

    @RequestMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("activePage", "Home");
        model.addAttribute("products", productService.findAll());
        addDefaultAttributes(model);
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("activePage", "Account");
        addDefaultAttributes(model);
        return "login";
    }

    @RequestMapping("/category/{id}")
    public String byCategoryPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("activePage", "byCategory");
        addDefaultAttributes(model);
        model.addAttribute("category", categoryRepository.findById(id).orElseThrow(IllegalStateException::new));
        model.addAttribute("products", productService.findByCategoryId(id));
        return "by-category";
    }

    @GetMapping("/brand/{id}")
    public String byBrandPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "byBrand");
        addDefaultAttributes(model);
        model.addAttribute("brand", brandRepository.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("products", productService.findByBrandId(id));
        return "by-brand";
    }

    @RequestMapping("/product/{id}")
    public String productPage(@PathVariable("id") Long id, Model model) {
        addDefaultAttributes(model);
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(NotFoundException::new));
        return "single-product";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("activePage", "Account");
        addDefaultAttributes(model);
        model.addAttribute("user", new UserRepr());
        return "register";
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model, HttpServletRequest request) {
        model.addAttribute("activePage", "Account");
        addDefaultAttributes(model);
        if (request.getUserPrincipal() == null) return "redirect:/";
        model.addAttribute("user", userService.findOneByUsername(request.getUserPrincipal().getName())
                .orElseThrow(NotFoundException::new)
        );
        return "profile";
    }

    @PostMapping("/regguestuser")
    public String mainSiteUpsertUser(@Valid UserRepr user, Model model, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            if (request.getUserPrincipal() == null)
                return "register"; // возваращаем на регу при ошибке валидации при регистрации
            else {
                model.addAttribute("user", userService.findOneByUsername(request.getUserPrincipal().getName())
                        .orElseThrow(NotFoundException::new));
                return "profile"; // возвращаем в профиль при ошибке валидации при редактировании
            }
        }

        if (request.getUserPrincipal() == null) {// если нието не залогинен, логинем
            userService.saveWithRoleLike(user, "GUEST");
            userAuthService.authWithHttpServletRequest(request, user.getUsername(), user.getPassword());
        } else {
            userService.save(user);
        }
        return "redirect:/profile";
    }

    private Model addDefaultAttributes(Model model) {
        List<Long> catIds = productService.findDistinctCategoryId();
        List<Long> brandIds = productService.findDistinctBrandId();
        model.addAttribute("brands", brandRepository.findByIdIn(brandIds));
        model.addAttribute("categories", categoryRepository.findByIdIn(catIds));
        model.addAttribute("lineItems", cartService.getLineItems());
        return model;
    }
}
