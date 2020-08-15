package com.jero.system.spring.controller;

import com.jero.system.spring.model.Usuario;
import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.jero.system.spring.service.UsuariosService;
import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author sala4
 */
@Controller
public class UsuariosController {

    @Autowired
    private UsuariosService usuarioService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
             
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public String productos(Locale locale, Model model, Principal principal) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);
        
        //Obtiene la lista de productos disponibles
        List lstDatos = usuarioService.listarUsuarios(-1);                

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );
        model.addAttribute("lstDatos", lstDatos);
        model.addAttribute("logueado", principal.getName());

        return "usuarios";
    }
    
    @RequestMapping(value = "/crearUsuario", method = RequestMethod.GET)
    public String crearUsuario(Locale locale, Model model, Principal principal, HttpServletRequest request) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = new Usuario();
        
        if(id != 0)        
            usuario = (Usuario) usuarioService.listarUsuarios(id).get(0);                
                
        String formattedDate = dateFormat.format(date);

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );        
        model.addAttribute("logueado", principal.getName());
        model.addAttribute("usuario", usuario);

        return "crearUsuario";
    }
    
    @RequestMapping(value = "/doCrearUsuario", method = RequestMethod.POST)
    public String doCrearUsuario(Locale locale, Model model, Principal principal, HttpServletRequest request, HttpSession session) {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        int id = Integer.parseInt(request.getParameter("hfId"));
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        Boolean exito;
        
        String formattedDate = dateFormat.format(date);        
        
        //Setea las variables del producto a guardar
        Usuario usuario = new Usuario();
        usuario.setId_usuario(id);        
        usuario.setUsername(request.getParameter("txtUsuario"));
        usuario.setPassword(passwordEncoder.encode(request.getParameter("txtClave")));
        usuario.setNombres(request.getParameter("txtNombres"));        
        usuario.setCategoria_perfil(Integer.parseInt(request.getParameter("sltPerfil")));        
        usuario.setUsuario_id_adiciono(Integer.parseInt(session.getAttribute("userId").toString()));
        usuario.setEstado(Integer.parseInt(request.getParameter("sltEstado")));
        
        //Invoca al adicionar o modificar en BD
        if(id == 0)
            exito = usuarioService.creaUsuario(usuario);
        else
            exito = usuarioService.editaUsuario(usuario);
                    
        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            model.addAttribute("error", "true");
        else
            model.addAttribute("error", "false");

        return "redirect:/usuarios";
    }
}