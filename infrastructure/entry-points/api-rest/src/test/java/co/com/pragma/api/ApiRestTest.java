package co.com.pragma.api;

import co.com.pragma.api.dto.*;
import co.com.pragma.model.key.Identification;
import co.com.pragma.model.key.KeyInformation;
import co.com.pragma.usecase.key.KeyUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiRest.class)
@ContextConfiguration(classes = {ApiRest.class})
class ApiRestTest {

    //ApiRest apiRest = new ApiRest();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeyUseCase keyUseCase;

    @MockBean
    private HealthEndpoint healthEndpoint;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void apiRestTest() {
        //var response = apiRest.commandName();
        //assertEquals("", response);
        //WebTestClient
    }
    
    @Test
    void healthShouldReturnOkWhenStatusIsUp() throws Exception {
        when(healthEndpoint.health()).thenReturn(Health.up().build());

        mockMvc.perform(head("/v1/key-management/key-information-api/health"))
                .andExpect(status().isOk());
    }

    @Test
    void listKeysShouldReturnValidResponse() throws Exception {

        IdentificationDto identification = new IdentificationDto("CC", "1193134338");
        CardDto cardDto = new CardDto("CUENTA_DE_AHORRO", "10234567890");
        CustomerInformationDto customerDto = new CustomerInformationDto(identification, cardDto);
        RequestInformationDto requestInformationDto = new RequestInformationDto("1234", "Principal");
        DataListKeysRequestDto requestDto = new DataListKeysRequestDto(requestInformationDto, customerDto);

        KeyInformation keyInfo = new KeyInformation("MSISDN", "3214508016", "ACTIVA", "2025-05-07T19:44:48.440109300", "10234567890", "1193134338"); // Rellenar con datos necesarios
        List<KeyInformation> list = new ArrayList<KeyInformation>();
        list.add(keyInfo);
        when(keyUseCase.listKeys(Mockito.any())).thenReturn(list);

        // Act & Assert
        mockMvc.perform(post("/v1/key-management/key-information-api/list-keys")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.keyList").exists()); // Ajustar a la estructura real del JSON
    }

    @Test
    void statusKeyShouldReturnValidResponse() throws Exception {
        RequestInformationDto requestInformationDto = new RequestInformationDto("1234", "Principal");
        KeyDto keyDto = new KeyDto("MSISDN", "3214508016");
        DataStatusKeyRequestDto requestDto = new DataStatusKeyRequestDto(requestInformationDto, keyDto);

        KeyInformation keyInfo = new KeyInformation("MSISDN", "3214508016", "ACTIVA", "2025-05-07T19:44:48.440109300", "10234567890", "1193134338");
        Mockito.when(keyUseCase.statusKey(Mockito.any())).thenReturn(keyInfo);

        // Act & Assert
        mockMvc.perform(post("/v1/key-management/key-information-api/status-key")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.keyInformation").exists()); // Ajustar según estructura del DTO
    }

}
