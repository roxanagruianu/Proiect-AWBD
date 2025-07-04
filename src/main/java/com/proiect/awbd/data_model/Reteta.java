package com.proiect.awbd.data_model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "retete")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reteta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String medicamente;

    @Column(length = 1000)
    private String instructiuni;

    private LocalDate dataEmitere;

    @OneToOne
    @JoinColumn(name = "programare_id", unique = true)
    private Programare programare;
}