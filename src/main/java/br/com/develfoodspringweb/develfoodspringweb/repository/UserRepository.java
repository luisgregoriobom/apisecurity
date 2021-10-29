package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByName(String nameUser);



}
