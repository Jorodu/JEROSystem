package com.jero.system.spring.dao;

import org.apache.commons.dbcp2.BasicDataSource;

public class Base {
    
    public static BasicDataSource dataSource;
  
    public static BasicDataSource getDataSource()
    {
          if (dataSource == null)
          {
              BasicDataSource ds = new BasicDataSource();
              ds.setUrl("jdbc:mysql://localhost:3306/jero_system?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
              ds.setUsername("root");
              ds.setPassword("");

              ds.setMinIdle(5);
              ds.setMaxIdle(10);
              ds.setMaxOpenPreparedStatements(100);

              dataSource = ds;
          }
          return dataSource;
      }    
}
