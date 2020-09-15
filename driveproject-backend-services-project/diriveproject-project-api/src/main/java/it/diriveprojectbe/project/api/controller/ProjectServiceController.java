package it.diriveprojectbe.project.api.controller;

import it.diriveprojectbe.commons.dto.GenericResponseDto;
import it.diriveprojectbe.project.api.ProjectServiceApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProjectServiceController implements ProjectServiceApi {
    @Override
    public ResponseEntity<GenericResponseDto> getAll(@RequestHeader(value = "x-auth-user", required = true) String userAuthId) {
        System.out.println(userAuthId);
        return new ResponseEntity<>(new GenericResponseDto(),HttpStatus.OK);
    }
}
