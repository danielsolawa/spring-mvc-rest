package com.danielsolawa.service;

import com.danielsolawa.domain.Customer;
import com.danielsolawa.mapper.CustomerMapper;
import com.danielsolawa.model.CustomerDTO;
import com.danielsolawa.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
public class CustomerServiceImplTest {

    public static final long ID = 1L;
    public static final String FIRSTNAME = "Ariana";
    public static final String LASTNAME = "Grande";
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerMapper = CustomerMapper.INSTANCE;
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());

        //given
        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOs = customerService.getAllCustomers();

        //then
        verify(customerRepository, times(1)).findAll();

        assertThat(customers.size(), equalTo(customerDTOs.size()));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        Optional<Customer> customerOptional = Optional.of(customer);

        //given
        when(customerRepository.findById(anyLong())).thenReturn(customerOptional);

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        //then
        verify(customerRepository, times(1)).findById(anyLong());

        assertNotNull(customerDTO.getCustomerUrl());
        assertThat(customer.getId(), equalTo(customerDTO.getId()));
        assertThat(customer.getFirstname(), equalTo(customerDTO.getFirstname()));
        assertThat(customer.getLastname(), equalTo(customerDTO.getLastname()));


    }

    @Test
    public void testCreateNewCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        //given
        when(customerRepository.save(any())).thenReturn(customer);

        //when
        CustomerDTO customerDTO =
                customerService.createNewCustomer(customerMapper.customerToCustomerDTO(customer));

        //then
        verify(customerRepository, times(1)).save(any());

        assertThat(customer.getId(), equalTo(customerDTO.getId()));
        assertThat(customer.getFirstname(), equalTo(customerDTO.getFirstname()));
        assertThat(customer.getLastname(), equalTo(customerDTO.getLastname()));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        //given
        when(customerRepository.save(any())).thenReturn(customer);

        //when
        CustomerDTO customerDTO =
                customerService.updateCustomer(ID, customerMapper.customerToCustomerDTO(customer));

        //then
        verify(customerRepository, times(1)).save(any());

        assertThat(customer.getId(), equalTo(customerDTO.getId()));
        assertThat(customer.getFirstname(), equalTo(customerDTO.getFirstname()));
        assertThat(customer.getLastname(), equalTo(customerDTO.getLastname()));
    }

    @Test
    public void testUpdateCustomerPatch() throws Exception {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        Optional<Customer> customerOptional = Optional.of(customer);

        //given
        when(customerRepository.findById(anyLong())).thenReturn(customerOptional);
        when(customerRepository.save(any())).thenReturn(customer);


        //when
        CustomerDTO customerDTO = customerService.updateCustomerPatch(ID, new CustomerDTO());

        //then
        verify(customerRepository, times(1)).findById(anyLong());
        verify(customerRepository, times(1)).save(any());

        assertThat(customer.getId(), equalTo(customerDTO.getId()));
        assertThat(customer.getFirstname(), equalTo(customerDTO.getFirstname()));
        assertThat(customer.getLastname(), equalTo(customerDTO.getLastname()));


    }

    @Test(expected = RuntimeException.class)
    public void testUpdateCustomerPatchFailure() throws Exception {

        //given
        when(customerRepository.findById(anyLong())).thenThrow(RuntimeException.class);

        //when
        CustomerDTO customerDTO = customerService.updateCustomerPatch(ID, new CustomerDTO());

        //then
        verify(customerRepository, times(1)).findById(anyLong());
        assertNull(customerDTO);
    }



    @Test
    public void testDeleteById() throws Exception {

        customerService.deleteById(ID);

        verify(customerRepository, times(1)).deleteById(anyLong());

    }
}