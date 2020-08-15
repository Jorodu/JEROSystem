package com.jero.system.spring.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categorias {
  @Id
  @Column(name = "id_categoria")
  private int id_categoria;
  
  @Column(name = "tipo_categoria_id")
  private int tipo_categoria_id;
  
  @Column(name = "descripcion", nullable = false)
  private String descripcion;
    
  @Column(name = "usuario_id_adiciono", nullable = false)
  private int usuario_id_adiciono;
  
  @Column(name = "usuario_id_modifico", nullable = true)
  private Integer usuario_id_modifico;
  
  @Column(name = "fecha_adiciono")
  private Date fecha_adiciono;
  
  @Column(name = "fecha_modificacion", nullable = true)
  private Date fecha_modifico;
  
  @Column(name = "usuarioAdi", nullable = true)
  private String usuarioAdi;

  @Column(name = "desc_tipo_categoria", nullable = true)
  private String desc_tipo_categoria;
  
  @Column(name = "desc_estado", nullable = true)
  private String desc_estado;
  
  @Column(name = "estado_id")
  private int estado_id;
  
  public Categorias() {
  }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getTipo_categoria_id() {
        return tipo_categoria_id;
    }

    public void setTipo_categoria_id(int tipo_categoria_id) {
        this.tipo_categoria_id = tipo_categoria_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUsuario_id_adiciono() {
        return usuario_id_adiciono;
    }

    public void setUsuario_id_adiciono(int usuario_id_adiciono) {
        this.usuario_id_adiciono = usuario_id_adiciono;
    }

    public Integer getUsuario_id_modifico() {
        return usuario_id_modifico;
    }

    public void setUsuario_id_modifico(Integer usuario_id_modifico) {
        this.usuario_id_modifico = usuario_id_modifico;
    }

    public Date getFecha_adiciono() {
        return fecha_adiciono;
    }

    public void setFecha_adiciono(Date fecha_adiciono) {
        this.fecha_adiciono = fecha_adiciono;
    }

    public Date getFecha_modifico() {
        return fecha_modifico;
    }

    public void setFecha_modifico(Date fecha_modifico) {
        this.fecha_modifico = fecha_modifico;
    }

    public String getUsuarioAdi() {
        return usuarioAdi;
    }

    public void setUsuarioAdi(String usuarioAdi) {
        this.usuarioAdi = usuarioAdi;
    }

    public String getDesc_tipo_categoria() {
        return desc_tipo_categoria;
    }

    public void setDesc_tipo_categoria(String desc_tipo_categoria) {
        this.desc_tipo_categoria = desc_tipo_categoria;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }    

    public String getDesc_estado() {
        return desc_estado;
    }

    public void setDesc_estado(String desc_estado) {
        this.desc_estado = desc_estado;
    }
}
