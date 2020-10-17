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
               

    <div class="box">
	      <div class = "box-body">
		        <span id="tituloCompra">Factura Compra #: {consecutivo}</span>
				<table class="table table-striped">
                        <thead>
                        <tr>
                            <th style="width:10%;"><div class="tituloFormat">Codigo Proveedor </div></th>
							<th style="width:10%;"><div class="tituloFormat">Codigo Inventario </div></th>
                            <th style="width:14%;"><div class="tituloFormat">Descripcion </div></th>
                            <th style="width:6%;"><div class="tituloFormat">Cant </div></th>
                            <th style="width:10%;"><div class="tituloFormat">Costo </div></th>
                            <th style="width:10%;"><div class="tituloFormat">Ganancia </div></th>
                            <th style="width:10%;"><div class="tituloFormat">Precio </div></th>
                            <th style="width:10%;"><div class="tituloFormat">IVA Compra </div></th>
                            <th style="width:10%;"><div class="tituloFormat">IVA Inventario </div></th>
							<th style="width:10%;"><div class="tituloFormat">Accion  </div></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={compras.aaData}>
                            <td class="text-right" style="width:10%;">
                                <input  class="campodetalle" type="number" step="any"  value = "{cod_proveedor}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td class="text-right" style="width:10%;">
                                <input  class="campodetalle" type="number" step="any"  value = "{cod_invet}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td class="text-right" style="width:14%;">
                                <span>{descripcion}</span>
                            </td>
                            <td class="text-right" style="width:6%;">
                                <span>{cantidad}</span>
                            </td>
                            <td class="text-right" style="width:10%;">
                                <span>{costo_prove}</span>
                            </td>
                            <td class="text-right" style="width:10%;">
                                <input  class="campodetalle" type="number" step="any"  value = "{ganancia}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td class="text-right" style="width:10%;">
                                <input  class="campodetalle" type="number" step="any"  value = "{precio_publico}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td class="text-right" style="width:10%;">
                                <span>{impuesto}</span>
                            </td>
                            <td class="text-right" style="width:10%;">
                                <span>{imp_art}</span>
                            </td>
                            <td><td>
                        </tr>
                        </tbody>
                    </table>  
		  </div>
	</div>

<style type="text/css"  >
 .tituloFormat{
     text-align: center;
 }
 .campodetalle{
    font-size: 18px;
 }
 .box{
    color: #000000 !important;
    background: #c2c5c5 !important;
 }
 .table-header {
     background: #c2c5c5 !important;
     color: #000000!important;
 }
 .dataTables_wrapper .dataTables_filter input {
    margin-left: 1.5em !important;
    height: 30px !important;
    border-radius: 10px !important;
    font-size: 16px !important;
}
</style>
	<script>
		var self = this;
	 	self.empresaActividadComercial= {}
		self.compras              = {aaData:[]}
        self.consecutivo = null
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
        url: 'ListarComprasSinIngresarInventarioAjax.do',
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

	__MostrarPDF()
    __MostrarDetalle()
	
}
/**
 * Formato del listado
 */
function __InformacionDataTableCuentas(){
	self.formato_tabla = [
		{ 'data': 'id', "name": "id", "title": "#id", "autoWidth": true },
		{ 'data': 'consecutivo', "name": "consecutivo", "title": "#Consecutivo", "autoWidth": true },
		{ 'data': 'fechaEmisionSTR', "name": "fechaEmisionSTR", "title": "Fecha Emision", "autoWidth": true },
		{ 'data': 'nombre_completo', "name": "nombre_completo", "title": "#Proveedor", "autoWidth": true },
		{ 'data': 'totalImpuestoSTR', "name": "totalImpuestoSTR", "title": "IVA", "autoWidth": true },
		{ 'data': 'totalCompraSTR', "name": "totalCompraSTR", "title": "Total", "autoWidth": true },
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
    menu += '<li><a href="#"  title="Mostrar PDF" class="  btnPDF" >Mostrar PDF</a></li>'
    menu += '<li><a href="#"  title="Aceptar al inventario" class="  btnAceptar" >Ingresar Inventario</a></li>'
    menu += "</ul></div>"
    return menu;
}

function __MostrarPDF() {
    $('.tableListar').on('click', '.btnPDF', function(e) {
        var table = $('#tableListar').DataTable();
        if (table.row(this).child.isShown()) {
            //cuando el datatable esta en modo responsive
            var data = table.row(this).data();
        } else {
            var data = table.row($(this).parents("tr")).data();
        }
      	var parametros = {
            direccion: "bajarArchivo.do?filename=" + data.factura_pdf,
            stylemodal: "modal-xl"
        }
        riot.mount('view-pdf', { datos: parametros });

    });
}

function __MostrarDetalle() {
    $('.tableListar').on('click', '.btnAceptar', function(e) {
        var table = $('#tableListar').DataTable();
        if (table.row(this).child.isShown()) {
            //cuando el datatable esta en modo responsive
            var data = table.row(this).data();
        } else {
            var data = table.row($(this).parents("tr")).data();
        }
        self.consecutivo = data.consecutivo;
        self.update()
      listadoDetallesCompras(data)
    });
}

/**
Listado de detalles de Compras
**/		
function listadoDetallesCompras(data) {
    var parametros = {
        idCompra : data.id
    }
    $.ajax({
        url: 'ListarDetalleComprasSinIngresarInventarioAjax.do',
        datatype: "json",
        data:parametros ,
        method: "GET",
        success: function(result) {
            console.log(result);
            if (result.aaData.length > 0) {
                console.log(result)
                self.compras.aaData = result.aaData
                self.update()
               
            }
        }
    });
}



</script>
</recepcion-compra>