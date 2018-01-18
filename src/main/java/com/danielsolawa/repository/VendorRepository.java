package com.danielsolawa.repository;

import com.danielsolawa.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Daniel Solawa on 2018-01-18.
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
