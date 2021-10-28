package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.UserRequest;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String cpf;
    private String address;
    private String email;
    private String phone;
    private List<UserRequest> userRequest;


    public UserDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.phone = user.getPhone();

    }

//    Método de conversão de Usuario para UsuarioDto sem retornar em Lista
    public static UserDto convertToUserDto(User user){
        return new UserDto(user);
    }

//    Conversão User pra UserDto em LISTA
//    public static List<UserDto> convertToDto(List<User> users) {
//        return users.stream().map(UserDto::new).collect(Collectors.toList());
//    }

}
