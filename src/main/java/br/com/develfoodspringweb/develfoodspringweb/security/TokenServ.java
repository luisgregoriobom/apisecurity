package br.com.develfoodspringweb.develfoodspringweb.security;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServ {

    //////////////////////////////////////////VALORES DO APPLICATION PROPERTIES/////////////////////////////////////////
    @Value("${develfoodspringweb.jwt.secret}")
    private String secret;

    @Value("${develfoodspringweb.jwt.expiration}")
    private String expiration;


    public String generateToken(Authentication authentication) { //nessa classe vai a API para fazer a CRIAÇÃO DO TOKEN

        Object resultadoAuthentication = authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
        if (resultadoAuthentication instanceof User) {
            User logged = (User) resultadoAuthentication;
            return Jwts.builder()
                    .setIssuer("API da Develfood")
                    .setSubject(logged.getId().toString())
                    .setIssuedAt(today)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .claim("type", "user")
                    .compact();
        }
        if (resultadoAuthentication instanceof Restaurant) {
            Restaurant restaurantLogged = (Restaurant) authentication.getPrincipal();
            return Jwts.builder()
                    .setIssuer("API da Develfood")
                    .setSubject(restaurantLogged.getId().toString())
                    .setIssuedAt(today)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .claim("type", "restaurant")
                    .compact();
        }
        throw new UsernameNotFoundException("Dados digitados não encontrados!");
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public Long getIdRestaurant(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public String getUserType(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return (String) claims.get("type");
    }
}
