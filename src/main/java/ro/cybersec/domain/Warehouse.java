package ro.cybersec.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Warehouse.
 */
@Entity
@Table(name = "warehouse")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @ManyToOne
    @JsonIgnoreProperties(value = "warehouses", allowSetters = true)
    private Location location;

    @ManyToMany(mappedBy = "warehouses")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public Warehouse warehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
        return this;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Warehouse postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Warehouse phoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Location getLocation() {
        return location;
    }

    public Warehouse location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Warehouse products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public Warehouse addProducts(Product product) {
        this.products.add(product);
        product.getWarehouses().add(this);
        return this;
    }

    public Warehouse removeProducts(Product product) {
        this.products.remove(product);
        product.getWarehouses().remove(this);
        return this;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Warehouse)) {
            return false;
        }
        return id != null && id.equals(((Warehouse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Warehouse{" +
            "id=" + getId() +
            ", warehouseName='" + getWarehouseName() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            "}";
    }
}
