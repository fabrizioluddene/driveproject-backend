package it.diriveprojectbe.apigateway.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.diriveprojectbe.apigateway.dto.JWTTokenResponse;
import it.diriveprojectbe.commons.message.ApplicationCodeEnum;
import it.diriveprojectbe.userservice.api.dto.PasswordDto;
import it.diriveprojectbe.userservice.api.dto.UsernameDto;
import it.diriveprojectbe.userservice.api.excpetion.NoUserFoundException;
import it.diriveprojectbe.apigateway.business.JWTTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(value = "AuthenticationApi", description = "Authentication api")
@Controller
public class AuthenticationController  extends AuthenticationErrorController{

    @Autowired
    JWTTokenService jwtTokenService;

    @ApiOperation(value = "Authentication", nickname = "Authentication", notes = "get user", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status 200", response = String.class),
            @ApiResponse(code = 403, message = "Status 403", response = String.class)})
    @RequestMapping(value = "/auth/v1/authentication",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<JWTTokenResponse> getUserByUsernameAndPassword(@Valid @RequestBody PasswordDto userUsernamePasswordDto) throws NoUserFoundException {

        JWTTokenResponse jwtTokenResponse = jwtTokenService.getJWTToken(userUsernamePasswordDto);
        if (jwtTokenResponse.getError() != null){
            if (jwtTokenResponse.getError().getCode().equalsIgnoreCase(ApplicationCodeEnum.NOTFOUND.getCode())){
                return new ResponseEntity<>(jwtTokenResponse, HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(jwtTokenResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        return new ResponseEntity<>(jwtTokenResponse, HttpStatus.OK);
    }
}
