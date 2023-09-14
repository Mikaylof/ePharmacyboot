package az.mushfigm.epharmacyboot.dto.response;

import az.mushfigm.epharmacyboot.entity.Medication;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RespCustomer {
    private Long id;
    private String name;
    private String surname;
    private String gender;
    private Date dob;
    private String phone;
}
