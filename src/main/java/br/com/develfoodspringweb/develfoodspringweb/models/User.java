package br.com.develfoodspringweb.develfoodspringweb.models;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String login;
    private String password;
    private String address;
    private String email;
    private String phone;
    @OneToMany(mappedBy = "user")
    private List<UserRequest> userRequest;


    public User(String name, String cpf, String login, String password, String address, String email, String phone) {
        this.name = name;
        this.cpf = cpf;
        this.login = login;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public User(UserForm userForm){
        this.name = userForm.getName();
        this.address = userForm.getAddress();
        this.email = userForm.getEmail();
        this.phone = userForm.getPhone();
        this.address = userForm.getAddress();
    }
}
