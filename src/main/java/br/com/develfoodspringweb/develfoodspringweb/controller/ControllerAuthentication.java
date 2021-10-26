package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.TokenDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.LoginForm;
import br.com.develfoodspringweb.develfoodspringweb.security.TokenServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class ControllerAuthentication {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServ tokenServ;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken LoginDetails = form.converter();

        try {
            Authentication authentication = authenticationManager.authenticate(LoginDetails);
            String token = tokenServ.generateToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch(AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }


}
