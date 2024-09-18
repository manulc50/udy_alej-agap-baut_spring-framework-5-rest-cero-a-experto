package com.devs4j.users.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// Esta clase entidad se mapea con la tabla "profiles" de la base de datos
@Entity
@Table(name = "profiles")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// No hace falta poner esta anotación si queremos que el nombre de la columna de la tabla correspondiente se llame igual que esta propiedad
	//@Column(name = "id")
	private Integer id;
	
	// Establecemos el nombre "first_name" para la columna de la tabla "profiles" que hace referencia a esta propiedad
	@Column(name = "first_name")
	private String firstName;
	
	// Establecemos el nombre "last_name" para la columna de la tabla "profiles" que hace referencia a esta propiedad
	@Column(name = "last_name")
	private String lastName;
	
	// Establecemos el nombre "birth_date" para la columna de la tabla "profiles" que hace referencia a esta propiedad
	@Column(name = "birth_date")
	private Date birthDate;
	
	// Relación uno a uno con la entidad User
	// Llamamos "user_id" a la clave foránea o extranjera y hace referencia a la propiedad con nombre "id" de la entidad User
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
