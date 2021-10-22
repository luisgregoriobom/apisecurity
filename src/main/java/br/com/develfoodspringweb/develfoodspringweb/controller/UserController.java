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
         return UserDto.convertToDto(opt.get());

    }

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserForm userForm,
                                            UriComponentsBuilder uriBuilder){
       User user = userForm.convertToUser();
       userRepository.save(user);

        URI uri = uriBuilder.path("/user/{id}").
                buildAndExpand(user.getId()).
                toUri();

       return ResponseEntity.created(uri).body(new UserDto(user));
    }

}
