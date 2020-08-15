package com.jero.system.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTOS")
public class Productos {
  
  @Id
  @Column(name = "ID_PRODUCTO")
  private int id_producto;
    
  @Column(name = "TITULO")
  private String titulo;
  
  @Column(name = "DESCRIPCION")
  private String descripcion;

  @Column(name = "IMAGEN", nullable = false)
  private String imagen;
    
  @Column(name = "ESPECIFICACIONES", nullable = false)
  private String especificaciones;
  
  @Column(name = "CATEGORIA", nullable = false)
  private int categoria;
  
  @Column(name = "PRODUCTOR_ID_USUARIO", nullable = false)
  private String productor_id;
  
  @Column(name = "NOMBRES", nullable = true)
  private String nombres;
  
  @Column(name = "APELLIDOS", nullable = true)
  private String apellidos;
  
  @Column(name = "USERNAME", nullable = true)
  private String username;
  
  @Column(name = "DESCRIPCION_CATEGORIA", nullable = true)
  private String descripcion_categoria;
  
  public Productos() {
  }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }
        
    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getProductor_id() {
        return productor_id;
    }

    public void setProductor_id(String productor_id) {
        this.productor_id = productor_id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescripcion_categoria() {
        return descripcion_categoria;
    }

    public void setDescripcion_categoria(String descripcion_categoria) {
        this.descripcion_categoria = descripcion_categoria;
    }
}
