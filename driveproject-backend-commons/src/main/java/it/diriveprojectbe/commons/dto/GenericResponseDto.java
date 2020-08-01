package it.diriveprojectbe.commons.dto;

import it.diriveprojectbe.commons.message.ApplicationCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseDto {
    private ApplicationCodeEnum code;
    private String description;
}
