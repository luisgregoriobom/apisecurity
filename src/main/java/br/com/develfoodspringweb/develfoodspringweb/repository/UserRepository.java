package br.com.develfoodspringweb.develfoodspringweb.repository;

import br.com.develfoodspringweb.develfoodspringweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * Function to search for a user by name.
     * @param userName
     * @return
     * @author: Luis Gregorio
     */

    List<User> findByName(String userName);

    /**
     * Function to search for a user by email.
     * @param email
     * @return
     * @author: Luis Gregorio
     */

    Optional<User> findByEmail(String email);
