package br.com.develfoodspringweb.develfoodspringweb.controller;


import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RestaurantDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.FilterForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.RestaurantForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
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
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

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

        Optional<Restaurant> opt = restaurantRepository.findByName(nameRestaurant);
        if (!opt.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Restaurant name not found");
        }
        return RestaurantDto.convertToRestaurantDto(opt.get());
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
        Restaurant restaurant = restaurantForm.convertToRestaurant(restaurantForm);
        restaurantRepository.save(restaurant);

        URI uri = uriComponentsBuilder.
                path("{id}").
                buildAndExpand(restaurant.getId()).
                toUri();

        return ResponseEntity.created(uri).body(new RestaurantDto(restaurant));
    }

    @PostMapping("/list")
    public ResponseEntity<List<RestaurantDto>> list(@RequestBody  FilterForm filterForm, Pageable pageable){

        Pageable pageByFilter = PageRequest.of(filterForm.getSkip()
                ,filterForm.getTake()
                ,Sort.by(Sort.Direction.ASC, "id"));

        Page<Restaurant> restaurants = restaurantRepository.findAll(Specification
                .where(RestaurantRepository.filterByNameIgnoreCase(filterForm.getSearch().toLowerCase()))
                .or(RestaurantRepository.filterByFoodType(filterForm.getSearch().toLowerCase()))
                , pageByFilter);

        List<RestaurantDto> restaurantDtoList = new ArrayList<>();

        restaurants.stream().map(restaurant -> restaurantDtoList.add(new RestaurantDto(restaurant))).collect(Collectors.toList());
        return new ResponseEntity<>(restaurantDtoList, HttpStatus.OK);

    }

}
