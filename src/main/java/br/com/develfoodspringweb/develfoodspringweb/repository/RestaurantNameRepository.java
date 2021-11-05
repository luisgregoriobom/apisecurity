package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Luis Gregorio.
 *
 * Interface created to find e-mail and names of restaurants through the implemented methods.
 */
public interface RestaurantNameRepository extends JpaRepository<Restaurant, Long> {

    /**
     * Function to convert Resdtaurant object for search names with id
     * @param id
     * @return
     */
    Optional<Restaurant> findById(Long id);
}

