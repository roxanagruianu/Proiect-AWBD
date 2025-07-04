package com.proiect.awbd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticDTO {
    private Long id;
    private String detalii;
    private String codDiagnostic;
    private LocalDate data;
    private Long programareId;
}
