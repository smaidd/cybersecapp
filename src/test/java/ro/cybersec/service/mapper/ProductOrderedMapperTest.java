package ro.cybersec.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductOrderedMapperTest {

    private ProductOrderedMapper productOrderedMapper;

    @BeforeEach
    public void setUp() {
        productOrderedMapper = new ProductOrderedMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(productOrderedMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(productOrderedMapper.fromId(null)).isNull();
    }
}
