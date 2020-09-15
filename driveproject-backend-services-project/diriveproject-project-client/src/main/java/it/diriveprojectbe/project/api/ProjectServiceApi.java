package it.diriveprojectbe.project.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.diriveprojectbe.commons.dto.GenericResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Api(value = "ProjectServiceApi", description = "Project Service api")
public interface ProjectServiceApi {

    @ApiOperation(value = "allproject", nickname = "allproject", notes = "add user", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status 200", response = String.class),
            @ApiResponse(code = 403, message = "Status 403", response = String.class) })
    @RequestMapping(value = "/api/v1/project",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<GenericResponseDto> getAll(@RequestHeader(value = "x-auth-user", required = true) String userAuthId);







}
