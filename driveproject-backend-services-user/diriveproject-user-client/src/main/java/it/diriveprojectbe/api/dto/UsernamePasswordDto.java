package it.diriveprojectbe.api.dto;

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
public class UsernamePasswordDto {
    @NotBlank(message = "username.mandatory.field")
    private String username;
    @NotBlank(message = "password.mandatory.field")
    private String password;
}
