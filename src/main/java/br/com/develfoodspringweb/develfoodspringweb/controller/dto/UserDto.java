package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

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
     * @param user
     * @return
     * @author: Thomas B.P.
     */
    public static UserDto convertToUserDto(User user){
        return new UserDto(user);
    }


}