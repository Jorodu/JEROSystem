<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"  %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

    <jsp:include page="MasterHead.jsp"></jsp:include>  

    <div style="text-align: center;">
        <div id="formulario" style="width: 95%;margin-left: 2%; text-align: center; background: #fff; padding: 1em;">
            <h3 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Datos Aportes</h3>
            <form action='<%=request.getContextPath()%>/docrearAporte' id="registration"  name="registration" method="post" enctype="multipart/form-data">                            
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Folder</label>                    
                    <input type="hidden" class="form-control" id="hfId" value="${aportes.idAporte}" name="hfId">
                    <select class="form-control" id="hfIdFolder" name="hfIdFolder">
                        <option value="">Seleccione</option>
                        <c:forEach var="dato" items="${lstFordes}">      
                            <option value="${dato.idFolder}">${dato.nombre_folder}</option>                            
                        </c:forEach>
                    </select>                    
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Fecha de registro</label>                                                  
                    <c:set var="fechaConv">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${aportes.fecha_registro_aporte}" />
                    </c:set>                    
                    <input type="text" class="form-control" onkeypress="return isNumberKey(event)" maxlength="10" id="txtFechaRegistro" value="${fechaConv}" placeholder="Fecha" name="txtFechaRegistro" />
                  </div> 
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Soporte</label>                                                                                          
                    <input type="hidden" id="hfSoporte" value="${aportes.soporte_aporte}" name="hfSoporte" />
                    <input type="hidden" class="form-control" id="hfSize" name="hfSize" />
                    <a href="<%=request.getContextPath()%>/resources/imagenes/${aportes.soporte_aporte}" target="_blank">Ver</a>
                    <input type="file" class="form-control" id="fuImagen" name="fuImagen">
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Valor Aporte</label>                                                                      
                    <input type="text" onkeypress="return soloNumeros(event)" class="form-control" maxlength = "12" oninput="maxLengthCheck(this)" id="txtValorAporte" value="${aportes.valor_aporte}" placeholder="Valor" name="txtValorAporte" />
                  </div>
                  <button type="submit" class="btn btn-primary">Registrar</button>                                
                  <button type="button" id="btnRegresar" class="btn btn-primary">Regresar</button>                                
                  <c:if test="${error == 'true'}">
                    <div class="alert alert-danger">
                        <spring:message text="Error al registrar usuario."/>
                        Error al crear el aporte.
                        <br/>
                    </div>
                  </c:if>
            </form>
            <script type="text/javascript">                
                
                <c:if test="${aportes.idAporte != 0}">
                    $("#hfIdFolder").val("${aportes.idFolder}");
                </c:if>
                
                $("#registration").submit(function(e){
                    var faltantes = "";
                    if($("#hfIdFolder").val() == "" || $("#txtFechaRegistro").val() == "" || $("#txtValorAporte").val() == "")
                    {
                        if($("#txtFechaRegistro").val() == "")
                            faltantes = faltantes + " Fecha Registro";
                        
                        if($("#txtValorAporte").val() == "")
                            faltantes = faltantes + " Valor Aporte";
                        
                        alert("Debe diligenciar los datos " + faltantes);                                                
                        e.preventDefault();
                    }   
                    
                    if($('#hfSize').val() > 2079332)
                    {
                        alert("La imagen supera los 2MB permitidos.");
                        e.preventDefault();
                    }   
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

                $("#btnRegresar").click(function(e){
                    location.href  = "<%=request.getContextPath()%>/aportes";
                });
                
                $('#fuImagen').bind('change', function() {

                    //this.files[0].size gets the size of your file.
                    $('#hfSize').val(this.files[0].size);

                  });

            </script>
        </div>        
    </div>        
    <jsp:include page="MasterFooter.jsp"></jsp:include>  