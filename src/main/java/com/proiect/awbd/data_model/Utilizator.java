package com.proiect.awbd.data_model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "utilizatori")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username este obligatoriu")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Parola este obligatorie")
    private String password;

    //Un utilizator poate avea mai multe roluri
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilizatori_roluri",
            joinColumns = @JoinColumn(name = "utilizator_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roluri;
}
