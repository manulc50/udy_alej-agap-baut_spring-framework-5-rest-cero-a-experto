package com.devs4j.users.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Esta clase entidad se mapea con la tabla "users" de la base de datos
@Entity
@Table(name = "users")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2176633223104804095L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// No hace falta poner esta anotación si queremos que el nombre de la columna de la tabla correspondiente se llame igual que esta propiedad
	//@Column(name = "id")
	private Integer id;
	
	// No hace falta poner esta anotación si queremos que el nombre de la columna de la tabla correspondiente se llame igual que esta propiedad
	//@Column(name = "username")
	private String username;
	
	// No hace falta poner esta anotación si queremos que el nombre de la columna de la tabla correspondiente se llame igual que esta propiedad
	//@Column(name = "password")
	private String password;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
