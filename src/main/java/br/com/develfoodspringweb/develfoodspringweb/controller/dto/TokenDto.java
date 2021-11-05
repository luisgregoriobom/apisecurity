package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDto {
    private String token;
    private String tipo;


    public TokenDto(String token, String tipo) {
    this.token = token;
    this.tipo = tipo;
    }
}
