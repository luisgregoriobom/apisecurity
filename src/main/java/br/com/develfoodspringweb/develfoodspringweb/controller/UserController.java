package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Function with GET method to do make a query with the name of the user as parameter.
     * @param nameUser
     * @return
     * @author: Thomas B.P.
     */
    @GetMapping
    public UserDto getUserByName(@RequestParam String nameUser){
        if(nameUser == null){ //validando o param da query
            return null;
        }

        Optional<User> opt = userRepository.findByName(nameUser);
        if (!opt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User name not found");
        }
         return UserDto.convertToUserDto(opt.get());

    }

    /**
     * Function with POST method to register new User while the function create the URI route and return the head HTTP location with the URL
     * @param userForm
     * @param uriBuilder
     * @return
     * @author: Thomas B.P.
     */
    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserForm userForm,
                                            UriComponentsBuilder uriBuilder){
       User user = userForm.convertToUser(userForm);
       userRepository.save(user);

        URI uri = uriBuilder.
                path("/{id}").
                buildAndExpand(user.getId()).
                toUri();

       return ResponseEntity.created(uri).body(new UserDto(user));
    }

}
