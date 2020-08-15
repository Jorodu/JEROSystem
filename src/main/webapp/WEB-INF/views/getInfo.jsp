<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
Datos del vendedor:

Nombres: <c:out value="${dato.nombres}"/>
Apellidos:  <c:out value="${dato.apellidos}"/>
Email:  <c:out value="${dato.username}"/>
