package com.proiect.awbd.data_model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "programari")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Programare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Future(message = "Data programării trebuie să fie în viitor")
    private LocalDateTime dataOra;

    //O programare este facuta de pacient / Un pacient poate avea mai multe programari
    @ManyToOne
    @JoinColumn(name = "pacient_id", nullable = false)
    private Pacient pacient;

    //Un doctor poate avea mai multe programari
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    //O clinica poate avea mai multe programari
    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

    @Size(max = 500)
    private String observatii;

    @OneToOne(mappedBy = "programare", cascade = CascadeType.ALL)
    private Diagnostic diagnostic;

    @OneToOne(mappedBy = "programare", cascade = CascadeType.ALL)
    private Reteta reteta;
}
