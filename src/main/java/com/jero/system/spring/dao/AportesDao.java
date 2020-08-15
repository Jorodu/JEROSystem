package com.jero.system.spring.dao;

import com.jero.system.spring.model.Aportes;
import java.util.List;

public interface AportesDao {
  List listarAportes(int idAporte);
  Boolean crearAporte(Aportes aportes);
  Boolean editarAporte(Aportes aportes);
  List listarAportesPendientesmes();
  List listarAportesMes();
}
