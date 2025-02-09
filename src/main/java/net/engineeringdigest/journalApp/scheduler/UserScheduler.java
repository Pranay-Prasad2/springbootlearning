package net.engineeringdigest.journalApp.scheduler;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.enums.Sentiment;
import net.engineeringdigest.journalApp.repositry.UserEntryRepositoryIMPL;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserEntryRepositoryIMPL userRepositoryIMPL;

    //@Autowired
//    private SentimentAnalysis sentimentAnalysis;

    @Autowired
    private AppCache appCache;

//    @Scheduled(cron = "0 0 9 * * SUN")
//    @Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendMail(){
        List<User> users = userRepositoryIMPL.getUserForSA();

        for(User user:users){
            List<JournalEntry> journalEntries = user.getJournalEntries();

            List<Sentiment> collect = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now()
                                    .minus(7, ChronoUnit.DAYS)))
                                    .map(x -> x.getSentiment())
                                    .collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCount = new HashMap<>();
            for (Sentiment sentiment:collect){
                if(sentiment != null){
                    sentimentCount.put(sentiment,sentimentCount.getOrDefault(sentiment,0)+1);
                }
            }
            Sentiment mostFrequent = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment,Integer> entry:sentimentCount.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequent = entry.getKey();
                }
            }
            if(mostFrequent != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", mostFrequent.toString());
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }

}
