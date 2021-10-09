<punto-venta>

<div id='modalCambiarDescripcion' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> &nbsp;{$.i18n.prop("titulo.cambiar.cantidad")}   </h1>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <label class="tituloClienteNuevo" >{$.i18n.prop("articulo.descripcion")} </label>
                        <input type="text" class="form-control cambiarDescripcionArticulo tamanoClienteNuevo modalInputCambioPrecio"  id="cambiarDescripcionArticulo" name="cambiarDescripcionArticulo"   autofocus="autofocus"  autocomplete="off">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__cambiarDescripcionDetalle}   class="btn-green btn-add pull-right" >  {$.i18n.prop("btn.aplicar")}</button>
                </div>
            </div>
        </div>
    </div>
</div>


<!--Modal Cambiar precio-->
<div id='modalCambiarPrecioDetalle' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Cambio de Precio en la Factura(no cambia el inventario)</h1>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <label class="tituloClienteNuevo" >Digite el nuevo Precio  </label>
                        <input type="number" class="form-control cambiarprecioArticulo tamanoClienteNuevo cambiarprecioArticuloDetalle"  id="cambiarprecioArticuloDetalle" name="cambiarprecioArticuloDetalle"   autofocus="autofocus" min="0" autocomplete="off">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__cambiarElPrecioDetalle}   class=" btn-green pull-right modalCambioPrecioBotones" > Aplicar </button>
                </div>
            </div>
        </div>
    </div>
</div>



<div id='modalCambiarPrecio' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Cambiar el Precio  del Ultimo producto ingresado</h1>
            </div>
            <div class="modal-body">
                <form  >
                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-6 col-lg-12">
                            <label class="tituloClienteNuevo" >Codigo</label>
                            <input type="text" class="form-control tamanoClienteNuevo modalInputCambioPrecioCodigoDescripcion " readonly  value ="{ultimoArticulo.codigo}">
                        </div>
                    </div>
                    <div class="row">    
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Descripcion</label>
                            <input type="text" class="form-control tamanoClienteNuevo modalInputCambioPrecioCodigoDescripcion "  id="descripcionArticuloAcambiar" name="descripcionArticuloAcambiar"  value ="{ultimoArticulo.descripcion}">

                        </div>                            
                    </div>

                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Precio al Publico </label>
                            <input type="text" class="form-control precioAcambiar tamanoClienteNuevo modalInputCambioPrecio"  id="precioAcambiar" name="precioAcambiar" autofocus="autofocus"  value ="{ultimoArticulo.precioPublico}" autocomplete="off">
                        </div>
                    </div>
 
                </form>    
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button onclick={__RegresarInputCodigo}   type="button" class="btn-dark-gray btn-back  pull-left modalCambioPrecioBotones"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__AplicarCambioPrecioUltimoArticulo}   class=" btn-green pull-right modalCambioPrecioBotones" > Cambiar Precio </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div id='modalRolUsuario' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Seguridad de Acceso Solo Administradores</h1>
            </div>
            <div class="modal-body">
                <form  id='formularioModalRolUsuario' name='formularioModalRolUsuario'>
                    <div class="row">    
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Digite el Usuario Administrador</label>
                            <input type="text" class="form-control usuarioSistema tamanoClienteNuevo modalInputCambioPrecio"  id="usuarioSistema" name="usuarioSistema" autofocus="autofocus"  >

                        </div>                            
                    </div>

                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Digite la Clave  </label>
                            <input type="password" class="form-control claveSistema tamanoClienteNuevo modalInputCambioPrecio"  id="claveSistema" name="claveSistema" autofocus="autofocus"   autocomplete="off">
                        </div>
                    </div>
 
                </form>    
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button onclick={__RegresarInputSeguridad}   type="button" class="btn-dark-gray btn-back  pull-left modalCambioPrecioBotones"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__SeguridadVentas}   class=" btn-green pull-right modalCambioPrecioBotones" > Autorizar </button>
                </div>
            </div>
        </div>
    </div>
</div>

 <!-- Titulos -->
    <div  class="row titulo-encabezado" show={parametros.codigoMoneda =="USD"?true:false} >
        <div  class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h1 ><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("facturar.dolares.titulo")}  </h1>
        </div>
        <div class=" col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
<!--validar rol de usuario-->



<!--Modal abrirCajon sin comanda-->
<div id='modalabrirCajon' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-body">
                <div id="imprAbriCajon" name ="imprAbriCajon">
                    <div class="row">
                        <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                            <div class="form-group has-success">
                                <input  type="text"  class="form-control" value={informacionAbrirCajon}>
                            </div>
                        </div>
                    </div> 
                </div>    
            </div>
        </div>
    </div>
