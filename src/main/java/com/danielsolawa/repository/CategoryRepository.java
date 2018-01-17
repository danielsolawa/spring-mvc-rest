package com.danielsolawa.repository;

import com.danielsolawa.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
