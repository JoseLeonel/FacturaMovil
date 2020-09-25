<recepcion-api>

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
										<input name="select_all" class="pull-left" value="1" type="checkbox">
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
        								<button type="button" class="btn-green btn-add pull-left botonAceptar" >Aplicar</button>
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

					<div class="row" show="{mostrarDetalle == false}">
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
		self.archivo ={				
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
				facturaCodigoMoneda:"0",
				facturaTipoCambio:"0",
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
				detalles:"",
				condicionImpuesto:"01",			
			}
		
		//Se cargan al montar el tag
		self.on('mount',function(){
		
		   
            __InformacionDataTableCuentas(); 
			listadoRecepcionCompras();

			//
		  	    __listadoTiposMensajes();
			__listadoTiposGasto();
		    __ListaActividadesComercales();
			__MostrarPDF()
			__MostrarAceptarManual()
		    
		});

		//Se actualiza la pagina
		//self.update();

		__MostrarGuia(){
			if(self.verAyuda ==true){
				self.verAyuda = false
			}else{
				self.verAyuda = true
			}
			self.update()
		}

		//Rereso a la pantalla para cargar otro archivo
		__regresarAlListado(){
			self.mostrarCargaArchivoMensaje = true;
			self.mostrarCargaArchivo = true;
		    self.mostrarFormulario   = true;
		    self.mostrarDetalle      = false;
		    self.update();
		}

		__detalle(){
			self.mostrarCargaArchivo = false;
			self.mostrarCargaArchivoMensaje = false;
		    self.mostrarFormulario   = false;
		    self.mostrarDetalle      = true;
		    self.update();			
		}
		
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

            }
        }


    });
}

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




function __MostrarAceptarManual() {
    $('.tableListar').on('click', '.btnAceptarXMLManual', function(e) {
        var table = $('#tableListar').DataTable();
        if (table.row(this).child.isShown()) {
            //cuando el datatable esta en modo responsive
            var data = table.row(this).data();
        } else {
            var data = table.row($(this).parents("tr")).data();
        }
        getXML(data)

    });
}

