package com.proiect.awbd.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDTO {
    private Long id;
    private String nume;
    private String prenume;
    private String specializare;
    private String email;
    private String telefon;
}
