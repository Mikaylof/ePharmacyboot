package az.mushfigm.epharmacyboot.controller;

import az.mushfigm.epharmacyboot.dto.request.ReqPurchaser;
import az.mushfigm.epharmacyboot.dto.response.Response;
import az.mushfigm.epharmacyboot.service.PurchaserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchaser")
@RequiredArgsConstructor
public class PurchaserController {

    private final PurchaserService purchaserService;

    @PostMapping("/CreatePurchaser")

    public Response createPurchaser(@RequestBody ReqPurchaser reqPurchaser){
        return purchaserService.createPurchaser(reqPurchaser);
    }
    @GetMapping("/register/{token}")
    public Response register(@PathVariable String token) {
        return purchaserService.register(token);
    }
}
