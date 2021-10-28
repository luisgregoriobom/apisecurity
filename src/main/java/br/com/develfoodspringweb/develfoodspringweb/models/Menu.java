package br.com.develfoodspringweb.develfoodspringweb.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "menus")
@Data
public class Menu {

    private Long id;
    private Plate plate;
    private Restaurant restaurant;
    private Category category;

    public Menu(){}

    public Menu(Plate plate, Restaurant restaurant, Category category) {
        this.plate = plate;
        this.restaurant = restaurant;
        this.category = category;
    }
}
