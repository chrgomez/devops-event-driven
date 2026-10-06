package com.devops.tp2.controller;

import com.devops.tp2.dto.TareaCreada;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final KafkaTemplate<String, TareaCreada> kafkaTemplate;

    public TareaController(KafkaTemplate<String, TareaCreada> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public String crearTarea(@RequestParam String nombre, @RequestParam String proyectoId) {
        String id = UUID.randomUUID().toString();
        TareaCreada evento = new TareaCreada(id, nombre, proyectoId);
        
        // Enviamos el evento al tópico "tareas-topic"
        kafkaTemplate.send("tareas-topic", id, evento);
        
        return "Tarea creada y evento enviado: " + id;
    }
}
