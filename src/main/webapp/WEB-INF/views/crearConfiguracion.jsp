<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"  %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

    <jsp:include page="MasterHead.jsp"></jsp:include>  

    <div style="text-align: center;">
        <div id="formulario" style="width: 95%;margin-left: 2%; text-align: center; background: #fff; padding: 1em;">
            <h3 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Datos Configuración</h3>
            <form action='<%=request.getContextPath()%>/docrearConfiguracion' id="registration"  name="registration" method="post" enctype="multipart/form-data">                            
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Tipo configuración</label>                    
                    <input type="hidden" class="form-control" id="hfId" value="${categoria.id_categoria}" name="hfId">
                    <input type="hidden" class="form-control" id="hfId" value="${categoria.tipo_categoria_id}" name="hfIdTipoCategoria">
                    <select class="form-control" id="sltTipoCategoria" name="sltTipoCategoria" <c:if test="${categoria.id_categoria != 0}">disabled="disabled"</c:if>>
                        <option value="">Seleccione</option>                        
                        <option value="2">Bancos</option>
                        <option value="3">Cajas de Compensación</option>
                        <option value="4">Tipos Identificación</option>
                        <option value="6">EPS</option>
                        <option value="7">Estado civil</option>
                        <option value="8">Parentesco</option>
                        <option value="9">Documentos Personas</option>
                        <option value="10">Ocupación</option>
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
                    <label for="exampleInputPassword1">Descripción</label>                                                                                          
                    <input type="text" class="form-control" maxlength="100" id="txtNombre" value="${categoria.descripcion}" name="txtNombre" />                    
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
                
                <c:if test="${categoria.id_categoria != 0}">
                    $("#sltTipoCategoria").val("${categoria.tipo_categoria_id}");
                    $("#sltEstado").val("${categoria.estado_id}");
                </c:if>
                
                $("#registration").submit(function(e){
                    var faltantes = "";
                    
                    if($("#txtNombre").val() == "" || $("#sltTipoCategoria").val() == "" || $("#sltEstado").val() == "")
                    {
                        if($("#txtNombre").val() == "")
                            faltantes = faltantes + " Descripcion";
                        
                        if($("#sltTipoCategoria").val() == "")
                            faltantes = faltantes + " Tipo de categoria";
                        
                        if($("#sltEstado").val() == "")
                            faltantes = faltantes + " Estado";
                        
                        alert("Debe diligenciar los datos " + faltantes);                        
                        e.preventDefault();
                    }   
                });

                $("#btnRegresar").click(function(e){
                    location.href  = "<%=request.getContextPath()%>/configuracion";
                });
            </script>
        </div>        
    </div>        
</body>
</html>