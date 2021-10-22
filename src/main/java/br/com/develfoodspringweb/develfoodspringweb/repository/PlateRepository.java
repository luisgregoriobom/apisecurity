package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlateRepository extends JpaRepository<Plate, Long> {

    Optional<Plate> findByName(String namePlate);

}
