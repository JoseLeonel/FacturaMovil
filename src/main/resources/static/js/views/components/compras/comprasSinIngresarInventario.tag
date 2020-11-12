<recepcion-compra>

			

					<div class="row" >
						<div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 ">
							<div class="box">
								<div class="box-body">
									<div class="planel-body">
										<div class="row">
                                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                                <span>Compras pendiente de ingresar al inventario</span>
                                            </div>
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
               

    <div class="box" show = {mostrarDetalles}>
	      <div class = "box-body">
		        <span id="tituloCompra">Factura Compra #: {consecutivo}</span>
				<table class="table table-striped">
                        <thead>
                        <tr>
                            <th style="width:6%;"><div class="tituloFormat">Cod.Proveedor </div></th>
							<th style="width:3%;"><div class="tituloFormat">Cod.Inventario </div></th>
                            <th style="width:30%;"><div class="tituloFormat">Descripcion </div></th>
                            <th style="width:6%;"><div class="tituloFormat">Cant </div></th>
                            <th style="width:10%;"><div class="tituloFormat">Costo</div></th>
                            <th style="width:10%;"><div class="tituloFormat">Ganancia</div></th>
                            <th style="width:10%;"><div class="tituloFormat">Precio </div></th>
                            <th style="width:10%;"><div class="tituloFormat">IVA Compra </div></th>
                            <th style="width:10%;"><div class="tituloFormat">IVA Inventario </div></th>
							<th style="width:10%;"><div class="tituloFormat">Accion  </div></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td class="text-right" style="width:6%;">
                                <span>{cod_proveedor}</span>
                            </td>
                            <td class="text-right" style="width:6%;">
                                <input  onclick={__agregarArticuloInventario}  class="campodetalle" type="text"   value = "{cod_invet}" readonly/>
                            </td>
                            <td class="text-right" style="width:22%;">
                                <span>{descripcion}</span>
                            </td>
                            <td class="text-right" style="width:6%;">
                                <span>{cantidad}</span>
                            </td>
                            <td class="text-right" style="width:10%;">
                                <span>{costo_prove}</span>
                            </td>
                            <td class="text-right" style="width:10%;">
                                <input  onkeyup={__actualizarGananciaKeyPress}  class="campodetalle" type="number" step="any"  value = "{ganancia}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td class="text-right" style="width:10%;">
                                <input  onkeyup={__actualizarPrecioKeyPress}   class="campodetalle" type="number" step="any"  value = "{precio_publico}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td class="text-right" style="width:10%;">
                                <span>{impuesto}</span>
                            </td>
                            <td class="text-right" style="width:10%;">
                                <span>{imp_art}</span>
                            </td>
                            <td>
                                <button id="{id}" name="{id}"  onclick={__IngresarAlInventario} class="btn btn-block botonAplicarInventario">Aplicar</button>
                            <td>
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
.botonAplicarInventario{
    background-color: #6dca42 !important;
    color: white !important;
    font-size: 14px !important;
    font-weight: 600 !important;
    border-radius: 14px !important;
}
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
		self.compras = {aaData:[]}
        self.detalleCompras = {aaData:[]}
        self.detail = []
        self.articulos = {data:[]}
        self.detalleCompra  = null
        self.consecutivo = null
        self.item = null;
        self.articulo = null;
        self.mostrarDetalles = false;

		//Se cargan al montar el tag
		self.on('mount',function(){
			__InformacionDataTableCuentas(); 
			listadoRecepcionCompras();
            __agregarArticulos()
         

		});
var itemDetalle = null;
__IngresarAlInventario(e){
    self.detalleCompra = e.item;
    self.update()
     swal({
           title: '',
           text: "Desea ingresarlo al inventario?",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
        }).then(function (isConfirm) {
            //Ajax__inicializarTabla();
            if(isConfirm){
                if(validar()){

                   actualizarDetalleAlInventario()
                }
            }
        })    
}

