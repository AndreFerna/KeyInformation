package co.com.pragma.mongo;

import co.com.pragma.model.key.KeyInformation;
import co.com.pragma.model.key.gateways.KeyGateway;
import co.com.pragma.mongo.entities.KeyEntity;
import co.com.pragma.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<KeyInformation, KeyEntity, String, MongoDBRepository> implements KeyGateway
{

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, KeyInformation.class));
    }

    @Override
    public KeyInformation statusKey(String id) {
        return findById(id);
    }

    @Override
    public List<KeyInformation> findByCustomerIdAndCardId(String customerId, String cardId) {
        List<KeyEntity> entities = repository.findByCustomerIdAndCardId(customerId, cardId);

        return entities.stream().map(item -> new KeyInformation(
                item.getType(),
                item.getValue(),
                item.getStatus(),
                item.getCreationDate(),
                item.getCardId(),
                item.getCustomerId()
        )).collect(Collectors.toList());
    }
}
