package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String address;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String phone;

    public User convertToUser(UserForm userForm){
        return new User(userForm);
    }

}
