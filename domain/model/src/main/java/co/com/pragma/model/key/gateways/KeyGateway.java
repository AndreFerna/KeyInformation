package co.com.pragma.model.key.gateways;

import co.com.pragma.model.key.KeyInformation;
import java.util.List;

public interface KeyGateway {
    KeyInformation statusKey(String id);
    List<KeyInformation> findByCustomerIdAndCardId(String customerId, String cardId);
}
