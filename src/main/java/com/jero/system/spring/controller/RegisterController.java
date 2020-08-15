/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jero.system.spring.controller;

import com.jero.system.spring.service.RegisterService;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author sala4
 */

@SessionAttributes({"currentUser"})
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Simply selects the home view to render by returning its name.
     */    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Locale locale, Model model) {
                
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate );
        model.addAttribute("error", "false");

        return "register";
    }
    
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String doRegister(Locale locale, Model model, HttpServletRequest request) {
                
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);        
        
        //Setea las variables del producto a guardar
        /*User usuario = new User();
        usuario.setUsername(request.getParameter("txtEmail"));
        usuario.setPassword(passwordEncoder.encode(request.getParameter("txtClave")));
        usuario.setNombres(request.getParameter("txtNombres"));
        usuario.setApellidos(request.getParameter("txtApellidos"));
        usuario.setNumero_identificacion(request.getParameter("txtNumIdentificacion"));
        usuario.setPerfil(Integer.parseInt(request.getParameter("sltPerfil")));
        usuario.setGenero(Integer.parseInt(request.getParameter("sltGenero")));
        
        //Invoca al adicionar en BD
        Boolean exito = registerService.registraUsuario(usuario);

        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            model.addAttribute("error", "true");
        else
            model.addAttribute("error", "false");*/

        return "register";
    }
}