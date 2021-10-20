package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    //fazendo a query pela linguagem do springdata 'findByPedidoPreco' de um atributo de uma classe relacionada
    //nesse caso, a classe relacionada à Usuario é a Pedido e o atributo é preco (que vem da classe Pedido)
//    List<User> findByPedidoPreco(String precoPedido);

}
