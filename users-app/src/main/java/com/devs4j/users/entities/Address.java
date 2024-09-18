package com.devs4j.users.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Esta clase entidad se mapea con la tabla "addresses" de la base de datos
@Entity
@Table(name = "addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// No hace falta poner esta anotación si queremos que el nombre de la columna de la tabla correspondiente se llame igual que esta propiedad
	//@Column(name = "id")
	private Integer id;
	
	// No hace falta poner esta anotación si queremos que el nombre de la columna de la tabla correspondiente se llame igual que esta propiedad
	//@Column(name = "street")
	private String street;
	
	// No hace falta poner esta anotación si queremos que el nombre de la columna de la tabla correspondiente se llame igual que esta propiedad
	//@Column(name = "number")
	private String number;
	
	// No hace falta poner esta anotación si queremos que el nombre de la columna de la tabla correspondiente se llame igual que esta propiedad
	//@Column(name = "city")
	private String city;
	
	// Relación uno a muchos de la entidad Profile con esta entidad Address
	// Este lado de la relación se corresponde con la perte muchos a uno
	// Si no se indica el nombre de la clabe foránea o extranjera usando la anotación @JoinColumn, por defecto el nombre de dicha clave se compone del nombre de la entidad("Profile") seguido de "_" y seguido del nombre de la propiedad de esa clase entidad que hace de clave primaria("id"). En general, el nombre de la clave sería "profile_id"
	@ManyToOne
	private Profile profile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
}
