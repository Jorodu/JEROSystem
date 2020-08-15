package com.jero.system.spring.dao;

import com.jero.system.spring.model.Afiliado;
import org.springframework.stereotype.Repository;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AfiliadosDaoImp implements AfiliadosDao {

  @Transactional(readOnly = true)
  public Boolean crearAfiliado(Afiliado afiliado)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("persona_adicionar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("estado", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_tipo_identificacion",Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("numero_identicicacion",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("nombres",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("primer_apellido",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("segundo_apellido",Types.VARCHAR));                                    
            jdbcCall.addDeclaredParameter(new SqlParameter("telefono",Types.BIGINT));            
            jdbcCall.addDeclaredParameter(new SqlParameter("celular",Types.BIGINT));            
            jdbcCall.addDeclaredParameter(new SqlParameter("localidad",Types.VARCHAR));                        
            jdbcCall.addDeclaredParameter(new SqlParameter("barrio",Types.VARCHAR));                        
            jdbcCall.addDeclaredParameter(new SqlParameter("correo_electronico",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("fecha_nacimiento",Types.DATE));                        
            jdbcCall.addDeclaredParameter(new SqlParameter("puntaje_sisben",Types.DECIMAL));              
            jdbcCall.addDeclaredParameter(new SqlParameter("afiliado_fna",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_caja_compens",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("ahorro_bancario",Types.VARCHAR));                         
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_banco",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_ahorro_prog",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("otras_cuentas",Types.VARCHAR));                           
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_otras_cuentas",Types.DECIMAL));            
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_cesantias",Types.DECIMAL));            
            jdbcCall.addDeclaredParameter(new SqlParameter("ocupacion",Types.VARCHAR));                           
            jdbcCall.addDeclaredParameter(new SqlParameter("tiene_discapacidad",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("discapacidad",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_genero",Types.INTEGER));                           
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_eps",Types.INTEGER));            
            jdbcCall.addDeclaredParameter(new SqlParameter("fallecido",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_estado_civil",Types.INTEGER));         
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_parentesco",Types.INTEGER));   
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));   
            jdbcCall.addDeclaredParameter(new SqlParameter("foto",Types.VARCHAR));            
            
            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("estado", String.valueOf(afiliado.getEstado()));
            callParams.put("categoria_tipo_identificacion", String.valueOf(afiliado.getCategoria_tipo_identificacion()));
            callParams.put("numero_identicicacion", afiliado.getNumero_identicicacion());
            callParams.put("nombres", String.valueOf(afiliado.getNombres()));
            callParams.put("primer_apellido", afiliado.getPrimer_apellido());
            callParams.put("segundo_apellido", afiliado.getSegundo_apellido());            
            callParams.put("telefono", String.valueOf(afiliado.getTelefono()));
            callParams.put("celular", String.valueOf(afiliado.getCelular()));
            callParams.put("localidad", afiliado.getLocalidad());            
            callParams.put("barrio", afiliado.getBarrio());
            callParams.put("correo_electronico", afiliado.getCorreo_electronico());            
            callParams.put("fecha_nacimiento", dateFormat.format(afiliado.getFecha_nacimiento()));            
            callParams.put("puntaje_sisben", String.valueOf(afiliado.getPuntaje_sisben()));
            callParams.put("afiliado_fna", afiliado.getAfiliado_fna());                       
            callParams.put("categoria_caja_compens", String.valueOf(afiliado.getCategoria_caja_compens()));             
            callParams.put("ahorro_bancario", afiliado.getAhorro_bancario());            
            callParams.put("categoria_banco", String.valueOf(afiliado.getCategoria_banco()));
            callParams.put("valor_ahorro_prog", String.valueOf(afiliado.getValor_ahorro_prog()));
            callParams.put("otras_cuentas", afiliado.getOtras_cuentas());            
            callParams.put("valor_otras_cuentas", String.valueOf(afiliado.getValor_otras_cuentas()));
            callParams.put("valor_cesantias", String.valueOf(afiliado.getValor_cesantias()));
            callParams.put("ocupacion", afiliado.getOcupacion());            
            callParams.put("tiene_discapacidad", afiliado.getTiene_discapacidad());
            callParams.put("discapacidad", afiliado.getDiscapacidad());
            callParams.put("categoria_genero", String.valueOf(afiliado.getCategoria_genero()));            
            callParams.put("categoria_eps", String.valueOf(afiliado.getCategoria_eps()));
            callParams.put("fallecido", afiliado.getFallecido());
            callParams.put("categoria_estado_civil", String.valueOf(afiliado.getCategoria_estado_civil()));
            callParams.put("categoria_parentesco", String.valueOf(afiliado.getCategoria_parentesco()));
            callParams.put("categoria_estado_civil", String.valueOf(afiliado.getCategoria_estado_civil()));
            callParams.put("usuario_id_adiciono", String.valueOf(afiliado.getUsuario_id_adiciono()));
            callParams.put("foto", afiliado.getFoto());
            
            //Ejecuta el procedimiento en bd
            Map<String, Object> outputMap = jdbcCall.execute(callParams);    

            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
  
  @Transactional(readOnly = true)
  public Boolean editaAfiliado(Afiliado afiliado)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("persona_actualizar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("idpersona", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("estado", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_tipo_identificacion",Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("numero_identicicacion",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("nombres",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("primer_apellido",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("segundo_apellido",Types.VARCHAR));                                    
            jdbcCall.addDeclaredParameter(new SqlParameter("telefono",Types.BIGINT));            
            jdbcCall.addDeclaredParameter(new SqlParameter("celular",Types.BIGINT));            
            jdbcCall.addDeclaredParameter(new SqlParameter("localidad",Types.VARCHAR));                        
            jdbcCall.addDeclaredParameter(new SqlParameter("barrio",Types.VARCHAR));                        
            jdbcCall.addDeclaredParameter(new SqlParameter("correo_electronico",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("fecha_nacimiento",Types.DATE));                        
            jdbcCall.addDeclaredParameter(new SqlParameter("puntaje_sisben",Types.DOUBLE));              
            jdbcCall.addDeclaredParameter(new SqlParameter("afiliado_fna",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_caja_compens",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("ahorro_bancario",Types.VARCHAR));                         
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_banco",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_ahorro_prog",Types.DECIMAL));            
            jdbcCall.addDeclaredParameter(new SqlParameter("otras_cuentas",Types.VARCHAR));                           
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_otras_cuentas",Types.DECIMAL));            
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_cesantias",Types.DECIMAL));            
            jdbcCall.addDeclaredParameter(new SqlParameter("ocupacion",Types.VARCHAR));                           
            jdbcCall.addDeclaredParameter(new SqlParameter("tiene_discapacidad",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("discapacidad",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_genero",Types.INTEGER));                           
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_eps",Types.INTEGER));            
            jdbcCall.addDeclaredParameter(new SqlParameter("fallecido",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_estado_civil",Types.INTEGER));         
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_parentesco",Types.INTEGER));   
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));   
            jdbcCall.addDeclaredParameter(new SqlParameter("foto",Types.VARCHAR));            
            
            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("idpersona", String.valueOf(afiliado.getId_persona()));
            callParams.put("estado", String.valueOf(afiliado.getEstado()));
            callParams.put("categoria_tipo_identificacion", String.valueOf(afiliado.getCategoria_tipo_identificacion()));
            callParams.put("numero_identicicacion", afiliado.getNumero_identicicacion());
            callParams.put("nombres", String.valueOf(afiliado.getNombres()));
            callParams.put("primer_apellido", afiliado.getPrimer_apellido());
            callParams.put("segundo_apellido", afiliado.getSegundo_apellido());            
            callParams.put("telefono", String.valueOf(afiliado.getTelefono()));
            callParams.put("celular", String.valueOf(afiliado.getCelular()));
            callParams.put("localidad", afiliado.getLocalidad());            
            callParams.put("barrio", afiliado.getBarrio());
            callParams.put("correo_electronico", afiliado.getCorreo_electronico());            
            callParams.put("fecha_nacimiento", dateFormat.format(afiliado.getFecha_nacimiento()));            
            callParams.put("puntaje_sisben", String.valueOf(String.valueOf(afiliado.getPuntaje_sisben())));
            callParams.put("afiliado_fna", afiliado.getAfiliado_fna());                       
            callParams.put("categoria_caja_compens", String.valueOf(afiliado.getCategoria_caja_compens()));             
            callParams.put("ahorro_bancario", afiliado.getAhorro_bancario());            
            callParams.put("categoria_banco", String.valueOf(afiliado.getCategoria_banco()));
            callParams.put("valor_ahorro_prog", String.valueOf(afiliado.getValor_ahorro_prog()));
            callParams.put("otras_cuentas", afiliado.getOtras_cuentas());            
            callParams.put("valor_otras_cuentas", String.valueOf(afiliado.getValor_otras_cuentas()));
            callParams.put("valor_cesantias", String.valueOf(afiliado.getValor_cesantias()));
            callParams.put("ocupacion", afiliado.getOcupacion());            
            callParams.put("tiene_discapacidad", afiliado.getTiene_discapacidad());
            callParams.put("discapacidad", afiliado.getDiscapacidad());
            callParams.put("categoria_genero", String.valueOf(afiliado.getCategoria_genero()));            
            callParams.put("categoria_eps", String.valueOf(afiliado.getCategoria_eps()));
            callParams.put("fallecido", afiliado.getFallecido());
            callParams.put("categoria_estado_civil", String.valueOf(afiliado.getCategoria_estado_civil()));
            callParams.put("categoria_parentesco", String.valueOf(afiliado.getCategoria_parentesco()));
            callParams.put("categoria_estado_civil", String.valueOf(afiliado.getCategoria_estado_civil()));
            callParams.put("usuario_id_adiciono", String.valueOf(afiliado.getUsuario_id_adiciono()));
            callParams.put("foto", afiliado.getFoto());
            
            //Ejecuta el procedimiento en bd
            Map<String, Object> outputMap = jdbcCall.execute(callParams);    

            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
  
  @Transactional(readOnly = true)
  public Boolean creDocumentoAfiliado(Afiliado afiliado)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("documentos_por_persona_adicionar")
                    .withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("id_persona", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_tipo_documento", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("documento",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("nombre_documento",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));   
            
            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("id_persona", String.valueOf(afiliado.getId_persona()));
            callParams.put("categoria_tipo_documento", String.valueOf(afiliado.getCategoria_tipo_documento()));
            callParams.put("documento", String.valueOf(afiliado.getDocumento()));
            callParams.put("nombre_documento", afiliado.getDocumento());
            callParams.put("usuario_id_adiciono", String.valueOf(afiliado.getUsuario_id_adiciono()));
            
            //Ejecuta el procedimiento en bd
            Map<String, Object> outputMap = jdbcCall.execute(callParams);    

            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
  
  @Transactional(readOnly = true)
  public Boolean EliminaDocumentoAfiliado(Afiliado afiliado)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("documentos_por_persona_eliminar")
                    .withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("id_personas", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("categoria_tipo_documentos", Types.INTEGER));  
            
            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("id_personas", String.valueOf(afiliado.getId_persona()));
            callParams.put("categoria_tipo_documentos", String.valueOf(afiliado.getCategoria_tipo_documento()));
            
            //Ejecuta el procedimiento en bd
            Map<String, Object> outputMap = jdbcCall.execute(callParams);    

            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
  
  @Transactional(readOnly = true)
  public List listarDocumentoPorAfiliado(int idAfiliado) {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("documentos_por_persona_seleccionarPorIdPersona")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("afiliado", BeanPropertyRowMapper.newInstance(Afiliado.class));

            jdbcCall.addDeclaredParameter(new SqlParameter("IdPersona", Types.INTEGER));

            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("IdPersona", String.valueOf(idAfiliado));

            outputMap = jdbcCall.execute(callParams);    

            return (List) outputMap.get("afiliado");
        }
        catch(Exception ex)
        {
            return null;
        }
    }  
  
  @Transactional(readOnly = true)
  public List listarAfiliados(int idAfiliado) {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("persona_seleccionar")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("afiliado", BeanPropertyRowMapper.newInstance(Afiliado.class));

            jdbcCall.addDeclaredParameter(new SqlParameter("id_persona", Types.INTEGER));

            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("id_persona", String.valueOf(idAfiliado));

            outputMap = jdbcCall.execute(callParams);    

            return (List) outputMap.get("afiliado");
        }
        catch(Exception ex)
        {
            return null;
        }
    }  
}
