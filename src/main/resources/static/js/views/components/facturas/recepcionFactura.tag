<recepcion-factura>
   <div class="row">
        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
        </div>      
    </div>              
	<div id="formularioCargaArchivo" class="row center" show={mostrarCargaArchivo}>
    	<div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-8 col-lg-8 col-sx-12 col-sm-8">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {$.i18n.prop("titulo.aceptar.factura")}</h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name="formulario" 	class="advanced-search-form">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                             <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("archivo.factura")}  <span class="requeridoDato">*</span></label>
					     		<input type="file" id="fileUpload" />
                            </div>
                        </div>
					</form>
				</div>				
			</div>
		</div>						
	</div>
	<div id="formularioAceptar" class="row center" show={mostrarFormulario} >
    	<div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-8 col-lg-8 col-sx-12 col-sm-8">
            <div class="box box-solid box-primary">
                <div class="box-body">
                    <form id="formularioAceptarForm" name="formularioAceptarForm" class="advanced-search-form">
				        <input type="hidden" name="tipoCedulaEmisor" id="tipoCedulaEmisor" value="{recepcionFactura.emisorTipoCedula}">
                        <div class="row">                        
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> {$.i18n.prop("factura.clave")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control recepcionClave" placeHolder ="{$.i18n.prop("factura.clave")}" id="clave" name="clave" value="{recepcionFactura.clave}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("emisor.cedula")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control recepcionCedulaEmisor" placeHolder ="{$.i18n.prop("emisor.cedula")}" id="cedulaEmisor" name="cedulaEmisor" value="{recepcionFactura.cedulaEmisor}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.fechaEmision")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control recepcionFechaEmision" placeHolder ="{$.i18n.prop("factura.fechaEmision")}" id="fechaEmision" name="fechaEmision" value="{recepcionFactura.fechaEmision}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalImpuestos")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control recepcionTotalImpuestos" placeHolder ="{$.i18n.prop("factura.totalImpuestos")}" id="totalImpuestos" name="totalImpuestos" value="{recepcionFactura.totalImpuestos}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalComprobante")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control recepcionTotalVentaNeta" placeHolder ="{$.i18n.prop("factura.totalComprobante")}" id="totalFactura" name="totalFactura" value="{recepcionFactura.totalFactura}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("receptor.cedula")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control recepcionReceptorCedula" placeHolder ="{$.i18n.prop("receptor.cedula")}" id="cedulaReceptor" name="cedulaReceptor" value="{recepcionFactura.cedulaReceptor}">
                            </div>                                                        
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label> {$.i18n.prop("receptor.mensaje")}  <span class="requeridoDato">*</span></label>
                                <select class="form-control receptorMensaje" id="mensaje" name="mensaje" >
                                    <option each={tiposMensajes.data}  value="{valor}" selected="{recepcionFactura.mensaje==valor?true:false}">{descripcion}</option>
                                </select>
                            </div>                            
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label  >{$.i18n.prop("receptor.detalleMensaje")}</label>
                                <textarea maxlength="250" placeHolder ="{$.i18n.prop("receptor.detalleMensaje")}" class="form-control recepcionDetalleMensaje" id="detalleMensaje" name="detalleMensaje" value="{recepcionFactura.detalleMensaje}" ></textarea> 
                            </div>
                            
						</div>
					</form>
				</div>				
				<div class="box-footer">
	                <div class="row">
	                     <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
	                         <button onclick={__detalle}    type="button" class="btn-green btn-edit pull-left" id="btnDetalle" name ="btnDetalle"> 
	                         	{$.i18n.prop("btn.detalle")}
	                         </button>
	                     </div>
	                     <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
	                         <button onclick={__crearFactura}   type="button" class="btn-green btn-add pull-right" id="btnAceptar" name ="btnAceptar">
	                         	 {$.i18n.prop("btn.aceptar")}
	                        	</button>
	                     </div>
	                 </div>    
	            </div>    
			</div>
		</div>						
	</div>	
	<div id="formularioDetalle" class="row center" show={mostrarDetalle} >
    	<div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-8 col-lg-8 col-sx-12 col-sm-8">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {$.i18n.prop("titulo.aceptar.factura")}</h1>
                </div>
                <div class="box-body">
                    <form id = "formularioDetalle" name="formularioDetalle" class="advanced-search-form">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
	                         	<h3> <p class="text-primary">{$.i18n.prop("titulo.datos.emisor")}</p></h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("emisor.cedula")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorCedula" placeHolder ="{$.i18n.prop("emisor.cedula")}" id="emisorCedula" name="emisorCedula" value="{archivo.emisorCedula}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("emisor.tipoCedula")}  <span class="requeridoDato">*</span></label>
                                <select disabled="disabled" class="form-control emisorTipoCedula" id="emisorTipoCedula" name="emisorTipoCedula" >
                                    <option each={tipoCedulas.data}  value="{valor}" selected="{archivo.emisorTipoCedula==valor?true:false}">{descripcion}</option>
                                </select>
                            </div>                            
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> {$.i18n.prop("emisor.nombre")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorNombre" placeHolder ="{$.i18n.prop("emisor.nombre")}" id="emisorNombre" name="emisorNombre" value="{archivo.emisorNombre}">
                            </div>
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label> {$.i18n.prop("emisor.correoElectronico")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorCorreo" id="emisorCorreo" name="emisorCorreo" placeHolder="{$.i18n.prop("emisor.correoElectronico")}" value="{archivo.emisorCorreo}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("emisor.provincia")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorProvincia" id="emisorProvincia" name="emisorProvincia" placeHolder="{$.i18n.prop("emisor.provincia")}" value="{archivo.emisorProvincia}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("emisor.canton")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorCanton" id="emisorCanton" name="emisorCanton" placeHolder="{$.i18n.prop("emisor.canton")}" value="{archivo.emisorCanton}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("emisor.distrito")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorDistrito" id="emisorDistrito" name="emisorDistrito" placeHolder="{$.i18n.prop("emisor.distrito")}" value="{archivo.emisorDistrito}">
                            </div>
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label  >{$.i18n.prop("emisor.otrasSenas")}</label>
                                <textarea readonly="readonly" maxlength="250" placeHolder ="{$.i18n.prop("emisor.otrasSenas")}" class="form-control emisorOtraSena" id="emisorOtraSena" name="emisorOtraSena" value="{archivo.emisorOtraSena}" ></textarea> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
	                         	<h3> <p class="text-primary">{$.i18n.prop("titulo.datos.receptor")}</p></h3>
                            </div>
                        </div>
						<div class="row">
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("receptor.cedula")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control receptorCedula" placeHolder ="{$.i18n.prop("receptor.cedula")}" id="receptorCedula" name="receptorCedula" value="{archivo.receptorCedula}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("receptor.tipoCedula")}  <span class="requeridoDato">*</span></label>
                                <select disabled="disabled" class="form-control receptorTipoCedula " id="receptorTipoCedula" name="receptorTipoCedula" >
                                    <option each={tipoCedulas.data}  value="{valor}" selected="{archivo.receptorTipoCedula ==valor?true:false}">{descripcion}</option>
                                </select>
                            </div>                            
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> {$.i18n.prop("receptor.nombre")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control receptorNombre" placeHolder ="{$.i18n.prop("receptor.nombre")}" id="receptorNombre" name="receptorNombre" value="{archivo.receptorNombre}">
                            </div>
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label> {$.i18n.prop("receptor.nombreComercial")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control receptorNombreComercial" placeHolder ="{$.i18n.prop("receptor.nombreComercial")}" id="receptorNombreComercial" name="receptorNombreComercial" value="{archivo.receptorNombreComercial}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("receptor.telefono")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control receptorTelefono" id="receptorTelefono" name="receptorTelefono" placeHolder="{$.i18n.prop("receptor.telefono")}" value="{archivo.receptorTelefono}">
                            </div>
                            <div class= "col-md-9 col-sx-9 col-sm-9 col-lg-9">
                                <label> {$.i18n.prop("receptor.correoElectronico")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control receptorCorreo" id="receptorCorreo" name="receptorCorreo" placeHolder="{$.i18n.prop("receptor.correoElectronico")}" value="{archivo.receptorCorreo}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("receptor.provincia")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control receptorProvincia" id="receptorProvincia" name="receptorProvincia" placeHolder="{$.i18n.prop("receptor.provincia")}" value="{archivo.receptorProvincia}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("receptor.canton")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control receptorCanton" id="receptorCanton" name="receptorCanton" placeHolder="{$.i18n.prop("receptor.canton")}" value="{archivo.receptorCanton}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("receptor.distrito")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control receptorDistrito" id="receptorDistrito" name="receptorDistrito" placeHolder="{$.i18n.prop("receptor.distrito")}" value="{archivo.receptorDistrito}">
                            </div>
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label  >{$.i18n.prop("receptor.otrasSenas")}</label>
                                <textarea readonly="readonly" maxlength="250" placeHolder ="{$.i18n.prop("receptor.otrasSenas")}" class="form-control receptorOtraSena" id="receptorOtraSena" name="receptorOtraSena" value="{archivo.receptorOtraSena}" ></textarea> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
	                         	<h3> <p class="text-primary">{$.i18n.prop("titulo.datos.factura")}</p></h3>
                            </div>
                        </div>                	
						<div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> {$.i18n.prop("factura.consecutivo")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaConsecutivo" placeHolder ="{$.i18n.prop("factura.consecutivo")}" id="facturaConsecutivo" name="facturaConsecutivo" value="{archivo.facturaConsecutivo}">
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> {$.i18n.prop("factura.clave")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaClave" placeHolder ="{$.i18n.prop("factura.clave")}" id="facturaClave" name="facturaClave" value="{archivo.facturaClave}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.fechaEmision")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaFechaEmision" placeHolder ="{$.i18n.prop("factura.fechaEmision")}" id="facturaFechaEmision" name="facturaFechaEmision" value="{archivo.facturaFechaEmision}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.condicionVenta")}  <span class="requeridoDato">*</span></label>
                                <select disabled="disabled" class="form-control facturaCondicionVenta" id="facturaCondicionVenta" name="facturaCondicionVenta" >
                                    <option each={condicionesVenta.data}  value="{valor}" selected="{archivo.facturaCondicionVenta==valor?true:false}">{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.medioPago")}  <span class="requeridoDato">*</span></label>
                                <select disabled="disabled" class="form-control facturaMedioPago" id="facturaMedioPago" name="facturaMedioPago" >
                                    <option each={mediosPago.data}  value="{valor}" selected="{archivo.facturaMedioPago==valor?true:false}">{descripcion}</option>
                                </select>
                            </div>
                        </div>
					    <div class="row">
				            <div class="col-sx-12 col-lg-12 col-md-12 col-sm-12 " style="width:100%; padding-top: 15px">
				                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar"   cellspacing="0" width="100%">
				                        <thead id="detalleFactura">
				                            <tr>
				                                <th class="table-header" >{$.i18n.prop("factura.cantidad")}</th>
				                                <th class="table-header" >{$.i18n.prop("factura.unidadMedida")}</th>
				                                <th class="table-header" >{$.i18n.prop("factura.detalle")}</th>
				                                <th class="table-header" >{$.i18n.prop("factura.precioUnitario")}</th>
				                                <th class="table-header" >{$.i18n.prop("factura.montoTotal")}</th>
				                                <th class="table-header" >{$.i18n.prop("factura.subTotal")}</th>
				                                <th class="table-header" >{$.i18n.prop("factura.montoTotalLinea")}</th>
				                            </tr>
				                        </thead>
				                    </table>
				            </div>
					    </div>
						<div class="row">
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.codigoMoneda")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaCodigoMoneda" placeHolder ="{$.i18n.prop("factura.codigoMoneda")}" id="facturaCodigoMoneda" name="facturaCodigoMoneda" value="{archivo.facturaCodigoMoneda}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.tipoCambio")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTipoCambio" placeHolder ="{$.i18n.prop("factura.tipoCambio")}" id="facturaTipoCambio" name="facturaTipoCambio" value="{archivo.facturaTipoCambio}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalServExentos")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalServExentos" placeHolder ="{$.i18n.prop("factura.totalServExentos")}" id="facturaTotalServExentos" name="facturaTotalServExentos" value="{archivo.facturaTotalServExentos}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalVenta")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalVenta" placeHolder ="{$.i18n.prop("factura.totalVenta")}" id="facturaTotalVenta" name="facturaTotalVenta" value="{archivo.facturaTotalVenta}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalVentaNeta")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalVentaNeta" placeHolder ="{$.i18n.prop("factura.totalVentaNeta")}" id="facturaTotalVentaNeta" name="facturaTotalVentaNeta" value="{archivo.facturaTotalVentaNeta}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalImpuestos")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalImpuestos" placeHolder ="{$.i18n.prop("factura.totalImpuestos")}" id="facturaTotalImpuestos" name="facturaTotalImpuestos" value="{archivo.facturaTotalImpuestos}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalComprobante")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalComprobante" placeHolder ="{$.i18n.prop("factura.totalComprobante")}" id="facturaTotalComprobante" name="facturaTotalComprobante" value="{archivo.facturaTotalComprobante}">
                            </div>
                        </div>
                	</form>
                </div>
                <div class="box-footer">
                    <div class="row">
                        <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelar" name = "btnCancelar">
                                {$.i18n.prop("btn.volver")}
                            </button>
                        </div>
                    </div>    
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>

	<script>
	
		var self = this;
	    self.mostrarCargaArchivo  = true;
		self.mostrarFormulario    = false;	    
	    self.mostrarDetalle       = false;
		self.tipoCedulas	   	  = {data:[]} 
		self.mediosPago	   		  = {data:[]}
		self.condicionesVenta	  = {data:[]}
		self.tiposMensajes		  = {data:[]}
		
		self.archivo ={				
				emisorNombre:"",
				emisorCedula:"",
				emisorTipoCedula:"",
				emisorCorreo:"",
				emisorCodigoProvincia:"0",
				emisorProvincia:"",
				emisorCanton:"",
				emisorCodigoCanton:"",
				emisorDistrito:"",
				emisorCodigoDistrito:"",
				emisorCorreo:"",
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
		}
		
		self.recepcionFactura ={
				id:null,
				clave:"",
				cedulaEmisor:"",
				emisorTipoCedula:"",
				fechaEmision:"",
				mensaje:"0",
				detalleMensaje:"",
				totalImpuestos:"0",
				totalFactura:"0",
				cedulaReceptor:"",
				numeroConsecutivoReceptor:"",				
		}
		
		//Se cargan al montar el tag
		self.on('mount',function(){
			$('input[id=fileUpload]').change(function() {
				__cargarXML($(this).val());
			});
		    __listadoTipoCedulas();
		    __listadoMediosPago();
		    __listadoCondicionesVenta();
		    __listadoTiposMensajes();
		    
		});

		//Se actualiza la pagina
		self.update();

		
		//Rereso a la pantalla para cargar otro archivo
		__regresarAlListado(){
			self.mostrarCargaArchivo = true;
		    self.mostrarFormulario   = true;
		    self.mostrarDetalle      = false;
		    self.update();
		}

		__detalle(){
			self.mostrarCargaArchivo = false;
		    self.mostrarFormulario   = false;
		    self.mostrarDetalle      = true;
		    self.update();			
		}
		
		function __limpiarValores(){			
			self.archivo ={				
					emisorNombre:"",
					emisorCedula:"",
					emisorTipoCedula:"",
					emisorCorreo:"",
					emisorCodigoProvincia:"0",
					emisorProvincia:"",
					emisorCanton:"",
					emisorCodigoCanton:"",
					emisorDistrito:"",
					emisorCodigoDistrito:"",
					emisorCorreo:"",
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
			}
			
			self.recepcionFactura ={
					id:"0",
					clave:"",
					cedulaEmisor:"",
					emisorTipoCedula:"",
					fechaEmision:"",
					mensaje:"0",
					detalleMensaje:"",
					totalImpuestos:"0",
					totalFactura:"0",
					cedulaReceptor:"",
					numeroConsecutivoReceptor:"",				
			}
			
		}
		
		//Se muestra los tipos de cedulas	
		function __listadoTipoCedulas(){
		    self.tipoCedulas               = {data:[]}  // definir el data del datatable
		    self.update()
		    self.tipoCedulas.data.push({
		        valor:"01",
		        descripcion:$.i18n.prop("tipo.cedula.fisica")
		    })
		   self.tipoCedulas.data.push({
		        valor:"02",
		        descripcion:$.i18n.prop("tipo.cedula.juridica")
		    })
		   self.tipoCedulas.data.push({
		        valor:"03",
		        descripcion:$.i18n.prop("tipo.cedula.dimex")
		    })
		     self.tipoCedulas.data.push({
		        valor:"04",
		        descripcion:$.i18n.prop("tipo.cedula.nite")
		    })
		    self.update()
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
		function __listadoMediosPago(){
		    self.mediosPago = {data:[]}  // definir el data del datatable
		    self.update()
		    self.mediosPago.data.push({
		        valor:"01",
		        descripcion:$.i18n.prop("medio.pago.efectivo")
		    })
		    self.mediosPago.data.push({
		        valor:"02",
		        descripcion:$.i18n.prop("medio.pago.tarjeta")
		    })
		    self.mediosPago.data.push({
		        valor:"03",
		        descripcion:$.i18n.prop("medio.pago.cheque")
		    })
		    self.mediosPago.data.push({
		        valor:"04",
		        descripcion:$.i18n.prop("medio.pago.transferencia")
		    })
		    self.mediosPago.data.push({
		        valor:"05",
		        descripcion:$.i18n.prop("medio.pago.terceros")
		    })
		    self.mediosPago.data.push({
		        valor:"99",
		        descripcion:$.i18n.prop("medio.pago.otros")
		    })
		    self.update()
		}
		
		//Se muestra las condiciones de venta
		function __listadoCondicionesVenta(){
		    self.condicionesVenta = {data:[]}  // definir el data del datatable
		    self.update()
		    self.condicionesVenta.data.push({
		        valor:"01",
		        descripcion:$.i18n.prop("condicion.venta.contado")
		    })
		    self.condicionesVenta.data.push({
		        valor:"02",
		        descripcion:$.i18n.prop("condicion.venta.credito")
		    })
		    self.condicionesVenta.data.push({
		        valor:"03",
		        descripcion:$.i18n.prop("condicion.venta.consignacion")
		    })
		    self.condicionesVenta.data.push({
		        valor:"04",
		        descripcion:$.i18n.prop("condicion.venta.apartado")
		    })
		    self.condicionesVenta.data.push({
		        valor:"05",
		        descripcion:$.i18n.prop("condicion.venta.arendamiento.compra")
		    })
		    self.condicionesVenta.data.push({
		        valor:"06",
		        descripcion:$.i18n.prop("condicion.venta.arendamiento.financiero")
		    })
		    self.condicionesVenta.data.push({
		        valor:"99",
		        descripcion:$.i18n.prop("condicion.venta.otros")
		    })
		    self.update()
		}
		
		function __cargarXML(xml) {
			limpiar()
            self.archivo ={}
			self.update()
			//Se limpian los errores
			$(".errorServerSideJgrid").remove();

            //Se carga el xml en los campos
			//var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xml)$/;
            //alert($("#fileUpload").val().toLowerCase());
	        //if (regex.test($("#fileUpload").val().toLowerCase())) {
	        //} else {
	            //alert("El archivo seleccionado no es un XML valido");
	        //}
            if (typeof (FileReader) != "undefined") {
                var reader = new FileReader();
                reader.onload = function (e) {

                	//Se carga el XML
                	var xmlDoc = $.parseXML(e.target.result);

                	//Se cargan los datos para presentar el detalle de una factura
                	
                    //Se cargan los datos del emisor
                    var emisor = $(xmlDoc).find("Emisor");
                    self.archivo.emisorNombre = emisor.find("Nombre").text();
                    self.archivo.emisorCedula = emisor.find("Identificacion").find("Numero").text();
                    self.archivo.emisorTipoCedula = emisor.find("Identificacion").find("Tipo").text();
                    self.archivo.emisorCorreo = emisor.find("CorreoElectronico").text();
                    self.archivo.emisorCodigoProvincia = emisor.find("Ubicacion").find("Provincia").text();
                    self.archivo.emisorCodigoCanton = emisor.find("Ubicacion").find("Canton").text();
                    self.archivo.emisorCodigoDistrito = emisor.find("Ubicacion").find("Distrito").text();
                    self.archivo.emisorOtraSena = emisor.find("Ubicacion").find("OtrasSenas").text();

                   //Se cargan los datos del emisor
                    var receptor = $(xmlDoc).find("Receptor");
                    self.archivo.receptorNombre = receptor.find("Nombre").text();
                    self.archivo.receptorCedula = receptor.find("Identificacion").find("Numero").text();
                    self.archivo.receptorTipoCedula = receptor.find("Identificacion").find("Tipo").text();
                    self.archivo.receptorCorreo = receptor.find("CorreoElectronico").text();
                    self.archivo.receptorCodigoProvincia = receptor.find("Ubicacion").find("Provincia").text();
                    self.archivo.receptorCodigoCanton = receptor.find("Ubicacion").find("Canton").text();
                    self.archivo.receptorCodigoDistrito = receptor.find("Ubicacion").find("Distrito").text();
                    self.archivo.receptorOtraSena = receptor.find("Ubicacion").find("OtrasSenas").text();
                    self.archivo.receptorTelefono = receptor.find("Telefono").find("NumTelefono").text();
                    self.archivo.receptorNombreComercial = receptor.find("NombreComercial").text();
                      
                    //Se cargan los datos de la factura
                    self.archivo.facturaConsecutivo = $(xmlDoc).find("NumeroConsecutivo").text();
                    self.archivo.facturaClave = $(xmlDoc).find("Clave").text();
                    self.archivo.facturaFechaEmision = $(xmlDoc).find("FechaEmision").text();
                    self.archivo.facturaCondicionVenta = $(xmlDoc).find("CondicionVenta").text();
                    self.archivo.facturaMedioPago = $(xmlDoc).find("MedioPago").text();
                    
                    //Se carga el detalle de la factura
					$("#detalleFactura").find("tr:gt(0)").remove();
                    var detalles = $(xmlDoc).find("DetalleServicio");
                    $(detalles).each(function () {
                    	$(this).children().each(function () {
		                    var row = "<tr>" + 
			                    		  "<td>" + $(this).find("Cantidad").text() + "</td>" + 
			                    		  "<td>" + $(this).find("UnidadMedida").text() + "</td>" + 
			                    		  "<td>" + $(this).find("Detalle").text() + "</td>" + 
			                    		  "<td>" + $(this).find("PrecioUnitario").text() + "</td>" + 
			                    		  "<td>" + $(this).find("MontoTotal").text() + "</td>" + 
			                    		  "<td>" + $(this).find("SubTotal").text() + "</td>" + 
			                    		  "<td>" + $(this).find("MontoTotalLinea").text() + "</td>" +
		                    		  "</tr>";
		      	            $('#detalleFactura tr:last').after(row);
                        });
                    });
                    
                    //Se carga el resumen de la factura
                    var resumenFactura = $(xmlDoc).find("ResumenFactura");
                    self.archivo.facturaCodigoMoneda = resumenFactura.find("CodigoMoneda").text();
                    self.archivo.facturaTipoCambio = resumenFactura.find("TipoCambio").text();
                    self.archivo.facturaTotalServExentos = resumenFactura.find("TotalServExentos").text();
                    self.archivo.facturaTotalExento = resumenFactura.find("TotalExento").text();
                    self.archivo.facturaTotalVenta = resumenFactura.find("TotalVenta").text();
                    self.archivo.facturaTotalVentaNeta = resumenFactura.find("TotalVentaNeta").text();
                    self.archivo.facturaTotalComprobante = resumenFactura.find("TotalComprobante").text();
                    self.archivo.facturaTotalImpuestos = resumenFactura.find("TotalImpuesto").text();
                    self.update();

                    
                    __buscaProvincias();
                    __buscaCantones();
                    __buscaDistritos();
                    
                    
                	//Se cargan los datos principales
                    self.recepcionFactura.clave = $(xmlDoc).find("Clave").text();
                	self.recepcionFactura.cedulaEmisor=emisor.find("Identificacion").find("Numero").text();
                	self.recepcionFactura.emisorTipoCedula = emisor.find("Identificacion").find("Tipo").text();
                    self.recepcionFactura.fechaEmision = $(xmlDoc).find("FechaEmision").text();
                    self.recepcionFactura.totalImpuestos = $(xmlDoc).find("TotalImpuesto").text();
                    self.recepcionFactura.totalFactura = resumenFactura.find("TotalComprobante").text();
                    self.recepcionFactura.cedulaReceptor = receptor.find("Identificacion").find("Numero").text();
	                self.recepcionFactura.totalImpuestos = $(xmlDoc).find("TotalImpuesto").text();
                }
                
                reader.readAsText($("#fileUpload")[0].files[0]);

                //Se muestra la pantalla
    		    self.mostrarFormulario     = true;
    		    self.mostrarCargaArchivo   = true;
    		    self.update();

            } else {
                alert("Se presento un problema al intentar cargar el archivo, su browser no soporta HTML 5");
            }
		}

		function limpiar(){
			self.archivo ={				
				emisorNombre:"",
				emisorCedula:"",
				emisorTipoCedula:"",
				emisorCorreo:"",
				emisorCodigoProvincia:"0",
				emisorProvincia:"",
				emisorCanton:"",
				emisorCodigoCanton:"",
				emisorDistrito:"",
				emisorCodigoDistrito:"",
				emisorCorreo:"",
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
			}
		
			self.recepcionFactura ={
				id:null,
				clave:"",
				cedulaEmisor:"",
				emisorTipoCedula:"",
				fechaEmision:"",
				mensaje:"0",
				detalleMensaje:"",
				totalImpuestos:"0",
				totalFactura:"0",
				cedulaReceptor:"",
				numeroConsecutivoReceptor:"",				
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
			
		    var formulario = $("#formularioAceptarForm").serialize();
		    $.ajax({
		        type : "POST",
		        dataType : "json",
		        data : formulario,
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
		    		    self.mostrarCargaArchivo   = true;		    		    
		    		    $("#fileUpload").val("");		    		    
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
</recepcion-factura>