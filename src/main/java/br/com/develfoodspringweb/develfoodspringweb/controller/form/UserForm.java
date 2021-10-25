package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;


public class UserForm {

    @NotEmpty
    @NotNull
    @Length(min = 5)
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public User converter(UserRepository userRepository) {
    return new User(name, email, phone, address);
    }
}
