package com.danielsolawa.controller;

import com.danielsolawa.model.CategoryDTO;
import com.danielsolawa.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
public class CategoryControllerTest extends AbstractControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "John";
    public static final String URL_TEMPLATE = "/api/v1/categories";
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testGetAllCategories() throws Exception {
        List<CategoryDTO> categoryDTOs =
                Arrays.asList(new CategoryDTO(), new CategoryDTO(), new CategoryDTO());

        //given
        when(categoryService.getAllCategories()).thenReturn(categoryDTOs);

        //when
        mockMvc.perform(get(URL_TEMPLATE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(3)));

        verify(categoryService, times(1)).getAllCategories();


    }

    @Test
    public void testGetById() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID);
        categoryDTO.setName(NAME);

        //given
        when(categoryService.getById(anyLong())).thenReturn(categoryDTO);

        //when
        mockMvc.perform(get(URL_TEMPLATE + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(NAME)));

        verify(categoryService, times(1)).getById(anyLong());


    }

    @Test
    public void testCreateNewCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID);
        categoryDTO.setName(NAME);

        //given
        when(categoryService.createNewCategory(any())).thenReturn(categoryDTO);

        //when
        mockMvc.perform(post(URL_TEMPLATE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(categoryDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(NAME)));

        verify(categoryService, times(1)).createNewCategory(any());

    }

    @Test
    public void testUpdateCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID);
        categoryDTO.setName(NAME);

        //given
        when(categoryService.updateCustomer(anyLong(),any())).thenReturn(categoryDTO);

        //when
        mockMvc.perform(put(URL_TEMPLATE + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(NAME)));

        verify(categoryService, times(1)).updateCustomer(anyLong(), any());

    }
}