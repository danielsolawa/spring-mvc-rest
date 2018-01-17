package com.danielsolawa.service;

import com.danielsolawa.domain.Category;
import com.danielsolawa.model.CategoryDTO;
import com.danielsolawa.model.CustomerDTO;

import java.util.List;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getById(Long id);
    CategoryDTO createNewCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCustomer(Long id, CategoryDTO categoryDTO);
}
