package ro.cybersec.web.rest;

import ro.cybersec.service.WarehouseService;
import ro.cybersec.web.rest.errors.BadRequestAlertException;
import ro.cybersec.service.dto.WarehouseDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ro.cybersec.domain.Warehouse}.
 */
@RestController
@RequestMapping("/api")
public class WarehouseResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseResource.class);

    private static final String ENTITY_NAME = "warehouse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WarehouseService warehouseService;

    public WarehouseResource(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    /**
     * {@code POST  /warehouses} : Create a new warehouse.
     *
     * @param warehouseDTO the warehouseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new warehouseDTO, or with status {@code 400 (Bad Request)} if the warehouse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/warehouses")
    public ResponseEntity<WarehouseDTO> createWarehouse(@RequestBody WarehouseDTO warehouseDTO) throws URISyntaxException {
        log.debug("REST request to save Warehouse : {}", warehouseDTO);
        if (warehouseDTO.getId() != null) {
            throw new BadRequestAlertException("A new warehouse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WarehouseDTO result = warehouseService.save(warehouseDTO);
        return ResponseEntity.created(new URI("/api/warehouses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /warehouses} : Updates an existing warehouse.
     *
     * @param warehouseDTO the warehouseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated warehouseDTO,
     * or with status {@code 400 (Bad Request)} if the warehouseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the warehouseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/warehouses")
    public ResponseEntity<WarehouseDTO> updateWarehouse(@RequestBody WarehouseDTO warehouseDTO) throws URISyntaxException {
        log.debug("REST request to update Warehouse : {}", warehouseDTO);
        if (warehouseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WarehouseDTO result = warehouseService.save(warehouseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, warehouseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /warehouses} : get all the warehouses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of warehouses in body.
     */
    @GetMapping("/warehouses")
    public ResponseEntity<List<WarehouseDTO>> getAllWarehouses(Pageable pageable) {
        log.debug("REST request to get a page of Warehouses");
        Page<WarehouseDTO> page = warehouseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /warehouses/:id} : get the "id" warehouse.
     *
     * @param id the id of the warehouseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the warehouseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/warehouses/{id}")
    public ResponseEntity<WarehouseDTO> getWarehouse(@PathVariable Long id) {
        log.debug("REST request to get Warehouse : {}", id);
        Optional<WarehouseDTO> warehouseDTO = warehouseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(warehouseDTO);
    }

    /**
     * {@code DELETE  /warehouses/:id} : delete the "id" warehouse.
     *
     * @param id the id of the warehouseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/warehouses/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        log.debug("REST request to delete Warehouse : {}", id);
        warehouseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
