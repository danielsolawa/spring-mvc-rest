package com.danielsolawa.controller;

import com.danielsolawa.model.CategoryDTO;
import com.danielsolawa.model.CategoryListDTO;
import com.danielsolawa.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Daniel Solawa on 2018-01-17.
 */

@Controller
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){
        return new ResponseEntity<CategoryListDTO>(
                new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable String id){
        return new ResponseEntity<CategoryDTO>(categoryService.getById(Long.valueOf(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createNewCategory(@RequestBody CategoryDTO categoryDTO){

        return new ResponseEntity<CategoryDTO>(categoryService.createNewCategory(categoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable String id, @RequestBody CategoryDTO categoryDTO){

        return new ResponseEntity<CategoryDTO>(
                categoryService.updateCustomer(Long.valueOf(id), categoryDTO), HttpStatus.OK);
    }
}
