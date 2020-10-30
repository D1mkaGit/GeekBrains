package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.model.Brand;
import ru.geekbrains.repo.BrandRepository;


@Controller
public class BrandController {

    private final static Logger logger = LoggerFactory.getLogger(BrandController.class);

    private final BrandRepository brandRepository;

    @Autowired
    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @GetMapping("/brands")
    public String adminCategoriesPage(Model model) {
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brands", brandRepository.findAll());
        return "brands";
    }

    @GetMapping("/brand/create")
    public String adminBrandCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brand", new Brand());
        return "brand_form";
    }

    @GetMapping("/brand/{id}/edit")
    public String adminEditBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brand", brandRepository.findById(id).orElseThrow(NotFoundException::new));
        return "brand_form";
    }

    @DeleteMapping("/brand/{id}/delete")
    public String adminDeleteBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Brands");
        brandRepository.deleteById(id);
        return "redirect:/brands";
    }

    @PostMapping("/brand")
    public String adminUpsertBrand(Model model, RedirectAttributes redirectAttributes, Brand brand) {
        model.addAttribute("activePage", "Brands");

        try {
            brandRepository.save(brand);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating brand", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (brand.getId() == null) {
                return "redirect:/brand/create";
            }
            return "redirect:/brand/" + brand.getId() + "/edit";
        }
        return "redirect:/brands";
    }

}
