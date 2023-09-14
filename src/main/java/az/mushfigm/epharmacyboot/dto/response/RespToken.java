package az.mushfigm.epharmacyboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespToken {
    private Long userId;
    private String token;
}
