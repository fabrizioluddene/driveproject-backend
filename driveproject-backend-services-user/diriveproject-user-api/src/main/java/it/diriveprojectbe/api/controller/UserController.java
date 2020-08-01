package it.diriveprojectbe.api.controller;

import it.diriveprojectbe.api.UserApi;
import it.diriveprojectbe.api.business.UserService;
import it.diriveprojectbe.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class UserController extends UserErrorController implements UserApi  {
    @Autowired
    private UserService userService;


    @Override
    public ResponseEntity<GenericResponseDto> addUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);
    }


}
