package com.exxeta.expensemanagerms.controller.expenseController;

import com.exxeta.expenseservice.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping(path = "/categories")
public class CategoryController {

    private final ObjectMapper mapper = new ObjectMapper();

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllCategories(@RequestParam String userId) {
        try {
            return mapper.writeValueAsString(categoryService.getAllCategories(userId));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
