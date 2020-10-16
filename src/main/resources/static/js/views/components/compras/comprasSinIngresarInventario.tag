<recepcion-compra>

			

					<div class="row" >
						<div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 ">
							<div class="box">
								<div class="box-body">
									<div class="planel-body">
										<div class="row">
											<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
												<table id="tableListar"
													class="display table responsive table-hover nowrap table-condensed tableListar ">
													<thead>
														<tr>
   															<th style="width: 4%;" class="table-header">Id </th>
															<th style="width: 4%;" class="table-header">Fecha Emision </th>
															<th style="width: 4%;" class="table-header">Consecutivo </th>
															<th style="width: 4%;" class="table-header">Proveedor </th>
															<th style="width: 4%;" class="table-header">Impuesto </th>
															<th style="width: 4%;" class="table-header">Total </th>
															<th style="width: 4%;" class="table-header">{$.i18n.prop("listado.acciones")} </th>
														</tr>
													</thead>
													<tfoot style="display: table-header-group;">
														<tr>
															<th>ID   </th>
															<th>Fecha Emision </th>
															<th>Consecutivo </th>
															<th>Proveedor </th>
															<th>Impuesto </th>
															<th>Total </th>
															<th style="width: 2%"></th>
														</tr>
													</tfoot>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- Fin del Listado -->
				</div>
				<!-- Fin del Listado -->
<style type="text/css"  >
.btn-green {
    background-color: #4cae4c;
    color: #FFF;
    border-radius: 5px;
    padding-bottom: 10px;
    padding-top: 10px;
    padding-left: 20px;
    padding-right: 20px;
    font-size: 14px;
    font-weight: bold;
    margin-top: 5%!important;
    margin-right: 15px;
    border: none;
    float: right;
    cursor: pointer;
}
</style>
	<script>
		var self = this;
	 	self.empresaActividadComercial= {}
		self.compras              = {aaData:[]}
		//Se cargan al montar el tag
		self.on('mount',function(){
			__InformacionDataTableCuentas(); 
			listadoRecepcionCompras();
		});

/**
Listado de recepcion de compras
**/		
function listadoRecepcionCompras() {
    $.ajax({
        url: 'listarRecepcionCompras.do',
        datatype: "json",
        method: "GET",
        success: function(result) {
            console.log(result);
            if (result.aaData.length > 0) {
                console.log(result)
                self.compras.aaData = result.aaData
                __cargarTablaCompras()
            }else{
				agregarInputsCombos()
				ActivarEventoFiltro(".tableListar")
			}
        }
    });
}
function agregarInputsCombos() {
    // Agregar los input de busqueda
    $('.tableListar tfoot th').each(
        function(e) {
            var name = '<input id = "filtroCampos' + e + '"';
            var title = $('.tableListar thead th').eq($(this).index())
                .text();
            // No se toma en cuenta la columna de las acctiones(botones)
            if ($(this).index() != 6 || $(this).index() != 5 || $(this).index() != 4) {
                $(this).html(name + 'type="text" class="form-control"  placeholder="' + title + '" />');
            }
        })
}
/**
Carga Tablas de compras
**/
function __cargarTablaCompras() {
    $("#tableListar").dataTable().fnClearTable();
    __InformacionDataTableCuentas();
    $('#tableListar').DataTable().destroy();
    $("#tableListar").DataTable({
        destroy: true,
        "aLengthMenu": [
            [5, 10, 15, 25, -1],
            [5, 10, 15, 25, "All"]
        ],
        "language": idioma_espanol,
        "sDom": 'lfrtip',
        "order": [],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        "columns": self.formato_tabla ,
    })
    $("#tableListar").dataTable().fnAddData(self.compras.aaData);
	agregarInputsCombos()
	ActivarEventoFiltro(".tableListar")
	
}
/**
 * Formato del listado
 */
function __InformacionDataTableCuentas(){
	self.formato_tabla = [
		{ 'data': 'id', "name": "id", "title": "#id", "autoWidth": true },
		{ 'data': 'consecutivo', "name": "consecutivo", "title": "#Consecutivo", "autoWidth": true },
		{ 'data': 'fechaEmision', "name": "fechaEmision", "title": "Fecha Emision", "autoWidth": true },
		{ 'data': 'emisorFactura', "name": "emisorFactura", "title": "#Proveedor", "autoWidth": true },
		{ 'data': 'totalImpuestos', "name": "totalImpuestos", "title": "IVA", "autoWidth": true },
		{ 'data': 'totalComprobante', "name": "totalComprobante", "title": "Total", "autoWidth": true },
		{
			'data': 'id',
			"name": "id",
			"bSortable": false,
			"bSearchable": false,
			"autoWidth": true,
			"render": function(id, type, row) {
				return __Opciones(id, type, row);
			}
		}
	];
	self.update()
}

/**
 * Opciones listado de los clientes
 */
function __Opciones(id, type, row) {
    let menu = '<div class="dropdown">'
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>'
    menu += '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
    menu += '<li><a href="#"  title="Bajar PDF" class="  btnPDF" >Bajar PDF</a></li>'
    menu += '<li><a href="#"  title="Aceptar Manual" class="btnAceptarXMLManual  btnBajarXML" >Aceptar Manual</a></li>'
    menu += '<li><a href="#"  title="Bajar XML Respuesta de Triburacion" class="  btnRespuestaHacienda" >XML Respuesta</a></li>'
    menu += "</ul></div>"
    return menu;
}


</script>
</recepcion-compra>