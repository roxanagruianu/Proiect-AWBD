package com.proiect.awbd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramareDTO {
    private Long id;
    private LocalDateTime dataOra;
    private Long pacientId;
    private Long doctorId;
    private Long clinicaId;
    private String observatii;
}
