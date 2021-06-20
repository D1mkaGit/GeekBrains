package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        logger.info("List page requested");

        model.addAttribute("students", studentRepository.findAll());
        return "student";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("student", studentRepository.findById(id));
        return "student_form";
    }

    @PostMapping("/update")
    public String update(@Valid Student student, BindingResult result) {
        logger.info("Update endpoint requested");

        if (result.hasErrors()) {
            return "student_form";
        }

        if (student.getId() != null) {
            logger.info("Updating student with id {}", student.getId());
            studentRepository.update(student);
        } else {
            logger.info("Creating new student");
            studentRepository.insert(student);
        }
        return "redirect:/student";
    }

    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Create new student request");

        model.addAttribute("student", new Student());
        return "student_form";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("User delete request");

        studentRepository.delete(id);
        return "redirect:/student";
    }
}
