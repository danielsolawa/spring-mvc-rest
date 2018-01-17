package com.danielsolawa.bootstrap;

import com.danielsolawa.domain.Category;
import com.danielsolawa.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
@Component
@Slf4j
public class Boostrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public Boostrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        loadCategories();
    }

    private void loadCategories() {
        Category category1 = new Category();
        category1.setName("Cars");

        Category category2 = new Category();
        category2.setName("Sports");

        Category category3 = new Category();
        category3.setName("Travels");

        Category category4 = new Category();
        category4.setName("Music");


        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);

        log.info("Categories loaded "  + categoryRepository.count());
    }
}
