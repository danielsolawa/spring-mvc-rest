package com.danielsolawa.bootstrap;

import com.danielsolawa.domain.Category;
import com.danielsolawa.domain.Customer;
import com.danielsolawa.repository.CategoryRepository;
import com.danielsolawa.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Daniel Solawa on 2018-01-17.
 */
@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstname("Taylor");
        customer1.setLastname("Hill");

        Customer customer2 = new Customer();
        customer2.setFirstname("Jessica");
        customer2.setLastname("Alba");

        Customer customer3 = new Customer();
        customer3.setFirstname("Jasmine");
        customer3.setLastname("Tooks");



        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
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
