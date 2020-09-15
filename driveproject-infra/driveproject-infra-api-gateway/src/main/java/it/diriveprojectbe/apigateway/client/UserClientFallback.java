package it.diriveprojectbe.apigateway.client;

import feign.hystrix.FallbackFactory;
import it.diriveprojectbe.project.api.dto.PasswordDto;
import it.diriveprojectbe.project.api.dto.UserDto;
import it.diriveprojectbe.project.api.dto.UsernameDto;
import it.diriveprojectbe.project.api.excpetion.NoUserFoundException;
import it.diriveprojectbe.commons.dto.GenericResponseDto;
import it.diriveprojectbe.commons.message.ApplicationCodeEnum;
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
                return setAddUserError(throwable);
            }

            @Override
            public ResponseEntity<UserDto> getUserByUsernameAndPassword(@Valid PasswordDto userUsernamePasswordDto) throws NoUserFoundException {
                return setUserError(throwable);
            }

            @Override
            public ResponseEntity<UserDto> getUserByUsername(@Valid UsernameDto userUsernamePasswordDto) throws NoUserFoundException {

                return setUserError(throwable);
            }
        };
    }

    private ResponseEntity<UserDto> setUserError(Throwable throwable) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        UserDto userDtoResponse = new UserDto();
        if (throwable.getMessage() != null && throwable.getMessage().contains("404")) {
            genericResponseDto.setCode(ApplicationCodeEnum.USERNOTFOUND.getCode());
            genericResponseDto.setDescription(ApplicationCodeEnum.USERNOTFOUND.getMessage());
            userDtoResponse.setError(genericResponseDto);
            return new ResponseEntity<>(userDtoResponse, HttpStatus.NOT_FOUND);
        } else {
            genericResponseDto.setCode(ApplicationCodeEnum.APPLICATIONERROR.getCode());
            genericResponseDto.setDescription(ApplicationCodeEnum.APPLICATIONERROR.getMessage());
            userDtoResponse.setError(genericResponseDto);
            return new ResponseEntity<>(userDtoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    private ResponseEntity<GenericResponseDto> setAddUserError(Throwable throwable) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        if (throwable.getMessage() != null && throwable.getMessage().contains("404")) {
            genericResponseDto.setCode(ApplicationCodeEnum.NOTFOUND.getCode());
            genericResponseDto.setDescription(ApplicationCodeEnum.NOTFOUND.getMessage());
            return new ResponseEntity<>(genericResponseDto, HttpStatus.NOT_FOUND);
        } else if (throwable.getMessage() != null && throwable.getMessage().contains("409")) {
            genericResponseDto.setCode(ApplicationCodeEnum.UNIQUECONSTRAINT.getCode());
            genericResponseDto.setDescription(ApplicationCodeEnum.UNIQUECONSTRAINT.getMessage());
            return new ResponseEntity<>(genericResponseDto, HttpStatus.CONFLICT);
        } else {
            genericResponseDto.setCode(ApplicationCodeEnum.APPLICATIONERROR.getCode());
            genericResponseDto.setDescription(ApplicationCodeEnum.APPLICATIONERROR.getMessage());
            return new ResponseEntity<>(genericResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
