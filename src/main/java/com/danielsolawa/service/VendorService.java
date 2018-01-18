package com.danielsolawa.service;

import com.danielsolawa.model.VendorDTO;

import java.util.List;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */
public interface VendorService {

    List<VendorDTO> getVendorList();
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO vendorDTO);
    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);
    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
    void deleteVendorById(Long id);
}
