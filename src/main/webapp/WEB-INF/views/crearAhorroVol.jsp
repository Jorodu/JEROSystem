<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"  %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

    <jsp:include page="MasterHead.jsp"></jsp:include>  

    <div style="text-align: center;">
        <div id="formulario" style="width: 95%;margin-left: 2%; text-align: center; background: #fff; padding: 1em;">
            <h3 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Datos Ahorro Voluntario</h3>
            <form action='<%=request.getContextPath()%>/docrearAhorroVol' id="registration"  name="registration" method="post" enctype="multipart/form-data">                            
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Folder</label>                    
                    <input type="hidden" class="form-control" id="hfId" value="${ahorroVol.id_ahorro_voluntario}" name="hfId">
                    <select class="form-control" id="hfIdFolder" name="hfIdFolder">
                        <option value="">Seleccione</option>
                        <c:forEach var="dato" items="${lstFordes}">      
                            <option value="${dato.idFolder}">FUN-${dato.idFolder}</option>                            
                        </c:forEach>
                    </select>                    
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Fecha Max. pago</label>                                                  
                    <c:set var="fechaConv">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${ahorroVol.fecha_max_pago}" />
                    </c:set>                    
                    <input type="text" class="form-control" onkeypress="return isNumberKey(event)" maxlength="10" id="txtFechaMaxPago" value="${fechaConv}" placeholder="Fecha" name="txtFechaMaxPago" />
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Fecha Fin ahorro</label>                                                  
                    <c:set var="fechaConv">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${ahorroVol.fecha_fin_ahorro}" />
                    </c:set>                    
                    <input type="text" class="form-control" onkeypress="return isNumberKey(event)" maxlength="10" id="txtFechaFinAhorro" value="${fechaConv}" placeholder="Fecha" name="txtFechaFinAhorro" />
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Soporte</label>                                                                                          
                    <input type="hidden" id="hfSoporte" value="${ahorroVol.soporte}" name="hfSoporte" />
                    <input type="hidden" class="form-control" id="hfSize" name="hfSize" />
                    <a href="<%=request.getContextPath()%>/resources/imagenes/${ahorroVol.soporte}" target="_blank">Ver</a>
                    <input type="file" class="form-control" id="fuImagen" name="fuImagen">
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Valor Ahorro</label>                                                                      
                    <input type="text" onkeypress="return soloNumeros(event)"class="form-control" maxlength = "12" oninput="maxLengthCheck(this)" id="txtValorAporte" value="${ahorroVol.valor_ahorro}" placeholder="Valor" name="txtValorAporte" />
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Valor Total Ahorro</label>                                                                      
                    <input type="text" onkeypress="return soloNumeros(event)"class="form-control" maxlength = "12" oninput="maxLengthCheck(this)" id="txtValorTotalAporte" value="${ahorroVol.valor_total_ahorro}" placeholder="Valor" name="txtValorTotalAporte" />
                  </div> 
                  <button type="submit" class="btn btn-primary">Registrar</button>                                
                  <button type="button" id="btnRegresar" class="btn btn-primary">Regresar</button>                                
                  <c:if test="${error == 'true'}">
                    <div class="alert alert-danger">
                        <spring:message text="Error al registrar usuario."/>
                        Error al crear el folder.
                        <br/>
                    </div>
                  </c:if>
            </form>
            <script type="text/javascript">                
                
                <c:if test="${ahorroVol.id_ahorro_voluntario != 0}">
                    $("#hfIdFolder").val("${ahorroVol.id_folder}");
                </c:if>
                
                $("#registration").submit(function(e){
                    var faltantes = "";
                    
                    if($("#hfIdFolder").val() == "" || $("#txtFechaMaxPago").val() == "" 
                        || $("#txtValorAporte").val() == "" || $("#txtValorTotalAporte").val() == "")
                    {
                        if($("#txtFechaMaxPago").val() == "")
                            faltantes = faltantes + " Fecha Max pago";
                        
                        if($("#txtValorAporte").val() == "")
                            faltantes = faltantes + " Valor Aporte";
                        
                        if($("#txtValorTotalAporte").val() == "")
                            faltantes = faltantes + " Valor Total Aporte";
                        
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
                
                $("#txtValorAporte").on({
                    "focus": function (event) {
                        $(event.target).select();
                    },
                    "keyup": function (event) {
                        $(event.target).val(function (index, value ) {
                            return value.replace(/\D/g, "")
                                        .replace(/([0-9])([0-9]{2})$/, '$1.$2')
                                        .replace(/\B(?=(\d{3})+(?!\d)\.?)/g, ",");
                        });
                    }
                });
                
                $("#txtValorTotalAporte").on({
                    "focus": function (event) {
                        $(event.target).select();
                    },
                    "keyup": function (event) {
                        $(event.target).val(function (index, value ) {
                            return value.replace(/\D/g, "")
                                        .replace(/([0-9])([0-9]{2})$/, '$1.$2')
                                        .replace(/\B(?=(\d{3})+(?!\d)\.?)/g, ",");
                        });
                    }
                });
                
                $('#fuImagen').bind('change', function() {

                    //this.files[0].size gets the size of your file.
                    $('#hfSize').val(this.files[0].size);

                  });
                
            </script>
        </div>        
    </div>        
    <jsp:include page="MasterFooter.jsp"></jsp:include>  