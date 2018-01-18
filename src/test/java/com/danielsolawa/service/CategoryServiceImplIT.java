package com.danielsolawa.service;

import com.danielsolawa.bootstrap.Bootstrap;
import com.danielsolawa.domain.Category;
import com.danielsolawa.mapper.CategoryMapper;
import com.danielsolawa.repository.CategoryRepository;
import com.danielsolawa.repository.CustomerRepository;
import com.danielsolawa.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryServiceImplIT {

    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VendorRepository vendorRepository;

    Bootstrap bootstrap;

    @Before
    public void setUp() throws Exception {
        //init data for testing
        bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);

        bootstrap.run();

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);

    }

    @Test
    public void testUpdateCategory() throws Exception {
        Long id = getId();

        Category category = categoryRepository.getOne(id);

        assertNotNull(category);

        //if not null update
        String originalName = category.getName();

        category.setName("Movies");

        Category savedCategory = categoryRepository.save(category);

        Category returnedCategory = categoryRepository.getOne(savedCategory.getId());

        assertNotNull(returnedCategory);
        assertThat(originalName, not(equalTo(returnedCategory.getName())));


    }

    @Test
    public void testCreateCategory() throws Exception {

        //save new category
        Category category = new Category();
        category.setName("Books");

        Category savedCategory = categoryRepository.save(category);

        assertNotNull(savedCategory);
        assertThat(savedCategory.getName(), equalTo(category.getName()));

    }


    private Long getId(){
        return categoryRepository.findAll().get(0).getId();
    }
}
