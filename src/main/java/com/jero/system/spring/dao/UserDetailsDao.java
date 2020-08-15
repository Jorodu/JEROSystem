package com.jero.system.spring.dao;

import com.jero.system.spring.model.User;

public interface UserDetailsDao {
  User findUserByUsername(String username);
}
