package com.jero.system.spring.service;

import com.jero.system.spring.model.AhorroVoluntario;
import java.util.List;

public interface AhorroVolService {
    public List listarAhorroVol(int idAhorro);
    public Boolean editaAhorroVol(AhorroVoluntario ahorroVol);
    public Boolean creaAhorroVol(AhorroVoluntario ahorroVol);
    public Boolean creaComprobanteAhorro(AhorroVoluntario ahorroVol);
    public Boolean eliminarAhorroVol(int idComprobante);
    public List listarComprobantesAhorroVol(int idAhorro);
}
