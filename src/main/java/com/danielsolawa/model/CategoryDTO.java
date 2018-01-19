package com.danielsolawa.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
@Data
public class CategoryDTO {

    @ApiModelProperty(value = "just an id", required = false)
    private Long id;
    @ApiModelProperty(value = "a name for the category", required = true)
    private String name;
}
