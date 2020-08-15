package com.jero.system.spring.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ahorro_voluntario")
public class AhorroVoluntario {
  @Id
  @Column(name = "id_ahorro_voluntario")
  private int id_ahorro_voluntario;
  
  @Column(name = "tipo_categoria_id")
  private int tipo_categoria_id;
  
  @Column(name = "id_folder")
  private int id_folder;
  
  @Column(name = "valor_ahorro", nullable = false)
  private int valor_ahorro;
  
  @Column(name = "fecha_max_pago", nullable = false)
  private Date fecha_max_pago;
  
  @Column(name = "soporte", nullable = false)
  private String soporte;
  
  @Column(name = "valor_total_ahorro", nullable = false)
  private int valor_total_ahorro;
  
  @Column(name = "fecha_fin_ahorro", nullable = false)
  private Date fecha_fin_ahorro;
  
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
  
  @Column(name = "id_comprobante", nullable = true)
  private Integer id_comprobante;
  
  @Column(name = "fecha_registro", nullable = true)
  private Date fecha_registro;
  
  public AhorroVoluntario() {
  }

    public int getId_ahorro_voluntario() {
        return id_ahorro_voluntario;
    }

    public void setId_ahorro_voluntario(int id_ahorro_voluntario) {
        this.id_ahorro_voluntario = id_ahorro_voluntario;
    }

    public int getId_folder() {
        return id_folder;
    }

    public void setId_folder(int id_folder) {
        this.id_folder = id_folder;
    }

    public int getTipo_categoria_id() {
        return tipo_categoria_id;
    }

    public void setTipo_categoria_id(int tipo_categoria_id) {
        this.tipo_categoria_id = tipo_categoria_id;
    }

    public int getValor_ahorro() {
        return valor_ahorro;
    }

    public void setValor_ahorro(int valor_ahorro) {
        this.valor_ahorro = valor_ahorro;
    }

    public Date getFecha_max_pago() {
        return fecha_max_pago;
    }

    public void setFecha_max_pago(Date fecha_max_pago) {
        this.fecha_max_pago = fecha_max_pago;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    public int getValor_total_ahorro() {
        return valor_total_ahorro;
    }

    public void setValor_total_ahorro(int valor_total_ahorro) {
        this.valor_total_ahorro = valor_total_ahorro;
    }

    public Date getFecha_fin_ahorro() {
        return fecha_fin_ahorro;
    }

    public void setFecha_fin_ahorro(Date fecha_fin_ahorro) {
        this.fecha_fin_ahorro = fecha_fin_ahorro;
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

    public Integer getId_comprobante() {
        return id_comprobante;
    }

    public void setId_comprobante(Integer id_comprobante) {
        this.id_comprobante = id_comprobante;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }    
}
