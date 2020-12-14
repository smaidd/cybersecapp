package ro.cybersec.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @ManyToMany
    @JoinTable(name = "product_warehouse",
               joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "warehouse_id", referencedColumnName = "id"))
    private Set<Warehouse> warehouses = new HashSet<>();

    @OneToOne(mappedBy = "product")
    @JsonIgnore
    private ProductOrdered productOrdered;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public Product productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public Product description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public Product price(Long price) {
        this.price = price;
        return this;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public Product warehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
        return this;
    }

    public Product addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
        warehouse.getProducts().add(this);
        return this;
    }

    public Product removeWarehouse(Warehouse warehouse) {
        this.warehouses.remove(warehouse);
        warehouse.getProducts().remove(this);
        return this;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public ProductOrdered getProductOrdered() {
        return productOrdered;
    }

    public Product productOrdered(ProductOrdered productOrdered) {
        this.productOrdered = productOrdered;
        return this;
    }

    public void setProductOrdered(ProductOrdered productOrdered) {
        this.productOrdered = productOrdered;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", productName='" + getProductName() + "'" +
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            "}";
    }
}
