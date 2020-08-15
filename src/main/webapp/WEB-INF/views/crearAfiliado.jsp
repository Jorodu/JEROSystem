<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"  %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

    <jsp:include page="MasterHead.jsp"></jsp:include>  

    <div style="text-align: center;">
        <div id="formulario" style="width: 95%;margin-left: 2%; text-align: center; background: #fff; padding: 1em;">
            <h3 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Datos Afiliado</h3>
            <form style="margin-left: 22%;" action='<%=request.getContextPath()%>/docrearAfiliado' id="registration"  name="registration" method="post" enctype="multipart/form-data">                            
                <table>
                    <tr>
                        <td  style="width: 50%;">
                            <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Tipo de identificación</label>                    
                            <input type="hidden" class="form-control" id="hfId" value="${afiliado.id_persona}" name="hfId">
                            <select class="form-control" id="sltTipoIdentificacion" name="sltTipoIdentificacion">
                                <option value="">Seleccione</option>
                                <c:forEach var="dato" items="${lstTiposIdent}">      
                                    <option value="${dato.id_categoria}">${dato.descripcion}</option>                            
                                </c:forEach>
                            </select>                    
                          </div>
                          <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Estado</label>                    
                            <select class="form-control" id="sltEstado" name="sltEstado">
                                <option value="">Seleccione</option>
                                <option value="1">Activo</option>
                                <option value="2">Inactivo</option>
                            </select>
                          </div>
                          <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Número de identificación</label>                                                                                          
                            <input type="text" class="form-control" maxlength="20" id="txtNumeroIdentificacion" value="${afiliado.numero_identicicacion}" name="txtNumeroIdentificacion" />                    
                          </div>
                          <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Nombres</label>                                                                                          
                            <input type="text" class="form-control" maxlength="100" id="txtNombres" value="${afiliado.nombres}" name="txtNombres" />                    
                          </div>
                          <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Primer Apellido</label>                                                                                          
                            <input type="text" class="form-control" maxlength="100" id="txtPrimerApellido" value="${afiliado.primer_apellido}" name="txtPrimerApellido" />                    
                          </div>
                          <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Segundo Apellido</label>                                                                                          
                            <input type="text" class="form-control" maxlength="100" id="txtSegundoApellido" value="${afiliado.segundo_apellido}" name="txtSegundoApellido" />                    
                          </div> 
                          <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Telefono</label>                                                                                          
                            <input type="text" onkeypress="return soloNumeros(event)" class="form-control" maxlength = "12" oninput="maxLengthCheck(this)" id="txtTelefono" value="${afiliado.telefono}" name="txtTelefono" />                    
                          </div> 
                          <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Celular</label>                                                                                          
                            <input type="text" onkeypress="return soloNumeros(event)" class="form-control" maxlength = "12" oninput="maxLengthCheck(this)" id="txtCelular" value="${afiliado.celular}" name="txtCelular" />                    
                          </div> 
                          <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Localidad</label>                                                                                          
                            <input type="text" class="form-control" maxlength="100" id="txtLocalidad" value="${afiliado.localidad}" name="txtLocalidad" />                    
                          </div> 
                          <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Barrio</label>                                                                                          
                            <input type="text" class="form-control" maxlength="100" id="txtBarrio" value="${afiliado.barrio}" name="txtBarrio" />                    
                          </div> 
                          <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Fecha de nacimiento</label>                                                  
                            <c:set var="fechaConv">
                                <fmt:formatDate pattern="dd/MM/yyyy" value="${afiliado.fecha_nacimiento}" />
                            </c:set>                    
                            <input type="text" class="form-control" onkeypress="return isNumberKey(event)" maxlength="10" id="txtFechaNacimiento" value="${fechaConv}" placeholder="Fecha" name="txtFechaNacimiento" />
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-th"></span>
                            </div
                          </div> 
                          <div class="form-group" style="text-align:left;">
                            <label for="exampleInputPassword1">Correo electrónico</label>                                                                                          
                            <input type="email" class="form-control" maxlength="100" id="txtCorreoElectronico" value="${afiliado.correo_electronico}" name="txtCorreoElectronico" />                    
                          </div>
                          <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Puntaje Sisben</label>                                                                                          
                                <input type="text" onkeypress="return soloNumerosDecimal(event)" class="form-control" maxlength = "5" oninput="maxLengthCheck(this)" id="txtPuntajeSisben" value="${afiliado.puntaje_sisben}" name="txtPuntajeSisben" />                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Afiliado FNA</label>                    
                                <select class="form-control" id="sltAfiliadoFNA" name="sltAfiliadoFNA">
                                    <option value="">Seleccione</option>
                                    <option value="S">Si</option>
                                    <option value="N">No</option>
                                </select>                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Caja de compensación</label>                    
                                <select class="form-control" id="sltCajaComp" name="sltCajaComp">
                                    <option value="">Seleccione</option>
                                    <c:forEach var="dato" items="${lstCajasComp}">      
                                        <option value="${dato.id_categoria}">${dato.descripcion}</option>                            
                                    </c:forEach>
                                </select>                    
                              </div>                              
                        </td>
                        <td  style="width: 50%;padding-top: 15px;padding-left: 15px;">
                            <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Ahorro bancario</label>                    
                                <select class="form-control" id="sltAhorroBanc" name="sltAhorroBanc">
                                    <option value="">Seleccione</option>
                                    <option value="S">Si</option>
                                    <option value="N">No</option>
                                </select>                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Banco</label>                    
                                <select class="form-control" id="sltBanco" name="sltBanco">
                                    <option value="">Seleccione</option>
                                    <c:forEach var="dato" items="${lstBancos}">      
                                        <option value="${dato.id_categoria}">${dato.descripcion}</option>                            
                                    </c:forEach>
                                </select>                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Valor ahorro programado</label>                                                                                          
                                <input type="text" class="form-control" maxlength = "12" id="txtValorAhoProg" value="${afiliado.valor_ahorro_prog}" name="txtValorAhoProg" />                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Otras cuentas</label>                    
                                <select class="form-control" id="sltOtrasCuentas" name="sltOtrasCuentas">
                                    <option value="">Seleccione</option>
                                    <option value="S">Si</option>
                                    <option value="N">No</option>
                                </select>                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Valor otras cuentas</label>                                                                                          
                                <input type="text" onkeypress="return soloNumeros(event)" class="form-control" maxlength = "12" oninput="maxLengthCheck(this)" id="txtValorOtrasCuentas" value="${afiliado.valor_otras_cuentas}" name="txtValorOtrasCuentas" />                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Valor cesantias</label>                                                                                          
                                <input type="text" onkeypress="return soloNumeros(event)" class="form-control" maxlength = "12" oninput="maxLengthCheck(this)" id="txtValorCesantias" value="${afiliado.valor_cesantias}" name="txtValorCesantias" />                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Ocupación</label>                                                                                                                          
                                <select class="form-control" id="txtOcupacion" name="txtOcupacion">
                                    <option value="">Seleccione</option>
                                    <c:forEach var="dato" items="${lstOcupaciones}">      
                                        <option value="${dato.id_categoria}">${dato.descripcion}</option>                            
                                    </c:forEach>
                                </select>  
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Tiene discapacidad</label>                    
                                <select class="form-control" id="sltTieneDiscapacidad" name="sltTieneDiscapacidad">
                                    <option value="">Seleccione</option>
                                    <option value="S">Si</option>
                                    <option value="N">No</option>
                                </select>                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Discapacidad</label>                                                                                          
                                <input type="text" class="form-control" maxlength="100" id="txtDiscapacidad" value="${afiliado.discapacidad}" name="txtDiscapacidad" />                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Genero</label>                    
                                <select class="form-control" id="sltGenero" name="sltGenero">
                                    <option value="">Seleccione</option>
                                    <c:forEach var="dato" items="${lstGenero}">      
                                        <option value="${dato.id_categoria}">${dato.descripcion}</option>                            
                                    </c:forEach>
                                </select>                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">EPS</label>                    
                                <select class="form-control" id="sltEPS" name="sltEPS">
                                    <option value="">Seleccione</option>
                                    <c:forEach var="dato" items="${lstEPS}">      
                                        <option value="${dato.id_categoria}">${dato.descripcion}</option>                            
                                    </c:forEach>
                                </select>                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Fallecido</label>                    
                                <select class="form-control" id="sltFallecido" name="sltFallecido">
                                    <option value="">Seleccione</option>
                                    <option value="S">Si</option>
                                    <option value="N">No</option>
                                </select>                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Parentesco</label>                    
                                <select class="form-control" id="sltParentesco" name="sltParentesco">
                                    <option value="">Seleccione</option>
                                    <c:forEach var="dato" items="${lstParentesco}">      
                                        <option value="${dato.id_categoria}">${dato.descripcion}</option>                            
                                    </c:forEach>
                                </select>                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Estado Civil</label>                    
                                <select class="form-control" id="sltEstadoCivil" name="sltEstadoCivil">
                                    <option value="">Seleccione</option>
                                    <c:forEach var="dato" items="${lstEstadoCivil}">      
                                        <option value="${dato.id_categoria}">${dato.descripcion}</option>                            
                                    </c:forEach>
                                </select>                    
                              </div>
                              <div class="form-group" style="text-align:left;">
                                <label for="exampleInputPassword1">Foto</label>                                                                                          
                                <input type="hidden" id="hfSoporte" value="${afiliado.foto}" name="hfSoporte" />
                                <input type="hidden" class="form-control" id="hfSize" name="hfSize" />
                                <a href="<%=request.getContextPath()%>/resources/imagenes/${afiliado.foto}" target="_blank">Ver</a>
                                <input type="file" class="form-control" id="fuImagen" name="fuImagen">
                              </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit" class="btn btn-primary">Registrar</button>                                
                            <button type="button" id="btnRegresar" class="btn btn-primary">Regresar</button>                                
                            <c:if test="${error == 'true'}">
                              <div class="alert alert-danger">
                                  <spring:message text="Error al registrar usuario."/>
                                  Error al crear el afiliado.
                                  <br/>
                              </div>
                            </c:if>
                        </td>
                    </tr>
                </table>                                     
                </div>
                  
            </form>
            <script type="text/javascript">                                
                                
                <c:if test="${afiliado.id_persona != 0}">
                    $("#sltEstado").val("${afiliado.estado}");
                    $("#sltTipoIdentificacion").val("${afiliado.categoria_tipo_identificacion}");
                    $("#sltBanco").val("${afiliado.categoria_banco}");
                    $("#sltCajaComp").val("${afiliado.categoria_caja_compens}");
                    $("#sltGenero").val("${afiliado.categoria_genero}");
                    $("#sltParentesco").val("${afiliado.categoria_parentesco}");
                    $("#sltEPS").val("${afiliado.categoria_eps}");               
                    $("#sltEstadoCivil").val("${afiliado.categoria_estado_civil}");   
                    $("#sltAfiliadoFNA").val("${afiliado.afiliado_fna}");   
                    $("#sltAhorroBanc").val("${afiliado.ahorro_bancario}");   
                    $("#sltOtrasCuentas").val("${afiliado.otras_cuentas}");   
                    $("#sltTieneDiscapacidad").val("${afiliado.tiene_discapacidad}");                       
                    $("#sltFallecido").val("${afiliado.fallecido}");                                           
                    $("#txtOcupacion").val("${afiliado.ocupacion}");   
                    $("#sltValorAhorroProg").val("${afiliado.valor_ahorro_prog}");
                </c:if>
                $("#registration").submit(function(e){
                    var faltantes = "";
                    
                    if($("#sltTipoIdentificacion").val() == "" || $("#sltEstado").val() == "" || $("#txtNumeroIdentificacion").val() == ""
                        || $("#txtNombres").val() == "" || $("#txtPrimerApellido").val() == "" || $("#txtSegundoApellido").val() == ""
                        || $("#txtTelefono").val() == "" || $("#txtCelular").val() == "" || $("#txtLocalidad").val() == ""
                        || $("#txtBarrio").val() == "" || $("#txtFechaNacimiento").val() == "" || $("#txtCorreoElectronico").val() == ""
                        || $("#txtPuntajeSisben").val() == "" || $("#sltAfiliadoFNA").val() == "" || $("#sltCajaComp").val() == ""
                        || $("#sltBanco").val() == "" || $("#sltOtrasCuentas").val() == "" || $("#txtValorCesantias").val() == ""
                        || $("#txtOcupacion").val() == "" || $("#sltTieneDiscapacidad").val() == ""
                        || $("#sltGenero").val() == "" || $("#sltEPS").val() == "" || $("#sltFallecido").val() == ""
                        || $("#sltParentesco").val() == "" || $("#sltEstadoCivil").val() == "" || $("#sltValorAhorroProg").val() == "")
                    {
                        if($("#sltTipoIdentificacion").val() == "")
                            faltantes = faltantes + " Tipo de identificacion";
                        
                        if($("#sltEstado").val() == "")
                            faltantes = faltantes + " Estado";
                        
                        if($("#txtNumeroIdentificacion").val() == "")
                            faltantes = faltantes + " Numero de identificacion";
                        
                        if($("#txtNombres").val() == "")
                            faltantes = faltantes + " Nombres";
                        
                        if($("#txtPrimerApellido").val() == "")
                            faltantes = faltantes + " Primer Apellidos";
                        
                        if($("#txtSegundoApellido").val() == "")
                            faltantes = faltantes + " Segundo Apellidos";
                        
                        if($("#txtTelefono").val() == "")
                            faltantes = faltantes + " Telefono";
                        
                        if($("#txtCelular").val() == "")
                            faltantes = faltantes + " Celular";
                        
                        if($("#txtLocalidad").val() == "")
                            faltantes = faltantes + " Localidad";
                        
                        if($("#txtBarrio").val() == "")
                            faltantes = faltantes + " Barrio";
                        
                        if($("#txtFechaNacimiento").val() == "")
                            faltantes = faltantes + " Fecha Nacimiento";
                        
                        if($("#txtCorreoElectronico").val() == "")
                            faltantes = faltantes + " Correo electronico";
                        
                        if($("#txtPuntajeSisben").val() == "")
                            faltantes = faltantes + " Puntaje sisben";
                        
                        if($("#sltAfiliadoFNA").val() == "")
                            faltantes = faltantes + " Afiliado FNA";
                        
                        if($("#sltCajaComp").val() == "")
                            faltantes = faltantes + " Caja de compensación";
                        
                        if($("#sltBanco").val() == "")
                            faltantes = faltantes + " Banco";
                        
                        if($("#sltOtrasCuentas").val() == "")
                            faltantes = faltantes + " Otras cuentas";
                        
                        if($("#txtValorCesantias").val() == "")
                            faltantes = faltantes + " Valor cesantias";
                        
                        if($("#txtOcupacion").val() == "")
                            faltantes = faltantes + " Ocupacion";
                        
                        if($("#sltTieneDiscapacidad").val() == "")
                            faltantes = faltantes + " Tiene discapacidad";
                        
                        if($("#sltGenero").val() == "")
                            faltantes = faltantes + " Genero";
                        
                        if($("#sltEPS").val() == "")
                            faltantes = faltantes + " EPS";
                        
                        if($("#sltFallecido").val() == "")
                            faltantes = faltantes + " Fallecido";
                        
                        if($("#sltParentesco").val() == "")
                            faltantes = faltantes + " Parentesco";
                        
                        if($("#sltEstadoCivil").val() == "")
                            faltantes = faltantes + " Estado civil";
                        
                       // if (("#sltValorAhorroProg").val() == "")
                          //  faltantes = faltantes + "Valor ahorro programado";
                           // alert("Por favor en el campo Valor ahorro programado digite un entero.");
                           // e.preventDefault();
                        
                                                
                        alert("Debe diligenciar los datos " + faltantes);
                        e.preventDefault();
                    }   
                    
                    if($('#hfSize').val() > 2079332)
                    {
                        alert("La imagen supera los 2MB permitidos.");
                        e.preventDefault();
                    }  
                    /*if(Number.isInteger($("#sltValorAhorroProg").val())== false) {
                        alert("Por favor en el campo Valor ahorro programado digite un entero.");
                        e.preventDefault();
                    }*/
                });

                $("#btnRegresar").click(function(e){
                    location.href  = "<%=request.getContextPath()%>/afiliados";
                });
                
                $('#fuImagen').bind('change', function() {

                    //this.files[0].size gets the size of your file.
                    $('#hfSize').val(this.files[0].size);

                  });
            </script>
        </div>        
    </div>        
    <jsp:include page="MasterFooter.jsp"></jsp:include>  