package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.UserRequest;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserDto> listUser(String nameUser){
        if(nameUser == null){
            List<User> user = userRepository.findAll();
            return UserDto.convertToDto(user);
        } else {
            List<User> user = userRepository.findByName("testeName");
            return UserDto.convertToDto(user);
        }

    }

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody UserForm userForm, UriComponentsBuilder uriBuilder){
       User user = userForm.convertToUser();
       userRepository.save(user);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
       return ResponseEntity.created(uri).body(new UserDto(user));
    }

}
