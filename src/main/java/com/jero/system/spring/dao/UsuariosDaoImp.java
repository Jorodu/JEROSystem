package com.jero.system.spring.dao;

import com.jero.system.spring.model.Productos;
import com.jero.system.spring.model.Usuario;
import org.springframework.stereotype.Repository;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsuariosDaoImp implements UsuariosDao {

    @Transactional(readOnly = true)
    public Boolean creaUsuario(Usuario usuario)  {

          try
          {
              //Invoca al JDBC que se conecta a la BD
              JdbcTemplate jdbcTemplate = new JdbcTemplate();
              jdbcTemplate.setDataSource(Base.getDataSource());

              SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("usuario_adicionar").withoutProcedureColumnMetaDataAccess();

              //declara los argumentos del procedimiento
              jdbcCall.addDeclaredParameter(new SqlParameter("nombres", Types.VARCHAR));
              jdbcCall.addDeclaredParameter(new SqlParameter("usuario",Types.VARCHAR));
              jdbcCall.addDeclaredParameter(new SqlParameter("clave",Types.VARCHAR));
              jdbcCall.addDeclaredParameter(new SqlParameter("categoria_perfil",Types.INTEGER));
              jdbcCall.addDeclaredParameter(new SqlParameter("estado",Types.INTEGER));
              jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));            

              //Asgina los parametros a pasar a la BD
              Map<String, String> callParams = new HashMap<String, String>();
              callParams.put("nombres", usuario.getNombres());
              callParams.put("usuario", usuario.getUsername());
              callParams.put("clave", usuario.getPassword());
              callParams.put("categoria_perfil", String.valueOf(usuario.getCategoria_perfil()));
              callParams.put("estado", String.valueOf(usuario.getEstado()));
              callParams.put("usuario_id_adiciono", String.valueOf(usuario.getUsuario_id_adiciono()));

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
    public Boolean editaUsuario(Usuario usuario)  {

          try
          {
              //Invoca al JDBC que se conecta a la BD
              JdbcTemplate jdbcTemplate = new JdbcTemplate();
              jdbcTemplate.setDataSource(Base.getDataSource());

              SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("usuario_actualizar").withoutProcedureColumnMetaDataAccess();

              //declara los argumentos del procedimiento
              jdbcCall.addDeclaredParameter(new SqlParameter("idusuario", Types.INTEGER));
              jdbcCall.addDeclaredParameter(new SqlParameter("nombres", Types.VARCHAR));
              jdbcCall.addDeclaredParameter(new SqlParameter("usuario",Types.VARCHAR));
              jdbcCall.addDeclaredParameter(new SqlParameter("clave",Types.VARCHAR));
              jdbcCall.addDeclaredParameter(new SqlParameter("categoria_perfil",Types.INTEGER));
              jdbcCall.addDeclaredParameter(new SqlParameter("estado",Types.INTEGER));
              jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));            

              //Asgina los parametros a pasar a la BD
              Map<String, String> callParams = new HashMap<String, String>();
              callParams.put("idusuario", String.valueOf(usuario.getId_usuario()));
              callParams.put("nombres", usuario.getNombres());
              callParams.put("usuario", usuario.getUsername());
              callParams.put("clave", usuario.getPassword());
              callParams.put("categoria_perfil", String.valueOf(usuario.getCategoria_perfil()));
              callParams.put("estado", String.valueOf(usuario.getEstado()));
              callParams.put("usuario_id_adiciono", String.valueOf(usuario.getUsuario_id_adiciono()));

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
    public List listarUsuarios(int idUsuario)  {

          Map outputMap = null;
          try
          {
              JdbcTemplate jdbcTemplate = new JdbcTemplate();
              jdbcTemplate.setDataSource(Base.getDataSource());

              SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("usuario_seleccionarPorIdusuario")
                      .withoutProcedureColumnMetaDataAccess()
                      .returningResultSet("usuarios", BeanPropertyRowMapper.newInstance(Usuario.class));

              jdbcCall.addDeclaredParameter(new SqlParameter("idusuario", Types.INTEGER));

              Map<String, String> callParams = new HashMap<String, String>();
              callParams.put("idusuario", String.valueOf(idUsuario));

              outputMap = jdbcCall.execute(callParams);    

              return (List) outputMap.get("usuarios");
          }
          catch(Exception ex)
          {
              return null;
          }
    }  
}
