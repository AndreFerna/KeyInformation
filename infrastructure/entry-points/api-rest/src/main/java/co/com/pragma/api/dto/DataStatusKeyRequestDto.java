package co.com.pragma.api.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataStatusKeyRequestDto {
    @NotNull
    @Valid
    private RequestInformationDto requestInformation;
    @NotNull
    @Valid
    private KeyDto key;
}
