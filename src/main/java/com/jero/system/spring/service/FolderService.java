package com.jero.system.spring.service;

import com.jero.system.spring.model.Folder;
import java.util.List;

public interface FolderService {
    public List listarFolder(int idFolder);
    public Boolean creaFolder(Folder folder);
    public Boolean editaFolder(Folder folder);
    public Boolean creaFolderPorAfiliado(Folder folder);
    public Boolean eliminaFolderPorAfiliado(Folder folder);
    public List listarFolderPorPersona(int idPersona);
    public List listarFolderPorFolder(int idFolder);
}
