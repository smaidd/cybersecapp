package ro.cybersec.service;

import ro.cybersec.service.dto.ProductOrderedDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ro.cybersec.domain.ProductOrdered}.
 */
public interface ProductOrderedService {

    /**
     * Save a productOrdered.
     *
     * @param productOrderedDTO the entity to save.
     * @return the persisted entity.
     */
    ProductOrderedDTO save(ProductOrderedDTO productOrderedDTO);

    /**
     * Get all the productOrdereds.
     *
     * @return the list of entities.
     */
    List<ProductOrderedDTO> findAll();


    /**
     * Get the "id" productOrdered.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductOrderedDTO> findOne(Long id);

    /**
     * Delete the "id" productOrdered.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
