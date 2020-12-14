package ro.cybersec.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.cybersec.web.rest.TestUtil;

public class ProductOrderedDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductOrderedDTO.class);
        ProductOrderedDTO productOrderedDTO1 = new ProductOrderedDTO();
        productOrderedDTO1.setId(1L);
        ProductOrderedDTO productOrderedDTO2 = new ProductOrderedDTO();
        assertThat(productOrderedDTO1).isNotEqualTo(productOrderedDTO2);
        productOrderedDTO2.setId(productOrderedDTO1.getId());
        assertThat(productOrderedDTO1).isEqualTo(productOrderedDTO2);
        productOrderedDTO2.setId(2L);
        assertThat(productOrderedDTO1).isNotEqualTo(productOrderedDTO2);
        productOrderedDTO1.setId(null);
        assertThat(productOrderedDTO1).isNotEqualTo(productOrderedDTO2);
    }
}