</div>
<!---Datos Final cuando no es un venta de Crucero -->
<div show={mostrarFormularioPago}>
		<div class="row " >
			<div class="col-md-8 col-sm-8 col-lg-8 col-sx-12 ">
				<div class="box">
					<div class="box-header with-border fondoEncabezado">
						<h3 class="box-title">{$.i18n.prop("ventas.titulo")} </h3>
                        <h3 class="box-title pull-right ">{$.i18n.prop("ventas.tipo.cambio.titulo")} {tipoCambio.total} </h3>
					</div>
					<div class="box-body">
                        <form id="formularioFactura">
                            <div class="row">
                                <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                    <div class="row">
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group ">
                                                <label>{$.i18n.prop("factura.condicion.pago")} </label> 
                                                <select  onchange= {__formaPago} class="form-control condicionVenta" id="condicionVenta" name="condicionVenta">
                                                    <option each={comboCondicionPagos} value="{estado}" selected="{factura.condicionVenta ==estado?true:false}" >{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>    
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group ">
                                                <label for="pago_tipoVentaL">{$.i18n.prop("factura.tipo.documento")} </label> 
                                                <select onchange= {__AsignarTipoDocumento}  class="form-control tipoDoc" id="tipoDoc" name="tipoDoc"   >
                                                    <option each={comboTipoDocumentos} value="{estado}" selected="{factura.tipoDoc ==estado?true:false}" >{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label >{$.i18n.prop("factura.nota")}</label> 
                                        <input type="text" class="campo nota" id="nota" name="nota" value="{factura.nota}">
                                    </div>
                                    <h3> <p class="text-primary">{$.i18n.prop("factura.emisor")}</p></h3>
                                    <div class="input-group ">
                                        <span onclick = {__ClienteNuevo} title="AGREGAR CLIENTE NUEVO" class="input-group-addon btnClientes" id="add-new-client"> 
                                        <small class="fa fa-plus" style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                        <span class="fa fa-user" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"></span> 
                                    </div>
                                    <div class="form-group ">
                                        </span>
                                        <input   type="hidden" class="form-control" id="cliente" name="cliente" value="{cliente.id}">
                                        <label>{$.i18n.prop("factura.cliente")}</label> 
                                        <input onclick = {_EscogerClientes}  type="text" id="nombreCliente" name="nombreCliente" class="campo"  value="{cliente.nombreCompleto}" readonly>
                                    </div>
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.correoElectronico")}</label> 
                                        <input  type="text"  class="form-control"  value="{cliente.correoElectronico}" readonly>
                                    </div>                                    
                                   <div class="form-group ">
                                        <label>{$.i18n.prop("factura.correoAlternativo")}</label> 
                                        <input type="email" id="correoAlternativo" name="correoAlternativo" class="campo correoAlternativo"  value="{factura.correoAlternativo}"  autocomplete="off">
                                    </div>
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("factura.nombreFactura")}</label> 
                                        <input type="text" id="nombreFactura" name="nombreFactura" class="campo nombreFactura "  value="{factura.nombreFactura}"  autocomplete="off"> 
                                    </div>
                                    
                                    <div show = "{mostrarCamposIngresoContado ==false }" class="form-group ">
                                        <label >{$.i18n.prop("factura.fecha.credito")}</label> 
                                        <div  class="form-group input-group date datepickerFechaCredito" data-provide="datepicker"  data-date-start-date="0d" data-date-format="yyyy-mm-dd">
                                            <input  onclick={__ActualizarPlazoCredito} type="text" class="campo fechaCredito selectFechaCredito" name="fechaCredito" id="fechaCredito" value="{factura.fechaCredito}" >
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>    
                                    <div class="form-group " show = "{mostrarCamposIngresoContado == false}">
                                        <label>{$.i18n.prop("factura.plazoCredito")}</label> 
                                        <input type="number" id = "plazoCreditoL"  name "plazoCreditoL" class="campo plazoCreditoL" value="{factura.plazoCredito}" autocomplete="off" >
                                    </div>
                                </div>
                                <div  class= "col-md-6 col-sx-6 col-sm-6 col-lg-6" >
                                    <div class="form-group ">
                                        <label class="{labelTotales}">{$.i18n.prop("factura.resumen.efectivo")} </label> 
                                        <input onclick={_clickEfectivo}   onkeyup={ __TotalDeEfectivoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="{campoTotales} {tamanoLetra} totalEfectivo " id="totalEfectivo" name="totalEfectivo"  autocomplete="off">
                                    </div>
                                    <div  class="form-group ">
                                        <label class="{labelTotales}">{$.i18n.prop("factura.resumen.tarjeta")}<span class="teclashift">(Tecla =shift )</span>  </label> 
                                        <input onclick={_SeleccionarTarjeta} onkeyup={ __TotalDeTarjetaAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="{campoTotales} {tamanoLetra} totalTarjeta" id="totalTarjeta" name="totalTarjeta"  value="{factura.totalTarjeta}" autocomplete="off">
                                    </div> 
                                    <div  class="form-group " show={empresa.pantChino == 0}>
                                        <label class="{labelTotales} ">{$.i18n.prop("factura.resumen.banco")} </label> 
                                        <input onclick={_SeleccionarBanco} onkeyup={ __TotalDeBancoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number"  onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="{campoTotales} {tamanoLetra}  totalBanco"  id="totalBanco" name="totalBanco"  value="{factura.totalBanco}"  autocomplete="off">
                                    </div>
                                    
                                

                                </div>
                            </div>
                            <input type="hidden" id='codigoActividad' name='codigoActividad'  value="{factura.codigoActividad}" >
                            <input type="hidden" id='codigoMoneda'            name='codigoMoneda'            value="{factura.codigoMoneda}" >
                            <input type="hidden" id='tipoCambioMoneda'        name='tipoCambioMoneda'        value="{tipoCambio.total}" >
                            <input type="hidden" id='pesoTransporteTotal'     name='pesoTransporteTotal'     value="{totalPesoByFactura}" >
                            <input type="hidden" id='id'                      name='id'                      value="{factura.id}" >
                            <input type="hidden" id='plazoCredito'            name='plazoCredito'            value="{factura.plazoCredito}" >
                            <input type="hidden" id='estado'                  name='estado'                  value="{factura.estado}" >
                            <input type="hidden" id='subTotal'                name='subTotal'                value="{factura.subTotal}" >
                            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
                            <input type="hidden" id='totalComprobante'        name='totalComprobante'        value="{factura.totalComprobante}" >
                            <input type="hidden" id='totalMercanciasGravadas' name='totalMercanciasGravadas' value="{factura.totalMercanciasGravadas}" >
                            <input type="hidden" id='totalMercanciasExentas'  name='totalMercanciasExentas'  value="{factura.totalMercanciasExentas}" >
                            <input type="hidden" id='totalServGravados'       name='totalServGravados'       value="{factura.totalServGravados}" >
                            <input type="hidden" id='totalServExentos'        name='totalServExentos'        value="{factura.totalServExentos}" >
                            <input type="hidden" id='totalGravado'            name='totalGravado'            value="{factura.totalGravado}" >
                            <input type="hidden" id='totalExento'             name='totalExento'             value="{factura.totalExento}" >
                            <input type="hidden" id='totalVenta'              name='totalVenta'              value="{factura.totalVenta}" >
                            <input type="hidden" id='totalDescuentos'         name='totalDescuentos'         value="{factura.totalDescuentos}" >
                            <input type="hidden" id='totalVentaNeta'          name='totalVentaNeta'          value="{factura.totalVentaNeta}" >
                            <input type="hidden" id='totalImpuesto'           name='totalImpuesto'           value="{factura.totalImpuesto}" >
                            <input type="hidden" id='totalCambioPagar'        name='totalCambioPagar'        value="{factura.totalCambioPagar}" >
                            <input type="hidden" id='detalleFactura'          name='detalleFactura'          value="{factura.detalleFactura}" >
                        </form>   
                        <div class="botonesContainer">
                            <div class="boton">
                                <button onclick={_AtrasFacturaFinal} class="btn-dark-gray btn-back pull-left">  {$.i18n.prop("btn.volver")}</button>
                            </div> 
                            <div class="boton">
                                <button onclick={__AplicarYcrearFactura}  class="btn-green btn-add pull-right"> </i> {$.i18n.prop("btn.aplicar")}</button>
                            </div>
                                       
                        </div>
                    </div>
                  
                </div>
                  
            </div>
            <div class="col-md-4 col-sm-4 col-lg-4 col-sx-12 ">
		        <div class="box">
				    <div class="box-body">
                        <aside class="right-sidebar">
                            <!--Booking details-->
                            <article class="booking-details clearfix">
                                <h1><span id="lblSCS">{$.i18n.prop("factura.resumen.venta")}</span></h1>
                                <div class="TotalesContainer"  onclick = {__MostrarFormularioDePago}>
                                    <div  show="{soloParaChinos == false}" class="elementoTotales">{$.i18n.prop("factura.resumen.subTotal")}   <span id="lblSubtotal"> {subTotalGeneral}   </span> </div> 
                                    <div  show="{soloParaChinos == false}" class="elementoTotales">{$.i18n.prop("factura.resumen.descuento")}  <span id="lblSubtotal"> {totalDescuentos}   </span> </div> 
                                    <div  show= "{soloParaChinos == false}" class="elementoTotales">{$.i18n.prop("factura.resumen.impuesto")}    <span id="lblSubtotal"> {totalImpuesto}    </span> </div> 
                                    <div  show="{soloParaChinos == false && montoExoneracion.length > 0}" class="elementoTotales">{$.i18n.prop("factura.resumen.exoneracion")} <span id="lblSubtotal"> {montoExoneracion} </span> </div> 
                                    <div  show="{soloParaChinos == false}" class="elementoTotales">{$.i18n.prop("factura.resumen.total")}   <span id="lblTotal">{totalComprobante}         </span> </div> 
                                    <div  show="{soloParaChinos == false}" class="elementoTotales">{$.i18n.prop("factura.resumen.cambio")} <span id="lblTotal">{totalCambioPagarSTR}</span> </div> 
                                </div>
                                <div class="precioTotalFactura" show={soloParaChinos == true}>
                                    <p class="total label-totalesChinos" style="text-align:right;">Total:  <span id="lblTotal">{totalComprobante}</span></p>
                                </div>
                                <div class="{claseCambioDinero}" show={mostrarCamposIngresoContado && soloParaChinos == true }>
                                    <p class="total label-totalesChinos" style="text-align:right;">{$.i18n.prop("factura.resumen.cambio")} <span id="lblTotal">{totalCambioPagarSTR}</span></p>    
                                </div>
                                <div class="pantallaBilletes">
                                   <div class="billeteContainer">
                                      <div class="billete" each={billetes}   onclick={_sumarBilletes}>
                                          <img  alt="" class="img-responsive imagenesBilletes " src="{imagen}">
                                      </div>
                                   </div>
                                </div>
                            </article>
                        </aside>
                    </div><!-- fin box-body-->
				</div><!-- fin box -->
		    </div>
        </div>  
         
</div>  
<!--Fin Ventana de los billetes-->      
 <div class="ventaEsperaSeleccionada" show={factura.id !=null && mostrarFormularioPago == false}>
    <div class="tituloVentaEspera"> 
           Venta en Espera: {factura.consecutivoProforma != null && factura.consecutivoProforma.length > 0  ?factura.consecutivoProforma :factura.id} 
            {factura.cliente.nombreCompleto === 'CLIENTE_FRECUENTE' || factura.cliente.nombreCompleto === 'CLIENTE_CREDITO'? factura.nombreFactura.length > 0?"-Nombre:" +factura.nombreFactura:'Sin Cliente Asociado' :"-Nombre:"+factura.cliente.nombreCompleto} 
    </div>
</div>
 <div  class="contenedorFactura" show={mostarParaCrearNuevaFactura}>
                <div class="cabecera-izquierda">
                    <div class="botonesFuncionalContainer">
                         <div class="botonesFuncional ">
                           <div   class= "labelBotones" onclick = {_ListaFacturasDia} title="{$.i18n.prop("btn.tiquete")}"> {$.i18n.prop("factura.f5")}</div>
                        </div> 
                        <div class="botonesFuncional ">
                           <div  class= "labelBotones" onclick = {__LimpiarFormulario} title="{$.i18n.prop("btn.limpiar")}"> {$.i18n.prop("factura.f10")}</div>
                        </div>
                        <div class="botonesFuncional ">
                           <div  class= "labelBotones"  onclick = {__ListaDecodigos} title="{$.i18n.prop("btn.tiquete")}"> {$.i18n.prop("factura.f4")}</div>
                        </div>
                        <div class="botonesFuncional ">
                           <div class= "labelBotones"     onclick = {_ReimprimirFactura} title="Reemprimir la factura/tiquete"> {$.i18n.prop("factura.f6")}</div>
                        </div>
                        <div class="botonesFuncional ">
                           <div  class= "labelBotones"   onclick = {__MostrarFormularioDePago}   title="{$.i18n.prop("crear.ventas")}"> {$.i18n.prop("factura.f8")}</div>
                        </div>
                        <div class="botonesFuncional ">
                           <div  class= "labelBotones"    onclick = {__AplicarYcrearFacturaTemporal} title="{$.i18n.prop("btn.tiquete")}"> {$.i18n.prop("factura.f9")}</div>
                        </div>
                        <div class="botonesFuncional " show={mostarAbrirCajon == true} >
                           <div  class= "labelBotones"    onclick = {__AbrirCajon} title="{$.i18n.prop("btn.tiquete")}"> {$.i18n.prop("abrir.cajon")}</div>
                        </div>
                   </div>
                    <div class="codigoBarraPrecioContainer">
                        <div class="inputCodigoPrecio">
                            <input id="codigo" name ="codigo" class="campo codigo" type="text" placeholder="XXXXXXXXXXXXXXXXXX" autocomplete="off"/>
                        </div>
                        <div class="inputCodigoPrecio">
                            <input onkeypress={__addPrecioDetail}  id="precioVenta" name ="precioVenta" class="campo precioVenta" type="number" step="any"  placeholder="Precio Ejemplo:600" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="tituloProductoIngresadoContainer" show={descripcionArticulo.length>0?descripcionArticulo:""}>
                        <div class="tituloDescripcionProductoIngresado">
                            <div class="ultimo_1">Ultimo: </div>
                            <div class="ultimo_2"> {descripcionArticulo.length > 50?descripcionArticulo.substring(0, 50):descripcionArticulo} </div>
                        </div>
                    </div>
                    <div class="detalles-factura">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th style="width:5%;">                                                        </th>
                                <th style="width:5%;"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.linea")}                         </div></th>
                                <th><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.codigo")}                        </div></th>
                                <th style="width:20%;"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.descripcion")}</div></th>
                                <th style="width:8%;"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.cantidad")}    </div> </th>
                                <th ><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.precio")}                       </div></th>
                                <th style="width:4%"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.descuento")}                    </div></th>
                                <th style="width:4%"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.impuesto")}     </div></th>
                                <th ><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.subTotal")}                     </div> </th> 
                            </tr>
                            </thead>
                            <tbody>
                            <tr each={detail}>
                                <td>
                                   <button id="{numeroLinea}" name="{numeroLinea}"  onclick={__removeProductFromDetail} class="btn btn-danger btn-xs btn-block">X</button>
                                </td>
                                <td style="width:5%;"  class="campoLabel"><label >{numeroLinea}</label> </td>
                                <td  style="width:4%" class="campoLabel"> <label >{codigo}</label></td>
                                <td class="campoLabel" onclick ={__CambiarDescripcion}><label >{descripcion}</label></td>
                                <td class="text-right" style="width:8%;">
                                    <input onclick={__CambiarCantidad} id= "cantidadDetalle" class="campoDetalle " type="number" placeholder="Cantidad Detalle" value = {cantidad.toFixed(3)} readonly />
                                </td>
                                <td class="campoLabel">
                                    <a class="botones-funcionales" href="#"    onclick = {__CambiarPrecioFactura} title="Cambiar Precio: {precioUnitario.toFixed(2)}"> <label  class= "lineaPrecioPuntoVenta">{precioUnitario.toFixed(2)}</label></a>
                                    
                                </td>
                                <td class="text-right" style="width:8%">
                                    <input  onclick={__CambiarDescuento} class="campoDetalle" type="text"  value = "{porcentajeDesc.toFixed(2)}" readonly/>
                                </td>
                                <td  style="width:4%" class="campoLabel">
                                     <label >{impuesto.toFixed(2)}</label>
                                </td>
                                <td class="campoLabel">
                                    <label >{montoTotalLinea.toFixed(2)}</label>
                                </td>
                            </tr>
                            </tbody>
                        </table>          
                    </div>
                </div>
                <section class="cabecera-derecha">
                    <div class="tituloCantidadArticulos" show={cantArticulos>0}><div class="cantidadArticulosTitulo"> Producto.No={cantArticulos}</div></div>
                    
                     <aside class="left-sidebar">
                        <article class="clearfix">
                            <div onclick = {__MostrarFormularioDePago}  class="precioTotalFacturaContainer"  show={soloParaChinos == true}>
                                <div class="label-totalesComprobanteChino" >Total:  {totalComprobante}</div>
                            </div>
                            <div class="TotalesContainer"  onclick = {__MostrarFormularioDePago}>
                                 <div  show="{soloParaChinos == false}" class="elementoTotales">{$.i18n.prop("factura.resumen.subTotal")}   <span id="lblSubtotal"> {subTotalGeneral}   </span> </div> 
                                 <div  show="{soloParaChinos == false }" class="elementoTotales">{$.i18n.prop("factura.resumen.descuento")}  <span id="lblSubtotal"> {totalDescuentos}   </span> </div> 
                                 <div  show="{soloParaChinos == false }" class="elementoTotales" >{$.i18n.prop("factura.resumen.impuesto")}     <span id="lblSubtotal"> {totalImpuesto}    </span> </div> 
                                 <div  show="{soloParaChinos == false && montoExoneracion > 0}" class="elementoTotales">{$.i18n.prop("factura.resumen.exoneracion")} <span id="lblSubtotal"> {montoExoneracion} </span> </div> 
                                 <div  show="{soloParaChinos == false}" class="elementoTotales">{$.i18n.prop("factura.resumen.total")}   <span id="lblTotal">{totalComprobante}         </span> </div> 
                            </div>
                            <div class="gananciaContainer">
                                <div class="formatoTituloGanancia">IG: {totalGananciaByProducto}</div>
                                <div class="formatoTituloGanancia">PG: {totalPesoByFacturaSTR}</div>
                            </div>
                            <br>
                            <div class="seleccionOtroPrecioVenta">
                                <div class="opcionPrecioPublico">
                                    <label class="titleListaPrecio">Lista de Precios </label>  
                                    <select  class="form-control selectListaPrecios" id="selectListaPrecios" >
                                        <option    value="1"  >Precio Publico</option>
                                        <option    value="2"  >Precio Mayorista</option>
                                        <option    value="3"  >Precio Especial</option>
                                    </select>
                                </div>
                            </div>
                            <br>
                            <div class="seleccionOtroPrecioVenta">
                                <div class="opcionPrecioPublico">
                                    <label class="titleListaPrecio">Actividades Economicas </label>  
                                    <select onchange= {__AsignarActividad} class="form-control selectActividadComercial"  name="selectActividadComercial" id="selectActividadComercial" >
                                        <option  each={empresaActividadComercial}  value="{codigo}"   >{codigo}-{descripcion}</option>
                                    </select>
                                </div>
                            </div>
                            <br>
                            <div id = "tipoCambioDolarCSS">
                                <div >
                                    <span class="ssCambioCentral">Tipo Cambio del Banco Central</span>
                                </div>
                                <div id= "tipoCambioCSS">
                                    <div >
                                        <span class="ssCambio">Compra USD $ </span>
                                        <label class="tituloTipoCambio">{tipoCambio.totalCompra} </label>  
                                    </div>
                                    <div>
                                        <span class="ssCambio">Venta USD $ </span> 
                                        <label class="tituloTipoCambio">{tipoCambio.total}  </label>  
                                    </div>
                                
                                </div>
                            </div>
                            <div class = 'containerIconosSumaRestaAgregarCliente'>
                                <div class="BotonesSumarRestar">
                                    <span onclick = {__SumarConMouse} title="Sumar +" class="fontSumarRestar input-group-addon btnClientes" id="botonSumar"> 
                                        <small class="fa " style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                        <span class="fa fa-plus" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"/>
                                          Sumar
                                    </span> 
                                </div>                     
                            
                                <div class="BotonesSumarRestar">
                                    <span onclick = {__RestarConMouse} title="Restar -" class="fontSumarRestar input-group-addon btnClientes" id="botonRestar"> 
                                        <small class="fa " style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                        <span class="fa fa-minus" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"/>
                                          Restar
                                    </span> 
                                </div>                     
                                
                                <div class="BotonesSumarRestar">
                                    <span onclick = {__AplicarCambioPrecio} title="Cambio de Precio" class="fontSumarRestar input-group-addon btnClientes" id="botonCambiarPrecio"> 
                                        <small class="fa " style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                        <span class="fa fa-calculator" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"/>
                                      / = cambio Precio
                                    </span> 
                                </div>                     

                                <div class="BotonesSumarRestar">
                                    <span onclick = {__EntradaDinero} title="Salida de Dinero de la caja" class="fontSumarRestar input-group-addon btnClientes" id="botonEntradaDinero"> 
                                        <small class="fa " style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                        <span class="" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"/>
                                        Entrada de Dinero 
                                    </span> 
                                </div>                     
                                <div class="BotonesSumarRestar">
                                    <span onclick = {__SalidaDinero} title="Salida de Dinero de la caja" class="fontSumarRestar input-group-addon btnClientes" id="botonEntradaSalida"> 
                                        <small class="fa " style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                        <span class="" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"/>
                                        Salida de Dinero
                                    </span> 
                                </div>                     
                                <div class="BotonesSumarRestar">
                                    <span onclick = {__ClienteNuevo} title="AGREGAR CLIENTE NUEVO" class="fontSumarRestar input-group-addon btnClientes" id="botonNuevoCliente"> 
                                        <small class="fa " style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                        <span class="fa fa-user-o" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"/>
                                        Nuevo Cliente
                                    </span> 
                                </div>
                              

                            </div>
                            <section   class="ventaEspera">
                                <div class="elementoVentaEspera"  each={facturas_espera.data}  onclick={__CargarFacturaEspera}>
                                    <div show ="{consecutivoProforma.length>0?true:false}"  class="fondoVentaEspera" title="{nombreCompleto !=null?nombreCompleto:""}"><span class="tamanoVentaEspera">P: {consecutivoProforma} </span></div>  
                                    <div show ="{consecutivoProforma.length == 0?true:false}" class="fondoVentaEspera"  title="{nombreCompleto !=null?nombreCompleto:"Venta en espera"}"><span class="tamanoVentaEspera">V: {id} </span></div>  
                                </div>    
                            </section >
                            
                        </article>
                    </aside>
                </section>
</div><!-- fin contenedor-factura-->
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
                                    <input type="text" class="form-control codigoArt"  id="codigoArt" name="codigoArt"  onkeypress={__ConsultarProductosCod} autocomplete="off">
                                </div>
                                <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                    <label  >{$.i18n.prop("articulo.descripcion")}</label>
                                    <input type="text" class="form-control descArticulo "   id="descArticulo" name="descArticulo" onkeypress={__ConsultarProductosDesc} autofocus="autofocus" autocomplete="off">
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
<!--Modal mostrar Facturas del Dias -->
<div id='modalFacturasDia' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("factura.listar.dia")} </h4>
            </div>
            <div class="modal-body facturaDiaContainer">
                <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <table id="tableListarFacturasDia" class="display table responsive table-hover nowrap table-condensed tableListarFacturasDia "   cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class = "table-header" >{$.i18n.prop("listado.acciones")}                 </th>
                                <th class = "table-header" >{$.i18n.prop("factura.fecha.emision")}            </th>
                                <th class = "table-header" >{$.i18n.prop("factura.documento")}                </th>
                                <th class = "table-header" >{$.i18n.prop("factura.condicion.pago")}      
                                
                                <th class = "table-header" >{$.i18n.prop("factura.cliente")}                  </th>
                                <th class = "table-header" >{$.i18n.prop("factura.linea.detalle.descuento")}  </th>
                                <th class = "table-header" >{$.i18n.prop("factura.total")}                    </th>
                                
                            </tr>
                            </thead>
                                <tfoot style="display: table-header-group;">
                                    <tr>
                                        <th>                                                  </th>
                                        <th>{$.i18n.prop("factura.fecha.emision")}            </th>
                                        <th>{$.i18n.prop("factura.documento")}                </th>
                                        <th>{$.i18n.prop("factura.condicion.pago")}           </th>
                                        
                                        <th>{$.i18n.prop("factura.cliente")}                  </th>
                                        <th>{$.i18n.prop("factura.linea.detalle.descuento")}  </th>
                                        <th>{$.i18n.prop("factura.total")}                    </th>
                                        
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
<!--Modal mostrar  -->
<div id="modalClientes" class="modal fade modalClientes " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("cliente.lista")}   </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <table id="tableListaCliente" class="table responsive display table-striped table-hover nowrap tableListaCliente " cellspacing="0" width="100%">
                        <thead>
                                <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                                <th style="width:5%" class="table-header">{$.i18n.prop("cliente.cedula")}            </th>
                                <th class="table-header">{$.i18n.prop("cliente.nombreCompleto")}    </th>
                                <th style="width:8%"  class="table-header">{$.i18n.prop("cliente.correoElectronico")} </th>
                                <th class="table-header">{$.i18n.prop("cliente.nombreComercial")}   </th>
                            </thead>
                            <tfoot style="display: table-header-group;">
                                <tr>
                                    <th>                                          </th>
                                    <th style="width:5%">{$.i18n.prop("cliente.cedula")}</th>
                                    <th>{$.i18n.prop("cliente.nombreCompleto")}</th>
                                    <th style="width:8%">{$.i18n.prop("cliente.correoElectronico")}</th>
                                    <th>{$.i18n.prop("cliente.nombreComercial")}</th>
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
 
<!--Modal mostrar  -->
<div id="modalVendedor" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("vendedor.lista")}   </h4>
            </div>
            <div class="modal-body">
                <table id="tableListaVendedor" class="table responsive display table-striped table-hover nowrap tableListaVendedor " cellspacing="0" width="100%">
                   <thead>
                        <th class="table-header">{$.i18n.prop("vendedor.cedula")}            </th>
                        <th class="table-header">{$.i18n.prop("vendedor.nombreCompleto")}    </th>
                        <th class="table-header">{$.i18n.prop("vendedor.correoElectronico")} </th>
                        <th class="table-header">{$.i18n.prop("vendedor.telefono")}          </th>
                        <th class="table-header">{$.i18n.prop("vendedor.celular")}           </th>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("vendedor.cedula")}           </th>
                            <th>{$.i18n.prop("vendedor.nombreCompleto")}   </th>
                            <th>{$.i18n.prop("vendedor.correoElectronico")}</th>
                            <th>{$.i18n.prop("vendedor.telefono")}         </th>
                            <th>{$.i18n.prop("vendedor.celular")}          </th>
                            <th>                                           </th>
                        </tr>
                    </tfoot>                    
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left" >{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->


<div id='modalCambiarCantidad' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Cambiar Cantidad</h1>
            </div>
            <div class="modal-body">
                <form  >

                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Cantidad </label>
                            <input type="text" class="form-control cambiarCantidadArticulo tamanoClienteNuevo modalInputCambioPrecio"  id="cambiarCantidadArticulo" name="cambiarCantidadArticulo"    min="0" autocomplete="off">
                        </div>
                    </div>
 
                </form>    
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__recalculacionDelDetalle}   class=" btn-green pull-right modalCambioPrecioBotones" > Aplicar </button>
                </div>
            </div>
        </div>
    </div>
</div>




<div id='modalCambiarDescuento' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Aplicar el Descuento al producto</h1>
            </div>
            <div class="modal-body">
                <form id='formularioDescuento' name ='formularioDescuento'  >

                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Descuento </label>
                            <input type="text" class="form-control aplicarDescuento tamanoClienteNuevo modalInputCambioPrecio"  id="aplicarDescuento" name="aplicarDescuento" autofocus="autofocus"    min="0" autocomplete="off">
                        </div>
                    </div>
 
                </form>    
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__actualizarDescuento}   class=" btn-green pull-right modalCambioPrecioBotones" > Aplicar </button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Fin Cambiar Descuento-->


<div id='modalAgregarClienteNuevo' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> Agregar Cliente Nuevo </h4>
            </div>
            <div class="modal-body">
                <form id="formularioAgregarCliente" name ="formularioAgregarCliente" >
                    <div class="row">
                        <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <label class="tituloClienteNuevo" >{$.i18n.prop("cliente.cedula")} <span class="requeridoDato">*</span></label>
                            <input type="text" class="form-control tamanoClienteNuevo cedula" id="cedula" name="cedula"  onkeypress = {__ConsultarHacienda} onBlur ={__ConsultarHaciendaBlur}  autocomplete="off">
                        </div>
                        <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <label class="tituloClienteNuevo" >{$.i18n.prop("cliente.tipoCedula")}  <span class="requeridoDato">*</span></label>
                            <select  class="form-control tipoCedula  tamanoClienteNuevo" id="tipoCedula" name="tipoCedula" >
                                <option  each={tipoCedulas.data}  value="{valor}"   >{descripcion}</option>
                            </select>
                        </div>                            
                    </div>

                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >{$.i18n.prop("cliente.nombreCompleto")}  <span class="requeridoDato">*</span></label>
                            <input type="text" class="form-control nombreCompleto tamanoClienteNuevo"  id="nombreCompleto" name="nombreCompleto" autocomplete="off">
                        </div>
                    </div>
                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >{$.i18n.prop("cliente.correoElectronico")}</label>
                            <input type="text" class="form-control correoElectronico tamanoClienteNuevo"  id="correoElectronico" name="correoElectronico"  autocomplete="off">
                        </div>
                    </div>

                    <div class="row">
                        <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >{$.i18n.prop("cliente.codigoPais")} <span class="requeridoDato">*</span> </label>
                            <input type="text" class="form-control codigoPais tamanoClienteNuevo"  id="codigoPais" name="codigoPais" autocomplete="off" >
                        </div>
                        <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >{$.i18n.prop("cliente.telefono")} </label>
                            <input type="text" class="form-control telefono tamanoClienteNuevo"  id="telefono" name="telefono" autocomplete="off" >
                        </div>
                        <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Autorizacion del Mag</label>
                                <select  class="form-control tipoMag tamanoClienteNuevo" id="tipoMag" name="tipoMag"   >
                                    <option  each={estadosMag}  value="{codigo}" selected="{cliente.tipoMag ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        <div>    


                    </div>
 
                </form>    
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button onclick ={__regresarClienteNuevo}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" show="{mostrarBotonAgregarCliente == true?true:false}">
                    <button  onclick={__AplicarAgregarCliente}   class=" btn-green pull-right" >  Agregar Cliente</button>
                </div>
            </div>
        </div>
    </div>
