package co.com.pragma.api;
import co.com.pragma.api.dto.*;
import co.com.pragma.api.mapper.ListKeysMapper;
import co.com.pragma.usecase.key.KeyUseCase;
import co.com.pragma.model.key.KeyInformation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * API Rest controller.
 * 
 * Example of how to declare and use a use case:
 * <pre>
 * private final MyUseCase useCase;
 * 
 * public String commandName() {
 *     return useCase.execute();
 * }
 * </pre>
 */
@RestController
@RequestMapping(value = "/v1/key-management/key-information-api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final KeyUseCase keyUseCase;

    @GetMapping(path = "/usecase/path")
    public String commandName() {
        return "";
    }

    @PostMapping(path = "/list-keys")
    public DataListKeysResponseDto listKeys(@RequestBody @Valid DataListKeysRequestDto dataListKeysRequestDto){
        return null;
    }

    @PostMapping(path = "/status-key")
    public DataStatusKeyResponseDto statusKey(@RequestBody @Valid DataStatusKeyRequestDto dataStatusKeyRequestDto){
        KeyInformation keyInformation = keyUseCase.statusKey(ListKeysMapper.KeyDtoToKey(dataStatusKeyRequestDto.getKey()));
        KeyInformationDto keyInformationDto = ListKeysMapper.KeyInformationToKeyInformationDto(keyInformation);
        return ListKeysMapper.toDataStatusKeyResponseDto(keyInformationDto);
    }



}
