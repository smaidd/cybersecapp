package ro.cybersec.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Location.
 */
@Entity
@Table(name = "location")
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "county")
    private String county;

    @OneToMany(mappedBy = "location")
    private Set<Warehouse> warehouses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public Location address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public Location city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public Location county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public Location warehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
        return this;
    }

    public Location addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
        warehouse.setLocation(this);
        return this;
    }

    public Location removeWarehouse(Warehouse warehouse) {
        this.warehouses.remove(warehouse);
        warehouse.setLocation(null);
        return this;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        return id != null && id.equals(((Location) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Location{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", county='" + getCounty() + "'" +
            "}";
    }
}
