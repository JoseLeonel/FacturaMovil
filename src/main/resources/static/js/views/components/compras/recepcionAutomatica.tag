<recepcion-api>
<input type="hidden" id = "listaCompras" name = "listaCompras"  >

				<!-- Listado  -->
				<div id="containerRecepcion">
					<div class="box">
						<div class="box-body">
							<div class="planel-body">
 							  	<div class="row">
                        			<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                            			<label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                        			</div>
                    			</div>
								<div class="row">
									<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                		<label> {$.i18n.prop("receptor.tipoCondicionImpuesto")}  <span class="requeridoDato">*</span></label>
                                		<select class="form-control condicionImpuesto" id="condicionImpuesto" name="condicionImpuesto" >
                                    		<option each={tiposCondiciones.data}  value="{valor}" selected="{recepcionFactura.condicionImpuesto==valor?true:false}">{descripcion}</option>
                                		</select>
                            		</div>     
		                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                		<label> Tipo Gasto  <span class="requeridoDato">*</span></label>
                                		<select class="form-control tipoGasto" id="tipoGasto" name="tipoGasto" >
                                    		<option value="1">{$.i18n.prop("tipo.gasto.aceptacion.compra.inventario")}</option>
                                    		<option value="2">{$.i18n.prop("tipo.gasto.aceptacion.compra.gasto")}</option>  
                                		</select>
                            		</div>                            
									<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
										<label> {$.i18n.prop("receptor.codigo.actividad")}  <span class="requeridoDato">*</span></label>
										<select class="form-control codigoActividad" id="codigoActividad" name="codigoActividad" >
											<option each={empresaActividadComercial}  value="{codigo}" >{codigo}-{descripcion}</option>
										</select>
									</div>                            
									<div class="col-md-3 col-sx-12 col-sm-3 col-lg-3">
										<label class="pull-left"> Marcar Todos <span class="requeridoDato">*</span></label>
										
										<input type="checkbox" id = "marcarDatos" class= "formatocheck" name = "marcarDatos" onclick={_marcarTodos} >
									</div>

								</div>
								<div  class="row">
									<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
										<label> {$.i18n.prop("receptor.mensaje")}  <span class="requeridoDato">*</span></label>
										<select class="form-control mensaje" id="mensaje" name="mensaje" >
											<option each={tiposMensajes.data}  value="{valor}" selected="{recepcionFactura.mensaje==valor?true:false}">{descripcion}</option>
										</select>
									</div>    
														
									<div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
										<label  >{$.i18n.prop("receptor.detalleMensaje")}</label>
										<textarea maxlength="250" placeHolder ="{$.i18n.prop("receptor.detalleMensaje")}" class="form-control  detalleMensaje" id="detalleMensaje" name="detalleMensaje" value="{recepcionFactura.detalleMensaje}" ></textarea> 
									</div>

									<div class="col-md-4 col-sx-12 col-sm-4 col-lg-4">
        								<button type="button" class="btn-green btn-add pull-left botonAceptar" onclick={__AplicarCompras}   >Aplicar</button>
									</div>


								</div>
								<div  class="row">
									<div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
										<section> 
											<a class="pull-left" href="#"    onclick = {__MostrarGuia} title="Guia para aceptar compra de un proveedor"> <span style="color:red;font-weight:bold"><u>Guia de aceptacion de compras</u></span></a><br>
											<div show="{verAyuda ==true}">
											<h1>Como aceptar la compra electronica: </h1>
											<p>1. Marque las facturas que desea aceptar.</p>
											<p>2. Presionar el boton aplicar".</p>
											</div> 
										</section> 
                            		</div>
								</div>
								
							</div>
						</div>
					</div>
