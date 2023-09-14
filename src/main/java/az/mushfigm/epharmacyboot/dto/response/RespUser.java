package az.mushfigm.epharmacyboot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RespUser {
    private String username;
    private String fullName;
    @JsonProperty(value = "token")
    private RespToken respToken;
}
