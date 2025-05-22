package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.*;
import co.com.pragma.model.key.*;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ListKeysMapper {

    public Key KeyDtoToKey(KeyDto keyDto){
        return Key.builder()
                .type(keyDto.getType())
                .value(keyDto.getValue())
                .build();
    }

    public Identification identificationDtoToIdentification(IdentificationDto identificationDto){
        return Identification.builder()
                .type(identificationDto.getType())
                .number(identificationDto.getNumber())
                .build();
    }

    public Card cardDtoToCard(CardDto cardDto){
        return Card.builder()
                .type(cardDto.getType())
                .number(cardDto.getNumber())
                .build();
    }

    public CustomerInformation customerInformationDtoToCustomerInformation(CustomerInformationDto customerInformationDto){
        return CustomerInformation.builder()
                .identification(identificationDtoToIdentification(customerInformationDto.getIdentification()))
                .card(cardDtoToCard(customerInformationDto.getCard()))
                .build();
    }

    public List<KeyInformationDto> toList(List<KeyInformation> list){
        return new ArrayList<> (list.stream().map(item -> new KeyInformationDto(
                item.getType(),
                item.getValue(),
                item.getStatus(),
                item.getCreationDate()
        )).collect(Collectors.toList()));
    }

    public DataListKeysResponseDto toDataListKeysResponseDto(List<KeyInformationDto> list){
        return DataListKeysResponseDto.builder()
                .keyList(list)
                .build();
    }

}
