package br.com.develfoodspringweb.develfoodspringweb.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request")
@Getter @Setter
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusRequest status = StatusRequest.EMPTY;
    private LocalDateTime dateRequest;
    @ManyToOne
    private Plate plate;

}
