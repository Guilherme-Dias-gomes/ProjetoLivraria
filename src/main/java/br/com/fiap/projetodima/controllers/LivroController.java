package br.com.fiap.projetodima.controllers;

import br.com.fiap.projetodima.dto.LivroDetailsDTO;
import br.com.fiap.projetodima.model.Livro;
import br.com.fiap.projetodima.services.LivroServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody @Valid Livro livro) {
        Livro novoLivro = livroServices.PostLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarLivro(@PathVariable Long id) {
        livroServices.deleteLivro(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@RequestBody @Valid Livro livro, @PathVariable Long id) {
        Livro livroAtulizado = livroServices.atualizarLivro(livro, id);
        return ResponseEntity.ok(livroAtulizado);
    }

}
