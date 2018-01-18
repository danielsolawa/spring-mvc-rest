package com.danielsolawa.mapper;

import com.danielsolawa.domain.Vendor;
import com.danielsolawa.model.VendorDTO;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */
public class VendorMapperTest {

    public static final long ID = 1L;
    public static final String NAME = "Richard";
    VendorMapper vendorMapper;

    @Before
    public void setUp() throws Exception {
        vendorMapper = VendorMapper.INSTANCE;
    }

    @Test
    public void testVendorDTOToVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertThat(vendorDTO.getId(), equalTo(vendor.getId()));
        assertThat(vendorDTO.getName(), equalTo(vendor.getName()));


    }

    @Test
    public void testVendorToVendorDTO() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertThat(vendor.getId(), equalTo(vendorDTO.getId()));
        assertThat(vendor.getName(), equalTo(vendorDTO.getName()));
    }
}