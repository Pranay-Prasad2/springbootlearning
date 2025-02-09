package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositry.UserEntryRepositry;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserEntryService {

    @Autowired
    private UserEntryRepositry  userEntryRepositry;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserEntryService.class);

    public void saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userEntryRepositry.save(user);
        }catch (Exception e){
            log.info("chutiya sala",e);
        }
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userEntryRepositry.save(user);
    }

    public void saveUser(User user){
        userEntryRepositry.save(user);
    }

    public List<User> getAll(){
        return userEntryRepositry.findAll();
    }

    public Optional<User> findByID(ObjectId id){
        return userEntryRepositry.findById(id);
    }

    public void deleteByID(ObjectId id){
        userEntryRepositry.deleteById(id);
    }

    public User findByUserName(String userName){
        return userEntryRepositry.findByUserName(userName);
    }

}
