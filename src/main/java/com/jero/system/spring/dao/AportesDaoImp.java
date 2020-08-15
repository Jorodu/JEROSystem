package com.jero.system.spring.dao;

import com.jero.system.spring.model.Aportes;
import com.jero.system.spring.model.Productos;
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
public class AportesDaoImp implements AportesDao {

  @Transactional(readOnly = true)
  public Boolean crearAporte(Aportes aportes)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("aportes_adicionar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("idFolder", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("fecha_registro_aporte",Types.DATE));
            jdbcCall.addDeclaredParameter(new SqlParameter("soporte_aporte",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_aporte",Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("idFolder", String.valueOf(aportes.getIdFolder()));
            callParams.put("fecha_registro_aporte", dateFormat.format(aportes.getFecha_registro_aporte()));
            callParams.put("soporte_aporte", aportes.getSoporte_aporte());
            callParams.put("valor_aporte", String.valueOf(aportes.getValor_aporte()));
            callParams.put("usuario_id_adiciono", String.valueOf(aportes.getUsuario_id_adiciono()));

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
  public Boolean editarAporte(Aportes aportes)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("aportes_actualizar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("id_Aporte", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("id_Folder", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("fecha_registro_aportes",Types.DATE));
            jdbcCall.addDeclaredParameter(new SqlParameter("soporte_aportes",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("valor_aportes",Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("id_Aporte", String.valueOf(aportes.getIdAporte()));
            callParams.put("id_Folder", String.valueOf(aportes.getIdFolder()));
            callParams.put("fecha_registro_aportes", dateFormat.format(aportes.getFecha_registro_aporte()));
            callParams.put("soporte_aportes", aportes.getSoporte_aporte());
            callParams.put("valor_aportes", String.valueOf(aportes.getValor_aporte()));
            callParams.put("usuario_id_adiciono", String.valueOf(aportes.getUsuario_id_adiciono()));

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
  public List listarAportes(int idAporte) {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("aportes_seleccionarPorIdAporte")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("aportes", BeanPropertyRowMapper.newInstance(Aportes.class));

            jdbcCall.addDeclaredParameter(new SqlParameter("id_Aporte", Types.INTEGER));

            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("id_Aporte", String.valueOf(idAporte));

            outputMap = jdbcCall.execute(callParams);    

            return (List) outputMap.get("aportes");
        }
        catch(Exception ex)
        {
            return null;
        }
    }
  
  @Transactional(readOnly = true)
  public List listarAportesPendientesmes() {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("aportes_seleccionarFaltantesMes")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("aportes", BeanPropertyRowMapper.newInstance(Aportes.class));

            outputMap = jdbcCall.execute();    

            return (List) outputMap.get("aportes");
        }
        catch(Exception ex)
        {
            return null;
        }
    }
  
  @Transactional(readOnly = true)
  public List listarAportesMes() {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("aportes_seleccionarMes")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("aportes", BeanPropertyRowMapper.newInstance(Aportes.class));

            outputMap = jdbcCall.execute();    

            return (List) outputMap.get("aportes");
        }
        catch(Exception ex)
        {
            return null;
        }
    }
  
}
