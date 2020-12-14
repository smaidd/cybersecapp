package ro.cybersec.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.cybersec.web.rest.TestUtil;

public class ProductOrderedTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductOrdered.class);
        ProductOrdered productOrdered1 = new ProductOrdered();
        productOrdered1.setId(1L);
        ProductOrdered productOrdered2 = new ProductOrdered();
        productOrdered2.setId(productOrdered1.getId());
        assertThat(productOrdered1).isEqualTo(productOrdered2);
        productOrdered2.setId(2L);
        assertThat(productOrdered1).isNotEqualTo(productOrdered2);
        productOrdered1.setId(null);
        assertThat(productOrdered1).isNotEqualTo(productOrdered2);
    }
}
