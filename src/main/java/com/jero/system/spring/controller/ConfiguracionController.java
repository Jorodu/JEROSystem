/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jero.system.spring.controller;

import com.jero.system.spring.model.Categorias;
import com.jero.system.spring.service.ConfiguracionService;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author
 */
@Controller
public class ConfiguracionController {

    @Autowired
    private ConfiguracionService configuracionService;
             
    @RequestMapping(value = "/configuracion", method = RequestMethod.GET)
    public String productos(Locale locale, Model model, Principal principal) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);
        
        //Obtiene la lista de productos disponibles
        List lstDatos = configuracionService.listarCategorias(-1);                

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );
        model.addAttribute("lstDatos", lstDatos);
        model.addAttribute("logueado", principal.getName());

        return "configuracion";
    }
    
    @RequestMapping(value = "/crearConfiguracion", method = RequestMethod.GET)
    public String crearUsuario(Locale locale, Model model, Principal principal, HttpServletRequest request) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        int id = Integer.parseInt(request.getParameter("id"));
        Categorias categoria = new Categorias();
        
        if(id != 0)        
            categoria = (Categorias) configuracionService.listarCategorias(id).get(0);                
                
        String formattedDate = dateFormat.format(date);

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );        
        model.addAttribute("logueado", principal.getName());
        model.addAttribute("categoria", categoria);

        return "crearConfiguracion";
    }
    
    @RequestMapping(value = "/docrearConfiguracion", method = RequestMethod.POST)
    public String doCrearUsuario(Locale locale, Model model, Principal principal, HttpServletRequest request, HttpSession session) throws ParseException {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        int id = Integer.parseInt(request.getParameter("hfId"));
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        Boolean exito = false;
        int idTipoCategoria;
        
        if(id == 0)
            idTipoCategoria = Integer.parseInt(request.getParameter("sltTipoCategoria"));        
        else
            idTipoCategoria = Integer.parseInt(request.getParameter("hfIdTipoCategoria"));                               
        
        String formattedDate = dateFormat.format(date);        
        
        //Setea las variables del producto a guardar
        Categorias categoria = new Categorias();
        categoria.setId_categoria(id);        
        categoria.setTipo_categoria_id(idTipoCategoria);        
        categoria.setDescripcion(request.getParameter("txtNombre"));                        
        categoria.setEstado_id(Integer.parseInt(request.getParameter("sltEstado")));
        categoria.setUsuario_id_adiciono(Integer.parseInt(session.getAttribute("userId").toString()));       
        
        //Invoca al adicionar o modificar en BD
        if(id == 0)
            exito = configuracionService.creaCategoria(categoria);
        else
            exito = configuracionService.editaCategoria(categoria);
                    
        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            model.addAttribute("error", "true");
        else
            model.addAttribute("error", "false");        

        return "redirect:/configuracion";
    }
    
}