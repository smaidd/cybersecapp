package ro.cybersec.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ro.cybersec.domain.ProductOrdered} entity.
 */
public class ProductOrderedDTO implements Serializable {
    
    private Long id;

    private Long quantity;

    private Long shippingPrice;


    private Long productId;

    private Long sellingCompanyId;

    private Long orderId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Long shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellingCompanyId() {
        return sellingCompanyId;
    }

    public void setSellingCompanyId(Long warehouseId) {
        this.sellingCompanyId = warehouseId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductOrderedDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductOrderedDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductOrderedDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", shippingPrice=" + getShippingPrice() +
            ", productId=" + getProductId() +
            ", sellingCompanyId=" + getSellingCompanyId() +
            ", orderId=" + getOrderId() +
            "}";
    }
}
