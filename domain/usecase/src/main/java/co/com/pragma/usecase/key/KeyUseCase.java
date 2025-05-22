package co.com.pragma.usecase.key;

import co.com.pragma.model.key.Key;
import co.com.pragma.model.key.KeyInformation;
import co.com.pragma.model.key.config.ErrorCode;
import co.com.pragma.model.key.config.PragmaException;
import co.com.pragma.model.key.CustomerInformation;
import co.com.pragma.model.key.gateways.KeyGateway;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class KeyUseCase {

    private final KeyGateway keyGateway;

    public List<KeyInformation> listKeys(CustomerInformation customerInformation){
        List<KeyInformation> listKeys = keyGateway.findByCustomerIdAndCardId(customerInformation.getIdentification().getNumber(), customerInformation.getCard().getNumber());
        if(listKeys.isEmpty()){
            throw new PragmaException(ErrorCode.BP409_1);
        }
        return listKeys;
    }

    public KeyInformation statusKey(Key key){
        KeyInformation keyInformation = keyGateway.statusKey(key.getValue());
        if (Objects.isNull(keyInformation)) {
            throw new PragmaException(ErrorCode.BP409);
        }
        return keyInformation;
    }

}
