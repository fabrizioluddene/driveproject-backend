package it.diriveprojectbe.userservice.api.business;

import it.diriveprojectbe.userservice.api.dto.PasswordDto;
import it.diriveprojectbe.userservice.api.dto.UserDto;
import it.diriveprojectbe.userservice.api.dto.UsernameDto;
import it.diriveprojectbe.userservice.api.excpetion.NoUserFoundException;
import it.diriveprojectbe.commons.dto.GenericResponseDto;

public interface UserService {
    public GenericResponseDto addUser(UserDto userDto);
    public UserDto getUserByUsernameAndPassword(PasswordDto usernamePasswordDto) throws NoUserFoundException;
    public UserDto getUserByUsername(UsernameDto usernamePasswordDto) throws NoUserFoundException;
}
