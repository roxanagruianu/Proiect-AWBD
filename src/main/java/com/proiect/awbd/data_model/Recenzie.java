package com.proiect.awbd.data_model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recenzii")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recenzie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pacient_id", nullable = false)
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @NotBlank(message = "Comentariul nu poate fi gol")
    @Size(max = 1000)
    private String comentariu;

    @Min(1)
    @Max(5)
    private int rating;

    private LocalDateTime dataRecenzie;
}