</div>






<!--fin validar rol de usuario-->


<script>
    var self = this;
    self.colorVentaEspera = 'green'
    self.parametros   = opts.parametros;
    
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.rutaAutorizada        = ""
    self.autorizarBorrado      = 0
    self.comboCondicionPagos   = []
    self.comboTipoDocumentos   = []
    self.tipoCedulas               = {data:[]}
    self.comboTipoDocumentoExonerados   = []
    self.subTotalGeneral       = 0
    self.codigoBarraFueraPantalla = ""
    self.totalDescuentos       = 0
    self.totalImpuesto         = 0
    self.totalImpuesto1         = 0
    self.montoExoneracion     = ""
    self.montoExoneracion1     = ""
    self.pesoPrioridad =  0
    self.numeroLinea =0
    self.cantArticulos =0
    self.bloqueoFactura = 0;
    self.precioUltimo = ""
    self.tipoCambio = {
        total:0,
        totalCompra:0,
        id:null
    }
    self.validarRolCommand = {
        usuarioSistema : "",
        claveSistema:""
    }
    self.itemEliminar = {}
    self.descripcionArticulo = ""
    self.factura                = {
        id:null,
	   fechaCredito:null,
	   fechaEmision:null,
	   condicionVenta:"",
       codigoActividad:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalComprobante:0,
	    totalEfectivo:0,
        totalTarjeta:0,
        totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
	    codigoMoneda:"",
	    estado:1,
	    cliente:{
            id:null,
            nombreCompleto:""
        },
	    vendedor:{
            id:null,
            nombreCompleto:""
        }

    }
    self.cantidadEnterFacturar =0
    self.item                  = null;
    self.articulo              = null;
    self.articulos             = {data:[]}
    self.detalleFactura        = {data:[]}
    self.cliente               = {
         id:null,
        nombreCompleto:"",
        cedula:""
    }
    self.vendedor              = {
        id:null,
        nombreCompleto:"",
        cedula:""
    };
    self.facturas_espera       = {data:[]}
    self.articulos_uso_interno      = {data:[]}
    self.informacion_tabla             = []
    self.informacion_tabla_articulo    = []
    self.informacion_tabla_clientes    = []
    self.idiomaDataTable               = {}
    self.mostrarFormularioPago         = false
    self.mostarParaCrearNuevaFactura   = true
    self.mostrarCamposIngresoContado   = true
    self.mostrarReferencias            = false
    self.todasProvincias               = {data:[]}
    self.todosCantones                 = {data:[]}
    self.todosDistritos                = {data:[]}
    self.todosBarrios                  = {data:[]}
    self.estadosMag   = []
    self.clientes                  = {data:[]}
    self.cantones                      = []
    self.distritos                     = []
    self.barrios                       = []
    self.actividadesComerciales        = []
    self.mostrarListadoArticulos == false
    self.empresa              = {}
    self.subTotalGeneral               = 0
    self.totalDescuentos               = 0
    self.totalImpuesto                 = 0
    self.totalImpuesto1                 = 0
    self.montoExoneracion              = ""
    self.montoExoneracion1             = ""
    self.totalComprobante              = 0
    self.primeraVezBilleteClick = false
    self.totalCambioPagar              = 0
    self.semaforo_carga_articulos = false;
    self.mostarAbrirCajon = true
    self.informacionAbrirCajon = "."
    self.soloParaChinos = false
    self.totalGananciaByProducto = 0
    self.totalPesoByFactura = 0
    self.ultimoArticulo ={
        descripcion:"",
        precioPublico:0
        }
    self.rol = {
      rolAdministrador:0
    }
    self.tamanoLetra = "tamanoLetraConBanco"
    self.labelTotales = "labelTotalesConBanco"
    self.campoTotales = "campoTotalesConBanco"
    self.mostrarBotonAgregarCliente = false
    self.actividadComercial = {
        codigo:"",
        descripcion:""
    }
    self.facturaImpresa={
        cliente:{
            cedula:""
        },
        nombreFactura:"",
        correoAlternativo:"",
        correoElectronico:"",
        nota:"",
        referenciaNumero:"",
        subTotalGeneralSTR:"",
        totalDescuentos:0,
        totalDescuentosSTR:"",
        totalComprobanteSTR:"",
        totalCambioPagarSTR:"",
        tipoCambio:0,
        tipoCambioSTR:"",
        estado:0,
        empresa:{
            noFacturaElectronica:"",

        },
        tipoDoc:"",
        consecutivoProforma:"",

    }
    self.transaccion = false
    self.on('mount',function(){
        __ObtengoTipoCambio()
        $("#formularioFactura").validate(reglasDeValidacionFactura());
         self.informacion_tabla_clientes =__informacionData_formato_cliente()
         self.tipoCambio.total = __getTipoCambioTotal()
         self.tipoCambio.totalCompra = __getTipoCambioCompra()
         self.update()
        __informacionData_vendedores()
        __InicializarTabla('.tableListaCliente')
        __InicializarTabla('.tableListaInventario')
        __InicializarTabla('.tableListaVendedor')
        __InicializarTabla('.tableListarFacturasDia')
        agregarInputsCombos_Articulo()
        __ListaFacturasEnEspera()
        __comboCondicionPago()
        __RolAdministrador()
       cargaBilletes()
       __InformacionDataTableDia()
       __ListaActividadesComercales()
       __ListaDeVendedores()
       __agregarArticulos()
       _Empresa()
       __ComboTipoDocumentoExonerados()
        getPosicionInputCodigo()
         __bajarPDF()
          __reimprimir()
          __seleccionarClientes()
        $(".nota").attr("maxlength", 80);
        $('.datepickerFechaCredito').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-0d',
              todayHighlight:true,
            }
        );
        var xTriggered = 0;
        $( "#codigo" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
                lecturaCodigo($('.codigo').val())
           }
           if(event.which == 107){
            __SumarConTecla(event)
            }
             if(event.which == 109){
            __RestarConTecla(event)
            }
            if(event.which == 111){
                if(!$('#modalCambiarCantidad').is(':visible')){
                    if(!$('#modalFacturasDia').is(':visible')){
                    $(".codigo").val("")
                    seguridadCambiarPrecioLinea()}
                    return
                }else{
                    if($('#modalFacturasDia').is(':visible')){
                        return
                    }else{
                        $(".codigo").val("")
                        event.preventDefault()

                    }
                    return
                }
            }
        });

    $( "#cambiarprecioArticuloDetalle" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
               __cambiarElPrecioModalDetalle()
           }
        });
        $( "#claveSistema" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
               __validarRolAdministrador('#formularioModalRolUsuario','validarRolAdministradorAjax.do');
           }
        });

        $( "#precioAcambiar" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
               __AplicarCambioPrecioBD()
           }
        });
        $( "#cambiarCantidadArticulo" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
               cambiarCantidadModal()
           }
        });
        $( "#aplicarDescuento" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
               aplicarDescuentoEnter()
           }
        });

        $.fn.delayPasteKeyUp = function(fn, ms)
        {
            var timer = 0;
            $(this).on("propertychange input", function()
            {
                clearTimeout(timer);
                timer = setTimeout(fn, ms);
            });
        };
        getTipoCambioDolar()
        var retrievedObject = JSON.parse(localStorage.getItem('DetallesNueva'));
        if(retrievedObject != null){
            self.detail = retrievedObject
            var facturaObject = JSON.parse(localStorage.getItem('facturaNueva'));
            self.factura = facturaObject
            var clienteObject = JSON.parse(localStorage.getItem('cliente'));
            self.cliente = clienteObject
            self.update()
            __calculate()
        }
         window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
             actualizaElPlazoDiasCredito();
             __Teclas(evento.keyCode,evento)
            disableF5(evento);
        }, false );

    window.addEventListener( "mouseover", function(evento){
        actualizaElPlazoDiasCredito();
    }, false );
    window.addEventListener( "mouseout", function(evento){
        actualizaElPlazoDiasCredito();
    }, false );
    window.addEventListener( "mousedown", function(evento){
        actualizaElPlazoDiasCredito();
    }, false );
    window.addEventListener( "click", function(evento){
        actualizaElPlazoDiasCredito();
        teclamodal(evento);
    }, false );

    })
__CambiarDescripcion(e){
   self.item = e.item; 
   self.update()
   if(self.item.codigo =="8888"){
        return true
    } 
  
    $('#modalCambiarDescripcion').modal({backdrop: 'static', keyboard: true}) 
    $('#modalCambiarDescripcion').on('shown.bs.modal', function () {
        $( ".cambiarDescripcionArticulo" ).val(self.item.descripcion)      
        $('.cambiarDescripcionArticulo').focus()
        $('.cambiarDescripcionArticulo').select()
    })



}
function  __cambiarDescripcionDetalleModal(){
    var descripcion = $(".cambiarDescripcionArticulo").val();
    self.item.descripcion = descripcion
    self.update()
    $(".cambiarDescripcionArticulo").val(null);
    $('#modalCambiarDescripcion').modal('hide') 

  }
/**
* Cambiar el precio del detalle de la factura
**/
__cambiarDescripcionDetalle(e){
    __cambiarDescripcionDetalleModal()
}
/**&&  !$('#modalCambiarDescripcion').is(':visible')
*Cambiar precio del producto
**/
__CambiarPrecioFactura(e){
   self.item = e.item; 
   if(self.item.codigo =="8888"){
        return true
    } 
   self.update()
   $('#modalCambiarPrecioDetalle').modal({backdrop: 'static', keyboard: true}) 
   $('#modalCambiarPrecioDetalle').on('shown.bs.modal', function () {
       $( "#cambiarprecioArticuloDetalle" ).val( e.item.precioUnitario)
       $( "#cambiarprecioArticuloDetalle" ).focus()
       $( "#cambiarprecioArticuloDetalle" ).select()
   })

}

/**
* Cambiar el precio del detalle de la factura
**/
__cambiarElPrecioDetalle(){
    __cambiarElPrecioModalDetalle()
}

function __cambiarElPrecioModalDetalle(){
    var precio = $(".cambiarprecioArticuloDetalle").val();
     self.item.precioUnitario = __valorNumerico(precio);
     self.update()
    agregarPrecioAlDetalleFactura(precio)

}

/**
* Cambiar el precio en el detalle
**/
function agregarPrecioAlDetalleFactura(precio){
       //factura.js
    self.item = ActualizarLineaDEtalle(self.item) 
    self.update()
    aplicarCambioLineaDetalle() 
    $(".cambiarprecioArticuloDetalle").val(null);
    $('#modalCambiarPrecioDetalle').modal('hide') 
}

__EntradaDinero(){
  modalEntradaSalidaDinero(1)
}

__SalidaDinero(){
    modalEntradaSalidaDinero(2)
}

function modalEntradaSalidaDinero(tipo){
 var parametros = {
        tipo:tipo,
    }
    riot.mount('entrada-salida',{parametros:parametros});
}

__AplicarCambioPrecioUltimoArticulo(){
    __AplicarCambioPrecioBD()
}

__RegresarInputCodigo(){
    $(".codigo").val("")
    $('#modalCambiarPrecio').modal('hide')
    getPosicionInputCodigo()
}

__RegresarInputSeguridad(){
    $(".codigo").val("")
    $('#modalRolUsuario').modal('hide')
    getPosicionInputCodigo()
}

function __ObtengoTipoCambio(){
    var tempTipoCambio =__getTipoCambioCompra()
    if(tempTipoCambio == null){
       getTipoCambioDolar()
    }else{
        self.tipoCambio.total = __getTipoCambioTotal()
        self.tipoCambio.totalCompra = __getTipoCambioCompra()

    }
    self.update()

}

function __SetUltimoArticuloIngresado(){
   localStorage.setItem('ultimoArticulo', JSON.stringify(self.articulo));
}

function __getUltimoArticuloIngresado(){
    return JSON.parse(localStorage.getItem('ultimoArticulo'));
}

function __DeleteUltimoArticuloIngresado(){
    localStorage.removeItem('ultimoArticulo');
}

function __SetUltimoItemIngresado(item){
   localStorage.setItem('ultimoItem', JSON.stringify(item));
}

function __getUltimoItemIngresado(){
    return JSON.parse(localStorage.getItem('ultimoItem'));
}

function __DeleteUltimoItemIngresado(){
    localStorage.removeItem('ultimoItem');
}

function teclamodal(e){
    if ($('#modalInventario').is(':visible')) {
        $('.precioventa').focus()
    }
    if (!$('#modalFacturasDia').is(':visible') &&  !$('#modalClientes').is(':visible')
                        &&  !$('#modalCambiarCantidad').is(':visible') &&  !$('#modalCambiarDescuento').is(':visible')
                        &&  !$('#modalAgregarClienteNuevo').is(':visible') &&  !$('#modalCambiarDescuento').is(':visible')
                        &&  !$('#modalInventario').is(':visible')  &&  !$('#modalAgregarClienteNuevo').is(':visible')
                        &&  !$('#modalCambiarPrecio').is(':visible') &&  !$('#modalCambiarPrecioDetalle').is(':visible') &&  !$('#modalCambiarDescripcion').is(':visible')
                        && !$('#modalEntradaSalidaDinero').is(':visible') && !$('#modalRolUsuario').is(':visible')  
                    ) {
                        if(e.target.id != 'selectListaPrecios' && e.target.id != 'selectActividadComercial' && e.target.id != 'botonRestar'
                          && e.target.id != 'botonSumar'   && e.target.id != 'botonCambiarPrecio' && e.target.id != 'botonEntradaDinero'
                          && e.target.id != 'descripEntradaSalidaDinero' ){
                           if(VerificarSiEstaPrecioOrCodigo(e)){
                              getPosicionInputCodigo();
                           }
                            

                        }
                        
                    }
       
    
}
/**Verifica si algo se escribio en precioventa*/
function VerificarSiEstaPrecioOrCodigo(e){
    var resultadoCodigo = true
    if(e.target.id == 'codigo' ){
        var resultado = __valorNumerico($('.precioVenta').val())
         if(resultado  > 0 ){
            resultadoCodigo = false
        }

    }
    return resultadoCodigo

}

    function disableF5(e) {

        if ((e.which || e.keyCode) == 116) e.preventDefault();
        if ((e.which || e.keyCode) == 114) e.preventDefault();
        if ((e.which || e.keyCode) == 112) e.preventDefault();
        if ((e.which || e.keyCode) == 117) e.preventDefault();
        if(e.target.id != 'codigo' && e.target.id != 'precioVenta' && e.target.id != 'nota'
           && e.target.id != 'correoAlternativo' && e.target.id != 'nombreFactura' &&
           e.target.id != 'totalEfectivo' && e.target.id != 'totalTarjeta' &&
           e.target.id != 'totalBanco' && e.target.id != 'plazoCreditoL' && e.target.id != 'fechaCredito'
           && e.target.id != 'aplicarDescuento' && e.target.id != 'cambiarCantidadArticulo'
           && e.target.id != 'cedula' && e.target.id != 'nombreCompleto'
           && e.target.id != 'codigoArt' && e.target.id != 'descArticulo'
           && e.target.id != 'correoElectronico' && e.target.id != 'codigoPais'
           && e.target.id != 'telefono'  && e.target.id != 'descripEntradaSalidaDinero' ){
            if (self.mostrarFormularioPago == false ){
               if ((e.which || e.keyCode) == 13) {
                    if (!$('#modalFacturasDia').is(':visible') &&  !$('#modalClientes').is(':visible')
                        &&  !$('#modalCambiarCantidad').is(':visible') &&  !$('#modalCambiarDescuento').is(':visible')
                        &&  !$('#modalAgregarClienteNuevo').is(':visible') &&  !$('#modalCambiarDescuento').is(':visible')
                        &&  !$('#modalInventario').is(':visible')  &&  !$('#modalAgregarClienteNuevo').is(':visible')
                        &&  !$('#modalCambiarPrecio').is(':visible') && !$('#modalCambiarDescripcion').is(':visible') && !$('#modalEntradaSalidaDinero').is(':visible') 
                        && !$('#modalRolUsuario').is(':visible')  
                    ) {
                        getPosicionInputCodigo()
                     }

                }

            }
        }

        }

