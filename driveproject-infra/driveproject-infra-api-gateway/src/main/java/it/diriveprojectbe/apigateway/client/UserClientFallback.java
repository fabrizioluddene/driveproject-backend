package it.diriveprojectbe.apigateway.client;

import feign.hystrix.FallbackFactory;
import it.diriveprojectbe.userservice.api.dto.PasswordDto;
import it.diriveprojectbe.userservice.api.dto.UserDto;
import it.diriveprojectbe.userservice.api.dto.UsernameDto;
import it.diriveprojectbe.userservice.api.excpetion.NoUserFoundException;
import it.diriveprojectbe.commons.dto.GenericResponseDto;
import it.diriveprojectbe.commons.message.ApplicationCodeEnum;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.ELState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class UserClientFallback implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public ResponseEntity<GenericResponseDto> addUser(@Valid UserDto userDto) {
                return null;
            }

            @Override
            public ResponseEntity<UserDto> getUserByUsernameAndPassword(@Valid PasswordDto userUsernamePasswordDto) throws NoUserFoundException {
                return setError(throwable);
            }

            @Override
            public ResponseEntity<UserDto> getUserByUsername(@Valid UsernameDto userUsernamePasswordDto) throws NoUserFoundException {

                return setError(throwable);
            }
        };
    }
    private ResponseEntity<UserDto> setError(Throwable throwable){
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        UserDto userDtoResponse = new UserDto();
        if (throwable.getMessage() != null && throwable.getMessage().contains("404")) {
            genericResponseDto.setCode(ApplicationCodeEnum.NOTFOUND.getCode());
            genericResponseDto.setDescription(ApplicationCodeEnum.NOTFOUND.getMessage());
            userDtoResponse.setError(genericResponseDto);
            return new ResponseEntity<>(userDtoResponse, HttpStatus.NOT_FOUND);
        }else{
            genericResponseDto.setCode(ApplicationCodeEnum.APPLICATIONERROR.getCode());
            genericResponseDto.setDescription(ApplicationCodeEnum.APPLICATIONERROR.getMessage());
            userDtoResponse.setError(genericResponseDto);
            return new ResponseEntity<>(userDtoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
