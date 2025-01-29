package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositry.JournalEntryRepositry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepositry journalEntryRepositry;
    @Autowired
    private UserEntryService userEntryService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){

        try {
            User user = userEntryService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepositry.save(journalEntry);
            user.getJournalEntries().add(saved);
            userEntryService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("An Error Occurred while saving the entry",e);
        }

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepositry.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepositry.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId id){
        return journalEntryRepositry.findById(id);
    }

    @Transactional
    public boolean deleteByID(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userEntryService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userEntryService.saveUser(user);
                journalEntryRepositry.deleteById(id);
            }
        }catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An Error occurred while deleting the entry.", e);
        }
        return removed;
    }
}
