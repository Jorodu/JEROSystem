<%@page import="java.util.Map"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"  %>

<%@page contentType="text/html" pageEncoding="windows-1252"%>

        <jsp:include page="MasterHead.jsp"></jsp:include>  

        <section class="banner-bottom-w3ls py-5" id="news" style="background-color: #fff;width: 95%;margin-left: 2%;">            
            <div class="py-xl-2 py-lg-2" id='divConsulta' style="margin-left: 5%;">
			<div class="d-flex">				
                                <div class="col-lg-12 about-right slider-right-con">
                                    <div style="text-align: left;">
                                        <b style="font-size: 42px;color: #5DADF7;">Afiliados</b>
                                    
                                        <c:if test="${lstDatos.size() == 0}">
                                            No hay productos publicados.
                                        </c:if>    
                                        <div class="header-contact-w3ls text-center mt-lg-0 mt-4" style="text-align: center;">
                                            <a href="<%=request.getContextPath()%>/crearAfiliado?id=0" class="rounded py-sm-3 py-2 px-5" style="background-color: #5DADF7;">
                                                <i class="fas fa-arrow-up"></i> Adicionar Afiliado
                                            </a>
                                        </div>
                                        <table id="tableConsulta" class="table table-striped table-bordered" style="width:100%">
                                            <thead class="thead-dark" style="font-weight: bold;">
                                                <tr>
                                                    <td></td>
                                                    <td>Nombres</td>
                                                    <td>Numero Identificación</td>
                                                    <td>Estado</td>
                                                    <td>Sexo</td>
                                                    <td>Certificado</td>
                                                    <td>Carnet</td>
                                                    <td>Usuario Creación</td>
                                                    <td>Fecha Creación</td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="dato" items="${lstDatos}">      
                                                    <tr>
                                                        <td>
                                                            <c:if test="${perfil == 1}">
                                                                <a href="<%=request.getContextPath()%>/crearAfiliado?id=${dato.id_persona}">Editar</a>
                                                                <br/>
                                                                <a href="<%=request.getContextPath()%>/crearDocumentosPorAfiliado?id=${dato.id_persona}">Cargar Docs</a>
                                                            </c:if>
                                                        </td>
                                                        <td>${dato.nombre_completo}</td>
                                                        <td>${dato.numero_identicicacion}</td>                                                                                                        
                                                        <td>${dato.desc_estado}</td>
                                                        <td>${dato.genero}</td>
                                                        <td><a href="<%=request.getContextPath()%>/consultaCertificado?id=${dato.id_persona}" target="_blank">Ver</a></td>
                                                        <td><a href="<%=request.getContextPath()%>/consultaCarnet?id=${dato.id_persona}" target="_blank">Ver</a></td>
                                                        <td>${dato.usuarioAdi}</td>
                                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${dato.fecha_adiciono}" /></td>                                                    
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>     
                                    </div>
                            </div>
			</div>
		</div>
	</section>
                
        <jsp:include page="MasterFooter.jsp"></jsp:include>  