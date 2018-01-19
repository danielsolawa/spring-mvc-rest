package com.danielsolawa.controller;

import com.danielsolawa.model.CategoryDTO;
import com.danielsolawa.model.CategoryListDTO;
import com.danielsolawa.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Daniel Solawa on 2018-01-17.
 */
@Api(description = "This is a controller for the Category model")
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Returns a list of all categories", notes = "Additional info about controller")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories(){
        return new CategoryListDTO(categoryService.getAllCategories());
    }


    @ApiOperation(value = "Returns a category with the given id")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getById(@PathVariable String id){
        return categoryService.getById(Long.valueOf(id));
    }

    @ApiOperation(value = "Creates a new category")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createNewCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createNewCategory(categoryDTO);
    }

    @ApiOperation(value = "Updates a given category")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO updateCategory(@PathVariable String id, @RequestBody CategoryDTO categoryDTO){

        return categoryService.updateCustomer(Long.valueOf(id), categoryDTO);
    }
}
