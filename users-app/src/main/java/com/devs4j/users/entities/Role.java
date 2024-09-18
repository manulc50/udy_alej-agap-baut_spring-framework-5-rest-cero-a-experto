package com.devs4j.users.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Esta clase entidad se mapea con la tabla "addresses" de la base de datos
@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// No hace falta poner esta anotación si queremos que el nombre de la columna de la tabla correspondiente se llame igual que esta propiedad
	//@Column(name = "id")
	private Integer id;
	
	// No hace falta poner esta anotación si queremos que el nombre de la columna de la tabla correspondiente se llame igual que esta propiedad
	//@Column(name = "name")
	private String name;
	
	
	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
