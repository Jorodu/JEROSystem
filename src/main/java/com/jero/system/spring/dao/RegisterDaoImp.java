package com.jero.system.spring.dao;

import org.springframework.stereotype.Repository;
import com.jero.system.spring.model.User;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RegisterDaoImp implements RegisterDao {

  @PersistenceContext
  private EntityManager entityManager;
  
  @Transactional(readOnly = true)
  @Override
  public Boolean registraUsuario(User user)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("users_adicionar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("username", Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("pwd",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("nombres",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("apellidos",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("num_iden",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("perfil",Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("genero",Types.INTEGER));

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("username", user.getUsername());
            /*callParams.put("pwd", user.getPassword());
            callParams.put("nombres", user.getNombres());
            callParams.put("apellidos", user.getApellidos());
            callParams.put("num_iden", user.getNumero_identificacion());
            callParams.put("perfil", String.valueOf(user.getPerfil()));
            callParams.put("genero", String.valueOf(user.getGenero()));*/

            //Ejecuta el procedimiento en bd
            Map<String, Object> outputMap = jdbcCall.execute(callParams);    

            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
}
