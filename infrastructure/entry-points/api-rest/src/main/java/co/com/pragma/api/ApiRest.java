package co.com.pragma.api;
import co.com.pragma.api.dto.*;
import co.com.pragma.api.mapper.ListKeysMapper;
import co.com.pragma.api.mapper.StatusKeyMapper;
import co.com.pragma.model.key.config.ErrorCode;
import co.com.pragma.model.key.config.PragmaException;
import co.com.pragma.usecase.key.KeyUseCase;
import co.com.pragma.model.key.KeyInformation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private HealthEndpoint healthEndpoint;

    @RequestMapping(path = "/health", method = RequestMethod.HEAD)
    public ResponseEntity<Void> health() {
        HealthComponent healthComponent = healthEndpoint.health();

        if (Status.UP.equals(healthComponent.getStatus())) {
            return ResponseEntity.ok().build();
        } else {
            throw new PragmaException(ErrorCode.SP503);
        }
    }

    @PostMapping(path = "/list-keys")
    public DataListKeysResponseDto listKeys(@RequestBody @Valid DataListKeysRequestDto dataListKeysRequestDto){
        List<KeyInformation> listKeys = keyUseCase.listKeys(ListKeysMapper.customerInformationDtoToCustomerInformation(dataListKeysRequestDto.getCustomerInformation()));
        List<KeyInformationDto> listKeysDto = ListKeysMapper.toList(listKeys);
        return ListKeysMapper.toDataListKeysResponseDto(listKeysDto);
    }

    @PostMapping(path = "/status-key")
    public DataStatusKeyResponseDto statusKey(@RequestBody @Valid DataStatusKeyRequestDto dataStatusKeyRequestDto){
        KeyInformation keyInformation = keyUseCase.statusKey(StatusKeyMapper.KeyDtoToKey(dataStatusKeyRequestDto.getKey()));
        KeyInformationDto keyInformationDto = StatusKeyMapper.KeyInformationToKeyInformationDto(keyInformation);
        return StatusKeyMapper.toDataStatusKeyResponseDto(keyInformationDto);
    }

}
