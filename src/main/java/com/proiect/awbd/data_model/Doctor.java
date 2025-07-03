package com.proiect.awbd.data_model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctori")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Numele este obligatoriu")
    private String nume;

    @NotBlank(message = "Prenumele este obligatoriu")
    private String prenume;

    @NotBlank(message = "Specializarea este obligatorie")
    private String specializare;

    @Email(message = "Email invalid")
    private String email;

    @Pattern(regexp = "\\+?[0-9]{9,15}", message = "Telefon invalid")
    private String telefon;

    // Un doctor poate avea mai multe programari
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Programare> programari;

    // Un doctor poate lucra in mai multe clinici
    @ManyToMany
    @JoinTable(
            name = "doctor_clinica",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "clinica_id")
    )
    private Set<Clinica> clinici;

    //Un doctor poate avea doar un utilizator
    @OneToOne
    @JoinColumn(name = "utilizator_id", unique = true)
    private Utilizator utilizator;

    // Un doctor poate avea mai multe recenzii
    @OneToMany(mappedBy = "doctor")
    private Set<Recenzie> recenzii;
}
