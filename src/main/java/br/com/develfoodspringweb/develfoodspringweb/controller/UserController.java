package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PasswordEncoder encoder;

    public UserController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public Page<UserDto> lista(@RequestParam(required = false) String userName,
                               @PageableDefault(sort = "id",
                                       direction = Sort.Direction.DESC,
                                       page = 0,
                                       size = 10) Pageable pageable) {
        if (userName == null) {
            Page<User> users = userRepository.findAll(pageable);
            return UserDto.converter(users);
        } else {
            Page<User> users = userRepository.findByName(userName, pageable);
            return UserDto.converter(users);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
        User user = form.converter(userRepository);
        userRepository.save(user);
        user.setPassword(encoder.encode(user.getPassword()));
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> details(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(new UserDto(user.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/passwordAuth")
    @Transactional
    public ResponseEntity<Boolean> passwordAuth(@RequestParam String email,
                                                @RequestParam String password) {
       Optional<User> optUser = userRepository.findByEmail(email);
        if(optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        User user = optUser.get();
        boolean valid  = encoder.matches(password, optUser.get().getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }

    @PutMapping("/passwordAuth")
    @Transactional
        public ResponseEntity<Boolean> passwordRefresh(@RequestParam String email,
                                                    @RequestParam String password) {
            Optional<User> optUser = userRepository.findByEmail(email);
            if(optUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
            }
            User user = optUser.get();
            boolean valid  = encoder.matches(password, optUser.get().getPassword());

            HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
            return ResponseEntity.status(status).body(valid);

        }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserFormUpdate form) {

        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = form.update(id, userRepository);
            return ResponseEntity.ok(new UserDto(user));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
