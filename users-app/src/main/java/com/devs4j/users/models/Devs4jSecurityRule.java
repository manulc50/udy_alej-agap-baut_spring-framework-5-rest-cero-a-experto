package com.devs4j.users.models;

import java.lang.annotation.Retention;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

// Anotación de seguridad propia
// Cuando una anotación utiliza otras anotaciones, esta anotación se convierte en una Meta anotación

@Retention(RUNTIME)
// Con la anotación "@PreAuthorize"(misma funcionalidad que las notaciones "@Secured" y "@RolesAllowed") indicamos que este método handler sólo puede ser ejecutado por un usuario autenticado que tenga el role "USER" o el role "ADMIN"
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // A diferencia de las anotaciones "@Secured" y "@RolesAllowed", esta anotación evalua expresiones de tipo SpEL
// La anotación "@PostAuthorize" se evalua al final de la ejecución de un método y permite ejecutar ese método(si no se indica la anotación "@PreAuthorize" o si se indica y el role del usuario está presente en esa anotación) al usuario autenticado pero únicamente devuelve la respuesta de ese método si el role de ese usuario está presente en esta anotación "@PostAuthorize" 
// En caso contrario, el usuario autenticado puede ejecutar el método pero no va a recibir la respuesta de ese método
// Al igual que la anotación "@PreAuthorize", esta anotación "@PostAuthorize" también evalua expresiones SpeEL 
@PostAuthorize("hasRole('ROLE_ADMIN')") // En este caso, sólo se devuelve la respuesta del método al usuario autenticado que tenga el role ADMIN
//Para usar alguna de estas anotaciones, siempre se tiene que especificar el prefijo "ROLE_" delate del nombre del role
public @interface Devs4jSecurityRule {

}
