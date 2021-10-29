package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

public class TokenDto {

    private final String token;
    private final String type;

    public TokenDto(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
