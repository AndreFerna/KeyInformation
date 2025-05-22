package co.com.pragma.mongo;

import co.com.pragma.mongo.entities.KeyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface MongoDBRepository extends MongoRepository<KeyEntity, String> , QueryByExampleExecutor<KeyEntity> {
    List<KeyEntity> findByCustomerIdAndCardId(String customerId, String cardId);
}
