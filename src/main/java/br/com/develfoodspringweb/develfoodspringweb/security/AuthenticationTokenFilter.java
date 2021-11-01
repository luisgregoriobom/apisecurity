package br.com.develfoodspringweb.develfoodspringweb.security;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
    //AUTENTICAÇÃO VIA TOKEN, CLASSE IRÁ INTERCEPTAR A REQUISIÇÃO DO TOKEN PARA LIBERAR ACESSO A TAL USUÁRIO
    //REALIZAR ALGO RESTRITO NO SISTEMA.

    private TokenServ tokenServs;
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    public AuthenticationTokenFilter(TokenServ tokenServs, UserRepository userRepository, RestaurantRepository restaurantRepository) { //precisa de construtor pois classe filter nao faz injeção
        this.tokenServs = tokenServs;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = recoveryToken(request);
        boolean valid = tokenServs.isTokenValid(token);
        if(valid) {
            String userType = tokenServs.getUserType(token);
            System.out.println(userType);
            if(userType.equals("user")) {
                System.out.println("luis1");
                authenticateCliente(token);
            } else if(userType.equals("restaurant")) {
                authenticateRestaurant(token);
            }
        }
        filterChain.doFilter(request, response);

    }

    private void authenticateCliente(String token) {

        System.out.println("luis2");
        Long idUser = tokenServs.getIdUser(token);
        User users = userRepository.getById(idUser);
        System.out.println(users.getId());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(users, null, users.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private void authenticateRestaurant(String token){
        Long idRestaurant = tokenServs.getIdRestaurant(token);
        Restaurant restaurant = restaurantRepository.getById(idRestaurant);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(restaurant, null, restaurant.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private String recoveryToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
