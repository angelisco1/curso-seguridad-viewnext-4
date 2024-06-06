package com.curso.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="informes")
public class Informe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String titulo;
	private String contenido;
	private String descripcion;
	private Integer userId;
	private String temaColor;
	
	public Informe() {}
	
	
	public Informe(String id, String titulo, String contenido, String descripcion, String temaColor, Integer userId) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.contenido = contenido;
		this.descripcion = descripcion;
		this.userId = userId;
		this.temaColor = temaColor;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTemaColor() {
		return temaColor;
	}
	public void setTemaColor(String temaColor) {
		this.temaColor = temaColor;
	}
	
	
	
	
}
