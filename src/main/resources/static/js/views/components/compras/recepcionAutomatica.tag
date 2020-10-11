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
										
										<input type="checkbox" id = "marcarDatos" name = "marcarDatos" onclick={_marcarTodos} >
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
										<textarea maxlength="250" placeHolder ="{$.i18n.prop("receptor.detalleMensaje")}" class="form-control recepcionDetalleMensaje" id="detalleMensaje" name="detalleMensaje" value="{recepcionFactura.detalleMensaje}" ></textarea> 
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
											<p>1. Le llegara un correo electronico con tres archivos.</p>
											<p>2. Descargue el documento cuyo nombre es "XML".</p>
											<p>3. Verificar el xml de respuesta se encuentre Aceptado por hacienda </p>
											<p>3. Clic Seleccionar XML de Factura </p>
											<p>4. Si todo esta bien presionar aceptar compra </p>
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
		self.recepcionFactura ={
				id:null,
				mensaje:"0",
				detalleMensaje:"",
				emisorNombre:"",
				emisorCedula:"",
				emisorTipoCedula:"",
				emisorCodigoProvincia:"0",
				emisorProvincia:"",
				emisorCanton:"",
				emisorCodigoCanton:"",
				emisorDistrito:"",
				emisorCodigoDistrito:"",
				emisorCorreo:"",
				emisorTelefono:"0",
				emisorOtraSena:"",
				receptorNombre:"",
				receptorCedula:"",
				receptorTipoCedula:"",
				receptorCorreo:"",
				receptorProvincia:"",
				receptorCodigoProvincia:"",
				receptorCanton:"",
				receptorCodigoCanton:"",
				receptorDistrito:"",
				receptorCodigoDistrito:"",
				receptorOtraSena:"",
				receptorTelefono:"0",
				receptorNombreComercial:"",
				facturaConsecutivo:"",
				facturaClave:"",
				facturaFechaEmision:"",
				facturaCondicionVenta:"0",
				facturaMedioPago:"0",
				facturaCodigoMoneda:"CRC",
				facturaTipoCambio:"1",
				facturaTotalServExentos:"0",
				facturaTotalExento:"0",
				facturaTotalVenta:"0",
				facturaTotalVentaNeta:"0",
				facturaTotalComprobante:"0",
				facturaTotalImpuestos:"0",
				
				emisorNombreComercial:"",
				facturaCodigoActividad:"0",
				facturaPlazoCredito:"0",
				facturaTotalServGravados:"0",
				facturaTotalServExonerado:"0",
				facturaTotalMercanciasGravadas:"0",
				facturaTotalMercanciasExentas:"0",
				facturaTotalMercExonerada:"0",
				facturaTotalGravado:"0",
				facturaTotalExonerado:"0",
				facturaTotalIVADevuelto:"0",
				facturaTotalOtrosCargos:"0",
				facturaTotalDescuentos:"0",
				version_doc:"4.3",
				tipoDocEmisor:"",
				detalles:"",
				condicionImpuesto:"01",			
			}
			self.xmlDoc = null
		
		//Se cargan al montar el tag
		self.on('mount',function(){
			__InformacionDataTableCuentas(); 
			listadoRecepcionCompras();
			__listadoTiposMensajes();
			__listadoTiposGasto();
			__ListaActividadesComercales();
			__listadoCondicionImpuesto()
			__MostrarPDF()
			__MostrarAceptarManual()
		    
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

	//mover a un vector las compras marcadas en el listado 
	enviarComprasCallback()
	
}

/**
**  Se va a guardar las compras que fueron chequeadas por el usuario
**/
function  moverComprasVector(callback){

	//recorrido de las compras y verificar cuales estan chequedas con estado igual "C"
	for (var count = 0; count < self.compras.aaData.length; count++) {
        if (self.compras.aaData[count].estado == "C" ){// 
        	getXML(self.compras.aaData[count],function(resultado){
                 console.log(resultado) 
							
			})
        }
    }
	console.log("cargo el vector")
    console.log(self.comprasIngresadas)
	callback("Se cargo el vector exitosamente")
}

/**
EnviarComprasCallback
**/
function enviarComprasCallback(){
	moverComprasVector(function(resultado){
		//enviar al back end el vector
		console.log(resultado)
    

	})

}
/**
Cargar el XML
**/
function getXML(data,callbackSolicitarXML) {
    $.ajax({
        url: "base64",
        datatype: "json",
        data: { ruta: data.facturaXml },
        method: "GET",
        success: function(resultado) {
		    self.documentoXML = resultado.xmlString
			self.recepcionFactura.id = data.id
			self.update()
			 __cargarXML(data,function(valor){
                callbackSolicitarXML(data)
			 });	
			
            
        },
        error: function(xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
}
/**
Cargar XML de los escoge en el listado
**/
function __cargarXML(data,callbackGetXML) {
	limpiar(function(resultado){
		console.log(resultado)
		//Se carga el XML
		self.xmlDoc = $.parseXML(self.documentoXML);
		self.recepcionFactura.id = data.id
		self.update()
		cargaEmisor(function(resultado){
            console.log(resultado)
			cargaReceptor(function(resultado){
				console.log(resultado)
				datosGeneralesFactura(function(resultado){
					console.log(resultado)
					agregarDetallesFacturaXML(function(resultado){
						console.log(resultado)
						getResumenFactura(function(resultado){
							console.log(resultado)
							agregarVentorCompras(function(resultado){
								console.log(resultado)
									__crearFactura()

							})
						})
					})  

				});
			})
		})
		callbackGetXML("Carga Exitosamente en objeto compra")

	})
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
	$('.tableListar tbody').on('change','input[type="checkbox"]', function (e) {
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
		 if (chk1.checked == false){
        	__modificarEstado(data,"C")
			 __ActualizarTablas()
		 }
		else{
			  __modificarEstado(data,"D")
			   __ActualizarTablas()
		
		}
			
	});
} 

/**
*  actualiza los data tables cuando se marcan todos los check
**/
 function __ActualizarTablas(){
     __cargarTablaCompras()         
    
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
            if ($(this).index() != 8 || $(this).index() != 0) {
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
	__MarcarCompras();
	agregarInputsCombos()
	ActivarEventoFiltro(".tableListar")
	
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
    var inputcheck = '<div ><input type="checkbox" id="' + idCheck + '"  "  ' + checked + '></div>'
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
    menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
    menu += '<li><a href="#"  title="Bajar PDF" class="  btnPDF" >Bajar PDF</a></li>'
    menu += '<li><a href="#"  title="Aceptar Manual" class="btnAceptarXMLManual  btnBajarXML" >Aceptar Manual</a></li>'
    menu += '<li><a href="#"  title="Bajar XML Respuesta de Triburacion" class="  btnRespuestaHacienda" >XML Respuesta</a></li>'
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
* Cargar el Emisor
**/
function cargaEmisor(callback){
    //Se cargan los datos del emisor
	var emisor = $(self.xmlDoc).find("Emisor");
	self.recepcionFactura.emisorNombre = emisor.find("Nombre").text();
	self.recepcionFactura.emisorCedula = emisor.find("Identificacion").find("Numero").text();
	self.recepcionFactura.emisorTipoCedula = emisor.find("Identificacion").find("Tipo").text();
	self.recepcionFactura.emisorCorreo = emisor.find("CorreoElectronico").text();
	self.recepcionFactura.emisorTelefono = emisor.find("Telefono").find("NumTelefono").text();
	self.recepcionFactura.emisorCodigoProvincia = emisor.find("Ubicacion").find("Provincia").text();
	self.recepcionFactura.emisorCodigoCanton = emisor.find("Ubicacion").find("Canton").text();
	self.recepcionFactura.emisorCodigoDistrito = emisor.find("Ubicacion").find("Distrito").text();
	self.recepcionFactura.emisorOtraSena = emisor.find("Ubicacion").find("OtrasSenas").text();
	self.recepcionFactura.emisorNombreComercial = emisor.find("NombreComercial").text();
	
	
	self.update()
	callback("Se cargaron los datos del emisor, exitosamente")

}
/**
* Cargar el Receptor
**/
function cargaReceptor(callback){
    //Se cargan los datos del Receptor
    var receptor = $(self.xmlDoc).find("Receptor");
	self.recepcionFactura.receptorNombre = __valorString(receptor.find("Nombre").text());
	self.recepcionFactura.receptorCedula = __valorString(receptor.find("Identificacion").find("Numero").text());
	self.recepcionFactura.receptorTipoCedula = __valorString(receptor.find("Identificacion").find("Tipo").text());
	self.recepcionFactura.receptorCorreo = __valorString(receptor.find("CorreoElectronico").text());
	self.recepcionFactura.receptorCodigoProvincia = __valorString(receptor.find("Ubicacion").find("Provincia").text());
	self.recepcionFactura.receptorCodigoCanton = __valorString(receptor.find("Ubicacion").find("Canton").text());
	self.recepcionFactura.receptorCodigoDistrito = __valorString(receptor.find("Ubicacion").find("Distrito").text());
	self.recepcionFactura.receptorOtraSena = __valorString(receptor.find("Ubicacion").find("OtrasSenas").text());
	self.recepcionFactura.receptorTelefono = __valorString(receptor.find("Telefono").find("NumTelefono").text());
	self.recepcionFactura.receptorNombreComercial = __valorString(receptor.find("NombreComercial").text());
	self.update()
	callback("Receptor cargado")
}
/**
* Datos Generales de la factura
**/
function datosGeneralesFactura(callback){
    //Se cargan los datos de la factura
	
	
    self.recepcionFactura.facturaConsecutivo = __valorString($(self.xmlDoc).find("NumeroConsecutivo").first().text());
    self.recepcionFactura.facturaClave = __valorString($(self.xmlDoc).find("Clave").first().text());
    self.recepcionFactura.facturaFechaEmision = __valorString(($(self.xmlDoc).find("FechaEmision")).first().text());
    self.recepcionFactura.facturaCondicionVenta = __valorString($(self.xmlDoc).find("CondicionVenta").first().text());
    self.recepcionFactura.facturaMedioPago = __valorString($(self.xmlDoc).find("MedioPago").first().text());
    self.recepcionFactura.facturaCodigoActividad = __valorString($(self.xmlDoc).find("CodigoActividad").first().text());
    self.recepcionFactura.facturaPlazoCredito = __valorString($(self.xmlDoc).find("PlazoCredito").first().text());
	self.update()
	callback("carga datos generales de la factura")
}
/**
Inicializar los impuestos
**/
function iniImpuestos(){
	var impuestos ={
		    codigo1 :'',
			codigoTarifa1:'',
			tarifa1:'',
			monto1:0,
		    codigo2 :'',
			codigoTarifa2:'',
			tarifa2:'',
			monto2:0,
		    codigo3 :'',
			codigoTarifa3:'',
			tarifa3:'',
			monto3:0,
		    codigo4 :'',
			codigoTarifa4:'',
			tarifa4:'',
			monto4:0,
		    codigo5 :'',
			codigoTarifa5:'',
			tarifa5:'',
			monto5:0,
		    codigo6 :'',
			codigoTarifa6:'',
	    	tarifa6:'',
			monto6:0,
		    codigo7 :'',
			codigoTarifa7:'',
			tarifa7:'',
			monto7:0
    }
	return impuestos

}
/**
Agregar detalles
**/
function agregarDetalle(impuestos,xmlt,numeroLinea,codigoComercial,tipoCodigoComercial){
	
	
	self.detalleServicio.data.push({
		numeroLinea     : __valorFloat(numeroLinea),
		cantidad        : __valorFloat($(xmlt).find("Cantidad").text()),
		unidadMedida    : __valorString($(xmlt).find("UnidadMedida").text()),
		detalle         : __valorString($(xmlt).find("Detalle").text()),
		precioUnitario  : __valorFloat($(xmlt).find("PrecioUnitario").text()),
		montoTotal      : __valorFloat($(xmlt).find("MontoTotal").text()),
		subTotal        : __valorFloat($(xmlt).find("SubTotal").text()),
		montoTotalLinea : __valorFloat($(xmlt).find("MontoTotalLinea").text()),
		impuestoNeto    : __valorFloat($(xmlt).find("ImpuestoNeto").text()),
		baseImponible   : __valorFloat($(xmlt).find("BaseImponible").text()),
		codigoComercialTipo   : tipoCodigoComercial,
		codigoComercialCodigo : codigoComercial,
		descuentoMonto        : __valorFloat($(xmlt).find("Descuento").find("MontoDescuento").text()),
		descuentoNaturaleza   : __valorString($(xmlt).find("Descuento").find("NaturalezaDescuento").text()),
		impuestoCodigo        : __valorString(impuestos.codigo1),
		impuestoCodigoTarifa  : __valorString(impuestos.codigoTarifa1),
		impuestoTarifa        : __valorFloat(impuestos.tarifa1),
     	impuestoMonto         : __valorFloat(impuestos.monto1),
		impuestoCodigo1        : __valorString(impuestos.codigo2),
		impuestoCodigoTarifa1  : __valorString(impuestos.codigoTarifa2),
		impuestoTarifa1        : __valorFloat(impuestos.tarifa2),
     	impuestoMonto1         : __valorFloat(impuestos.monto2),
		impuestoCodigo2        : __valorString(impuestos.codigo3),
		impuestoCodigoTarifa2  : __valorString(impuestos.codigoTarifa3),
		impuestoTarifa2        : __valorFloat(impuestos.tarifa3),
     	impuestoMonto2         : __valorFloat(impuestos.monto3),
		impuestoCodigo3        : __valorString(impuestos.codigo4),
		impuestoCodigoTarifa3  : __valorString(impuestos.codigoTarifa4),
		impuestoTarifa3        : __valorFloat(impuestos.tarifa4),
     	impuestoMonto3         : __valorFloat(impuestos.monto4),
		impuestoCodigo4        : __valorString(impuestos.codigo5),
		impuestoCodigoTarifa4  : __valorString(impuestos.codigoTarifa5),
		impuestoTarifa4        : __valorFloat(impuestos.tarifa5),
     	impuestoMonto4         : __valorFloat(impuestos.monto5),
		impuestoCodigo5        : __valorString(impuestos.codigo6),
		impuestoCodigoTarifa5  : __valorString(impuestos.codigoTarifa6),
		impuestoTarifa5        : __valorFloat(impuestos.tarifa6),
     	impuestoMonto5         : __valorFloat(impuestos.monto6),
		impuestoCodigo6        : __valorString(impuestos.codigo7),
		impuestoCodigoTarifa6  : __valorString(impuestos.codigoTarifa7),
		impuestoTarifa6        : __valorFloat(impuestos.tarifa7),
     	impuestoMonto6         : __valorFloat(impuestos.monto7),
        impuestoExoneracionTipoDocumento         : __valorString($(xmlt).find("Impuesto").find("Exoneracion").find("TipoDocumento").text()),
        impuestoExoneracionNumeroDocumento       : __valorString($(xmlt).find("Impuesto").find("Exoneracion").find("NumeroDocumento").text()),
        impuestoExoneracionNombreInstitucion     : __valorString($(xmlt).find("Impuesto").find("Exoneracion").find("NombreInstitucion").text()),
        impuestoExoneracionFechaEmision          : __valorString($(xmlt).find("Impuesto").find("Exoneracion").find("FechaEmision").text()),
        impuestoExoneracionPorcentaje            : __valorFloat($(xmlt).find("Impuesto").find("Exoneracion").find("PorcentajeExoneracion").text()),
        impuestoExoneracionMonto                 : __valorFloat($(xmlt).find("Impuesto").find("Exoneracion").find("MontoExoneracion").text()),
     });

	 self.update()	      
	var JSONDetalles = JSON.stringify(self.detalleServicio);
	self.recepcionFactura.detalles = JSONDetalles;
	self.update(); 	            


}
/**
Resumen de la factura
**/
function getResumenFactura(callback){
	//Se carga el resumen de la factura
    var resumenFactura = $(self.xmlDoc).find("ResumenFactura");
    self.recepcionFactura.facturaCodigoMoneda = __valorString(resumenFactura.find("CodigoTipoMoneda").find("CodigoMoneda").text());
	self.recepcionFactura.facturaTipoCambio = __valorFloat(resumenFactura.find("CodigoTipoMoneda").find("TipoCambio").text());
	if(self.recepcionFactura.facturaCodigoMoneda != 'CRC' && self.recepcionFactura.facturaCodigoMoneda != 'USD' ){
        self.recepcionFactura.facturaCodigoMoneda = __valorString(resumenFactura.find("CodigoMoneda").text());
        self.recepcionFactura.facturaTipoCambio = __valorFloat(resumenFactura.find("TipoCambio").text());
	}
    self.recepcionFactura.facturaTotalServExentos = __valorFloat(resumenFactura.find("TotalServExentos").text());
    self.recepcionFactura.facturaTotalExento = __valorFloat(resumenFactura.find("TotalExento").text());
    self.recepcionFactura.facturaTotalVenta = __valorFloat(resumenFactura.find("TotalVenta").text());
    self.recepcionFactura.facturaTotalVentaNeta = __valorFloat(resumenFactura.find("TotalVentaNeta").text());
    self.recepcionFactura.facturaTotalComprobante = __valorFloat(resumenFactura.find("TotalComprobante").text());
    self.recepcionFactura.facturaTotalImpuestos = __valorFloat(resumenFactura.find("TotalImpuesto").text());
    self.recepcionFactura.facturaTotalServGravados = __valorFloat(resumenFactura.find("TotalServGravados").text());
    self.recepcionFactura.facturaTotalServExonerado = __valorFloat(resumenFactura.find("TotalServExonerado").text());
    self.recepcionFactura.facturaTotalMercanciasGravadas = __valorFloat(resumenFactura.find("TotalMercanciasGravadas").text());
    self.recepcionFactura.facturaTotalMercanciasExentas = __valorFloat(resumenFactura.find("TotalMercanciasExentas").text());
    self.recepcionFactura.facturaTotalMercExonerada =__valorFloat(resumenFactura.find("TotalMercExonerada").text());
    self.recepcionFactura.facturaTotalGravado = __valorFloat(resumenFactura.find("TotalGravado").text());
    self.recepcionFactura.facturaTotalExonerado = __valorFloat(resumenFactura.find("TotalExonerado").text());
    self.recepcionFactura.facturaTotalIVADevuelto = __valorFloat(resumenFactura.find("IVADevuelto").text());
    self.recepcionFactura.facturaTotalOtrosCargos = __valorFloat(resumenFactura.find("TotalOtrosCargos").text());	                    
    self.recepcionFactura.facturaTotalDescuentos = __valorFloat(resumenFactura.find("TotalDescuentos").text());	   
	self.recepcionFactura.mensaje = $("#mensaje").val();
	self.recepcionFactura.detalleMensaje = $("#detalleMensaje").val();
	self.recepcionFactura.tipoGasto = $("#tipoGasto").val()
	self.recepcionFactura.condicionImpuesto = $("#condicionImpuesto").val()
	self.recepcionFactura.codigoActividad = $("#codigoActividad").val()                 
	self.update()	
	callback("Resumen de la factura")	
}

/**
Agregar los detalles de la factura de los que vienen en el xml
**/
function agregarDetallesFacturaXML(callback){
    //Se carga el detalle de la factura
	$("#detalleFactura").find("tr:gt(0)").remove();
        var detallesServicioXml = $(self.xmlDoc).find("DetalleServicio");
        $(detallesServicioXml).each(function () {
			var valor = __valorString($(this).find("CodigoComercial").find("Codigo").text())
			var codigoComercial = __valorString($(this).find("CodigoComercial").find("Codigo").text())
			var tipoCodigoComercial = __valorString($(this).find("CodigoComercial").find("Tipo").text())
			var numeroLinea = __valorString($(this).find("NumeroLinea").text())
           	$(this).children().each(function () {
				var impuestos = iniImpuestos()
				var impuestosItems    = this.getElementsByTagName("Impuesto");
				$.each(impuestosItems, function(i, impuesto){
				    var codigo = ''
					var codigoTarifa = ''
					var tarifa = 0 
					var monto = 0
					$(this).children().each(function () {
						var name = $(this).get(0).nodeName               
						if(name.indexOf('Codigo') != -1){
							codigo = $(this).text()
						}else if(name.indexOf('CodigoTarifa') != -1){
							codigoTarifa = $(this).text()
						}else if(name.indexOf('Tarifa') != -1){
							tarifa = $(this).text()
						}else if(name.indexOf('Monto') != -1){
							monto = $(this).text()
						} 
					});
					if(impuestos.codigo1.length ==0){
						impuestos.codigo1 = codigo;
						impuestos.codigoTarifa1 = codigoTarifa
						impuestos.tarifa1 = tarifa
						impuestos.monto1 = monto
					} else if(impuestos.codigo2.length ==0){
						impuestos.codigo2 = codigo;
						impuestos.codigoTarifa2 = codigoTarifa
						impuestos.tarifa2 = tarifa
						impuestos.monto2 = monto
					} else if(impuestos.codigo3.length ==0){
						impuestos.codigo3 = codigo;
						impuestos.codigoTarifa3 = codigoTarifa
						impuestos.tarifa3 = tarifa
						impuestos.monto3 = monto
					} else if(impuestos.codigo4.length ==0){
						impuestos.codigo4 = codigo;
						impuestos.codigoTarifa4 = codigoTarifa
						impuestos.tarifa4 = tarifa
						impuestos.monto4 = monto
					} else if(impuestos.codigo5.length ==0){
						impuestos.codigo5 = codigo;
						impuestos.codigoTarifa5 = codigoTarifa
						impuestos.tarifa5 = tarifa
						impuestos.monto5 = monto
					} else if(impuestos.codigo6.length ==0){
						impuestos.codigo6 = codigo;
						impuestos.codigoTarifa6 = codigoTarifa
						impuestos.tarifa6 = tarifa
						impuestos.monto6 = monto
					} else if(impuestos.codigo7.length ==0){
						impuestos.codigo7 = codigo;
						impuestos.codigoTarifa7 = codigoTarifa
						impuestos.tarifa7 = tarifa
					    impuestos.monto7 = monto
					}       
				});
                agregarDetalle(impuestos,this,numeroLinea,codigoComercial,tipoCodigoComercial)
				self.update();
             });
       });
      callback("agregarDetallesFacturaXML")
}


/**
Incluir en el vector de compras
**/		
function agregarVentorCompras(callback){
	 self.comprasIngresadas.dataFactura.push({
        recepcionFactura: JSON.stringify(self.recepcionFactura) ,
	 })
	 console.log("compras ingresadas al vector")
	 console.log(self.comprasIngresadas)
	 callback("Vector de compras")
}	
/**
Limpiar parametros de los objetos
**/
function limpiar(callback){
    self.comprasIngresadas = {dataFactura:[]}
	self.detalleServicio = {data:[]}
	self.recepcionFactura ={
		id:null,
		mensaje:"0",
		detalleMensaje:"",
		emisorNombre:"",
		emisorCedula:"",
		emisorTipoCedula:"",
		emisorCorreo:"",
		emisorTelefono:"0",
		emisorCodigoProvincia:"0",
		emisorProvincia:"",
		emisorCanton:"",
		emisorCodigoCanton:"",
		emisorDistrito:"",
		emisorCodigoDistrito:"",
		emisorOtraSena:"",
		receptorNombre:"",
		receptorCedula:"",
		receptorTipoCedula:"",
		receptorCorreo:"",
		receptorProvincia:"",
    	receptorCodigoProvincia:"",
		receptorCanton:"",
		receptorCodigoCanton:"",
		receptorDistrito:"",
		receptorCodigoDistrito:"",
		receptorOtraSena:"",
		receptorTelefono:"0",
		receptorNombreComercial:"",
		facturaConsecutivo:"",
		facturaClave:"",
		facturaFechaEmision:"",
		facturaCondicionVenta:"0",
		facturaMedioPago:"0",
		facturaCodigoMoneda:"CRC",
		facturaTipoCambio:"1",
		facturaTotalServExentos:"0",
		facturaTotalExento:"0",
		facturaTotalVenta:"0",
		facturaTotalVentaNeta:"0",
		facturaTotalComprobante:"0",
		facturaTotalImpuestos:"0",
		emisorNombreComercial:"",
		facturaCodigoActividad:"0",
		facturaPlazoCredito:"0",
		facturaTotalServGravados:"0",
		facturaTotalServExonerado:"0",
		facturaTotalMercanciasGravadas:"0",
		facturaTotalMercanciasExentas:"0",
		facturaTotalMercExonerada:"0",
		facturaTotalGravado:"0",
		facturaTotalExonerado:"0",
		facturaTotalIVADevuelto:"0",
		facturaTotalOtrosCargos:"0",
		facturaTotalDescuentos:"0",
		version_doc:"4.3",
		detalles:"",
		condicionImpuesto:"01",
	}
	self.update()
	callback("Se inicializo correctamente los atributos")
}
/**
*  Crear Factura nueva
**/
function __crearFactura(){
	//Se limpian los errores
	var JSONDetalles = JSON.stringify( self.comprasIngresadas);
    var temp = btoa(JSONDetalles)
    $.ajax({
        type : "POST",
        dataType : "json",
        data : {listaCompras:temp},
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
    		    $("#fileUpload").val("");
    		    $("#fileUploadMensajeArchivo").val("");		    		    
            	self.update();
                swal({
                    title: '',
                    text: data.message,
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonText: 'Aceptar',                               	  
                })		            	
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}	
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