package br.com.fiap.projetodima.exception;

public class LivroNotFoundException extends RuntimeException {
    public LivroNotFoundException(String name){
        super("Livro not found with name = " + name);
    }
}
