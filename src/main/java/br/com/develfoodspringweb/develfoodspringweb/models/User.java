package br.com.develfoodspringweb.develfoodspringweb.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login;
    private String email;
    private String password;
    private String phone;
    private String address;
    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profile = new ArrayList<>();

    public User() {
    }

    public User(String name, String password, String email, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profile;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
