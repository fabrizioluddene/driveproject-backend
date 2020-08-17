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
public class UserDto extends PasswordDto {
    private Long id;
    @NotBlank(message = "firstName.mandatory.field")
    private String firstName;
    @NotBlank(message = "lastName.mandatory.field")
    private String lastName;
}
