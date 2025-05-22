package co.com.pragma.model.key;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerInformation {
    private Identification identification;
    private Card card;
}
