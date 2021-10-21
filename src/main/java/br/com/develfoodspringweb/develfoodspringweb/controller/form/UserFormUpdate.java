package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class UserFormUpdate {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String phone;
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User update(Long id, UserRepository userRepository) {
        User user = userRepository.getOne(id);
        user.setPhone(this.phone);
        user.setAddress(this.address);
        return user;
    }
}
