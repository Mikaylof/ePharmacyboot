package az.mushfigm.epharmacyboot.service;

import az.mushfigm.epharmacyboot.dto.request.ReqOrder;
import az.mushfigm.epharmacyboot.dto.response.RespOrder;
import az.mushfigm.epharmacyboot.dto.response.Response;

import java.util.List;

public interface OrderService {
    Response<List<RespOrder>> getOrderList(Long medicationId);

    Response createOrder(ReqOrder reqOrder);
}
