package br.com.develfoodspringweb.develfoodspringweb.controller;


import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RestaurantDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.FilterForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import br.com.develfoodspringweb.develfoodspringweb.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantService restaurantService;

    /**
     * Function with GET method to do make a query with the name of the restaurant as parameter.
     * @param nameRestaurant
     * @return
     * @author: Thomas B.P.
     */
    @GetMapping
    public RestaurantDto getRestaurantByName(@RequestParam String nameRestaurant){
        if(nameRestaurant == null){
            return null;
        }

        RestaurantDto queryByName = restaurantService.getRestaurantByName(nameRestaurant);
        if (queryByName == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Restaurant name not found");
        }
        return queryByName;
    }

    /**
     * Function with POST method to register new Restaurant while the function create the URI route and return the head HTTP location with the URL
     * @param restaurantForm
     * @param uriComponentsBuilder
     * @return
     * @author: Thomas B.P.
     */
    @PostMapping
    public ResponseEntity<RestaurantDto> register(@RequestBody @Valid RestaurantForm restaurantForm,
                                                  UriComponentsBuilder uriComponentsBuilder){

        RestaurantDto restaurantToRegister = restaurantService.register(restaurantForm);

        URI uri = uriComponentsBuilder
                .path("{id}")
                .buildAndExpand(restaurantToRegister.getId())
                .toUri();

        return ResponseEntity.created(uri).body(restaurantToRegister);

    }

    /**
     * Function to filter by the name and or food type of the restaurant and return a list of the search pageable
     * @param filterForm
     * @param pageable
     * @return
     * @author: Thomas B.P.
     */
    @PostMapping("/filter")
    public ResponseEntity<List<RestaurantDto>> filter(@RequestBody  FilterForm filterForm,
                                                      Pageable pageable){

        List<RestaurantDto> listOfFilter = restaurantService.filter(filterForm, pageable);
        return new ResponseEntity<>(listOfFilter, HttpStatus.OK);

    }

}
