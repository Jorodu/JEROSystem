package com.jero.system.spring.service;

import com.jero.system.spring.model.Usuario;
import java.util.List;

public interface UsuariosService {
    public List listarUsuarios(int idUsuario);
    public Boolean creaUsuario(Usuario usuario);
    public Boolean editaUsuario(Usuario usuario);
}
