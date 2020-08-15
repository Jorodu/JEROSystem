<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"  %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

    <jsp:include page="MasterHead.jsp"></jsp:include>  

    <div style="text-align: center;">
        <div id="formulario" style="width: 95%;margin-left: 2%; text-align: center; background: #fff; padding: 1em;">
            <h3 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Datos Folder</h3>
            <form action='<%=request.getContextPath()%>/docrearFolder' id="registration"  name="registration" method="post">                              
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Nombres</label>                    
                    <input type="hidden" class="form-control" id="hfId" value="${folder.idFolder}" name="hfId">
                    <input type="text" class="form-control" id="txtNombres" value="FUN-${carpeta+1}" readonly="true" placeholder="Nombres" name="txtNombres">
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Fecha de registro</label>                                                  
                    <c:set var="fechaConv">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${folder.fecha_registro}" />
                    </c:set>                    
                    <input type="text" class="form-control" onkeypress="return isNumberKey(event)" maxlength="10" id="txtFechaRegistro" value="${fechaConv}" placeholder="Fecha" name="txtFechaRegistro" />
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
                $("#registration").submit(function(e){
                    var faltantes = "";
                    
                    if($("#txtNombres").val() == "" || $("#txtFechaRegistro").val() == "")
                    {
                        if($("#txtNombres").val() == "")
                            faltantes = faltantes + " Nombres";
                        
                        if($("#txtFechaRegistro").val() == "")
                            faltantes = faltantes + " Fecha registro";
                        
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