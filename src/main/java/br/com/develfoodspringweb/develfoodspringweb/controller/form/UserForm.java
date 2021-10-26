package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;

@Data
public class UserForm {

    @NotEmpty
    @NotNull
    @Length(min = 5)
    private String name;
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String password;
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String email;
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String phone;
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String address;

    public User converter(UserRepository userRepository) {
    return new User(name, password, email, phone, address);
    }
}
