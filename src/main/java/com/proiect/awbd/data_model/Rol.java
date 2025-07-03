package com.proiect.awbd.data_model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "roluri")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nume;  // ex: "ROLE_ADMIN", "ROLE_USER"

    //Un rol poate apartine mai multor utilizatori
    @ManyToMany(mappedBy = "roluri")
    private Set<Utilizator> utilizatori;
}
