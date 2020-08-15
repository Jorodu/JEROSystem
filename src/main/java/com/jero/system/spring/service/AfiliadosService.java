package com.jero.system.spring.service;

import com.jero.system.spring.model.Afiliado;
import java.util.List;

public interface AfiliadosService {
    public List listarAfiliados(int idAfiliado);
    public Boolean crearAfiliado(Afiliado afiliado);
    public Boolean editaAfiliado(Afiliado afiliado);
    public Boolean creDocumentoAfiliado(Afiliado afiliado);
    public List listarDocumentoPorAfiliado(int idAfiliado);
    public Boolean EliminaDocumentoAfiliado(Afiliado afiliado);
}
