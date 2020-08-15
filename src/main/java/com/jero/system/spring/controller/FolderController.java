/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jero.system.spring.controller;

import com.jero.system.spring.model.Folder;
import com.jero.system.spring.service.AfiliadosService;
import com.jero.system.spring.service.FolderService;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class FolderController {

    @Autowired
    private FolderService folderService;
    
    @Autowired
    private AfiliadosService afiliadosService;
             
    @RequestMapping(value = "/folders", method = RequestMethod.GET)
    public String productos(Locale locale, Model model, Principal principal) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);
        
        //Obtiene la lista de productos disponibles
        List lstDatos = folderService.listarFolder(-1);                

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );
        model.addAttribute("lstDatos", lstDatos);
        model.addAttribute("logueado", principal.getName());

        return "folders";
    }
    
    @RequestMapping(value = "/crearFolder", method = RequestMethod.GET)
    public String crearUsuario(Locale locale, Model model, Principal principal, HttpServletRequest request) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        int id = Integer.parseInt(request.getParameter("id"));
        Folder folder = new Folder();
        int carpeta = folderService.listarFolder(-1).size();                
        
        if(id != 0)        
        {
            folder = (Folder) folderService.listarFolder(id).get(0);                
            carpeta = folder.getIdFolder();
        }
                
        String formattedDate = dateFormat.format(date);

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );        
        model.addAttribute("logueado", principal.getName());
        model.addAttribute("folder", folder);
        model.addAttribute("carpeta", carpeta);

        return "crearFolder";
    }
    
    @RequestMapping(value = "/crearAfiliadoPorCarpeta", method = RequestMethod.GET)
    public String crearAfiliadoPorCarpeta(Locale locale, Model model, Principal principal, HttpServletRequest request) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        int id = Integer.parseInt(request.getParameter("id"));
        Folder folder = (Folder) folderService.listarFolder(id).get(0);
        List lstAfiliadosFolder = folderService.listarFolderPorFolder(id);                
        String error = request.getParameter("error");
                                    
        //Obtiene la lista de productos disponibles
        List lstAfiliados = afiliadosService.listarAfiliados(-1);                
        
        String formattedDate = dateFormat.format(date);
        
        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );        
        model.addAttribute("logueado", principal.getName());
        model.addAttribute("folder", folder);
        model.addAttribute("lstAfiliados", lstAfiliados);        
        model.addAttribute("lstAfiliadosFolder", lstAfiliadosFolder);                
        model.addAttribute("error", error);

        return "crearAfiliadoPorCarpeta";
    }
    
    @RequestMapping(value = "/docrearFolder", method = RequestMethod.POST)
    public String doCrearUsuario(Locale locale, Model model, Principal principal, HttpServletRequest request, HttpSession session) throws ParseException {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        int id = Integer.parseInt(request.getParameter("hfId"));
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        Boolean exito;
        
        String formattedDate = dateFormat.format(date);        
        
        //Setea las variables del producto a guardar
        Folder folder = new Folder();
        folder.setIdFolder(id);        
        folder.setNombre_folder(request.getParameter("txtNombres"));                        
        folder.setFecha_registro(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("txtFechaRegistro")));
        folder.setUsuario_id_adiciono(Integer.parseInt(session.getAttribute("userId").toString()));        
        
        //Invoca al adicionar o modificar en BD
        if(id == 0)
            exito = folderService.creaFolder(folder);
        else
            exito = folderService.editaFolder(folder);
                    
        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            model.addAttribute("error", "true");
        else
            model.addAttribute("error", "false");

        return "redirect:/folders";
    }
    
    @RequestMapping(value = "/docrearAfiliadoFolder", method = RequestMethod.POST)
    public String docrearAfiliadoFolder(Locale locale, Model model, Principal principal, HttpServletRequest request, HttpSession session) throws ParseException {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        int id = Integer.parseInt(request.getParameter("hfId"));
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        Boolean exito;
        String error = "false";
        
        String formattedDate = dateFormat.format(date);        
        
        //Setea las variables del producto a guardar
        Folder folder = new Folder();
        folder.setIdFolder(id);        
        folder.setId_persona(Integer.parseInt(request.getParameter("sltAfiliado")));
        folder.setTipo_afiliado(request.getParameter("sltTipoAfiliado"));
        folder.setUsuario_id_adiciono(Integer.parseInt(session.getAttribute("userId").toString()));        
        
        //Invoca al adicionar o modificar en BD
        exito = folderService.creaFolderPorAfiliado(folder);
                    
        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            error = "true";
        
        String url = "/crearAfiliadoPorCarpeta?id=" + String.valueOf(id) +  "&error=" + error;

        return "redirect:" + url; 
    }
    
    @RequestMapping(value = "/dodeleteFolderAfil", method = RequestMethod.GET)
    public String dodeleteFolderAfil(Locale locale, Model model, Principal principal, HttpServletRequest request, HttpSession session) throws ParseException {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        int id = Integer.parseInt(request.getParameter("idFolder"));
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        Boolean exito;
        String error = "false";
        
        String formattedDate = dateFormat.format(date);        
        
        //Setea las variables del producto a guardar
        Folder folder = new Folder();
        folder.setIdFolder(id);        
        folder.setId_persona(Integer.parseInt(request.getParameter("idAfiliado")));
        folder.setUsuario_id_adiciono(Integer.parseInt(session.getAttribute("userId").toString()));        
        
        //Invoca al adicionar o modificar en BD
        exito = folderService.eliminaFolderPorAfiliado(folder);
                    
        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            error = "true";
        
        String url = "/crearAfiliadoPorCarpeta?id=" + String.valueOf(id) +  "&error=" + error;

        return "redirect:" + url; 
    }
}