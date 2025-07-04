package com.proiect.awbd.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicaDTO {
    private Long id;
    private String nume;
    private String adresa;
    private String telefon;
}
