/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jero.system.spring.controller;


import com.jero.system.spring.model.AhorroVoluntario;
import com.jero.system.spring.service.AhorroVolService;
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
public class AhorroVolController {

    @Autowired
    private AhorroVolService ahorroVolService;
    
    @Autowired
    private FolderService folderService;
             
    @RequestMapping(value = "/ahorroVoluntario", method = RequestMethod.GET)
    public String productos(Locale locale, Model model, Principal principal) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);
        
        //Obtiene la lista de productos disponibles
        List lstDatos = ahorroVolService.listarAhorroVol(-1);                

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );
        model.addAttribute("lstDatos", lstDatos);
        model.addAttribute("logueado", principal.getName());

        return "ahorroVoluntario";
    }
    
    
    @RequestMapping(value = "/crearAhorroVol", method = RequestMethod.GET)
    public String crearAhorroVol(Locale locale, Model model, Principal principal, HttpServletRequest request) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        int id = Integer.parseInt(request.getParameter("id"));
        AhorroVoluntario ahorroVol = new AhorroVoluntario();
        
        if(id != 0)        
            ahorroVol = (AhorroVoluntario) ahorroVolService.listarAhorroVol(id).get(0);                
        
        List lstFolders = folderService.listarFolder(-1);                
                
        String formattedDate = dateFormat.format(date);

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );        
        model.addAttribute("logueado", principal.getName());
        model.addAttribute("ahorroVol", ahorroVol);
        model.addAttribute("lstFordes", lstFolders);

        return "crearAhorroVol";
    }
    
    @RequestMapping(value = "/crearComprobantePorAhorro", method = RequestMethod.GET)
    public String crearComprobantePorAhorro(Locale locale, Model model, Principal principal, HttpServletRequest request) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        int id = Integer.parseInt(request.getParameter("id"));
        AhorroVoluntario ahorroVol = new AhorroVoluntario();
        List lstComprobantes = null;
        
        if(id != 0)        
        {
            ahorroVol = (AhorroVoluntario) ahorroVolService.listarAhorroVol(id).get(0);                
            lstComprobantes = ahorroVolService.listarComprobantesAhorroVol(id);                
        }
        
        String formattedDate = dateFormat.format(date);

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );        
        model.addAttribute("logueado", principal.getName());
        model.addAttribute("ahorroVol", ahorroVol);
        model.addAttribute("lstComprobantes", lstComprobantes);

        return "crearComprobantePorAhorro";
    }
    
    @RequestMapping(value = "/docrearAhorroVol", method = RequestMethod.POST)
    public String docrearAhorroVol(@RequestParam("fuImagen") MultipartFile fuImagen, Locale locale, 
            Model model, Principal principal, HttpServletRequest request, HttpSession session) throws ParseException, IOException {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        String valor;
        int id = Integer.parseInt(request.getParameter("hfId"));
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        Boolean exito;
        
        String formattedDate = dateFormat.format(date);        
        String soporte;
        
        if (!fuImagen.isEmpty()) {
            //Obtiene y guarda las imagenes
            byte[] bytes = fuImagen.getBytes();
            Path path = Paths.get(request.getServletContext().getRealPath("/") + "/resources/imagenes/av_" + fuImagen.getOriginalFilename());
            Files.write(path, bytes);
            soporte = "av_" + fuImagen.getOriginalFilename();
        }
        else
            soporte = request.getParameter("hfSoporte");
            
        //Setea las variables del producto a guardar
        AhorroVoluntario ahorroVol = new AhorroVoluntario();
        ahorroVol.setId_ahorro_voluntario(id);        
        ahorroVol.setId_folder(Integer.parseInt(request.getParameter("hfIdFolder")));        
        
        valor = request.getParameter("txtValorAporte").replace(",", "");
            
        if(valor.length() > 3 && valor.contains("."))
            valor = valor.substring(0, request.getParameter("txtValorAporte").length() - 4);
        
        ahorroVol.setValor_ahorro(Integer.parseInt(valor));                
        ahorroVol.setFecha_max_pago(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("txtFechaMaxPago")));        
        ahorroVol.setSoporte(soporte);                                
        
        valor = request.getParameter("txtValorTotalAporte").replace(",", "");
            
        if(valor.length() > 3 && valor.contains("."))
            valor = valor.substring(0, request.getParameter("txtValorTotalAporte").length() - 4);
        
        ahorroVol.setValor_total_ahorro(Integer.parseInt(valor));                
        ahorroVol.setFecha_fin_ahorro(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("txtFechaFinAhorro")));        
        ahorroVol.setUsuario_id_adiciono(Integer.parseInt(session.getAttribute("userId").toString()));        
        
        //Invoca al adicionar o modificar en BD
        if(id == 0)
            exito = ahorroVolService.creaAhorroVol(ahorroVol);
        else
            exito = ahorroVolService.editaAhorroVol(ahorroVol);
                    
        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            model.addAttribute("error", "true");
        else
            model.addAttribute("error", "false");

        return "redirect:/ahorroVoluntario";
    }
    
    @RequestMapping(value = "/docrearComprobantePorAhorro", method = RequestMethod.POST)
    public String docrearComprobantePorAhorro(@RequestParam("fuImagen") MultipartFile fuImagen, Locale locale, 
            Model model, Principal principal, HttpServletRequest request, HttpSession session) throws ParseException, IOException {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        String valor;
        int id = Integer.parseInt(request.getParameter("hfId"));
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        Boolean exito;
        
        String formattedDate = dateFormat.format(date);        
        String soporte;
        
        if (!fuImagen.isEmpty()) {
            //Obtiene y guarda las imagenes
            byte[] bytes = fuImagen.getBytes();
            Path path = Paths.get(request.getServletContext().getRealPath("/") + "/resources/imagenes/dah_" + fuImagen.getOriginalFilename());
            Files.write(path, bytes);
            soporte = "dah_" + fuImagen.getOriginalFilename();
        }
        else
            soporte = request.getParameter("fuImagen");
            
        //Setea las variables del producto a guardar
        AhorroVoluntario ahorroVol = new AhorroVoluntario();
        ahorroVol.setId_ahorro_voluntario(id);        
        ahorroVol.setFecha_registro(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("txtFechaMaxPago")));                
        ahorroVol.setSoporte(soporte);                                            
        ahorroVol.setUsuario_id_adiciono(Integer.parseInt(session.getAttribute("userId").toString()));        
        
        //Invoca al adicionar en BDs
        exito = ahorroVolService.creaComprobanteAhorro(ahorroVol);
        
        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            model.addAttribute("error", "true");
        else
            model.addAttribute("error", "false");

        return "redirect:/crearComprobantePorAhorro?id=" + id;
    }    
    
    @RequestMapping(value = "/doEliminarComprobantePorAhorro", method = RequestMethod.GET)
    public String doEliminarComprobantePorAhorro(Locale locale, Model model, Principal principal, 
            HttpServletRequest request, HttpSession session) throws ParseException, IOException {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();        
        int id = Integer.parseInt(request.getParameter("idComprobante"));
        int idAhorro = Integer.parseInt(request.getParameter("idAhorro"));        
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        Boolean exito;        
        String formattedDate = dateFormat.format(date);        
        
        //Invoca al adicionar en BDs
        exito = ahorroVolService.eliminarAhorroVol(id);
        
        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            model.addAttribute("error", "true");
        else
            model.addAttribute("error", "false");

        return "redirect:/crearComprobantePorAhorro?id=" + idAhorro;
    }    
}