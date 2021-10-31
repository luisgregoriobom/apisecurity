package br.com.develfoodspringweb.develfoodspringweb.security;


import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService implements UserDetailsService {//Spring entenderá com essa classe é onde
                                                                    //contém a lógica de Autenticação
    private final UserRepository repository;

    @Override //Lógica de Autenticação, irá procurar o email no DB, caso não ache retorna uma exception
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);
        if(user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("Dados Inválidos");
    }



}
