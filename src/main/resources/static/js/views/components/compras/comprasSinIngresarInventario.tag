<recepcion-compra>

			

					<div class="row"  style="overflow-y: scroll;height: 260px;">
						<div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 ">
							<div class="box">
								<div class="box-body" >
									<div class="planel-body">
										<div class="row">
                                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                                <span>Compras pendiente de ingresar al inventario</span>
                                            </div>
											<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12" >
												<table id="tableListar" 
													class="display table responsive table-hover nowrap table-condensed tableListar ">
													<thead>
														<tr>
   															<th style="width: 4%;" class="table-header">Fecha Emision </th>
															<th style="width: 4%;" class="table-header">Consecutivo </th>
															<th style="width: 4%;" class="table-header">Proveedor </th>
															<th style="width: 4%;" class="table-header">Total </th>
															<th style="width: 4%;" class="table-header">{$.i18n.prop("listado.acciones")} </th>
														</tr>
													</thead>
                                                     <tbody>
                                                           <tr each={compras.aaData}>
                                                                 <td  style="width:20%;">
                                                                    <span>{consecutivo}</span>
                                                                </td>
                                                                 <td  style="width:20%;">
                                                                    <span>{fechaEmisionSTR}</span>
                                                                </td>
                                                                 <td  style="width:20%;">
                                                                    <span>{nombre_completo}</span>
                                                                </td>
                                                                 <td  style="width:20%;">
                                                                    <span>{totalCompraSTR}</span>
                                                                </td>
                                                                <td  style="width:20%;">
                                                                   <div class = "botoneras">
                                                                     <div onclick={__MostrarDetalle} class="botonCompra"> <span>Ver detalles</span></div>
                                                                     <div onclick={__MostrarPDF} class="botonCompra"> PDF</div>
                                                                    
                                                                    <div>
                                                                </td>

                                                            </tr>    
                                                     </tbody>      
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
               






    <div class="box" show = {mostrarDetalles }>
	      <div class = "box-body">
           <div class="row" >
                    <div class= "col-md-12 col-sx-12 col-sm-12 ol-lg-12">
		        <span id="tituloCompra">Factura Compra #: {consecutivo}</span>
                <div style="overflow: scroll;height: 500px;">
				<table class="table table-striped" style="width:100%">
                        <thead>
                        <tr>
                            <th style="width:4%;"><div class="tituloFormat">Num.linea </div></th>
                            <th style="width:6%;"><div class="tituloFormat">Cod.Proveedor </div></th>
							<th style="width:3%;"><div class="tituloFormat">Cod.Inv </div></th>
                            <th style="width:22%;"><div class="tituloFormat">Desc </div></th>
                            <th style="width:6%;"><div class="tituloFormat">Cant </div></th>
                             <th style="width:6%;"><div class="tituloFormat">Cant Inv </div></th>
                            <th style="width:7%;"><div class="tituloFormat">Costo Compra</div></th>
                            <th style="width:7%;"><div class="tituloFormat">Costo Inv</div></th>
                            <th style="width:10%;"><div class="tituloFormat">Ganancia</div></th>
                            <th style="width:10%;"><div class="tituloFormat">Precio </div></th>
                            <th style="width:10%;"><div class="tituloFormat">IVA Compra </div></th>
                            <th style="width:10%;"><div class="tituloFormat">IVA Inventario </div></th>
							<th style="width:10%;"><div class="tituloFormat">Accion  </div></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td  style="width:6%;">
                                <button onclick={_AnularAlInventario} class="btn btn-danger btn-xs btn-block" id="{numero_linea}" name="{numero_linea}">{numero_linea}</button>
                                
                            </td>

                            <td  style="width:6%;">
                                <span>{cod_proveedor}</span>
                            </td>
                            <td  style="width:6%;">
                                <input  onclick={__agregarArticuloInventario}  class="campodetalle" type="text"   value = "{cod_invet}" readonly/>
                            </td>
                            <td style="width:22%;">
                                <span>{descripcion}</span>
                            </td>
                            <td style="width:6%;">
                                <span>{cantidad}</span>
                            </td>
                             <td  style="width:7%;">
                                
                                <input  onkeyup={__actualizarCantidadInventario}  class="campodetalle" type="number" step="any"  value = "{cant_inv}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td  style="width:7%;">
                                <span>{costo_prove}</span>
                            </td>
                            <td  style="width:7%;">
                                
                                <input  onkeyup={__actualizarCostoInventario}  class="campodetalle" type="number" step="any"  value = "{costo_inv}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td  style="width:10%;">
                                <input  onkeyup={__actualizarGananciaKeyPress}  class="campodetalle" type="number" step="any"  value = "{ganancia}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td  style="width:10%;">
                                <input  onkeyup={__actualizarPrecioKeyPress}   class="campodetalle" type="number" step="any"  value = "{precio_publico}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td  style="width:10%;">
                                <span>{impuesto}</span>
                            </td>
                            <td  style="width:10%;">
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
          </div>
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

                        <table id="tableListarArticulos" class="display table responsive table-hover nowrap table-condensed tableListarArticulos " cellspacing="0" style="width:100%">
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
.botoneras{
    display:flex;

}
.botonCompra{
        cursor: pointer;
    background-color: #3c8dbc!important;
    color: white !important;
    font-size: 14px !important;
    font-weight: 600 !important;
    border-radius: 14px !important;
    flex: 1;
    padding-top: 5px;
    margin-right: 5px;
    height: 32px;
    text-align: center;
        margin-top: 5px;
}
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
    font-size: 14px;
    width: 125px!important;
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
        mensajeAlertErrorOConfirmacion('error',"Error: Ingresar el codigo del articulo. 輸入商品編號")
        return false
    }
    if(self.detalleCompra.cod_invet.length == 0 ){
        mensajeAlertErrorOConfirmacion('error',"Error: Ingresar el codigo del articulo 輸入商品編號")
        
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
        costo_inv:self.detalleCompra.costo_inv,
        cant_inv:self.detalleCompra.cant_inv,
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
                     mensajeAlertErrorOConfirmacion('error',result.message)
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

_AnularAlInventario(e){
    self.detalleCompra = e.item;
    self.update()
     swal({
           title: '',
           text: "Desea Anular el ingresarlo al inventario?",
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

                   anularDetalle(function(resultado){
                       console.log(resultado)
                       
                   })
            }
        })    
}


