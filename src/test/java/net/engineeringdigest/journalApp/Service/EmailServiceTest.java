package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void test(){
        emailService.sendEmail("pranayprasad41@gmail.com","Kaise hai aap","bol bhai kaisa hai");
    }

}
