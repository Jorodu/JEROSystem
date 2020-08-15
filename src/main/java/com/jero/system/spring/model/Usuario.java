package com.jero.system.spring.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
  @Id
  @Column(name = "username")
  private String username;
  
  @Column(name = "id_usuario")
  private int id_usuario;
  
  @Column(name = "nombres", nullable = false)
  private String nombres;
  
  @Column(name = "password", nullable = false)
  private String password;
    
  @Column(name = "estado", nullable = false)
  private int enabled;
  
  @Column(name = "estado", nullable = false)
  private int estado;
  
  @Column(name = "categoria_perfil", nullable = false)
  private int categoria_perfil;
  
  @Column(name = "usuario_id_adiciono", nullable = false)
  private int usuario_id_adiciono;
  
  @Column(name = "usuario_id_modifico", nullable = true)
  private Integer usuario_id_modifico;
  
  @Column(name = "fecha_adiciono")
  private Date fecha_adiciono;
  
  @Column(name = "fecha_modificacion", nullable = true)
  private Date fecha_modifico;
  
  @Column(name = "desc_perfil", nullable = true)
  private String desc_perfil;
  
  @Column(name = "desc_estado", nullable = true)
  private String desc_estado;
  
  @Column(name = "usuarioAdi", nullable = true)
  private String usuarioAdi;

  public Usuario() {
  }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getCategoria_perfil() {
        return categoria_perfil;
    }

    public void setCategoria_perfil(int categoria_perfil) {
        this.categoria_perfil = categoria_perfil;
    }
    
    public int getUsuario_id_adiciono() {
        return usuario_id_adiciono;
    }

    public void setUsuario_id_adiciono(int usuario_id_adiciono) {
        this.usuario_id_adiciono = usuario_id_adiciono;
    }

    public int getUsuario_id_modifico() {
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
    
    public String getDesc_perfil() {
        return desc_perfil;
    }

    public void setDesc_perfil(String desc_perfil) {
        this.desc_perfil = desc_perfil;
    }

    public String getDesc_estado() {
        return desc_estado;
    }

    public void setDesc_estado(String desc_estado) {
        this.desc_estado = desc_estado;
    }

    public String getUsuarioAdi() {
        return usuarioAdi;
    }

    public void setUsuarioAdi(String usuarioAdi) {
        this.usuarioAdi = usuarioAdi;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }    
}
