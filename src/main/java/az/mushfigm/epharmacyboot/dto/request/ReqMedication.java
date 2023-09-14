package az.mushfigm.epharmacyboot.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class ReqMedication {
    private String medication_name;
    private Double medication_cost;
    private Date manufactured_date;
    private Date expired_date;
    private Long customerId;
}
