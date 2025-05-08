package co.com.pragma.model.key.config;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SA400(400, "Bad Request", "SA400", "Petición mal formada"),
    SA401(401, "Unauthorized", "SA401", "Id o secreto de cliente no válido"),
    BP409(409, "Conflict", "BP409", "No se encontro información de la llave"),
    SP500(500, "Internal Server Error", "SP500", "Ha ocurrido un error en el servicio"),
    SP502(502, "Bad Gateway", "SP502", "El mensaje del servidor es inválido"),
    SP503(503, "Service Unavailable", "SP503", "Servicio no disponible"),
    SP504(504, "Gateway Timeout", "SP504", "El servicio no respondió en el tiempo esperado");

    private final int status;
    private final String title;
    private final String code;
    private final String detail;

    ErrorCode(int status, String title, String code, String detail) {
        this.status = status;
        this.title = title;
        this.code = code;
        this.detail = detail;
    }

}
