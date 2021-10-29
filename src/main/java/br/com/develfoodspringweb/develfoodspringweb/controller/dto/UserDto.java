package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.UserRequest;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String cpf;
    private String address;
    private String email;
    private String phone;
    private List<UserRequest> userRequest;


    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.address = user.getAddress();
    }

    /**
     * Function to convert the object Model class received into a DTO Object class
     * @param users
     * @return
     * @author: Thomas B.P.
     */
//    public static UserDto convertToUserDto(User user){
//        return new UserDto(user);
//    }

    public static List<UserDto> converter(List<User> users) {
    return users.stream().map(UserDto::new).collect(Collectors.toList());
    }
}