package com.danielsolawa.service;

import com.danielsolawa.domain.Category;
import com.danielsolawa.mapper.CategoryMapper;
import com.danielsolawa.model.CategoryDTO;
import com.danielsolawa.model.CustomerDTO;
import com.danielsolawa.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
public class CategoryServiceImplTest {

    public static final long ID = 2L;
    public static final String NAME = "Cars";
    CategoryServiceImpl service;

    CategoryMapper categoryMapper;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryMapper = CategoryMapper.INSTANCE;
        service = new CategoryServiceImpl(categoryMapper, categoryRepository);
    }

    @Test
    public void testGetAllCategories() throws Exception {
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        //given
        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryDTOs = service.getAllCategories();

        //then
        verify(categoryRepository, times(1)).findAll();

        assertThat(categories, hasSize(3));

    }

    @Test
    public void testGetById() throws Exception {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);


        //given
        when(categoryRepository.getOne(anyLong())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = service.getById(ID);


        //then
        verify(categoryRepository, times(1)).getOne(anyLong());

        assertThat(category.getId(), equalTo(categoryDTO.getId()));
        assertThat(category.getName(), equalTo(categoryDTO.getName()));

    }

    @Test
    public void testCreateNewCustomer() throws Exception {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        //given
        when(categoryRepository.save(any())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = service.createNewCategory(new CategoryDTO());

        //then
        verify(categoryRepository, times(1)).save(any());

        assertThat(category.getId(), equalTo(categoryDTO.getId()));
        assertThat(category.getName(), equalTo(categoryDTO.getName()));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        //given
        when(categoryRepository.save(any())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = service.updateCustomer(ID, new CategoryDTO());

        //then
        verify(categoryRepository, times(1)).save(any());

        assertThat(category.getId(), equalTo(categoryDTO.getId()));
        assertThat(category.getName(), equalTo(categoryDTO.getName()));

    }
}