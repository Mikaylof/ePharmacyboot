package az.mushfigm.epharmacyboot.service;

import az.mushfigm.epharmacyboot.dto.request.ReqLogin;
import az.mushfigm.epharmacyboot.dto.request.ReqToken;
import az.mushfigm.epharmacyboot.dto.response.RespUser;
import az.mushfigm.epharmacyboot.dto.response.Response;

public interface UserService {
    Response<RespUser> login(ReqLogin reqLogin);

    Response logout(ReqToken reqToken);
}
