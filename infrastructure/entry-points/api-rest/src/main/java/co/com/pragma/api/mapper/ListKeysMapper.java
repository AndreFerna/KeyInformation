package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.DataStatusKeyResponseDto;
import co.com.pragma.api.dto.KeyDto;
import co.com.pragma.api.dto.KeyInformationDto;
import co.com.pragma.model.key.Key;
import co.com.pragma.model.key.KeyInformation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ListKeysMapper {

    public Key KeyDtoToKey(KeyDto keyDto){
        return Key.builder()
                .type(keyDto.getType())
                .value(keyDto.getValue())
                .build();
    }

    public KeyInformationDto KeyInformationToKeyInformationDto(KeyInformation keyInformation){
        return KeyInformationDto.builder()
                .type(keyInformation.getType())
                .value(keyInformation.getValue())
                .status(keyInformation.getStatus())
                .creationDate(keyInformation.getCreationDate())
                .build();
    }

    public DataStatusKeyResponseDto toDataStatusKeyResponseDto(KeyInformationDto keyInformationDto){
        return DataStatusKeyResponseDto.builder()
                .keyInformationDto(keyInformationDto)
                .build();
    }

}
