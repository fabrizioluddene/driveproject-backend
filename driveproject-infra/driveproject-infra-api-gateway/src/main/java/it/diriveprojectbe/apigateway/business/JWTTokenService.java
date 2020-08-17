package it.diriveprojectbe.apigateway.business;

import it.diriveprojectbe.apigateway.dto.JWTTokenResponse;
import it.diriveprojectbe.userservice.api.dto.PasswordDto;
import it.diriveprojectbe.userservice.api.excpetion.NoUserFoundException;

public interface JWTTokenService {
    public JWTTokenResponse getJWTToken(PasswordDto userUsernamePasswordDto) throws NoUserFoundException;

}
