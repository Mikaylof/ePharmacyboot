package az.mushfigm.epharmacyboot.dto.request;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
public class ReqPurchaser {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String token;
}
