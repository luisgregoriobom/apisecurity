package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserRepository userRepository;

    /**
     * Method to list all database users.
     * @param userName
     * @return
     * @author: Luis Gregorio
     */

    @GetMapping
    @Transactional
    public List<UserDto> list(String userName) {
        if (userName == null) {
            List<User> users = userRepository.findAll();
            return UserDto.converter(users);
        } else {
            List<User> users = userRepository.findByName(userName);
            return UserDto.converter(users);
        }
    }

    /**
     * Method for registering a new user in the database.
     * @param userForm
     * @param uriBuilder
     * @return
     * @author: Luis Gregorio
     */

    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder){
        User user = userForm.convertToUser(userRepository);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));

    }

    /**
     *Method to detail information about a user that already exists in the database
     * @param id
     * @return
     * @author: Luis Gregorio
     */

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> details(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(new UserDto(user.get()));
        }

        return ResponseEntity.notFound().build();

    }

    /**
     * Method to update some information of a user exists in the database.
     * @param id
     * @param userFormUpdate
     * @return
     * @author: Luis Gregorio
     */

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserFormUpdate userFormUpdate) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            User user = userFormUpdate.update(id, userRepository);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            return ResponseEntity.ok(new UserDto(user));

        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Method to delete a user from the database.
     * @param id
     * @return
     * @author: Luis Gregorio
     */

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}