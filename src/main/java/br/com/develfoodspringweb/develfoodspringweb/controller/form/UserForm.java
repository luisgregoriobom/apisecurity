package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class UserForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String login;
    @NotNull @NotEmpty
    private String password;
    @NotNull @NotEmpty
    private String adress;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String phone;


    public User convertToUser() {
        return new User(name, login, password, adress, email, phone);
    }
}
