package co.com.pragma.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestInformationDto {
    @NotNull
    @NotBlank(message = "Parametro requestInformation.identifier es obligatorio")
    @Size(min = 1, max = 40, message = "Parametro requestInformation.identifier no cumple con la longitud")
    private String identifier;
    @NotNull
    @NotBlank(message = "Parametro requestInformation.consumer es obligatorio")
    @Size(min = 1, max = 50, message = "Parametro requestInformation.consumer no cumple con la longitud")
    private String consumer;
}
