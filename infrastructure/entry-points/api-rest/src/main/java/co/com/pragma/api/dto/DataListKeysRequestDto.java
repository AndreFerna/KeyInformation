package co.com.pragma.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataListKeysRequestDto {
    @NotNull
    @Valid
    private RequestInformationDto requestInformation;
    @NotNull
    @Valid
    private CustomerInformationDto customerInformation;

}
