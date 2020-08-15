package com.jero.system.spring.service;

import com.jero.system.spring.model.Categorias;
import java.util.List;

public interface ConfiguracionService {
    public List listarCategorias(int idCategoria);
    public Boolean creaCategoria(Categorias categoria);
    public Boolean editaCategoria(Categorias categoria);
    public List listarCategoriasPorTipoCat(int idTipoCategoria);
}
