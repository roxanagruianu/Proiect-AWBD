package com.proiect.awbd.data_model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clinici")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Numele clinicii este obligatoriu")
    private String nume;

    @NotBlank(message = "Adresa este obligatorie")
    private String adresa;

    @Pattern(regexp = "\\+?[0-9]{9,15}", message = "Telefon invalid")
    private String telefon;

    //Mai multi doctori pot lucra intr-o clinica.
    @ManyToMany(mappedBy = "clinici")
    private Set<Doctor> doctori;

    //O clinica poate avea mai multe programari
    @OneToMany(mappedBy = "clinica", cascade = CascadeType.ALL)
    private List<Programare> programari;
}
