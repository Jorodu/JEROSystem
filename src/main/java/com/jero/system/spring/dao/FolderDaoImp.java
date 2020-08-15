package com.jero.system.spring.dao;

import com.jero.system.spring.model.Folder;
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
public class FolderDaoImp implements FolderDao {

  @Transactional(readOnly = true)
  public Boolean creaFolder(Folder folder)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("folder_adicionar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("nombre_folder", Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("fecha_registro",Types.DATE));            
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));            

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("nombre_folder", folder.getNombre_folder());
            callParams.put("fecha_registro", dateFormat.format(folder.getFecha_registro()));            
            callParams.put("usuario_id_adiciono", String.valueOf(folder.getUsuario_id_adiciono()));            

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
  public Boolean editaFolder(Folder folder)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("folder_actualizar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("id_folder", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("nombre_folder", Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("fecha_registro",Types.DATE));            
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));            
            
            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("id_folder", String.valueOf(folder.getIdFolder()));            
            callParams.put("nombre_folder", folder.getNombre_folder());            
            callParams.put("fecha_registro", dateFormat.format(folder.getFecha_registro()));            
            callParams.put("usuario_id_adiciono", String.valueOf(folder.getUsuario_id_adiciono()));            

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
  public Boolean creaFolderPorAfiliado(Folder folder)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("folder_por_persona_adicionar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("idFolder", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("id_persona",Types.INTEGER));            
            jdbcCall.addDeclaredParameter(new SqlParameter("tipo_afiliado",Types.INTEGER));                
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));            

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("idFolder", String.valueOf(folder.getIdFolder()));
            callParams.put("id_persona", String.valueOf(folder.getId_persona()));            
            callParams.put("tipo_afiliado", String.valueOf(folder.getTipo_afiliado()));            
            callParams.put("usuario_id_adiciono", String.valueOf(folder.getUsuario_id_adiciono()));            

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
  public Boolean eliminaFolderPorAfiliado(Folder folder)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("folder_por_persona_eliminar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("idFolders", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("id_personas",Types.INTEGER));                        

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("idFolders", String.valueOf(folder.getIdFolder()));
            callParams.put("id_personas", String.valueOf(folder.getId_persona()));                                   

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
  public List listarFolder(int idFolder) {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("folder_seleccionarPorIdFolder")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("folder", BeanPropertyRowMapper.newInstance(Folder.class));

            jdbcCall.addDeclaredParameter(new SqlParameter("idFolder", Types.INTEGER));

            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("idFolder", String.valueOf(idFolder));

            outputMap = jdbcCall.execute(callParams);    

            return (List) outputMap.get("folder");
        }
        catch(Exception ex)
        {
            return null;
        }
    }  
    
  @Transactional(readOnly = true)
  public List listarFolderPorPersona(int idPersona) {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("folder_por_persona_seleccionarPorIdpersona")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("folder", BeanPropertyRowMapper.newInstance(Folder.class));

            jdbcCall.addDeclaredParameter(new SqlParameter("id_persona", Types.INTEGER));

            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("id_persona", String.valueOf(idPersona));

            outputMap = jdbcCall.execute(callParams);    

            return (List) outputMap.get("folder");
        }
        catch(Exception ex)
        {
            return null;
        }
    }  
  
  @Transactional(readOnly = true)
  public List listarFolderPorFolder(int idFolder) {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("folder_por_persona_seleccionarPorIdFolder")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("folder", BeanPropertyRowMapper.newInstance(Folder.class));

            jdbcCall.addDeclaredParameter(new SqlParameter("idFolder", Types.INTEGER));

            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("idFolder", String.valueOf(idFolder));

            outputMap = jdbcCall.execute(callParams);    

            return (List) outputMap.get("folder");
        }
        catch(Exception ex)
        {
            return null;
        }
    }  
}
