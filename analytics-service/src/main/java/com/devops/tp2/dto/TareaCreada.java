package com.devops.tp2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaCreada {
    private String id;
    private String nombre;
    private String proyectoId;
}
