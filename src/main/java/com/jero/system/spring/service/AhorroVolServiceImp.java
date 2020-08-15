package com.jero.system.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.jero.system.spring.dao.AhorroVolDao;
import com.jero.system.spring.model.AhorroVoluntario;

@Service("ahorroVolService")
public class AhorroVolServiceImp implements AhorroVolService {

  @Autowired
  private AhorroVolDao ahorroVolDao;
  
  public List listarAhorroVol(int idAhorro)
  {
      return ahorroVolDao.listarAhorroVol(idAhorro);
  }
  
  public Boolean editaAhorroVol(AhorroVoluntario ahorroVol)
  {
      return ahorroVolDao.editaAhorroVol(ahorroVol);
  }
  
  public Boolean creaAhorroVol(AhorroVoluntario ahorroVol)
  {
      return ahorroVolDao.creaAhorroVol(ahorroVol);
  }
  
  public Boolean creaComprobanteAhorro(AhorroVoluntario ahorroVol)
  {
      return ahorroVolDao.creaComprobanteAhorro(ahorroVol);
  }
  
  public Boolean eliminarAhorroVol(int idComprobante)
  {
      return ahorroVolDao.eliminarAhorroVol(idComprobante);
  }
  
  public List listarComprobantesAhorroVol(int idAhorro)
  {
      return ahorroVolDao.listarComprobantesAhorroVol(idAhorro);
  }
}
