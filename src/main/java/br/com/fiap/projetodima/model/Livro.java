package br.com.fiap.projetodima.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PD_LIVROS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Long id;

    @Column(name = "nm_livro")
    private String name;

    @Column(name = "ds_livro")
    private String description;

    @Column(name = "tp_livro")
    private String tipo;
}
