package com.endava.parkinglot.DTO.auth;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@GroupSequence({AuthenticationDTO.NotEmptyValidationGroup.class, AuthenticationDTO.PatternValidationGroup.class, AuthenticationDTO.class})
public class AuthenticationDTO {

    @NotBlank(message = "Email cannot be empty.", groups = NotEmptyValidationGroup.class)
    @Pattern(regexp = "^(?=.{5,320}$)(?=[a-zA-Z])[a-zA-Z0-9._!#$%&'*+/=?^`{|}~-]{1,64}@[a-zA-Z0-9.-]{1,255}\\.[a-zA-Z]{1,63}$", message="Invalid email. It should be like: 'example@email.com'.", groups = PatternValidationGroup.class)
    private String email;

    @NotBlank(message = "Invalid password.Must be 5-10 characters, including symbols, upper- and lower-case letters." +
            "Should contain at least one digit,one upper case and one symbol", groups = NotEmptyValidationGroup.class)
    @Pattern(regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\W_])(.{5,10})",
            message = "Invalid password. Must be 5-10 characters, including symbols, upper- and lower-case letters. " +
                    "Should contain at least one digit, one uppercase letter, and one symbol")
    private String password;

    public interface NotEmptyValidationGroup {
    }

    public interface PatternValidationGroup{
    }
}
