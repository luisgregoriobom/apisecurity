package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantNameRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findByName(String name);
}

