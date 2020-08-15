package com.jero.system.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.jero.system.spring.dao.AfiliadosDao;
import com.jero.system.spring.model.Afiliado;

@Service("afiliadosService")
public class AfiliadosServiceImp implements AfiliadosService {

  @Autowired
  private AfiliadosDao ahorroVolDao;
  
  public List listarAfiliados(int idAfiliado)
  {
      return ahorroVolDao.listarAfiliados(idAfiliado);
  }
  
  public Boolean crearAfiliado(Afiliado afiliado)
  {
      return ahorroVolDao.crearAfiliado(afiliado);
  }
  
  public Boolean editaAfiliado(Afiliado afiliado)
  {
      return ahorroVolDao.editaAfiliado(afiliado);
  }
  
  public Boolean creDocumentoAfiliado(Afiliado afiliado)
  {
      return ahorroVolDao.creDocumentoAfiliado(afiliado);
  }
  
  public List listarDocumentoPorAfiliado(int idAfiliado)
  {
      return ahorroVolDao.listarDocumentoPorAfiliado(idAfiliado);
  }
  
  public Boolean EliminaDocumentoAfiliado(Afiliado afiliado)
  {
      return ahorroVolDao.EliminaDocumentoAfiliado(afiliado);
  }
}
