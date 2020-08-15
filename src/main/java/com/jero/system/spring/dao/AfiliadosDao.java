package com.jero.system.spring.dao;

import com.jero.system.spring.model.Afiliado;
import java.util.List;

public interface AfiliadosDao {
  List listarAfiliados(int idAfiliado);
  Boolean crearAfiliado(Afiliado afiliado);
  Boolean editaAfiliado(Afiliado afiliado);
  Boolean creDocumentoAfiliado(Afiliado afiliado);
  List listarDocumentoPorAfiliado(int idAfiliado);
  Boolean EliminaDocumentoAfiliado(Afiliado afiliado);
}