</form>
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
   														    <th style="width: 4%;" class="table-header"></th>
															<th style="width: 4%;" class="table-header">Id </th>
															<th style="width: 4%;" class="table-header">Fecha Emision </th>
															<th style="width: 4%;" class="table-header">Consecutivo </th>
															<th style="width: 4%;" class="table-header">Proveedor </th>
															<th style="width: 4%;" class="table-header">Impuesto </th>
															<th style="width: 4%;" class="table-header">Moneda </th>
															<th style="width: 4%;" class="table-header">Total </th>
															<th style="width: 4%;" class="table-header">{$.i18n.prop("listado.acciones")} </th>
														</tr>
													</thead>
													<tfoot style="display: table-header-group;">
														<tr>
															<th style="width: 4%;"></th>
															<th>ID   </th>
															<th>Fecha Emision </th>
															<th>Consecutivo </th>
															<th>Proveedor </th>
															<th>Impuesto </th>
															<th>Monedas </th>
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
	    self.mostrarCargaArchivo = false;
	    self.mostrarCargaArchivoMensaje = false;
		self.mostrarFormulario    = false;	    
	    self.mostrarDetalle       = false;
		self.verAyuda             = false;
		self.tipoCedulas	   	  = {data:[]} 
		self.mediosPago	   		  = {data:[]}
		self.condicionesVenta	  = {data:[]}
		self.tiposMensajes		  = {data:[]}
		self.tiposGasto           = {data:[]}
		self.tiposCondiciones     = {data:[]}
		self.detalleServicio 	  = {data:[]}
		self.empresaActividadComercial= {}
		self.compras              = {aaData:[]}
		self.documentoXML = null;
		self.mensaje ={				
				facturaClave:"",
				mensaje:"",
		}		
		
		self.comprasIngresadas = {dataFactura:[]}
		//Se cargan al montar el tag
		self.on('mount',function(){
			__InformacionDataTableCuentas(); 
			listadoRecepcionCompras();
			__listadoTiposMensajes();
			__listadoTiposGasto();
			__ListaActividadesComercales();
			__listadoCondicionImpuesto()
			__MostrarPDF()
		    
		});

/**
Se muestra los tipos impuestos	
**/
function __listadoCondicionImpuesto(){
    self.tiposCondiciones = {data:[]}  // definir el data del datatable
    self.update()
    self.tiposCondiciones.data.push({
        valor:"01",
        descripcion:$.i18n.prop("tipo.condicion.impuesto.01")
    })
    self.update()
}

/**
aplicar compras
**/
__AplicarCompras(){
    var isAplicar = false;
 	for (var count = 0; count < self.compras.aaData.length; count++) {
        if (self.compras.aaData[count].estado == 'C' ){// Si existe actualiza  estado enviado
            isAplicar = true;
			break;
        }
    }


	//mover a un vector las compras marcadas en el listado 
	if(isAplicar){
		 swal({
           title: '',
           text: "Aceptar las compras / 接受購買",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
        }).then(function (isConfirm) {
            if(isConfirm){
				moverComprasVector(function(resultado){
					__crearFactura();
				})
			}})
       
	}else{
		sweetAlert("No hay compras seleccionadas / 沒有選擇的購買","error")
	}
	
	
}
//********************************************************************************************
/**
**  Se va a guardar las compras que fueron chequeadas por el usuario
**/
function  moverComprasVector(callback){
 	self.comprasIngresadas = {dataFactura:[]}
	 self.update()
	//recorrido de las compras y verificar cuales estan chequedas con estado igual "C"
	for (var count = 0; count < self.compras.aaData.length; count++) {
        if (self.compras.aaData[count].estado == "C" ){// 
		    agregarVentorCompras(self.compras.aaData[count].id,function(resultado){
				console.log("resultado de cargar las checkeadas")
			})
        	
        }
    }
	console.log("cargo el vector")
    console.log(self.comprasIngresadas)
	callback("Se cargo el vector exitosamente")
}
/**
Incluir en el vector de compras
**/		
function agregarVentorCompras(id,callback){
	 self.comprasIngresadas.dataFactura.push({
        id: id
	 })
	 console.log("compras ingresadas al vector")
	 console.log(self.comprasIngresadas)
	 callback("Vector de compras")
}	

