package co.com.pragma.usecase.key;

import co.com.pragma.model.key.Key;
import co.com.pragma.model.key.KeyInformation;
import co.com.pragma.model.key.config.ErrorCode;
import co.com.pragma.model.key.config.PragmaException;
import co.com.pragma.model.key.gateways.CustomerInformation;
import co.com.pragma.model.key.gateways.KeyGateway;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;

@RequiredArgsConstructor
public class KeyUseCase {

    private final KeyGateway keyGateway;

    public ArrayList<KeyInformation> listKeys(CustomerInformation customerInformation){
        return null;
    }

    public KeyInformation statusKey(Key key){
        KeyInformation keyInformation = keyGateway.statusKey(key.getValue());
        if (Objects.isNull(keyInformation)) {
            throw new PragmaException(ErrorCode.BP409);
        }
        return keyInformation;
    }

}
