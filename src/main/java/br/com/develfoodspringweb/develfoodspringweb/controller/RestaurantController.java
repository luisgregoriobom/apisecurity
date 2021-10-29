package br.com.develfoodspringweb.develfoodspringweb.controller;


import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RestaurantDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping // query de um restaurant
    public RestaurantDto getRestaurantByName(@RequestParam String nameRestaurant){
        if(nameRestaurant == null){
            return null;
        }

        Optional<Restaurant> opt = restaurantRepository.findByName(nameRestaurant);
        if (!opt.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Restaurant name not found");
        }
        return RestaurantDto.convertToRestaurantDto(opt.get());
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> register(@RequestBody @Valid RestaurantForm restaurantForm,
                                                  UriComponentsBuilder uriComponentsBuilder){
        Restaurant restaurant = restaurantForm.convertToRestaurant(restaurantForm);
        restaurantRepository.save(restaurant);

        URI uri = uriComponentsBuilder.
                path("{id}").
                buildAndExpand(restaurant.getId()).
                toUri();

        return ResponseEntity.created(uri).body(new RestaurantDto(restaurant));
    }
}
