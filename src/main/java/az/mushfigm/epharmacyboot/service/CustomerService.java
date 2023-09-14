package az.mushfigm.epharmacyboot.service;

import az.mushfigm.epharmacyboot.dto.request.ReqCustomer;
import az.mushfigm.epharmacyboot.dto.request.ReqToken;
import az.mushfigm.epharmacyboot.dto.response.RespCustomer;
import az.mushfigm.epharmacyboot.dto.response.Response;

import java.util.List;

public interface CustomerService {
    Response<List<RespCustomer>> getCustomerList(ReqToken reqToken);

    Response<RespCustomer> getCustomerById(ReqCustomer reqCustomer);

    Response addCustomer(ReqCustomer reqCustomer);

    Response updateCustomer(ReqCustomer reqCustomer);

    Response deleteCustomer(ReqCustomer reqCustomer);

}
