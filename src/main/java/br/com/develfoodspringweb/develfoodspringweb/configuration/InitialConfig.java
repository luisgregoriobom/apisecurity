package br.com.develfoodspringweb.develfoodspringweb.configuration;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitialConfig {

    @Autowired
    private UserRepository userRepository;

    public void configurar(){
        User user = new User();
        user.setName("hm1");
        user.setEmail("teste");
        user.setPassword("hm23");
        userRepository.save(user);



    }

}
