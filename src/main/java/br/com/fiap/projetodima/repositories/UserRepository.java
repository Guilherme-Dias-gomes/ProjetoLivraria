package br.com.fiap.projetodima.repositories;

import br.com.fiap.projetodima.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
