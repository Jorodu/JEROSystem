<%@page contentType="text/html" pageEncoding="windows-1252"%>

    <jsp:include page="MasterHead.jsp"></jsp:include>  

    <section class="banner-bottom-w3ls py-5" id="news" style="background-color: #fff;width: 95%;margin-left: 2%;">
		<div class="py-xl-5 py-lg-3" id='divContactenos'>
                        <div style="width: 94%;text-align: left;margin-left: 5%;">
                            <b style="font-size: 42px;color: #5DADF7;">Panel de control</b>
                        </div>
                        <br/><br/>
			<div class="d-flex">
                                <div style="margin-left: 15%;">  
                                    <h4 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Afiliados por genero</h4>
                                    <canvas id="myChart" width="400" height="400"></canvas>
                                    <script>
                                    var ctx = document.getElementById('myChart');
                                    var myChart = new Chart(ctx, {
                                        type: 'bar',
                                        data: {
                                            labels: ['Mujeres', 'Hombres'],
                                            datasets: [{
                                                label: '# de Afiliados',
                                                data: [${sexoF}, ${sexoM}],
                                                backgroundColor: [
                                                    'rgba(255, 99, 132, 0.2)',
                                                    'rgba(54, 162, 235, 0.2)'
                                                ],
                                                borderColor: [
                                                    'rgba(255, 99, 132, 1)',
                                                    'rgba(54, 162, 235, 1)',
                                                ],
                                                borderWidth: 1
                                            }]
                                        },
                                        options: {
                                            scales: {
                                                yAxes: [{
                                                    ticks: {
                                                        beginAtZero: true
                                                    }
                                                }]
                                            }
                                        }
                                    });
                                    </script>
                                </div> 
                                <div style="margin-left: 15%;">
                                    <h4 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Afiliados Activos/Inactivos</h4>
                                    <canvas id="myChart2" width="400" height="400"></canvas>
                                    <script>
                                    var ctx = document.getElementById('myChart2');
                                    var myChart = new Chart(ctx, {
                                        type: 'bar',
                                        data: {
                                            labels: ['Inactivos', 'Activos'],
                                            datasets: [{
                                                label: '# de afiliados por estados',
                                                data: [${inactivo}, ${activo}],
                                                backgroundColor: [
                                                    'rgba(255, 99, 132, 0.2)',
                                                    'rgba(54, 162, 235, 0.2)'
                                                ],
                                                borderColor: [
                                                    'rgba(255, 99, 132, 1)',
                                                    'rgba(54, 162, 235, 1)',
                                                ],
                                                borderWidth: 1
                                            }]
                                        },
                                        options: {
                                            scales: {
                                                yAxes: [{
                                                    ticks: {
                                                        beginAtZero: true
                                                    }
                                                }]
                                            }
                                        }
                                    });
                                    </script>
                                </div> 
			</div>
                        <div class="d-flex">	                            
                                <div style="margin-left: 15%;">   
                                    <h4 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Afiliados por Mes (${anyo})</h4>
                                    <canvas id="myChart1" width="1000" height="400"></canvas> 
                                    <script>
                                    var ctx = document.getElementById('myChart1');
                                    var myChart = new Chart(ctx, {
                                        type: 'bar',
                                        data: {
                                            labels: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
                                            datasets: [{
                                                label: '# de Afiliados por mes (${anyo})',
                                                data: [${ene}, ${feb}, ${mar}, ${abr}, ${may}, ${jun}, ${jul}, ${ago}, ${sep}, ${oct}, ${nov}, ${dic}],
                                                backgroundColor: [
                                                    'rgba(255, 99, 132, 0.2)',
                                                    'rgba(54, 162, 235, 0.2)',
                                                    'rgba(255, 206, 86, 0.2)',
                                                    'rgba(75, 192, 192, 0.2)',
                                                    'rgba(153, 102, 255, 0.2)',
                                                    'rgba(255, 159, 64, 0.2)',
                                                    'rgba(255, 99, 132, 0.2)',
                                                    'rgba(54, 162, 235, 0.2)',
                                                    'rgba(255, 206, 86, 0.2)',
                                                    'rgba(75, 192, 192, 0.2)',
                                                    'rgba(153, 102, 255, 0.2)',
                                                    'rgba(255, 159, 64, 0.2)'
                                                ],
                                                borderColor: [
                                                    'rgba(255, 99, 132, 1)',
                                                    'rgba(54, 162, 235, 1)',
                                                    'rgba(255, 206, 86, 1)',
                                                    'rgba(75, 192, 192, 1)',
                                                    'rgba(153, 102, 255, 1)',
                                                    'rgba(255, 159, 64, 1)',
                                                    'rgba(255, 99, 132, 1)',
                                                    'rgba(54, 162, 235, 1)',
                                                    'rgba(255, 206, 86, 1)',
                                                    'rgba(75, 192, 192, 1)',
                                                    'rgba(153, 102, 255, 1)',
                                                    'rgba(255, 159, 64, 1)'
                                                ],
                                                borderWidth: 1
                                            }]
                                        },
                                        options: {
                                            scales: {
                                                yAxes: [{
                                                    ticks: {
                                                        beginAtZero: true
                                                    }
                                                }]
                                            }
                                        }
                                    });
                                    </script>
                                </div>
			</div>
                        <div style="width: 94%;text-align: center;margin-top: 35px;">
                            <h3 class="tittle mb-xl-5 mb-4 text-dark"><span class="text-uppercase"></span>Reportes</h3>
                            <ul>
                                <li>
                                    <a href="<%=request.getContextPath()%>/generateAfiliados" target="_blank">* Generar Reporte Afiliados</a>
                                </li>
                                <li>
                                    <a href="<%=request.getContextPath()%>/generateAportesPendientes" target="_blank">* Generar Reporte Aportes Folders Pendiente Mes</a>
                                </li>
                                <li>
                                    <a href="<%=request.getContextPath()%>/generateAportesMes" target="_blank">* Generar Reporte Aportes Folders Mes</a>
                                </li>
                            </ul>
                        </div>
                        
		</div>
	</section>
    
    <jsp:include page="MasterFooter.jsp"></jsp:include>  
    