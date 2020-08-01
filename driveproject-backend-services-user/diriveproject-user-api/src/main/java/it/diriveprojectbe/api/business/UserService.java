package it.diriveprojectbe.api.business;

import it.diriveprojectbe.api.dto.UserDto;

public interface UserService {
    public GenericResponseDto addUser(UserDto userDto);
}
