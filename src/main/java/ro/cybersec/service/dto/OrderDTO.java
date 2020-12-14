package ro.cybersec.service.dto;

import java.time.Instant;
import java.io.Serializable;
import ro.cybersec.domain.enumeration.Status;

/**
 * A DTO for the {@link ro.cybersec.domain.Order} entity.
 */
public class OrderDTO implements Serializable {
    
    private Long id;

    private Status status;

    private Instant orderDate;


    private Long customerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDTO)) {
            return false;
        }

        return id != null && id.equals(((OrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", customerId=" + getCustomerId() +
            "}";
    }
}