function __AplicarCambioPrecioBD(){
    var parametros = __getUltimoArticuloIngresado();
    if(parametros == null){
        getPosicionInputCodigo()
        return
    }
    parametros.precioPublico = $('.precioAcambiar').val()

    $.ajax({
        type : "POST",
        dataType : "json",
        data : {id:parametros.id,precioPublico:parametros.precioPublico},
        url : 'CambiarPrecioArticulo.do',
        success : function(data) {
            if (data.status != 200) {
              	serverMessageJson(data);
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message)
                }
            } else {
                mensajeToasExito(data.message)
                self.articulo  = null
                self.update()
                if (data.message != null && data.message.length > 0) {
                    self.articulo =null
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.articulo  = modeloTabla
                        self.update()
                        __SetUltimoArticuloIngresado()
                        aplicarLineaFacturaCambioPrecio()
                        $('#modalCambiarPrecio').modal('hide')
                        getPosicionInputCodigo()
                    });
                }

	        }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

function getPosicionInputCodigo(){
    $('.precioVenta').val(null)
    $('.codigo').val(null)
    $('.codigo').focus()
}

this.__ConsultarHaciendaBlur = function(){
    getClienteHacienda()

}.bind(this)

__ConsultarHacienda(e){
     if (e.keyCode != 13) {
        return;
    }
    getClienteHacienda()
}

function getClienteHacienda(){
     var cedula = $('#cedula').val()
    if(stringVacio($(".cedula").val()) == false){
       return
    }
    $('.correoElectronico').val('')
    self.clienteHacienda= {
        nombre:"",
        tipoIdentificacion:"",
        regimen:{
            codigo:"",
            descripcion:""
        },
        actividades:[]
    }
    self.update()
    $.ajax({
        url: "clienteHacienda.do",
        datatype: "json",
        data: {cedula:cedula},
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeErrorTiempo( "Cedula no se encuentra registrada en Registro Nacional de Costa Rica" )
                    __listadoTipoCedulas()
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.clienteHacienda = modeloTabla
                        self.mostrarBotonAgregarCliente = true
                        self.update()
                        __listadoTipoCedulas()
                        $('#nombreCompleto').val(self.clienteHacienda.nombre)

                    });
                }
            }

        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });

}

function __listadoTipoCedulas(){
    self.tipoCedulas               = {data:[]}
    self.update()
    if(self.clienteHacienda.tipoIdentificacion == "01") {
        self.tipoCedulas.data.push({
            valor:"01",
            descripcion:$.i18n.prop("tipo.cedula.fisica")
        })
    }
    if(self.clienteHacienda.tipoIdentificacion == "02") {
        self.tipoCedulas.data.push({
            valor:"02",
            descripcion:$.i18n.prop("tipo.cedula.juridica")
        })
    }
    if(self.clienteHacienda.tipoIdentificacion == "03" ){
        self.tipoCedulas.data.push({
            valor:"03",
            descripcion:$.i18n.prop("tipo.cedula.dimex")
        })
    }
    if(self.clienteHacienda.tipoIdentificacion == "04" ){
     self.tipoCedulas.data.push({
        valor:"04",
        descripcion:$.i18n.prop("tipo.cedula.nite")
    })
    }
    if(self.tipoCedulas.data.length == 0){
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

    }
    self.update()
}

__ClienteNuevo(){
    __ComboEstadosMag()
    __nuevoCliente()
}

/**
*  Crear el combo de estados
**/
function __ComboEstadosMag(){
    self.estadosMag =[]
    self.update()
    self.estadosMag.push({
        codigo: 0,
        descripcion:$.i18n.prop("combo.estadoMag.inactivo")
     });

    self.estadosMag.push({
        codigo: 1,
        descripcion:$.i18n.prop("combo.estadoMag.agro")
     });
    self.estadosMag.push({
        codigo: 2,
        descripcion: $.i18n.prop("combo.estadoMag.pesca")
     });
     self.update();

}

function __nuevoCliente(){
    $("#formularioAgregarCliente").validate(reglasDeValidacionClienteNuevo());
    $("#nombreCompleto").attr("maxlength", 80);
    $("#cedula").attr("maxlength", 20);
    $("#correoElectronico").attr("maxlength", 80);
    $("#telefono").attr("maxlength", 8);
    $('#codigoPais').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
    $('#cedula').mask('000000000000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
    $('.telefono').mask('00000000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	})

    $("#cedula").val(null)
    $("#nombreCompleto").val(null)
    $("#correoElectronico").val(null)
    $("#codigoPais").val(506)
    $("#telefono").val(null)

    $('#modalAgregarClienteNuevo').modal({backdrop: 'static', keyboard: true})

    $('#modalAgregarClienteNuevo').on('shown.bs.modal', function () {
        $('#cedula').select()
        $("#cedula").focus()
     });

}

this.__AplicarCambioPrecio = function(){
   seguridadCambiarPrecioLinea()
}.bind(this)

function seguridadCambiarPrecioLinea(){
    self.autorizarBorrado = 3
    self.update()
    if(self.rol.rolAdministrador == 0){
        self.rutaAutorizada = '';
        self.update()
        inputCursorUsuario()
    }else{
         __AplicarPrecioLinea()
    }
}

function __AplicarPrecioLinea(){
    self.ultimoArticulo = __getUltimoArticuloIngresado()
    self.update()
    if(self.ultimoArticulo == null){
        return
    }
    if(self.ultimoArticulo.tipoCodigo == '04' ){
        return

    }

    $('#modalCambiarPrecio').modal({backdrop: 'static', keyboard: true})
    $('#modalCambiarPrecio').on('shown.bs.modal', function () {
        $(".precioAcambiar").val( self.ultimoArticulo.precioPublico)
        $(".precioAcambiar").focus()
        $(".precioAcambiar").select()
    })

}

var reglasDeValidacionClienteNuevo = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			cedula : {
				required : true,
                maxlength:12,
                minlength:9,
			},

			nombreCompleto : {
				required : true,
                maxlength:80,
                minlength:1,
                lettersOnly : true
			},
			correoElectronico : {
                required : true,
                maxlength:60,
                minlength:1,
                email:true
			},
            codigoPais : {
                required : true,
                minlength:3,

			}
		},
		ignore : []

	});
	return validationOptions;
};

__regresarClienteNuevo(){
    $('#modalAgregarClienteNuevo').modal('hide')
    getPosicionInputCodigo()
}

__AplicarAgregarCliente(){
     if ($("#formularioAgregarCliente").valid()) {
        aplicarCreacionClienteNuevo()
    }else{
        mensajeAdvertencia("Error Faltan datos requeridos")
        return true
    }
}

function aplicarCreacionClienteNuevo(){
    var formulario = $("#formularioAgregarCliente").serialize();
    swal({
        title: '',
        text: $.i18n.prop("cliente.mensaje.alert.agregar"),
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
        $.ajax({
            type : "POST",
            dataType : "json",
            data : formulario,
            url : 'AgregarClienteAjax.do',
            success : function(data) {
                if (data.status != 200) {
                   	serverMessageJson(data);
                    if (data.message != null && data.message.length > 0) {
                        mensajeAdvertencia(data.message)
                    }
                } else {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.cliente = modeloTabla
                       self.update()
                    });
                   	serverMessageJson(data);
                    mensajeToasExito(data.message)
                    seleccionarEfectivo()
                }
            },
            error : function(xhr, status) {
                console.log(xhr);
                mensajeErrorServidor(xhr, status);
            }
        });
     }
    })

}
__ActualizarPlazoCredito(){
    actualizaElPlazoDiasCredito();
}

__AsignarActividad(e){
    BuscarActividadComercial()
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
           self.actividadComercial.descripcion =  modeloTabla.descripcion
            self.actividadComercial.codigo =  codigo
            self.factura.codigoActividad = codigo
            self.update()
        }

    })
}

function actualizaElPlazoDiasCredito(){
     if(!$('#modalFacturasDia').is(':visible') && !$('#modalEntradaSalidaDinero').is(':visible')  ){
        var valor = $('.selectFechaCredito').val()
        if(valor ==null || valor ==""){
            return true
    }
    var fechaReal = new Date();
    var formatoFecha = formatoFechaF(fechaReal);
    var fecha1 = moment(formatoFecha);
    var fecha2 = moment($('.selectFechaCredito').val());
    self.factura.plazoCredito = fecha2.diff(fecha1, 'days');
    self.update();

     }

}

function __RolAdministrador(){
    $.ajax({
        url: "RolUsuarioAjax.do",
        datatype: "json",
        global: false,
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message);
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.rol = modeloTabla
                       self.update()
                    });
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);

        }
    });

}

__SeguridadVentas(){
     __validarRolAdministrador('#formularioModalRolUsuario','validarRolAdministradorAjax.do');

}

function __validarRolAdministrador(formulario,url){
    var resultado = false;
    if ($(formulario).valid()) {
        var formulario = $(formulario).serialize();
        $.ajax({
          type : "POST",
          sync: true,
          dataType : "json",
          data : formulario,
          url : url,
          success : function(data) {
             if (data.status != 200) {
            	 serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                	 mensajeAdvertencia("No autorizado");
                 }
                return resultado;
             } else {
            	 var modelTabla = {};
            	 data.listaObjetos.forEach(function(modelo) {
            		 modeloTabla = modelo;
            	 })
               	if(modeloTabla.aceptacion === 1){
                    self.validarRolCommand = {
                        usuarioSistema : "",
                        claveSistema:""
                    }
                    self.update()
               	    $('#modalRolUsuario').modal('hide')
                    $('.modal-backdrop').remove();
                    if(self.autorizarBorrado == 0){
                        permitirModal()
                    }
                    if(self.autorizarBorrado == 1){
                        self.autorizarBorrado = 0
                        self.update()
                        eliminarDetalle()
                    }
                    if(self.autorizarBorrado == 2){
                        self.autorizarBorrado = 0
                        self.update()
                        refrescarPagina()
                    }

                    if(self.autorizarBorrado == 3){
                        self.autorizarBorrado = 0
                        self.update()
                        __AplicarPrecioLinea()
                    }

                    return true;
               	}else{
                    self.rutaAutorizada = '';
                    self.update()
                    mensajeAdvertencia("No autorizado");
                    return true;
                }

             }
          },
          error : function(xhr, status) {
             console.log(status);
             mensajeErrorServidor(xhr, status);
          }
        });
    }

}
function permitirModal(){
    $(self.rutaAutorizada).modal({backdrop: 'static', keyboard: true})
        $(self.rutaAutorizada).on('shown.bs.modal', function () {
        $(self.inputCursorModal).val()
        $(self.inputCursorModal ).focus()
        $(self.inputCursorModal).select()
    })
}

_clickEfectivo(){
    $('.totalEfectivo').select()
    $(".totalEfectivo").focus()
}

_SeleccionarTarjeta(){
    $('.totalTarjeta').select()
    $(".totalTarjeta").focus()
}
_SeleccionarBanco(){
    $('.totalBanco').select()
    $(".totalBanco").focus()
}

_ReimprimirFactura(){
    reimprimirFacturaEnMomento()

}
function reimprimirFacturaEnMomento(){

  if(self.facturaReimprimir ==null){
    var retrievedObject = JSON.parse(localStorage.getItem('facturaReimprimir'));
    if(retrievedObject != null){
       self.facturaReimprimir = retrievedObject
       var clienteObject = JSON.parse(localStorage.getItem('cliente'));
       self.cliente = clienteObject
       self.update()
    }
  }
  if(self.facturaReimprimir ==null){
      return
  }
  consultaFactura(self.facturaReimprimir,0)
  getPosicionInputCodigo()
}

function _Empresa(){
     $.ajax({
        url: "ParametrosEmpresaAjax.do",
        datatype: "json",
        global: false,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message);
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.empresa =   modeloTabla
                       if(self.empresa.abrirSinComanda == 0 && self.empresa.abrirConComanda == 0){
                         self.mostarAbrirCajon = false
                       }
                       if(self.empresa.pantChino == 1){
                           self.soloParaChinos = true
                           self.tamanoLetra = "tamanoLetra"
                           self.labelTotales = "labelTotales"
                           self.campoTotales = "campoTotales"
                        }else{
                            self.tamanoLetra = "tamanoLetraConBanco"
                            self.labelTotales = "labelTotalesConBanco"
                            self.campoTotales = "campoTotalesConBanco"
                        }
                       self.update()
                       __ComboTipoDocumentos(0)
                    });
                }
            }

        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });

}

__LimpiarFormulario(){
    __SeguridadLimpiar()
}

function __SeguridadLimpiar(){
    self.autorizarBorrado = 2
    self.update()
    if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        if(self.detail.length > 0){
            self.rutaAutorizada = '';
            self.update()
            inputCursorUsuario()
        }else{
           refrescarPagina()
        }

    }else{
        refrescarPagina()
    }

}

function inputCursorUsuario(){
    $('#modalRolUsuario').modal({backdrop: 'static', keyboard: true})
    $('#modalRolUsuario').on('shown.bs.modal', function () {
        $("#usuarioSistema").val("")
        $("#claveSistema").val("")
        $(".usuarioSistema").focus()
        $(".usuarioSistema").select()
    })

}

var reglasDeValidacionFactura = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {

             nota:{
                 maxlength:255,
             },
             direccion:{
                 maxlength:255,
             }
		},
		ignore : []

	});
	return validationOptions;
};

_ListaFacturasDia(){
   ListadoFacturasDelDia()
}

function ListadoFacturasDelDia(){
      
      $(".tableListarFacturasDia").dataTable().fnClearTable();
        __InicializarTabla('.tableListarFacturasDia')
        $.ajax({
            url: "ListarFacturasDelDiaAjax.do",
            datatype: "json",
            global: false,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTableDia();
                    loadListar(".tableListarFacturasDia",idioma_espanol,self.formato_tabla_dias,result.aaData)
                    agregarInputsCombos_Facturas_Dias();
                    ActivarEventoFiltro(".tableListarFacturasDia")
                  //  $('#modalFacturasDia').remove()
                    $('#modalFacturasDia').modal('show')
                    // __reimprimir()
                    // __bajarPDF()
                     
                }else{
                    __InformacionDataTableDia();
                     agregarInputsCombos_Facturas_Dias();
                }
            },
            error: function (xhr, status) {
                mensajeErrorServidor(xhr, status);
                console.log(xhr);
            }
        });
        
}

function loadListar(table,idioma,formatoTabla,data){
	$(table).DataTable().destroy();
        $(table).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'flrtip',
        "order": [1, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,

        "columns": formatoTabla,
    });
    $(table).dataTable().fnClearTable();
    $(table).dataTable().fnAddData(data);
}

function __InformacionDataTableDia(){
    self.formato_tabla_dias = [
                               {'data' : 'id' ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
                               },

                               {'data' :'fechaEmisionSTR'   ,"name":"fechaEmisionSTR"    ,"title" : $.i18n.prop("factura.fecha.emision")     ,"autoWidth" :true ,
                               },
                               {'data' :'numeroConsecutivo'                    ,"name":"numeroConsecutivo"                     ,"title" : $.i18n.prop("factura.documento")   ,"autoWidth" :true ,
                                   "render":function(numeroConsecutivo,type, row){
									    return __TipoDocumentos(numeroConsecutivo,row)
	 							    }
                               },

                               {'data' :'condicionVentaSTR' ,"name":"condicionVentaSTR","title" : "Pago"   ,"autoWidth" :true },
                                {'data' :'cliente'  ,"name":"cliente"  ,"title" : $.i18n.prop("factura.cliente")   ,"autoWidth" :true ,
                                   "render":function(cliente,type, row){
									    return __ClienteNombreFactura(row);
	 							    }
                               },
                               {'data' :'totalDescuentosSTR'  ,"name":"totalDescuentosSTR" ,"title" : $.i18n.prop("factura.linea.detalle.descuento")  ,"autoWidth" :true },
                               {'data' :'totalComprobanteSTR' ,"name":"totalComprobanteSTR" ,"title" : $.i18n.prop("factura.total") ,"autoWidth" :true },
	      		            ];
    self.update();

}

function __ClienteNombreFactura(row){
    var nombreFactura  = row.nombreFactura == null?"":row.nombreFactura
    var nombreCliente  = row.cliente  == null?"":row.cliente
    if(nombreCliente == null){
        return ""
    }
    if(row.cedula != "999999999999"){
       return nombreCliente.length > 50 ?nombreCliente.substring(0,35) + "..." :nombreCliente;
    }else {
        return nombreFactura.length > 50 ?nombreFactura.substring(0,35) + "..." :nombreFactura;
    }

}

function __Opciones(){
    let menu = '<div class="dropdown">' 
	menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
	menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
	menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    menu += '<li><a href="#"  title="Imprimir" class="  btnReimprimir" >Imprimir</a></li>'
    menu += '<li><a href="#"  title="Bajar PDF" class="  btnBajarPdf" >Bajar PDF</a></li>'
  
  return  menu ;
}

/**
*imprimir
**/
function __bajarPDF(){
	$('#tableListarFacturasDia').on('click','.btnBajarPdf',function(e){
		var table = $('#tableListarFacturasDia').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
       //location.href = "generaFacturaPDF?idFactura=" + data.id
       var parametros = {
            direccion: "generaFacturaPDF?idFactura=" + data.id,
            stylemodal: "modal-xl"
        }
        riot.mount('view-pdf', { datos: parametros });

	});
}


function __reimprimir(){
	$('#tableListarFacturasDia').on('click','.btnReimprimir',function(e){
		var table = $('#tableListarFacturasDia').DataTable();
		if(table.row(this).child.isShown()){

	       var data = table.row(this).data();
	    }else{
	       var data = table.row($(this).parents("tr")).data();
	    }
        consultaFactura(data,1)
	});
}

