package it.diriveprojectbe.userservice.api.business;

import it.diriveprojectbe.userservice.api.dto.PasswordDto;
import it.diriveprojectbe.userservice.api.dto.UserDto;
import it.diriveprojectbe.userservice.api.dto.UsernameDto;
import it.diriveprojectbe.userservice.api.excpetion.NoUserFoundException;
import it.diriveprojectbe.userservice.api.repository.UserRepository;
import it.diriveprojectbe.commons.dto.GenericResponseDto;
import it.diriveprojectbe.commons.message.ApplicationCodeEnum;
import it.diriveprojectbe.commons.util.DriveProjectCommonsUtil;
import it.diriveprojectbe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements  UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public GenericResponseDto addUser(UserDto userDto) {
        GenericResponseDto responseDto = new GenericResponseDto();
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(DriveProjectCommonsUtil.ecodeString( userDto.getPassword()));

        userRepository.save(user);
        responseDto.setCode(ApplicationCodeEnum.SUCCESS.getCode());
        responseDto.setDescription("inserted successuful");
        return responseDto;
    }

    @Override
    public UserDto getUserByUsernameAndPassword(PasswordDto usernamePasswordDto) throws NoUserFoundException {
        String username =usernamePasswordDto.getUsername();
        String password =DriveProjectCommonsUtil.ecodeString( usernamePasswordDto.getPassword());
        User user =userRepository.getUserByUsernameAndPassword(username,password);;
        if (user ==null){
            throw new NoUserFoundException();
        }
        UserDto userResponse =new UserDto();
        userResponse.setFirstName(user.getFirstName());
        userResponse.setId(user.getId());
        userResponse.setLastName(user.getLastName());
        userResponse.setUsername(user.getUsername());


        return userResponse;
    }

    @Override
    public UserDto getUserByUsername(UsernameDto usernamePasswordDto) throws NoUserFoundException {
        String username =usernamePasswordDto.getUsername();

        User user =userRepository.getUserByUsername(username);
        if (user ==null){
            throw new NoUserFoundException();
        }
        UserDto userResponse =new UserDto();
        userResponse.setFirstName(user.getFirstName());
        userResponse.setId(user.getId());
        userResponse.setLastName(user.getLastName());
        userResponse.setUsername(user.getUsername());


        return userResponse;
    }

}
