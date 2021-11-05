package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PlateRepository extends JpaRepository<Plate, Long>{

    List<Plate> findByName(String name);


    //fazendo a query pela linguagem do springdata 'findByPedidoPreco' de um atributo de uma classe relacionada
    //nesse caso, a classe relacionada à Usuario é a Pedido e o atributo é preco (que vem da classe Pedido)
//    User findByName(String nameUser); NÃO UTILIZADO porque retorna retorna NullPointerException para parametro que não encontrado no banco



}