/**
*  Crear Factura nueva
**/
function __crearFactura(){
	//Se limpian los errores
	var JSONDetalles = JSON.stringify( self.comprasIngresadas);
    var temp = btoa(JSONDetalles)
	var parametros = {
		condicionImpuesto: $("#condicionImpuesto").val(),
		tipoGasto:$("#tipoGasto").val(),
		codigoActividad:$("#codigoActividad").val(),
		mensaje:$("#mensaje").val(),
		detalleMensaje:$("#detalleMensaje").val(),
		listaCompras:temp
	}
    $.ajax({
        type : "POST",
        dataType : "json",
        data : parametros,
        url : "recepcionComprasMasivas.do",
        success : function(data) {
            if (data.status != 200) {
            	serverMessageJson(data);
                if (data.message != null && data.message.length > 0) {
                    mensajeError(data.message)
                }
            } else {
            	serverMessageJson(data);
    		    self.mostrarFormulario     = false;
    		    self.mostrarCargaArchivo   = false;		    		    
    		    self.mostrarCargaArchivoMensaje   = true;		    		    
    		  //  $("#fileUpload").val("");
    		  //  $("#fileUploadMensajeArchivo").val("");		    		    
            	self.update();
                swal({
                    title: '',
                    text: data.message,
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonText: 'Aceptar',                               	  
                })	
                listadoRecepcionCompras()	            	
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}	

/**
Marca todos los ckeckbox
**/		
_marcarTodos(){
	if ($('#marcarDatos').is(':checked')) {
        $("input[type=checkbox]").prop('checked', true); //todos los check
		marcarVector("C")
	}else{
		$("input[type=checkbox]").prop('checked', false); //todos los check
		marcarVector("P")
	}	
}
/**
**  Se va a guardar las compras que fueron chequeadas por el usuario
**/
function  marcarVector(valor){
	//recorrido de las compras y verificar cuales estan chequedas con estado igual "C"
	for (var count = 0; count < self.compras.aaData.length; count++) {
        self.compras.aaData[count].estado = valor 
    }
	self.update()
    
}
/**
*Marcar o desmarcar 
**/
function __MarcarCompras() {
	$('.tableListar tbody').on('click','input[type="checkbox"]', function (e) {
	//	$("#marcarDatos").prop('checked', false);
        var check1 =  ($(this).attr('id'));
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			/*cuando el datatable esta en modo responsive*/
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	     }
         var chk1 =  document.getElementById(check1)
         // Este IF es para cuando usuario deschequea el SIM y se debe reversar el estado
		 //return
		 if (chk1.checked == false){
        	__modificarEstado(data,"D")
		 }
		else{
			__modificarEstado(data,"C")
		}
			
	});
} 


/**
**  Cambia el Valor de estado de solicitud de liberacion del numero 
**/
function __modificarEstado(elemento,valorEstado){
    for (var count = 0; count < self.compras.aaData.length; count++) {
        if (self.compras.aaData[count].id == elemento.id ){// Si existe actualiza  estado enviado
        	self.compras.aaData[count].estado = valorEstado;
            break;
        }
    }
    self.update();
}
/**
* Eliminar del vector de las facturas checkeadas aceptadas si el usuario descheckea
**/
function eliminarVectorFactruasCheckeadasAceptadas(id){
 	for (var count = 0; count < self.comprasIngresadas.dataFactura.length; count++) {
        if (self.comprasIngresadas.dataFactura[count].recepcionFactura.id == id ){
             self.comprasIngresadas.dataFactura.splice(count, 1);
             self.update()
        }
    }
}
/**
Mostrar la guia de ayuda
**/
__MostrarGuia(){
	if(self.verAyuda ==true){
		self.verAyuda = false
	}else{
    	self.verAyuda = true
	}
	self.update()
}

/**
Listado de recepcion de compras
**/		
function listadoRecepcionCompras() {
	inicializarlista(function(resultado){
       listaRecepcionCompras()
	})
}
function listaRecepcionCompras(){
    $.ajax({
        url: 'listarRecepcionCompras.do',
        datatype: "json",
        method: "GET",
        success: function(result) {
            if (result.aaData.length > 0) {
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
            if ($(this).index() != 8 && $(this).index() != 0) {
                $(this).html(name + 'type="text" class="form-control"  placeholder="' + title + '" />');
            }
        })
}
/**
Carga Tablas de compras
**/
function __cargarTablaCompras() {
   inicializarlista(function(resultado){
    $("#tableListar").dataTable().fnAddData(self.compras.aaData);
	__MarcarCompras();
	agregarInputsCombos()
	ActivarEventoFiltro(".tableListar")

   })
	
}

function inicializarlista(callback){
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
        "sDom": 'lrtip',
        "order": [],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        "columns": self.formato_tabla ,
    })
    callback("listo")
}
/**
 * Formato del listado
 */
function __InformacionDataTableCuentas(){
	self.formato_tabla = [{
			'data': 'id',
			"name": "id",
			"bSortable": false,
			"bSearchable": false,
			"autoWidth": false,
			"render": function(id, type, row) {
				return __checkbox(row);
			}
		},
		{ 'data': 'id', "name": "id", "title": "#id", "autoWidth": true },
		{ 'data': 'consecutivo', "name": "consecutivo", "title": "#Consecutivo", "autoWidth": true },
		{ 'data': 'fechaEmision', "name": "fechaEmision", "title": "Fecha Emision", "autoWidth": true },
		{ 'data': 'emisorFactura', "name": "emisorFactura", "title": "#Proveedor", "autoWidth": true },
		{ 'data': 'totalImpuestos', "name": "totalImpuestos", "title": "IVA", "autoWidth": true },
		{ 'data': 'moneda', "name": "moneda", "title": "Moneda", "autoWidth": true },
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
check de cuentas por cobrar
**/
function __checkbox(row) {
    var idCheck = 'check-' + row.id;
    var checked = " ";
   checked = row.estado == "C" ?"checked ":checked;
    var inputcheck = '<div ><input type="checkbox" class="formatocheckFiltroListado" id="' + idCheck + '"  "  ' + checked + '></div>'
    return inputcheck;
}
/**
 * Opciones listado de los clientes
 */
function __Opciones(id, type, row) {
    let menu = '<div class="dropdown">'
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>'
    menu += '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    menu += '<li><a href="#"  title="Bajar PDF" class="  btnPDF" >Bajar PDF</a></li>'
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
            direccion: "bajarArchivo.do?filename=" + data.facturaPdf,
            stylemodal: "modal-xl"
        }
        riot.mount('view-pdf', { datos: parametros });

    });
}

/**
Agregar detalles
**/



/**
Se muestra los tipos medio de pago	
**/
function __listadoTiposMensajes(){
    self.tiposMensajes = {data:[]}  // definir el data del datatable
    self.update()
    self.tiposMensajes.data.push({
        valor:"01",
        descripcion:$.i18n.prop("tipo.mensaje.aceptado")
    })
    self.tiposMensajes.data.push({
        valor:"02",
        descripcion:$.i18n.prop("tipo.mensaje.aceptado.parcial")
	 })
    self.tiposMensajes.data.push({
        valor:"03",
        descripcion:$.i18n.prop("tipo.mensaje.aceptado.rechazado")
    })
    self.update()
}
/**
Se muestra los tipos medio de pago	
**/
function __listadoTiposGasto(){
    self.tiposGasto = {data:[]}  // definir el data del datatable
    self.update()
    self.tiposGasto.data.push({
        valor:"01",
        descripcion:$.i18n.prop("tipo.gasto.inventario.mensaje")
    })
    self.tiposGasto.data.push({
        valor:"02",
        descripcion:$.i18n.prop("tipo.gasto.gasto.mensaje")
    })
    self.update()
}
/**
Se muestra los tipos impuestos	
**/
function __listadoCondicionImpuesto(){
    self.tiposCondiciones = {data:[]}  // definir el data del datatable
    self.update()
    self.tiposCondiciones.data.push({
        valor:"01",
        descripcion:$.i18n.prop("tipo.condicion.impuesto.01")
    })
    self.update()
}
/**
*  Lista de las actividades
**/
function __ListaActividadesComercales(){
    $.ajax({
        url: 'ListaEmpresaActividadComercialPorPricipalAjax.do',
        datatype: "json",
        global: false,
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.empresaActividadComercial   = result.aaData
                self.update()
                BuscarActividadComercial()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
    return
}
/**
Buscar Actividades Comerciales
**/
function BuscarActividadComercial(){
    var codigo =$('#selectActividadComercial').val()
    if(self.empresaActividadComercial == null){
       return    
    }
    if(self.empresaActividadComercial.length == 0){
       return    
    }
    $.each(self.empresaActividadComercial, function( index, modeloTabla ) {
        if(modeloTabla.codigo == codigo  ){
           self.actividadComercial.descripcion = modeloTabla.codigo +"-" + modeloTabla.descripcion
            self.actividadComercial.codigo =  codigo
            self.factura.codigoActividad = codigo
            self.update()
        }

    })
}	
</script>
</recepcion-api>