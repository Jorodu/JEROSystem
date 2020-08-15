package com.jero.system.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.jero.system.spring.dao.ConfiguracionDao;
import com.jero.system.spring.model.Categorias;

@Service("configuracionService")
public class ConfiguracionServiceImp implements ConfiguracionService {

  @Autowired
  private ConfiguracionDao configuracionDao;
  
  public List listarCategorias(int idCategoria)
  {
      return configuracionDao.listarCategorias(idCategoria);
  }
  
  public Boolean creaCategoria(Categorias categoria)
  {
      return configuracionDao.creaCategoria(categoria);
  }
  
  public Boolean editaCategoria(Categorias categoria)
  {
      return configuracionDao.editaCategoria(categoria);
  }
  
  public List listarCategoriasPorTipoCat(int idTipoCategoria)
  {
      return configuracionDao.listarCategoriasPorTipoCat(idTipoCategoria);
  }
}
