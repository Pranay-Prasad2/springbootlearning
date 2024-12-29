package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repositry.JournalEntryRepositry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepositry journalEntryRepositry;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepositry.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepositry.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId id){
        return journalEntryRepositry.findById(id);
    }

    public void deleteByID(ObjectId id){
        journalEntryRepositry.deleteById(id);
    }

}
