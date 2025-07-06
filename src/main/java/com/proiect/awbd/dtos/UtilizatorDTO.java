package com.proiect.awbd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UtilizatorDTO {
    private Long id;
    private String username;
    private String password;
    private Set<String> roluri;
}
