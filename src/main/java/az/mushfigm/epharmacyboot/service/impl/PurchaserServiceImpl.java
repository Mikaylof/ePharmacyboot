package az.mushfigm.epharmacyboot.service.impl;

import az.mushfigm.epharmacyboot.dto.request.ReqPurchaser;
import az.mushfigm.epharmacyboot.dto.response.RespStatus;
import az.mushfigm.epharmacyboot.dto.response.Response;
import az.mushfigm.epharmacyboot.entity.Customer;
import az.mushfigm.epharmacyboot.entity.Purchaser;
import az.mushfigm.epharmacyboot.enums.EnumAvailableStatus;
import az.mushfigm.epharmacyboot.enums.EnumForEmail;
import az.mushfigm.epharmacyboot.exception.ExceptionConstants;
import az.mushfigm.epharmacyboot.exception.MyException;
import az.mushfigm.epharmacyboot.repository.PurchaserRepository;
import az.mushfigm.epharmacyboot.service.PurchaserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaserServiceImpl implements PurchaserService {

    @Autowired
    private EmailSenderService senderService;

    private final PurchaserRepository purchaserRepository;

    @Override
   // @EventListener(ApplicationReadyEvent.class)
    public Response createPurchaser(ReqPurchaser reqPurchaser) {
        Response response = new Response();
        try {
            String name = reqPurchaser.getName();
            String surname = reqPurchaser.getSurname();
            if (name == null || surname == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Purchaser purchaser = Purchaser.builder()
                    .name(reqPurchaser.getName())
                    .surname(reqPurchaser.getSurname())
                    .email(reqPurchaser.getEmail())
                    .token(String.valueOf(UUID.randomUUID()))
                    .build();
            purchaserRepository.save(purchaser);
            response.setStatus(RespStatus.getSuccessMessage());
            /*senderService.sendEmail(reqPurchaser.getEmail()
                    , "Hello Mushfig"
                    , "Please confirm your register! http://localhost:8082/pharmacy/purchaser/register/" + purchaser.getToken());
            System.out.println("Email successfully sent!");*/
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
    public Response register(String token) {
        Response response = new Response();
        try {
            Purchaser purchaser = purchaserRepository.findByTokenAndActive(token, EnumForEmail.INPROGRESS.value);
            if (purchaser == null) {
                throw new MyException(ExceptionConstants.INVALID_TOKEN, "Invalid token");
            }
            purchaser.setActive(EnumForEmail.SENT_EMAIL.value);
            purchaserRepository.save(purchaser);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }
        return response;
    }
}
