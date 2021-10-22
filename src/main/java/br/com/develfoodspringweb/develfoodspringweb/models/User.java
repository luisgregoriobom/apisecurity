package br.com.develfoodspringweb.develfoodspringweb.models;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login;
    private String password; //!!!!!
    private String adress;
    private String email;
    private String phone;
    @ManyToOne
    private UserRequest userRequest;

    public User() {
    }

    public User(String name, String login, String password, String adress, String email, String phone) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.adress = adress;
        this.email = email;
        this.phone = phone;
    }
}
