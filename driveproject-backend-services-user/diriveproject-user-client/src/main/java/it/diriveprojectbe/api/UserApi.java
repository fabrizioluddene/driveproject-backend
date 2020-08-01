package it.diriveprojectbe.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.diriveprojectbe.api.dto.UserDto;
import it.diriveprojectbe.commons.dto.GenericResponseDto;
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
            method = RequestMethod.POST)
    ResponseEntity<GenericResponseDto> addUser(@Valid @RequestBody UserDto userDto);




}
