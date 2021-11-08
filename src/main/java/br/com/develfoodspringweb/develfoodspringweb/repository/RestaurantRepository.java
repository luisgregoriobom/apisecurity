package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {

    /**
     * Function to search for a restaurant by name.
     * @param restaurantName
     * @return
     * @author: Luis Gregorio
     */
    Optional<Restaurant> findByName(String restaurantName);

    public static Specification<Restaurant> filterByName(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name + "%");
    }

    public static Specification<Restaurant> filterByFoodType(String foodType){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("foodType")), "%" + foodType + "%");
    }

    /**
     *Function to search for a restaurant by email.
     * @param email
     * @return
     * @author: Luis Gregorio
     */
    Optional<Restaurant> findByEmail(String email);

    Optional<Restaurant> findById(Long id);
}
