package com.jero.system.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jero.system.spring.dao.UserDetailsDao;
import com.jero.system.spring.model.MUserDetails;
import com.jero.system.spring.model.User;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

  @Autowired
  private UserDetailsDao userDetailsDao;  

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = null;
    try
    {
        user = userDetailsDao.findUserByUsername(username);
        UserBuilder builder = null;
        Boolean isEnable = false;
        if (user != null) {
            
          if(user.getEnabled() == 2)
              isEnable = true;
          
          builder = org.springframework.security.core.userdetails.User.withUsername(username);
          builder.disabled(isEnable);
          builder.password(user.getPassword());
          String[] authorities = user.getAuthorities()
              .stream().map(a -> a.getAuthority()).toArray(String[]::new);

          builder.authorities(authorities);
        } else {
          throw new UsernameNotFoundException("User not found.");
        }
        return new MUserDetails(user);//builder.build();
    }
    catch(Exception ex)
    {
        return new MUserDetails(user);
    }
  }  
}
