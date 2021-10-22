package br.com.develfoodspringweb.develfoodspringweb.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    //Configurações de Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/user").permitAll()
                .antMatchers(HttpMethod.GET, "/user/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

    @Autowired
    private AutenticationService autenticationService;

    //Configurações de Autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(autenticationService).passwordEncoder(new BCryptPasswordEncoder());

    }

    //Configurações de recursos estáticos (js, css, imagens)
    @Override
    public void configure(WebSecurity web) throws Exception {
    }

}
