package com.jero.system.spring.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persona")
public class Afiliado {
  @Id
  @Column(name = "id_persona")
  private int id_persona;
  
  @Column(name = "estado")
  private int estado;
  
  @Column(name = "categoria_tipo_identificacion", nullable = false)
  private int categoria_tipo_identificacion;
  
  @Column(name = "numero_identicicacion", nullable = false)
  private String numero_identicicacion;
  
  @Column(name = "nombres", nullable = false)
  private String nombres;
  
  @Column(name = "primer_apellido", nullable = false)
  private String primer_apellido;
  
  @Column(name = "segundo_apellido", nullable = false)
  private String segundo_apellido;
  
  @Column(name = "telefono", nullable = false)
  private long telefono;
  
  @Column(name = "celular", nullable = false)
  private long celular;
  
  @Column(name = "localidad", nullable = false)
  private String localidad;
  
  @Column(name = "barrio", nullable = false)
  private String barrio;
  
  @Column(name = "correo_electronico", nullable = false)
  private String correo_electronico;
  
  @Column(name = "fecha_nacimiento", nullable = false)
  private Date fecha_nacimiento;
  
  @Column(name = "puntaje_sisben", nullable = false)
  private double puntaje_sisben;
  
  @Column(name = "afiliado_fna", nullable = false)
  private String afiliado_fna;
  
  @Column(name = "categoria_caja_compens", nullable = false)
  private int categoria_caja_compens;
  
  @Column(name = "ahorro_bancario", nullable = false)
  private String ahorro_bancario;
  
  @Column(name = "categoria_banco", nullable = false)
  private int categoria_banco;
  
  @Column(name = "valor_ahorro_prog", nullable = false)
  private int valor_ahorro_prog;
  
  @Column(name = "otras_cuentas", nullable = false)
  private String otras_cuentas;
  
  @Column(name = "valor_otras_cuentas", nullable = false)
  private int valor_otras_cuentas;
  
  @Column(name = "valor_cesantias", nullable = false)
  private int valor_cesantias;
  
  @Column(name = "ocupacion", nullable = false)
  private String ocupacion;
  
  @Column(name = "tiene_discapacidad", nullable = false)
  private String tiene_discapacidad;
  
  @Column(name = "discapacidad", nullable = false)
  private String discapacidad;
  
  @Column(name = "categoria_genero", nullable = false)
  private int categoria_genero;
  
  @Column(name = "categoria_eps", nullable = false)
  private int categoria_eps;
  
  @Column(name = "categoria_estado_civil", nullable = false)
  private int categoria_estado_civil;
  
  @Column(name = "categoria_parentesco", nullable = false)
  private int categoria_parentesco;
  
  @Column(name = "fallecido", nullable = false)
  private String fallecido;
  
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

  @Column(name = "nombre_completo", nullable = true)
  private String nombre_completo;
  
  @Column(name = "estado_civil", nullable = true)
  private String estado_civil;
  
  @Column(name = "parentesco", nullable = true)
  private String parentesco;
  
  @Column(name = "banco", nullable = true)
  private String banco;
  
  @Column(name = "caja_compens", nullable = true)
  private String caja_compens;
  
  @Column(name = "tipo_identificacion", nullable = true)
  private String tipo_identificacion;
  
  @Column(name = "genero", nullable = true)
  private String genero;
  
  @Column(name = "eps", nullable = true)
  private String eps;
  
  @Column(name = "foto", nullable = true)
  private String foto;
  
  @Column(name = "desc_estado", nullable = true)
  private String desc_estado;
  
  @Column(name = "documento", nullable = true)
  private String documento;
  
  @Column(name = "tipo_documento_persona", nullable = true)
  private String tipo_documento_persona;
  
  @Column(name = "categoria_tipo_documento", nullable = true)
  private int categoria_tipo_documento;
  
  public Afiliado() {
  }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCategoria_tipo_identificacion() {
        return categoria_tipo_identificacion;
    }

