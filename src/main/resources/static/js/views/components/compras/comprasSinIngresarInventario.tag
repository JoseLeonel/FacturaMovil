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
                                <input  class="campodetalle" type="text"   value = "{cod_proveedor}" />
                            </td>
                            <td class="text-right" style="width:10%;">
                                <input  onclick={__agregarArticuloInventario}  class="campodetalle" type="text"   value = "{cod_invet}" />
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
<!--Modal mostrar Articulos de la empresa -->
<div id='modalInventario' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("articulo.listar")} </h4>
            </div>
            <div class="modal-body">
               <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 ol-lg-12">
                        <form id="formularioParametros" name ="formularioParametros" >
                            <div class="row">
                                <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                    <label  >{$.i18n.prop("articulo.codigo")}  </label>
                                    <input type="text" class="form-control codigoArt"  id="codigoArt" name="codigoArt"  onkeypress={__ConsultarProductosCod} >
                                </div>
                                <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                    <label  >{$.i18n.prop("articulo.descripcion")}</label>
                                    <input type="text" class="form-control descArticulo "   id="descArticulo" name="descArticulo" onkeypress={__ConsultarProductosDesc} autofocus="autofocus">
                                </div>
                            </div> 
                        </form>    
                        <br>      

                        <table id="tableListarArticulos" class="display table responsive table-hover nowrap table-condensed tableListarArticulos " cellspacing="0" width="100%">
                            <thead>
                                <th class="table-header">{$.i18n.prop("listado.acciones")}       </th>
                                <th class="table-header">{$.i18n.prop("articulo.codigo")}        </th>
                                <th class="table-header">{$.i18n.prop("articulo.descripcion")}   </th>
                                <th class="table-header">{$.i18n.prop("inventario.cantidad")}    </th>
                                <th class="table-header">{$.i18n.prop("articulo.precioPublico")} </th>
                                
                            </thead>
                            <tfoot style="display: table-header-group;">
                                <tr>
                                    <th >                                        </th>
                                    <th >{$.i18n.prop("articulo.codigo")}        </th>
                                    <th >{$.i18n.prop("articulo.descripcion")}   </th>
                                    <th >{$.i18n.prop("inventario.cantidad")}    </th>
                                    <th >{$.i18n.prop("articulo.precioPublico")} </th>
                                    
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>        
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->

<style type="text/css"  >
 .tituloFormat{
     text-align: center;
     font-weight: 600;
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
#tituloCompra{
	font-size: 18px;
    font-weight: 600;

}
</style>
	<script>
		var self = this;
	 	self.empresaActividadComercial= {}
		self.compras              = {aaData:[]}
        self.articulos             = {data:[]}
        self.detalleCompra  = {}
        self.consecutivo = null
		//Se cargan al montar el tag
		self.on('mount',function(){
			__InformacionDataTableCuentas(); 
			listadoRecepcionCompras();
            __agregarArticulos()
            window.addEventListener( "click", function(evento){
                teclamodal(evento);
            }, false );

		});

__agregarArticuloInventario(e){
    self.detalleCompra = e.item;
    self.update()
    console.log(self.detalleCompra)
    ListarCodigosArticulos();
}

this.__ConsultarProductosCod = function(e){
    if (e.keyCode != 13) {
        return;
    }
    __ListaDeArticulosPorDescripcion()
}.bind(this)

this.__ConsultarProductosDesc = function(e){
    if (e.keyCode != 13) {
        return;
    }
__ListaDeArticulosPorDescripcion($("#codigoArt").val(),e.currentTarget.value)
}.bind(this)

function __ListaDeArticulosPorDescripcion(){
    if($('#codigoArt').val() =='' && $('#descArticulo').val() =='' ){
        return
    }
    $(".tableListarArticulos").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
    var formulario = $('#formularioParametros').serialize();
    $.ajax({
        url: 'ListarPorDescripcionCodigoArticuloAjax.do',
        datatype: "json",
        method:"GET",
        data :formulario,
        success: function (result) {
            if(result.aaData.length > 0){
                _informacionData_Articulo()
                self.articulos.data           = result.aaData
                self.update()
                loadListar(".tableListarArticulos",idioma_espanol,self.informacion_tabla_articulo,self.articulos.data)
                agregarInputsCombos_Articulo()
                ActivarEventoFiltro(".tableListarArticulos")

            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
function agregarInputsCombos_Articulo(){
    $('.tableListarArticulos tfoot th').each( function (e) {
        var title = $('.tableListarArticulos thead th').eq($(this).index()).text();

        if ( $(this).index() != 0    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}

function teclamodal(e){
    if ($('#modalInventario').is(':visible')) {
        $('.precioventa').focus()
    }
    
}
function _informacionData_Articulo(){
   self.informacion_tabla_articulo = [
                                        {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
                                            "render":function(id,type, row){
                                                    return __OpcionesArticulos(id,type,row);
                                                }
                                        },
                                       {'data' : 'codigo'         ,"name":"codigo"          ,"title" : $.i18n.prop("articulo.codigo")       ,"autoWidth":false},
                                        {'data' : 'descripcion'    ,"name":"descripcion"     ,"title" : $.i18n.prop("articulo.descripcion")  ,"autoWidth":false},
                                        {'data' : 'cantidad'       ,"name":"cantidad"        ,"title" : $.i18n.prop("inventario.cantidad")   ,"autoWidth":false},
                                        {'data' : 'precioPublico'  ,"name":"precioPublico"   ,"title" : $.i18n.prop("articulo.precioPublico"),"autoWidth":false,
                                          "render":function(precioPublico,type, row){
                                              var resultado = formatoDecimales(__valorNumerico(precioPublico))
                                               return  resultado;
                                            }
                                        },
                              ];

 self.update()
}

function __OpcionesArticulos(){
  var agregar  = '<a href="#"  class="btn btnAgregar btn-success form-control" title="Seleccionar" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;

}


function ListarCodigosArticulos(){
    self.mostrarListadoArticulos = true
    self.update()
    $('.descArticulo').val(null)
    $('.codigoArt').val(null)
    $(".tableListarArticulos").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
     $('#modalInventario').modal('show')
    $('#modalInventario').on('shown.bs.modal', function () {
        $('#descArticulo').select()
        $('#descArticulo').focus()

    })

 }
 function __agregarArticulos() {
     $('#tableListarArticulos').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListarArticulos').DataTable();
		if(table.row(this).child.isShown()){

	       var data = table.row(this).data();
	    }else{
	       var data = table.row($(this).parents("tr")).data();
	     }
        self.articulo = data;
        self.update();
        if(self.articulo !=null){
            $('#modalInventario').modal('hide')
            return
        }

    });
}
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