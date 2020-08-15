<%-- 
    Document   : Master
    Created on : 20-oct-2019, 12:07:39
    Author     : Forero
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!doctype html>
<html lang="en">

<head>
	<title>JeroSystem | Home</title>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<meta name="keywords" content="Agro Crop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
        
        <style type="text/css">
            .divInline{
                display: inline-block;
            }
        </style>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">        
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css" rel="stylesheet"/>    
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">                

        <!-- Flexslider css (for Testimonials) -->
        <link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
	<!-- Style-CSS -->        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	<!-- Font-Awesome-Icons-CSS -->
	<!-- //Custom-Files -->

	<!-- Web-Fonts -->
	<link href="//fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i,900,900i&amp;subset=cyrillic,latin-ext,vietnamese"
	    rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&amp;subset=devanagari,latin-ext"
	    rel="stylesheet">
	<!-- //Web-Fonts -->
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.js"></script>    
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.css"></script>    
        
        <script>
            function maxLengthCheck(object)
            {
              if (object.value.length > object.maxLength)
                object.value = object.value.slice(0, object.maxLength)
            }
            
            function isNumberKey(evt)
            {
               var charCode = (evt.which) ? evt.which : evt.keyCode;               
               if (charCode != 46 && charCode != 47 && charCode > 31 
                 && (charCode < 48 || charCode > 57))
                  return false;

               return true;
            }
            
            function soloNumeros(e){
                var key = window.Event ? e.which : e.keyCode
                return (key >= 48 && key <= 57)
            } 
            
            function soloNumerosDecimal(e){
                var key = window.Event ? e.which : e.keyCode                
                return ((key >= 48 && key <= 57) || charCode == 46)
            } 
        </script>
        
</head>

<body style="background-color: #525659;text-align: center;">    
	<!-- header -->
	<header style="width: 95%;margin-left: 2%;margin-top: 1%;">
		<!-- navigation -->
		<nav class="navbar navbar-expand-lg navbar-light px-sm-5 px-3 py-0" style="background-color: #5DADF7;">
			<center>
                            <c:if test="${perfil == 1}">
                                <a class="navbar-brand py-2" href="<%=request.getContextPath()%>/home"><img src="<%=request.getContextPath()%>/resources/imagenes/logo.png" width="210" height="70" alt=""/></a>
                            </c:if>
                            <c:if test="${perfil == 73}">
                                <a class="navbar-brand py-2" href="<%=request.getContextPath()%>/afiliados"><img src="<%=request.getContextPath()%>/resources/imagenes/logo.png" width="210" height="70" alt=""/></a>
                            </c:if>                                
			</center>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
			    aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto ml-xl-6 ml-lg-5 px-xl-7 px-lg-5 py-4 text-center"  style="background-color: #5DADF7;">
                                        <c:if test="${perfil == 1}">
					<li class="nav-item">
                                            <a class="nav-link" href="<%=request.getContextPath()%>/home">Panel de control
                                                    <span class="sr-only">(current)</span>
                                            </a>
					</li>
                                        </c:if>
					<li class="nav-item mx-lg-4 my-lg-0 my-3">
                                            <a class="nav-link" href="<%=request.getContextPath()%>/afiliados">Afiliados</a>
					</li>
					<li class="nav-item mx-lg-4 my-lg-0 my-3">
                                            <a class="nav-link" href="<%=request.getContextPath()%>/folders">Carpetas</a>
					</li>                                       
                                        <li class="nav-item mx-lg-4 my-lg-0 my-3">
                                            <a class="nav-link" href="<%=request.getContextPath()%>/aportes">Aportes</a>
					</li>
                                        <li class="nav-item mx-lg-4 my-lg-0 my-3">
                                            <a class="nav-link" href="<%=request.getContextPath()%>/ahorroVoluntario">Ahorro voluntario</a>
					</li>
                                        <c:if test="${perfil == 1}">
                                            <li class="nav-item mx-lg-4 my-lg-0 my-3">
                                                <a class="nav-link" href="<%=request.getContextPath()%>/configuracion">Configuración</a>
                                            </li>                                         
                                            <li class="nav-item">
                                                <a class="nav-link" href="<%=request.getContextPath()%>/usuarios">Usuarios</a>
                                            </li>
                                        </c:if>
                                        <li class="nav-item mx-lg-4 my-lg-0 my-3">
                                            <a href="<%=request.getContextPath()%>/doLogout" class="nav-link" >
                                                <i class="fas fa-window-close"></i> Cerrar Sesión
                                            </a> 
					</li>
				</ul>
			</div>
		</nav>
		<!-- //navigation -->
	</header>
	<!-- //header -->