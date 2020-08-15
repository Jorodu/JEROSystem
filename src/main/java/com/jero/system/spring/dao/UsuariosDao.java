package com.jero.system.spring.dao;

import com.jero.system.spring.model.Usuario;
import java.util.List;

public interface UsuariosDao {
  List listarUsuarios(int idUsuario);
  Boolean creaUsuario(Usuario usuario);
  Boolean editaUsuario(Usuario usuario);
}
