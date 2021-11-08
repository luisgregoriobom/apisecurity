package br.com.develfoodspringweb.develfoodspringweb.service;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    /**
     * Function that make a query with the name of the user as parameter and check in the database if the name is present
     * @param nameUser
     * @return
     * @author: Thomas B.P.
     */
    public UserDto getUserByName(String nameUser){
        Optional<User> opt = userRepository.findByName(nameUser);
        if (!opt.isPresent()){
            return null;
        }
        return UserDto.convertToUserDto(opt.get());
    }

    /**
     * Function to register new User
     * @param userForm
     * @return
     * @author: Thomas B.P.
     */
    public UserDto register(UserForm userForm){
        User user = userForm.convertToUser(userForm);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userForm.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        if (user.getId() == null){
            return null;
        }
        return new UserDto(user);
    }
}
