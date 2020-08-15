package com.jero.system.spring.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aportes")
public class Aportes {
  @Id
  @Column(name = "idAporte")
  private int idAporte;
  
  @Column(name = "idFolder")
  private int idFolder;
  
  @Column(name = "fecha_registro_aporte", nullable = false)
  private Date fecha_registro_aporte;
  
  @Column(name = "soporte_aporte", nullable = false)
  private String soporte_aporte;
  
  @Column(name = "valor_aporte", nullable = false)
  private int valor_aporte;
  
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

  @Column(name = "nombre_folder", nullable = true)
  private String nombre_folder;
  
  @Column(name = "nombre_completo", nullable = true)
  private String nombre_completo;
  
  @Column(name = "numero_identicicacion", nullable = true)
  private String numero_identicicacion;
  
  public Aportes() {
  }

    public int getIdAporte() {
        return idAporte;
    }

    public void setIdAporte(int idAporte) {
        this.idAporte = idAporte;
    }

    public int getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(int idFolder) {
        this.idFolder = idFolder;
    }

    public Date getFecha_registro_aporte() {
        return fecha_registro_aporte;
    }

    public void setFecha_registro_aporte(Date fecha_registro_aporte) {
        this.fecha_registro_aporte = fecha_registro_aporte;
    }

    public String getSoporte_aporte() {
        return soporte_aporte;
    }

    public void setSoporte_aporte(String soporte_aporte) {
        this.soporte_aporte = soporte_aporte;
    }

    public int getValor_aporte() {
        return valor_aporte;
    }

    public void setValor_aporte(int valor_aporte) {
        this.valor_aporte = valor_aporte;
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

    public String getNombre_folder() {
        return nombre_folder;
    }

    public void setNombre_folder(String nombre_folder) {
        this.nombre_folder = nombre_folder;
    }    

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getNumero_identicicacion() {
        return numero_identicicacion;
    }

    public void setNumero_identicicacion(String numero_identicicacion) {
        this.numero_identicicacion = numero_identicicacion;
    }
}
