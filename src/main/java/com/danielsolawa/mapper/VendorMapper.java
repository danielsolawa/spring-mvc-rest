package com.danielsolawa.mapper;

import com.danielsolawa.domain.Vendor;
import com.danielsolawa.model.VendorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
    VendorDTO vendorToVendorDTO(Vendor vendor);
}
