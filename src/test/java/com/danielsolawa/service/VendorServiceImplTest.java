package com.danielsolawa.service;

import com.danielsolawa.domain.Vendor;
import com.danielsolawa.exception.ResourceNotFoundException;
import com.danielsolawa.mapper.VendorMapper;
import com.danielsolawa.model.VendorDTO;
import com.danielsolawa.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */
public class VendorServiceImplTest {

    public static final long ID = 1L;
    public static final String NAME = "Joe";
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    VendorMapper vendorMapper;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorMapper = VendorMapper.INSTANCE;
        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);
    }

    @Test
    public void testGetVendorList() throws Exception {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        //given
        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorDTO> vendorDTOs = vendorService.getVendorList();

        //then
        verify(vendorRepository, times(1)).findAll();

        assertThat(vendorDTOs, hasSize(3));
    }



    @Test
    public void testGetVendorByIdHappyPath() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        Optional<Vendor> vendorOptional = Optional.of(vendor);

        //given
        when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        //then
        verify(vendorRepository, times(1)).findById(anyLong());

        assertThat(vendor.getId(), equalTo(vendorDTO.getId()));
        assertThat(vendor.getName(), equalTo(vendorDTO.getName()));
        assertNotNull(vendorDTO.getVendorUrl());


    }


    @Test(expected = ResourceNotFoundException.class)
    public void testGetVendorByIdFailure() throws Exception {

        //given
        when(vendorRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        //then
        verify(vendorRepository, times(1)).findById(anyLong());




    }

    @Test
    public void testCreateNewVendor() throws Exception {

        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        //given
        when(vendorRepository.save(any())).thenReturn(vendor);

        //when
        VendorDTO vendorDTO = vendorService.createNewVendor(new VendorDTO());

        //then
        verify(vendorRepository, times(1)).save(any());

        assertThat(vendor.getId(), equalTo(vendorDTO.getId()));
        assertThat(vendor.getName(), equalTo(vendorDTO.getName()));


    }

    @Test
    public void testUpdateVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        //given
        when(vendorRepository.save(any())).thenReturn(vendor);

        //when
        VendorDTO vendorDTO = vendorService.updateVendor(ID ,new VendorDTO());

        //then
        verify(vendorRepository, times(1)).save(any());

        assertThat(vendor.getId(), equalTo(vendorDTO.getId()));
        assertThat(vendor.getName(), equalTo(vendorDTO.getName()));

    }

    @Test
    public void testPatchVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        Optional<Vendor> vendorOptional = Optional.of(vendor);

        //given
        when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);
        when(vendorRepository.save(any())).thenReturn(vendor);

        //when
        VendorDTO vendorDTO = vendorService.patchVendor(ID ,new VendorDTO());

        //then
        verify(vendorRepository, times(1)).findById(anyLong());
        verify(vendorRepository, times(1)).save(any());

        assertThat(vendor.getId(), equalTo(vendorDTO.getId()));
        assertThat(vendor.getName(), equalTo(vendorDTO.getName()));
        assertNotNull(vendorDTO.getVendorUrl());
    }

    @Test
    public void testDeleteVendorById() throws Exception {

        vendorService.deleteVendorById(ID);

        verify(vendorRepository, times(1)).deleteById(anyLong());

    }
}