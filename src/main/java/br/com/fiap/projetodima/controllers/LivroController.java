package br.com.fiap.projetodima.controllers;

import br.com.fiap.projetodima.dto.LivroDetailsDTO;
import br.com.fiap.projetodima.model.Livro;
import br.com.fiap.projetodima.services.LivroServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroServices livroServices;

    public LivroController(LivroServices livroServices) {
        this.livroServices = livroServices;
    }

    @GetMapping("/{name}")
    public LivroDetailsDTO getLivrosByname(@PathVariable String name) {
        return LivroDetailsDTO.fromLivro(livroServices.getLivroByName(name));
    }

    @GetMapping
    public List<LivroDetailsDTO> getTodosLivros() {
        return livroServices.getAllLivros().stream()
                .map(LivroDetailsDTO::fromLivro)
                .collect(Collectors.toList());
    }
}
