package co.com.pragma.mongo;

import co.com.pragma.model.key.KeyInformation;
import co.com.pragma.model.key.gateways.KeyGateway;
import co.com.pragma.mongo.entities.KeyEntity;
import co.com.pragma.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<KeyInformation, KeyEntity, String, MongoDBRepository> implements KeyGateway
// implements ModelRepository from domain
{

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, KeyInformation.class));
    }

    @Override
    public ArrayList<KeyInformation> listKeys() {
        return (ArrayList<KeyInformation>) findAll();
    }

    @Override
    public KeyInformation statusKey(String id) {
        return findById(id);
    }
}
