package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.exceptions.NotFoundException;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.repo.BrandRepository;


@Controller
public class BrandController {

    private final static Logger logger = LoggerFactory.getLogger(BrandController.class);

    private final BrandRepository brandRepository;

    @Autowired
    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @GetMapping("/admin/brands")
    public String adminCategoriesPage(Model model) {
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brands", brandRepository.findAll());
        return "brands";
    }

    @GetMapping("/admin/brand/create")
    public String adminBrandCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brand", new Brand());
        return "brand_form";
    }

    @GetMapping("/admin/brand/{id}/edit")
    public String adminEditBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brand", brandRepository.findById(id).orElseThrow(NotFoundException::new));
        return "brand_form";
    }

    @DeleteMapping("/admin/brand/{id}/delete")
    public String adminDeleteBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Brands");
        brandRepository.deleteById(id);
        return "redirect:/admin/brands";
    }

    @PostMapping("/admin/brand")
    public String adminUpsertBrand(Model model, RedirectAttributes redirectAttributes, Brand brand) {
        model.addAttribute("activePage", "Brands");

        try {
            brandRepository.save(brand);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating brand", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (brand.getId() == null) {
                return "redirect:/admin/brand/create";
            }
            return "redirect:/admin/brand/" + brand.getId() + "/edit";
        }
        return "redirect:/admin/brands";
    }

}
