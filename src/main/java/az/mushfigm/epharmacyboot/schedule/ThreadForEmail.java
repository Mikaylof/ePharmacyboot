package az.mushfigm.epharmacyboot.schedule;

import az.mushfigm.epharmacyboot.dto.request.ReqCustomer;
import az.mushfigm.epharmacyboot.dto.request.ReqPurchaser;
import az.mushfigm.epharmacyboot.entity.Customer;
import az.mushfigm.epharmacyboot.entity.Purchaser;
import az.mushfigm.epharmacyboot.enums.EnumAvailableStatus;
import az.mushfigm.epharmacyboot.enums.EnumForEmail;
import az.mushfigm.epharmacyboot.exception.ExceptionConstants;
import az.mushfigm.epharmacyboot.exception.MyException;
import az.mushfigm.epharmacyboot.repository.CustomerRepository;
import az.mushfigm.epharmacyboot.repository.PurchaserRepository;
import az.mushfigm.epharmacyboot.service.impl.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@RequiredArgsConstructor
public class ThreadForEmail extends Thread {
    @Autowired
    private EmailSenderService senderService;

    private final PurchaserRepository purchaserRepository;

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        try {
            while (true) {
                ReqPurchaser reqPurchaser = new ReqPurchaser();
                Long reqPurchaserId = reqPurchaser.getId();
                if (reqPurchaserId == null) {
                    throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
                }
                Purchaser purchaser = purchaserRepository.findCustomerByIdAndActive(reqPurchaserId, EnumAvailableStatus.ACTIVE.value);
                if (purchaser == null) {
                    throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
                }
                purchaser.setActive(EnumForEmail.INPROGRESS.value);
                purchaserRepository.save(purchaser);
                senderService.sendEmail("mikayilzademusviq9@gmail.com"
                        , "Hello Mushfig, Please confirm your register!"
                        , "http://localhost:8082/register/" + reqPurchaser.getToken());
                System.out.println("Email successfully sent!");
                purchaser.setActive(EnumForEmail.SENT_EMAIL.value);
                purchaserRepository.save(purchaser);
                Thread.sleep(3000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
