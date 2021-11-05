package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Luis Gregorio.
 *
 * Interface created to find name of plates through the implemented methods.
 */
public interface PlateRepository extends JpaRepository<Plate, Long>{

    /**
     *Function to search for a plates by name.
     * @param name
     * @return
     */
    List<Plate> findByName(String name);
}
