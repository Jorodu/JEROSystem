package com.jero.system.spring.dao;

import com.jero.system.spring.model.AhorroVoluntario;
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
public class AhorroVolDaoImp implements AhorroVolDao {

  @Transactional(readOnly = true)
  public Boolean creaAhorroVol(AhorroVoluntario ahorroVol)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("ahorro_voluntario_adicionar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("id_folder", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_ahorro",Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("fecha_max_pago",Types.DATE));
            jdbcCall.addDeclaredParameter(new SqlParameter("soporte",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_total_ahorro",Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("fecha_fin_ahorro",Types.DATE));            
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));              

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("id_folder", String.valueOf(ahorroVol.getId_folder()));
            callParams.put("valor_ahorro", String.valueOf(ahorroVol.getValor_ahorro()));
            callParams.put("fecha_max_pago", dateFormat.format(ahorroVol.getFecha_max_pago())); 
            callParams.put("soporte", String.valueOf(ahorroVol.getSoporte()));
            callParams.put("valor_total_ahorro", String.valueOf(ahorroVol.getValor_total_ahorro()));
            callParams.put("fecha_fin_ahorro", dateFormat.format(ahorroVol.getFecha_fin_ahorro())); 
            callParams.put("usuario_id_adiciono", String.valueOf(ahorroVol.getUsuario_id_adiciono()));

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
  public Boolean editaAhorroVol(AhorroVoluntario ahorroVol)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("ahorro_voluntario_actualizar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("idAhorro_voluntario", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("id_folder", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_ahorro",Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("fecha_max_pago",Types.DATE));
            jdbcCall.addDeclaredParameter(new SqlParameter("soporte",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_total_ahorro",Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("fecha_fin_ahorro",Types.DATE));            
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));              

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("idAhorro_voluntario", String.valueOf(ahorroVol.getId_ahorro_voluntario()));
            callParams.put("id_folder", String.valueOf(ahorroVol.getId_folder()));
            callParams.put("valor_ahorro", String.valueOf(ahorroVol.getValor_ahorro()));
            callParams.put("fecha_max_pago", dateFormat.format(ahorroVol.getFecha_max_pago())); 
            callParams.put("soporte", String.valueOf(ahorroVol.getSoporte()));
            callParams.put("valor_total_ahorro", String.valueOf(ahorroVol.getValor_total_ahorro()));
            callParams.put("fecha_fin_ahorro", dateFormat.format(ahorroVol.getFecha_fin_ahorro())); 
            callParams.put("usuario_id_adiciono", String.valueOf(ahorroVol.getUsuario_id_adiciono()));

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
  public Boolean creaComprobanteAhorro(AhorroVoluntario ahorroVol)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("comprobante_ahorro_por_ahorro_adicionar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento            
            jdbcCall.addDeclaredParameter(new SqlParameter("id_ahorro_voluntario", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("fecha",Types.DATE));            
            jdbcCall.addDeclaredParameter(new SqlParameter("soporte",Types.VARCHAR));            
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));              

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();            
            callParams.put("id_ahorro_voluntario", String.valueOf(ahorroVol.getId_ahorro_voluntario()));
            callParams.put("fecha", dateFormat.format(ahorroVol.getFecha_registro()));            
            callParams.put("soporte", String.valueOf(ahorroVol.getSoporte()));            
            callParams.put("usuario_id_adiciono", String.valueOf(ahorroVol.getUsuario_id_adiciono()));

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
  public Boolean eliminarAhorroVol(int idComprobante)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("comprobante_ahorro_por_ahorro_eliminar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento            
            jdbcCall.addDeclaredParameter(new SqlParameter("id_comprobantes", Types.INTEGER));            

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();            
            callParams.put("id_comprobantes", String.valueOf(idComprobante));            

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
  public List listarAhorroVol(int idAhorro) {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("ahorro_voluntario_seleccionarPorId")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("ahorroVol", BeanPropertyRowMapper.newInstance(AhorroVoluntario.class));

            jdbcCall.addDeclaredParameter(new SqlParameter("id_ahorro_voluntario", Types.INTEGER));

            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("id_ahorro_voluntario", String.valueOf(idAhorro));

            outputMap = jdbcCall.execute(callParams);    

            return (List) outputMap.get("ahorroVol");
        }
        catch(Exception ex)
        {
            return null;
        }
    }  
  
  @Transactional(readOnly = true)
  public List listarComprobantesAhorroVol(int idAhorro) {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("comprobante_ahorro_por_aho_selec_ahorro")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("ahorroVol", BeanPropertyRowMapper.newInstance(AhorroVoluntario.class));

            jdbcCall.addDeclaredParameter(new SqlParameter("id_ahorro_voluntarios", Types.INTEGER));

            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("id_ahorro_voluntarios", String.valueOf(idAhorro));

            outputMap = jdbcCall.execute(callParams);    

            return (List) outputMap.get("ahorroVol");
        }
        catch(Exception ex)
        {
            return null;
        }
    }  
}
