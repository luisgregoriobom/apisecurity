package br.com.develfoodspringweb.develfoodspringweb.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

//lombok

@Entity
@Table(name = "plate")
@Getter @Setter
public class Plate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private PlateCategory category;

    public Plate(String name, String description, BigDecimal price, PlateCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

}
