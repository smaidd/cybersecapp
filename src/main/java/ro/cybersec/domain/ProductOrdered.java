package ro.cybersec.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProductOrdered.
 */
@Entity
@Table(name = "product_ordered")
public class ProductOrdered implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "shipping_price")
    private Long shippingPrice;

    @OneToOne
    @JoinColumn(unique = true)
    private Product product;

    @OneToOne
    @JoinColumn(unique = true)
    private Warehouse sellingCompany;

    @ManyToOne
    @JsonIgnoreProperties(value = "products", allowSetters = true)
    private Order order;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ProductOrdered quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getShippingPrice() {
        return shippingPrice;
    }

    public ProductOrdered shippingPrice(Long shippingPrice) {
        this.shippingPrice = shippingPrice;
        return this;
    }

    public void setShippingPrice(Long shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public Product getProduct() {
        return product;
    }

    public ProductOrdered product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getSellingCompany() {
        return sellingCompany;
    }

    public ProductOrdered sellingCompany(Warehouse warehouse) {
        this.sellingCompany = warehouse;
        return this;
    }

    public void setSellingCompany(Warehouse warehouse) {
        this.sellingCompany = warehouse;
    }

    public Order getOrder() {
        return order;
    }

    public ProductOrdered order(Order order) {
        this.order = order;
        return this;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductOrdered)) {
            return false;
        }
        return id != null && id.equals(((ProductOrdered) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductOrdered{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", shippingPrice=" + getShippingPrice() +
            "}";
    }
}
