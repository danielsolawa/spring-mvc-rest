package com.danielsolawa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */
@Data
public class VendorDTO {

    @ApiModelProperty(value = "an id for the vendor model", required = false)
    private Long id;
    @ApiModelProperty(value = "a name for the vendor", required = true)
    private String name;

    @ApiModelProperty(value = "a url for the vendor", required = false)
    @JsonProperty("vendor_url")
    private String vendorUrl;
}