function consultaFactura(data,tipoImpresion){
    var parametros = {
        factura:data,
        facturaDia:tipoImpresion
    }
    riot.mount('ptv-imprimir',{parametros:parametros});
    return
}

var reglasDescuentoAplicar = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			aplicarDescuento : {
			    numeroMayorIgualCero:true,
			}
		},
		ignore : []

	});
	return validationOptions;
};

__CambiarDescuento(e){
    $("#aplicarDescuento").attr("maxlength", 7);
     $("#formularioDescuento").validate(reglasDescuentoAplicar());
    self.item = e.item;
    self.rutaAutorizada = '';
    self.update()
    if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        self.rutaAutorizada = '#modalCambiarDescuento';
        self.inputCursorModal = "#aplicarDescuento"
        self.update()
        inputCursorUsuario()
   }else{
        $('#modalCambiarDescuento').modal({backdrop: 'static', keyboard: true})
        $('#modalCambiarDescuento').on('shown.bs.modal', function () {
            $( "#aplicarDescuento" ).focus()
            $( "#aplicarDescuento" ).val(null)
        })
    }
}

__CambiarCantidad(e){
   var cantidad = e.currentTarget.value;
   self.item = e.item;
   self.rutaAutorizada = '';
   self.update()
   if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        self.rutaAutorizada = '#modalCambiarCantidad';
        self.inputCursorModal = "#cambiarCantidadArticulo"
        self.update()
        inputCursorUsuario()
   }else{
        $('#modalCambiarCantidad').modal({backdrop: 'static', keyboard: true})
        $('#modalCambiarCantidad').on('shown.bs.modal', function () {
            $( "#cambiarCantidadArticulo" ).val(cantidad)
            $( "#cambiarCantidadArticulo" ).focus()
            $( "#cambiarCantidadArticulo" ).select()
        })
   }
 }

__TotalDeEfectivoAPagar(e){
    self.totalCambioPagarSTR = 0
    self.factura.totalEfectivo = __valorNumerico(e.target.value)
    if(self.factura.totalEfectivo ==0 ){
        self.cantidadEnterFacturar = 0
    }
    self.update()
    _calculoEnterPago()
}

__TotalDeTarjetaAPagar(e){
    self.factura.totalTarjeta = __valorNumerico(e.target.value)
    if(self.factura.totalTarjeta ==0 ){
        self.cantidadEnterFacturar = 0
    }
    self.update()
    _calculoEnterPago()
}

__TotalDeBancoAPagar(e){
    self.factura.totalBanco = __valorNumerico(e.target.value)
    if(self.factura.totalBanco ==0 ){
        self.cantidadEnterFacturar = 0
    }
    self.update()
    _calculoEnterPago()
}

__CalculaCambioAEntregarOnblur(e){
    _calculoEnterPago()
}

__CalculaCambioAEntregarKeyPress(e){
    if (e.keyCode == 13) {
        __Calcular()
    }
}

function __Calcular(){
       _calculoEnterPago()
       if(self.totalCambioPagar == 0){
            if(self.cantidadEnterFacturar >= 1){
               __EnterFacturar()
            }else{
                self.cantidadEnterFacturar = self.cantidadEnterFacturar + 1
                self.update()
            }
        }else{
            var totalFactura   = __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2))
            if(self.totalCambioPagar > 0 || self.totalCambioPagar > totalFactura || self.totalCambioPagar == totalFactura  ){
                if(self.cantidadEnterFacturar >= 1){
                __EnterFacturar()
                }else{
                    self.cantidadEnterFacturar = self.cantidadEnterFacturar + 1
                    self.update()
               }
            }else{
                self.cantidadEnterFacturar = 0
                self.update()

            }
        }
}
function _calculoEnterPago(){
        var sumaMontosEntregadosParaCambios  = __valorNumerico($('.totalTarjeta').val())
        sumaMontosEntregadosParaCambios += __valorNumerico($('#totalBanco').val())
        sumaMontosEntregadosParaCambios += __valorNumerico($('.totalEfectivo').val())
        if(sumaMontosEntregadosParaCambios == 0){
            self.factura.totalCambioPagar =  __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2)) * -1
            self.totalCambioPagar =  __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2)) * -1
            self.totalCambioPagarSTR = formatoDecimales(self.totalCambioPagar,2)
            self.update()
            return
        }
        self.factura.totalCambioPagar = 0
        var totalEntregado = __valorNumerico(redondeoDecimales(sumaMontosEntregadosParaCambios,2))
        var totalFactura   = __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2))
        totalEntregado     = __valorNumerico(totalEntregado)
        totalFactura       = __valorNumerico(totalFactura)
        self.factura.totalCambioPagar = totalEntregado - totalFactura
        self.factura.totalCambioPagar =__valorNumerico(self.factura.totalCambioPagar)
        self.totalCambioPagar = __valorNumerico(redondeoDecimales(self.factura.totalCambioPagar,2))
        self.totalCambioPagarSTR = formatoDecimales(self.totalCambioPagar,2)
        self.update()
}

function __EnterFacturar(){
    if(self.empresa.enterFacturar == 0){
        return
    }
    if(self.empresa.abrirSinComanda == 1 || self.empresa.abrirConComanda == 1 ){

      __OpcionAbrirCajon()
    }
    swal({
           title: '',
           text: $.i18n.prop("factura.mensaje.alert.enter"),
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:"Enter=" +$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',

    }).then(function (isConfirm) {
            if(isConfirm){
                 aplicarFactura(2)
            }
    }
        );
        self.cantidadEnterFacturar = 0
        self.update()
}

 __ListaDecodigos(){
     ListarCodigosArticulos()
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

__CargarFacturaEspera(e){
   __FacturaEnEspera(e.item)
}

__AplicarYcrearFactura(e){

 aplicarFactura(2)
}

__AplicarYcrearFacturaTemporal(e){

 __OpcionAbrirCajon()
 aplicarFactura(1)
}

function aplicarFactura(estado){
    if($("#tipoDoc").val() ==null){
        mensajeAdvertencia($.i18n.prop("Se presento inconveniente ,vuelva a presiona F8 Factura o F9 Proformas"))
        return
    }
    var limiteCredito = __valorNumerico(self.cliente.limiteCredito)
    var totalComprobante = self.factura.totalComprobante
    if(limiteCredito > 0){
        if(totalComprobante > limiteCredito ){
            sweetAlert("", "El Cliente ya no tiene saldo para credito,El Limite maximo es: " + limiteCredito + " \n    Colaborador por favor Solicite autorizacion con el administrador", "error");
            return
        }
    }
    if(self.detail.length == 0 ){
        mensajeAdvertencia($.i18n.prop("factura.alert.sin.detalles"))
        getPosicionInputCodigo()
        return
    }
    if($('#condicionVenta').val() == "02"  ){
        if($('#fechaCredito').val() == null || $('#fechaCredito').val() == 0){
           mensajeAdvertencia($.i18n.prop("factura.alert.fechaCredito"))
            return
        }else if($('#plazoCreditoL').val() < 0 || $('#plazoCreditoL').val() == null || $('#plazoCreditoL').val() == 0){
           mensajeAdvertencia($.i18n.prop("factura.alert.plazoCredito"))
           return
        }
    }else{

        if($("#tipoDoc").val() !="88"){
            if(estado == 2){
                if(__valorNumerico($('#totalTarjeta').val()) == 0 && __valorNumerico($('#totalBanco').val()) == 0 && __valorNumerico($('#totalEfectivo').val()) == 0){
                    mensajeAdvertencia($.i18n.prop("error.factura.monto.ingresado"))
                    return
                }
                var montoEntregado = __valorNumerico($('#totalTarjeta').val())  + __valorNumerico($('#totalBanco').val()) + __valorNumerico($('#totalEfectivo').val())
                montoEntregado = redondeoDecimales(__valorNumerico(montoEntregado),2)

                var resultado  = redondeoDecimales( __valorNumerico(self.factura.totalComprobante),2)
                if(__valorNumerico(resultado) > __valorNumerico(montoEntregado)  ){
                    mensajeAdvertencia($.i18n.prop("error.factura.monto.ingresado.es.menor.ala.venta"))
                    return
                }

                var tarjeta = __valorNumerico($('#totalTarjeta').val())
                var banco = __valorNumerico($('#totalBanco').val())
                if(tarjeta != 0 || banco !=0){
                    if(resultado != montoEntregado  ){
                        mensajeAdvertencia($.i18n.prop("error.factura.monto.tarjeta.banco.igual.venta"))
                    return

                    }
                }

            }
       }

    }
    // if(self.cliente.tipoMag > 0){
      //      retotalizarMAG(self.cliente.tipoMag == 1 || self.cliente.tipoMag == 2 ? 1:0 )                 
       // }
    crearFactura(estado)
}

__Limpiar(){
    __Init()
}

function __Init(){
    __DeleteUltimoItemIngresado()
    __DeleteUltimoArticuloIngresado()
    self.facturaImpresa={
        cliente:{
            cedula:""
        },
        nombreFactura:"",
        correoAlternativo:"",
        correoElectronico:"",
        nota:"",
        referenciaNumero:"",
        subTotalGeneralSTR:"",
        totalDescuentos:0,
        totalDescuentosSTR:"",
        totalComprobanteSTR:"",
        totalCambioPagarSTR:"",
        tipoCambio:0,
        tipoCambioSTR:"",
        estado:0,
        empresa:{
            noFacturaElectronica:"",

        },
        tipoDoc:"",
        consecutivoProforma:"",

    }
    self.bloqueoFactura = 0;
    self.transaccion = false
    self.precioUltimo = ""
    self.factura                = {
        id:null,
        codigoActividad:"",
	    fechaCredito:null,
	    fechaEmision:null,
	    condicionVenta:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalComprobante:0,
	    totalEfectivo:0,
        totalTarjeta:0,
        totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
        totalCambioPagar:0,
	    codigoMoneda:"",
	    estado:1,
	    cliente:{
            id:null,
            nombreCompleto:"",
        },
	    vendedor:{
            id:null,
            nombreCompleto:""
        }

    }
    self.totalPesoByFacturaSTR = ''
    self.totalPesoByFactura  =0
    self.totalGananciaByProducto =0
    self.primeraVezBilleteClick = false
    self.cantidadEnterFacturar = 0
    self.pesoPrioridad =  0
    self.numeroLinea =0
    self.cantArticulos =0
    self.mostrarListadoArticulos == false
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.comboCondicionPagos        = []
    self.comboTipoDocumentos   = []
    self.facturas_espera       = {data:[]}
    self.item                  = null;
    self.articulo              = null;
    self.descripcionArticulo   = ""
    self.precioUltimo = ""
    self.detalleFactura        ={data:[]}
    self.cliente               = {
         id:null,
        nombreCompleto:"",
        cedula:""
    }
    self.vendedor              = {
        id:null,
        nombreCompleto:"",
        cedula:""
    }
    self.informacion_tabla             = []
    self.informacion_tabla_articulo    = []
    self.informacion_tabla_clientes    = []
    self.idiomaDataTable               = {}
    self.mostrarFormularioPago         = false
    self.mostarParaCrearNuevaFactura    = true
    self.mostrarCamposIngresoContado   = true;
    self.subTotalGeneral               = 0
    self.totalDescuentos               = 0
    self.totalImpuesto                 = 0
    self.totalImpuesto1                 = 0
    self.montoExoneracion              = ""
    self.montoExoneracion1              = ""
    self.totalComprobante              = 0
    self.totalCambioPagar              = 0
    self.totalCambioPagarSTR           = 0
    self.update();
    $(".tableListarFacturasDia").dataTable().fnClearTable();
    __InicializarTabla('.tableListarFacturasDia')
    $('#condicionVenta').prop("selectedIndex", 0);
     $('#condicionVenta').prop("selectedIndex", 0);
    $('#tipoDoc').prop("selectedIndex", 0);
    $('.selectListaPrecios').prop("selectedIndex", 0);
    $(".nota").attr("maxlength", 80);
    $("#totalBanco").val(null)
    $(".totalTarjeta").val(null)
    $(".nombreFactura").val(null)
    $(".correoAlternativo").val(null)
    $(".totalEfectivo").val(null)
    $('.precioVenta').val(null)
    $("#plazoCreditoL").val(null)
    $("#nota").val(null)
    $("#fechaCredito").val(null)
    $('.datepickerFechaCredito').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-0d',
              todayHighlight:true,
            }
    );
    $("#cambiarCantidadArticulo").val(null)
    $("#aplicarDescuento").val(null)
    $('.selectActividadComercial').prop("selectedIndex", 0);
    __comboCondicionPago()
    __ComboTipoDocumentos(0)
    __ListaFacturasEnEspera()
    localStorage.setItem('DetallesNueva', JSON.stringify(self.detail));
    localStorage.setItem('facturaNueva', JSON.stringify(self.factura));
    localStorage.setItem('cliente', JSON.stringify(self.cliente));
    getPosicionInputCodigo()
}

function __FacturaEnEspera(factura){
     __Init()
    $.ajax({
        url: "ListarDetlleByFacturaAjax.do",
        datatype: "json",
        data: {idFactura:factura.id},
        method:"POST",
        success: function (data) {
            if(data.aaData.length > 0){
               cargarDetallesFacturaEnEspera(data.aaData,null,1)
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);

        }
    });
}

function seleccionarEfectivo(){

    $('#totalEfectivo').focus()
    $('#totalEfectivo').select()
    return

}

function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('YYYY-MM-DD ');
}

function crearFactura(estado){
    BuscarActividadComercial()
    if( self.factura.codigoActividad.length == 0 ){
      mensajeAdvertencia($.i18n.prop("error.factura.actividad.comercial.no.existe"))
      return
    }
      if (self.transaccion == true ){
        return false
    }
    if(self.factura.totalComprobante >= 20000000 && self.empresa.cedula != '109350164'){
      mensajeAdvertencia("Error el monto mayor a 20000000, corregir  ")
      return

    }
    self.transaccion = true
    self.update()
    self.detalleFactura.data =self.detail
    self.update()
    var fechaCreditoTemporal =condicionVenta.value == "02" || self.factura.tipoDoc == '88'  ?fechaCredito.value:new Date()
    var fechaReferencia =$('#referenciaFechaEmision').val() !=null?referenciaFechaEmision.value:new Date()
    var JSONDetalles = JSON.stringify( self.detalleFactura );
    self.factura.id = self.factura.id
    self.factura.condicionVenta = $('#condicionVenta').val()
    self.factura.fechaCredito =fechaCreditoTemporal.toString()
    self.factura.referenciaFechaEmision =fechaReferencia
    self.factura.totalEfectivo =__valorNumerico($('#totalEfectivo').val())
    self.factura.totalTarjeta = __valorNumerico($('#totalTarjeta').val())
    self.factura.totalBanco = __valorNumerico($('#totalBanco').val())
    self.factura.plazoCredito = __valorNumerico($('#plazoCreditoL').val())
    self.factura.detalleFactura =JSONDetalles
    self.factura.estado = estado
    self.factura.codigoMoneda = self.parametros.codigoMoneda
    self.factura.tipoCambio = self.tipoCambio.total ==null?__getTipoCambioCompra():self.tipoCambio.total
    if(self.factura.tipoCambio ==null){
        self.factura.tipoCambio =575

    }
    self.update();
    var formulario = $("#formularioFactura").serialize();
    $.ajax({
        type : "POST",
        dataType : "json",
        async: false,
        data : formulario,
        url : "CrearFacturaAjax",
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                     mensajeAdvertencia(data.message);
                }
                self.transaccion = false
                self.update()
            } else {
               	self.cantidadEnterFacturar =0
                self.update()
                evaluarFactura(data)
                self.transaccion = false
                self.update()
                __DeleteUltimoArticuloIngresado()
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
            self.transaccion = false
            self.update()
        }
    });
}

function evaluarFactura(data){
    self.facturaImprimir =null
     self.factura                = {
        id:null,
	    fechaCredito:null,
	    fechaEmision:null,
	    condicionVenta:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalComprobante:0,
	    totalEfectivo:0,
        totalTarjeta:0,
        totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
        totalCambioPagar:0,
	    codigoMoneda:"",
	    estado:1,
	    cliente:{
            id:null,
            nombreCompleto:"",
        },
	    vendedor:{
            id:null,
            nombreCompleto:""
        }
    }
    self.update()
   if (data.message != null && data.message.length > 0) {
        $.each(data.listaObjetos, function( index, modeloTabla ) {
            self.facturaImprimir   = self.empresa.imprimirSiempre == 0 ? modeloTabla:modeloTabla.factura
            self.facturaReimprimir = self.facturaImprimir
            self.detallesFactura = self.empresa.imprimirSiempre == 0 ? null:modeloTabla
            self.bloqueoFactura = 1;
            self.update()
        });
        if(self.facturaImprimir.estado == 2 || self.facturaImprimir.estado == 3 || self.facturaImprimir.estado == 4){
            __Init()

            localStorage.setItem('facturaReimprimir', JSON.stringify(self.facturaReimprimir));
            if(self.empresa.imprimirSiempre == 0){
                mensajeToasExito(mostrarMensajeCreacionConsecutivo(self.facturaImprimir))
            }else{
               var parametros = {
                    factura: self.empresa.imprimirSiempre == 0 ? self.facturaReimprimir : data.listaObjetos  ,
                    facturaDia:self.empresa.imprimirSiempre == 0 ? 0 : 3
                }
                riot.mount('ptv-imprimir',{parametros:parametros});
            }
        }else{
            mensajeToasExito(mostrarMensajeCreacionConsecutivo(self.facturaImprimir))
            __Init()
        }
    }
}

