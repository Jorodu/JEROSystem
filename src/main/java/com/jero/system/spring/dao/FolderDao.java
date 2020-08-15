package com.jero.system.spring.dao;

import com.jero.system.spring.model.Folder;
import java.util.List;

public interface FolderDao {
  List listarFolder(int idFolder);
  Boolean creaFolder(Folder folder);
  Boolean editaFolder(Folder folder);
  Boolean creaFolderPorAfiliado(Folder folder);
  Boolean eliminaFolderPorAfiliado(Folder folder);
  List listarFolderPorPersona(int idPersona);
  List listarFolderPorFolder(int idFolder);
}
