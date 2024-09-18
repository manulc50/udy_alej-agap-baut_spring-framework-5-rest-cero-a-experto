package com.devs4j.users.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class Devs4jKafkaConfiguration {
	
	// Método que crea y devuelve un HashMap con las configuraciones del consumidor de Kafka
	private Map<String, Object> consumerProperties(){
		Map<String, Object> props = new HashMap<>();
		// Broker de Kafka al que nos vamos a conectar para consumir mensajes
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		// Identificador de nuestro grupo de consumo o consumer group
		props.put(ConsumerConfig.GROUP_ID_CONFIG,"devs4j-group");
		// Para que de forma automática se haga commit a los mensajes que se van leyendo o consumiendo
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
		// Tiempo en milisegundos para realizar cada commit
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"100");
		// Tiempo de vida de la sesión del consumidor en ms
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"15000");
		// Indicamos que la clave, o key, del mensaje se deserializa y se consume como un Integer
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,IntegerDeserializer.class);
		// Indicamos que el valor del mensaje se deserializa y se consume como un String
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		return props;
	}
	
	// Método que crea y devuelve un HashMap con las configuraciones del productor de Kafka
	private Map<String, Object> producerProperties(){
		Map<String, Object> props = new HashMap<>();
		// Broker de Kafka al que nos vamos a conectar para enviar mensajes
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		// No hay reintentos de envío de mensajes en caso de envíos fallidos
		props.put(ProducerConfig.RETRIES_CONFIG,0);
		// En lugar de enviar un mensaje tras otro, enviamos bloques, o batches, de mensajes de un tamaño determinado al mismo tiempo
		props.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
		// Los bloques, o batchs, de mensajes se enviarán cada 1 ms
		props.put(ProducerConfig.LINGER_MS_CONFIG,1);
		// Este buffer almacena bloques, o batches, de mensajes en memoria que esperan ser enviados(cada bloque, o batch, es enviado cada LINGER_MS_CONFIG ms) y en esta propiedad se configura el tamaño de ese buffer
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
		// Indicamos que la clave, o key, del mensaje se serializa y se envía como un Integer
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,IntegerSerializer.class);
		// Indicamos que el valor del mensaje se serializa y se envía como un String
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		return props;
	}
	
	// Método que devuelve la implementación de un ConsumerFactory como un bean de Spring
	// En este caso, la implementación del ConsumerFactory es de tipo DefaultKafkaConsumerFactory que se crea a partir de las configuraciones del condumidor de Kafka establecidas en el método anterior "consumerProperties"
	// Para la creación de un ConsumerFactory, es necesario indicar el tipo de la clave, o key, de los mensajes y el tipo del valor de los mensajes que se van a consumir. En este caso, la clave, o key, es de tipo Integer y el valor de los mensaje es de tipo String
	@Bean
	public ConsumerFactory<Integer, String> consumerFactory(){
		return new DefaultKafkaConsumerFactory<Integer, String>(consumerProperties());
	}
	
	// Método que crea y devuelve un listener, para el consumo de mensajes desde Kafka, de tipo ConcurrentKafkaListenerContainerFactory como un bean de Spring
	// Para la creación de este listener, establecemos el ConsumerFactory creado en el método anterior "consumerFactory"
	// Para la creación de este listener, es necesario indicar el tipo de la clave, o key, de los mensajes y el tipo del valor de los mensajes que se van a consumir. En este caso, la clave, o key, es de tipo Integer y el valor de los mensaje es de tipo String
	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, String> listenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<Integer, String> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<Integer, String>();
		listenerContainerFactory.setConsumerFactory(consumerFactory());
		return listenerContainerFactory;
	}
	
	// Método que crea y devuelve un KafkaTemplate, para el envío de mensajes a Kafka, a partir de un DefaultKafkaProducerFactory, como un bean de Spring
	// Tanto para la creación del KafkaTemplate como para la creación del DefaultKafkaProducerFactory, hay que indicar el tipo de la clave, o key, de los mensajes y el tipo de valor de los mensajes que se van a enviar a Kafka. En este caso, la clave, o key, es de tipo Integer y el valor de los mensaje es de tipo String
	@Bean
	public KafkaTemplate<Integer, String> kafkaTemplate(){
		DefaultKafkaProducerFactory<Integer, String> producerFactory = new DefaultKafkaProducerFactory<Integer, String>(producerProperties());
		KafkaTemplate<Integer, String> template = new KafkaTemplate<Integer, String>(producerFactory);
		return template;
	}

}
