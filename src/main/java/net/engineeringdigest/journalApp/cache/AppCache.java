package net.engineeringdigest.journalApp.cache;

import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repositry.ConfigJournalRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {


    @Autowired
    private ConfigJournalRepositry configJournalRepositry;

    public Map<String,String> APP_CACHE;

    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalRepositry.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity:all){
            APP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }
}
