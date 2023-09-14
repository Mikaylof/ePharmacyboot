package az.mushfigm.epharmacyboot.util;

import az.mushfigm.epharmacyboot.dto.request.ReqToken;
import az.mushfigm.epharmacyboot.entity.User;
import az.mushfigm.epharmacyboot.entity.UserToken;
import az.mushfigm.epharmacyboot.enums.EnumAvailableStatus;
import az.mushfigm.epharmacyboot.exception.ExceptionConstants;
import az.mushfigm.epharmacyboot.exception.MyException;
import az.mushfigm.epharmacyboot.repository.UserRepository;
import az.mushfigm.epharmacyboot.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utility {
    private final UserTokenRepository userTokenRepository;

    private final UserRepository userRepository;
    public UserToken checkToken(ReqToken reqToken){
        Long userId = reqToken.getUserId();
        String token = reqToken.getToken();
        if (userId == null || token == null) {
            throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
        }
        User user = userRepository.findUserByIdAndActive(userId, EnumAvailableStatus.ACTIVE.value);
        if(user == null){
            throw new MyException(ExceptionConstants.USER_NOT_FOUND,"User not found");
        }
        UserToken userToken = userTokenRepository.findUserTokenByUserAndTokenAndActive(user, token, EnumAvailableStatus.ACTIVE.value);
        if(userToken == null){
            throw new MyException(ExceptionConstants.INVALID_TOKEN,"Invalid token");
        }
        return userToken;
    }
}
