<recepcion-factura>


   <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-comment-o"></i>&nbsp {$.i18n.prop("nota.aceptacion.compras")}  </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>          
	<div id="formularioCargaArchivo" class="row center-block" show={mostrarCargaArchivo}>
    	
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
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
                             <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> {$.i18n.prop("archivo.factura")}  <span class="requeridoDato">*</span></label>
					     		<input type="file" id="fileUpload" class="form-control fileUpload"/>
                            </div>
                        </div>
						<br>
						<div class="row">
							<div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
							    <section> 
								    <a class="pull-left" href="#"    onclick = {__MostrarGuia} title="Guia para aceptar compra de un proveedor"> <span style="color:red;font-weight:bold"><u>Guia de aceptacion de compras</u></span></a><br>
									<div show="{verAyuda ==true}">
							     	<h1>Como aceptar la compra electronica: </h1>
									 <p>1. Le llegará un correo electronico con tres archivos.</p>
									 <p>2. Descargue el documento cuyo nombre es “XML”.</p>
									 <p>3. Clic Seleccionar Archivo </p>
									 <p>4. Si todo esta bien presionar aceptar compra  </p>
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
    	<div class="col-md-12 col-sx-12 col-lg-12 col-sm-12"></div>
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-body">
                    <form id="formularioAceptarForm" name="formularioAceptarForm" class="advanced-search-form">
				        <input type="hidden" name="emisorTipoCedula" id="emisorTipoCedula" value="{recepcionFactura.emisorTipoCedula}">
						 
                        <div class="row"> 
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("emisor.codigoActividad")} <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorCodigoActividad" id="emisorCodigoActividad" name="emisorCodigoActividad" placeHolder="{$.i18n.prop("emisor.codigoActividad")}" value="{archivo.emisorCodigoActividad}">
                            </div>
                       
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> {$.i18n.prop("factura.clave")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control facturaClave" placeHolder ="{$.i18n.prop("factura.clave")}" id="facturaClave" name="facturaClave" value="{recepcionFactura.facturaClave}">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label> {$.i18n.prop("emisor.cedula")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control emisorCedula" placeHolder ="{$.i18n.prop("emisor.cedula")}" id="emisorCedula" name="emisorCedula" value="{recepcionFactura.emisorCedula}">
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
                                <label> {$.i18n.prop("receptor.mensaje")}  <span class="requeridoDato">*</span></label>
                                <select class="form-control receptorMensaje" id="mensaje" name="mensaje" >
                                    <option each={tiposMensajes.data}  value="{valor}" selected="{recepcionFactura.mensaje==valor?true:false}">{descripcion}</option>
                                </select>
                            </div>                  
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label> {$.i18n.prop("aceptarCompras.condicion.impuesto")}  <span class="requeridoDato">*</span></label>
                                <select class="form-control condicionImpuesto" id="condicionImpuesto" name="condicionImpuesto" >
                                    <option each={condicionImpuestos.data}  value="{valor}" >{descripcion}</option>
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
    	<div class="col-md-12 col-sx-12 col-lg-12 col-sm-12"></div>
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {$.i18n.prop("titulo.aceptar.factura")}</h1>
                </div>
                <div class="box-body">
                    <form id = "formularioDetalle" name="formularioDetalle" class="advanced-search-form">
						<ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-itemEmisor active" onclick={emisorPantallaClick} >
                                <a class="nav-link "  data-toggle="tab" href="#itemEmisor" role="tab" aria-controls="itemEmisor"
                                    aria-selected="false"><h3> <p class="text-primary">{$.i18n.prop("titulo.datos.emisor")}</p></h3></a>
                                </li>
                                <li class="nav-itemReceptor" onclick={receptorPantallaClick}  >
                                    <a class="nav-link" id="temReceptor"  href="#itemReceptor" role="tab" aria-controls="itemReceptor"
                                    aria-selected="false"><h3> <p class="text-primary">{$.i18n.prop("titulo.datos.receptor")}</p></h3></a>
                                </li>
                                <li class="nav-itemDetalleCompra" onclick={detalleCompraPantallaClick} >
                                    <a class="nav-link" id="itemDetalleCompra" data-toggle="tab" href="#itemDetalleCompra" role="tab" aria-controls="itemDetalleCompra"
                                    aria-selected="false"><h3> <p class="text-primary">{$.i18n.prop("titulo.datos.factura")}</p></h3></a>
                                </li>
                        </ul>     					
                        <div class="tab-pane "  show={tabDatosEmisor == true}>   
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
						</div>
                        <div class="tab-pane "  show={tabDatosReceptor == true}>   
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
						</div>
						<div class="tab-pane "  show={tabDetalleCompra == true}> 
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
								<div class="col-sx-12 col-lg-12 col-md-12 col-sm-12 " style="width:100%; padding-top: 15px; overflow-x: scroll;overflow-y: scroll; height:100%;">
										<table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar"   cellspacing="0" width="100%">
											<thead id="detalleFactura">
												<tr>
													<th class="table-header" >{$.i18n.prop("compraAceptada.codigo")}</th>
													<th class="table-header" >{$.i18n.prop("factura.detalle")}</th>
													<th class="table-header" >{$.i18n.prop("factura.precioUnitario")}</th>
													<th class="table-header" >{$.i18n.prop("factura.cantidad")}</th>
													<th class="table-header" >{$.i18n.prop("factura.unidadMedida")}</th>
													<th class="table-header" >{$.i18n.prop("factura.montoTotal")}</th>
													<th class="table-header" >{$.i18n.prop("compraAceptada.descuento")}</th>
													<th class="table-header" >{$.i18n.prop("factura.subTotal")}</th>
													<th class="table-header" >{$.i18n.prop("compraAceptada.tarifa")}</th>
													<th class="table-header" >{$.i18n.prop("comprasAceptada.porcentaje.impuestos")}</th>
													<th class="table-header" >{$.i18n.prop("compraAceptada.ImpuestoNeto")}</th>
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
									<label> {$.i18n.prop("factura.facturaTipoDocumentoOtroCargo")}  <span class="requeridoDato">*</span></label>
									<input type="text" readonly="readonly" class="form-control facturaTipoDocumentoOtroCargo" placeHolder ="{$.i18n.prop("factura.facturaTipoDocumentoOtroCargo")}" id="facturaTipoDocumentoOtroCargo" name="facturaTipoDocumentoOtroCargo" value="{archivo.facturaTipoDocumentoOtroCargo}">
								</div>
								<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
									<label> {$.i18n.prop("factura.facturaDetalleOtroCargo")}  <span class="requeridoDato">*</span></label>
									<input type="text" readonly="readonly" class="form-control facturaDetalleOtroCargo" placeHolder ="{$.i18n.prop("factura.facturaDetalleOtroCargo")}" id="facturaDetalleOtroCargo" name="facturaDetalleOtroCargo" value="{archivo.facturaDetalleOtroCargo}">
								</div>
								<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
									<label> {$.i18n.prop("factura.facturaMontoCargoOtroCargo")}  <span class="requeridoDato">*</span></label>
									<input type="text" readonly="readonly" class="form-control facturaMontoCargoOtroCargo" placeHolder ="{$.i18n.prop("factura.facturaMontoCargoOtroCargo")}" id="facturaMontoCargoOtroCargo" name="facturaMontoCargoOtroCargo" value="{archivo.facturaMontoCargoOtroCargo}">
								</div>
	
								<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
									<label> {$.i18n.prop("factura.totalServExentos")}  <span class="requeridoDato">*</span></label>
									<input type="text" readonly="readonly" class="form-control facturaTotalServExentos" placeHolder ="{$.i18n.prop("factura.totalServExentos")}" id="facturaTotalServExentos" name="facturaTotalServExentos" value="{archivo.facturaTotalServExentos}">
								</div>
								<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
									<label> {$.i18n.prop("factura.facturaTotalServGravados")}  </label>
									<input type="text" readonly="readonly" class="form-control facturaTotalServGravados" placeHolder ="{$.i18n.prop("factura.facturaTotalServGravados")}" id="facturaTotalServGravados" name="facturaTotalServGravados" value="{archivo.facturaTotalServGravados}">
								</div>
								<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
									<label> {$.i18n.prop("factura.facturaTotalMercanciasGravadas")}  </label>
									<input type="text" readonly="readonly" class="form-control facturaTotalServGravados" placeHolder ="{$.i18n.prop("factura.facturaTotalMercanciasGravadas")}" id="facturaTotalMercanciasGravadas" name="facturaTotalMercanciasGravadas" value="{archivo.facturaTotalMercanciasGravadas}">
								</div>
								<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
									<label> {$.i18n.prop("factura.facturaTotalMercanciasExentas")}  </label>
									<input type="text" readonly="readonly" class="form-control facturaTotalMercanciasExentas" placeHolder ="{$.i18n.prop("factura.facturaTotalMercanciasExentas")}" id="facturaTotalMercanciasExentas" name="facturaTotalMercanciasExentas" value="{archivo.facturaTotalMercanciasExentas}">
								</div>
								<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
									<label> {$.i18n.prop("factura.facturaTotalMercExonerada")}  </label>
									<input type="text" readonly="readonly" class="form-control facturaTotalMercExonerada" placeHolder ="{$.i18n.prop("factura.facturaTotalMercExonerada")}" id="facturaTotalMercExonerada" name="facturaTotalMercExonerada" value="{archivo.facturaTotalMercExonerada}">
								</div>
								<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
									<label> {$.i18n.prop("factura.facturaTotalGravado")}  </label>
									<input type="text" readonly="readonly" class="form-control facturaTotalGravado" placeHolder ="{$.i18n.prop("factura.facturaTotalGravado")}" id="facturaTotalGravado" name="facturaTotalGravado" value="{archivo.facturaTotalGravado}">
								</div>
								<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
									<label> {$.i18n.prop("factura.facturaTotalDescuentos")}  </label>
									<input type="text" readonly="readonly" class="form-control facturaTotalDescuentos" placeHolder ="{$.i18n.prop("factura.facturaTotalDescuentos")}" id="facturaTotalDescuentos" name="facturaTotalDescuentos" value="{archivo.facturaTotalDescuentos}">
								</div>
								<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
									<label> {$.i18n.prop("factura.facturaTotalIVADevuelto")}  </label>
									<input type="text" readonly="readonly" class="form-control facturaTotalIVADevuelto" placeHolder ="{$.i18n.prop("factura.facturaTotalIVADevuelto")}" id="facturaTotalDescuentos" name="facturaTotalDescuentos" value="{archivo.facturaTotalIVADevuelto}">
								</div>
								<div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
									<label> {$.i18n.prop("factura.facturaTotalOtrosCargos")}  </label>
									<input type="text" readonly="readonly" class="form-control facturaTotalOtrosCargos" placeHolder ="{$.i18n.prop("factura.facturaTotalOtrosCargos")}" id="facturaTotalOtrosCargos" name="facturaTotalOtrosCargos" value="{archivo.facturaTotalOtrosCargos}">
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
		self.tabDatosEmisor = true
        self.tabDatosReceptor = false
        self.tabDetalleCompra= false
	    self.mostrarCargaArchivo  = true;
		self.mostrarFormulario    = false;	    
	    self.mostrarDetalle       = false;
		self.verAyuda             = false;
		self.tipoCedulas	   	  = {data:[]} 
		self.mediosPago	   		  = {data:[]}
		self.condicionesVenta	  = {data:[]}
		self.tiposMensajes		  = {data:[]}
		
		self.archivo ={		
			    emisorCodigoActividad:"",		
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
			__listadoCondiccionImpuesto()
		    
		});
		emisorPantallaClick(){
			self.tabDatosEmisor = true
			self.tabDatosReceptor = false
			self.tabDetalleCompra= false
			self.update()

		}
        receptorPantallaClick(){
			self.tabDatosEmisor = false
			self.tabDatosReceptor = true
			self.tabDetalleCompra= false
            self.update() 
		}
        detalleCompraPantallaClick(){
			self.tabDatosEmisor = false
			self.tabDatosReceptor = false
			self.tabDetalleCompra= true
            self.update()
		}

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
				    emisorCodigoActividad:"",			
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
					facturaTipoDocumentoOtroCargo:"",
					facturaDetalleOtroCargo:"",
					facturaMontoCargoOtroCargo:0,
					facturaTotalServGravados:0,
					facturaTotalMercanciasGravadas:0,
					facturaTotalMercanciasExentas:0,
					facturaTotalMercExonerada:0, 
					facturaTotalGravado:0, 
					facturaTotalExonerado:0, 
					facturaTotalDescuentos:0, 
					facturaTotalIVADevuelto:0, 
					facturaTotalOtrosCargos:0 

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
					facturaTipoDocumentoOtroCargo:"",
					facturaDetalleOtroCargo:"",
					facturaMontoCargoOtroCargo:0,
					facturaTotalServGravados:0,
					facturaTotalMercanciasGravadas:0,
					facturaTotalMercanciasExentas:0,
					facturaTotalMercExonerada:0, 
					facturaTotalGravado:0, 
					facturaTotalExonerado:0, 
					facturaTotalDescuentos:0, 
					facturaTotalIVADevuelto:0, 
					facturaTotalOtrosCargos:0 

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
		
		
        //Se muestra los tipos medio de aceptacion	
		function __listadoCondiccionImpuesto(){
		    self.condicionImpuestos = {data:[]}  // definir el data del datatable
		    self.update()
		    self.condicionImpuestos.data.push({
		        valor:"01",
		        descripcion:$.i18n.prop("codicion.impuesto.genera.iva.aplicable")
		    })
		    self.update()
		}

		//Se muestra los tipos medio de aceptacion	
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

                	//Se verifica que el XML no sea el del mensaje de hacienda enviado al correo
					if($(xmlDoc).find("MensajeHacienda").length > 0){
	    	  		    self.mostrarFormulario     = false;
	    	   		    self.mostrarCargaArchivo   = true;
	    	   		    self.update();					
	    	   		 	sweetAlert("", "Favor de revisar esta cargando el XML de respuesta, cargue el correcto", "error");
					}else{
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
						/**4.3**/
						self.archivo.emisorCodigoActividad = $(xmlDoc).find("CodigoActividad").text();
						
						/*Fin de la 4.3**/

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
	                    self.archivo.facturaConsecutivo = $(xmlDoc).find("NumeroConsecutivo").first().text();
	                    self.archivo.facturaClave = $(xmlDoc).find("Clave").first().text();
	                    self.archivo.facturaFechaEmision = ($(xmlDoc).find("FechaEmision")).first().text();
	                    self.archivo.facturaCondicionVenta = $(xmlDoc).find("CondicionVenta").first().text();
	                    self.archivo.facturaMedioPago = $(xmlDoc).find("MedioPago").first().text();
	                    
	                    //Se carga el detalle de la factura
						$("#detalleFactura").find("tr:gt(0)").remove();
	                    var detalles = $(xmlDoc).find("DetalleServicio");
						self.archivo.detallesFactura =[]
						self.itemArticulo = {}
						self.itemArticuloimpuestos =[]
						self.update()
	                    $(detalles).each(function () {
	                    	$(this).children().each(function () {
								var codigoTarifas = ""
								var montoTarifas = ""
								self.itemArticulo = {
									tipo:"",
									codigo:"",
									detalle:"",
									precioUnitario:0,
									cantidad:0,
									unidadMedida:"",
									montoTotal:0,
									montoDescuento:0,
									subTotal:0,
									ImpuestoNeto:0,
									MontoTotalLinea:0,
									impuestos:[{}]
								}
								 $(this).parent().find("Impuesto").each(function(id, node) {
									 self.impuesto={
										 codigo:"",
										 codigoTarifa:"",
										 tarifa:0,
										 factorIVA:0,
										 monto:0
									 }
								   
								   self.update()
                                   self.impuesto.codigo =$(node).find("Codigo").text()
								   self.impuesto.codigTarifa = $(node).find("CodigoTarifa").text()
								   self.impuesto.tarifa=$(node).find("Tarifa").text()
								   self.impuesto.factorIVA= $(node).find("FactorIVA").text()
								   self.impuesto.monto = $(node).find("Monto").text()
								   if(self.impuesto.codigo !=""){
                                      self.itemArticuloimpuestos.push(self.impuesto)
								   }
								   
                                   console.log('valor impuesto: ' + $(node).find("Codigo").text());
								   codigoTarifas +=$(node).find("CodigoTarifa").text() + ","
								   montoTarifas +=$(node).find("Monto").text() + ","
                                 })
								self.itemArticulo.impuestos = self.itemArticuloimpuestos
								self.itemArticulo.codigo = $(this).find("CodigoComercial").find("Codigo").text()
                                self.itemArticulo.tipo = $(this).find("CodigoComercial").find("Tipo").text()
								self.itemArticulo.detalle = $(this).find("Detalle").text()
								self.itemArticulo.precioUnitario = $(this).find("PrecioUnitario").text()
								self.itemArticulo.cantidad = $(this).find("Cantidad").text()
								self.itemArticulo.unidadMedida = $(this).find("UnidadMedida").text()
								self.itemArticulo.montoTotal = $(this).find("MontoTotal").text()
								self.itemArticulo.montoDescuento = $(this).find("MontoDescuento").text()
								self.itemArticulo.subTotal = $(this).find("SubTotal").text()
								if(self.itemArticulo.codigo !=""){
                                   self.archivo.detallesFactura.push(self.itemArticulo)
								}
								
				                var row = "<tr>" + 
				                    		  "<td>" + $(this).find("CodigoComercial").find("Codigo").text() + "</td>" + 
											  "<td>" + $(this).find("Detalle").text() + "</td>" + 
				                    		  "<td>" + $(this).find("PrecioUnitario").text() + "</td>" + 
				                    		  "<td>" + $(this).find("Cantidad").text() + "</td>" + 
				                    		  "<td>" + $(this).find("UnidadMedida").text() + "</td>" + 
				                    		  "<td>" + $(this).find("MontoTotal").text() + "</td>" + 
				                    		  "<td>" + $(this).find("MontoDescuento").text() + "</td>" + 
											  "<td>" + $(this).find("SubTotal").text() + "</td>" + 
											  "<td>" + codigoTarifas + "</td>" + 
											  "<td>" + montoTarifas + "</td>" + 
											  "<td>" + $(this).find("ImpuestoNeto").text() + "</td>" + 
				                    		  "<td>" + $(this).find("MontoTotalLinea").text() + "</td>" +
											  
			                    		  "</tr>";
			      	            $('#detalleFactura tr:last').after(row);
	                        });
	                    });
	                    
	                    //Se carga el resumen de la factura
	                    var resumenFactura = $(xmlDoc).find("ResumenFactura");
	                    self.archivo.facturaCodigoMoneda = resumenFactura.find("CodigoTipoMoneda").find("CodigoMoneda").text();
	                    self.archivo.facturaTipoCambio = resumenFactura.find("CodigoTipoMoneda").find("TipoCambio").text();
						/*nuevo 4.3*/
						self.archivo.facturaTipoDocumentoOtroCargo = $(xmlDoc).find("OtrosCargos").find("TipoDocumento").text();
						self.archivo.facturaDetalleOtroCargo = $(xmlDoc).find("OtrosCargos").find("Detalle").text();
						self.archivo.facturaMontoCargoOtroCargo = $(xmlDoc).find("OtrosCargos").find("MontoCargo").text();
						self.archivo.facturaTotalServGravados = resumenFactura.find("TotalServGravados").text();
						self.archivo.facturaTotalMercanciasGravadas = resumenFactura.find("TotalMercanciasGravadas").text();
						self.archivo.facturaTotalMercanciasExentas = resumenFactura.find("TotalMercanciasExentas").text();
						self.archivo.facturaTotalMercExonerada = resumenFactura.find("TotalMercExonerada").text();
						self.archivo.facturaTotalGravado = resumenFactura.find("TotalGravado").text();
						self.archivo.facturaTotalExonerado = resumenFactura.find("TotalExonerado").text();
						self.archivo.facturaTotalVenta = resumenFactura.find("TotalVenta").text();
						self.archivo.facturaTotalDescuentos = resumenFactura.find("TotalDescuentos").text();
						
						self.archivo.facturaTotalIVADevuelto = resumenFactura.find("TotalIVADevuelto").text();
						self.archivo.facturaTotalOtrosCargos = resumenFactura.find("TotalOtrosCargos").text();

                        /**fin del 4.3*/
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
	                                        
	                	//Se cargan los datos del objecto a almacenar en base de datos
	                	self.recepcionFactura.emisorNombre = self.archivo.emisorNombre;
	                	self.recepcionFactura.emisorCedula =  self.archivo.emisorCedula;
	                	self.recepcionFactura.emisorTipoCedula = self.archivo.emisorTipoCedula;
	                	self.recepcionFactura.emisorCorreo = self.archivo.emisorCorreo;
	                	self.recepcionFactura.emisorTelefono = self.archivo.emisorTelefono;
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
	                   /*nuevo 4.3*/
						self.recepcionFactura.facturaTipoDocumentoOtroCargo = self.archivo.facturaTipoDocumentoOtroCargo 
						self.recepcionFactura.facturaDetalleOtroCargo = self.archivo.facturaDetalleOtroCargo 
						self.recepcionFactura.facturaMontoCargoOtroCargo = self.archivo.facturaMontoCargoOtroCargo 
						self.recepcionFactura.facturaTotalServGravados = self.archivo.facturaTotalServGravados 
						self.recepcionFactura.facturaTotalMercanciasGravadas =  self.archivo.facturaTotalMercanciasGravadas
						self.recepcionFactura.facturaTotalMercanciasExentas = self.archivo.facturaTotalMercanciasExentas
						self.recepcionFactura.facturaTotalMercExonerada = self.archivo.facturaTotalMercExonerada
						self.recepcionFactura.facturaTotalGravado = self.archivo.facturaTotalGravado 
						self.recepcionFactura.facturaTotalExonerado = self.archivo.facturaTotalExonerado 
						self.recepcionFactura.facturaTotalDescuentos = self.archivo.facturaTotalDescuentos 
						self.recepcionFactura.facturaTotalIVADevuelto = self.archivo.facturaTotalIVADevuelto 
						self.recepcionFactura.facturaTotalOtrosCargos = self.archivo.facturaTotalOtrosCargos 
                        self.recepcionFactura.detallesFactura =self.archivo.detallesFactura
                        /**fin del 4.3*/	                	
	    	  		    self.mostrarFormulario     = true;
	    	   		    self.mostrarCargaArchivo   = true;
					    self.update()

					}
                }

                reader.readAsText($("#fileUpload")[0].files[0]);
                //Se muestra la pantalla
	   		    self.update();					

            } else {
	   		 	sweetAlert("", "Se presento un problema al intentar cargar el archivo, su browser no soporta HTML 5", "error");
            }
		}
        
		function limpiar(){
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
				facturaTipoDocumentoOtroCargo:"",
				facturaDetalleOtroCargo:"",
				facturaMontoCargoOtroCargo:0,
				facturaTotalServGravados:0,
				facturaTotalMercanciasGravadas:0,
				facturaTotalMercanciasExentas:0,
				facturaTotalMercExonerada:0, 
				facturaTotalGravado:0, 
				facturaTotalExonerado:0, 
				facturaTotalDescuentos:0, 
     			facturaTotalIVADevuelto:0, 
	    		facturaTotalOtrosCargos:0 

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
				facturaTipoDocumentoOtroCargo:"",
				facturaDetalleOtroCargo:"",
				facturaMontoCargoOtroCargo:0,
				facturaTotalServGravados:0,
				facturaTotalMercanciasGravadas:0,
				facturaTotalMercanciasExentas:0,
				facturaTotalMercExonerada:0, 
				facturaTotalGravado:0, 
				facturaTotalExonerado:0, 
				facturaTotalDescuentos:0, 
     			facturaTotalIVADevuelto:0, 
	    		facturaTotalOtrosCargos:0 ,

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