package ro.cybersec.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ro.cybersec.domain.Warehouse} entity.
 */
public class WarehouseDTO implements Serializable {
    
    private Long id;

    private String warehouseName;

    private String postalCode;

    private Long phoneNumber;


    private Long locationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WarehouseDTO)) {
            return false;
        }

        return id != null && id.equals(((WarehouseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WarehouseDTO{" +
            "id=" + getId() +
            ", warehouseName='" + getWarehouseName() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", locationId=" + getLocationId() +
            "}";
    }
}
