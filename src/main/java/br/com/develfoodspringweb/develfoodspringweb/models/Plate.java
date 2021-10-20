package br.com.develfoodspringweb.develfoodspringweb.models;


import javax.persistence.*;

@Entity
@Table(name = "plates")
public class Plate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int price;
    @Enumerated(EnumType.STRING)
    private PlateCategory category;

    public Plate(String name, String description, int price, PlateCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Enum getCategory() {
        return category;
    }

    public void setCategory(PlateCategory category) {
        this.category = category;
    }
}
