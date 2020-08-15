<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"  %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

    <jsp:include page="MasterHead.jsp"></jsp:include>  

    <div style="text-align: center;">
        <div id="formulario" style="width: 95%;margin-left: 2%; text-align: center; background: #fff; padding: 1em;">
            <h3 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Documentos por afiliados</h3>
            <form action='<%=request.getContextPath()%>/docrearComprobantePorAhorro' id="registration"  name="registration" method="post"  enctype="multipart/form-data">                            
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Folder</label>                    
                    <input type="hidden" class="form-control" id="hfId" value="${ahorroVol.id_ahorro_voluntario}" name="hfId">
                    <input type="text" class="form-control" id="txtFolder" readonly="true" value="FUN-${ahorroVol.id_folder}" name="txtFolder">                                     
                  </div>                  
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Fecha Fin ahorro</label>                                                  
                    <c:set var="fechaConv">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${ahorroVol.fecha_fin_ahorro}" />
                    </c:set>                    
                    <input type="text" class="form-control" readonly="true" maxlength="10" id="txtFechaFinAhorros" value="${fechaConv}" placeholder="Fecha" name="txtFechaFinAhorros" />
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Valor Ahorro</label>                                                                      
                    <input type="text" readonly="true" class="form-control" maxlength = "12" oninput="maxLengthCheck(this)" id="txtValorAporte" value="${ahorroVol.valor_ahorro}" placeholder="Valor" name="txtValorAporte" />
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Valor Total Ahorro</label>                                                                      
                    <input type="text" readonly="true" class="form-control" maxlength = "12" oninput="maxLengthCheck(this)" id="txtValorTotalAporte" value="${ahorroVol.valor_total_ahorro}" placeholder="Valor" name="txtValorTotalAporte" />
                  </div> 
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Fecha Comprobante</label>                                                                               
                    <input type="text" class="form-control" onkeypress="return isNumberKey(event)" maxlength="10" id="txtFechaMaxPago" value="" placeholder="Fecha" name="txtFechaMaxPago" />
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword3">Soporte</label>                        
                    <input type="hidden" class="form-control" id="hfSize" name="hfSize" />
                    <input type="file" class="form-control" id="fuImagen" data-max-size="2048" name="fuImagen" />
                  </div>
                  <button type="submit" class="btn btn-primary">Crear</button>                                
                  <button type="button" id="btnRegresar" class="btn btn-primary">Regresar</button>                                
                  <c:if test="${error == 'true'}">
                    <div class="alert alert-danger">                        
                        Error al crear el documento al ahorro.
                        <br/>
                    </div>
                  </c:if>
                  <br/><br/>
                  <table id="tableConsulta" class="table table-striped table-bordered" style="width:100%">
                        <thead class="thead-dark" style="font-weight: bold;">
                            <tr>
                                <td></td>
                                <td>Fecha Comprobante</td>                                                                         
                                <td>Soporte</td>
                                <td>Fecha creación</td> 
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dato" items="${lstComprobantes}">      
                                <tr>
                                    <td>
                                        <c:if test="${perfil == 1}">
                                            <a href="<%=request.getContextPath()%>/doEliminarComprobantePorAhorro?idComprobante=${dato.id_comprobante}&idDocumento=${dato.id_comprobante}&idAhorro=${ahorroVol.id_ahorro_voluntario}">Quitar</a>                                            
                                        </c:if>
                                    </td>                                                                                      
                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${dato.fecha_registro}" /></td>  
                                    <td><a href="<%=request.getContextPath()%>/resources/imagenes/${dato.soporte}" target="_blank">Ver</a></td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${dato.fecha_adiciono}" /></td>  
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>   
            </form>
            <script type="text/javascript">                
                                
                $("#registration").submit(function(e){
                    var faltantes = "";
                    
                    if($("#txtFechaMaxPago").val() == "" || $("#fuImagen").val() == "")
                    {
                        if($("#txtFechaMaxPago").val() == "")
                            faltantes = faltantes + " Fecha Comprobante";
                        
                        if($("#fuImagen").val() == "")
                            faltantes = faltantes + " Soporte";
                        
                        alert("Debe diligenciar los datos " + faltantes);                            
                        e.preventDefault();
                    }   
                    
                    if($('#hfSize').val() > 2079332)
                    {
                        alert("La imagen supera los 2MB permitidos.");
                        e.preventDefault();
                    }   
                });

                $("#btnRegresar").click(function(e){
                    location.href  = "<%=request.getContextPath()%>/ahorroVoluntario";
                });
                
                $('#fuImagen').bind('change', function() {
                    //this.files[0].size gets the size of your file.
                    $('#hfSize').val(this.files[0].size);
                });
            </script>
        </div>        
    </div>        
    <jsp:include page="MasterFooter.jsp"></jsp:include>  