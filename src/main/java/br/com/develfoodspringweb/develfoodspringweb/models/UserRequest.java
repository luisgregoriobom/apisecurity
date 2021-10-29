package br.com.develfoodspringweb.develfoodspringweb.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "request")
@Data
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusRequest status = StatusRequest.EMPTY;
    private LocalDateTime dateRequest;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "userRequest")
    private List<Plate> plate;

}
