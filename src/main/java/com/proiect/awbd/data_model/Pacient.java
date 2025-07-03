package com.proiect.awbd.data_model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "pacienti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Numele este obligatoriu")
    private String nume;

    @NotBlank(message = "Prenumele este obligatoriu")
    private String prenume;

    @Past(message = "Data nașterii trebuie să fie în trecut")
    private LocalDate dataNasterii;

    @Email(message = "Email invalid")
    private String email;

    @Pattern(regexp = "\\+?[0-9]{9,15}", message = "Telefon invalid")
    private String telefon;

    // Un pacient poate avea mai multe programari
    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL)
    private java.util.List<Programare> programari;

    //Un pacient poate avea doar un utilizator
    @OneToOne
    @JoinColumn(name = "utilizator_id", unique = true)
    private Utilizator utilizator;

    //Un pacient poate scrie mai multe recenzii
    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Recenzie> recenzii;
}

