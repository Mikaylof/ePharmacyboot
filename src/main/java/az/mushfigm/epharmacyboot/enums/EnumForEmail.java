package az.mushfigm.epharmacyboot.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum EnumForEmail {
    ACTIVE(1), INPROGRESS(2),SENT_EMAIL(3);
    public int value;
}
