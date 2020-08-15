/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jero.system.spring.controller;

import com.jero.system.spring.model.Afiliado;
import com.jero.system.spring.model.Aportes;
import com.jero.system.spring.model.GenerateExcelReport;
import com.jero.system.spring.service.AfiliadosService;
import com.jero.system.spring.service.AportesService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.document.AbstractXlsView;

/**
 *
 * @author sala4
 */

@SessionAttributes({"currentUser"})
@Controller
public class HomeController {

    @Autowired
    private AfiliadosService afiliadosService;
    @Autowired
    private AportesService aportesService;
    
    @GetMapping(value = "/home")
    public String home(Locale locale, Model model, HttpSession session, Principal principal) {
        
        //Valida si está logueado o no y pasa a la vista los parametros
        if(principal == null)    
            return "redirect:/login";    
    
        //Obtiene la información del usuario en sesión
        String user = session.getAttribute("currentUser").toString(); // + "(" + session.getAttribute("DescPerfil").toString() +  ")";              
        Object perfil = session.getAttribute("perfil");        
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        int sexoM = 0, sexoF = 0, activo = 0, inactivo = 0;
        Calendar cal= Calendar.getInstance();
        int anyo = cal.get(Calendar.YEAR);
        int ene = 0, feb = 0, mar = 0, abr = 0, may = 0, jun = 0, jul = 0, ago = 0, sep = 0, oct = 0, nov = 0, dic = 0;
        
        String formattedDate = dateFormat.format(date);
        
        //Obtiene la lista de productos disponibles
        List<Afiliado> lstDatos = (List<Afiliado>)afiliadosService.listarAfiliados(-1);                
        
        for (Afiliado afil : lstDatos) {
            
            if(afil.getCategoria_genero()== 57)
                sexoM++;
            else if(afil.getCategoria_genero() == 58)
                sexoF++;

            if(afil.getEstado()== 1)
                activo++;
            else if(afil.getEstado() == 2)
                inactivo++;
          
            switch(afil.getFecha_adiciono().getMonth() + 1)
            {
                case 1: ene++;
                        break;
                case 2: feb++;
                        break;
                case 3: mar++;
                        break;
                case 4: abr++;
                        break;
                case 5: may++;
                        break;
                case 6: jun++;
                        break;
                case 7: jul++;
                        break;
                case 8: ago++;
                        break;         
                case 9: sep++;
                        break;         
                case 10: oct++;
                        break;         
                case 11: nov++;
                        break;         
                case 12: dic++;
                        break;         
            }
                   
        }
                
        //Pasa parametros a la vista
        model.addAttribute("serverTime", formattedDate );
        model.addAttribute("currentUser", user);
        model.addAttribute("perfil", perfil);
        model.addAttribute("sexoM", sexoM);
        model.addAttribute("sexoF", sexoF);
        model.addAttribute("activo", activo);
        model.addAttribute("inactivo", inactivo);
        model.addAttribute("anyo", anyo);        
        model.addAttribute("ene", ene);   
        model.addAttribute("feb", feb);   
        model.addAttribute("mar", mar);           
        model.addAttribute("abr", abr);   
        model.addAttribute("may", may);           
        model.addAttribute("jun", jun);   
        model.addAttribute("jul", jul);   
        model.addAttribute("ago", ago);           
        model.addAttribute("sep", sep);   
        model.addAttribute("oct", oct);   
        model.addAttribute("nov", nov);   
        model.addAttribute("dic", dic);   
        
        return "home";
    }
    
    @GetMapping(value = "/generateAfiliados")
    public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
        List<Afiliado> lstDatos = (List<Afiliado>)afiliadosService.listarAfiliados(-1); 
        ByteArrayInputStream in = GenerateExcelReport.usersToExcel(lstDatos);
        // return IO ByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        // set filename in header
        headers.add("Content-Disposition", "attachment; filename=Afiliados.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
    
    @GetMapping(value = "/generateAportesPendientes")
    public ResponseEntity<InputStreamResource> excelCustomersAportes() throws IOException {
        List<Aportes> lstDatos = (List<Aportes>)aportesService.listarAportesPendientesmes(); 
        ByteArrayInputStream in = GenerateExcelReport.aportesToExcel(lstDatos);
        // return IO ByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        // set filename in header
        headers.add("Content-Disposition", "attachment; filename=AportesPendientesMes.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
    
    @GetMapping(value = "/generateAportesMes")
    public ResponseEntity<InputStreamResource> excelCustomersAportesMes() throws IOException {
        List<Aportes> lstDatos = (List<Aportes>)aportesService.listarAportesMes(); 
        ByteArrayInputStream in = GenerateExcelReport.aportesMesToExcel(lstDatos);
        // return IO ByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        // set filename in header
        headers.add("Content-Disposition", "attachment; filename=AportesMes.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}