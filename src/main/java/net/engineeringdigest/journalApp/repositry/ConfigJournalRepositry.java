package net.engineeringdigest.journalApp.repositry;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConfigJournalRepositry extends MongoRepository<ConfigJournalAppEntity, ObjectId>{



}
