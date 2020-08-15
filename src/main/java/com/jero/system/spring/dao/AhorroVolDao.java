package com.jero.system.spring.dao;

import com.jero.system.spring.model.AhorroVoluntario;
import java.util.List;

public interface AhorroVolDao {
  List listarAhorroVol(int idAhorro);
  Boolean creaAhorroVol(AhorroVoluntario ahorroVol);
  Boolean editaAhorroVol(AhorroVoluntario ahorroVol);
  Boolean creaComprobanteAhorro(AhorroVoluntario ahorroVol);
  Boolean eliminarAhorroVol(int idComprobante);
  List listarComprobantesAhorroVol(int idAhorro);
}
