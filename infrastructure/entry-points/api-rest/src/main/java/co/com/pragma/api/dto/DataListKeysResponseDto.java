package co.com.pragma.api.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataListKeysResponseDto {
    List<KeyInformationDto> keyList;
}
