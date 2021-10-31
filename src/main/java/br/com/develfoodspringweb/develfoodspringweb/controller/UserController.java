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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping
    public List<UserDto> list(String userName) {
        if (userName == null) {
            List<User> users = userRepository.findAll();
            return UserDto.converter(users);
        } else {
            List<User> users = userRepository.findByName(userName);
            return UserDto.converter(users);
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder){
        User user = userForm.convertToUser(userForm);
        userRepository.save(user);

        URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> details(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(new UserDto(user.get()));
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserFormUpdate userFormUpdate) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            User user = userFormUpdate.update(id, userRepository);
            return ResponseEntity.ok(new UserDto(user));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}














//    @Autowired
//    private UserRepository userRepository;
//
//    /**
//     * Function with GET method to do make a query with the name of the user as parameter.
//     * @param nameUser
//     * @return
//     * @author: Thomas B.P.
//     */
//    @GetMapping
//    public UserDto getUserByName(@RequestParam String nameUser){
//        if(nameUser == null){
//            return null;
//        }
//
//        Optional<User> opt = userRepository.findByName(nameUser);
//        if (!opt.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    "User name not found");
//        }
//         return UserDto.convertToUserDto(opt.get());
//
//    }
////teste
//    /**
//     * Function with POST method to register new User while the function create the URI route and return the head HTTP location with the URL
//     * @param userForm
//     * @param uriBuilder
//     * @return
//     * @author: Thomas B.P.
//     */
//    @PostMapping
//    public ResponseEntity<UserDto> register(@RequestBody @Valid UserForm userForm,
//                                            UriComponentsBuilder uriBuilder){
//       User user = userForm.convertToUser(userForm);
//       userRepository.save(user);
//
//        URI uri = uriBuilder.
//                path("/{id}").
//                buildAndExpand(user.getId()).
//                toUri();
//
//       return ResponseEntity.created(uri).body(new UserDto(user));
//    }
//
//}
