package com.danielsolawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */

@Data
@AllArgsConstructor
public class VendorListDTO {

    private List<VendorDTO> vendors;
}
