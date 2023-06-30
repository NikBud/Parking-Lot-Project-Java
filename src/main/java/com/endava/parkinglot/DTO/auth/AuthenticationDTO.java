package com.endava.parkinglot.DTO.auth;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
@GroupSequence({AuthenticationDTO.NotEmptyValidationGroup.class, AuthenticationDTO.PatternValidationGroup.class, AuthenticationDTO.SizeValidationGroup.class, AuthenticationDTO.EmailValidationGroup.class, AuthenticationDTO.class})
public class AuthenticationDTO {

    @NotBlank(message = "Email cannot be empty.", groups = NotEmptyValidationGroup.class)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message="Invalid email. It should be like: 'example@email.com'.", groups = PatternValidationGroup.class)
    @Size(min = 12, max = 35, message = "Size of the email should be between 12 and 35 characters.", groups = SizeValidationGroup.class)
    @Email(message = "Please enter a valid email address.", groups = EmailValidationGroup.class)
    private String email;

    @NotBlank(message = "Password cannot be empty.", groups = NotEmptyValidationGroup.class)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public interface NotEmptyValidationGroup {
    }

    public interface SizeValidationGroup {
    }

    public interface EmailValidationGroup{

    }

    public interface PatternValidationGroup{

    }
}
