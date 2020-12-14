package ro.cybersec.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link ro.cybersec.domain.Product} entity.
 */
public class ProductDTO implements Serializable {
    
    private Long id;

    private String productName;

    private String description;

    private Long price;

    private Set<WarehouseDTO> warehouses = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Set<WarehouseDTO> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<WarehouseDTO> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + getId() +
            ", productName='" + getProductName() + "'" +
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            ", warehouses='" + getWarehouses() + "'" +
            "}";
    }
}
