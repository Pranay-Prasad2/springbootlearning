package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositry.JournalEntryRepositry;
import org.bson.types.ObjectId;

// slf4j logback implimentation (Simple Logging Facade for Java)
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepositry journalEntryRepositry;
    @Autowired
    private UserEntryService userEntryService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){

        try {
            User user = userEntryService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepositry.save(journalEntry);
            user.getJournalEntries().add(saved);
            userEntryService.saveUser(user);
        } catch (Exception e) {
            log.info("Error in saving a entry");
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
            log.info("Error in deleting a entry",e);
            throw new RuntimeException("An Error occurred while deleting the entry.", e);
        }
        return removed;
    }
}
