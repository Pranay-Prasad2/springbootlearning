package net.engineeringdigest.journalApp.Controller;
import net.engineeringdigest.journalApp.api_response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositry.UserEntryRepositry;
import net.engineeringdigest.journalApp.service.UserEntryService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    //using reddis in this project
    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private UserEntryRepositry userEntryRepositry;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userEntryService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userEntryService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepositry.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("London");
        String greetings = "";
        if(weatherResponse != null){
            greetings = " Weather feels like : "+ weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("HI "+authentication.getName() + greetings,HttpStatus.OK);
    }


}
