package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class LoginForm { //informações do login para autenticar e gerar o token

    private String email;
    private String password;

    public UsernamePasswordAuthenticationToken converter() {
    return new UsernamePasswordAuthenticationToken(email, password);
    }
}
