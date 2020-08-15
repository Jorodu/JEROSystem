package com.jero.system.spring.controller;

import com.jero.system.spring.model.Afiliado;
import com.jero.system.spring.model.Folder;
import com.jero.system.spring.service.AfiliadosService;
import com.jero.system.spring.service.ConfiguracionService;
import com.jero.system.spring.service.FolderService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

/**
 *
 * @author 
 */
@Controller
public class AfiliadosController {

    @Autowired
    private AfiliadosService afiliadosService;
    
    @Autowired
    private ConfiguracionService configuracionService;
    
    @Autowired
    private FolderService folderService;
             
    @RequestMapping(value = "/afiliados", method = RequestMethod.GET)
    public String productos(Locale locale, Model model, Principal principal) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
           
        String formattedDate = dateFormat.format(date);
        
        //Obtiene la lista de productos disponibles
        List lstDatos = afiliadosService.listarAfiliados(-1);                
        
        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );
        model.addAttribute("lstDatos", lstDatos);
        model.addAttribute("logueado", principal.getName());

        return "afiliados";
    }
    
    @RequestMapping(value = "/crearDocumentosPorAfiliado", method = RequestMethod.GET)
    public String crearDocumentosPorAfiliado(Locale locale, Model model, Principal principal, HttpServletRequest request) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        int id = Integer.parseInt(request.getParameter("id"));
        Afiliado afiliado = (Afiliado) afiliadosService.listarAfiliados(id).get(0);
        List lstDocumentosAfiliados = afiliadosService.listarDocumentoPorAfiliado(id);                
        String error = request.getParameter("error");
        
        //Obtiene la lista de productos disponibles
        List lstTiposDocumento = configuracionService.listarCategoriasPorTipoCat(9);                
        
        String formattedDate = dateFormat.format(date);
        
        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );        
        model.addAttribute("logueado", principal.getName());
        model.addAttribute("afiliado", afiliado);
        model.addAttribute("lstTiposDocumento", lstTiposDocumento);        
        model.addAttribute("lstDocumentosAfiliados", lstDocumentosAfiliados);                
        model.addAttribute("error", error);

        return "crearDocumentosPorAfiliado";
    }
            
    @RequestMapping(value = "/docrearDocumentosPorAfiliado", method = RequestMethod.POST)
    public String docrearDocumentosPorAfiliado(@RequestParam("fuImagen") MultipartFile fuImagen, Locale locale, Model model, 
            Principal principal, HttpServletRequest request, HttpSession session) throws ParseException, IOException {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        int id = Integer.parseInt(request.getParameter("hfId"));
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        Boolean exito;
        String error = "false";
        String formattedDate = dateFormat.format(date); 
        String foto;
                    
        if (!fuImagen.isEmpty()) {
            //Obtiene y guarda las imagenes
            byte[] bytes = fuImagen.getBytes();
            Path path = Paths.get(request.getServletContext().getRealPath("/") + "/resources/imagenes/docaf_" + fuImagen.getOriginalFilename());
            Files.write(path, bytes);
            foto = "docaf_" + fuImagen.getOriginalFilename();
        }
        else
            foto = request.getParameter("hfSoporte");
        
        //Setea las variables del producto a guardar
        Afiliado afiliado = new Afiliado();
        afiliado.setId_persona(id);        
        afiliado.setCategoria_tipo_documento(Integer.parseInt(request.getParameter("sltTipoDocuemtno")));
        afiliado.setDocumento(foto);
        afiliado.setUsuario_id_adiciono(Integer.parseInt(session.getAttribute("userId").toString()));        
         
        //Invoca al adicionar o modificar en BD
        exito = afiliadosService.creDocumentoAfiliado(afiliado);
                    
        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            error = "true";
        
        String url = "/crearDocumentosPorAfiliado?id=" + String.valueOf(id) +  "&error=" + error;

        return "redirect:" + url; 
    }
    
    @RequestMapping(value = "/doDeleteDocumentosPorAfiliado", method = RequestMethod.GET)
    public String dodeleteFolderAfil(Locale locale, Model model, Principal principal, HttpServletRequest request, HttpSession session) throws ParseException {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        int id = Integer.parseInt(request.getParameter("IdPersona"));
        int idDocumento = Integer.parseInt(request.getParameter("idDocumento"));
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        Boolean exito;
        String error = "false";
        
        String formattedDate = dateFormat.format(date);        
        
        //Setea las variables del producto a guardar
        Afiliado afiliado = new Afiliado();
        afiliado.setId_persona(id);        
        afiliado.setCategoria_tipo_documento(idDocumento);     
        
        //Invoca al adicionar o modificar en BD
        exito = afiliadosService.EliminaDocumentoAfiliado(afiliado);
                    
        model.addAttribute("serverTime", formattedDate );
        
        //Valida si se guarda exitosamente.
        if(!exito)
            error = "true";
        
        String url = "/crearDocumentosPorAfiliado?id=" + String.valueOf(id) +  "&error=" + error;

        return "redirect:" + url; 
    }
    
    @RequestMapping(value = "/crearAfiliado", method = RequestMethod.GET)
    public String crearUsuario(Locale locale, Model model, Principal principal, HttpServletRequest request) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "redirect:/login";
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        int id = Integer.parseInt(request.getParameter("id"));
        Afiliado afiliado = new Afiliado();
        
        if(id != 0)        
            afiliado = (Afiliado) afiliadosService.listarAfiliados(id).get(0);                
                    
        List lstTiposIdent = configuracionService.listarCategoriasPorTipoCat(4);                
        List lstBancos = configuracionService.listarCategoriasPorTipoCat(2);                
        List lstCajasComp = configuracionService.listarCategoriasPorTipoCat(3);                
        List lstGenero = configuracionService.listarCategoriasPorTipoCat(5);                
        List lstEPS = configuracionService.listarCategoriasPorTipoCat(6);
        List lstEstadoCivil = configuracionService.listarCategoriasPorTipoCat(7);
        List lstParentesco = configuracionService.listarCategoriasPorTipoCat(8);
        List lstDocumentoPersona = configuracionService.listarCategoriasPorTipoCat(9);
        List lstOcupaciones = configuracionService.listarCategoriasPorTipoCat(10);
                
        String formattedDate = dateFormat.format(date);

        //Pasa los datos a la vista
        model.addAttribute("serverTime", formattedDate );        
        model.addAttribute("logueado", principal.getName());
        model.addAttribute("afiliado", afiliado);
        model.addAttribute("lstTiposIdent", lstTiposIdent);
        model.addAttribute("lstBancos", lstBancos);
        model.addAttribute("lstCajasComp", lstCajasComp);
        model.addAttribute("lstGenero", lstGenero);
        model.addAttribute("lstEPS", lstEPS);
        model.addAttribute("lstEstadoCivil", lstEstadoCivil);
        model.addAttribute("lstParentesco", lstParentesco);
        model.addAttribute("lstDocumentoPersona", lstDocumentoPersona);
        model.addAttribute("lstOcupaciones", lstOcupaciones);

        return "crearAfiliado";
    }
    
    @RequestMapping(value = "/docrearAfiliado", method = RequestMethod.POST)
    public String doCrearUsuario(@RequestParam("fuImagen") MultipartFile fuImagen, Locale locale, 
            Model model, Principal principal, HttpServletRequest request, HttpSession session) throws ParseException, IOException {
                
        if(principal.getName() == null)
            return "redirect:/login";
        
        int id = Integer.parseInt(request.getParameter("hfId"));        
        Boolean exito = false;
        String foto;
        String valor;
                    
        if (!fuImagen.isEmpty()) {
            //Obtiene y guarda las imagenes
            byte[] bytes = fuImagen.getBytes();
            Path path = Paths.get(request.getServletContext().getRealPath("/") + "/resources/imagenes/af_" + fuImagen.getOriginalFilename());
            Files.write(path, bytes);
            foto = "af_" + fuImagen.getOriginalFilename();
        }
        else
            foto = request.getParameter("hfSoporte");
        
        //Setea las variables del producto a guardar
        Afiliado afiliado = new Afiliado();
        afiliado.setId_persona(id);        
        afiliado.setCategoria_tipo_identificacion(Integer.parseInt(request.getParameter("sltTipoIdentificacion")));    
        afiliado.setNumero_identicicacion(request.getParameter("txtNumeroIdentificacion"));    
        afiliado.setNombres(request.getParameter("txtNombres"));    
        afiliado.setPrimer_apellido(request.getParameter("txtPrimerApellido"));    
        afiliado.setSegundo_apellido(request.getParameter("txtSegundoApellido"));    
        afiliado.setTelefono(Long.parseLong(request.getParameter("txtTelefono")));
        afiliado.setCelular(Long.parseLong(request.getParameter("txtCelular")));
        afiliado.setLocalidad(request.getParameter("txtLocalidad"));    
        afiliado.setBarrio(request.getParameter("txtBarrio"));    
        afiliado.setFecha_nacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("txtFechaNacimiento")));        
        afiliado.setCorreo_electronico(request.getParameter("txtCorreoElectronico"));    
        afiliado.setPuntaje_sisben(Double.parseDouble(request.getParameter("txtPuntajeSisben")));    
        afiliado.setAfiliado_fna(request.getParameter("sltAfiliadoFNA"));    
        afiliado.setCategoria_caja_compens(Integer.parseInt(request.getParameter("sltCajaComp")));
        afiliado.setAhorro_bancario(request.getParameter("sltAhorroBanc"));    
        afiliado.setCategoria_banco(Integer.parseInt(request.getParameter("sltBanco")));    
        
        if(!request.getParameter("txtValorAhoProg").equals(""))            
        {
            valor = request.getParameter("txtValorAhoProg").replace(",", "");
            
            if(valor.length() > 3 && valor.contains("."))
                valor = valor.substring(0, request.getParameter("txtValorAhoProg").length() - 4);
            
            afiliado.setValor_ahorro_prog(Integer.parseInt(valor));
        }
        else
            afiliado.setValor_ahorro_prog(0);
        
        afiliado.setOtras_cuentas(request.getParameter("sltOtrasCuentas"));    
        
        if(!request.getParameter("txtValorOtrasCuentas").equals(""))        
        {
            valor = request.getParameter("txtValorOtrasCuentas").replace(",", "");
            
            if(valor.length() > 3 && valor.contains("."))
                valor = valor.substring(0, request.getParameter("txtValorOtrasCuentas").length() - 4);
            
            afiliado.setValor_otras_cuentas(Integer.parseInt(valor));    
        }
        else
            afiliado.setValor_otras_cuentas(0);    
            
        if(!request.getParameter("txtValorCesantias").equals(""))    
        {
            valor = request.getParameter("txtValorCesantias").replace(",", "");
            
            if(valor.length() > 3 && valor.contains("."))
                valor = valor.substring(0, request.getParameter("txtValorCesantias").length() - 4);
            
            afiliado.setValor_cesantias(Integer.parseInt(valor));    
        }
        else
            afiliado.setValor_cesantias(0);    
            
        afiliado.setOcupacion(request.getParameter("txtOcupacion"));    
        afiliado.setTiene_discapacidad(request.getParameter("sltTieneDiscapacidad"));  
        
        if(!request.getParameter("txtDiscapacidad").equals(""))        
            afiliado.setDiscapacidad(request.getParameter("txtDiscapacidad"));    
        else
            afiliado.setDiscapacidad("");
        
        afiliado.setCategoria_genero(Integer.parseInt(request.getParameter("sltGenero")));    
        afiliado.setCategoria_eps(Integer.parseInt(request.getParameter("sltEPS")));    
        afiliado.setFallecido(request.getParameter("sltFallecido"));    
        afiliado.setCategoria_parentesco(Integer.parseInt(request.getParameter("sltParentesco")));    
        afiliado.setCategoria_estado_civil(Integer.parseInt(request.getParameter("sltEstadoCivil")));    
        afiliado.setEstado(Integer.parseInt(request.getParameter("sltEstado")));        
        afiliado.setFoto(foto);
        afiliado.setUsuario_id_adiciono(Integer.parseInt(session.getAttribute("userId").toString()));        
        
        //Invoca al adicionar o modificar en BD
        if(id == 0)
            exito = afiliadosService.crearAfiliado(afiliado);
        else
            exito = afiliadosService.editaAfiliado(afiliado);
        
        //Valida si se guarda exitosamente.
        if(!exito)
            model.addAttribute("error", "true");
        else
            model.addAttribute("error", "false");

        return "redirect:/afiliados";
    }
    
    @RequestMapping(value = "/getpdf", method = RequestMethod.GET)
    public ResponseEntity<byte[]>  getpdf(Locale locale, Model model, Principal principal, HttpServletRequest request) throws IOException {        
        
        String filename = request.getServletContext().getRealPath("/") + "/WEB-INF/views/certificado.pdf";
        String numero_identificacion = request.getParameter("id");
        File file = new File(filename);
        byte[] contents = Files.readAllBytes(file.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);        
        headers.setContentDispositionFormData(filename, "Certificado_" + numero_identificacion + ".pdf");        
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }
    
    @RequestMapping(value = "/getcarnet", method = RequestMethod.GET)
    public ResponseEntity<byte[]>  getcarnet(Locale locale, Model model, Principal principal, HttpServletRequest request) throws IOException {        
        
        String filename = request.getServletContext().getRealPath("/") + "/WEB-INF/views/carnet.pdf";
        String numero_identificacion = request.getParameter("id");
        File file = new File(filename);
        byte[] contents = Files.readAllBytes(file.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);        
        headers.setContentDispositionFormData(filename, "Carnet_" + numero_identificacion + ".pdf");        
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }
    
    @RequestMapping(value = "/consultaCertificado", method = RequestMethod.GET)
    public String consultaCertificad(Locale locale, Model model, Principal principal, HttpServletRequest request) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "login";
        
        int id = Integer.parseInt(request.getParameter("id"));
        Afiliado afiliado = new Afiliado();
        Folder folder = new Folder();
        List<Folder> lstfolder;
        
        if(id != 0)        
        {
            lstfolder = (List<Folder>) folderService.listarFolderPorPersona(id);
            
            if(lstfolder.size() > 0)
                folder = (Folder) folderService.listarFolderPorPersona(id).get(0);                
            
            afiliado = (Afiliado) afiliadosService.listarAfiliados(id).get(0);       
        }
            
         try {

            final W3CDom w3cDom = new W3CDom();
            final Document w3cDoc = w3cDom.fromJsoup(Jsoup.parse(readFile(request, afiliado, folder, locale)));
            final OutputStream outStream = new FileOutputStream(request.getServletContext().getRealPath("/") + "/WEB-INF/views/certificado.pdf");

            final PdfRendererBuilder pdfBuilder = new PdfRendererBuilder();
            pdfBuilder.useFastMode();
            pdfBuilder.withW3cDocument(w3cDoc, "/");            
            pdfBuilder.toStream(outStream);

            pdfBuilder.run();
            outStream.close();

        } catch (Exception e) {
            System.out.println("PDF could not be created: " + e.getMessage());
        }

        //Pasa los datos a la vista
        model.addAttribute("logueado", principal.getName());

        return "redirect: getpdf?id=" + afiliado.getNumero_identicicacion();
    }
    
    @RequestMapping(value = "/consultaCarnet", method = RequestMethod.GET)
    public String consultaCarnet(Locale locale, Model model, Principal principal, HttpServletRequest request) {
        
        //Valida si está logueado o no
        if(principal.getName() == null)
            return "login";
        
        int id = Integer.parseInt(request.getParameter("id"));
        Afiliado afiliado = new Afiliado();
        
        if(id != 0)        
            afiliado = (Afiliado) afiliadosService.listarAfiliados(id).get(0);      
            
         try {

            final W3CDom w3cDom = new W3CDom();
            final Document w3cDoc = w3cDom.fromJsoup(Jsoup.parse(readFileCarnet(request, afiliado)));
            final OutputStream outStream = new FileOutputStream(request.getServletContext().getRealPath("/") + "/WEB-INF/views/carnet.pdf");

            final PdfRendererBuilder pdfBuilder = new PdfRendererBuilder();
            pdfBuilder.useFastMode();
            pdfBuilder.withW3cDocument(w3cDoc, "/");            
            pdfBuilder.toStream(outStream);

            pdfBuilder.run();
            outStream.close();

        } catch (Exception e) {
            System.out.println("PDF could not be created: " + e.getMessage());
        }

        //Pasa los datos a la vista
        model.addAttribute("logueado", principal.getName());

        return "redirect: getcarnet?id=" + afiliado.getNumero_identicicacion();
    }
    
    private static String readFile(HttpServletRequest request, Afiliado afiliado, Folder folder, Locale locale) throws IOException {
        String ruta = request.getServletContext().getRealPath("/") + "/WEB-INF/views/certificado.html";        
        File file = new File(ruta);
        URL url = new URL(request.getRequestURL().toString());
        String dias = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd"));
        String mes = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM"));
        String anyo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy"));
        String folderDesc = "SIN ASIGNAR";
        
        if(folder.getNombre_folder() != null)
            folderDesc = String.valueOf(folder.getIdFolder());        
        
        // Si el archivo no existe es creado
        if (file.exists()) 
            file.delete();
            
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <body>\n" +
                "        <div align=\"left\" style=\"font-family: Arial;color: #111;font-size: 11px;\">\n" +
                "            <img src=\"http://" + url.getHost() + ":" + url.getPort() + request.getContextPath() + "/resources/imagenes/header_certificado.JPG\" width=\"740\" height=\"100\" alt=\"\"/>\n" +
                "            <div style=\"font-size: 20px;margin-left: 40px;margin-top: 200px; width: 90%; text-align: center;\">\n" +
                "               HACE CONSTAR QUE:\n" +
                "            </div>\n" +
                "            <div style=\"font-size: 15px;margin-left: 50px;margin-top: 50px; width: 90%; height: 300px;; text-align: justify;\">\n" +
                "                Mediante el presente documento que reconoce que al afiliado Se&ntilde;or(a) <b>" + afiliado.getNombre_completo() + "</b> " +
                "                identificado(a) con documento de identidad No. <b>" + afiliado.getNumero_identicicacion() + "</b> "+
                "                <b>Se encuentra ACTIVO CON NUMERO DE CARPETA N° FUN-" + folderDesc + " en la FUNDACI&Oacute;N MANOS AL CIELO DE PERSONAS CON DISCAPACIDAD, CUIDADORES (AS)</b>." +
                "                <br/><br/>\n" +                                
                "                Se expide la presente certificaci&oacute;n a solicitud del interesado, a los " + dias + " d&iacute;as del mes de " + nombreMes(Integer.parseInt(mes)) + " de " + anyo+ ".\n" +
                "            </div>\n" +
                "            <img src=\"http://" + url.getHost() + ":" + url.getPort() + request.getContextPath() + "/resources/imagenes/footer_certificado.JPG\" width=\"640\" height=\"320\" alt=\"\"/>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>");
        bw.close();

        final InputStream inputStream = new FileInputStream(file);
        final StringBuilder sb = new StringBuilder();
        final Reader r = new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
        char[] buf = new char[1024];
        int amt = r.read(buf);
        while(amt > 0) {
            sb.append(buf, 0, amt);
            amt = r.read(buf);
        }
        return sb.toString();
    }
    
    private static String readFileCarnet(HttpServletRequest request, Afiliado afiliado) throws IOException {
        String ruta = request.getServletContext().getRealPath("/") + "/WEB-INF/views/carnet.html";        
        File file = new File(ruta);
        URL url = new URL(request.getRequestURL().toString());
        
        // Si el archivo no existe es creado
        if (file.exists()) 
            file.delete();
            
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <body>\n" +
                    "        <div align=\"left\" style=\"background:url('http://" + url.getHost() + ":" + url.getPort() + request.getContextPath() + "/resources/imagenes/carnet_frontal.JPG');margin-left:100px; width: 463px; height: 710px; font-weight: bold;font-family: Arial;color: #111;font-size: 25px;\">\n" +
                    "            <div style=\"padding-top: 300px; width: 100%; text-align: center;\">\n" +
                    "				<div style=\"margin-left: 34.5%; width: 34%; text-align: center;border: 2px aolid #111;\">\n" +
                    "					<img src=\"http://" + url.getHost() + ":" + url.getPort() + request.getContextPath() + "/resources/imagenes/" + afiliado.getFoto() + "\" width=\"179\" height=\"190\" />\n" +
                    "				</div>\n" +
                    "            </div>\n" +
                    "			<div style=\"padding-top: 65px; width: 100%; text-align: center;\">\n" +
                    "                " + afiliado.getNombre_completo() + "\n" +
                    "            </div>\n" +
                    "			<div style=\"padding-left: 15px; padding-top: 6px; width: 100%; text-align: center;\">\n" +
                    "                " + afiliado.getNumero_identicicacion()+ "\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "		<div align=\"left\" style=\"background:url('http://" + url.getHost() + ":" + url.getPort() + request.getContextPath() + "/resources/imagenes/carnet_atras.JPG'); margin-left:100px; margin-top:340px; width: 463px; height: 710px; font-family: Arial;color: #111;font-size: 21px;\">\n" +
                    "            \n" +
                    "        </div>\n" +
                    "    </body>\n" +
                    "</html>");
        bw.close();

        final InputStream inputStream = new FileInputStream(file);
        final StringBuilder sb = new StringBuilder();
        final Reader r = new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
        char[] buf = new char[1024];
        int amt = r.read(buf);
        while(amt > 0) {
            sb.append(buf, 0, amt);
            amt = r.read(buf);
        }
        return sb.toString();
    }
    
    public static String nombreMes(int mes)
    {
        String valor = "";
        
        switch(mes)
        {
            case 1: valor = "Enero";
                    break;
            case 2: valor = "Febrero";
                    break;
            case 3: valor = "Marzo";
                    break;
            case 4: valor = "Abril";
                    break;
            case 5: valor = "Mayo";
                    break;
            case 6: valor = "Junio";
                    break;
            case 7: valor = "Julio";
                    break;
            case 8: valor = "Agosto";
                    break;         
            case 9: valor = "Septiembre";
                    break;         
            case 10: valor = "Octubre";
                    break;         
            case 11: valor = "Noviembre";
                    break;         
            case 12: valor = "Diciembre";
                    break;         
        }
        return valor;
    }
}