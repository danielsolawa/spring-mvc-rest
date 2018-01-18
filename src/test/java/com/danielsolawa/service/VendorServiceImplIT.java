package com.danielsolawa.service;

import com.danielsolawa.bootstrap.Bootstrap;
import com.danielsolawa.domain.Vendor;
import com.danielsolawa.repository.CategoryRepository;
import com.danielsolawa.repository.CustomerRepository;
import com.danielsolawa.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class VendorServiceImplIT {

    Bootstrap bootstrap;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VendorRepository vendorRepository;


    @Before
    public void setUp() throws Exception {
        //load vendor data
        bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();


    }

    @Test
    public void testUpdate() throws Exception {
        Long id = getId();

        //find by id
        Optional<Vendor> vendorOptional =  vendorRepository.findById(id);

        assertTrue(vendorOptional.isPresent());

        Vendor vendor = vendorOptional.get();
        String originalName = vendor.getName();

        //update name
        vendor.setName("Hannah");
        vendorRepository.save(vendor);

        //find updated vendor
        Optional<Vendor> updatedVendor =  vendorRepository.findById(id);

        assertTrue(updatedVendor.isPresent());
        assertThat(originalName, not(equalTo(updatedVendor.get().getName())));
        assertThat(updatedVendor.get().getName(), equalTo("Hannah"));


    }

    @Test
    public void testCreate() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setName("Erica");

        //save new vendor
        Vendor savedVendor = vendorRepository.save(vendor);

        assertNotNull(savedVendor);
        Long id = savedVendor.getId();

        //find new vendor by id
        Optional<Vendor> vendorOptional = vendorRepository.findById(id);

        assertTrue(vendorOptional.isPresent());
        assertThat("Erica", equalTo(vendorOptional.get().getName()));


    }

    @Test
    public void testDelete() throws Exception {
        Long id = getId();

        //delete by id
        vendorRepository.deleteById(id);

        //try to find deleted id
        Optional<Vendor> vendorOptional = vendorRepository.findById(id);

        assertTrue(!vendorOptional.isPresent());

    }

    private Long getId(){
        return vendorRepository.findAll().get(0).getId();

    }
}
