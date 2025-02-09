package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.repositry.UserEntryRepositry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.DisabledIf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisabledIf("true")
public class UserEntryServiceTest {
    @Autowired
    private UserEntryRepositry userEntryRepositry;

    @Test
    public void testFindByUsername(){
        assertNotNull(userEntryRepositry.findByUserName("anisha"));
    }
}
