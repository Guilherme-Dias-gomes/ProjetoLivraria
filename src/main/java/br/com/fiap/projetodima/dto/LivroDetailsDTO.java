package br.com.fiap.projetodima.dto;

import br.com.fiap.projetodima.model.Livro;

public record LivroDetailsDTO(
        Long id,
        String name,
        String description,
        String tipo
) {
    public static LivroDetailsDTO fromLivro(Livro livro) {
        return new LivroDetailsDTO(
                livro.getId(),
                livro.getName(),
                livro.getDescription(),
                livro.getTipo()
        );
    }
}
