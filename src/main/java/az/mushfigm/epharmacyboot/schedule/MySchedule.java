package az.mushfigm.epharmacyboot.schedule;

import az.mushfigm.epharmacyboot.entity.Customer;
import az.mushfigm.epharmacyboot.entity.Purchaser;
import az.mushfigm.epharmacyboot.enums.EnumForEmail;
import az.mushfigm.epharmacyboot.repository.CustomerRepository;
import az.mushfigm.epharmacyboot.repository.PurchaserRepository;
import az.mushfigm.epharmacyboot.service.impl.EmailSenderService;
import az.mushfigm.epharmacyboot.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class MySchedule {

    private final PurchaserRepository purchaserRepository;

    private final EmailSenderService emailSenderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MySchedule.class);

    @Scheduled(fixedRate = 3000)
    public void fixedRate() {
        List<Purchaser> purchaserList = purchaserRepository.findAllByActive(EnumForEmail.ACTIVE.value);
        for (Purchaser purchaser : purchaserList) {
            if (purchaser.getActive() == 1) {
                purchaser.setActive(EnumForEmail.INPROGRESS.value);
                purchaserRepository.save(purchaser);
                emailSenderService.sendEmail(purchaser.getEmail()
                        , "Hello Mushfig"
                        , "Please confirm your register! http://localhost:8082/pharmacy/purchaser/register/" + purchaser.getToken());
                System.out.println("Email successfully sent!");
            }
        }
        //LOGGER.info("Hello Schedule!");
    }
}
