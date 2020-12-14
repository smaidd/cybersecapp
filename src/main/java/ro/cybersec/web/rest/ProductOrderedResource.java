package ro.cybersec.web.rest;

import ro.cybersec.service.ProductOrderedService;
import ro.cybersec.web.rest.errors.BadRequestAlertException;
import ro.cybersec.service.dto.ProductOrderedDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ro.cybersec.domain.ProductOrdered}.
 */
@RestController
@RequestMapping("/api")
public class ProductOrderedResource {

    private final Logger log = LoggerFactory.getLogger(ProductOrderedResource.class);

    private static final String ENTITY_NAME = "productOrdered";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductOrderedService productOrderedService;

    public ProductOrderedResource(ProductOrderedService productOrderedService) {
        this.productOrderedService = productOrderedService;
    }

    /**
     * {@code POST  /product-ordereds} : Create a new productOrdered.
     *
     * @param productOrderedDTO the productOrderedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productOrderedDTO, or with status {@code 400 (Bad Request)} if the productOrdered has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-ordereds")
    public ResponseEntity<ProductOrderedDTO> createProductOrdered(@RequestBody ProductOrderedDTO productOrderedDTO) throws URISyntaxException {
        log.debug("REST request to save ProductOrdered : {}", productOrderedDTO);
        if (productOrderedDTO.getId() != null) {
            throw new BadRequestAlertException("A new productOrdered cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductOrderedDTO result = productOrderedService.save(productOrderedDTO);
        return ResponseEntity.created(new URI("/api/product-ordereds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-ordereds} : Updates an existing productOrdered.
     *
     * @param productOrderedDTO the productOrderedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productOrderedDTO,
     * or with status {@code 400 (Bad Request)} if the productOrderedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productOrderedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-ordereds")
    public ResponseEntity<ProductOrderedDTO> updateProductOrdered(@RequestBody ProductOrderedDTO productOrderedDTO) throws URISyntaxException {
        log.debug("REST request to update ProductOrdered : {}", productOrderedDTO);
        if (productOrderedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductOrderedDTO result = productOrderedService.save(productOrderedDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, productOrderedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-ordereds} : get all the productOrdereds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productOrdereds in body.
     */
    @GetMapping("/product-ordereds")
    public List<ProductOrderedDTO> getAllProductOrdereds() {
        log.debug("REST request to get all ProductOrdereds");
        return productOrderedService.findAll();
    }

    /**
     * {@code GET  /product-ordereds/:id} : get the "id" productOrdered.
     *
     * @param id the id of the productOrderedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productOrderedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-ordereds/{id}")
    public ResponseEntity<ProductOrderedDTO> getProductOrdered(@PathVariable Long id) {
        log.debug("REST request to get ProductOrdered : {}", id);
        Optional<ProductOrderedDTO> productOrderedDTO = productOrderedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productOrderedDTO);
    }

    /**
     * {@code DELETE  /product-ordereds/:id} : delete the "id" productOrdered.
     *
     * @param id the id of the productOrderedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-ordereds/{id}")
    public ResponseEntity<Void> deleteProductOrdered(@PathVariable Long id) {
        log.debug("REST request to delete ProductOrdered : {}", id);
        productOrderedService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
