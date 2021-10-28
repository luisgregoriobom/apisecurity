package br.com.develfoodspringweb.develfoodspringweb.models;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String login;
    private String password;
    private String address;
    private String email;
    private String phone;
    @ManyToOne
    private UserRequest userRequest;

    public User() {
    }

    public User(String name, String cpf, String login, String password, String address, String email, String phone) {
        this.name = name;
        this.cpf = cpf;
        this.login = login;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public User(UserForm userForm){
        this.name = userForm.getName();
        this.address = userForm.getAddress();
        this.email = userForm.getEmail();
        this.phone = userForm.getPhone();
    }
}
