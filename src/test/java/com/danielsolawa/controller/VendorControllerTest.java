package com.danielsolawa.controller;

import com.danielsolawa.domain.Vendor;
import com.danielsolawa.model.VendorDTO;
import com.danielsolawa.service.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */
public class VendorControllerTest extends AbstractControllerTest{

    public static final String NAME = "Jasmine";
    @InjectMocks
    VendorController vendorController;


    @Mock
    VendorService vendorService;


    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(vendorController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void testGetVendorList() throws Exception {
        List<VendorDTO> vendorDTOs = Arrays.asList(new VendorDTO(), new VendorDTO(), new VendorDTO());

        //given
        when(vendorService.getVendorList()).thenReturn(vendorDTOs);


        //when
        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(3)));

        verify(vendorService, times(1)).getVendorList();
    }

    @Test
    public void testGetVendorById() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + "/1");

        //given
        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);

        //when
        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));

        verify(vendorService, times(1)).getVendorById(1L);


    }

    @Test
    public void testCreateNewVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + "/1");

        //given
        when(vendorService.createNewVendor(any())).thenReturn(vendorDTO);

        //when
        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));

        verify(vendorService, times(1)).createNewVendor(any());
    }

    @Test
    public void testUpdateVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + "/1");

        //given
        when(vendorService.updateVendor(anyLong(), any())).thenReturn(vendorDTO);

        //when
        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));



    }

    @Test
    public void testPatchVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + "/1");

        //given
        when(vendorService.patchVendor(anyLong(), any())).thenReturn(vendorDTO);

        //when
        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));

        verify(vendorService, times(1)).patchVendor(anyLong(), any());

    }

    @Test
    public void testDeleteVendorById() throws Exception {

        mockMvc.perform(delete(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(vendorService, times(1)).deleteVendorById(anyLong());

    }
}