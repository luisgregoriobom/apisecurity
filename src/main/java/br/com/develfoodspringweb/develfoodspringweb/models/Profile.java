package br.com.develfoodspringweb.develfoodspringweb.models;

////////////////////////////////////CLASSE PERFIL COM INFORMAÇÕES DO USUÁRIO///////////////////////////////////


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Profile implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //Devolver atributo que tem o nome do perfil
    @Override
    public String getAuthority() {
        return name;
    }
}
