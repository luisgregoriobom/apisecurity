package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class UserFormUpdate {


    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String phone;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String address;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String password;

    public User update(Long id, UserRepository userRepository) {
        User user = userRepository.getOne(id);
        user.setPhone(this.phone);
        user.setAddress(this.address);
        user.setPassword(this.password);
        return user;
    }

}
