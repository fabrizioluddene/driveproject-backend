package it.diriveprojectbe.userservice.api.business;

import it.diriveprojectbe.project.api.dto.PasswordDto;
import it.diriveprojectbe.project.api.dto.UserDto;
import it.diriveprojectbe.project.api.dto.UsernameDto;
import it.diriveprojectbe.project.api.excpetion.NoUserFoundException;
import it.diriveprojectbe.commons.dto.GenericResponseDto;

public interface UserService {
    public GenericResponseDto addUser(UserDto userDto);
    public UserDto getUserByUsernameAndPassword(PasswordDto usernamePasswordDto) throws NoUserFoundException;
    public UserDto getUserByUsername(UsernameDto usernamePasswordDto) throws NoUserFoundException;
}
