package az.mushfigm.epharmacyboot.service;

import az.mushfigm.epharmacyboot.dto.request.ReqPurchaser;
import az.mushfigm.epharmacyboot.dto.response.Response;

public interface PurchaserService {
    Response createPurchaser(ReqPurchaser reqPurchaser);

    Response register(String token);
}
