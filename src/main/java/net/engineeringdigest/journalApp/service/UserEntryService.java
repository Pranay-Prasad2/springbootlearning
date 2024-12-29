package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositry.JournalEntryRepositry;
import net.engineeringdigest.journalApp.repositry.UserEntryRepositry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepositry userEntryRepositry;

    public void saveEntry(User user){
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
