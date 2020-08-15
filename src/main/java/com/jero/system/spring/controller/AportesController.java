/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jero.system.spring.controller;

import com.jero.system.spring.model.Aportes;
import com.jero.system.spring.service.AportesService;
import com.jero.system.spring.service.FolderService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 
 */
@Controller
public class AportesController {

    @Autowired
    private AportesService aportesService;
    
    @Autowired
    private FolderService folderService;
             
    @RequestMapping(value = "/aportes", method = RequestMethod.GET)
    public String productos(Locale locale, Model model, Principal principal) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);
        
        //Obtiene la lista de productos disponibles
        List lstDatos = aportesService.listarAportes(-1);                

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );
        model.addAttribute("lstDatos", lstDatos);
        model.addAttribute("logueado", principal.getName());

        return "aportes";
    }
        
    @RequestMapping(value = "/crearAporte", method = RequestMethod.GET)
    public String crearUsuario(Locale locale, Model model, Principal principal, HttpServletRequest request) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        int id = Integer.parseInt(request.getParameter("id"));
        Aportes aportes = new Aportes();
        
        if(id != 0)        
            aportes = (Aportes) aportesService.listarAportes(id).get(0);                
        
        List lstFolders = folderService.listarFolder(-1);                
                
        String formattedDate = dateFormat.format(date);

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );        
        model.addAttribute("logueado", principal.getName());
        model.addAttribute("aportes", aportes);
        model.addAttribute("lstFordes", lstFolders);

        return "crearAporte";
    }
    
    @RequestMapping(value = "/docrearAporte", method = RequestMethod.POST)
    public String doCrearUsuario(@RequestParam("fuImagen") MultipartFile fuImagen, Locale locale, 
            Model model, Principal principal, HttpServletRequest request, HttpSession session) throws ParseException, IOException {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        int id = Integer.parseInt(request.getParameter("hfId"));
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        Boolean exito;
        
        String formattedDate = dateFormat.format(date);        
        String soporte;
        
        if (!fuImagen.isEmpty()) {
            //Obtiene y guarda las imagenes
            byte[] bytes = fuImagen.getBytes();
            Path path = Paths.get(request.getServletContext().getRealPath("/") + "/resources/imagenes/ap_" + fuImagen.getOriginalFilename());
            Files.write(path, bytes);
            soporte = "ap_" + fuImagen.getOriginalFilename();
        }
        else
            soporte = request.getParameter("hfSoporte");
            
        //Setea las variables del producto a guardar
        Aportes aporte = new Aportes();
        aporte.setIdAporte(id);        
        aporte.setIdFolder(Integer.parseInt(request.getParameter("hfIdFolder")));        
        aporte.setFecha_registro_aporte(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("txtFechaRegistro")));        
        aporte.setSoporte_aporte(soporte);                        
        
        String valor = request.getParameter("txtValorAporte").replace(",", "");
            
        if(valor.length() > 3 && valor.contains("."))
            valor = valor.substring(0, request.getParameter("txtValorAporte").length() - 4);
        
        aporte.setValor_aporte(Integer.parseInt(valor));        
        aporte.setUsuario_id_adiciono(Integer.parseInt(session.getAttribute("userId").toString()));        
        
        //Invoca al adicionar o modificar en BD
        if(id == 0)
            exito = aportesService.crearAporte(aporte);
        else
            exito = aportesService.editarAporte(aporte);
                    
        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            model.addAttribute("error", "true");
        else
            model.addAttribute("error", "false");

        return "redirect:/aportes";
    }
}