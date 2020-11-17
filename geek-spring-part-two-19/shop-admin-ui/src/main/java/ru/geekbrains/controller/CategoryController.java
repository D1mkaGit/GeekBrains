package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.repo.CategoryRepository;

@Controller
public class CategoryController {

    private final static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping ("/admin/categories")
    public String adminCategoriesPage(Model model) {
        model.addAttribute("activePage", "Categories");
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @GetMapping("/admin/category/create")
    public String adminCategoryCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", new Category());
        return "category_form";
    }

    @GetMapping("/admin/category/{id}/edit")
    public String adminEditCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", categoryRepository.findById(id).orElseThrow(IllegalStateException::new));
        return "category_form";
    }

    @DeleteMapping("/admin/category/{id}/delete")
    public String adminDeleteCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Categories");
        categoryRepository.deleteById(id);
        return "redirect:/admin/categories";
    }

    @PostMapping("/admin/category")
    public String adminUpsertCategory(Model model, RedirectAttributes redirectAttributes, Category category) {
        model.addAttribute("activePage", "Categories");

        try {
            categoryRepository.save(category);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating category", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (category.getId() == null) {
                return "redirect:/admin/category/create";
            }
            return "redirect:/admin/category/" + category.getId() + "/edit";
        }
        return "redirect:/admin/categories";
    }

}
