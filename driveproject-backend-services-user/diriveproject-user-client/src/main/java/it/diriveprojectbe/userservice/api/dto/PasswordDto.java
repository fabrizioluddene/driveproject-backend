package it.diriveprojectbe.userservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PasswordDto extends  UsernameDto{
    @NotBlank(message = "password.mandatory.field")
    private String password;
}
