package co.com.pragma.model.key.config;

import lombok.*;

@Getter
@Setter
@Builder
public class Errors {

    private String code;
    private String detail;

}
