package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.ArrayUtils;
import ru.geekbrains.model.Brand;
import ru.geekbrains.repo.BrandRepository;
import ru.geekbrains.repo.BrandSpecification;

import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final static Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandRepository brandRepository;


    @GetMapping
    public String allBrands(Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size,
                              @RequestParam("sort") Optional<String> sort,
                              @RequestParam(value = "desc", required = false, defaultValue = "false") boolean desc
    ) {
        String currentSort = sort.orElse("id");
        String[] sortItems = {"id", "name"};

        if (!ArrayUtils.contains(sortItems, currentSort)) currentSort = "id";

        int brandsCountPerPage = 5;
        int currentPage = page.orElse(1);

        Specification<Brand> spec = BrandSpecification.trueLiteral();

        Sort.Direction sortDirection = ASC;
        if (desc) sortDirection = DESC;

        PageRequest pageRequest = PageRequest.of(currentPage - 1, size.orElse(brandsCountPerPage), Sort.by(sortDirection, currentSort));


        int totalPages = brandRepository.findAll(spec, pageRequest).getTotalPages();
        if (totalPages < 1) totalPages = 1; // если тотал пэйджес будет 0, пойдут баги
        if (currentPage > totalPages) {
            currentPage = totalPages;
            pageRequest = PageRequest.of(currentPage - 1, size.orElse(brandsCountPerPage), Sort.by(sortDirection, currentSort)); // поправка текущей страницы в реквесте
        }

        model.addAttribute("brandsPage", brandRepository.findAll(spec, pageRequest));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        return "brands";
    }

}
