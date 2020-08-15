package com.jero.system.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.jero.system.spring.dao.FolderDao;
import com.jero.system.spring.model.Folder;

@Service("folderService")
public class FolderServiceImp implements FolderService {

  @Autowired
  private FolderDao folderVolDao;
  
  public List listarFolder(int idFolder)
  {
      return folderVolDao.listarFolder(idFolder);
  }
  
  public Boolean creaFolder(Folder folder)
  {
      return folderVolDao.creaFolder(folder);
  }
  
  public Boolean editaFolder(Folder folder)  
  {
      return folderVolDao.editaFolder(folder);
  }
  
  public Boolean creaFolderPorAfiliado(Folder folder)
  {
      return folderVolDao.creaFolderPorAfiliado(folder);
  }
  
  public Boolean eliminaFolderPorAfiliado(Folder folder)
  {
      return folderVolDao.eliminaFolderPorAfiliado(folder);
  }
  
  public List listarFolderPorPersona(int idPersona)
  {
      return folderVolDao.listarFolderPorPersona(idPersona);
  }
  
  public List listarFolderPorFolder(int idFolder)
  {
      return folderVolDao.listarFolderPorFolder(idFolder);
  }
}
