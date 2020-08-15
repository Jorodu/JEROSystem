package com.jero.system.spring.service;

import com.jero.system.spring.model.Productos;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.jero.system.spring.dao.AportesDao;
import com.jero.system.spring.model.Aportes;

@Service("aportesService")
public class AportesServiceImp implements AportesService {

  @Autowired
  private AportesDao aportesVolDao;
  
  public List listarAportes(int idAporte)
  {
      return aportesVolDao.listarAportes(idAporte);
  }
  
  public Boolean crearAporte(Aportes aportes)  
  {
      return aportesVolDao.crearAporte(aportes);
  }
  
  public Boolean editarAporte(Aportes aportes)
  {
      return aportesVolDao.editarAporte(aportes);
  }  
  
  public List listarAportesPendientesmes() 
  {
      return aportesVolDao.listarAportesPendientesmes();
  }
  
  public List listarAportesMes()
  {
      return aportesVolDao.listarAportesMes();
  }
}
