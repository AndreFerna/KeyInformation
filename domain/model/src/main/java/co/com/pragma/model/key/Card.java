package co.com.pragma.model.key;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    private String type;
    private String number;
}
