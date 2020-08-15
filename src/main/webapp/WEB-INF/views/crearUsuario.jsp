<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

    <jsp:include page="MasterHead.jsp"></jsp:include>  

    <div style="text-align: center;">
        <div id="formulario" style="width: 95%;margin-left: 2%; text-align: center; background: #fff; padding: 1em;">
            <h3 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Datos Usuario</h3>
            <form action='<%=request.getContextPath()%>/doCrearUsuario' id="registration"  name="registration" method="post">            
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Perfil</label>                    
                    <select class="form-control" id="sltPerfil" name="sltPerfil">
                        <option value="">Seleccione</option>
                        <option value="1">Administrador</option>
                        <option value="73">Consulta</option>
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
                    <label for="exampleInputPassword1">Nombres</label>                    
                    <input type="hidden" class="form-control" id="hfId" value="${usuario.id_usuario}" name="hfId">
                    <input type="text" class="form-control" maxlength="100" id="txtNombres" value="${usuario.nombres}" placeholder="Nombres" name="txtNombres">
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Usuario</label>
                    <input type="text" class="form-control" maxlength="100" id="txtUsuario" value="${usuario.username}" placeholder="Usuario" name="txtUsuario">
                  </div>                                              
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Clave</label>
                    <input type="password" class="form-control" maxlength="100" id="txtClave" placeholder="Password" name="txtClave">
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Confirmar Clave</label>
                    <input type="password" class="form-control" maxlength="100" id="txtClaveC" placeholder="Repetir Password" name="txtClaveC">
                  </div>
                  <button type="submit" class="btn btn-primary">Registrar</button>                                
                  <button type="button" id="btnRegresar" class="btn btn-primary">Regresar</button>                                
                  <c:if test="${error == 'true'}">
                    <div class="alert alert-danger">
                        <spring:message text="Error al registrar usuario."/>
                        Usuario creado previamente.
                        <br/>
                    </div>
                  </c:if>
            </form>
            <script type="text/javascript">
                <c:if test="${usuario.id_usuario != 0}">
                    $("#sltPerfil").val("${usuario.categoria_perfil}");
                    $("#sltEstado").val("${usuario.estado}");
                </c:if>
                
                $("#registration").submit(function(e){
                    var faltantes = "";
                    
                    if($("#txtNombres").val() == "" || $("#sltPerfil").val() == "" || $("#txtUsuario").val() == "" || 
                        $("#txtClave").val() == "" || $("#txtClaveC").val() == "")
                    {
                        if($("#txtNombres").val() == "")
                            faltantes = faltantes + " Nombres";
                        
                        if($("#sltPerfil").val() == "")
                            faltantes = faltantes + " Perfil";
                        
                        if($("#txtUsuario").val() == "")
                            faltantes = faltantes + " Usuario";
                        
                        if($("#txtClave").val() == "")
                            faltantes = faltantes + " Clave";
                        
                        alert("Debe diligenciar los datos " + faltantes);                         
                        e.preventDefault();
                    }   
                    else if($("#txtClave").val() != $("#txtClaveC").val())
                    {
                        alert("las claves no coinciden");
                        e.preventDefault();
                    } 
                });
                
                $("#btnRegresar").click(function(e){
                    location.href  = "<%=request.getContextPath()%>/usuarios";
                });
            </script>
        </div>        
    </div>        
</body>
</html>