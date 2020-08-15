<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"  %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

    <jsp:include page="MasterHead.jsp"></jsp:include>  

    <div style="text-align: center;">
        <div id="formulario" style="width: 95%;margin-left: 2%; text-align: center; background: #fff; padding: 1em;">
            <h3 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Afiliados por carpeta</h3>
            <form action='<%=request.getContextPath()%>/docrearAfiliadoFolder' id="registration"  name="registration" method="post" enctype="multipart/form-data">                            
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Folder</label>                    
                    <input type="hidden" class="form-control" id="hfId" value="${folder.idFolder}" name="hfId">
                    <input type="text" class="form-control" id="txtFolder" value="${folder.nombre_folder}" readonly="true" name="txtFolder">                    
                  </div>     
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Afiliado</label>                                        
                    <select class="form-control" id="sltAfiliado" name="sltAfiliado">
                        <option value="">Seleccione</option>
                        <c:forEach var="dato" items="${lstAfiliados}">      
                            <option value="${dato.id_persona}">${dato.numero_identicicacion} - ${dato.nombre_completo}</option>                            
                        </c:forEach>
                    </select>                    
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Tipo Afiliado</label>                                        
                    <select class="form-control" id="sltTipoAfiliado" name="sltTipoAfiliado">
                        <option value="">Seleccione</option>
                        <option value="1">Titular</option>
                        <option value="2">Nucleo Familiar</option>                        
                    </select>                    
                  </div>
                  <button type="submit" class="btn btn-primary">Asociar</button>                                
                  <button type="button" id="btnRegresar" class="btn btn-primary">Regresar</button>                                
                  <c:if test="${error == 'true'}">
                    <div class="alert alert-danger">                        
                        Error al asociar el afiliado al folder, ya existe.
                        <br/>
                    </div>
                  </c:if>
                  <br/><br/>
                  <table id="tableConsulta" class="table table-striped table-bordered" style="width:100%">
                        <thead class="thead-dark" style="font-weight: bold;">
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>                                                                     
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dato" items="${lstAfiliadosFolder}">      
                                <tr>
                                    <td>
                                        <c:if test="${perfil == 1}">
                                            <a href="<%=request.getContextPath()%>/dodeleteFolderAfil?idFolder=${dato.idFolder}&idAfiliado=${dato.id_persona}">Quitar</a>                                            
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${dato.tipo_afiliado == 1}">
                                            Titular
                                        </c:if>
                                        <c:if test="${dato.tipo_afiliado == 2}">
                                            Nucleo Familiar
                                        </c:if>
                                    </td>
                                    <td>
                                        <table style="text-align: justify;width: 100%;">
                                            <tr>
                                                <td style="text-align: justify;width: 50%;">                                                    
                                                    <b>Tipo de identificación:</b> ${dato.tipo_identificacion}
                                                    <br/>
                                                    <b>Número de identificación:</b> ${dato.numero_identicicacion}
                                                    <br/>
                                                    <b>Nombres:</b> ${dato.nombre_completo}
                                                    <br/>
                                                    <b>Telefono:</b> ${dato.telefono}
                                                    <br/>
                                                    <b>Celular:</b> ${dato.celular}
                                                    <br/>
                                                    <b>Localidad:</b> ${dato.localidad}
                                                    <br/>
                                                    <b>Barrio:</b> ${dato.barrio}
                                                    <br/>
                                                    <b>Fecha de nacimiento:</b> ${dato.fecha_nacimiento}
                                                    <br/>
                                                    <b>Correo electrónico:</b> ${dato.correo_electronico}
                                                    <br/>
                                                    <b>Puntaje Sisben:</b> ${dato.puntaje_sisben}
                                                    <br/>
                                                    <b>Afiliado FNA:</b> ${dato.afiliado_fna}
                                                    <br/>
                                                    <b>Caja de compensación:</b> ${dato.caja_compens}                                                    
                                                </td>   
                                                <td style="text-align: justify;width: 50%;">                                                    
                                                    <b>Ahorro bancario:</b> ${dato.ahorro_bancario}
                                                    <br/>
                                                    <b>Banco:</b> ${dato.banco}
                                                    <br/>
                                                    <b>Valor ahorro programado:</b> ${dato.valor_ahorro_prog}
                                                    <br/>
                                                    <b>Otras cuentas:</b> ${dato.otras_cuentas}
                                                    <br/>
                                                    <b>Valor otras cuentas:</b> ${dato.otras_cuentas}
                                                    <br/>
                                                    <b>Valor cesantias:</b> ${dato.valor_cesantias}
                                                    <br/>
                                                    <b>Ocupación:</b> ${dato.ocupacion}
                                                    <br/>
                                                    <b>Discapacidad</b> ${dato.discapacidad}
                                                    <br/>
                                                    <b>Genero</b> ${dato.genero}
                                                    <br/>
                                                    <b>EPS</b> ${dato.eps}
                                                    <br/>
                                                    <b>Fallecido</b> ${dato.fallecido}
                                                    <br/>
                                                    <b>Parentesco</b> ${dato.parentesco}
                                                    <br/>
                                                    <b>Estado Civil</b> ${dato.estado_civil}
                                                </td>
                                            </tr>
                                        </table>                                        
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>   
            </form>
            <script type="text/javascript">                
                
                
                $("#hfIdFolder").val("${aportes.idFolder}");
                
                $("#registration").submit(function(e){
                    var faltantes = "";
                    
                    if($("#sltTipoAfiliado").val() == "" || $("#sltAfiliado").val() == "")
                    {
                        if($("#sltAfiliado").val() == "")
                            faltantes = faltantes + " Afiliado";
                        
                        if($("#sltTipoAfiliado").val() == "")
                            faltantes = faltantes + " Tipo Afiliado";
                        
                        alert("Debe diligenciar los datos " + faltantes);
                        e.preventDefault();
                    }   
                });

                $("#btnRegresar").click(function(e){
                    location.href  = "<%=request.getContextPath()%>/folders";
                });
            </script>
        </div>        
    </div>        
    <jsp:include page="MasterFooter.jsp"></jsp:include>  