    public void setCategoria_tipo_identificacion(int categoria_tipo_identificacion) {
        this.categoria_tipo_identificacion = categoria_tipo_identificacion;
    }

    public String getNumero_identicicacion() {
        return numero_identicicacion;
    }

    public void setNumero_identicicacion(String numero_identicicacion) {
        this.numero_identicicacion = numero_identicicacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public double getPuntaje_sisben() {
        return puntaje_sisben;
    }

    public void setPuntaje_sisben(double puntaje_sisben) {
        this.puntaje_sisben = puntaje_sisben;
    }

    public String getAfiliado_fna() {
        return afiliado_fna;
    }

    public void setAfiliado_fna(String afiliado_fna) {
        this.afiliado_fna = afiliado_fna;
    }

    public int getCategoria_caja_compens() {
        return categoria_caja_compens;
    }

    public void setCategoria_caja_compens(int categoria_caja_compens) {
        this.categoria_caja_compens = categoria_caja_compens;
    }

    public String getAhorro_bancario() {
        return ahorro_bancario;
    }

    public void setAhorro_bancario(String ahorro_bancario) {
        this.ahorro_bancario = ahorro_bancario;
    }

    public int getCategoria_banco() {
        return categoria_banco;
    }

    public void setCategoria_banco(int categoria_banco) {
        this.categoria_banco = categoria_banco;
    }

    public int getValor_ahorro_prog() {
        return valor_ahorro_prog;
    }

    public void setValor_ahorro_prog(int valor_ahorro_prog) {
        this.valor_ahorro_prog = valor_ahorro_prog;
    }

    public String getOtras_cuentas() {
        return otras_cuentas;
    }

    public void setOtras_cuentas(String otras_cuentas) {
        this.otras_cuentas = otras_cuentas;
    }

    public int getValor_otras_cuentas() {
        return valor_otras_cuentas;
    }

    public void setValor_otras_cuentas(int valor_otras_cuentas) {
        this.valor_otras_cuentas = valor_otras_cuentas;
    }

    public int getValor_cesantias() {
        return valor_cesantias;
    }

    public void setValor_cesantias(int valor_cesantias) {
        this.valor_cesantias = valor_cesantias;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getTiene_discapacidad() {
        return tiene_discapacidad;
    }

    public void setTiene_discapacidad(String tiene_discapacidad) {
        this.tiene_discapacidad = tiene_discapacidad;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public int getCategoria_genero() {
        return categoria_genero;
    }

    public void setCategoria_genero(int categoria_genero) {
        this.categoria_genero = categoria_genero;
    }

    public int getCategoria_eps() {
        return categoria_eps;
    }

    public void setCategoria_eps(int categoria_eps) {
        this.categoria_eps = categoria_eps;
    }

    public int getCategoria_estado_civil() {
        return categoria_estado_civil;
    }

    public void setCategoria_estado_civil(int categoria_estado_civil) {
        this.categoria_estado_civil = categoria_estado_civil;
    }

    public int getCategoria_parentesco() {
        return categoria_parentesco;
    }

    public void setCategoria_parentesco(int categoria_parentesco) {
        this.categoria_parentesco = categoria_parentesco;
    }

    public String getFallecido() {
        return fallecido;
    }

    public void setFallecido(String fallecido) {
        this.fallecido = fallecido;
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

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCaja_compens() {
        return caja_compens;
    }

    public void setCaja_compens(String caja_compens) {
        this.caja_compens = caja_compens;
    }

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getDesc_estado() {
        return desc_estado;
    }

    public void setDesc_estado(String desc_estado) {
        this.desc_estado = desc_estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getCategoria_tipo_documento() {
        return categoria_tipo_documento;
    }

    public void setCategoria_tipo_documento(int categoria_tipo_documento) {
        this.categoria_tipo_documento = categoria_tipo_documento;
    }

    public String getTipo_documento_persona() {
        return tipo_documento_persona;
    }

    public void setTipo_documento_persona(String tipo_documento_persona) {
        this.tipo_documento_persona = tipo_documento_persona;
    }
}
