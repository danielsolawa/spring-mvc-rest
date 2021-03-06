package com.danielsolawa.service;

import com.danielsolawa.controller.CustomerController;
import com.danielsolawa.domain.Customer;
import com.danielsolawa.exception.ResourceNotFoundException;
import com.danielsolawa.mapper.CustomerMapper;
import com.danielsolawa.model.CustomerDTO;
import com.danielsolawa.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        /*return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(id));
                    return customerDTO;
                }).orElseThrow(ResourceNotFoundException::new);*/


        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .map(customerDTO -> {
                    customerDTO.setCustomerUrl("/api/v1/customers/" + id);
                    return customerDTO;
                }).orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveCustomerDTO(customerDTO);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        customerDTO.setId(id);

        return saveCustomerDTO(customerDTO);
    }

    @Override
    public CustomerDTO updateCustomerPatch(Long id, CustomerDTO customerDTO) {
            return customerRepository.findById(id)
                .map(customer -> {
                    if(customerDTO.getFirstname() != null){
                        customer.setFirstname(customerDTO.getFirstname());
                    }

                    if(customerDTO.getLastname() != null){
                        customer.setLastname(customerDTO.getLastname());
                    }

                    CustomerDTO returnedCustomerDTO =
                            customerMapper.customerToCustomerDTO(customerRepository.save(customer));

                    returnedCustomerDTO.setCustomerUrl(getCustomerUrl(id));

                    return returnedCustomerDTO;

                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }


    private CustomerDTO saveCustomerDTO(CustomerDTO customerDTO){
        Customer customerToSave = customerMapper.customerDTOTOCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customerToSave);

        return customerMapper.customerToCustomerDTO(savedCustomer);
    }

    private String getCustomerUrl(Long id){
        return CustomerController.BASE_URL + "/" + id;
    }
}
