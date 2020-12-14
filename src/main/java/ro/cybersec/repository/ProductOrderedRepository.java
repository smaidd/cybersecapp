package ro.cybersec.repository;

import ro.cybersec.domain.ProductOrdered;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProductOrdered entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductOrderedRepository extends JpaRepository<ProductOrdered, Long> {
}
