package com.jero.system.spring.config;

import com.jero.system.spring.service.AfiliadosService;
import com.jero.system.spring.service.AfiliadosServiceImp;
import com.jero.system.spring.service.AhorroVolService;
import com.jero.system.spring.service.AhorroVolServiceImp;
import com.jero.system.spring.service.AportesService;
import com.jero.system.spring.service.AportesServiceImp;
import com.jero.system.spring.service.ConfiguracionService;
import com.jero.system.spring.service.ConfiguracionServiceImp;
import com.jero.system.spring.service.FolderService;
import com.jero.system.spring.service.FolderServiceImp;
import com.jero.system.spring.service.UsuariosServiceImp;
import com.jero.system.spring.service.RegisterService;
import com.jero.system.spring.service.RegisterServiceImp;
import com.jero.system.spring.service.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.jero.system.spring.service.UsuariosService;
import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public UserDetailsService userDetailsService() {
    return new UserDetailsServiceImp();
  }
  
  @Bean
  public RegisterService registerService() {
    return new RegisterServiceImp();
  }
  
  @Bean
  public UsuariosService usuariosService() {
    return new UsuariosServiceImp();
  }
  
  @Bean
  public ConfiguracionService configuracionService() {
    return new ConfiguracionServiceImp();
  }
  
  @Bean
  public AhorroVolService ahorroVolService() {
    return new AhorroVolServiceImp();
  }
  
  @Bean
  public AportesService aportesService() {
    return new AportesServiceImp();
  }
  
  @Bean
  public FolderService folderService() {
    return new FolderServiceImp();
  }
  
  @Bean
  public AfiliadosService afiliadosService() {
    return new AfiliadosServiceImp();
  };
  
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  };
  
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());        
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
  }
    
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception  {
      //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
      auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http    
    .authorizeRequests().antMatchers("/views").hasAnyRole("ADMIN", "USER")
    .and()
    .authorizeRequests().antMatchers("/login", "/resource/**", "/views/home").permitAll()
    .and()
    .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
    .loginProcessingUrl("/doLogin")
    .successForwardUrl("/postLogin")
    .failureUrl("/loginFailed")
    .and()
    .logout().logoutUrl("/doLogout").logoutSuccessUrl("/logout").permitAll()
    .and()
    .exceptionHandling()
    .accessDeniedHandler((request, response, accessDeniedException) -> {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    })
    .authenticationEntryPoint((request, response, authException) -> {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    })
    .and()
    .csrf().disable();      
  }
  
  @Override
  public void configure(WebSecurity web) throws Exception {
        web.ignoring()        
        .antMatchers("/resources/**");
        //.antMatchers("/**");
  }  
}
