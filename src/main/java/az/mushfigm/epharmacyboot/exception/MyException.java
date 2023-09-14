package az.mushfigm.epharmacyboot.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MyException extends RuntimeException {
    private Integer code;
    public MyException(Integer code, String message){
        super(message);
        this.code = code;
    }
    public Integer getCode(){
        return code;
    }
}
