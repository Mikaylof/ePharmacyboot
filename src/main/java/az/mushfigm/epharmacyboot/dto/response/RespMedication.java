package az.mushfigm.epharmacyboot.dto.response;

import az.mushfigm.epharmacyboot.entity.Customer;
import lombok.Builder;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class RespMedication {
    private Long id;
    private String medication_name;
    private Double medication_cost;
    private Date manufactured_date;
    private Date expired_date;
    private RespCustomer respCustomer;
}
