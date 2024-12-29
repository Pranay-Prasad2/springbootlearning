package net.engineeringdigest.journalApp.repositry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserEntryRepositry extends MongoRepository<User, ObjectId>{

    User findByUserName(String username);

}
