package com.danielsolawa.mapper;

import com.danielsolawa.domain.Category;
import com.danielsolawa.model.CategoryDTO;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
public class CategoryMapperTest {

    public static final String NAME = "Erica";
    public static final long ID = 1L;
    CategoryMapper categoryMapper;

    @Before
    public void setUp() throws Exception {
        categoryMapper = CategoryMapper.INSTANCE;
    }

    @Test
    public void testCategoryDTOToCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID);
        categoryDTO.setName(NAME);

        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);

        assertThat(categoryDTO.getId(), equalTo(category.getId()));
        assertThat(categoryDTO.getName(), equalTo(category.getName()));


    }

    @Test
    public void testCategoryToCategoryDTO() throws Exception {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);


        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertThat(category.getId(), equalTo(categoryDTO.getId()));
        assertThat(category.getName(), equalTo(categoryDTO.getName()));
    }
}