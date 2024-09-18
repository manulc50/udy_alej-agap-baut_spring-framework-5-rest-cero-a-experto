package com.devs4j.users.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Esta clase entidad representa la tabla adicional requerida para la relación muchos a muchos entre la clase entidad User y la clase entidad Role

//Esta clase entidad se mapea con la tabla "users_roles" de la base de datos
@Entity
@Table(name = "users_roles")
public class UserInRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// No hace falta poner esta anotación si queremos que el nombre de la columna de la tabla correspondiente se llame igual que esta propiedad
	//@Column(name = "id")
	private Integer id;
	
	
	// Relación muchos a muchos entre la entidad User y la entidad Role
	// Respecto a esta entidad que representa la tabla adicional de esta relación muhcos a muchos, la relación es mucho a uno con respecto a la entidad User y también es muchoa a uno con respecto a la entidad Role
	
	// Llamamos "user_id" a la clave foránea o extranjera que viaja desde la entidad User y hace referencia a la propiedad "id" de esta clase entidad User
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName ="id")
	private User user;
	
	// Llamamos "role_id" a la clave foránea o extranjera que viaja desde la entidad Role y hace referencia a la propiedad "id" de esta clase entidad Role
	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName ="id")
	private Role role;
	

	public UserInRole() {
	}

	public UserInRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
