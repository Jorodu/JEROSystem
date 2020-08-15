package com.jero.system.spring.service;

import com.jero.system.spring.dao.RegisterDao;
import com.jero.system.spring.dao.UserDetailsDao;
import com.jero.system.spring.model.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("registerService")
public class RegisterServiceImp implements RegisterService {

  @Autowired
  private RegisterDao registerDao;
  @Autowired
  private UserDetailsDao userDetailsDao;  
  
  @Override
  public Boolean registraUsuario(User user)
  {
      /*User userSearch = userDetailsDao.findUserByUsername(user.getUsername());
      
      if (userSearch != null) 
          return false;
      else*/
      return registerDao.registraUsuario(user);
  }  
}
