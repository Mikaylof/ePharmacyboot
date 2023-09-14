package az.mushfigm.epharmacyboot.dto.response;

import az.mushfigm.epharmacyboot.entity.Customer;
import az.mushfigm.epharmacyboot.entity.Medication;
import lombok.Builder;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class RespOrder {
    private Long id;
    private Double amount;
    private Integer discount;
    private String status;
    private Integer quantity_ordered;
    private RespMedication medication;
}
