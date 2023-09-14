package az.mushfigm.epharmacyboot.dto.request;

import az.mushfigm.epharmacyboot.entity.Medication;
import lombok.Data;

@Data
public class ReqOrder {
    private Double amount;
    private Integer discount;
    private String status;
    private Integer quantity_ordered;
    private Long medicationId;
}
