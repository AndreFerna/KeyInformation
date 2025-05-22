package co.com.pragma.model.key;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Identification {
    private String type;
    private String number;
}
