package com.jero.system.spring.controller;

import com.jero.system.spring.model.MUserDetails;
import com.jero.system.spring.model.User;
import com.jero.system.spring.model.Usuario;
import com.jero.system.spring.service.UsuariosService;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@SessionAttributes({"currentUser"})
@Controller
public class LoginController {
    
    private static final Logger log = LogManager.getLogger(LoginController.class);
    
    @Autowired
    private UsuariosService usuarioService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("error", "false");
        return "login";
    }
    
    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public String loginError(Model model) {
        log.info("Login attempt failed");
        model.addAttribute("error", "true");
        return "login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(SessionStatus session, HttpSession ses) {
        ses.setAttribute("userId", null);
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String loginPost(Model model, HttpSession session, Authentication authentication) {

        try
        {
            String client_id = (String) session.getAttribute("client_id");
            return "/postLogin";
        }
        catch(Exception ex)
        {
            log.info("Login attempt failed");
            model.addAttribute("error", "true");
            return "login";
        }        
    }
    
    @RequestMapping(value = "/postLogin", method = RequestMethod.POST)
    public String postLogin(Model model, HttpSession session) {
        try
        {
            log.info("postLogin()");
            //Autentica con spring
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();        
            validatePrinciple(authentication.getPrincipal());

            User loggedInUser = ((MUserDetails) authentication.getPrincipal()).getUserDetails();
            Usuario usuario = (Usuario) usuarioService.listarUsuarios(loggedInUser.getId_usuario()).get(0);                
            
            //Pasa parametros a la vista y sesión
            model.addAttribute("currentUser", loggedInUser.getUsername());        
            model.addAttribute("perfil", loggedInUser.getPerfil());
            session.setAttribute("currentUser", loggedInUser.getUsername());
            session.setAttribute("userId", loggedInUser.getId_usuario());
            session.setAttribute("perfil", loggedInUser.getPerfil());
            session.setAttribute("DescPerfil", usuario.getDesc_perfil());
            
            if(loggedInUser.getPerfil() == 1)            
                return "redirect:/home";
            else
                return "redirect:/afiliados";                
        }
        catch(Exception ex)
        {
            log.info("Login attempt failed");
            model.addAttribute("error", "true");
            return "login";
        }        
    }
    
    private void validatePrinciple(Object principal) {
        if (!(principal instanceof MUserDetails)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }
}