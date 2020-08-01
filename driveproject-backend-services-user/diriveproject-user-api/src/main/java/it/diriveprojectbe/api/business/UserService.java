package it.diriveprojectbe.api.business;

import it.diriveprojectbe.api.dto.UserDto;
import it.diriveprojectbe.api.dto.UsernamePasswordDto;
import it.diriveprojectbe.api.excpetion.NoUserFoundException;
import it.diriveprojectbe.commons.dto.GenericResponseDto;

public interface UserService {
    public GenericResponseDto addUser(UserDto userDto);
    public UserDto getUserByUsernameAndPassword(UsernamePasswordDto usernamePasswordDto) throws NoUserFoundException;
}
