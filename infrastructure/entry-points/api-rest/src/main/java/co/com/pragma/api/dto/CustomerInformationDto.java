package co.com.pragma.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerInformationDto {
    @NotNull
    @Valid
    private IdentificationDto identification;
    @NotNull
    @Valid
    private CardDto card;
}
