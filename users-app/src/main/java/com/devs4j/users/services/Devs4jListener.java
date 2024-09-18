package com.devs4j.users.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Devs4jListener {
	
	// Habilitamos el logger en esta clase
	private static Logger log = LoggerFactory.getLogger(Devs4jListener.class);
	
	// Método para leer, o consumir, mensajes de Kafka
	// @KafkaListener es una anotación necesaria para leer, o consumir, mensajes de Kafka
	// Esta anotación recibe 2 parámetros o atributos obligatorios; el nombre del tópico o tópicos de Kafka que tiene que escuchar para leer o consumir los mensajes, y el identificar del grupo de consumo o consumer group
	@KafkaListener(topics ="devs4j-topic", groupId ="devs4j-group")
	public void listen(String message) {
		// Muestra en el log a modo de información(escribe en consola) cada mensaje recibido en este método
		log.info("Code to post the message in the audit api: {}", message);
		// Simulamos que el proceso que envía el mensaje recibido a la api de auditoría tarda 5 seg
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
