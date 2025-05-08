package co.com.pragma.model.key;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class KeyInformation {
    private String type;
    private String value;
    private String status;
    private String creationDate;
    private String cardId;
    private String customerId;
}
