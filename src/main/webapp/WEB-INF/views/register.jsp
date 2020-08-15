<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

    <jsp:include page="MasterHead.jsp"></jsp:include>  

    <div style="padding-top: 3%;text-align: center;background-image: url('<%=request.getContextPath()%>/resources/imagenes/1.jpg');">
        <div id="formulario" style="margin: 0 auto; width: 60%; max-width: 500px; text-align: center; background: #f2f2f4; padding: 1em; border-radius: 2em; ">
            <img src="<%=request.getContextPath()%>/resources/imagenes/LogoDistricampoVt-220x80.png" width="220" height="70" alt=""/>		
            <form action='<%=request.getContextPath()%>/doRegister' id="registration"  name="registration" method="post">            
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Rol</label>                    
                    <select class="form-control" id="sltPerfil" name="sltPerfil">
                        <option value="">Seleccione</option>
                        <option value="1">Productor</option>
                        <option value="2">Distribuidor</option>
                    </select>
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Nombres</label>
                    <input type="text" class="form-control" id="txtNombres" placeholder="Nombres" name="txtNombres">
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Apellidos</label>
                    <input type="text" class="form-control" id="txtApellidos" placeholder="Apellidos" name="txtApellidos">
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Número de identificación</label>
                    <input type="number" class="form-control" id="txtNumIdentificacion" placeholder="Num. Identificación" name="txtNumIdentificacion">
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Correo electrónico</label>
                    <input type="email" class="form-control" id="txtEmail" placeholder="Email" name="txtEmail">
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Confirmar Correo electrónico</label>
                    <input type="email" class="form-control" id="txtEmailC" placeholder="Email" name="txtEmailC">
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Género</label>                    
                    <select class="form-control" id="sltGenero" name="sltGenero">
                        <option value="-1">Seleccione</option>
                        <option value="1">Masculino</option>
                        <option value="2">Femenino</option>
                    </select>
                  </div>                  
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Clave</label>
                    <input type="password" class="form-control" id="txtClave" placeholder="Password" name="txtClave">
                  </div>
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Confirmar Clave</label>
                    <input type="password" class="form-control" id="txtClaveC" placeholder="Repetir Password" name="txtClaveC">
                  </div>
                  <button type="submit" class="btn btn-primary">Registrar</button>                                
                  <c:if test="${error == 'true'}">
                    <div class="alert alert-danger">
                        <spring:message text="Error al registrar usuario."/>
                        Usuario creado previamente.
                        <br/>
                    </div>
                  </c:if>
            </form>
            <script type="text/javascript">
                $("#registration").submit(function(e){
                    if($("#txtNombres").val() == "" || $("#sltPerfil").val() == "" || $("#txtApellidos").val() == "" || 
                        $("#txtNumIdentificacion").val() == "" || $("#txtEmail").val() == "" || $("#txtEmailC").val() == "" ||
                        $("#sltGenero").val() == "" || $("#txtClave").val() == "" || $("#txtClaveC").val() == "")
                    {
                        alert("Debe diligenciar todos los datos");
                        e.preventDefault();
                    }   
                    else if($("#txtClave").val() != $("#txtClaveC").val())
                    {
                        alert("las claves no coinciden");
                        e.preventDefault();
                    } 
                    else if($("#txtEmail").val() != $("#txtEmailC").val())
                    {
                        alert("Los correos no coinciden");
                        e.preventDefault();
                    } 
                });
            </script>
        </div>        
    </div>        
</body>
</html>