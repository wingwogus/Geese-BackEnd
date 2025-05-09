package goorm.geese.domain.dto;

import goorm.geese.domain.email.CustomEmail;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class SignUpRequest {
    @Valid
    @CustomEmail
    private String email;
    private String password;
    private String nickname;
    private String phone;
    private String code;
}
