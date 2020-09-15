package it.diriveprojectbe.userservice.api.controller;

import it.diriveprojectbe.userservice.api.UserApi;
import it.diriveprojectbe.userservice.api.business.UserService;
import it.diriveprojectbe.project.api.dto.PasswordDto;
import it.diriveprojectbe.project.api.dto.UserDto;
import it.diriveprojectbe.project.api.dto.UsernameDto;
import it.diriveprojectbe.project.api.excpetion.NoUserFoundException;
import it.diriveprojectbe.commons.dto.GenericResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class UserController extends UserErrorController implements UserApi {
    @Autowired
    private UserService userService;


    @Override
    public ResponseEntity<GenericResponseDto> addUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> getUserByUsernameAndPassword(@Valid @RequestBody PasswordDto userUsernamePasswordDto) throws NoUserFoundException {
        UserDto userResponse = userService.getUserByUsernameAndPassword(userUsernamePasswordDto);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> getUserByUsername(@Valid UsernameDto userUsernamePasswordDto) throws NoUserFoundException {
        UserDto userResponse = userService.getUserByUsername(userUsernamePasswordDto);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }
}
