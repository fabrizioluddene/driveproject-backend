package it.diriveprojectbe.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "api", description = "Prova")
public interface UserApi {




    @ApiOperation(value = "prova", nickname = "prova", notes = "prova", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status 200", response = String.class),
            @ApiResponse(code = 403, message = "Status 403", response = String.class) })
    @RequestMapping(value = "/api/v1/user/prova",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<String> prova();
}
