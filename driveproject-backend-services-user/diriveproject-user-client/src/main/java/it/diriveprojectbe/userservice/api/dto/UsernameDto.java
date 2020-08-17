package it.diriveprojectbe.userservice.api.dto;

import it.diriveprojectbe.commons.dto.GenericResponseDto;
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
public class UsernameDto {

    GenericResponseDto error;

    @NotBlank(message = "username.mandatory.field")
    private String username;

}
