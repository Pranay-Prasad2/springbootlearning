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

        User user = userEntryService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepositry.save(journalEntry);
        user.getJournalEntries().add(saved);
        userEntryService.saveEntry(user);
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

    public void deleteByID(ObjectId id, String userName){
        User user = userEntryService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userEntryService.saveEntry(user);
        journalEntryRepositry.deleteById(id);
    }

}
