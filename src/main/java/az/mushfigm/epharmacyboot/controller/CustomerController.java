package az.mushfigm.epharmacyboot.controller;

import az.mushfigm.epharmacyboot.dto.request.ReqCustomer;
import az.mushfigm.epharmacyboot.dto.request.ReqToken;
import az.mushfigm.epharmacyboot.dto.response.RespCustomer;
import az.mushfigm.epharmacyboot.dto.response.Response;
import az.mushfigm.epharmacyboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping("/GetCustomerList")
    public Response<List<RespCustomer>> getCustomerList(@RequestBody ReqToken reqToken) {
        return customerService.getCustomerList(reqToken);
    }
    @PostMapping("/GetCustomerById")
    public Response<RespCustomer> getCustomerById(@RequestBody ReqCustomer reqCustomer){
        return customerService.getCustomerById(reqCustomer);
    }
    @PostMapping("/AddCustomer")
    public Response addCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.addCustomer(reqCustomer);
    }
    @PutMapping("/UpdateCustomer")
    public Response updateCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.updateCustomer(reqCustomer);
    }
    @PutMapping("/DeleteCustomer")
    public Response deleteCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.deleteCustomer(reqCustomer);
    }
}
