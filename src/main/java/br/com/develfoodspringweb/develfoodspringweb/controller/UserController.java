package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RestaurantDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import br.com.develfoodspringweb.develfoodspringweb.service.UserService;
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

    private final UserService userService;


    /**
     * Function with GET method to do make a query with the name of the user as parameter.
     * @param nameUser
     * @return
     * @author: Thomas B.P.
     */
    @GetMapping
    public ResponseEntity<UserDto> getUserByName(@RequestParam String nameUser){
        if(nameUser == null){
            return null;
        }

        UserDto queryByName = userService.getUserByName(nameUser);
        if (queryByName == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User name not found");
        }
         return new ResponseEntity<>(queryByName, HttpStatus.OK);

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

        UserDto userToRegister = userService.register(userForm);
        if (userToRegister == null){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "User not created.");
        }
        if (userForm.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Error encrypting password.");
        }

        URI uri = uriBuilder
                .path("/api/user/{id}")
                .buildAndExpand(userToRegister.getId())
                .toUri();

       return ResponseEntity.created(uri).body(userToRegister);
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
            UserDto userDetail = userService.details(id);
            if(userDetail == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User Not Found");
            }
            return ResponseEntity.ok(userDetail);
        }

    /**
     * Method to update some information of a user exists in the database.
     * @param id
     * @param form
     * @return
     * @author: Luis Gregorio
     */
        @PutMapping("/{id}")
        @Transactional
        public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserFormUpdate form){
            UserDto userUpdate = userService.update(id, form);
            if(userUpdate == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Restaurant Not Found");
            }
            return ResponseEntity.ok(userUpdate);
        }

    /**
     * Method to delete a user from the database.
     * @param id
     * @return
     * @author: Luis Gregorio
     */
        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity<?> remove(@PathVariable Long id){
            UserDto userRemove = userService.remove(id);
            if(userRemove == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Restaurant Not Found");
            }
            return ResponseEntity.ok().build();
        }
}