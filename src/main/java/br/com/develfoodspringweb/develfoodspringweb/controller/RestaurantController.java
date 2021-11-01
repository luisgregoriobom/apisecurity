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

        @GetMapping("/{id}")
        @Transactional
        public ResponseEntity<RestaurantDto> details(@PathVariable Long id) {
            Optional<Restaurant> restaurant = restaurantRepository.findById(id);
            if(restaurant.isPresent()) {
                return ResponseEntity.ok(new RestaurantDto(restaurant.get()));
            }

            return ResponseEntity.notFound().build();
     }

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














//    @Autowired
//    private RestaurantRepository restaurantRepository;
//
//    /**
//     * Function with GET method to do make a query with the name of the restaurant as parameter.
//     * @param nameRestaurant
//     * @return
//     * @author: Thomas B.P.
//     */
//    @GetMapping
//    public RestaurantDto getRestaurantByName(@RequestParam String nameRestaurant){
//        if(nameRestaurant == null){
//            return null;
//        }
//
//        Optional<Restaurant> opt = restaurantRepository.findByName(nameRestaurant);
//        if (!opt.isPresent()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    "Restaurant name not found");
//        }
//        return RestaurantDto.convertToRestaurantDto(opt.get());
//    }
//
//    /**
//     * Function with POST method to register new Restaurant while the function create the URI route and return the head HTTP location with the URL
//     * @param restaurantForm
//     * @param uriComponentsBuilder
//     * @return
//     * @author: Thomas B.P.
//     */
//    @PostMapping
//    public ResponseEntity<RestaurantDto> register(@RequestBody @Valid RestaurantForm restaurantForm,
//                                                  UriComponentsBuilder uriComponentsBuilder){
//        Restaurant restaurant = restaurantForm.convertToRestaurant(restaurantForm);
//        restaurantRepository.save(restaurant);
//
//        URI uri = uriComponentsBuilder.
//                path("{id}").
//                buildAndExpand(restaurant.getId()).
//                toUri();
//
//        return ResponseEntity.created(uri).body(new RestaurantDto(restaurant));
//    }

