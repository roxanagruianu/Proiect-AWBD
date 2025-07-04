package com.proiect.awbd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecenzieDTO {
    private Long id;
    private Long pacientId;
    private Long doctorId;
    private String comentariu;
    private int rating;
    private LocalDateTime dataRecenzie;
}
