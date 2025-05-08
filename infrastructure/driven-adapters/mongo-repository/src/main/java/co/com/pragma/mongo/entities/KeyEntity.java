package co.com.pragma.mongo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "key")
public class KeyEntity {
    @Id
    @Field("_id")
    private String value;
    private String type;
    private String status;
    private String creationDate;
    @Field("customer_id")
    private String customerId;
    @Field("card_id")
    private String cardId;
}
