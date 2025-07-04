package com.proiect.awbd.data_model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "diagnostice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diagnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String detalii;

    private String codDiagnostic;

    private LocalDate data;

    @OneToOne
    @JoinColumn(name = "programare_id", unique = true)
    private Programare programare;
}
