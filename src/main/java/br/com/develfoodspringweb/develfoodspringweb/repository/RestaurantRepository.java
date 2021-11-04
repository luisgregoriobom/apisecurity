package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Luis Gregorio.
 *
 * Interface created to find e-mail and names of restaurants through the implemented methods.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    /**
     * Function to search for a restaurant by name.
     * @param restaurantName
     * @return
     * @author: Luis Gregorio
     */
    List<Restaurant> findByName(String restaurantName);

    /**
     *Function to search for a restaurant by email.
     * @param email
     * @return
     * @author: Luis Gregorio
     */
    Optional<Restaurant> findByEmail(String email);
}
