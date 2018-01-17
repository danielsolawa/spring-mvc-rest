package com.danielsolawa.mapper;

import com.danielsolawa.domain.Category;
import com.danielsolawa.model.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);
    CategoryDTO categoryToCategoryDTO(Category category);
}
