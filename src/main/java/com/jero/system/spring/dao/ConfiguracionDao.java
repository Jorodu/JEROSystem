package com.jero.system.spring.dao;

import com.jero.system.spring.model.Categorias;
import java.util.List;

public interface ConfiguracionDao {
  List listarCategorias(int idCategoria);
  Boolean creaCategoria(Categorias categoria);
  Boolean editaCategoria(Categorias categoria);
  List listarCategoriasPorTipoCat(int idTipoCategoria);
}
