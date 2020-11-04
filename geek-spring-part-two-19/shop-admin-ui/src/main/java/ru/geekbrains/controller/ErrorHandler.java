package ru.geekbrains.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.exceptions.NotFoundException;
import ru.geekbrains.exceptions.ServerInternalException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler
    public ModelAndView internalServerExceptionHandler(ServerInternalException ex) {
        ModelAndView modelAndView = new ModelAndView("internal_server_error");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
