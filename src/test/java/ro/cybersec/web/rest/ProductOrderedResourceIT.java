package ro.cybersec.web.rest;

import ro.cybersec.CybersecurityApp;
import ro.cybersec.domain.ProductOrdered;
import ro.cybersec.repository.ProductOrderedRepository;
import ro.cybersec.service.ProductOrderedService;
import ro.cybersec.service.dto.ProductOrderedDTO;
import ro.cybersec.service.mapper.ProductOrderedMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProductOrderedResource} REST controller.
 */
@SpringBootTest(classes = CybersecurityApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProductOrderedResourceIT {

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final Long DEFAULT_SHIPPING_PRICE = 1L;
    private static final Long UPDATED_SHIPPING_PRICE = 2L;

    @Autowired
    private ProductOrderedRepository productOrderedRepository;

    @Autowired
    private ProductOrderedMapper productOrderedMapper;

    @Autowired
    private ProductOrderedService productOrderedService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductOrderedMockMvc;

    private ProductOrdered productOrdered;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductOrdered createEntity(EntityManager em) {
        ProductOrdered productOrdered = new ProductOrdered()
            .quantity(DEFAULT_QUANTITY)
            .shippingPrice(DEFAULT_SHIPPING_PRICE);
        return productOrdered;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductOrdered createUpdatedEntity(EntityManager em) {
        ProductOrdered productOrdered = new ProductOrdered()
            .quantity(UPDATED_QUANTITY)
            .shippingPrice(UPDATED_SHIPPING_PRICE);
        return productOrdered;
    }

    @BeforeEach
    public void initTest() {
        productOrdered = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductOrdered() throws Exception {
        int databaseSizeBeforeCreate = productOrderedRepository.findAll().size();
        // Create the ProductOrdered
        ProductOrderedDTO productOrderedDTO = productOrderedMapper.toDto(productOrdered);
        restProductOrderedMockMvc.perform(post("/api/product-ordereds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOrderedDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductOrdered in the database
        List<ProductOrdered> productOrderedList = productOrderedRepository.findAll();
        assertThat(productOrderedList).hasSize(databaseSizeBeforeCreate + 1);
        ProductOrdered testProductOrdered = productOrderedList.get(productOrderedList.size() - 1);
        assertThat(testProductOrdered.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testProductOrdered.getShippingPrice()).isEqualTo(DEFAULT_SHIPPING_PRICE);
    }

    @Test
    @Transactional
    public void createProductOrderedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productOrderedRepository.findAll().size();

        // Create the ProductOrdered with an existing ID
        productOrdered.setId(1L);
        ProductOrderedDTO productOrderedDTO = productOrderedMapper.toDto(productOrdered);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductOrderedMockMvc.perform(post("/api/product-ordereds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOrderedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductOrdered in the database
        List<ProductOrdered> productOrderedList = productOrderedRepository.findAll();
        assertThat(productOrderedList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductOrdereds() throws Exception {
        // Initialize the database
        productOrderedRepository.saveAndFlush(productOrdered);

        // Get all the productOrderedList
        restProductOrderedMockMvc.perform(get("/api/product-ordereds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productOrdered.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].shippingPrice").value(hasItem(DEFAULT_SHIPPING_PRICE.intValue())));
    }
    
    @Test
    @Transactional
    public void getProductOrdered() throws Exception {
        // Initialize the database
        productOrderedRepository.saveAndFlush(productOrdered);

        // Get the productOrdered
        restProductOrderedMockMvc.perform(get("/api/product-ordereds/{id}", productOrdered.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productOrdered.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.shippingPrice").value(DEFAULT_SHIPPING_PRICE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProductOrdered() throws Exception {
        // Get the productOrdered
        restProductOrderedMockMvc.perform(get("/api/product-ordereds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductOrdered() throws Exception {
        // Initialize the database
        productOrderedRepository.saveAndFlush(productOrdered);

        int databaseSizeBeforeUpdate = productOrderedRepository.findAll().size();

        // Update the productOrdered
        ProductOrdered updatedProductOrdered = productOrderedRepository.findById(productOrdered.getId()).get();
        // Disconnect from session so that the updates on updatedProductOrdered are not directly saved in db
        em.detach(updatedProductOrdered);
        updatedProductOrdered
            .quantity(UPDATED_QUANTITY)
            .shippingPrice(UPDATED_SHIPPING_PRICE);
        ProductOrderedDTO productOrderedDTO = productOrderedMapper.toDto(updatedProductOrdered);

        restProductOrderedMockMvc.perform(put("/api/product-ordereds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOrderedDTO)))
            .andExpect(status().isOk());

        // Validate the ProductOrdered in the database
        List<ProductOrdered> productOrderedList = productOrderedRepository.findAll();
        assertThat(productOrderedList).hasSize(databaseSizeBeforeUpdate);
        ProductOrdered testProductOrdered = productOrderedList.get(productOrderedList.size() - 1);
        assertThat(testProductOrdered.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testProductOrdered.getShippingPrice()).isEqualTo(UPDATED_SHIPPING_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingProductOrdered() throws Exception {
        int databaseSizeBeforeUpdate = productOrderedRepository.findAll().size();

        // Create the ProductOrdered
        ProductOrderedDTO productOrderedDTO = productOrderedMapper.toDto(productOrdered);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductOrderedMockMvc.perform(put("/api/product-ordereds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOrderedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductOrdered in the database
        List<ProductOrdered> productOrderedList = productOrderedRepository.findAll();
        assertThat(productOrderedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductOrdered() throws Exception {
        // Initialize the database
        productOrderedRepository.saveAndFlush(productOrdered);

        int databaseSizeBeforeDelete = productOrderedRepository.findAll().size();

        // Delete the productOrdered
        restProductOrderedMockMvc.perform(delete("/api/product-ordereds/{id}", productOrdered.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductOrdered> productOrderedList = productOrderedRepository.findAll();
        assertThat(productOrderedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
