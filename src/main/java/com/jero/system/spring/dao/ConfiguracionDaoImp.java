package com.jero.system.spring.dao;

import com.jero.system.spring.model.Categorias;
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
public class ConfiguracionDaoImp implements ConfiguracionDao {

  @Transactional(readOnly = true)
  public Boolean creaCategoria(Categorias categoria)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("categoria_adicionar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("tipo_categoria_id", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("descripcion",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("estado",Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));            

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("tipo_categoria_id", String.valueOf(categoria.getTipo_categoria_id()));
            callParams.put("descripcion", categoria.getDescripcion());
            callParams.put("estado", String.valueOf(categoria.getEstado_id()));
            callParams.put("usuario_id_adiciono", String.valueOf(categoria.getUsuario_id_adiciono()));            

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
  public Boolean editaCategoria(Categorias categoria)  {

        try
        {
            //Invoca al JDBC que se conecta a la BD
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("categoria_actualizar").withoutProcedureColumnMetaDataAccess();

            //declara los argumentos del procedimiento
            jdbcCall.addDeclaredParameter(new SqlParameter("idcategoria", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("tipo_categoria_id", Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("descripcion",Types.VARCHAR));
            jdbcCall.addDeclaredParameter(new SqlParameter("estado",Types.INTEGER));
            jdbcCall.addDeclaredParameter(new SqlParameter("usuario_id_adiciono",Types.INTEGER));            

            //Asgina los parametros a pasar a la BD
            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("idcategoria", String.valueOf(categoria.getId_categoria()));
            callParams.put("tipo_categoria_id", String.valueOf(categoria.getTipo_categoria_id()));
            callParams.put("descripcion", categoria.getDescripcion());
            callParams.put("estado", String.valueOf(categoria.getEstado_id()));
            callParams.put("usuario_id_adiciono", String.valueOf(categoria.getUsuario_id_adiciono()));            

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
  public List listarCategorias(int idCategoria) {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("categoria_seleccionarPorIdcategoria")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("categorias", BeanPropertyRowMapper.newInstance(Categorias.class));

            jdbcCall.addDeclaredParameter(new SqlParameter("idcategoria", Types.INTEGER));

            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("idcategoria", String.valueOf(idCategoria));

            outputMap = jdbcCall.execute(callParams);    

            return (List) outputMap.get("categorias");
        }
        catch(Exception ex)
        {
            return null;
        }
    }  
  
  @Transactional(readOnly = true)
  public List listarCategoriasPorTipoCat(int idTipoCategoria) {

        Map outputMap = null;
        try
        {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(Base.getDataSource());

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("categoria_seleccionarPorIdTipocategoriaLV")
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet("categorias", BeanPropertyRowMapper.newInstance(Categorias.class));

            jdbcCall.addDeclaredParameter(new SqlParameter("tipo_categoriaId", Types.INTEGER));

            Map<String, String> callParams = new HashMap<String, String>();
            callParams.put("tipo_categoriaId", String.valueOf(idTipoCategoria));

            outputMap = jdbcCall.execute(callParams);    

            return (List) outputMap.get("categorias");
        }
        catch(Exception ex)
        {
            return null;
        }
    }  
}
