<%-- 
    Document   : MasterFooter
    Created on : 07-nov-2019, 16:59:56
    Author     : Forero
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>

    <!-- footer -->
    <footer>
            <div>
                    <!-- footer end -->
                    <div class="d-flex footer-end" style="background-color: #525659;color: #fff;">
                            <!-- footer social icons -->
                            <div class="col-lg-3 footer-social">
                                    
                            </div>
                            <!-- //footer social icons -->
                            <!-- copyright -->
                            <div class="col-lg-6 copy-right text-center" >
                                    <p style="color: #fff;font-size: 14px;"> &copy; 2019 JeroSystem. All Rights Reserved | Design by
                                        <a>Jenny Paola Pacheco and Jorge Rojas</a>
                                    </p>
                            </div>
                            <!-- //copyright -->
                    </div>
                    <!-- //footer end -->
            </div>
    </footer>
    <!-- //footer -->
    
    
    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.2/moment.min.js"></script>    
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>    
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>    
    

    <script>
        $(document).ready(function() {
            $('#tableConsulta').DataTable({
                language: {
                    "decimal": "",
                    "emptyTable": "No hay información",
                    "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
                    "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
                    "infoFiltered": "(Filtrado de _MAX_ total entradas)",
                    "infoPostFix": "",
                    "thousands": ",",
                    "lengthMenu": "Mostrar _MENU_ Entradas",
                    "loadingRecords": "Cargando...",
                    "processing": "Procesando...",
                    "search": "Buscar:",
                    "zeroRecords": "Sin resultados encontrados",
                    "paginate": {
                        "first": "Primero",
                        "last": "Ultimo",
                        "next": "Siguiente",
                        "previous": "Anterior"
                    }
                }
            });
            
            $('#txtFechaNacimiento').datepicker({
                format: 'dd/mm/yyyy',
                startDate: '-80y',
                endDate: '-18y'
            });
            $('#txtFechaRegistro').datepicker({
                format: 'dd/mm/yyyy',
                startDate: '-80y'
            });
            $('#txtFechaMaxPago').datepicker({
                format: 'dd/mm/yyyy',
                startDate: '-3d'
            });
            $('#txtFechaFinAhorro').datepicker({
                format: 'dd/mm/yyyy',
                startDate: '-3d'
            });      
            
        } );
    </script>
</body>

</html>