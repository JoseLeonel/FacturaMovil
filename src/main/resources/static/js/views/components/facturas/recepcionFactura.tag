<recepcion-factura>
 	<!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-right"></div>
    </div>
    <div id="formularioMensajeArchivo" class="row center-block" show={mostrarCargaArchivoMensaje}>    	
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {$.i18n.prop("titulo.aceptar.factura")}</h1>
                </div>
                <div class="box-body">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                    
				</div>
				<div class="box-body" >
                    <form id="formulario" name="formulario" class="advanced-search-form">
                        <div class="row">
                             <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> {$.i18n.prop("archivo.factura")}  <span class="requeridoDato">*</span></label>
					     		<input type="file" id="fileUpload" class="form-control fileUpload"/>
                            </div>
							<div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" show={mostrarFormulario ==true}>
								<button onclick={__crearFactura}   type="button" class="btn-green btn-add pull-left" id="btnAceptar" name ="btnAceptar">
									{$.i18n.prop("btn.aceptar")}
								</button>
							</div>

                        </div>
						<br>
						<div class="row">
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
					</form>
				</div>					
								
			</div>
		</div>						
	</div>          
	
	<div id="formularioAceptar" class="row center-block" show={mostrarFormulario} >
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-body">
                    <form id="formularioAceptarForm" name="formularioAceptarForm" class="advanced-search-form">
				        <input type="hidden" name="emisorTipoCedula" id="emisorTipoCedula" value="{recepcionFactura.emisorTipoCedula}">
                        <div class="row">                        
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("emisor.cedula")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorCedula" placeHolder ="{$.i18n.prop("emisor.cedula")}" id="emisorCedula" name="emisorCedula" value="{recepcionFactura.emisorCedula}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("emisor.nombre")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control "  value="{recepcionFactura.emisorNombre}">
                            </div>

                            <div class= "col-md-5 col-sx-12 col-sm-5 col-lg-5">
                                <label> {$.i18n.prop("factura.clave")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaClave" placeHolder ="{$.i18n.prop("factura.clave")}" id="facturaClave" name="facturaClave" value="{recepcionFactura.facturaClave}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("emisor.correoElectronico")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorCorreoElectronico" placeHolder ="{$.i18n.prop("emisor.correoElectronico")}" id="emisorCorreo" name="emisorCorreo" value="{recepcionFactura.emisorCorreo}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("emisor.telefono")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorTelefono" placeHolder ="{$.i18n.prop("emisor.telefono")}" id="emisorTelefono" name="emisorTelefono" value="{recepcionFactura.emisorTelefono}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.fechaEmision")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaFechaEmision" placeHolder ="{$.i18n.prop("factura.fechaEmision")}" id="facturaFechaEmision" name="facturaFechaEmision" value="{recepcionFactura.facturaFechaEmision}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalImpuestos")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalImpuestos" placeHolder ="{$.i18n.prop("factura.totalImpuestos")}" id="facturaTotalImpuestos" name="facturaTotalImpuestos" value="{recepcionFactura.facturaTotalImpuestos}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalComprobante")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalComprobante" placeHolder ="{$.i18n.prop("factura.totalComprobante")}" id="facturaTotalComprobante" name="facturaTotalComprobante" value="{recepcionFactura.facturaTotalComprobante}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.tipoCambio")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTipoCambio" placeHolder ="{$.i18n.prop("factura.tipoCambio")}" id="facturaTipoCambio" name="facturaTipoCambio" value="{recepcionFactura.facturaTipoCambio}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.codigoMoneda")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaCodigoMoneda" placeHolder ="{$.i18n.prop("factura.codigoMoneda")}" id="facturaCodigoMoneda" name="facturaCodigoMoneda" value="{recepcionFactura.facturaCodigoMoneda}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("receptor.cedula")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control receptorCedula" placeHolder ="{$.i18n.prop("receptor.cedula")}" id="receptorCedula" name="receptorCedula" value="{recepcionFactura.receptorCedula}">
                            </div>                                                        
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label> {$.i18n.prop("receptor.tipoCondicionImpuesto")}  <span class="requeridoDato">*</span></label>
                                <select class="form-control condicionImpuesto" id="condicionImpuesto" name="condicionImpuesto" >
                                    <option each={tiposCondiciones.data}  value="{valor}" selected="{recepcionFactura.condicionImpuesto==valor?true:false}">{descripcion}</option>
                                </select>
                            </div>                            
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label> {$.i18n.prop("receptor.codigo.actividad")}  <span class="requeridoDato">*</span></label>
                                <select class="form-control codigoActividad" id="codigoActividad" name="codigoActividad" >
                                    <option each={empresaActividadComercial}  value="{codigo}" >{codigo}-{descripcion}</option>
                                </select>
                            </div>                            
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label> {$.i18n.prop("receptor.mensaje")}  <span class="requeridoDato">*</span></label>
                                <select class="form-control mensaje" id="mensaje" name="mensaje" >
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
	                   
	                 </div>    
	            </div>    
			</div>
		</div>						
	</div>	
	<div id="formularioDetalle" class="row center" show={mostrarDetalle} >
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
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
                                <label> {$.i18n.prop("emisor.nombreComercial")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorNombreComercial" placeHolder ="{$.i18n.prop("emisor.nombreComercial")}" id="emisorNombreComercial" name="emisorNombreComercial" value="{archivo.emisorNombreComercial}">
                            </div>
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label> {$.i18n.prop("emisor.correoElectronico")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorCorreo" id="emisorCorreo" name="emisorCorreo" placeHolder="{$.i18n.prop("emisor.correoElectronico")}" value="{archivo.emisorCorreo}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("emisor.telefono")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorTelefono" id="emisorTelefono" name="emisorTelefono" placeHolder="{$.i18n.prop("emisor.telefono")}" value="{archivo.emisorTelefono}">
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
                                <label> {$.i18n.prop("factura.codigoActividad")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaCodigoActividad" placeHolder ="{$.i18n.prop("factura.codigoActividad")}" id="facturaCodigoActividad" name="facturaCodigoActividad" value="{archivo.facturaCodigoActividad}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.plazoCredito")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaPlazoCredito" placeHolder ="{$.i18n.prop("factura.plazoCredito")}" id="facturaPlazoCredito" name="facturaPlazoCredito" value="{archivo.facturaPlazoCredito}">
                            </div>
                            
                            
                            
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.facturaTotalServGravados")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalServGravados" placeHolder ="{$.i18n.prop("factura.facturaTotalServGravados")}" id="facturaTotalServGravados" name="facturaTotalServGravados" value="{archivo.facturaTotalServGravados}">
                            </div>                            
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalServExentos")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalServExentos" placeHolder ="{$.i18n.prop("factura.totalServExentos")}" id="facturaTotalServExentos" name="facturaTotalServExentos" value="{archivo.facturaTotalServExentos}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.facturaTotalServExonerado")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalServExonerado" placeHolder ="{$.i18n.prop("factura.facturaTotalServExonerado")}" id="facturaTotalServExonerado" name="facturaTotalServExonerado" value="{archivo.facturaTotalServGravados}">
                            </div>                            
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.facturaTotalMercanciasGravadas")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalMercanciasGravadas" placeHolder ="{$.i18n.prop("factura.facturaTotalMercanciasGravadas")}" id="facturaTotalMercanciasGravadas" name="facturaTotalMercanciasGravadas" value="{archivo.facturaTotalMercanciasGravadas}">
                            </div>                            
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.facturaTotalMercanciasExentas")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalMercanciasExentas" placeHolder ="{$.i18n.prop("factura.facturaTotalMercanciasExentas")}" id="facturaTotalMercanciasExentas" name="facturaTotalMercanciasExentas" value="{archivo.facturaTotalMercanciasExentas}">
                            </div>                            
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.facturaTotalMercExonerada")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalMercExonerada" placeHolder ="{$.i18n.prop("factura.facturaTotalMercExonerada")}" id="facturaTotalMercExonerada" name="facturaTotalMercExonerada" value="{archivo.facturaTotalMercExonerada}">
                            </div>                            

                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.facturaTotalGravado")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalGravado" placeHolder ="{$.i18n.prop("factura.facturaTotalGravado")}" id="facturaTotalGravado" name="facturaTotalGravado" value="{archivo.facturaTotalGravado}">
                            </div>                            
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.facturaTotalExento")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalExento" placeHolder ="{$.i18n.prop("factura.facturaTotalExento")}" id="facturaTotalExento" name="facturaTotalExento" value="{archivo.facturaTotalExento}">
                            </div>                            
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.facturaTotalExonerado")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalExonerado" placeHolder ="{$.i18n.prop("factura.facturaTotalExonerado")}" id="facturaTotalExonerado" name="facturaTotalExonerado" value="{archivo.facturaTotalExonerado}">
                            </div>                                                        
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalVenta")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalVenta" placeHolder ="{$.i18n.prop("factura.totalVenta")}" id="facturaTotalVenta" name="facturaTotalVenta" value="{archivo.facturaTotalVenta}">
                            </div>                            
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("factura.totalDescuentos")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaTotalDescuentos" placeHolder ="{$.i18n.prop("factura.totalDescuentos")}" id="facturaTotalDescuentos" name="facturaTotalDescuentos" value="{archivo.facturaTotalDescuentos}">
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
	    self.mostrarCargaArchivoMensaje = true;
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
			$('input[id=fileUpload]').change(function() {
				__cargarXML($(this).val());
			});
			
			
			
		    __listadoTipoCedulas();
		    __listadoMediosPago();
		    __listadoCondicionImpuesto();
		    __listadoCondicionesVenta();
		    __listadoTiposMensajes();
			__listadoTiposGasto();
		    __ListaActividadesComercales();
		    
		});

		//Se actualiza la pagina
		self.update();

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
		
		function __cargarXML(xml) {
			limpiar()
            self.archivo ={}
			self.update()
			//Se limpian los errores
			$(".errorServerSideJgrid").remove();

           
	        if (typeof (FileReader) != "undefined") {
                var reader = new FileReader();
                reader.onload = function (e) {

                	//Se carga el XML
                	var xmlDoc = $.parseXML(e.target.result);
                	
                	//Se cargan los datos para presentar el detalle de una factura

                
						console.log($(xmlDoc).find("Clave").first().text());
						console.log(self.mensaje.facturaClave);
						
							//Se cargan los datos del emisor
							var emisor = $(xmlDoc).find("Emisor");
		                    self.archivo.emisorNombre = emisor.find("Nombre").text();
		                    self.archivo.emisorCedula = emisor.find("Identificacion").find("Numero").text();
		                    self.archivo.emisorTipoCedula = emisor.find("Identificacion").find("Tipo").text();
		                    self.archivo.emisorCorreo = emisor.find("CorreoElectronico").text();
		                    self.archivo.emisorTelefono = emisor.find("Telefono").find("NumTelefono").text();
		                    self.archivo.emisorCodigoProvincia = emisor.find("Ubicacion").find("Provincia").text();
		                    self.archivo.emisorCodigoCanton = emisor.find("Ubicacion").find("Canton").text();
		                    self.archivo.emisorCodigoDistrito = emisor.find("Ubicacion").find("Distrito").text();
		                    self.archivo.emisorOtraSena = emisor.find("Ubicacion").find("OtrasSenas").text();
		                    self.archivo.emisorNombreComercial = emisor.find("NombreComercial").text();

		                   //Se cargan los datos del emisor
		                    var receptor = $(xmlDoc).find("Receptor");
		                    self.archivo.receptorNombre = __valorString(receptor.find("Nombre").text());
		                    self.archivo.receptorCedula = __valorString(receptor.find("Identificacion").find("Numero").text());
		                    self.archivo.receptorTipoCedula = __valorString(receptor.find("Identificacion").find("Tipo").text());
		                    self.archivo.receptorCorreo = __valorString(receptor.find("CorreoElectronico").text());
		                    self.archivo.receptorCodigoProvincia = __valorString(receptor.find("Ubicacion").find("Provincia").text());
		                    self.archivo.receptorCodigoCanton = __valorString(receptor.find("Ubicacion").find("Canton").text());
		                    self.archivo.receptorCodigoDistrito = __valorString(receptor.find("Ubicacion").find("Distrito").text());
		                    self.archivo.receptorOtraSena = __valorString(receptor.find("Ubicacion").find("OtrasSenas").text());
		                    self.archivo.receptorTelefono = __valorString(receptor.find("Telefono").find("NumTelefono").text());
		                    self.archivo.receptorNombreComercial = __valorString(receptor.find("NombreComercial").text());
		                      
		                    //Se cargan los datos de la factura
		                    self.archivo.facturaConsecutivo = __valorString($(xmlDoc).find("NumeroConsecutivo").first().text());
		                    self.archivo.facturaClave = __valorString($(xmlDoc).find("Clave").first().text());
		                    self.archivo.facturaFechaEmision = __valorString(($(xmlDoc).find("FechaEmision")).first().text());
		                    self.archivo.facturaCondicionVenta = __valorString($(xmlDoc).find("CondicionVenta").first().text());
		                    self.archivo.facturaMedioPago = __valorString($(xmlDoc).find("MedioPago").first().text());

		                    self.archivo.facturaCodigoActividad = __valorString($(xmlDoc).find("CodigoActividad").first().text());
		                    self.archivo.facturaPlazoCredito = __valorString($(xmlDoc).find("PlazoCredito").first().text());
							
		                    
		                    //Se carga el detalle de la factura
							$("#detalleFactura").find("tr:gt(0)").remove();
						
		                    var detallesServicioXml = $(xmlDoc).find("DetalleServicio");
		                    $(detallesServicioXml).each(function () {
								var valor = __valorString($(this).find("CodigoComercial").find("Codigo").text())
		                    	$(this).children().each(function () {
									var impuestosItems    = this.getElementsByTagName("Impuesto");
									var impuestos ={
										    codigo1 :'',
											codigoTarifa1:'',
											tarifa1:'',
											monto1:0,
										    codigo2 :'',
											codigoTarifa2:'',
											tarifa2:'',
											monto2:0
					
										}
									$.each(impuestosItems, function(i, impuesto){
									    var codigo = ''
										var codigoTarifa = ''
										var tarifa = 0 
										var monto = 0
										$(this).children().each(function () {
											var name = $(this).get(0).nodeName               
											if(name.indexOf('Codigo') != -1){
												codigo = $(this).text()
											} 
											if(name.indexOf('CodigoTarifa') != -1){
												codigoTarifa = $(this).text()
											} 
											if(name.indexOf('Tarifa') != -1){
												tarifa = $(this).text()
											} 
											if(name.indexOf('Monto') != -1){
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
										}       
									    
									});

				                    var row = "<tr>" + 
												  "<td>" + __valorFloat($(this).find("Cantidad").text()) + "</td>" + 
					                    		  "<td>" + __valorString($(this).find("UnidadMedida").text()) + "</td>" + 
					                    		  "<td>" + __valorString($(this).find("Detalle").text()) + "</td>" + 
					                    		  "<td>" + __valorFloat($(this).find("PrecioUnitario").text()) + "</td>" + 
					                    		  "<td>" + __valorFloat($(this).find("MontoTotal").text()) + "</td>" + 
					                    		  "<td>" + __valorFloat($(this).find("SubTotal").text()) + "</td>" + 
					                    		  "<td>" + __valorFloat($(this).find("MontoTotalLinea").text()) + "</td>" +
				                    		  "</tr>";
				      	            $('#detalleFactura tr:last').after(row);
				      	            
				      	          	self.detalleServicio.data.push({
										detalleImpuestos : impuestos,	
					      	            numeroLinea     : 0,
					      	            cantidad        : __valorFloat($(this).find("Cantidad").text()),
					      	            unidadMedida    : __valorString($(this).find("UnidadMedida").text()),
					      	            detalle         : __valorString($(this).find("Detalle").text()),
					      	            precioUnitario  : __valorFloat($(this).find("PrecioUnitario").text()),
					      	            montoTotal      : __valorFloat($(this).find("MontoTotal").text()),
					      	            subTotal        : __valorFloat($(this).find("SubTotal").text()),
					      	            montoTotalLinea : __valorFloat($(this).find("MontoTotalLinea").text()),
					      	            impuestoNeto    : __valorFloat($(this).find("ImpuestoNeto").text()),
					      	            baseImponible   : __valorFloat($(this).find("BaseImponible").text()),
					      	            codigoComercialTipo   : "",
					      	            codigoComercialCodigo : "",
					      	            descuentoMonto        : __valorFloat($(this).find("Descuento").find("MontoDescuento").text()),
					      	            descuentoNaturaleza   : __valorString($(this).find("Descuento").find("NaturalezaDescuento").text()),
					      	            impuestoCodigo        : __valorString(impuestos.codigo1),
					      	            impuestoCodigoTarifa  : __valorString(impuestos.codigoTarifa1),
					      	            impuestoTarifa        : __valorFloat(impuestos.tarifa1),
     				      	            impuestoMonto         : __valorFloat(impuestos.monto1),
					      	            impuestoCodigo1        : __valorString(impuestos.codigo2),
					      	            impuestoCodigoTarifa1  : __valorString(impuestos.codigoTarifa2),
					      	            impuestoTarifa1        : __valorFloat(impuestos.tarifa2),
     				      	            impuestoMonto1         : __valorFloat(impuestos.monto2),
					      	            impuestoExoneracionTipoDocumento         : __valorString($(this).find("Impuesto").find("Exoneracion").find("TipoDocumento").text()),
					      	            impuestoExoneracionNumeroDocumento       : __valorString($(this).find("Impuesto").find("Exoneracion").find("NumeroDocumento").text()),
					      	            impuestoExoneracionNombreInstitucion     : __valorString($(this).find("Impuesto").find("Exoneracion").find("NombreInstitucion").text()),
					      	            impuestoExoneracionFechaEmision          : __valorString($(this).find("Impuesto").find("Exoneracion").find("FechaEmision").text()),
					      	            impuestoExoneracionPorcentaje            : __valorFloat($(this).find("Impuesto").find("Exoneracion").find("PorcentajeExoneracion").text()),
					      	            impuestoExoneracionMonto                 : __valorFloat($(this).find("Impuesto").find("Exoneracion").find("MontoExoneracion").text()),
					      	        });	       	            
		                        });
		                    });

		                    //Se carga el resumen de la factura
		                    var resumenFactura = $(xmlDoc).find("ResumenFactura");
		                    self.archivo.facturaCodigoMoneda = __valorString(resumenFactura.find("CodigoTipoMoneda").find("CodigoMoneda").text());
							self.archivo.facturaTipoCambio = __valorFloat(resumenFactura.find("CodigoTipoMoneda").find("TipoCambio").text());
							if(self.archivo.facturaCodigoMoneda != 'CRC' && self.archivo.facturaCodigoMoneda != 'USD' ){
		                       self.archivo.facturaCodigoMoneda = __valorString(resumenFactura.find("CodigoMoneda").text());
							   self.archivo.facturaTipoCambio = __valorFloat(resumenFactura.find("TipoCambio").text());

							}
		                    
		                    self.archivo.facturaTotalServExentos = __valorFloat(resumenFactura.find("TotalServExentos").text());
		                    self.archivo.facturaTotalExento = __valorFloat(resumenFactura.find("TotalExento").text());
		                    self.archivo.facturaTotalVenta = __valorFloat(resumenFactura.find("TotalVenta").text());
		                    self.archivo.facturaTotalVentaNeta = __valorFloat(resumenFactura.find("TotalVentaNeta").text());
		                    self.archivo.facturaTotalComprobante = __valorFloat(resumenFactura.find("TotalComprobante").text());
		                    self.archivo.facturaTotalImpuestos = __valorFloat(resumenFactura.find("TotalImpuesto").text());

		                    self.archivo.facturaTotalServGravados = __valorFloat(resumenFactura.find("TotalServGravados").text());
		                    self.archivo.facturaTotalServExonerado = __valorFloat(resumenFactura.find("TotalServExonerado").text());
		                    self.archivo.facturaTotalMercanciasGravadas = __valorFloat(resumenFactura.find("TotalMercanciasGravadas").text());
		                    self.archivo.facturaTotalMercanciasExentas = __valorFloat(resumenFactura.find("TotalMercanciasExentas").text());
		                    self.archivo.facturaTotalMercExonerada =__valorFloat(resumenFactura.find("TotalMercExonerada").text());
		                    self.archivo.facturaTotalGravado = __valorFloat(resumenFactura.find("TotalGravado").text());
		                    self.archivo.facturaTotalExonerado = __valorFloat(resumenFactura.find("TotalExonerado").text());
		                    self.archivo.facturaTotalIVADevuelto = __valorFloat(resumenFactura.find("IVADevuelto").text());
		                    self.archivo.facturaTotalOtrosCargos = __valorFloat(resumenFactura.find("TotalOtrosCargos").text());	                    
		                    self.archivo.facturaTotalDescuentos = __valorFloat(resumenFactura.find("TotalDescuentos").text());	                    

		                    self.update();

		                    
		                    __buscaProvincias();
		                    __buscaCantones();
		                    __buscaDistritos();
		                                        
		                	//Se cargan los datos del objecto a almacenar en base de datos
							self.recepcionFactura.tipoGasto = 1
							self.recepcionFactura.condicionImpuesto = $("#condicionImpuesto").val()
							self.recepcionFactura.codigoActividad = $("#codigoActividad").val()
		                	self.recepcionFactura.emisorNombre = self.archivo.emisorNombre;
		                	self.recepcionFactura.emisorCedula =  self.archivo.emisorCedula;
		                	self.recepcionFactura.emisorTipoCedula = self.archivo.emisorTipoCedula;
		                	self.recepcionFactura.emisorCorreo = self.archivo.emisorCorreo;
		                	self.emisorTelefono = self.archivo.emisorTelefono;
		                	self.recepcionFactura.emisorCodigoProvincia = self.archivo.emisorCodigoProvincia;
		                	self.recepcionFactura.emisorProvincia = self.archivo.emisorProvincia;
		                	self.recepcionFactura.emisorCanton = self.archivo.emisorCanton;
		                	self.recepcionFactura.emisorCodigoCanton = self.archivo.emisorCodigoCanton;
		                	self.recepcionFactura.emisorDistrito = self.archivo.emisorDistrito;
		                	self.recepcionFactura.emisorCodigoDistrito = self.archivo.emisorCodigoDistrito;
		                	self.recepcionFactura.emisorOtraSena = self.archivo.emisorOtraSena;
		                	self.recepcionFactura.receptorNombre = self.archivo.receptorNombre;
		                	self.recepcionFactura.receptorCedula = self.archivo.receptorCedula;
		                	self.recepcionFactura.receptorTipoCedula = self.archivo.receptorTipoCedula;
		                	self.recepcionFactura.receptorCorreo = self.archivo.receptorCorreo;
		                	self.recepcionFactura.receptorProvincia = self.archivo.receptorProvincia;
		                	self.recepcionFactura.receptorCodigoProvincia = self.archivo.receptorCodigoProvincia;
		                	self.recepcionFactura.receptorCanton = self.archivo.receptorCanton;
		                	self.recepcionFactura.receptorCodigoCanton = self.archivo.receptorCodigoCanton;
		                	self.recepcionFactura.receptorDistrito = self.archivo.receptorDistrito;
		                	self.recepcionFactura.receptorCodigoDistrito = self.archivo.receptorCodigoDistrito;
		                	self.recepcionFactura.receptorOtraSena = self.archivo.receptorOtraSena;
		                	self.recepcionFactura.receptorTelefono = self.archivo.receptorTelefono;
		                	self.recepcionFactura.receptorNombreComercial = self.archivo.receptorNombreComercial;
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
		                	
		    	  		    self.mostrarFormulario     = true;
		    	   		    self.mostrarCargaArchivo   = true;
		    	   		 	self.mostrarCargaArchivoMensaje = true;														
						
					
                }

                reader.readAsText($("#fileUpload")[0].files[0]);
                
                //Se muestra la pantalla
	   		    self.update();					

            } else {
	   		 	sweetAlert("", "Se presento un problema al intentar cargar el archivo, su browser no soporta HTML 5", "error");
            }
		}
		
		function __cargarXMLMensaje(xml) {
	        if (typeof (FileReader) != "undefined") {
                var reader = new FileReader();
                reader.onload = function (e) {
                	//Se carga el XML
                	var xmlDoc = $.parseXML(e.target.result);
                	//Se verifica que el XML no sea el del mensaje de hacienda enviado al correo
					if($(xmlDoc).find("MensajeHacienda").length <= 0){
	    	  		    self.mostrarFormulario     = false;
	    	  		    self.mostrarCargaArchivo   = false;
	    	  		    self.mostrarCargaArchivoMensaje = true;
	    	   		    self.update();					
	    	   		 	sweetAlert("", "Favor de revisar, debe cargar el XML donde se confirma la aceptacion de su factura, favor cargue el correcto", "error");
					}else{
	                	//Se cargan los datos para presentar el detalle de una factura
	    	   			self.mensaje.facturaClave = $(xmlDoc).find("Clave").first().text();
	    	   			self.mensaje.mensaje      = $(xmlDoc).find("Mensaje").first().text();
					    if(self.mensaje.mensaje != "1"){
		    	  		    self.mostrarFormulario     = false;
		    	  		    self.mostrarCargaArchivo   = false;
		    	  		    self.mostrarCargaArchivoMensaje = true;
		    	   		    self.update();					
		    	   		 	sweetAlert("", "Favor de revisar el mensaje aun no fue aceptado por hacienda", "error");
					    }else{
		    	   			self.mostrarFormulario          = false;
		    	   		 	self.mostrarCargaArchivoMensaje = true;
		    	   		    self.mostrarCargaArchivo        = true;
		    	   		    self.update();
					    }	   			
					}
                }                
                reader.readAsText($("#fileUploadMensajeArchivo")[0].files[0]);                
	   		 	reader.readAsText($("#fileUpload")[0].files[0]);
                
                //Se muestra la pantalla
	   		    self.update();					

            } else {
	   		 	sweetAlert("", "Se presento un problema al intentar cargar el archivo, su browser no soporta HTML 5", "error");
            }
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
			self.recepcionFactura.tipoGasto = 1
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
</recepcion-factura>