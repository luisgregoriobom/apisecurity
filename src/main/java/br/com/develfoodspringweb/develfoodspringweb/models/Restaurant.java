package br.com.develfoodspringweb.develfoodspringweb.models;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantForm;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cnpj;
    private String login;
    private String password;
    private String email;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "restaurant")
    private List<Plate> plate;


    public Restaurant(){}


    public Restaurant(String name, String cnpj, String login, String password, String email, String address, String phone, List plate) {
        this.name = name;
        this.cnpj = cnpj;
        this.login = login;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.plate = plate;
    }

    public Restaurant(RestaurantForm restaurantForm){
        this.name = restaurantForm.getName();
        this.cnpj = restaurantForm.getCnpj();
        this.phone = restaurantForm.getPhone();
        this.address = restaurantForm.getAddress();
    }


}