function getXML(data) {
    $.ajax({
        url: "base64",
        datatype: "json",
        data: { ruta: data.facturaXml },
        method: "GET",
        success: function(data) {
		    self.documentoXML = data.xmlString
			self.mostrarCargaArchivo = false;
			self.mostrarCargaArchivoMensaje = false;
		    self.mostrarFormulario   = false;
		    self.mostrarDetalle      = true;
			self.update()
              __cargarXML();

        },
        error: function(xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });


}
			

		
		//Se muestra los tipos medio de pago	
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
		//Se muestra los tipos medio de pago	
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

		//Se muestra los tipos impuestos	
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
/**
* Cargar el Emisor
**/
function cargaEmisor(){
    //Se cargan los datos del emisor
	var emisor = $(xmlDoc).find("Emisor");
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

}

/**
* Cargar el Receptor
**/
function cargaReceptor(){
    //Se cargan los datos del Receptor
    var receptor = $(xmlDoc).find("Receptor");
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

}

function datosGeneralesFactura(){
    //Se cargan los datos de la factura
    self.recepcionFactura.facturaConsecutivo = __valorString($(xmlDoc).find("NumeroConsecutivo").first().text());
    self.recepcionFactura.facturaClave = __valorString($(xmlDoc).find("Clave").first().text());
    self.recepcionFactura.facturaFechaEmision = __valorString(($(xmlDoc).find("FechaEmision")).first().text());
    self.recepcionFactura.facturaCondicionVenta = __valorString($(xmlDoc).find("CondicionVenta").first().text());
    self.recepcionFactura.facturaMedioPago = __valorString($(xmlDoc).find("MedioPago").first().text());
    self.recepcionFactura.facturaCodigoActividad = __valorString($(xmlDoc).find("CodigoActividad").first().text());
    self.recepcionFactura.facturaPlazoCredito = __valorString($(xmlDoc).find("PlazoCredito").first().text());

}
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

function agregarDetalle(impuestos,xmlt){
	self.detalleServicio.data.push({
		detalleImpuestos : impuestos,	
		numeroLinea     : 0,
		cantidad        : __valorFloat($(xmlt).find("Cantidad").text()),
		unidadMedida    : __valorString($(xmlt).find("UnidadMedida").text()),
		detalle         : __valorString($(xmlt).find("Detalle").text()),
		precioUnitario  : __valorFloat($(xmlt).find("PrecioUnitario").text()),
		montoTotal      : __valorFloat($(xmlt).find("MontoTotal").text()),
		subTotal        : __valorFloat($(xmlt).find("SubTotal").text()),
		montoTotalLinea : __valorFloat($(xmlt).find("MontoTotalLinea").text()),
		impuestoNeto    : __valorFloat($(xmlt).find("ImpuestoNeto").text()),
		baseImponible   : __valorFloat($(xmlt).find("BaseImponible").text()),
		codigoComercialTipo   : "",
		codigoComercialCodigo : "",
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


}

function getResumenFactura(){
    self.recepcionFactura.facturaCodigoMoneda = __valorString(resumenFactura.find("CodigoTipoMoneda").find("CodigoMoneda").text());
	self.recepcionFactura.facturaTipoCambio = __valorFloat(resumenFactura.find("CodigoTipoMoneda").find("TipoCambio").text());
	if(self.recepcionFactura.facturaCodigoMoneda != 'CRC' && self.archivo.facturaCodigoMoneda != 'USD' ){
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
	//Se cargan los datos del objecto a almacenar en base de datos
	self.recepcionFactura.tipoGasto = 1
	self.recepcionFactura.condicionImpuesto = $("#condicionImpuesto").val()
	self.recepcionFactura.codigoActividad = $("#codigoActividad").val()
	self.recepcionFactura.facturaConsecutivo = self.archivo.facturaConsecutivo;
	self.recepcionFactura.facturaClave = self.archivo.facturaClave;
	self.recepcionFactura.facturaFechaEmision = self.archivo.facturaFechaEmision;
	self.recepcionFactura.facturaCondicionVenta = self.archivo.facturaCondicionVenta;
	self.recepcionFactura.facturaMedioPago = self.archivo.facturaMedioPago;
	self.recepcionFactura.facturaCodigoMoneda = self.archivo.facturaCodigoMoneda;
	self.recepcionFactura.facturaTipoCambio = self.archivo.facturaTipoCambio;
	self.recepcionFactura.facturaTotalServExentos = self.archivo.facturaTotalServExentos;
	self.recepcionFactura.facturaTotalExento = self.archivo.facturaTotalExento;
	self.recepcionFactura.facturaTotalVenta = self.archivo.facturaTotalVenta;
	self.recepcionFactura.facturaTotalVentaNeta = self.archivo.facturaTotalVentaNeta;
	self.recepcionFactura.facturaTotalComprobante = self.archivo.facturaTotalComprobante;
	self.recepcionFactura.facturaTotalImpuestos = self.archivo.facturaTotalImpuestos;
	self.recepcionFactura.emisorNombreComercial = self.archivo.emisorNombreComercial;
	self.recepcionFactura.facturaCodigoActividad = self.archivo.facturaCodigoActividad;
	self.recepcionFactura.facturaPlazoCredito = self.archivo.facturaPlazoCredito;
	self.recepcionFactura.facturaTotalServGravados = self.archivo.facturaTotalServGravados;
	self.recepcionFactura.facturaTotalServExonerado = self.archivo.facturaTotalServExonerado;
	self.recepcionFactura.facturaTotalMercanciasGravadas = self.archivo.facturaTotalMercanciasGravadas;
   	self.recepcionFactura.facturaTotalMercanciasExentas = self.archivo.facturaTotalMercanciasExentas;
   	self.recepcionFactura.facturaTotalMercExonerada = self.archivo.facturaTotalMercExonerada;
   	self.recepcionFactura.facturaTotalGravado = self.archivo.facturaTotalGravado;
   	self.recepcionFactura.facturaTotalExonerado = self.archivo.facturaTotalExonerado;
   	self.recepcionFactura.facturaTotalIVADevuelto = self.archivo.facturaTotalIVADevuelto;
   	self.recepcionFactura.facturaTotalOtrosCargos = self.archivo.facturaTotalOtrosCargos;				
}

function __cargarXML() {
	limpiar()
    self.archivo ={}
	self.update()
	//Se carga el XML
    var xmlDoc = $.parseXML(self.documentoXML);
	cargaEmisor()
	cargaReceptor()
	datosGeneralesFactura();
    //Se carga el detalle de la factura
	$("#detalleFactura").find("tr:gt(0)").remove();
        var detallesServicioXml = $(xmlDoc).find("DetalleServicio");
        $(detallesServicioXml).each(function () {
			var valor = __valorString($(this).find("CodigoComercial").find("Codigo").text())
           	$(this).children().each(function () {
				var impuestos = iniImpuestos
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
                agregarDetalle(impuestos,this)
             });
       });
        //Se carga el resumen de la factura
        var resumenFactura = $(xmlDoc).find("ResumenFactura");
        self.update();
		getResumenFactura()
}
		
	

		function limpiar(){
			self.detalleServicio = {data:[]}
			self.archivo ={				
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
				emisorNombreComercial:"",
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
				facturaCodigoMoneda:"0",
				facturaTipoCambio:"0",
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
			}
		
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
		}
		
		
		function __buscaProvincias(){
			
			
			if(self.archivo.emisorCodigoProvincia > 0){
				$.ajax({
			        url: "BuscaProvinciaAjax.do",
			        datatype: "json",
			        data: { "codigoProvincia": self.archivo.emisorCodigoProvincia},
			        method:"GET",
			        success: function (data) {
			        	self.archivo.emisorProvincia = data.descripcion;
			        	self.recepcionFactura.emisorProvincia = data.descripcion;
					    self.update();
			        },
			        error: function (xhr, status) {
			            console.log(xhr);
			            mensajeErrorServidor(xhr, status);
			        }
			    });				
			}
			

			if(self.archivo.receptorCodigoProvincia > 0){
				$.ajax({
			        url: "BuscaProvinciaAjax.do",
			        datatype: "json",
			        data: { "codigoProvincia": self.archivo.receptorCodigoProvincia},
			        method:"GET",
			        success: function (data) {
			        	self.archivo.receptorProvincia = data.descripcion;
			        	self.recepcionFactura.receptorProvincia = data.descripcion;
					    self.update();
			        },
			        error: function (xhr, status) {
			            console.log(xhr);
			            mensajeErrorServidor(xhr, status);
			        }
			    });
			}
		}

		function __buscaCantones(){
			if(self.archivo.emisorCodigoProvincia > 0 && self.archivo.emisorCodigoCanton > 0){
				$.ajax({
			        url: "BuscaCantonAjax.do",
			        datatype: "json",
			        data: { "codigoProvincia": self.archivo.emisorCodigoProvincia, "codigoCanton": self.archivo.emisorCodigoCanton},
			        method:"GET",
			        success: function (data) {
			        	self.archivo.emisorCanton = data.descripcion;
			        	self.recepcionFactura.emisorCanton = data.descripcion;
					    self.update();
			        },
			        error: function (xhr, status) {
			            console.log(xhr);
			            mensajeErrorServidor(xhr, status);
			        }
			    });
			}
			
			if(self.archivo.receptorCodigoProvincia > 0 && self.archivo.receptorCodigoCanton > 0){
				$.ajax({
			        url: "BuscaCantonAjax.do",
			        datatype: "json",
			        data: { "codigoProvincia": self.archivo.receptorCodigoProvincia, "codigoCanton": self.archivo.receptorCodigoCanton},
			        method:"GET",
			        success: function (data) {
			        	self.archivo.receptorCanton = data.descripcion;
			        	self.recepcionFactura.receptorCanton = data.descripcion;
					    self.update();
			        },
			        error: function (xhr, status) {
			            console.log(xhr);
			            mensajeErrorServidor(xhr, status);
			        }
			    });
			}
		}
		
		function __buscaDistritos(){
			if(self.archivo.emisorCodigoProvincia > 0 && self.archivo.emisorCodigoCanton > 0 && self.archivo.emisorCodigoDistrito > 0){				
				$.ajax({
			        url: "BuscaDistritoAjax.do",
			        datatype: "json",
			        data: { "codigoProvincia": self.archivo.emisorCodigoProvincia, "codigoCanton": self.archivo.emisorCodigoCanton, "codigoDistrito": self.archivo.emisorCodigoDistrito},
			        method:"GET",
			        success: function (data) {
			        	self.archivo.emisorDistrito = data.descripcion;
			        	self.recepcionFactura.emisorDistrito = data.descripcion;
					    self.update();
			        },
			        error: function (xhr, status) {
			            console.log(xhr);
			            mensajeErrorServidor(xhr, status);
			        }
			    });
			}

			if(self.archivo.receptorCodigoProvincia > 0 && self.archivo.receptorCodigoCanton > 0 && self.archivo.receptorCodigoDistrito > 0){
				$.ajax({
			        url: "BuscaDistritoAjax.do",
			        datatype: "json",
			        data: { "codigoProvincia": self.archivo.receptorCodigoProvincia, "codigoCanton": self.archivo.receptorCodigoCanton, "codigoDistrito": self.archivo.receptorCodigoDistrito},
			        method:"GET",
			        success: function (data) {
			        	self.archivo.receptorDistrito = data.descripcion;
			        	self.recepcionFactura.receptorDistrito = data.descripcion;
					    self.update();
			        },
			        error: function (xhr, status) {
			            console.log(xhr);
			            mensajeErrorServidor(xhr, status);
			        }
			    });
			}
		}

		/**
		*  Crear Factura nueva
		**/
		__crearFactura(){
			
			//Se limpian los errores
			$(".errorServerSideJgrid").remove();
			self.recepcionFactura.mensaje = $("#mensaje").val();
			self.recepcionFactura.detalleMensaje = $("#detalleMensaje").val();
			self.recepcionFactura.tipoGasto = $("#tipoGasto").val()
			self.recepcionFactura.condicionImpuesto = $("#condicionImpuesto").val()
			self.recepcionFactura.codigoActividad = $("#codigoActividad").val()
		          
			var JSONDetalles = JSON.stringify(self.detalleServicio);
			self.recepcionFactura.detalles = JSONDetalles;
			self.update();
		    $.ajax({
		        type : "POST",
		        dataType : "json",
		        data : self.recepcionFactura,
		        url : "AgregarRecepcionFacturaAjax.do",
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
	</script>
</recepcion-api>