package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import org.springframework.data.domain.Page;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.address = user.getAddress();
    }

    public UserDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public static Page<UserDto> converter(Page<User> users) {
        return users.map(UserDto::new);
    }
}