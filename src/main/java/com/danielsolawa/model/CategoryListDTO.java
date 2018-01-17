package com.danielsolawa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
@Data
@AllArgsConstructor
public class CategoryListDTO {

    @JsonProperty("categories")
    List<CategoryDTO> categories;
}