function __ListaFacturasEnEspera(){
     self.facturas_espera       = {data:[]}
     self.update()
    $.ajax({
        url: 'ListarFacturasEsperaActivasAjax',
        datatype: "json",
        global: false,
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
               self.facturas_espera.data =  result.aaData;
               self.update();
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            window.location.href = "login";
        }
    });
}

this.__TotalDeDescuento = function(e){
    self.factura.porcentajeDesc = __valorNumerico(e.target.value)
    self.update()
    __calculate()
}.bind(this)

_AtrasFacturaFinal(){
    self.totalCambioPagarSTR = 0
    self.totalCambioPagar = 0
    self.factura.totalCambioPagar = 0
   self.mostrarFormularioPago = false
   self.mostarParaCrearNuevaFactura = true
   self.cantidadEnterFacturar = 0
   self.primeraVezBilleteClick = false
   self.error = false
   self.update()
   getPosicionInputCodigo()
}

/**
Asignar el tipo de documento a la factura
**/
__AsignarTipoDocumento(e){
    self.factura.tipoDoc = e.currentTarget.value
    asignarTiposDocumento();
}

function asignarTiposDocumento(){

    if(self.factura.tipoDoc == '88'){
        self.mostrarCamposIngresoContado = false
       // $('#condicionVenta').val('02')
       // self.factura.condicionVenta = '02'
        mostrarFechaCreditoCondicionPago()
    }else{
        self.mostrarCamposIngresoContado = true 
    }
    if($('#condicionVenta').val() == "02"  ){
        self.mostrarCamposIngresoContado = false
    }
    self.update()
}

function  mostrarFechaCreditoCondicionPago(){
        self.factura.fechaCredito = null
        self.factura.plazoCredito = 0
        $('.plazoCreditoL').val(0)
        
        $('.fechaCredito').val(null)
        $('.datepickerFechaCredito').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-0d',
              todayHighlight:true,
            }
        );

    
}

this.__formaPago = function(e){

    if(e.currentTarget.value == 1 || e.currentTarget.value == 3){
        if(self.factura.tipoDoc != '88'){
        self.mostrarCamposIngresoContado = true
        }
    }

    if(e.currentTarget.value == 2){
        self.mostrarCamposIngresoContado = false
        $('#condicionVenta').val('02')
    }
    self.update()
    mostrarFechaCreditoCondicionPago()

}.bind(this)

this.__MostrarFormularioDePago = function(){
    mostrarPAgo()
}.bind(this)

function mostrarPAgo(){

    if(self.detail.length == 0 ){
        getPosicionInputCodigo()
        mensajeAdvertencia($.i18n.prop("factura.alert.sin.detalles"))
        getPosicionInputCodigo()
        return
    }
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
   // getSubTotalGeneral()
    self.totalCambioPagar =0
    self.factura.totalCambioPagar =0
    self.mostarParaCrearNuevaFactura = false
    self.mostrarFormularioPago = true
    self.factura.cambioMoneda = self.factura.totalVentaNeta / self.tipoCambio.total
    self.update()
    $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
    seleccionarEfectivo()
}

function lecturaCodigo(){
    var valor = $('.codigo').val()
    if (valor == "" || valor.length == 0){
        if(self.cantidadEnterFacturar >= 1){
            self.cantidadEnterFacturar = 0
            self.update()
             __EnviarFacturar()
             return
        }else{
            self.cantidadEnterFacturar = self.cantidadEnterFacturar + 1
            self.update()
        }
    }
    var objetos = getCantidadAdnCodigo_PV();
    var codigoActual = objetos.codigo
    var cantidadAct =objetos.cantidad

    if(valor.indexOf("+") != -1){

       __sumarMasArticulo(objetos.codigo,0,cantidadAct)
       getPosicionInputCodigo()
       return
    }
    __buscarcodigo(codigoActual,__valorNumerico(cantidadAct),0);
}

__addPrecioDetail(e){
    if (e.keyCode != 13) {
        return;
    }
    var codigo = $('#codigo').val()
    if(codigo.length == 0){
       __EnviarFacturar()
    }
    var objetos = getCantidadAdnCodigo_PV();
    var codigoActual = objetos.codigo
    var cantidadAct =objetos.cantidad

    var valor = __valorNumerico(cantidadAct)
    var precio = e.currentTarget.value

    if(codigo.indexOf("+") != -1){
       __sumarMasArticulo(codigoActual,precio,cantidadAct)
       getPosicionInputCodigo()
       return
    }
    __buscarcodigoPrecio(codigoActual,valor,__valorNumerico(precio));
    getPosicionInputCodigo()
}

function getCantidadAdnCodigo_PV(){
    var objeto ={
        codigo:'',
        cantidad:0
    }

    var valor = $('.codigo').val()
    if(valor == "" | valor.length == 0){
        return objeto
    }
    var existe = false
    var existeMas = false
    for(i=0; i<valor.length; i++){
        existeMas = valor.indexOf("+") !=-1?true : false
       if(existe == false && existeMas  == false ){
          existe = valor.charAt(i) == "*"?true : false
         if(valor.charAt(i) !="*" && valor.charAt(i) != "+"){
              objeto.codigo = objeto.codigo + valor.charAt(i)
          }
       }else{
           if(valor.charAt(i) != "+" && valor.charAt(i) != "*"){
              objeto.cantidad = objeto.cantidad + valor.charAt(i)
           }

       }
    }
    return objeto;

}

function __sumarMasArticulo(codigo,precio,cant){
    var temArticulo = __getUltimoArticuloIngresado()
    if(temArticulo == null){
        return;
    }
    var valorPrecio =  parseFloat(precio)
    var cantidadAct =cant
    aplicarSumaAlCodigo(valorPrecio,cantidadAct,true);

}

function aplicarSumaAlCodigo(valorPrecio,cantidadAct,siSuma){
    var temItem = __getUltimoItemIngresado()
    if(temItem == null){
        getPosicionInputCodigo()
        return
    }
   for (var count = 0; count < self.detail.length; count++) {
        if (self.detail[count].codigo == temItem.codigo  && temItem.numeroLinea == self.detail[count].numeroLinea){
            self.item          = self.detail[count];
            var restarValores = self.item.cantidad - __valorNumerico(cantidadAct)
            self.item.cantidad = siSuma  == true?self.item.cantidad + __valorNumerico(cantidadAct):restarValores <= 0 ? 1 : self.item.cantidad - __valorNumerico(cantidadAct)
            self.item.precioUnitario = valorPrecio >0?valorPrecio:self.item.precioUnitario
            self.cantidadEnterFacturar = 0
            self.update();
                        //factura.js
            self.item = ActualizarLineaDEtalle(self.item) 
            self.update()
            self.detail[count] = self.item;
            self.update();
        }
    }
    __calculate();
}

this.__agregarArticuloBotonAgregar = function(){
   __buscarcodigo($( "#codigo" ).val(),1,0);
   return
}.bind(this)

this.__ConsultarProductosDesc = function(e){
    if (e.keyCode != 13) {
        return;
    }
    __ListaDeArticulosPorDescripcion($("#codigoArt").val(),e.currentTarget.value)
}.bind(this)

this.__ConsultarProductosCod = function(e){
    if (e.keyCode != 13) {
        return;
    }
    __ListaDeArticulosPorDescripcion()
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

this._EscogerClientes = function(){
    __ListaDeClientes()
 }.bind(this)

this._EscogerVendedores = function(){
    $('#modalVendedor').modal('show')
 }.bind(this)

function __ListaDeVendedores(){
    $.ajax({
        url: 'ListarVendedoresActivosAjax.do',
        datatype: "json",
        global: false,
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                __informacionData_vendedores()
                loadListar(".tableListaVendedor",idioma_espanol,self.informacion_tabla_vendedores,result.aaData)
                agregarInputsCombos_Vendedores()
                ActivarEventoFiltro(".tableListaVendedor")
                __seleccionarVendedor()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
    return
}

function __ListaDeClientes(){
    $.ajax({
        url: 'ListarClientesActivosAjax.do',
        datatype: "json",
         global: false,
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.clientes.data =result.aaData
                self.update()
                 $('.nombreInstitucionExoneracion').selectpicker(
                    {
                        style: 'btn-info',
                        size:10,
                        liveSearch: true
                    }
                );

                 self.informacion_tabla_clientes =__informacionData_formato_cliente()
                 self.update()
                loadListar(".tableListaCliente",idioma_espanol,self.informacion_tabla_clientes,result.aaData)
                agregarInputsCombos_Clientes()
                ActivarEventoFiltro(".tableListaCliente")
                
                 $('#modalClientes').modal('show')
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
    return
}

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

function __ListaArticulosUsoInterno(){
    $.ajax({
        url: 'ListarArticulosActivosUsoInternoAjax.do',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
              self.articulos_uso_interno = result.aaData
              self.update()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
    return
}

function __buscarcodigoPrecio(idArticulo,cantidad,precio){
    if(idArticulo ==null){
        return
    }
    if(idArticulo.length ==0){
        return
    }
    $.ajax({
        type: 'GET',
        url: 'findArticuloByCodigojax.do',
        method:"GET",
        data:{codigoArticulo:idArticulo},
        success: function(data){
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message);
                }
            }else{
                self.articulo  = null
                self.update()
                if (data.message != null && data.message.length > 0) {
                    self.articulo =null
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        if(modeloTabla.estado  == "Inactivo"){
                            mensajeError($.i18n.prop("error.articulo.inactivo.inventario"))
                            return
                        }
                        self.articulo  = modeloTabla
                        self.update()
                        if(self.articulo !=null){
                            actualizarArticuloPrecio(precio,cantidad)

                        }
                    });
                }
            }
        },
	    error : function(xhr, status) {
            console.log(xhr);
          mensajeErrorServidor(xhr, status);
        }
    });
   return
}

function actualizarArticuloPrecio(precio,cantidad){
    self.articulo.precioPublico = precio > 0 ?precio:self.articulo.precioPublico
    self.articulo.precioPublico = precio > 0 ?precio:self.articulo.precioPublico
    self.descripcionArticulo = self.articulo.descripcion
    self.cantidadEnterFacturar = 0
    self.update()
    __agregarArticulo(cantidad)
}

function __buscarcodigo(idArticulo,cantidad,precio){
    self.articulo = null
    self.update()
    if(idArticulo ==null){
        return
    }
      if(idArticulo.length ==0){
        return
    }
    $.ajax({
        type: 'GET',
        url: 'findArticuloByCodigojax.do',
        method:"GET",
        data:{codigoArticulo:idArticulo},
        success: function(data){
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message);

                }
            }else{
                self.articulo  = null
                self.update()
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.articulo  = modeloTabla
                        if(modeloTabla.estado  == "Inactivo"){
                            mensajeAdvertencia($.i18n.prop("error.articulo.inactivo.inventario"))
                            return
                        }
                        self.articulo.precioPublico = getListaPrecio(self.articulo)
                        self.descripcionArticulo = modeloTabla.descripcion
                        self.update()
                        if(self.articulo !=null){
                            if(self.articulo.tipoCodigo =="04" || self.empresa.tieneLector !="Activo"){
                                $('.precioVenta').val(getListaPrecio(self.articulo))
                                $('.precioVenta').select()
                                $(".precioVenta").focus()
                                return
                            }
                        }
                        __agregarArticulo(cantidad)
                  });
                }
            }
        },
	    error : function(xhr, status) {
            console.log(xhr);
          mensajeErrorServidor(xhr, status);
        }
    });
   return
}

function __agregarArticulo(cantidad){
    if(self.articulo == null){
        return;
    }
    if(cantidad == null){
        cantidad = 1
    }
    if(cantidad == 0){
        cantidad = 1
    }
    var encontrado = false;
    var valor = $('.codigo').val()
    //if(valor == "" || valor.length == 0){
    //    return
    //}
    var multiplicaPeso = valor.indexOf("*") !=-1?true : false;
    if(self.articulo.tipoCodigo =="04" || self.empresa.tieneLector !="Activo" || multiplicaPeso == true){
        __nuevoArticuloAlDetalle(cantidad);
        encontrado = true;
    }

    __SetUltimoArticuloIngresado()
    if( encontrado ==false){
        if(self.detail[0] == null){
            __nuevoArticuloAlDetalle(cantidad);
            encontrado = true;
        }else{
            for (var count = 0; count < self.detail.length; count++) {
                if (self.detail[count].codigo == self.articulo.codigo ){
                self.item          = self.detail[count];
                self.item.cantidad = self.item.cantidad + __valorNumerico(cantidad)
                self.cantidadEnterFacturar = 0
                self.cantArticulos = self.cantArticulos + 1
                self.update();
                            //factura.js
                self.item = ActualizarLineaDEtalle(self.item) 
                self.update()
    
                self.detail[count] = self.item;
                encontrado = true;
                self.update();

                }
            }
        }

        if(encontrado == false){
        __nuevoArticuloAlDetalle(cantidad);
        }

    }
    __calculate();
    return encontrado
}

function aplicarLineaFacturaCambioPrecio(){
    for (var count = 0; count < self.detail.length; count++) {
        if (self.detail[count].codigo == self.articulo.codigo ){
            self.item          = self.detail[count];
            self.cantidadEnterFacturar = 0
            self.cantArticulos = self.cantArticulos

            var resultadoPrecio = getListaPrecio(self.articulo)
            var resultaMontoImpuesto = __valorNumerico(self.articulo.impuesto)
            var precioUnitario  = getPrecioUnitario(resultadoPrecio,resultaMontoImpuesto)
            self.item.precioUnitario = precioUnitario
            self.update();
            //factura.js
            self.item = ActualizarLineaDEtalle(self.item) 
            self.update()
            self.detail[count] = self.item;
            self.update();
         }
    }
    __calculate();

}

this.__removeProductFromDetail = function(e) {
    self.autorizarBorrado = 1
    self.itemEliminar = e.item;
    self.update()
    if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        self.rutaAutorizada = '';
        self.update()
        inputCursorUsuario()
    }else{
        eliminarDetalle()
    }
}.bind(this)

function buscarItemEliminar(item){
    for (var count = 0; count < self.detail.length; count++) {
        if (self.detail[count].codigo == item.codigo && self.detail[count].numeroLinea == item.numeroLinea ){
             self.detail.splice(count, 1);
             self.update()

        }
    }
}

function  eliminarDetalle(){
    buscarItemEliminar(self.itemEliminar)
    self.cantArticulos = self.cantArticulos > 0?self.cantArticulos - 1:0
    var num = 0
    for (var count = 0; count < self.detail.length; count++) {
         num = num + 1
    }
    if(num > 0){
        var cont  = 0
       self.detail.forEach(function(elemen){
            elemen.numeroLinea = num
            num = num > 0?num -1:1
            cont =  cont + 1
        })
        self.numeroLinea =  cont
    }else{
      self.numeroLinea =  0
    }
    self.update()
     __calculate();
     __DeleteUltimoArticuloIngresado()
     __DeleteUltimoItemIngresado()
 }

function __nuevoArticuloAlDetalle(cantidad){
    if(self.articulo.descripcion == null){
        return;
    }
    if(self.articulo.descripcion == ""){
        return;
    }
    if(self.detail == null){
        __storege()
    }
    if(verificarTarifa()){
         mensajeAdvertencia(" Error El articulo no tiene la Tarifa IVA ")
        return false
    }
    var itemNuevo = setItemNuevo(cantidad)
    self.detail.push(itemNuevo);
    self.detail.sort(function(a,b) {
    if ( a.pesoPrioridad > b.pesoPrioridad )
        return -1;
    if ( a.pesoPrioridad < b.pesoPrioridad )
        return 1;
    return 0;
    } );
    self.cantidadEnterFacturar = 0
    self.update()
}

function setItemNuevo(cantidad){
    var resultadoPrecio = getListaPrecio(self.articulo)
    var resultaMontoImpuesto = __valorNumerico(self.articulo.impuesto)
    var precioUnitario  = getPrecioUnitario(resultadoPrecio,resultaMontoImpuesto)
    var montoTotal      = getMontoTotal(precioUnitario,cantidad)
    var montoDescuento  = 0
    var naturalezaDescuento = ""
    var subTotal        = montoTotal
    var montoImpuesto1  = 0
    var montoImpuesto   = _calcularImpuesto(subTotal,__valorNumerico(self.articulo.impuesto) ==null?0:__valorNumerico(self.articulo.impuesto))
    var montoTotalLinea = subTotal + montoImpuesto 
    self.pesoPrioridad  =  self.pesoPrioridad + 1
    self.numeroLinea    = self.numeroLinea + 1
    self.cantArticulos  = self.cantArticulos + 1
    var costoTotal      = __valorNumerico(self.articulo.costo) > precioUnitario ?0:__valorNumerico(self.articulo.costo);
    var ganancia        = __ObtenerGananciaProductoNuevoIngresado(0,precioUnitario,self.articulo.costo ==null?0:__valorNumerico(self.articulo.costo),cantidad)
    var item = {
       numeroLinea     : __valorNumerico(self.numeroLinea),
       pesoPrioridad   : self.pesoPrioridad,
       tipoImpuesto    : self.articulo.tipoImpuesto ==null?"":self.articulo.tipoImpuesto,
       tipoImpuesto1   : "",
       tipoCodigo      : self.articulo.tipoCodigo,
       unidadMedida    : self.articulo.unidadMedida,
       iva             : __valorNumerico(self.articulo.impuesto),
       iva1            : 0,
       codigo          : self.articulo.codigo,
       descripcion     : self.articulo.descripcion,
       cantidad        : __valorNumerico(cantidad),
       precioUnitario  : __valorNumerico(precioUnitario),
       impuesto        : __valorNumerico(self.articulo.impuesto),
       impuesto1        : 0,
       montoImpuesto   : __valorNumerico(montoImpuesto),
       montoImpuesto1  : __valorNumerico(montoImpuesto1),
       impuestoNeto    : __valorNumerico(montoImpuesto) ,
       montoDescuento  : 0,
       porcentajeDesc  : 0,
       ganancia        : __valorNumerico(ganancia),
       montoGanancia   : __valorNumerico(ganancia),
       subTotal        : __valorNumerico(subTotal),
       montoTotalLinea : __valorNumerico(montoTotalLinea),
       montoTotal      : __valorNumerico(montoTotal),
       costo           : costoTotal,
       porcentajeGanancia :   getListaPrecioGanancia(self.articulo) ==null?0:__valorNumerico(getListaPrecioGanancia(self.articulo)),
       pesoTransporte :  __valorNumerico(self.articulo.pesoTransporte),
       pesoTransporteTotal :__valorNumerico(self.articulo.pesoTransporte),
       montoExoneracion:0,
       montoExoneracion1:0,
       porcentajeExoneracion:0,
       fechaEmisionExoneracion:null,
       nombreInstitucionExoneracion:"",
       numeroDocumentoExoneracion:"",
       tipoDocumentoExoneracion:""
    }
     __SetUltimoItemIngresado(item);
    
    return item;
}

