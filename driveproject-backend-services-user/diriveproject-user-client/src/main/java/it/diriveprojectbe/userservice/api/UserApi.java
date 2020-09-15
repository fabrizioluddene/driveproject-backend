package it.diriveprojectbe.userservice.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.diriveprojectbe.commons.dto.GenericResponseDto;
import it.diriveprojectbe.project.api.dto.UserDto;
import it.diriveprojectbe.project.api.dto.UsernameDto;
import it.diriveprojectbe.project.api.excpetion.NoUserFoundException;
import it.diriveprojectbe.project.api.dto.PasswordDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(value = "UserApi", description = "User api")
public interface UserApi {

    @ApiOperation(value = "AddUser", nickname = "addUser", notes = "add user", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status 200", response = String.class),
            @ApiResponse(code = 403, message = "Status 403", response = String.class) })
    @RequestMapping(value = "/api/v1/user",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<GenericResponseDto> addUser(@Valid @RequestBody UserDto userDto);

    @ApiOperation(value = "getUserByUsernameAndPassword", nickname = "getUserByUsernameAndPassword", notes = "get user", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status 200", response = String.class),
            @ApiResponse(code = 403, message = "Status 403", response = String.class) })
    @RequestMapping(value = "/api/v1/user-by-username-password",
            produces = { "application/json" },
            method = RequestMethod.POST)

    ResponseEntity<UserDto> getUserByUsernameAndPassword(@Valid @RequestBody PasswordDto userUsernamePasswordDto) throws NoUserFoundException;


    @ApiOperation(value = "getUserByUsername", nickname = "getUserByUsername", notes = "get user by ueser name", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status 200", response = String.class),
            @ApiResponse(code = 403, message = "Status 403", response = String.class) })
    @RequestMapping(value = "/api/v1/user-by-username",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<UserDto> getUserByUsername(@Valid @RequestBody UsernameDto userUsernamePasswordDto) throws NoUserFoundException;


}
