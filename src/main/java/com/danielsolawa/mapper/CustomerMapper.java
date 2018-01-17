package com.danielsolawa.mapper;

import com.danielsolawa.domain.Customer;
import com.danielsolawa.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer customerDTOToCustomer(Customer customer);
    CustomerDTO customerToCustomerDTO(CustomerDTO customerDTO);
}
