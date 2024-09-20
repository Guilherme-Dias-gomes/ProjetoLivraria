package br.com.fiap.projetodima.repositories;

import br.com.fiap.projetodima.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long>{
    Optional<Livro> findByName(String name);
}
