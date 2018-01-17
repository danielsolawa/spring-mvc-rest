package com.danielsolawa.mapper;

import com.danielsolawa.domain.Customer;
import com.danielsolawa.model.CustomerDTO;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
public class CustomerMapperTest {

    public static final long ID = 1L;
    public static final String FIRSTNAME = "Taylor";
    public static final String LASTNAME = "Hill";

    CustomerMapper customerMapper;


    @Before
    public void setUp() throws Exception {
        customerMapper = CustomerMapper.INSTANCE;

    }

    @Test
    public void testCustomerDTOToCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(ID);
        customerDTO.setFirstname(FIRSTNAME);
        customerDTO.setLastname(LASTNAME);


        Customer customer = customerMapper.customerDTOTOCustomer(customerDTO);

        assertThat(customerDTO.getId(), equalTo(customer.getId()));
        assertThat(customerDTO.getFirstname(), equalTo(customer.getFirstname()));
        assertThat(customerDTO.getLastname(), equalTo(customer.getLastname()));
    }

    @Test
    public void testCustomerToCustomerDTO() throws Exception {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);


        assertThat(customer.getId(), equalTo(customerDTO.getId()));
        assertThat(customer.getFirstname(), equalTo(customerDTO.getFirstname()));
        assertThat(customer.getLastname(), equalTo(customerDTO.getLastname()));
    }
}