/**
	* Obtiene el precio unitario sin descuento sin impuesto
	**/
	function getPrecioUnitario(precio ,impuesto){
	   var porcentajeImpuesto = 0
	   var resultado  = 0
	   if(impuesto > 0){
	      porcentajeImpuesto = impuesto / 100
	      porcentajeImpuesto =  porcentajeImpuesto + 1
	      resultado  =  precio  / porcentajeImpuesto
	   }else{
	       resultado  =  precio
	   }
	   return resultado     
}
/** Funciones en ventas nueva , venta post , restaurante comunes **/
	/**
	* Monto de Total
	**/
	function getMontoTotal(precioUnitario,cantidad){
	    var resultado = parseFloat(precioUnitario) * parseFloat(cantidad)
	    return resultado
	}



/**
	 * calculo del impuesto iva
	 * */
	function _calcularImpuesto(precio,iva){
	    if(iva == 0){
	        return 0;
	    }
	    var impuesto = iva ;
	    var resultado = precio * impuesto;
		resultado = resultado / 100;
	    return resultado;
	}


function verificarTarifa(){
    if(__valorNumerico(self.articulo.impuesto) > 0 ){
       if (self.articulo.tipoImpuesto =='01' ||  self.articulo.tipoImpuesto =='07'){
            if (self.articulo.codigoTarifa == "" ){
                return true
            }

       }
    }
    return false
}

function getListaPrecio(articulo){

    if($('.selectListaPrecios').val()==1){
        resultado=  __valorNumerico(articulo.precioPublico )
    }

    if($('.selectListaPrecios').val()==2){
        resultado=  __valorNumerico(articulo.precioMayorista )
    }

    if($('.selectListaPrecios').val()==3){
        resultado=  __valorNumerico(articulo.precioEspecial)
    }
    return resultado > 0 ?resultado:__valorNumerico(articulo.precioPublico )

}

function getListaPrecioGanancia(articulo){

    if($('.selectListaPrecios').val()==1){
        resultado=  __valorNumerico(articulo.gananciaPrecioPublico )
    }

    if($('.selectListaPrecios').val()==2){
        resultado=  __valorNumerico(articulo.gananciaPrecioMayorista )
    }

    if($('.selectListaPrecios').val()==3){
        resultado=  __valorNumerico(articulo.gananciaPrecioEspecial)
    }
    return resultado > 0 ?resultado:__valorNumerico(articulo.gananciaPrecioEspecial )

}

function __storege(){
    self.bloqueoFactura = 0;
    self.detail = []
    self.factura                = {
        id:null,
	   fechaCredito:null,
	   fechaEmision:null,
	   condicionVenta:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalComprobante:0,
	    totalEfectivo:0,
        totalTarjeta:0,
        totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
	    codigoMoneda:"",
	    estado:1,
	    cliente:{
            id:null,
            nombreCompleto:""
        },
	    vendedor:{
            id:null,
            nombreCompleto:""
        }

    }
    self.update()
}

 this.__recalculacionDelDetalle = function(e){
    cambiarCantidadModal()
  }.bind(this)

 function cambiarCantidadModal(){
      var cantidad = $(".cambiarCantidadArticulo").val();

    cantidad =__valorNumerico(cantidad);
    if(cantidad == 0){
       cantidad = 1;
    }
    cantidad = __valorNumerico(redondeoDecimales(cantidad,3))
    __ValidarCantidadArticulo(self.item.codigo,cantidad)

 }

function __ValidarCantidadArticulo(idArticulo,cantidad){
    $.ajax({
        type: 'GET',
        url: 'findArticuloByCodigojax.do',
        method:"GET",
        data:{codigoArticulo:idArticulo},
        success: function(data){
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message);
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        agregarCantidadAlaVenta(cantidad)
                    })
                }
            }
        },
	    error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}




function agregarCantidadAlaVenta(cantidad){
    self.item.cantidad = cantidad
    var ganancia        = __ObtenerGananciaProductoNuevoIngresado(0,self.item.precioUnitario,self.item.costo ==null?0:parseFloat(self.item.costo),cantidad)
    self.item.ganancia = ganancia
    self.item.montoGanancia    = self.item.ganancia
                //factura.js
    self.item = ActualizarLineaDEtalle(self.item) 
    self.update()
    
    aplicarCambioLineaDetalle()
    cambiarCantidadArticulo.value = 0
    $('#modalCambiarCantidad').modal('hide')
    getPosicionInputCodigo()
}

function aplicarCambioLineaDetalle(){
    var index    = self.detail.indexOf(self.item);
    self.detail[index] = self.item;
    self.update()
    __calculate()
}

this.__actualizarDescuento = function(e){
   aplicarDescuentoEnter()
}.bind(this)
function aplicarDescuentoEnter(){
    if ($("#formularioDescuento").valid()) {
        _actualizarDesc()
    }
}

function _actualizarDesc(){
    var descuento = $(".aplicarDescuento").val();
    descuento = __valorNumerico(descuento)
    if(descuento > 100){
         mensajeAdvertencia("Error el descuento no puede ser mayor al 100%");
         return false
    }
    if(self.empresa.aplicaGanancia ==1){
        if(self.item.porcentajeGanancia < descuento ){
            mensajeAdvertencia("No se puede aplicar un descuento mayor a la ganancia");
            descuento  = __valorNumerico(self.item.porcentajeGanancia)
        }
    }
    var index     = self.detail.indexOf(self.item);
    self.item.porcentajeDesc =  __valorNumerico(descuento);
    self.update()
                //factura.js
    self.item = ActualizarLineaDEtalle(self.item) 
    self.update()
    
    aplicarCambioLineaDetalle()
     $(".aplicarDescuento").val(null);
    $('#modalCambiarDescuento').modal('hide')

   getPosicionInputCodigo()
}

function __calculate() {
    self.factura.total            = 0;
    self.factura.totalDescuentos  = 0;
    self.factura.totalImpuesto    = 0;
    self.factura.totalImpuestoServ = 0; 
    self.factura.subTotal          = 0;
    self.update()
                        //Factura.js
    var resultado = __ResumenFactura(self.detail,self.factura);
    self.factura = resultado.factura
    self.update()
    self.cantArticulos = resultado.cantArticulos
    self.totalGananciaByProducto = formatoDecimales(parseFloat(resultado.totalGananciaByProducto),2)
    self.totalPesoByFactura = __valorNumerico(resultado.totalPesoByFactura)
    self.totalPesoByFacturaSTR = formatoDecimales(resultado.totalPesoByFactura,2);
    self.totalComprobante = formatoDecimales(resultado.totalComprobante,2);
    self.totalDescuentos = formatoDecimales(resultado.totalDescuentos,2);
    self.totalImpuesto = formatoDecimales(resultado.totalImpuesto,2);
    self.montoExoneracion = resultado.montoExoneracion > 0 ?formatoDecimales(resultado.montoExoneracion,2):"";
    self.subTotalGeneral =  formatoDecimales(resultado.subTotalGeneral,2)
    self.totalDescuentos = formatoDecimales(resultado.totalDescuentos,2)
    var resultadoTotalImpuesto = __valorNumerico(resultado.totalImpuesto) 
    self.totalImpuesto = formatoDecimales(resultado.totalImpuesto,2)
    self.update()
    getPosicionInputCodigo()
    localStorage.setItem('DetallesNueva', JSON.stringify(self.detail));
    localStorage.setItem('facturaNueva', JSON.stringify(self.factura));
    localStorage.setItem('cliente', JSON.stringify(self.factura.cliente));
    $(".nombreFactura").val(self.factura.nombreFactura)
    $(".correoAlternativo").val(self.factura.correoAlternativo)
    $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    seleccionarEfectivo()

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
            if(self.articulo.tipoCodigo =="04" || self.empresa.tieneLector !="Activo"){
                $('#codigo').val(self.articulo.codigo)
                $('.precioVenta').val(getListaPrecio(self.articulo))
                 $('#modalInventario').modal('hide')
                $('.precioVenta').focus()
                $('.precioVenta').select()
                return
            }
        }
        if(self.articulo.contable == "si"){
           __buscarcodigo(self.articulo.codigo,1,0)
        }else{
            __agregarArticulo(1)
        }
        $('#modalInventario').modal('hide')

        return

    });
}

function __informacionData_vendedores(){
    self.informacion_tabla_vendedores = [
                                        {'data' : 'cedula'           ,"name":"cedula"            ,"title" : $.i18n.prop("vendedor.cedula")            ,"autoWidth":false},

                                        {'data' : 'nombreCompleto'   ,"name":"nombreCompleto"    ,"title" : $.i18n.prop("vendedor.nombreCompleto")    ,"autoWidth":false},
                                        {'data' : 'correoElectronico',"name":"correoElectronico" ,"title" : $.i18n.prop("vendedor.correoElectronico") ,"autoWidth":false},
                                        {'data' : 'telefono'         ,"name":"telefono"          ,"title" : $.i18n.prop("vendedor.telefono")          ,"autoWidth":false},
                                        {'data' : 'celular'          ,"name":"celular"           ,"title" : $.i18n.prop("vendedor.celular")           ,"autoWidth":false},
                                        {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									            "render":function(id,type, row){
										            return __OpcionesVendedores(id,type,row);
	 							                }
								            },
                                        ];

}

function __OpcionesVendedores(){
  var agregar  = '<a href="#"  title="Seleccionar Vendedor" class="btn btnVendedor btn-success form-control" title="Seleccione el vendedor" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;
}

function __seleccionarVendedor() {
     $('#tableListaVendedor').on('click', '.btnVendedor', function (e) {
         var table = $('#tableListaVendedor').DataTable();
		if(table.row(this).child.isShown()){

	       var data = table.row(this).data();
	    }else{
	       var data = table.row($(this).parents("tr")).data();
         }
        self.vendedor = data
        self.update();
    });
}

function __seleccionarClientes() {
     $('#tableListaCliente').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListaCliente').DataTable();
		if(table.row(this).child.isShown()){

	       var data = table.row(this).data();
	    }else{
	       var data = table.row($(this).parents("tr")).data();
	     }
        self.cliente = data
        self.update();
        $('#modalClientes').modal('hide')
        if(!verificarSiClienteFrecuente(self.cliente)){
            __aplicarExoneracionPorCliente()
            if(stringVacio(self.cliente.identificacionExtranjero)== false){
                if(self.factura.tipoDoc  == '88'){
                   mostrarFechaCreditoCondicionPago()
                }else{
                    self.factura.tipoDoc ='01'
                }
               if(self.item != null){
                    if(self.item.tipoDocumentoExoneracion !=null){
                        if(self.item.tipoDocumentoExoneracion =='02' && self.factura.tipoDoc  != '88'){
                           self.factura.tipoDoc ='04'
                        }
                    }
               }
            }else{
               self.factura.tipoDoc ='04'
               
            }
            self.update()
            __ComboTipoDocumentos(1)
            
           
        }else{
            if(self.factura.tipoDoc  != '88'){
                self.factura.tipoDoc ='04'
                __ComboTipoDocumentos(0)
            }
        }
        self.cliente.tipoMag = __valorNumerico(self.cliente.tipoMag)
        self.update()
        if(self.cliente.tipoMag > 0){
            retotalizarMAG(self.cliente.tipoMag == 1 || self.cliente.tipoMag == 2 ? 1:0 )                 
        }
       $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
       $('#totalTarjeta').val(null)
       $('#totalBanco').val(null)
       seleccionarEfectivo()
    });

}

/**
*  Retotalizar  Montos al detallle
**/
function retotalizarMAG(valor){
    self.detalleFactura.data =self.detail
    self.update()
    var JSONDetalles = JSON.stringify( self.detalleFactura );
    var datos = {
        detalleFactura: JSONDetalles,
        acionRetoralizar : valor
    }
    $.ajax({
        url: "retotalizarVentaMAG.do",
        datatype: "json",
        data: datos,
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeErrorTiempo( data.message )
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    cargarDetallesFacturaEnEspera(data.listaObjetos,self.factura,2)
                }
            }
            $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
            $('#totalTarjeta').val(null)
            $('#totalBanco').val(null)
            seleccionarEfectivo()
            
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });

}


function cargarDetallesFacturaEnEspera(data,facturaPametros,tipo){
    $('.nota').val(null);
    $('.correoAlternativo').val(null);
    $('.nombreFactura').val(null);
    self.detail = [];
    self.numeroLinea =  0
    self.cantArticulos =  0
    self.pesoPrioridad = 0
    self.update()
    $.each(data, function( index, modeloTabla ) {
        self.factura = tipo == 1?modeloTabla.factura:facturaPametros
        $('.nota').val(self.factura.nota);
        $('.correoAlternativo').val(self.factura.correoAlternativo);
        $('.nombreFactura').val(self.factura.nombreFactura);
        self.factura.fechaCredito = self.factura.fechaCredito !=null?__displayDate_detail(self.factura.fechaCredito):null
        self.cliente              = tipo == 1?modeloTabla.factura.cliente:self.cliente
        self.vendedor             = tipo == 1?modeloTabla.factura.vendedor:null
        self.update()
        self.descripcionArticulo = modeloTabla.descripcion
        self.detail.push({
            numeroLinea     : modeloTabla.numeroLinea,
            tipoCodigo      : modeloTabla.tipoCodigo,
            unidadMedida    : modeloTabla.unidadMedida,
            pesoPrioridad    :modeloTabla.numeroLinea,
            codigo          : modeloTabla.codigo,
            tipoImpuesto    : modeloTabla.tipoImpuesto,
            tipoImpuesto1   : "",
            descripcion     : modeloTabla.descripcion,
            cantidad        : __valorNumerico(modeloTabla.cantidad),
            precioUnitario  : __valorNumerico(modeloTabla.precioUnitario),
            impuesto        : __valorNumerico(modeloTabla.impuesto),
            impuesto1       : 0,
            montoImpuesto   : __valorNumerico(modeloTabla.montoImpuesto),
            montoImpuesto1  : __valorNumerico(modeloTabla.montoImpuesto1),
            montoDescuento  : __valorNumerico(modeloTabla.montoDescuento),
            porcentajeDesc  : __valorNumerico(modeloTabla.porcentajeDesc),
            subTotal        : __valorNumerico(modeloTabla.subTotal),
            montoTotalLinea : __valorNumerico(modeloTabla.montoTotalLinea),
            montoTotal      : __valorNumerico(modeloTabla.montoTotal),
            costo           : __valorNumerico(modeloTabla.costo),
            porcentajeGanancia :__valorNumerico(modeloTabla.porcentajeGanancia),
            montoGanancia :__valorNumerico(modeloTabla.montoGanancia),
            ganancia :__valorNumerico(__valorNumerico(modeloTabla.ganancia)),
            pesoTransporte :  __valorNumerico(modeloTabla.pesoTransporte),
            pesoTransporteTotal :__valorNumerico(modeloTabla.pesoTransporteTotal),
            montoExoneracion:__valorNumerico(modeloTabla.montoExoneracion),
            montoExoneracion1:__valorNumerico(modeloTabla.montoExoneracion1),
            porcentajeExoneracion:__valorNumerico(modeloTabla.porcentajeExoneracion),
            fechaEmisionExoneracion:modeloTabla.fechaEmisionExoneracion,
            nombreInstitucionExoneracion:modeloTabla.nombreInstitucionExoneracion,
            numeroDocumentoExoneracion:modeloTabla.numeroDocumentoExoneracion,
            tipoDocumentoExoneracion:modeloTabla.tipoDocumentoExoneracion
        });
        self.update()
        self.numeroLinea   = self.numeroLinea + 1
        self.cantArticulos = self.cantArticulos + 1
        self.pesoPrioridad = self.numeroLinea
    })
    self.factura.totalCambioPagar = self.factura.totalComprobante;
    self.totalCambioPagar         = self.factura.totalComprobante
    self.detail.sort(function(a,b) {
        if ( a.pesoPrioridad > b.pesoPrioridad )
            return -1;
        if ( a.pesoPrioridad < b.pesoPrioridad )
            return 1;
        return 0;
    } );
    self.update()
    $(".nombreFactura").val(self.factura.nombreFactura)
    $(".correoAlternativo").val(self.factura.correoAlternativo)
    seleccionarEfectivo()
    $('#totalBanco').val(null)
    __ComboTipoDocumentos(0)
    __aplicarExoneracionPorCliente()
    __calculate()
    asignarTiposDocumento()
}


