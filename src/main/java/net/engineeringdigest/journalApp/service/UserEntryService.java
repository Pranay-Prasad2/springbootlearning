package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositry.UserEntryRepositry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepositry  userEntryRepositry;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
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
