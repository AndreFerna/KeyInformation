package co.com.pragma.mongo;

import co.com.pragma.mongo.entities.KeyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MongoDBRepository extends MongoRepository<KeyEntity, String> , QueryByExampleExecutor<KeyEntity> {
}
