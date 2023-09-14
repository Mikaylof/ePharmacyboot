package az.mushfigm.epharmacyboot.service.impl;

import az.mushfigm.epharmacyboot.dto.request.ReqCustomer;
import az.mushfigm.epharmacyboot.dto.request.ReqToken;
import az.mushfigm.epharmacyboot.dto.response.RespCustomer;
import az.mushfigm.epharmacyboot.dto.response.RespStatus;
import az.mushfigm.epharmacyboot.dto.response.Response;
import az.mushfigm.epharmacyboot.entity.Customer;
import az.mushfigm.epharmacyboot.entity.User;
import az.mushfigm.epharmacyboot.entity.UserToken;
import az.mushfigm.epharmacyboot.enums.EnumAvailableStatus;
import az.mushfigm.epharmacyboot.enums.EnumForEmail;
import az.mushfigm.epharmacyboot.exception.ExceptionConstants;
import az.mushfigm.epharmacyboot.exception.MyException;
import az.mushfigm.epharmacyboot.repository.CustomerRepository;
import az.mushfigm.epharmacyboot.repository.UserRepository;
import az.mushfigm.epharmacyboot.repository.UserTokenRepository;
import az.mushfigm.epharmacyboot.service.CustomerService;
import az.mushfigm.epharmacyboot.util.Utility;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final UserTokenRepository userTokenRepository;

    private final UserRepository userRepository;

    private final Utility utility;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);


    @Override
    public Response<List<RespCustomer>> getCustomerList(ReqToken reqToken) {
        Response<List<RespCustomer>> response = new Response<>();
        LOGGER.info("getCustomerList request: " + reqToken);
        try {
            utility.checkToken(reqToken);
            List<Customer> customerList = customerRepository.findAllByActive(EnumAvailableStatus.ACTIVE.value);
            if (customerList.isEmpty()) {
                LOGGER.info("getCustomerList response: Customer not found" );
                throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            List<RespCustomer> respCustomerList = customerList.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respCustomerList);
            response.setStatus(RespStatus.getSuccessMessage());
            LOGGER.info("getCustomerList response: success ;" );
        } catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response<RespCustomer> getCustomerById(ReqCustomer reqCustomer) {
        Response<RespCustomer> response = new Response<>();
        try {
            Long customerId = reqCustomer.getCustomerId();
            utility.checkToken(reqCustomer.getReqToken());
            if (customerId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            RespCustomer respCustomer = mapping(customer);
            response.setT(respCustomer);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response addCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();
        try {
            utility.checkToken(reqCustomer.getReqToken());
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            if (name == null || surname == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = Customer.builder().
                    name(name).
                    surname(surname).
                    gender(reqCustomer.getGender()).
                    phone(reqCustomer.getPhone()).
                    cif(reqCustomer.getCif()).
                    dob(reqCustomer.getDob()).
                    token(UUID.randomUUID()).
                    build();
            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response updateCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();
        try {
           // utility.checkToken(reqCustomer.getReqToken());
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            Long customerId = reqCustomer.getCustomerId();
            if (name == null || surname == null || customerId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            customer.setName(name);
            customer.setSurname(surname);
            customer.setGender(reqCustomer.getGender());
            customer.setPhone(reqCustomer.getPhone());
            customer.setCif(reqCustomer.getCif());
            customer.setDob(reqCustomer.getDob());
            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response deleteCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();
        try {
           // utility.checkToken(reqCustomer.getReqToken());
            Long customerId = reqCustomer.getCustomerId();
            if (customerId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            customer.setActive(EnumAvailableStatus.DEACTIVE.value);
            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    private RespCustomer mapping(Customer customer) {
        RespCustomer respCustomer = RespCustomer.builder().
                id(customer.getId()).
                name(customer.getName()).
                surname(customer.getSurname()).
                gender(customer.getGender()).
                phone(customer.getPhone()).
                // cif(customer.getCif()).
                // dob(customer.getDob()).
                build();
        return respCustomer;
    }
}
