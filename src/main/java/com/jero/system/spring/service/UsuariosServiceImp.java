package com.jero.system.spring.service;

import com.jero.system.spring.model.Productos;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.jero.system.spring.dao.UsuariosDao;
import com.jero.system.spring.model.Usuario;

@Service("usuariosService")
public class UsuariosServiceImp implements UsuariosService {

  @Autowired
  private UsuariosDao usuarioDao;
  
  public List listarUsuarios(int idUsuario)
  {
      return usuarioDao.listarUsuarios(idUsuario);
  }
  
  public Boolean creaUsuario(Usuario usuario)
  {
      return usuarioDao.creaUsuario(usuario);
  }
  
  public Boolean editaUsuario(Usuario usuario)
  {
      return usuarioDao.editaUsuario(usuario);
  }
}
