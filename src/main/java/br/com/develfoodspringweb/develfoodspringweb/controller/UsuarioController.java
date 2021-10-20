package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.models.UserRequest;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/users")
    public List<UserDto> listUsers(){
        User user = new User();
        return UserDto.convertToDto(Arrays.asList(user, user));

    }

//    @GetMapping
//    public List<UserDto> lista(String precoPedido) {
//        if(precoPedido == null){
//            List<User> users = usuarioRepository.findAll();
//            return UserDto.converter(users);
//        } else{
//            List<User> users = usuarioRepository.findByPedidoPreco(precoPedido); //query para filtrar o atributo -> findByNomeDoAtributo
//            return UserDto.converter(users);
//        }
//
//    }

//    @PostMapping
//    public void cadastrar(@RequestBody UsuarioForm form){
//        User user = form.converter();
//
//    }

}
