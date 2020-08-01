package it.diriveprojectbe.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UserDto {
    private Long id;
    @NotBlank(message = "username.mandatory.field")
    private String username;
    @NotBlank(message = "password.mandatory.field")
    private String password;
    @NotBlank(message = "firstName.mandatory.field")
    private String firstName;
    @NotBlank(message = "lastName.mandatory.field")
    private String lastName;
}
