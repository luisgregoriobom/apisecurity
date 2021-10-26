package br.com.develfoodspringweb.develfoodspringweb.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String phone;
    @ManyToOne
    private Plate plate;

    public Restaurant(){}


    public Restaurant(String name, String login, String password, String email, String phone) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }


}


