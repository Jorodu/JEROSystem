<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html lang="es">

<head>
	<title>JeroSystem | Login</title>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<meta name="keywords" content="Agro Crop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"
	/>
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
                
                //alert('<%=request.getContextPath()%>');
	</script>
	<!-- //Meta tag Keywords -->

	<!-- Custom-Files -->
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
		<!-- Bootstrap-Core-CSS -->
        <link href="<%=request.getContextPath()%>/resources/css/owl.carousel.css" rel="stylesheet" type="text/css"/>
	
        <link href="<%=request.getContextPath()%>/resources/css/owl.theme.css" rel="stylesheet" type="text/css"/>
	<!-- Carousel (for News section) -->
        <link href="<%=request.getContextPath()%>/resources/css/flexslider.css" rel="stylesheet" type="text/css"/>
	<!-- Flexslider css (for Testimonials) -->
        <link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
	<!-- Style-CSS -->
        <link href="<%=request.getContextPath()%>/resources/css/fontawesome-all.css" rel="stylesheet" type="text/css"/>
	<!-- Font-Awesome-Icons-CSS -->
	<!-- //Custom-Files -->

	<!-- Web-Fonts -->
	<link href="//fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i,900,900i&amp;subset=cyrillic,latin-ext,vietnamese"
	    rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&amp;subset=devanagari,latin-ext"
	    rel="stylesheet">
	<!-- //Web-Fonts -->

</head>

<body style="background-color: #525659;">
	
    <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); width: 60%; max-width: 500px; text-align: center; background: #f2f2f4; padding: 1em; border-radius: 2em; ">
        <img src="<%=request.getContextPath()%>/resources/imagenes/logo.png" width="160" height="70" alt=""/>		
	<form action='<%=request.getContextPath()%>/doLogin' method="post">            
            <div class="form-group" style="text-align:left;">
                <label for="exampleInputEmail1">Usuario</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Ingrese usuario">
              </div>
              <div class="form-group" style="text-align:left;">
                <label for="exampleInputPassword1">Clave</label>
                <input type="password" class="form-control" id="password" placeholder="Password" name="password">
              </div>
              <button type="submit"  class="rounded py-sm-2 py-1 px-5" style="background-color: #5DADF7;color: #fff;">Ingresar</button>                            
              <c:if test="${error == 'true'}">
                <div class="alert alert-danger">
                    <spring:message text="Contraseña o usuario incorrecto"/>
                    <br/>
                </div>
            </c:if>
        </form>
    </div>        
</body>
</html>