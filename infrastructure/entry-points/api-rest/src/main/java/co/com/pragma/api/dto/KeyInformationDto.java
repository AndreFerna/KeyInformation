package co.com.pragma.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class KeyInformationDto {
    private String type;
    private String value;
    private String status;
    private String creationDate;
}
