<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"  %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

    <jsp:include page="MasterHead.jsp"></jsp:include>  

    <div style="text-align: center;">
        <div id="formulario" style="width: 95%;margin-left: 2%; text-align: center; background: #fff; padding: 1em;">
            <h3 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Documentos por afiliados</h3>
            <form action='<%=request.getContextPath()%>/docrearDocumentosPorAfiliado' id="registration"  name="registration" method="post"  enctype="multipart/form-data">                            
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword1">Afiliado</label>                    
                    <input type="hidden" class="form-control" id="hfId" value="${afiliado.id_persona}" name="hfId">
                    <input type="text" class="form-control" id="txtAfiliado" value="${afiliado.nombre_completo}" readonly="true" name="txtAfiliado">                    
                  </div>     
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPasswor21">Tipo de documento</label>                                        
                    <select class="form-control" id="sltTipoDocuemtno" name="sltTipoDocuemtno">
                        <option value="">Seleccione</option>
                        <c:forEach var="dato" items="${lstTiposDocumento}">      
                            <option value="${dato.id_categoria}">${dato.descripcion}</option>                            
                        </c:forEach>
                    </select>                    
                  </div>   
                  <div class="form-group" style="text-align:left;">
                    <label for="exampleInputPassword3">Foto</label>                        
                    <input type="hidden" class="form-control" id="hfSize" name="hfSize" />
                    <input type="file" class="form-control" id="fuImagen" data-max-size="2048" name="fuImagen" />
                  </div>
                  <button type="submit" class="btn btn-primary">Crear</button>                                
                  <button type="button" id="btnRegresar" class="btn btn-primary">Regresar</button>                                
                  <c:if test="${error == 'true'}">
                    <div class="alert alert-danger">                        
                        Error al crear el documento al afiliado, ya existe.
                        <br/>
                    </div>
                  </c:if>
                  <br/><br/>
                  <table id="tableConsulta" class="table table-striped table-bordered" style="width:100%">
                        <thead class="thead-dark" style="font-weight: bold;">
                            <tr>
                                <td></td>
                                <td>Tipo de documento</td>                                         
                                <td>Documento</td> 
                                <td>Soporte</td>
                                <td>Fecha creación</td> 
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dato" items="${lstDocumentosAfiliados}">      
                                <tr>
                                    <td>
                                        <c:if test="${perfil == 1}">
                                            <a href="<%=request.getContextPath()%>/doDeleteDocumentosPorAfiliado?IdPersona=${dato.id_persona}&idDocumento=${dato.categoria_tipo_documento}">Quitar</a>                                            
                                        </c:if>
                                    </td>
                                    <td>${dato.tipo_documento_persona}</td>                                                    
                                    <td>${dato.documento}</td>  
                                    <td><a href="<%=request.getContextPath()%>/resources/imagenes/${dato.documento}" target="_blank">Ver</a></td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${dato.fecha_adiciono}" /></td>  
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>   
            </form>
            <script type="text/javascript">                
                                
                $("#registration").submit(function(e){
                    var faltantes = "";
                    
                    if($("#sltTipoDocuemtno").val() == "" || $("#fuImagen").val() == "")
                    {
                        if($("#sltTipoDocuemtno").val() == "")
                            faltantes = faltantes + " Tipo documento";
                        
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