function validar(){
    if(self.detalleCompra.cod_invet == null ){
        mensajeAdvertencia("Error: Ingresar el codigo del articulo. 輸入商品編號")
        return false
    }
    if(self.detalleCompra.cod_invet.length == 0 ){
        mensajeAdvertencia("Error: Ingresar el codigo del articulo 輸入商品編號")
        return false
    }
    return true
}
/**
 @param idCompra
 * @param idDetalleCompra
 * @param codigoInventario
 * @param gananciaPrecioPublico
 * @param precioPublico
 * @param codigoProveedor
**/
function actualizarDetalleAlInventario(){
   var parametros = {
        idCompra:self.detalleCompra.idCompra,
        idDetalleCompra:self.detalleCompra.id,
        codigoInventario:self.detalleCompra.cod_invet,
        gananciaPrecioPublico: self.detalleCompra.ganancia,
        precioPublico:self.detalleCompra.precio_publico,
        codigoProveedor:self.detalleCompra.cod_proveedor,
   }
    $.ajax({
        url: 'actualizarDetalleCompraPorAutomatica.do',
        datatype: "json",
        method:"GET",
        data :parametros,
        success: function (result) {
            if (result.status != 200) {
                if (result.message != null && result.message.length > 0) {
                     mensajeAdvertencia(result.message);
                }
            } else {
                __DeleteArticuloIngresadoInventario()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });

}

function __DeleteArticuloIngresadoInventario(){
     var index = self.detalleCompras.aaData.indexOf(self.detalleCompra);
     self.detalleCompras.aaData.splice(index,1);
     self.detail.splice(index,1);
     self.update()
     if(self.detail.length == 0){
         self.update()
         listadoDetallesCompras(self.detalleCompra.idCompra,function(resultado){
             
             console.log(resultado)
         })
         
     }
}

__agregarArticuloInventario(e){
    detalleEscogido(e)
}

/**
*   Actualizar el costo del codigo y recalcular la compra
**/
__actualizarGananciaKeyPress(e){
    if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    aplicarGananciaCompra(e)
}

__actualizarGananciaBlur(e){
    if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    aplicarGananciaCompra(e)
}
function aplicarGananciaCompra(e){
    var ganancia = e.currentTarget.value;
    self.item = e.item; 
    var index = self.detalleCompras.aaData.indexOf(self.item);
    var impuesto  =  __valorNumerico(self.item.imp_art)
    var costo     =  __valorNumerico(self.item.costo_prove)
    var precioPublico    =  __valorNumerico(self.item.precio_publico)
    self.item.ganancia = __valorNumerico(ganancia);
    self.item.precio_publico =_ObtenerPrecio(self.item.costo_prove,self.item.imp_art * 100,0,self.item.ganancia)
    self.item.ganancia = __valorNumerico(redondeoDecimales(self.item.ganancia,aplicarRedondeo()))
    self.item.precio_publico =  __valorNumerico(redondeoDecimales(self.item.precio_publico,aplicarRedondeo()))
    self.detail[index] = self.item
    self.update()

}
/**
*   Actualizar el costo del codigo y recalcular la compra
**/
__actualizarPrecioKeyPress(e){
    
    __ActualizarPrecioDetalle(e)
}

__actualizarPrecioBlur(e){
    
    __ActualizarPrecioDetalle(e)
}

function __ActualizarPrecioDetalle(e){
    var precio = e.currentTarget.value;
    self.item = e.item; 
    var index = self.detail.indexOf(self.item);
    var impuesto = __valorNumerico(self.item.impuesto)
    var costo = __valorNumerico(self.item.costo_prove)
    var precioPublico =  __valorNumerico(precio)
    self.item.ganancia = __CalcularGanancia(impuesto,costo,precioPublico);
    self.item.ganancia = __valorNumerico(redondeoDecimales(self.item.ganancia,aplicarRedondeo()))
    self.item.precio_publico = precio
    self.detail[index] = self.item;
    self.update()
}
function detalleEscogido(e){
    self.detalleCompra = e.item;
    itemDetalle = e.item
    self.item = e.item
    self.update()
    console.log(self.detalleCompra)
    ListarCodigosArticulos();
    
 
}


function __agregarArticulos() {
     $('#tableListarArticulos').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListarArticulos').DataTable();
		if(table.row(this).child.isShown()){

	       var data = table.row(this).data();
	    }else{
	       var data = table.row($(this).parents("tr")).data();
	     }
        if(data !=null){
            asociarCodigoLineaDetalle(data.codigo,data.impuesto,data.precioPublico)
            $('#modalInventario').modal('hide')
            return
        }
    });
}

/**
* Actualizar el codigo del inventario y precio publico que tiene 
**/
function asociarCodigoLineaDetalle(codigo,impuesto,precioPublico){
    var index = self.detalleCompras.aaData.indexOf(self.item);
    self.item.cod_invet = codigo
    self.item.precio_publico = precioPublico
    self.item.imp_art = impuesto
    self.item.ganancia = _porcentajeGanancia(self.item.costo,self.item.imp_art,self.item.precio_publico)
    self.detail[index] = self.item
    self.update();
    
}
/**
* consultar producto
**/
__ConsultarProductosCod = function(e){
    if (e.keyCode != 13) {
        return;
    }
    __ListaDeArticulosPorDescripcion()
}

__ConsultarProductosDesc = function(e){
    if (e.keyCode != 13) {
        return;
    }
__ListaDeArticulosPorDescripcion()
}

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
                self.articulos.data = result.aaData
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
  
    $('.descArticulo').val(null)
    $('.codigoArt').val(null)
    $(".tableListarArticulos").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
     $('#modalInventario').modal('show')
    $('#modalInventario').on('shown.bs.modal', function () {
        $('.codigoArt').select()
        $('.codigoArt').focus()

    })

 }
 
/**
Listado de recepcion de compras
**/		
function listadoRecepcionCompras() {
    __InicializarTabla('.tableListar')  
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
    __InicializarTabla('.tableListar')  
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
      listadoDetallesCompras(data.id,function(resultado){
          console.log(resultado)
      })
    });
}

/**
Listado de detalles de Compras
**/		
function listadoDetallesCompras(idCompra,callback) {
    self.detail = []
    self.detalleCompra = {}
    self.mostrarDetalles = false;
    self.update()
    self.update()
    var parametros = {
        idCompra : idCompra
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
                $.each(result.aaData, function( index, modeloTabla ) {
                    self.detail.push(modeloTabla);
                    
                    self.mostrarDetalles = true;
                })
                self.detalleCompras.aaData = result.aaData
                self.update()
               
               
            }
             if(self.mostrarDetalles == false){
                    listadoRecepcionCompras()
                }
                callback("Consulta de detalles de la compra")
        }
    });
    
}



</script>
</recepcion-compra>