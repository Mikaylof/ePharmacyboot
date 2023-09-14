package az.mushfigm.epharmacyboot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReqCustomer {
    private Long customerId;
    private String name;
    private String surname;
    private String gender;
    private Date dob;
    private String phone;
    private String cif;
    private Long token;
    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
