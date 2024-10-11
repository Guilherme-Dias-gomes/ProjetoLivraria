package br.com.fiap.projetodima.services;

import br.com.fiap.projetodima.exception.LivroNotFoundException;
import br.com.fiap.projetodima.model.Livro;
import br.com.fiap.projetodima.model.User;
import br.com.fiap.projetodima.repositories.LivroRepository;
import br.com.fiap.projetodima.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroServices {
    private final LivroRepository livroRepository;
    private final PasswordEncoder passwordEncoder;

    public LivroServices(LivroRepository livroRepository, PasswordEncoder passwordEncoder) {
        this.livroRepository = livroRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }

    public Livro getLivroByName(String name){
        return livroRepository.findByName(name).orElseThrow(
                () -> new LivroNotFoundException(name)
        );
    }

    public Livro PostLivro(Livro livro){
        return livroRepository.save(livro);
    }

    public void deleteLivro(Long id){
        livroRepository.findById(id).orElseThrow(
                () -> new LivroNotFoundException(id)
        );
        livroRepository.deleteById(id);
    }

    public Livro atualizarLivro(Livro livro, Long id){
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException("Livro não encontrado com id " + id));

        livroExistente.setName(livro.getName());
        livroExistente.setDescription(livro.getDescription());
        livroExistente.setTipo(livro.getTipo());

        return livroRepository.save(livroExistente);
    }
}
