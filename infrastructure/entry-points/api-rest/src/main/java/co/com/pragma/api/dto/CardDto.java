package co.com.pragma.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {
    @NotNull
    @Size(min = 1, max = 30)
    private String type;
    @NotNull
    @Size(min = 1, max = 16)
    private String number;
}
