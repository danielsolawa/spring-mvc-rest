package com.danielsolawa.service;

import com.danielsolawa.model.CustomerDTO;

import java.util.List;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
    CustomerDTO updateCustomerPatch(Long id, CustomerDTO customerDTO);
}
