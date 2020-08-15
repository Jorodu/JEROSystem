package com.jero.system.spring.service;

import com.jero.system.spring.model.Aportes;
import java.util.List;

public interface AportesService {
    public List listarAportes(int idAporte);
    public Boolean crearAporte(Aportes aportes);
    public Boolean editarAporte(Aportes aportes);
    public List listarAportesPendientesmes();
    public List listarAportesMes();
}
