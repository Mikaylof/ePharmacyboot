package az.mushfigm.epharmacyboot.controller;

import az.mushfigm.epharmacyboot.dto.request.ReqLogin;
import az.mushfigm.epharmacyboot.dto.request.ReqToken;
import az.mushfigm.epharmacyboot.dto.response.RespUser;
import az.mushfigm.epharmacyboot.dto.response.Response;
import az.mushfigm.epharmacyboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Response<RespUser> login(@RequestBody ReqLogin reqLogin) {
        return userService.login(reqLogin);
    }

    @PostMapping("/logout")
    public Response logout(@RequestBody ReqToken reqToken) {
        return userService.logout(reqToken);
    }
}
