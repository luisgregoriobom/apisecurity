package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @RequestMapping("/api/user")
    @ResponseBody
    public List<UserDto> list() {
        User user = new User(name, cpf);

        return UserDto.converter(Arrays.asList(user, user, user);
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