function anularDetalle(callback){
    
   var parametros = {
        idDetalleCompra:self.detalleCompra.id,
        idCompra:self.detalleCompra.idCompra,
   }
    $.ajax({
        url: 'anularDetalleCompra.do',
        datatype: "json",
        method:"GET",
        data :parametros,
        success: function (result) {
            if (result.status != 200) {
                if (result.message != null && result.message.length > 0) {
                     mensajeAlertErrorOConfirmacion('error',result.message)
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
   callback("Resultado exitoso")

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

__actualizarCostoInventario(e){
    if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    var costo_inv = __valorNumerico(e.currentTarget.value);
    self.item = e.item; 
    var index = self.detalleCompras.aaData.indexOf(self.item);
    var impuesto  =  __valorNumerico(self.item.imp_art)
    var precioPublico    =  __valorNumerico(self.item.precio_publico)
    self.item.costo_inv = costo_inv
    self.item.ganancia = __CalcularGanancia(impuesto,costo_inv,precioPublico);
    self.item.ganancia = __valorNumerico(redondeoDecimales(self.item.ganancia,aplicarRedondeo()))
    if(self.item.precioPublico == 0){
        self.item.precio_publico =_ObtenerPrecio(self.item.costo_inv,self.item.imp_art * 100,0,self.item.ganancia)
        self.item.precio_publico =  __valorNumerico(redondeoDecimales(self.item.precio_publico,aplicarRedondeo()))
    }
    
    self.detail[index] = self.item
    self.update()
}

__actualizarCantidadInventario(e){
    if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    var cant_inv = __valorNumerico(e.currentTarget.value);
    self.item = e.item; 
    var index = self.detalleCompras.aaData.indexOf(self.item);
    self.item.cant_inv = cant_inv
    
    self.detail[index] = self.item
    self.update()
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
    var costo     =  __valorNumerico(self.item.costo_inv)
    var precioPublico    =  __valorNumerico(self.item.precio_publico)
    self.item.costo_inv = costo
    self.item.ganancia = __valorNumerico(ganancia);
    self.item.precio_publico =_ObtenerPrecio(self.item.costo_inv,self.item.imp_art ,0,self.item.ganancia)
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
    var costo = __valorNumerico(self.item.costo_inv)
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
            asociarCodigoLineaDetalle(data.codigo,data.impuesto,data.precioPublico,data.costo,data.gananciaPrecioPublico)
            $('#modalInventario').modal('hide')
            return
        }
    });
}

/**
* Actualizar el codigo del inventario y precio publico que tiene 
**/
function asociarCodigoLineaDetalle(codigo,impuesto,precioPublico,costo,ganancia){
    var index = self.detalleCompras.aaData.indexOf(self.item);
    self.item.cod_invet = codigo
    self.item.costo = costo
    self.item.costo_inv = costo
    
    self.item.precio_publico = precioPublico
    self.item.imp_art = impuesto
    self.item.ganancia = __valorNumerico(redondeoDecimales(ganancia,aplicarRedondeo()))
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
    self.compras = {aaData:[]}
    self.detalleCompras = {aaData:[]}
    self.update()

    cargarComprasBackEnd(function(resultado){

        console.log(resultado)


    })
}

function cargarComprasBackEnd(callback){
    self.compras = {aaData:[]}
    self.update();
    $.ajax({
        url: 'ListarComprasSinIngresarInventarioAjax.do',
        datatype: "json",
        method: "GET",
        success: function(result) {
            console.log(result);
            if (result.aaData.length > 0) {
                console.log(result)
                self.compras = {aaData:[]}
                self.compras.aaData = result.aaData
                self.update()
            }
        }
    });
    callback("exitosa la consulta")

}





   


__MostrarPDF(e) {
     var data = e.item;
      	var parametros = {
            direccion: "bajarArchivo.do?filename=" + data.factura_pdf,
            stylemodal: "modal-xl"
        }
        riot.mount('view-pdf', { datos: parametros });
}

 __MostrarDetalle(e) {
     var data = e.item;
        self.consecutivo = data.consecutivo;
        self.detail = []
       self.detalleCompra = {}
        self.update()
      listadoDetallesCompras(data.id,function(resultado){
          console.log(resultado)
          if(self.mostrarDetalles == false){
             listadoRecepcionCompras()
           }

      })
}

/**
Listado de detalles de Compras
**/		
function listadoDetallesCompras(idCompra,callback) {
    self.detail = []
    self.detalleCompra = {}
    self.mostrarDetalles = false;
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
                self.detail = []
                self.detalleCompra = {}
                self.mostrarDetalles = false;
                self.detalleCompras = {aaData:[]}
                self.update()
                console.log(result)
                $.each(result.aaData, function( index, modeloTabla ) {
                    self.detail.push(modeloTabla);
                    self.mostrarDetalles = true;
                })
                self.detalleCompras.aaData = result.aaData
                self.update()
            }else{
                listadoRecepcionCompras()
            }
                callback("Consulta de detalles de la compra")
        }
    });
    
}



</script>
</recepcion-compra>