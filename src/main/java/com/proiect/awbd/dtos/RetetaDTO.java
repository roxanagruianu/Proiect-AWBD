package com.proiect.awbd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RetetaDTO {
    private Long id;
    private String medicamente;
    private String instructiuni;
    private LocalDate dataEmitere;
    private Long programareId;
}
