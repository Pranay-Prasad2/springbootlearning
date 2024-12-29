package net.engineeringdigest.journalApp.Controller;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournelEnteryController {
    private Map<ObjectId,JournalEntry> journalentries = new HashMap<>();

    @GetMapping()
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalentries.values());

    }
    @PostMapping()
    public void createEntry(@RequestBody JournalEntry myEntry){
        journalentries.put(myEntry.getId(),myEntry);
    }

    @GetMapping("id/{myid}")
    public JournalEntry getJournalEntryByID(@PathVariable ObjectId myid){
        return journalentries.get(myid);
    }

    @DeleteMapping("id/{myid}")
    public Boolean deleteJournalEntryByID(@PathVariable ObjectId myid){
        journalentries.remove(myid);
        return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalEntryByID(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry){
        return journalentries.put(id,myEntry);
    }
}
