package br.com.develfoodspringweb.develfoodspringweb.models;

import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
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
    @JsonIgnore
    private List<Plate> plate;



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

    /**
     * Constructor created just to manually add data into the database with configurar method that belong to InitialConfig class on configuration package
     * @param name
     * @param phone
     * @author: Thomas B.P.
     */
    public Restaurant(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

}


