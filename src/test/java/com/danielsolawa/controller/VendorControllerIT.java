package com.danielsolawa.controller;

import com.danielsolawa.exception.ResourceNotFoundException;
import com.danielsolawa.model.VendorDTO;
import com.danielsolawa.service.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Daniel Solawa on 2018-01-19.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VendorController.class)
public class VendorControllerIT extends AbstractControllerTest{

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VendorService vendorService;

    VendorDTO vendorDTO1;
    VendorDTO vendorDTO2;

    @Before
    public void setUp() throws Exception {
        vendorDTO1 = new VendorDTO();
        vendorDTO1.setId(1L);
        vendorDTO1.setName("Thomas");
        vendorDTO1.setVendorUrl(VendorController.BASE_URL + "/1");

        vendorDTO2 = new VendorDTO();
        vendorDTO2.setId(2L);
        vendorDTO2.setName("Samantha");
        vendorDTO2.setVendorUrl(VendorController.BASE_URL + "/2");

    }

    @Test
    public void testGetVendorList() throws Exception {

        List<VendorDTO> vendorDTOs = Arrays.asList(vendorDTO1, vendorDTO2);

        given(vendorService.getVendorList()).willReturn(vendorDTOs);

        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));

        then(vendorService).should().getVendorList();

    }

    @Test
    public void testGetVendorByIdHappyPath() throws Exception {

        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO1);

        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())))
                .andExpect(jsonPath("$.vendor_url", equalTo(vendorDTO1.getVendorUrl())));

        then(vendorService).should().getVendorById(anyLong());

    }

    @Test
    public void testGetVendorByIdFailure() throws Exception {

        given(vendorService.getVendorById(anyLong())).willThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


        then(vendorService).should().getVendorById(anyLong());

    }

    @Test
    public void testCreateNewVendor() throws Exception {

        given(vendorService.createNewVendor(any(VendorDTO.class))).willReturn(vendorDTO2);

        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(vendorDTO2)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.name", equalTo(vendorDTO2.getName())))
                .andExpect(jsonPath("$.vendor_url", equalTo(vendorDTO2.getVendorUrl())));

        then(vendorService).should().createNewVendor(any(VendorDTO.class));

    }

    @Test
    public void testUpdateVendor() throws Exception {
        given(vendorService.updateVendor(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO1);

        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(vendorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())))
                .andExpect(jsonPath("$.vendor_url", equalTo(vendorDTO1.getVendorUrl())));

        then(vendorService).should().updateVendor(anyLong(), any(VendorDTO.class));

    }

    @Test
    public void testPatchVendor() throws Exception {
        given(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO1);

        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(vendorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())))
                .andExpect(jsonPath("$.vendor_url", equalTo(vendorDTO1.getVendorUrl())));

        then(vendorService).should().patchVendor(anyLong(), any(VendorDTO.class));
    }

    @Test
    public void testDeleteVendorById() throws Exception {

        mockMvc.perform(delete(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        then(vendorService).should().deleteVendorById(anyLong());

    }
}
