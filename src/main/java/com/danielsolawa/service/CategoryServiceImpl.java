package com.danielsolawa.service;

import com.danielsolawa.domain.Category;
import com.danielsolawa.mapper.CategoryMapper;
import com.danielsolawa.model.CategoryDTO;
import com.danielsolawa.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getById(Long id) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.getOne(id));
    }

    @Override
    public CategoryDTO createNewCategory(CategoryDTO categoryDTO) {
        return saveCategoryDTO(categoryDTO);
    }

    @Override
    public CategoryDTO updateCustomer(Long id, CategoryDTO categoryDTO) {
        categoryDTO.setId(id);
        return saveCategoryDTO(categoryDTO);
    }


    private CategoryDTO saveCategoryDTO(CategoryDTO categoryDTO){
        Category categoryToSave = categoryMapper.categoryDTOToCategory(categoryDTO);
        Category savedCategory = categoryRepository.save(categoryToSave);

        return categoryMapper.categoryToCategoryDTO(savedCategory);
    }
}
