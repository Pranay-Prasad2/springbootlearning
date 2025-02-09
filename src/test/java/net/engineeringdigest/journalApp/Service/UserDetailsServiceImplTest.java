package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.repositry.UserEntryRepositry;
import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.mockito.Mockito.*;


@DisabledIf("true")
public class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserEntryRepositry userEntryRepositry;

//    void loadUserByusernameTest(){
//        when(userEntryRepositry.findByUserName()).thenReturn(User.builder().user)
//        UserDetails user = userDetailsService.loadUserByUsername("Pranay");
//    }
}
