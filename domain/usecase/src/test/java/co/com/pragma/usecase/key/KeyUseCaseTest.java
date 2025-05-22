package co.com.pragma.usecase.key;

import co.com.pragma.model.key.*;
import co.com.pragma.model.key.config.PragmaException;
import co.com.pragma.model.key.gateways.KeyGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static co.com.pragma.model.key.config.ErrorCode.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class KeyUseCaseTest {

    @Mock
    private KeyGateway keyGateway;
    @InjectMocks
    private KeyUseCase keyUseCase;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listKeys() {
        Identification identification = new Identification("CC", "1193134338");
        Card card = new Card("CUENTA_DE_AHORRO", "10234567890");
        CustomerInformation customerInformation = new CustomerInformation(identification, card);
        List<KeyInformation> list = new ArrayList<KeyInformation>();
        KeyInformation keyInformation = new KeyInformation("MSISDN", "3146304163", "BLOQUEADA", "2025-05-07T19:45:17", "10234567890", "1193134338");
        list.add(keyInformation);

        when(keyGateway.findByCustomerIdAndCardId(customerInformation.getIdentification().getNumber(), customerInformation.getCard().getNumber())).thenReturn(list);

        List<KeyInformation>  listTest = keyUseCase.listKeys(customerInformation);

        Assertions.assertEquals(listTest, list);
    }

    @Test
    void statusKey() {
        Key key = new Key("MSISDN", "3146304163");
        KeyInformation keyInformation = new KeyInformation("MSISDN", "3146304163", "BLOQUEADA", "2025-05-07T19:45:17", "", "");

        when(keyGateway.statusKey(key.getValue())).thenReturn(keyInformation);

        KeyInformation keyInformationTest = keyUseCase.statusKey(key);

        Assertions.assertEquals(keyInformationTest, keyInformation);
    }

    @Test
    void isEmptyList(){
        Identification identification = new Identification("CC", "1193134338");
        Card card = new Card("CUENTA_DE_AHORRO", "10234567890");
        CustomerInformation customerInformation = new CustomerInformation(identification, card);
        List<KeyInformation> list = new ArrayList<KeyInformation>();

        when(keyGateway.findByCustomerIdAndCardId(customerInformation.getIdentification().getNumber(), customerInformation.getCard().getNumber())).thenReturn(list);

        PragmaException exception = assertThrows(PragmaException.class, () -> {
            keyUseCase.listKeys(customerInformation);
        });

        assertEquals(BP409_1, exception.getError());
    }

    @Test
    void isEmptyStatus(){
        Key key = new Key("MSISDN", "3146304163");

        when(keyGateway.statusKey(key.getValue())).thenReturn(null);

        PragmaException exception = assertThrows(PragmaException.class, () -> {
            keyUseCase.statusKey(key);
        });

        assertEquals(BP409, exception.getError());
    }
}