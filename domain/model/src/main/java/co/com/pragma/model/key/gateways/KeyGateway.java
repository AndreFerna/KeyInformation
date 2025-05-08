package co.com.pragma.model.key.gateways;

import co.com.pragma.model.key.KeyInformation;
import java.util.ArrayList;

public interface KeyGateway {
    ArrayList<KeyInformation> listKeys();
    KeyInformation statusKey(String id);
}
