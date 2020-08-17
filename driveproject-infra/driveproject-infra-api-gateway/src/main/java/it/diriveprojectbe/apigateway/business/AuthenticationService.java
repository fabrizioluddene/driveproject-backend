package it.diriveprojectbe.apigateway.business;

import it.diriveprojectbe.apigateway.dto.JWTTokenResponse;
import it.diriveprojectbe.commons.dto.GenericResponseDto;
import it.diriveprojectbe.userservice.api.dto.PasswordDto;
import it.diriveprojectbe.userservice.api.dto.UserDto;
import it.diriveprojectbe.userservice.api.excpetion.NoUserFoundException;

public interface AuthenticationService {
    public JWTTokenResponse getJWTToken(PasswordDto userUsernamePasswordDto) throws NoUserFoundException;

    public GenericResponseDto addUser(UserDto userDto);

}
