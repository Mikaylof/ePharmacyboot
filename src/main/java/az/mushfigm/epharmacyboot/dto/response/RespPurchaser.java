package az.mushfigm.epharmacyboot.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespPurchaser {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String token;
}
