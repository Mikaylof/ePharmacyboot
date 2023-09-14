package az.mushfigm.epharmacyboot.service.impl;

import az.mushfigm.epharmacyboot.dto.request.ReqOrder;
import az.mushfigm.epharmacyboot.dto.response.*;
import az.mushfigm.epharmacyboot.entity.Customer;
import az.mushfigm.epharmacyboot.entity.Medication;
import az.mushfigm.epharmacyboot.entity.Order;
import az.mushfigm.epharmacyboot.enums.EnumAvailableStatus;
import az.mushfigm.epharmacyboot.exception.ExceptionConstants;
import az.mushfigm.epharmacyboot.exception.MyException;
import az.mushfigm.epharmacyboot.repository.CustomerRepository;
import az.mushfigm.epharmacyboot.repository.MedicationRepository;
import az.mushfigm.epharmacyboot.repository.OrderRepository;
import az.mushfigm.epharmacyboot.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    private final MedicationRepository medicationRepository;

    @Override
    public Response<List<RespOrder>> getOrderList(Long medicationId) {
        Response<List<RespOrder>> response = new Response<>();
        try {
            if (medicationId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Medication medication = medicationRepository.findMedicationByIdAndActive(medicationId, EnumAvailableStatus.ACTIVE.value);
            if (medication == null) {
                throw new MyException(ExceptionConstants.MEDICATION_NOT_FOUND, "Medication not found");
            }
            List<Order> order = orderRepository.findAllByMedicationAndActive(medication, EnumAvailableStatus.ACTIVE.value);
            if (order.isEmpty()) {
                throw new MyException(ExceptionConstants.ORDER_NOT_FOUND, "Order not found");
            }
            List<RespOrder> respOrders = order.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respOrders);
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
    public Response createOrder(ReqOrder reqOrder) {
        Response response = new Response();
        try {
            Double amount = reqOrder.getAmount();
            Integer quantity_ordered = reqOrder.getQuantity_ordered();
            Integer discount = reqOrder.getDiscount();
            String status = reqOrder.getStatus();
            Long medicationId = reqOrder.getMedicationId();
            if (amount == null || quantity_ordered == null || discount == null || status == null || medicationId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            if(amount <= 0){
                throw new MyException(ExceptionConstants.INVALID_AMOUNT, "Invalid amount");
            }
            Medication medication = medicationRepository.findMedicationByIdAndActive(medicationId, EnumAvailableStatus.ACTIVE.value);
            if (medication == null) {
                throw new MyException(ExceptionConstants.MEDICATION_NOT_FOUND, "Medication not found");
            }
            Order order = Order.builder()
                    .medication(medication)
                    .amount(amount)
                    .quantity_ordered(quantity_ordered)
                    .discount(discount)
                    .status(status)
                    .build();
            orderRepository.save(order);
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

    private RespOrder mapping(Order order) {
        RespCustomer respCustomer = RespCustomer.builder()
                .id(order.getMedication().getCustomer().getId())
                .name(order.getMedication().getCustomer().getName())
                .surname(order.getMedication().getCustomer().getSurname())
                .phone(order.getMedication().getCustomer().getPhone())
                .gender(order.getMedication().getCustomer().getGender())
                .dob(order.getMedication().getCustomer().getDob())
                .build();
        RespMedication medication = RespMedication.builder()
                .id(order.getMedication().getId())
                .medication_name(order.getMedication().getMedication_name())
                .medication_cost(order.getMedication().getMedication_cost())
                .manufactured_date(order.getMedication().getManufactured_date())
                .expired_date(order.getMedication().getExpired_date())
                .respCustomer(respCustomer)
                .build();
        RespOrder respOrder = RespOrder.builder()
                .medication(medication)
                .amount(order.getAmount())
                .quantity_ordered(order.getQuantity_ordered())
                .status(order.getStatus())
                .discount(order.getDiscount())
                .build();
        return respOrder;
    }
}
