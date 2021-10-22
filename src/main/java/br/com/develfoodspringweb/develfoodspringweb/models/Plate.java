package br.com.develfoodspringweb.develfoodspringweb.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

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

    public Plate() {
    }


    public Plate(String name, String description) {

    }
}
