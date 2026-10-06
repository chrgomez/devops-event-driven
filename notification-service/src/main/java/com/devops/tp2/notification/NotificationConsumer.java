package com.devops.tp2.notification;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author christian
 */
@Service
public class NotificationConsumer {

    private final RestTemplate restTemplate = new RestTemplate();

    @KafkaListener(topics = "tareas-topic", groupId = "notificacion-group")
    public void consume(String mensaje) {
        String webhookUrl = "https://hooks.slack.com/services/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";

        // Usamos un Map para que Jackson cree el JSON correctamente sin errores de escape
        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("text", "🚀 Nueva tarea creada: " + mensaje);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(payloadMap, headers);

        try {
            restTemplate.postForEntity(webhookUrl, request, String.class);
            System.out.println("Notificación enviada a Slack correctamente.");
        } catch (Exception e) {
            System.err.println("Error enviando a Slack: " + e.getMessage());
        }
    }

}
