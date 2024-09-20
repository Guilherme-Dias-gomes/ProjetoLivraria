package br.com.fiap.projetodima.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PD_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "nm_name")
    private String name;

    @Column(name = "ds_email")
    private String email;

    @Column(name = "ds_password")
    private String password;
}
