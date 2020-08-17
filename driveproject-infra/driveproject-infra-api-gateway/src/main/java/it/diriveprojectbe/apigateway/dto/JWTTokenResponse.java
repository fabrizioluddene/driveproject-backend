package it.diriveprojectbe.apigateway.dto;

import it.diriveprojectbe.commons.dto.GenericResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTTokenResponse   {
    private GenericResponseDto error;
    private String token;
    private  String tokenType;
    private Date expirationDate;

}
