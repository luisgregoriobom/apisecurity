package br.com.develfoodspringweb.develfoodspringweb.controller;


import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RestaurantDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    /**
     * Method to list all database restaurants
     * @param name
     * @return
     * @author: Luis Gregorio
     */

    @GetMapping
    @Transactional
    public List<RestaurantDto> list(String name) {
        if (name == null) {
            List<Restaurant> restaurants = restaurantRepository.findAll();
            return RestaurantDto.convertToRestaurantDto(restaurants);
        } else {
            List<Restaurant> restaurants = restaurantRepository.findByName(name);
            return RestaurantDto.convertToRestaurantDto(restaurants);
        }
    }

    /**
     * Method to register a new restaurant in the database with encrypted password.
     * @param form
     * @param uriBuilder
     * @return
     * @author: Luis Gregorio
     */

        @PostMapping
        @Transactional
        public ResponseEntity<RestaurantDto> register(@RequestBody @Valid RestaurantForm form, UriComponentsBuilder uriBuilder) {
            Restaurant restaurant = form.converterToRestaurant(restaurantRepository);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(restaurant.getPassword());
            restaurant.setPassword(encodedPassword);
            restaurantRepository.save(restaurant);
            URI uri = uriBuilder.path("/api/restaurant/{id}").buildAndExpand(restaurant.getId()).toUri();
            return ResponseEntity.created(uri).body(new RestaurantDto(restaurant));
    }

    /**
     * Method to detail an already registered restaurant.
     * @param id
     * @return
     * @author: Luis Gregorio
     */

    @GetMapping("/{id}")
        @Transactional
        public ResponseEntity<RestaurantDto> details(@PathVariable Long id) {
            Optional<Restaurant> restaurant = restaurantRepository.findById(id);
            if(restaurant.isPresent()) {
                return ResponseEntity.ok(new RestaurantDto(restaurant.get()));
            }

            return ResponseEntity.notFound().build();
     }

    /**
     * Method for updating the data of an already registered restaurant.
     * @param id
     * @param form
     * @return
     * @author: Luis Gregorio
     */

        @PutMapping("/{id}")
        @Transactional
        public ResponseEntity<RestaurantDto> update(@PathVariable Long id, @RequestBody @Valid RestaurantFormUpdate form){
            Optional<Restaurant> opt = restaurantRepository.findById(id);
            if(opt.isPresent()) {
                Restaurant restaurant = form.update(id, restaurantRepository);
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(restaurant.getPassword());
                restaurant.setPassword(encodedPassword);
                return ResponseEntity.ok(new RestaurantDto(restaurant));
            }
            return ResponseEntity.notFound().build();
    }

    /**
     * Method to delete a restaurant from the database.
     * @param id
     * @return
     * @author: Luis Gregorio
     */
        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity<?> remove(@PathVariable Long id){
            Optional<Restaurant> opt = restaurantRepository.findById(id);
            if(opt.isPresent()) {
                restaurantRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }
}