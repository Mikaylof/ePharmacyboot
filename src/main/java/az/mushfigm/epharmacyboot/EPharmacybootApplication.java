package az.mushfigm.epharmacyboot;

import az.mushfigm.epharmacyboot.dto.request.ReqPurchaser;
import az.mushfigm.epharmacyboot.schedule.MyThread;
import az.mushfigm.epharmacyboot.schedule.MyThread2;
import az.mushfigm.epharmacyboot.schedule.ThreadForEmail;
import az.mushfigm.epharmacyboot.service.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class EPharmacybootApplication {

   /* @Autowired
    private EmailSenderService senderService;*/

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
/*        System.out.println(passwordEncoder.encode("mushfig123"));
        System.out.println(passwordEncoder.encode("ruslan123"));
        System.out.println(passwordEncoder.encode("ferid123"));*/
      /*  MyThread myThread = new MyThread();
        myThread.start();
        MyThread2 myThread2 = new MyThread2();
        myThread2.start();*/
        /*ThreadForEmail threadForEmail = new ThreadForEmail();
        threadForEmail.start();*/
        SpringApplication.run(EPharmacybootApplication.class, args);
    }

}
