package com.danielsolawa.service;

import com.danielsolawa.bootstrap.Bootstrap;
import com.danielsolawa.domain.Customer;
import com.danielsolawa.mapper.CustomerMapper;
import com.danielsolawa.repository.CategoryRepository;
import com.danielsolawa.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    public static final String UPDATE_FIRSTNAME = "Selena";
    public static final String UPDATE_LASTNAME = "Gomez";
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading data for customers...");
        System.out.println("Size " + customerRepository.findAll().size());

        //prepare data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }



    @Test
    public void testCreate() throws Exception {
        Customer customer = new Customer();
        customer.setFirstname("Gal");
        customer.setLastname("Gadot");

        //saving new customer
        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer);
        String firstname = savedCustomer.getFirstname();
        String lastname = savedCustomer.getLastname();

        //finding customer by id
        Customer returnedCustomer = customerRepository.getOne(savedCustomer.getId());

        assertNotNull(returnedCustomer);
        assertThat(firstname, equalTo(returnedCustomer.getFirstname()));
        assertThat(lastname, equalTo(returnedCustomer.getLastname()));

    }


    @Test
    public void testUpdate() throws Exception {
        Long id = getId();

        //find customer by id
        Customer customer = customerRepository.getOne(id);

        assertNotNull(customer);

        String originalFirstName = customer.getFirstname();
        String originalLastName = customer.getLastname();

        //if not null update
        customer.setFirstname(UPDATE_FIRSTNAME);
        customer.setLastname(UPDATE_LASTNAME);

        customerRepository.save(customer);

        Customer updatedCustomer = customerRepository.getOne(id);

        assertNotNull(updatedCustomer);
        assertEquals(id, updatedCustomer.getId());
        assertNotEquals(originalFirstName, updatedCustomer.getFirstname());
        assertNotEquals(originalLastName, updatedCustomer.getLastname());


    }



    private Long getId(){
        return customerRepository.findAll().get(0).getId();
    }
}