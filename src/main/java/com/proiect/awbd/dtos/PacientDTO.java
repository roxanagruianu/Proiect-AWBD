package com.proiect.awbd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PacientDTO {
    private Long id;
    private String nume;
    private String prenume;
    private String email;
    private String telefon;
    private LocalDate dataNasterii;
}