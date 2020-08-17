package it.diriveprojectbe.apigateway.business;

import it.diriveprojectbe.apigateway.dto.JWTTokenResponse;
import it.diriveprojectbe.apigateway.security.JwtTokenProvider;
import it.diriveprojectbe.commons.dto.GenericResponseDto;
import it.diriveprojectbe.commons.message.ApplicationCodeEnum;
import it.diriveprojectbe.userservice.api.dto.PasswordDto;
import it.diriveprojectbe.userservice.api.dto.UserDto;
import it.diriveprojectbe.userservice.api.excpetion.NoUserFoundException;
import it.diriveprojectbe.apigateway.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    @Autowired
    UserClient userClient;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public JWTTokenResponse getJWTToken(PasswordDto userUsernamePasswordDto) throws NoUserFoundException {
        ResponseEntity<UserDto> userDtoResponseEntity =userClient.getUserByUsernameAndPassword(userUsernamePasswordDto);
        JWTTokenResponse  jwtTokenResponse =  new JWTTokenResponse();
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        if (userDtoResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return jwtTokenProvider.createToken(userDtoResponseEntity.getBody());
        }else{
            switch (userDtoResponseEntity.getStatusCode()){
                case NOT_FOUND:
                    genericResponseDto.setDescription(ApplicationCodeEnum.USERNOTFOUND.getMessage());
                    genericResponseDto.setCode(ApplicationCodeEnum.USERNOTFOUND.getCode());
                    jwtTokenResponse.setError(genericResponseDto);
                    break;
                default:
                    genericResponseDto.setDescription(ApplicationCodeEnum.APPLICATIONERROR.getMessage());
                    genericResponseDto.setCode(ApplicationCodeEnum.APPLICATIONERROR.getCode());
                    jwtTokenResponse.setError(genericResponseDto);
                    break;

            }
        }
        return  jwtTokenResponse;

    }

    @Override
    public GenericResponseDto addUser(UserDto userDto) {
        return userClient.addUser(userDto).getBody();
    }
}
