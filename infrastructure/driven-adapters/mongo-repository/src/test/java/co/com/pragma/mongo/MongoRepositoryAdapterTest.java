package co.com.pragma.mongo;

import co.com.pragma.model.key.KeyInformation;
import co.com.pragma.mongo.entities.KeyEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoRepositoryAdapterTest {

    @Mock
    private MongoDBRepository mongoDBRepository;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private MongoRepositoryAdapter adapter;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        adapter = new MongoRepositoryAdapter(mongoDBRepository, mapper);
    }

    @Test
    void statusKey() {
        String keyId = "3146304163";
        KeyEntity entity = new KeyEntity("3146304163", "MSISDN", "ACTIVA", "2025-05-07T19:45:17.788529500", "1193134338", "10234567890");
        KeyInformation response = new KeyInformation("MSISDN", "3146304163", "ACTIVA", "2025-05-07T19:45:17.788529500", "10234567890", "1193134338");

        when(mongoDBRepository.findById(keyId)).thenReturn(Optional.of(entity));
        when(mapper.map(entity, KeyInformation.class)).thenReturn(response);

        KeyInformation result = adapter.statusKey(keyId);

        // Validaciones
        assertNotNull(result);
        assertEquals("MSISDN", result.getType());
        assertEquals("3146304163", result.getValue());
        verify(mongoDBRepository).findById(keyId);
    }

    @Test
    void findByCustomerIdAndCardId() {
        String customerId = "1193134338";
        String cardId = "10234567890";

        KeyEntity entity = new KeyEntity("3146304163", "MSISDN", "ACTIVA", "2025-05-07T19:45:17.788529500", customerId, cardId);
        when(mongoDBRepository.findByCustomerIdAndCardId(customerId, cardId)).thenReturn(List.of(entity));

        List<KeyInformation> results = adapter.findByCustomerIdAndCardId(customerId, cardId);

        // Validaciones
        assertNotNull(results);
        assertEquals("MSISDN", results.get(0).getType());
        assertEquals("3146304163", results.get(0).getValue());
        verify(mongoDBRepository).findByCustomerIdAndCardId(customerId, cardId);
    }

}