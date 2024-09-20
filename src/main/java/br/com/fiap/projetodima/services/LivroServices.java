package br.com.fiap.projetodima.services;

import br.com.fiap.projetodima.exception.LivroNotFoundException;
import br.com.fiap.projetodima.model.Livro;
import br.com.fiap.projetodima.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroServices {
    private final LivroRepository livroRepository;

    public LivroServices(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }

    public Livro getLivroByName(String name){
        return livroRepository.findByName(name).orElseThrow(
                () -> new LivroNotFoundException(name)
        );
    }
}
