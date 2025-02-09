package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.repositry.UserEntryRepositoryIMPL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.DisabledIf;

@SpringBootTest
@DisabledIf("true")
public class UserRepositoryIMPLTest {

    @Autowired
    private UserEntryRepositoryIMPL userRepositoryIMPL;

    @Test
    public void testUserRepo(){
        userRepositoryIMPL.getUserForSA();
    }

}
