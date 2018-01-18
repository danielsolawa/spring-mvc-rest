package com.danielsolawa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */
@Data
public class VendorDTO {

    private Long id;
    private String name;
    @JsonProperty("vendor_url")
    private String vendorUrl;
}