function __aplicarExoneracionPorCliente(){
    var aplicaExo = false
    var porcentaje = __valorNumerico(self.cliente.porcentajeExoneracion )
    if(porcentaje == 0){
        return
    }
    var valorTotal = 0
    for (var count = 0; count < self.detail.length; count++) {
        self.item          = self.detail[count];
        self.cliente.porcentajeExoneracion = __valorNumerico(self.cliente.porcentajeExoneracion)
            if(self.item.montoImpuesto > 0 || self.item.montoImpuesto1 > 0 ){
                if(self.cliente.porcentajeExoneracion > 0  ){
                    self.item.porcentajeExoneracion = getPorcentajeExoneracion(__valorNumerico(self.cliente.porcentajeExoneracion),self.item.impuesto )
                    self.item.fechaEmisionExoneracion = self.cliente.fechaEmisionExoneracion
                    self.item.nombreInstitucionExoneracion = self.cliente.nombreInstitucionExoneracion
                    self.item.numeroDocumentoExoneracion = self.cliente.numeroDocumentoExoneracion
                    self.item.tipoDocumentoExoneracion = self.cliente.tipoDocumentoExoneracion
                    self.item.montoExoneracion = getMontoExoneracionSubTotal(self.item.tipoDocumentoExoneracion,self.item.impuesto, self.item.porcentajeExoneracion, self.item.subTotal, self.item.montoImpuesto)   
                    self.item.ImpuestoNeto = self.item.montoImpuesto - self.item.montoExoneracion
                    self.item.montoTotalLinea = self.item.subTotal +  self.item.ImpuestoNeto
                    self.detail[count] = self.item;
                    self.update();
                    aplicaExo= true
                }else{

                    self.item.porcentajeExoneracion = 0
                    self.item.fechaEmisionExoneracion = null
                    self.item.nombreInstitucionExoneracion = ""
                    self.item.numeroDocumentoExoneracion = ""
                    self.item.tipoDocumentoExoneracion = ""
                    self.item.montoExoneracion = 0
                    self.item.montoExoneracion1 = 0
                    self.item.ImpuestoNeto = __valorNumerico(self.item.montoImpuesto) 
                    self.item.montoTotalLinea = self.item.subTotal +  self.item.ImpuestoNeto
                    self.detail[count] = self.item;
                    self.totalCambioPagar = 0
                    self.totalCambioPagarSTR = 0
                    self.factura.totalEfectivo =0
                    self.factura.totalTarjeta =0
                    self.factura.totalBanco =0
                    self.factura.totalCambioPagar = self.factura.totalComprobante
                    self.update();

                    aplicaExo = true
                }

            }else{
                self.item.porcentajeExoneracion = 0
                self.item.fechaEmisionExoneracion = null
                self.item.nombreInstitucionExoneracion = ""
                self.item.numeroDocumentoExoneracion = ""
                self.item.tipoDocumentoExoneracion = ""
                self.item.montoExoneracion = 0
                self.item.montoExoneracion1 = 0

            }
    }
    
    __calculate()
    if(aplicaExo == true){
       self.factura.totalCambioPagar = self.factura.totalComprobante
       self.factura.totalEfectivo =0
       self.factura.totalTarjeta =0
       self.factura.totalBanco =0
       self.totalCambioPagar = 0
       self.totalCambioPagarSTR = 0
       self.update();
    }
}

function __comboCondicionPago(){
    self.comboCondicionPagos = []
    self.update()
    self.comboCondicionPagos.push({
        estado:"01",
        descripcion:$.i18n.prop("factura.codicion.venta.contado")
    })
    self.comboCondicionPagos.push({
        estado:"02",
        descripcion:$.i18n.prop("factura.codicion.venta.credito")
    })
    self.update()
}

function __ComboTipoDocumentoExonerados(){
    self.comboTipoDocumentoExonerados = []
    self.update()
    self.comboTipoDocumentoExonerados.push({
        estado:"01",
        descripcion:$.i18n.prop("tipo.documento.exonerado.compras.autorizadas")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"02",
        descripcion:$.i18n.prop("tipo.documento.exonerado.venta.exenta.diplomado")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"03",
        descripcion:$.i18n.prop("tipo.documento.exonerado.autorizado.por.ley.hacienda")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"04",
        descripcion:$.i18n.prop("tipo.documento.exonerado.execciones.direccion.general.hacienda")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"05",
        descripcion:$.i18n.prop("tipo.documento.exonerado.transitorio.v")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"06",
        descripcion:$.i18n.prop("tipo.documento.exonerado.transitorio.ix")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"07",
        descripcion:$.i18n.prop("tipo.documento.exonerado.transitorio.xvii")
    })

    self.comboTipoDocumentoExonerados.push({
        estado:"99",
        descripcion:$.i18n.prop("tipo.documento.exonerado.otros")
    })
   self.update()
}

function __ComboTipoDocumentos(valor){
    self.comboTipoDocumentos = []
    self.update()
    if($('.tipoDoc').val() =="88"){
        self.comboTipoDocumentos.push({
            estado:"88",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.proforma")
        })
        self.update()

        return
    }
    if(valor == 1){
        self.comboTipoDocumentos.push({
            estado:"01",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
        })
        self.comboTipoDocumentos.push({
            estado:"04",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
        })
        self.comboTipoDocumentos.push({
            estado:"88",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.proforma")
        })
        self.update()
        return
    }
   if(self.empresa.prioridadFacturar == 1 ){
        self.comboTipoDocumentos.push({
            estado:"01",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
        })
        self.comboTipoDocumentos.push({
            estado:"04",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
        })
        self.comboTipoDocumentos.push({
            estado:"88",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.proforma")
        })

    }else{
    self.comboTipoDocumentos.push({
        estado:"04",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
    })
    self.comboTipoDocumentos.push({
         estado:"01",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
    })
    self.comboTipoDocumentos.push({
         estado:"88",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.proforma")
    })

    }
    self.update()
}

function agregarInputsCombos_Articulo(){
    $('.tableListarArticulos tfoot th').each( function (e) {
        var title = $('.tableListarArticulos thead th').eq($(this).index()).text();

        if ( $(this).index() != 0    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}

function agregarInputsCombos_Clientes(){

    $('.tableListaCliente tfoot th').each( function (e) {
        var title = $('.tableListaCliente thead th').eq($(this).index()).text();

        if ( $(this).index() != 0    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}

function agregarInputsCombos_Facturas_Dias(){

    $('.tableListarFacturasDia tfoot th').each( function (e) {
        var title = $('.tableListarFacturasDia thead th').eq($(this).index()).text();

        if ( $(this).index() != 0    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}

function agregarInputsCombos_Vendedores(){

    $('.tableListaVendedor tfoot th').each( function (e) {
        var title = $('.tableListaVendedor thead th').eq($(this).index()).text();

        if ( $(this).index() != 5    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}

function __EnviarFacturar(){
      if(self.mostrarFormularioPago == false && self.mostarParaCrearNuevaFactura == true){
            self.factura.totalCambioPagar =__valorNumerico(self.factura.totalComprobante)
            self.totalCambioPagar = redondeoDecimales(self.factura.totalComprobante,2)
            self.primeraVezBilleteClick == false
            self.update()
            $(".totalEfectivo").val(self.totalCambioPagar)
         mostrarPAgo()
      }else if (self.mostrarFormularioPago == true && self.mostarParaCrearNuevaFactura == false ){
          self.primeraVezBilleteClick == false
          self.update()
           __OpcionAbrirCajon()
            aplicarFactura(2)
        }
}

function __Teclas(tecla,event){
    if (tecla === undefined) {
        return 
    }
    if (event === undefined) {
        return 
    }

    if(event.id == 'nota' || event.id == 'correoAlternativo' || event.id == 'nombreFactura' ||
       event.target.id == 'nota' ||  event.target.id == 'correoAlternativo' || event.target.id == 'nombreFactura'){
        return
    }
  //  console.log(event)
   // console.log(tecla)
    if(tecla ==119){
        __EnviarFacturar()
        return

    }
    
    if(tecla ==111){
        if( self.rol.rolAdministrador == 0){
            return
        }
        if(!$('#modalCambiarCantidad').is(':visible')){
           seguridadCambiarPrecioLinea()
        }else{
            if(!$('#modalFacturasDia').is(':visible')){
            $(".codigo").val("")
            event.preventDefault()}
        }
        return
    }

    if(tecla ==115){
     ListarCodigosArticulos()
      $('.descArticulo').select()
      $('.descArticulo').focus()
      return
    }
    if(tecla ==16){
        if($('#modalAgregarClienteNuevo').is(':visible')){
           return
        }
      var resultado = __valorNumerico($(".totalEfectivo").val())
      if(resultado > 0){
        self.factura.totalTarjeta = resultado
        self.factura.totalEfectivo = 0
        self.factura.totalBanco = 0
        self.update()
        $(".totalEfectivo").val(null)
        $(".totalTarjeta").val(self.factura.totalTarjeta)
        $('.totalTarjeta').select()
        $('.totalTarjeta').focus()
        return
      }
      resultado = __valorNumerico($(".totalTarjeta").val())
      if(resultado > 0){
        self.factura.totalBanco = self.empresa.pantChino == 0?resultado:0
        self.factura.totalEfectivo = self.empresa.pantChino == 1?resultado:0
        self.factura.totalTarjeta = 0
        self.update()
        if(self.empresa.pantChino == 0){
            $(".totalEfectivo").val(null)
            $(".totalTarjeta").val(null)
            $(".totalBanco").val(self.factura.totalBanco)
            $('.totalBanco').select()
            $('.totalBanco').focus()

        }else{
            $(".totalBanco").val(null)
            $(".totalTarjeta").val(null)
            $(".totalEfectivo").val(self.factura.totalEfectivo.toFixed(2))
            seleccionarEfectivo()
        }
        return
      }
      if(self.empresa.pantChino == 0){
        resultado = __valorNumerico($(".totalBanco").val())
        if(resultado > 0){
            self.factura.totalEfectivo = resultado
            self.factura.totalBanco = 0
            self.factura.totalTarjeta = 0
            self.update()
            $(".totalBanco").val(null)
            $(".totalTarjeta").val(null)
            $(".totalEfectivo").val(self.factura.totalEfectivo.toFixed(2))
            seleccionarEfectivo()
            return
        }
      }else{
        resultado = __valorNumerico($(".totalEfectivo").val())
        if(resultado > 0){
            self.factura.totalEfectivo = resultado
            self.factura.totalBanco = 0
            self.factura.totalTarjeta = 0
            self.update()
            $(".totalBanco").val(null)
            $(".totalTarjeta").val(null)
            $(".totalEfectivo").val(self.factura.totalEfectivo.toFixed(2))
            seleccionarEfectivo()
            return
        }

      }
        self.factura.totalEfectivo = __valorNumerico(self.factura.totalComprobante)
        self.factura.totalBanco = 0
        self.factura.totalTarjeta = 0
        self.update()
        $(".totalBanco").val(null)
        $(".totalTarjeta").val(null)
        $(".totalEfectivo").val(self.factura.totalEfectivo.toFixed(2))
        seleccionarEfectivo()
      return
    }

    if(tecla ==120){
      aplicarFactura(1)
      return
    }

    if(tecla ==35){
     reimprimirFacturaEnMomento()
     return
    }

    if(tecla ==113){
      __SeguridadLimpiar()
      getPosicionInputCodigo()
      return
    }

    if(tecla ==45){
       __OpcionAbrirCajon()
    }
   if(tecla ==27){
      getPosicionInputCodigo()
      return
    }

}
__SumarConMouse(){
    aplicarSumaAlCodigo(0,1,true)
 
}

__RestarConMouse(){
    aplicarSumaAlCodigo(0,1,false)
   // getPosicionInputCodigo()
}

function __SumarConTecla(e){
    if(verificaSiSuma()){
        aplicarSumaAlCodigo(0,1,true)
        getPosicionInputCodigo()
        e.preventDefault()
         return
    }

}

function __RestarConTecla(e){
    if(verificaSiSuma()){
        aplicarSumaAlCodigo(0,1,false)
        getPosicionInputCodigo()
        e.preventDefault()
         return
    }

}

function verificaSiSuma(){
   var objetos =  getCantidadAdnCodigo_PV();
    for(i=0; i<objetos.codigo.length; i++){
        if(isNumber(objetos.codigo)){
          return false        }
    }
    return true
}

function refrescarPagina(){
     __Init()

}

this._sumarBilletes = function(e){
     if(self.primeraVezBilleteClick == false){
        self.factura.totalEfectivo = 0
        self.primeraVezBilleteClick = true
        self.update()
    }
    var item = e.item
    if(item.valor == 0 ){
       self.factura.totalEfectivo = 0
       self.factura.totalTarjeta  = 0
       self.factura.totalBanco    = 0
       self.factura.totalCambioPagar  = 0
       self.totalCambioPagar = 0
       self.claseCambioDinero     = "entregarCambioPositivo"
       self.totalCambioPagarSTR =0
    }else{
       self.factura.totalEfectivo += __valorNumerico(item.valor)
       $('.efectivo').val(self.factura.totalEfectivo.toFixed(2))
        self.update()
        var sumaMontosEntregadosParaCambios =__valorNumerico(self.factura.totalTarjeta)
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco)
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo)
        self.factura.totalCambioPagar = 0
        self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios - __valorNumerico(self.factura.totalComprobante)
        self.claseCambioDinero  = __valorNumerico(sumaMontosEntregadosParaCambios) > __valorNumerico(self.factura.totalComprobante)?'entregarCambioPositivo':'entregarCambioNegativo'
        self.totalCambioPagar = redondeoDecimales(self.factura.totalCambioPagar,2)
        self.totalCambioPagarSTR =formatoDecimales(self.totalCambioPagar,2)
        $(".totalEfectivo").val(self.factura.totalEfectivo.toFixed(2))
    }
    self.update()
}.bind(this)

function cargaBilletes(){
    self.billetes = []
    self.update()
    _incluirBilletes("₡","1000",1000,'/dist/img/billete1000.jpg')
    _incluirBilletes("₡","2,000",2000,'/dist/img/billete2000.jpg')
    _incluirBilletes("₡","5,000",5000,'/dist/img/billete5000.jpg')
    _incluirBilletes("₡","10,000",10000,'/dist/img/billete10000.jpg')
    _incluirBilletes("₡","20,000",20000,'/dist/img/billete20000.jpg')
    _incluirBilletes("₡","50,000",50000,'/dist/img/billete50000.jpg')
    _incluirBilletes("","Limpiar",0,'/dist/img/limpiar.png')
}

function _incluirBilletes(modena,descripcion,valor,imagen){
   self.billetes.push(
        {
            modena:modena,
            descripcion:descripcion,
            imagen:imagen,
            valor:valor
        }
    )
    self.update()
}

this.__AbrirCajon = function(){
    __OpcionAbrirCajon()
}.bind(this)

function __OpcionAbrirCajon(){
    self.informacionAbrirCajon = "."
    self.update()
    if(self.empresa.abrirSinComanda == 1){
        abrirCajonDineroSinComanda()
    }
    if(self.empresa.abrirConComanda == 1){
      abrirCajonDineroConComanda()
    }

}

function abrirCajonDineroSinComanda(){
  var div = document.querySelector("#imprAbriCajon");
  var ventana = window.open('', 'PRINT', 'height=20,width=20');
  ventana.document.write('<html><head><title>' + "" + '</title>');
  ventana.document.write('</head><body >');
  ventana.document.write(div.innerHTML);
  ventana.document.write('</body></html>');
  ventana.document.close();
  ventana.focus();
  ventana.print();
  ventana.close();
  return true;
}

function abrirCajonDineroConComanda(){

		var informacion = {
			mesa: "Abrir Cajon",
			mesero: "abrirCajon",
		    nombreImpresora:self.empresa.impresoraFactura,
		    cantidadCaracteresLinea:"40",
		    formatoTiquete:"",
		    detalles:""
		}
		var JSONData = JSON.stringify(informacion);

	    $.ajax({
	        contentType: 'application/json',
	        url: 'http://localhost:8033/service/abrirCajonDinero',
	        datatype: "json",
	        data : JSONData,
	        method:"POST",
	        success: function (result) {

	        },
	        error: function (xhr, status) {
	            console.log(xhr);
	           // mensajeAdvertencia("COMANDA NO ESTA ACTIVA PARA ABRIR EL CAJON DE DINERO,POR FAVOR ACTIVAR LA COMANDA");
	        }
	    });
}

function __ListaProformas(){
    $.ajax({
        url: 'ListarVendedoresActivosAjax.do',
        datatype: "json",
        global: false,
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                __informacionData_vendedores()
                loadListar(".tableListaVendedor",idioma_espanol,self.informacion_tabla_vendedores,result.aaData)
                agregarInputsCombos_Vendedores()
                ActivarEventoFiltro(".tableListaVendedor")
                __seleccionarVendedor()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
    return
}

</script>
</punto-venta>