<punto-venta>
 <!-- Titulos -->
    <div  class="row titulo-encabezado" show={parametros.codigoMoneda =="USD"?true:false} >
        <div  class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h1 ><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("facturar.dolares.titulo")}  </h1>
        </div>
        <div class=" col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
<!--validar rol de usuario-->

<!-- The Modal -->
  <div class="modal fade" id="modalRolUsuario">
    <div class="modal-dialog">
      <div class="modal-content">
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Seguridad</h4>
        </div>
        <!-- Modal body -->
        <div class="modal-body">
           <form id="formularioModalRolUsuario">
                <div class="form-group ">
                    <label>Usuario</label> 
                    <input  type="text"  class="form-control usuarioSistema"      id="usuarioSistema" name="usuarioSistema" value="{validarRolCommand.usuarioSistema}">
                </div>      
                <div class="form-group ">
                    <label>Clave</label> 
                    <input  type="password"  class="form-control claveSistema"  name="claveSistema" id="claveSistema"  value="{validarRolCommand.claveSistema}">
                </div>      
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger"  onclick ="{__SeguridadVentas}" >Autorizar</button>
        </div>
      </div>
    </div>
  </div>
<!--fin validar rol de usuario-->

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
                                                <select class="form-control tipoDoc" id="tipoDoc" name="tipoDoc"   >
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
                                    <div class="form-group ">
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
                                        <input type="email" id="correoAlternativo" name="correoAlternativo" class="campo correoAlternativo"  value="{factura.correoAlternativo}" >
                                    </div>
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("factura.nombreFactura")}</label> 
                                        <input type="text" id="nombreFactura" name="nombreFactura" class="campo nombreFactura "  value="{factura.nombreFactura}" > 
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
                                        <input type="number" id = "plazoCreditoL"  name "plazoCreditoL" class="campo plazoCreditoL" value="{factura.plazoCredito}" >
                                    </div>
                                </div>
                                <div  class= "col-md-6 col-sx-6 col-sm-6 col-lg-6" >
                                    <div class="form-group ">
                                        <label class="{labelTotales}">{$.i18n.prop("factura.resumen.efectivo")} </label> 
                                        <input onclick={_SeleccionarEfectivo}   onkeyup={ __TotalDeEfectivoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="{campoTotales} {tamanoLetra} totalEfectivo " id="totalEfectivo" name="totalEfectivo" value="{factura.totalEfectivo}" >
                                    </div>
                                    <div  class="form-group ">
                                        <label class="{labelTotales}">{$.i18n.prop("factura.resumen.tarjeta")} </label> 
                                        <input onclick={_SeleccionarTarjeta} onkeyup={ __TotalDeTarjetaAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="{campoTotales} {tamanoLetra} totalTarjeta" id="totalTarjeta" name="totalTarjeta"  value="{factura.totalTarjeta}" >
                                    </div> 
                                    <div  class="form-group " show={empresa.pantChino == 0}>
                                        <label class="{labelTotales} ">{$.i18n.prop("factura.resumen.banco")} </label> 
                                        <input onclick={_SeleccionarBanco} onkeyup={ __TotalDeBancoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number"  onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="{campoTotales} {tamanoLetra}  totalBanco"  id="totalBanco" name="totalBanco"  value="{factura.totalBanco}" >
                                    </div> 
                                </div>
                            </div>
                            <input type="hidden" id='codigoActividad' name='codigoActividad'  value="{factura.codigoActividad}" >
                            <input type="hidden" id='codigoMoneda'            name='codigoMoneda'            value="{factura.codigoMoneda}" >
                            <input type="hidden" id='pesoTransporteTotal'     name='pesoTransporteTotal'      value="{totalPesoByFactura}" >
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
                    </div>
                    <div class="box-footer">
                        <button onclick={_AtrasFacturaFinal} class="btn-dark-gray btn-back pull-left">  {$.i18n.prop("btn.volver")}</button>
                        <button onclick={__AplicarYcrearFactura}  class="btn-green btn-add pull-right"> </i> {$.i18n.prop("btn.aplicar")}</button>
                    </div>
                </div>
                  
            </div>
            <div class="col-md-4 col-sm-4 col-lg-4 col-sx-12 ">
		        <div class="box">
				    <div class="box-header with-border"><h1 class="box-title">Detalles Facturacion</h1></div>
				    <div class="box-body">
                        <aside class="right-sidebar">
                            <!--Booking details-->
                            <article class="booking-details clearfix">
                                <h1><span id="lblSCS">{$.i18n.prop("factura.resumen.venta")}</span></h1>
                                <div class="booking-info" show={soloParaChinos == false}>
                                    <p style="text-align:right" class="total label-totales">{$.i18n.prop("factura.resumen.subTotal")}  <span id="lblSubtotal"> {subTotalGeneral} </span></p>
                                    <p style="text-align:right" class="total label-totales">{$.i18n.prop("factura.resumen.descuento")}  <span id="lblSubtotal"> {totalDescuentos} </span></p>
                                    <p style="text-align:right" class="total label-totales">{$.i18n.prop("factura.resumen.impuesto")}   <span id="lblSubtotal"> {totalImpuesto} </span></p>
                                </div>
                                <div class="precioTotalFactura" show={soloParaChinos == false}>
                                    <p class="total label-totales" style="text-align:right;">{$.i18n.prop("factura.resumen.total")}   <span id="lblTotal">{totalComprobante}</span></p>
                                </div>
                                <div class="precioTotalFactura" show={soloParaChinos == true}>
                                    <p class="total label-totalesChinos" style="text-align:right;">Total:  <span id="lblTotal">{totalComprobante}</span></p>
                                </div>

                                <div class="{claseCambioDinero}" show={mostrarCamposIngresoContado && soloParaChinos == false }>
                                    <p class="total label-totales" style="text-align:right;">{$.i18n.prop("factura.resumen.cambio")} <span id="lblTotal">{totalCambioPagarSTR}</span></p>    
                                </div>
                                <div class="{claseCambioDinero}" show={mostrarCamposIngresoContado && soloParaChinos == true }>
                                    <p class="total label-totalesChinos" style="text-align:right;">{$.i18n.prop("factura.resumen.cambio")} <span id="lblTotal">{totalCambioPagarSTR}</span></p>    
                                </div>

                            </article>
                        </aside>
                    </div><!-- fin box-body-->
				</div><!-- fin box -->
		    </div>
        </div>  
         
</div>  
<!--Fin Ventana de los billetes-->      
    <!--Ventana de los billetes-->
            <div class="row" show={mostrarFormularioPago}>
                <div   class="col-sx-12 col-sm-12 col-md-12 col-lg-12 " >
                    <!--Seccion de Billetes-->
                    <section  class="lista-articulos" >
                        <div class="product-item" each={billetes}   onclick={_sumarBilletes}>
                            <img style = "height:110px;width:180px" alt="" class="img-responsive " src="{imagen}">
                            <a href="#">{modena} {descripcion}</a>
                        </div>
                    </section>
                   <!--Fin Seccion de Billetes-->
                </div> 
            </div>       
        <!--Fin Ventana de los billetes--> 
 <div class="ventaEsperaSeleccionada" show={factura.id !=null}>
       <div class="tituloVentaEspera"> 
           Venta en Espera: {factura.consecutivoProforma != null && factura.consecutivoProforma.length > 0  ?factura.consecutivoProforma :factura.id} 
            {factura.cliente.nombreCompleto === 'CLIENTE_FRECUENTE'? factura.nombreFactura.length > 0?"-Nombre:" +factura.nombreFactura:'Sin Cliente Asociado' :"-Nombre:"+factura.cliente.nombreCompleto} 
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
                           <div class= "labelBotones"     onclick = {_ReimprimirFactura} title="{$.i18n.prop("btn.tiquete")}"> {$.i18n.prop("factura.f6")}</div>
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
                                <input id="codigo" name ="codigo" class="campo codigo" type="text" placeholder="XXXXXXXXXXXXXXXXXX" />
                            </div>
                            <div class="inputCodigoPrecio">
                                <input onkeypress={__addPrecioDetail}  id="precioVenta" name ="precioVenta" class="campo precioVenta" type="number" step="any"  placeholder="Precio Ejemplo:600" />
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
                                <th style="width:4%"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.impuesto1")}    </div></th>
                                <th ><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.subTotal")}                     </div> </th> 
                            </tr>
                            </thead>
                            <tbody>
                            <tr each={detail}>
                                <td>
                                   <button  onclick={__removeProductFromDetail} class="btn btn-danger btn-xs btn-block">X</button>
                                   <button  onclick={__aplicarExoneracionAlDetalle} class="btn btn-primary btn-xs btn-block" title="Exonera"><i class="fa fa-book" aria-hidden="true"></i></button>
                                </td>
                                <td style="width:5%;"><h2>{numeroLinea}</h2></td>
                                <td><h2>{codigo}</h2></td>
                                <td><h2>{descripcion}</h2></td>
                                <td class="text-right" style="width:8%;">
                                    <input onclick={__CambiarCantidad} id= "cantidadDetalle" class="campo " type="number" placeholder="Cantidad Detalle" value = {cantidad.toFixed(3)} readonly />
                                </td>
                                <td class="text-right">
                                    <input   class="campo" type="text"  value = "{precioUnitario.toFixed(2)}" readonly />
                                </td>
                                <td class="text-right" style="width:4%">
                                    <input  onclick={__CambiarDescuento} class="campo" type="text"  value = "{porcentajeDesc.toFixed(2)}" readonly/>
                                </td>
                                <td class="text-right" style="width:4%">
                                    <input  class="campo" type="text"  value = "{impuesto}" readonly/>
                                </td>
                                <td class="text-right" style="width:4%">
                                    <input  class="campo" type="text"  value = "{impuesto1}" readonly/>
                                </td>
                                <td class="text-right">
                                    <input  class="campo" type="text"  value = "{montoTotalLinea.toFixed(2)}" readonly />
                                </td>
                            </tr>
                            </tbody>
                        </table>          
                    </div>
                </div>
                <section class="cabecera-derecha">
                    <div class="tituloCantidadArticulos" show={cantArticulos>0}>
                        <div class="cantidadArticulosTitulo"> Producto.No={cantArticulos}</div>
                    </div>
                    <section   class="lista-factura-espera">
                        <div class="elementoVentaEspera"  each={facturas_espera.data}  onclick={__CargarFacturaEspera}>
                            <a show ="{consecutivoProforma.length>0?true:false}" href="#"  title="{cliente !=null?cliente.nombreCompleto:""}" class="fondoVentaEspera">P: {consecutivoProforma}</a>
                            <a show ="{consecutivoProforma.length == 0?true:false}"  href="#" class="fondoVentaEspera"  title="{cliente !=null?cliente.nombreCompleto:"Venta en espera"}">V: {id}</a>
                        </div>    
                     </section >
                     <aside class="left-sidebar">
                        <article class="clearfix">
                            <div onclick = {__MostrarFormularioDePago}  class="precioTotalFacturaContainer"  show={soloParaChinos == true}>
                                <div class="label-totalesComprobanteChino" >Total:  {totalComprobante}</div>
                            </div>
                            <div class="booking-info" show={soloParaChinos == false}>
                                    <p style="text-align:right" class="total label-totales">{$.i18n.prop("factura.resumen.subTotal")}  <span id="lblSubtotal"> {subTotalGeneral} </span></p>
                                    <p style="text-align:right" class="total label-totales">{$.i18n.prop("factura.resumen.descuento")}  <span id="lblSubtotal"> {totalDescuentos} </span></p>
                                    <p style="text-align:right" class="total label-totales">{$.i18n.prop("factura.resumen.impuesto")}   <span id="lblSubtotal"> {totalImpuesto} </span></p>
                            </div>
                            <div class="precioTotalFactura" show={soloParaChinos == false} onclick = {__MostrarFormularioDePago}>
                                    <p class="total label-totales" style="text-align:right;">{$.i18n.prop("factura.resumen.total")}   <span id="lblTotal">{totalComprobante}</span></p>
                            </div>
                            <div class="gananciaContainer">
                                <div class="formatoTituloGanancia">IG: {totalGananciaByProducto}</div>
                                <div class="formatoTituloGanancia">PG: {totalPesoByFacturaSTR}</div>
                            </div>
                            <div class="seleccionOtroPrecioVenta">
                                <div class="opcionPrecioPublico">
                                    <label class="titleListaPrecio">Lista Precios </label>  
                                    <select  class="form-control selectListaPrecios" >
                                        <option    value="1"  >Precio Publico</option>
                                        <option    value="2"  >Precio Mayoista</option>
                                        <option    value="3"  >Precio Especial</option>
                                    </select>
                                </div>
                            </div>

                            <div class="seleccionOtroPrecioVenta">
                                <div class="opcionPrecioPublico">
                                    <label class="titleListaPrecio">Actividades Comerciales </label>  
                                    <select onchange= {__AsignarActividad} class="form-control selectActividadComercial"  name="selectActividadComercial" id="selectActividadComercial" >
                                        <option  each={empresaActividadComercial}  value="{codigo}"   >{codigo}-{descripcion}</option>
                                    </select>
                                </div>
                            </div>

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
<!--Modal mostrar Facturas del Dias -->
<div id='modalFacturasDia' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("factura.listar.dia")} </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <table id="tableListarFacturasDia" class="display table responsive table-hover nowrap table-condensed tableListarFacturasDia "   cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                
                                <th class = "table-header" >{$.i18n.prop("factura.fecha.emision")}            </th>
                                <th class = "table-header" >{$.i18n.prop("factura.documento")}                </th>
                                <th class = "table-header" >{$.i18n.prop("factura.cliente")}                  </th>
                                <th class = "table-header" >{$.i18n.prop("factura.linea.detalle.impuesto")}   </th>
                                <th class = "table-header" >{$.i18n.prop("factura.linea.detalle.descuento")}  </th>
                                <th class = "table-header" >{$.i18n.prop("factura.total")}                    </th>
                                <th class = "table-header" >{$.i18n.prop("listado.acciones")}                 </th>
                            </tr>
                            </thead>
                                <tfoot style="display: table-header-group;">
                                    <tr>
                                        
                                        <th>{$.i18n.prop("factura.fecha.emision")}            </th>
                                        <th>{$.i18n.prop("factura.documento")}                </th>
                                        <th>{$.i18n.prop("factura.cliente")}                  </th>
                                        <th>{$.i18n.prop("factura.linea.detalle.impuesto")}   </th>
                                        <th>{$.i18n.prop("factura.linea.detalle.descuento")}  </th>
                                        <th>{$.i18n.prop("factura.total")}                    </th>
                                        <th>                                                  </th>
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
                                
                                <th style="width:5%" class="table-header">{$.i18n.prop("cliente.cedula")}            </th>
                                <th class="table-header">{$.i18n.prop("cliente.nombreCompleto")}    </th>
                                <th style="width:8%"  class="table-header">{$.i18n.prop("cliente.correoElectronico")} </th>
                                <th class="table-header">{$.i18n.prop("cliente.nombreComercial")}   </th>
                                <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                                
                            </thead>
                            <tfoot style="display: table-header-group;">
                                <tr>
                                    
                                    <th style="width:5%">{$.i18n.prop("cliente.cedula")}           </th>
                                    <th>{$.i18n.prop("cliente.nombreCompleto")}   </th>
                                    <th style="width:8%">{$.i18n.prop("cliente.correoElectronico")}</th>
                                    <th>{$.i18n.prop("cliente.nombreComercial")}   </th>
                                    <th>                                          </th>
                                    
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
 

<!--Modal Cambiar Cantidad-->
<div id='modalCambiarCantidad' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.cantidad")}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                        <div class="form-group has-success">
                            <label >Cantidad:</label>
                            <input  type="number"  class="form-control cambiarCantidadArticulo" id="cambiarCantidadArticulo" name = "cambiarCantidadArticulo" autofocus="autofocus" min="0">
                        </div>
                    </div>
                </div> 
            </div>
            <div class="modal-footer">
                <button type="button" onclick ="{__recalculacionDelDetalle}" class="btn-green btn-edit pull-right">{$.i18n.prop("btn.aplicar")}</button>
            </div>
        </div>
    </div>
</div>

<!--Fin Cambiar Cantidad-->


<!--Modal Cambiar Descuento-->
<div id='modalCambiarDescuento' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.descuento")}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                        <div class="form-group has-success">
                            <label >{$.i18n.prop("factura.linea.detalle.descuento")}</label>
                            <input  type="number"  class="form-control aplicarDescuento" id="aplicarDescuento" name = "aplicarDescuento" autofocus="autofocus" min="0">
                        </div>
                    </div>
                </div> 

            </div>
            <div class="modal-footer">
                <button type="button" onclick ="{__actualizarDescuento}" class="btn-green btn-edit pull-right">{$.i18n.prop("btn.aplicar")}</button>
            </div>
        </div>
    </div>
</div>
<!--Fin Cambiar Descuento-->

<!--Modal mostrar Clientes de una sucursal -->
<div id="modalExoneracion" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> Aplicar Exoneracion al Producto</h4>
            </div>
            <div class="modal-body">
                <form id="formularioExoneracion">
                    <div class="contenedorExoneracion">
                        <div class="row">
                            <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                                <div class="form-group ">
                                    <label for="pago_tipoVentaL">{$.i18n.prop("factura.linea.detalle.codigo")} </label> 
                                    <input  type="text"  class="form-control " autofocus="autofocus"  value= "{itemExonerar.codigo}" readonly>                    
                                </div>
                            </div>
                            <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                                <div class="form-group ">
                                    <label for="pago_tipoVentaL">{$.i18n.prop("factura.linea.detalle.descripcion")} </label> 
                                    <input  type="text"  class="form-control " autofocus="autofocus"  value ="{itemExonerar.descripcion}" readonly>                    
                                </div>
                            </div>
                        </div>  

                        <div class="row">
                            <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                                <div class="form-group ">
                                    <label for="pago_tipoVentaL">{$.i18n.prop("factura.tipo.documento")} </label> 
                                    <select class="form-control tipoDocExonerado" id="tipoDocExonerado" name="tipoDocExonerado"   >
                                        <option each={comboTipoDocumentos} value="{estado}" selected="{montoExoneracion.tipoDoc ==estado?true:false}" >{descripcion}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                                <div class="form-group ">
                                    <label for="pago_tipoVentaL">#Compra Exoneracion </label> 
                                    <input  type="text"  class="form-control numeroDocumentoExonerado" id="numeroDocumentoExonerado" name = "numeroDocumentoExonerado" autofocus="autofocus"  value ="{itemExonerar.numeroDocumentoExonerado}">                    
                                </div>
                            </div>
                        </div>  
                        <div class="row">
                            <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                                <div class="form-group ">
                                    <label for="pago_tipoVentaL">Nombre del Cliente/Institucion Aplicar </label> 
                                    <input  type="text"  class="form-control nombreInstitucionExoneracion" id="nombreInstitucionExoneracion" name = "nombreInstitucionExoneracion" autofocus="autofocus"  value ="{itemExonerar.nombreInstitucionExoneracion}">                    
                                </div>
                            </div>
                            <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                                <div class="form-group ">
                                    <label for="pago_tipoVentaL">Fecha Exoneracion  </label> 
                                    <div  class="form-group input-group date datepickerFechaEmisionExoneracion" data-provide="datepicker"  data-date-start-date="30d" data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control fechaEmisionExoneracion" name="fechaEmisionExoneracion" id="fechaEmisionExoneracion" value="{itemExonerar.fechaCredito}" >
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                                <div class="form-group ">
                                    <label for="pago_tipoVentaL">Monto Total Impuesto del Producto  </label> 
                                    <input  type="text"  class="form-control "  value = "{itemExonerar.montoTotalImpuestoExonerarSTR}" autofocus="autofocus" readonly>                    
                                </div>
                            </div>   
                            <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                                <div class="form-group">
                                    <label for="pago_tipoVentaL">% Porcentaje a Exonerar  </label> 
                                    <input  type="text"  class="form-control porcentajeExoneracion" id="porcentajeExoneracion" name = "porcentajeExoneracion" autofocus="autofocus"  value="{itemExonerar.porcentajeExoneracion}">                    
                                </div>
                            </div>

                        </div> 
                        <div class="form-group ">
                            <label for="pago_tipoVentaL">Total Aplicado a Exonerar  </label> 
                            <input  type="text"  class="form-control montoExoneracion" id="montoExoneracion" name = "montoExoneracion" autofocus="autofocus" rvalue="{itemExonerar.montoExoneracion}" readonly>                    
                        </div>
                    </div>    
                </form> 
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back  btn_big  pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
                 <button type="button" onclick ="{__actualizarExoneracion}" class="btn-green btn_big btn-edit pull-right">{$.i18n.prop("btn.aplicar")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->



<style type="text/css"  >
.contenedorExoneracion{

}
.label-totalesComprobanteChino {
    display: flex;
    flex: 1;
    font-weight: 600 !important;
    font-size: 37px !important;
    font-family: Roboto,sans-serif !important;
    color: #30ed17 !important;
    text-shadow: 0px 0px 1px #ffffff;
    font-style: italic;
    border-collapse: separate;
    cursor: pointer;
    margin: 2%!important;
    text-align: center !important;
    background-color: black !important;
    box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
    border-radius: 25px !important;
    -webkit-transition: background-color 100ms linear;
    -moz-transition: background-color 100ms linear;
    -o-transition: background-color 100ms linear;
    -ms-transition: background-color 100ms linear;
    transition: background-color 100ms linear;
}
.titleListaPrecio{
    color:blue;
    text-decoration:underline;
}
.tituloDetalle{
    text-align: center;
    text-decoration: none;
    font-style: italic;
    color: black;
    font-weight: 600;
    font-size: 20px;
}
.btn-block {
    display: block;
    width: 100%;
    }    

.fondoVentaEspera{
    background: black;
    text-align: center;
    text-decoration: none;
    text-shadow: rgb(255, 255, 255) 0px 0px 1px;
    font-style: italic;
    color: #e2f312 !important;
    font-weight: 600;
    font-size: 14px;
    
}
.ventaEsperaSeleccionada{
    display: flex;
    padding-bottom: 0.2%;
}  
.ventaEsperaSeleccionada .tituloVentaEspera{
    color: yellow;
    background: black;
    font-weight: 700;
}                           
.contenedorFactura{
    display: flex;
    flex: 1;
    border: 1px solid #3c8dbc;
    background: #ffffff;
 

}

.precioTotalFacturaContainer{
    display:flex;
    flex:1;
}

.codigoBarraPrecioContainer{
    display:flex;
    flex:1;
}
.codigoBarraPrecioContainer .inputCodigoPrecio{
    flex:1;
    padding-left: 2%;
    padding-right: 2%;
    padding-bottom: 1%;
}

.labelBotones {
    font-weight: 600 !important;
    font-size: 19px !important;
    font-family: Roboto,sans-serif !important;
    color: #ffffff !important;
    text-shadow: 0px 0px 1px #ffffff;
    font-style: italic;
    text-align: left;
    line-height: 30px;
    border-collapse: separate;
    text-align: center;
    cursor: pointer;
    text-align: center !important;
    background-color: black !important;
    box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
    border-radius: 5px;
    -webkit-transition: background-color 100ms linear;
    -moz-transition: background-color 100ms linear;
    -o-transition: background-color 100ms linear;
    -ms-transition: background-color 100ms linear;
    transition: background-color 100ms linear;
}
@media screen and (max-width: 1024px) {
  .labelBotones {
    font-size: 14px !important;
  }
  .label-totalesComprobante{
      font-size: 18px !important;
  }
  .cantidadArticulosTitulo{
      font-size: 10px !important;
  }
  .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_1{
      font-size: 14px !important;
  }
  .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_2{
      font-size: 14px !important;
  }
}

.botonesFuncionalContainer{
    display:flex;
    flex:1;

}
.contenedorFactura .cabecera-izquierda .botonesFuncionalContainer{
  display:flex;
}

.contenedorFactura .cabecera-izquierda .botonesFuncionalContainer .botonesFuncional{
  flex:1;
  padding-right: 1%;
  padding-bottom: 2%;
}




.gananciaContainer{
    display:flex;
    flex:1;
}
.gananciaContainer .formatoTituloGanancia{
    flex:1;
    color: black;
    font-size: 15px;
    font-weight: bolder;
}
.tituloCantidadArticulos{
    display:flex;
}
.contenedorFactura .cabecera-derecha .tituloCantidadArticulos .cantidadArticulosTitulo{
  font-weight: 600 !important;
    font-size: 30px !important;
    font-family: Roboto,sans-serif !important;
    color: #edea17 !important;
    text-shadow: 0px 0px 1px #ffffff;
    font-style: italic;
    text-align: left;
    /* padding-left: 20px; */
    line-height: 30px;
    border-collapse: separate;
    text-align: center;
    cursor: pointer;
    /* padding: 5px; */
    /* margin: 5px; */
    /* border: none; */
    text-align: center !important;
    background-color: black !important;
    box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
    border-radius: 5px;
    -webkit-transition: background-color 100ms linear;
    -moz-transition: background-color 100ms linear;
    -o-transition: background-color 100ms linear;
    -ms-transition: background-color 100ms linear;
    transition: background-color 100ms linear;

}

.tituloProductoIngresadoContainer{
    display:flex;
    flex:1;
}
.tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado{
    flex: 1;
    display: flex;
    padding-left: 2%;
    padding-right: 2%;
 
}




.tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_1{
    flex: 0.15;
    font-weight: 600 !important;
    font-size: 36px !important;
    font-family: Roboto,sans-serif !important;
    color: yellow !important;
    text-shadow: 0px 0px 1px #ffffff;
    font-style: italic;
    text-align: left;
    /* padding-left: 20px; */
    /* line-height: 100%; */
    /* border-collapse: separate; */
    text-align: center;
     /* margin: 5px; */
    border: none;
    text-align: center !important;
    background-color: black !important;
    /* box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20); */
    /* border-radius: 5.tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_1{px; */
    /* -webkit-transition: background-color 100ms linear; */
    -moz-transition: background-color 100ms linear;
    -o-transition: background-color 100ms linear;
    -ms-transition: background-color 100ms linear;
    /* transition: background-color 100ms linear; */
}

.tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_2{
    flex: 1;
    font-weight: 600 !important;
    font-size: 36px !important;
    font-family: Roboto,sans-serif !important;
    color: #30ed17 !important;
    text-shadow: 0px 0px 1px #ffffff;
    font-style: italic;
    text-align: left;
    /* padding-left: 20px; */
    /* line-height: 100%; */
    /* border-collapse: separate; */
    text-align: center;
     /* margin: 5px; */
    border: none;
    text-align: center !important;
    background-color: black !important;
    /* box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20); */
    /* border-radius: 5px; */
    /* -webkit-transition: background-color 100ms linear; */
    -moz-transition: background-color 100ms linear;
    -o-transition: background-color 100ms linear;
    -ms-transition: background-color 100ms linear;
    /* transition: background-color 100ms linear; */
}



</style>    
<script>
    var self = this;
    self.colorVentaEspera = 'green'
    self.parametros   = opts.parametros; 
    // Detalle de la factura es una coleccion de articulos
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.rutaAutorizada        = ""    // llama al modal correspondiente
    self.autorizarBorrado      = 0    // 0 = no autoriza 1 = si autorisa 

    self.comboCondicionPagos   = []
    self.comboTipoDocumentos   = []
    self.subTotalGeneral       = 0
    self.codigoBarraFueraPantalla = ""
    self.totalDescuentos       = 0
    self.totalImpuesto         = 0
    self.pesoPrioridad =  0
    self.numeroLinea =0
    self.cantArticulos =0
    self.bloqueoFactura = 0;
    self.precioUltimo = ""
    self.tipoCambio = {
        total:0,
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
    self.clientes              = {data:[]}
    self.detalleFactura        = {data:[]}
    self.cliente               = {}
    self.vendedor              = {
        id:null,
        nombreCompleto:""
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
    self.subTotalGeneral               = 0
    self.vueltoImprimir               = 0
    self.todasProvincias               = {data:[]}
    self.todosCantones                 = {data:[]}
    self.todosDistritos                = {data:[]}
    self.todosBarrios                  = {data:[]}
    self.cantones                      = []
    self.distritos                     = []
    self.barrios                       = []
    self.actividadesComerciales        = []
    self.mostrarListadoArticulos == false
    self.empresa              = {}
    self.subTotalGeneral               = 0
    self.totalDescuentos               = 0
    self.totalImpuesto                 = 0
    self.totalComprobante              = 0
    self.primeraVezBilleteClick = false
    self.totalCambioPagar              = 0
    self.semaforo_carga_articulos = false;
    self.mostarAbrirCajon = true
    self.informacionAbrirCajon = "."
    self.soloParaChinos = false
    self.totalGananciaByProducto = 0
    self.totalPesoByFactura = 0
    self.rol = {
      rolAdministrador:0
    }
    self.tamanoLetra = "tamanoLetraConBanco"
    self.labelTotales = "labelTotalesConBanco"
    self.campoTotales = "campoTotalesConBanco"

    self.actividadComercial = {
        codigo:"",
        descripcion:""
    }
    self.on('mount',function(){

        $("#formularioFactura").validate(reglasDeValidacionFactura());
        __informacionData()
        __informacionData_vendedores()
        __InicializarTabla('.tableListaCliente')
        __InicializarTabla('.tableListaInventario')
        __InicializarTabla('.tableListaVendedor')
        __InicializarTabla('.tableListarFacturasDia')
        agregarInputsCombos_Articulo()
        __ListaFacturasEnEspera()
        __comboCondicionPago()
        __RolAdministrador()
       __Teclas()
       __TipoCambio()
       cargaBilletes()
       __InformacionDataTableDia()
       __ListaDeClientes()
       __ListaActividadesComercales()
       //__ListaArticulosUsoInterno()
       __ListaDeVendedores()
       __agregarArticulos()
       _Empresa()
       
       
        $('.codigo').select()
        $(".codigo").focus()
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
        });
        
       

       // var timer;
       // $("#codigo").on('keyup',function() {
       //     timer && clearTimeout(timer);
       //     timer = setTimeout(postData, 100);
       // });
            $.fn.delayPasteKeyUp = function(fn, ms)
        {
            var timer = 0;
            $(this).on("propertychange input", function()
            {
                clearTimeout(timer);
                timer = setTimeout(fn, ms);
            });
        };
        var retrievedObject = JSON.parse(localStorage.getItem('DetallesNueva'));
        if(retrievedObject != null){
            self.detail = retrievedObject
            var facturaObject = JSON.parse(localStorage.getItem('facturaNueva'));
            self.factura = facturaObject
            self.update()
            __calculate()

        }
        __Eventos()
         window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
             actualizaElPlazoDiasCredito();
             //disableF5(evento);
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
      
    }, false );
     
    })
    function disableF5(e) { if ((e.which || e.keyCode) == 116) e.preventDefault(); };


__actualizarExoneracion(e){
    if ($("#formularioExoneracion").valid()) {

    }else{
        mensajeError("Error Falta ingresar la informacion")
        return 
 
    }
}

function __Eventos(){
    $("#formularioExoneracion").validate(reglasDeValidacionExoneracion());
    $("#nombreInstitucionExoneracion").attr("maxlength", 160);
    $("#numeroDocumentoExonerado").attr("maxlength", 40);
    $('#porcentajeExoneracion').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
}

/**
* Camps requeridos
**/
var reglasDeValidacionExoneracion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
             numeroDocumentoExonerado:{
                 maxlength:40,
                 required : true,
                 minlength:1,
             },
             nombreInstitucionExoneracion:{
                 maxlength:160,
             } ,        
             fechaEmisionExoneracion:{
                 required : true,
             }         
             ,        
             montoTotalImpuestoExonerar:{
                 required : true,
                 numeroMayorCero:true,
                 number:true,
             } 
             ,        
             porcentajeExoneracion:{
                 required : true,
                 numeroMayorCero:true,
                 number:true,
             } 
             ,        
             montoExoneracion:{
                 required : true,
                 numeroMayorCero:true,
                 number:true,
             } 

		},
		ignore : []

	});
	return validationOptions;
};


__aplicarExoneracionAlDetalle(e) {
    self.itemExonerar = e.item;
    self.itemExonerar.montoTotalImpuestoExonerarSTR =formatoDecimales(self.itemExonerar.montoImpuesto + self.itemExonerar.montoImpuesto1,2) 
    self.update()
     if (self.itemExonerar.montoImpuesto > 0 || self.itemExonerar.montoImpuesto1 > 0) {
        $('#modalExoneracion').modal({backdrop: 'static', keyboard: true}) 
        $('#modalExoneracion').modal('show')
 
    }else{
        mensajeError("Error Producto no tiene impuestos incluidos , revisar")
        return 
 
    }
   
}

function agregarExoneracionAlProducto(){

}




__ActualizarPlazoCredito(){
    actualizaElPlazoDiasCredito();
}

__AsignarActividad(e){
    BuscarActividadComercial()
}

function BuscarActividadComercial(){
    var codigo =$('#selectActividadComercial').val()
    $.each(self.empresaActividadComercial, function( index, modeloTabla ) {
        if(modeloTabla.codigo == codigo  ){
           self.actividadComercial.descripcion = modeloTabla.codigo +"-" + modeloTabla.descripcion
            self.actividadComercial.codigo =  codigo
            self.factura.codigoActividad = codigo
            self.update()
        }

    })
}


function actualizaElPlazoDiasCredito(){
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

/**
* Verificar el Rol Admnistrador
**/
function __RolAdministrador(){
    $.ajax({
        url: "RolUsuarioAjax.do",
        datatype: "json",
        global: false,
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
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

/**
* Validar seguridad de ruta autorizada
**/ 
__SeguridadVentas(){
    if(self.rol.rolAdministrador == 0){
        return true
    }
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
                	 swal({
                         type: 'error',
                         title:"No autorizado",
                         showConfirmButton: false,
                         timer: 1500
                     });
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
                        $(self.rutaAutorizada).modal({backdrop: 'static', keyboard: true}) 
                        $(self.rutaAutorizada).modal('show')    	
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

                    return true;
               	}else{
                    self.rutaAutorizada = '';
                    self.update()
                    swal({
                        type: 'error',
                        title:"No autorizado",
                        showConfirmButton: false,
                        timer: 1500
                    })      
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

_SeleccionarEfectivo(){
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
/**
 * Reimprime la factura
 **/
_ReimprimirFactura(){
    reimprimirFacturaEnMomento()

}
function reimprimirFacturaEnMomento(){

  if(self.facturaReimprimir ==null){
    var retrievedObject = JSON.parse(localStorage.getItem('facturaReimprimir'));
    if(retrievedObject != null){
       self.facturaReimprimir = retrievedObject
       self.update()
    }    
  }
  if(self.facturaReimprimir ==null){
      return
  }
  consultaFactura(self.facturaReimprimir,0)
  $('.codigo').select()
  $(".codigo").focus() 
}
/**
* Consultar la empresa
**/
function _Empresa(){
     $.ajax({
        url: "ParametrosEmpresaAjax.do",
        datatype: "json",
        global: false,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.empresa =   modeloTabla
                       self.vueltoImprimir = modeloTabla.vueltoImprimir
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
                       __ComboTipoDocumentos()
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
/**
* LLimpiar Formulario
**/
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
            $("#usuarioSistema").val("")
            $("#claveSistema").val("")
            $('#modalRolUsuario').modal({backdrop: 'static', keyboard: true}) 
            $('#modalRolUsuario').modal('show')     
        }else{
           refrescarPagina()
        }

    }else{
        refrescarPagina()
    }

}
/**
* limpiar pantalla
**/
function __LimpiarClick(){  
    self.totalPesoByFacturaSTR = ''
    self.totalPesoByFactura  =0
    self.totalGananciaByProducto =0
    $(".plazoCredito").val(null)   
    $(".fechaCredito").val(null)   
    $(".totalEfectivo").val(null)   
    $(".totalTarjeta").val(null)   
    $(".totalBanco").val(null)   
    $(".nota").val(null)    
    $(".direccion").val(null)   
    $('.condicionVenta').prop("selectedIndex", 0);
    $('.tipoDoc').prop("selectedIndex", 0);
    $(".nota").attr("maxlength", 80);
    $('.selectActividadComercial').prop("selectedIndex", 0);
    self.cliente               = {}
    self.vendedor              = {
        id:null,
        nombreCompleto:""
    };
    self.mostrarCamposIngresoContado = true
    self.descripcionArticulo = ""
    self.precioUltimo = ""
    self.cantidadEnterFacturar = 0
    self.update()
}
/**
* Camps requeridos
**/
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
/**
* listado por Dia
**/
_ListaFacturasDia(){
   ListadoFacturasDelDia()
}
/**
*  Facturas del Dia
**/
function ListadoFacturasDelDia(){
      $(".tableListarFacturasDia").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListarFacturasDia')  
        $.ajax({
            url: "ListarFacturasDelDiaAjax.do",
            datatype: "json",
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTableDia();
                    loadListar(".tableListarFacturasDia",idioma_espanol,self.formato_tabla_dias,result.aaData)
                    agregarInputsCombos_Facturas_Dias();
                    ActivarEventoFiltro(".tableListarFacturasDia")
                    $('#modalFacturasDia').modal('show')    
                     __reimprimir()
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
        "sDom": 'lrtip',
        "order": [0, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
       
        "columns": formatoTabla,
    });  
    $(table).dataTable().fnClearTable();
    $(table).dataTable().fnAddData(data);        
}
/**
* formato del listado de facturas del dia
**/
function __InformacionDataTableDia(){
    self.formato_tabla_dias = [ 
                               {'data' :'fechaEmisionSTR'   ,"name":"fechaEmisionSTR"    ,"title" : $.i18n.prop("factura.fecha.emision")     ,"autoWidth" :true ,
                               },
                             
                               {'data' :'numeroConsecutivo'                    ,"name":"numeroConsecutivo"                     ,"title" : $.i18n.prop("factura.documento")   ,"autoWidth" :true ,
                                   "render":function(numeroConsecutivo,type, row){
									    return __TipoDocumentos(numeroConsecutivo,row)
	 							    }
                               },
                                {'data' :'cliente'                    ,"name":"cliente"                     ,"title" : $.i18n.prop("factura.cliente")   ,"autoWidth" :true ,
                                   "render":function(cliente,type, row){
									    return cliente ==null?"":cliente.cedula != "999999999999"?cliente.nombreCompleto:row.nombreFactura;
	 							    }
                               },
                               {'data' :'totalImpuestoSTR'               ,"name":"totalImpuestoSTR"        ,"title" : $.i18n.prop("factura.linea.detalle.impuesto")     ,"autoWidth" :true },
                               {'data' :'totalDescuentosSTR'             ,"name":"totalDescuentosSTR"      ,"title" : $.i18n.prop("factura.linea.detalle.descuento")  ,"autoWidth" :true },
                               {'data' :'totalComprobanteSTR'            ,"name":"totalComprobanteSTR"     ,"title" : $.i18n.prop("factura.total") ,"autoWidth" :true },
                               {'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
                               },
	      		            ];
    self.update();
   
}
/**
*Opciones del listado de las facturas por dia
**/
function __Opciones(){
  var agregar  = '<a href="#"  class="btn btnReimprimir btn-primary form-control" title="Imprimir" role="button"> <i class="glyphicon glyphicon glyphicon-print"></i></a>';
  return  agregar;
}
/**
*imprimir
**/
function __reimprimir(){
	$('#tableListarFacturasDia').on('click','.btnReimprimir',function(e){
		var table = $('#tableListarFacturasDia').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        consultaFactura(data,1)
	});
}
/**
* Consultar la factura
**/
function consultaFactura(data,tipoImpresion){
    self.contador = 0
    self.update()
    var modelo = null
     $.ajax({
        url: "MostrarFacturaAjax",
        datatype: "json",
        data: {idFactura:data.id},
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                      modelo = modeloTabla   
                      var parametros = {
                          factura:modelo,
                          facturaDia:tipoImpresion
                      }
                      console.log("consultaFactura")
                      riot.mount('ptv-imprimir',{parametros:parametros});
                    });
                    
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}
/**
* Tipo de Documento
**/
function __TipoDocumentos(numeroConsecutivo,row){
    switch(row.tipoDoc) {
    case "04":
          return  "Tiq:"+numeroConsecutivo
        break;
    case "01":
        return  "Fact:"+numeroConsecutivo
        break;
    case "02":
        return  "N.Debito:"+numeroConsecutivo
        break;
    case "03":
        return  "N.Credito:"+numeroConsecutivo
        break;
    case "88":
        return  "Proforma:"+row.id
        break;
    case "87":
        return  "TiqueteInterno:"+row.id
        break;

    default:
        return  numeroConsecutivo
}
}
/**
* Aplicar el descuento
**/
__CambiarDescuento(e){
    self.item = e.item; 
    self.rutaAutorizada = '';
    self.update()
    if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        self.rutaAutorizada = '#modalCambiarDescuento';
        self.update()
        $("#usuarioSistema").val("")
        $("#claveSistema").val("")
        $('#modalRolUsuario').modal({backdrop: 'static', keyboard: true}) 
        $('#modalRolUsuario').modal('show')     
   }else{
        $( "#aplicarDescuento" ).focus()
        $( "#aplicarDescuento" ).val(null)
        $('#modalCambiarDescuento').modal({backdrop: 'static', keyboard: true}) 
        $('#modalCambiarDescuento').modal('show')      
    }
}
/**
*Cambiar Cantidad del Articulo
**/
__CambiarCantidad(e){
   var cantidad = e.currentTarget.value;
   self.item = e.item; 
   self.rutaAutorizada = '';
   self.update()
   if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        self.rutaAutorizada = '#modalCambiarCantidad';
        self.update()
        $("#usuarioSistema").val("")
        $("#claveSistema").val("")
        $('#modalRolUsuario').modal({backdrop: 'static', keyboard: true}) 
        $('#modalRolUsuario').modal('show')     
   }else{
        $( "#cambiarCantidadArticulo" ).focus()
        $( "#cambiarCantidadArticulo" ).val(cantidad)
        $('#modalCambiarCantidad').modal({backdrop: 'static', keyboard: true}) 
        $('#modalCambiarCantidad').modal('show')      
   }
 }
/**
* Tipo Cambio de moneda
**/
function __TipoCambio(){
    self.tipoCambio = {
        total:0,
        id:null
    }
    self.update()
    $.ajax({
        url: "MostrarTipoCambioActivoAjax.do",
        datatype: "json",
        global: false,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.tipoCambio = modeloTabla
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
/**
*  Obtiene el valor de lo digitado en el campo de efectivo
**/
__TotalDeEfectivoAPagar(e){
    self.totalCambioPagarSTR = 0
    self.factura.totalEfectivo = __valorNumerico(e.target.value) 
    if(self.factura.totalEfectivo ==0 ){
        self.cantidadEnterFacturar = 0
    }
    self.update()
    _calculoEnterPago()
}
/**
*  Obtiene el valor de lo digitado en el campo de Tarjeta
**/
__TotalDeTarjetaAPagar(e){
    self.factura.totalTarjeta = __valorNumerico(e.target.value) 
    if(self.factura.totalTarjeta ==0 ){
        self.cantidadEnterFacturar = 0
    }
    self.update()
    _calculoEnterPago()
}
/**
*  Obtiene el valor de lo digitado en el campo de Banco
**/
__TotalDeBancoAPagar(e){
    self.factura.totalBanco = __valorNumerico(e.target.value) 
    if(self.factura.totalBanco ==0 ){
        self.cantidadEnterFacturar = 0
    }
    self.update()
    _calculoEnterPago()
}
/**
*   Calculo del cambio entregar en el evento onblur
**/
__CalculaCambioAEntregarOnblur(e){
    _calculoEnterPago()
}
/**
*   Calculo del cambio entregar en el evento keyPress
**/
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
/**
* Enter es para verificar si el cliente presiono dos veces enter y mandalo a facturar
**/
function __EnterFacturar(){
    if(self.empresa.enterFacturar == 0){
        return
    }
    if(self.empresa.abrirSinComanda == 1 || self.empresa.abrirConComanda == 1 ){
      //Abrir cajon sin comanda
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
/**
 * Listar codigos  llamado del modal para presentar los articulos
 **/   
 __ListaDecodigos(){
     ListarCodigosArticulos()
 }
/**
Lista de articulos
**/
 function ListarCodigosArticulos(){
    self.mostrarListadoArticulos = true
    self.update()
    $('.descArticulo').val(null)
    $('.codigoArt').val(null)
    $(".tableListarArticulos").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
    $('#descArticulo').select()
    $('#descArticulo').focus()
    $('#modalInventario').modal('show')    
 }
/**
*  Buscar la Factura Pendiente en espera
**/
__CargarFacturaEspera(e){
    self.factura =  e.item
    self.update()
   __FacturaEnEspera(e.item)
}
/**
** Se aplica o se crea una Factura cargada en la pantalla
**/
__AplicarYcrearFactura(){
 aplicarFactura(2)
}
/**
* Aplicando factura temporal
**/
__AplicarYcrearFacturaTemporal(){
 __OpcionAbrirCajon()
 aplicarFactura(1)
}
/**
* Aplicar la factura
**/
function aplicarFactura(estado){
    if(self.detail.length == 0 ){
         $('.precioVenta').val(null)
        $('.codigo').val("")
        $('.codigo').focus()
         swal({
                type: 'error',
                title:$.i18n.prop("factura.alert.sin.detalles"),
                showConfirmButton: false,
                timer: 1500
                })
        $('.precioVenta').val(null)
        $('.codigo').val("")
        $('.codigo').focus()
        return
    }
    if($('#condicionVenta').val() == "02"  ){
        if($('#fechaCredito').val() == null || $('#fechaCredito').val() == 0){
           mensajeError($.i18n.prop("factura.alert.fechaCredito"))
            return
        }
        if($('#plazoCreditoL').val() < 0 || $('#plazoCreditoL').val() == null || $('#plazoCreditoL').val() == 0){
           mensajeError($.i18n.prop("factura.alert.plazoCredito"))
            return
        }
    }else{
        // Si no es credito y el estado no es pendiente se debe verificar si ingresaron el monto a pagar
        if($("#tipoDoc").val() !="88"){
            if(estado == 2){
                if(__valorNumerico($('#totalTarjeta').val()) == 0 && __valorNumerico($('#totalBanco').val()) == 0 && __valorNumerico($('#totalEfectivo').val()) == 0){
                    mensajeError($.i18n.prop("error.factura.monto.ingresado"))
                    return
                }
                var montoEntregado = __valorNumerico($('#totalTarjeta').val())  + __valorNumerico($('#totalBanco').val()) + __valorNumerico($('#totalEfectivo').val())
                montoEntregado = redondeoDecimales(__valorNumerico(montoEntregado),2)
                if(montoEntregado > 20000000){
                    mensajeError("Monto entregado es muy alto")
                    return
                }
                var resultado  = redondeoDecimales( __valorNumerico(self.factura.totalComprobante),2)
                if(__valorNumerico(resultado) > __valorNumerico(montoEntregado)  ){
                    mensajeError($.i18n.prop("error.factura.monto.ingresado.es.menor.ala.venta"))
                    return
                }
                //Si el cliente esta pagando con tajeta, banco debe ser igual a la venta
                var tarjeta = __valorNumerico(self.factura.totalTarjeta)
                var banco = __valorNumerico(self.factura.totalBanco)
                if(tarjeta != 0 || banco !=0){
                    if(resultado != montoEntregado  ){
                        mensajeError($.i18n.prop("error.factura.monto.tarjeta.banco.igual.venta"))
                    return
                        
                    }
                }

            }
       }
            
    } 
    crearFactura(estado)  
}
/**
* Limpiar Pantalla
**/
__Limpiar(){
    __Init()
}
/**
*  Inicializar las variables de trabajos
**/
function __Init(){
    self.bloqueoFactura = 0;
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
    self.clientes              = {data:[]}
    self.detalleFactura        ={data:[]}
    self.cliente               = {};
    self.vendedor              = {
        id:null,
        nombreCompleto:""
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
    // Tipo de Pagos
     __comboCondicionPago()
     //Tipos de Documentos
      __ComboTipoDocumentos()
    __ListaFacturasEnEspera()
    $('.codigo').select()
    $(".codigo").focus()
    localStorage.setItem('DetallesNueva', JSON.stringify(self.detail));
    localStorage.setItem('facturaNueva', JSON.stringify(self.factura));
}
/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(factura){
     __Init()
    $.ajax({
        url: "ListarDetlleByFacturaAjax.do", 
        datatype: "json",
        data: {idFactura:factura.id},
        method:"POST",
        success: function (data) {
            if(data.aaData.length > 0){
               cargarDetallesFacturaEnEspera(data.aaData)
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}
/**
*  Cargar detalles Factura en espera
**/
function cargarDetallesFacturaEnEspera(data){
    $('.nota').val(null);
    $('.correoAlternativo').val(null);
    $('.nombreFactura').val(null);
    self.detail = [];
    self.numeroLinea =  0
    self.cantArticulos =  0
    self.pesoPrioridad = 0
    self.update()
    $.each(data, function( index, modeloTabla ) {
        self.factura = modeloTabla.factura
        $('.nota').val(self.factura.nota);
        $('.correoAlternativo').val(self.factura.correoAlternativo);
        $('.nombreFactura').val(self.factura.nombreFactura);
        self.factura.fechaCredito = self.factura.fechaCredito !=null?__displayDate_detail(self.factura.fechaCredito):null
        self.cliente              = modeloTabla.factura.cliente
        self.vendedor             = modeloTabla.factura.vendedor
        self.update()
        self.descripcionArticulo = modeloTabla.descripcion
        self.detail.push({
            numeroLinea     : modeloTabla.numeroLinea,
            pesoPrioridad    :modeloTabla.numeroLinea,
            codigo          : modeloTabla.codigo,
            tipoImpuesto    : modeloTabla.tipoImpuesto,
            tipoImpuesto1   : modeloTabla.tipoImpuesto1,
            descripcion     : modeloTabla.descripcion,
            cantidad        : parseFloat(modeloTabla.cantidad),
            precioUnitario  : parseFloat(modeloTabla.precioUnitario),
            impuesto        : parseFloat(modeloTabla.impuesto),
            impuesto1       : parseFloat(modeloTabla.impuesto1),
            montoImpuesto   : parseFloat(modeloTabla.montoImpuesto),
            montoImpuesto1  : parseFloat(modeloTabla.montoImpuesto1),
            montoDescuento  : parseFloat(modeloTabla.montoDescuento),
            porcentajeDesc  : parseFloat(modeloTabla.porcentajeDesc),
            subTotal        : parseFloat(modeloTabla.subTotal),
            montoTotalLinea : parseFloat(modeloTabla.montoTotalLinea),
            montoTotal      : parseFloat(modeloTabla.montoTotal),
            costo           : parseFloat(modeloTabla.costo),
            porcentajeGanancia :parseFloat(modeloTabla.porcentajeGanancia),
            montoGanancia :parseFloat(modeloTabla.montoGanancia),
            ganancia :parseFloat(__valorNumerico(modeloTabla.ganancia)),
            pesoTransporte :  parseFloat(modeloTabla.pesoTransporte),
            pesoTransporteTotal :parseFloat(modeloTabla.pesoTransporteTotal)
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
    $('#totalEfectivo').val(self.factura.totalComprobante)
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    $('#totalEfectivo').focus()
    $('#totalEfectivo').select()
     __calculate(); 
}
/** 
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('YYYY-MM-DD ');
}
/**
*  Crear Factura nueva
**/
function crearFactura(estado){
    
    BuscarActividadComercial()
    if( self.factura.codigoActividad.length == 0 ){
      mensajeError($.i18n.prop("error.factura.actividad.comercial.no.existe"))
      return
    }
    self.detalleFactura.data =self.detail
    self.update() 
    var fechaCreditoTemporal =condicionVenta.value == "02"?fechaCredito.value:new Date() 
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
    self.update();
    var dataTemporal = null

    var formulario = $("#formularioFactura").serialize();
    $.ajax({
        type : "POST",
        dataType : "json",
        data : formulario,
        url : "CrearFacturaAjax",
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                     mensajeAlertErrorOConfirmacion('error',data.message);    	
                }
            } else {
               	self.cantidadEnterFacturar =0
                self.update()
                serverMessageJsonClase(data);
                dataTemporal = data
                evaluarFactura(data)
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
    
}
/**
*Si fue facturada o tiquete
**/
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
            self.facturaImprimir   = modeloTabla
            self.bloqueoFactura = 1;
            self.update()
        });
        if(self.facturaImprimir.estado == 2 || self.facturaImprimir.estado == 3 || self.facturaImprimir.estado == 4){
                __Init()
                //Envia a la pantalla de impresion
                self.facturaReimprimir = self.facturaImprimir
                self.update()
                localStorage.setItem('facturaReimprimir', JSON.stringify(self.facturaReimprimir));
                if(self.vueltoImprimir == 0 && self.empresa.imprimirSiempre == 0){
                    var mensaje = self.facturaImprimir.numeroConsecutivo !=null ?"Cons# :"+   self.facturaImprimir.numeroConsecutivo:"Tiquete# :"+   self.facturaImprimir.id        
                    swal({
                        type: 'success',
                        title: mensaje,
                        showConfirmButton: false,
                        timer: 1500
                     })
                   
                }else{
                  var parametros = {
                          factura: self.facturaReimprimir ,
                          facturaDia:0
                      }
                      riot.mount('ptv-imprimir',{parametros:parametros});
                }
        }else{
                swal({
                type: 'success',
                title:data.message,
                showConfirmButton: false,
                timer: 1000
                })
                __Init()
                __ListaFacturasEnEspera()
        }
          
    }
}
/**
*  Lista de las facturas pendientes por el usuario
**/
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
/**
*  Obtiene el valor de lo digitado en el campo de Descuento
**/
__TotalDeDescuento(e){
    self.factura.porcentajeDesc = __valorNumerico(e.target.value) 
    self.update()
    __calculate()
}
/**
*   Retrocer a los ingresos de los codigos desde el formulario de ingresar el valor dinero a pagar
**/
_AtrasFacturaFinal(){
   self.mostrarFormularioPago = false
   self.mostarParaCrearNuevaFactura = true
   self.cantidadEnterFacturar = 0
   self.primeraVezBilleteClick = false
   self.error = false
   self.update()
   $('.codigo').val("")
   $('.codigo').select()
   $('.codigo').focus()
}
/**
*    Muesta el campo de la fecha de credito
**/
__formaPago(e){
    //Contado /sin cobro
    if(e.currentTarget.value == 1 || e.currentTarget.value == 3){
        self.mostrarCamposIngresoContado = true
    }
    //Credito
    if(e.currentTarget.value == 2){
        self.mostrarCamposIngresoContado = false
    }
    self.update()
    $('.fechaCredito').val(null)
    $('.datepickerFechaCredito').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-0d',
              todayHighlight:true,
            }
    );  
    $('.plazoCreditoL').val(0)  
}
/**
*   funcion para grabar la Factura en el back end
**/
__MostrarFormularioDePago(){
    mostrarPAgo()
}
/**
*Mostrar Pago
**/
function mostrarPAgo(){
     //No hay detalles registrados en la Factura
    if(self.detail.length == 0 ){
        $('.precioVenta').val(null)
        $('.codigo').val("")
        $('.codigo').focus()
        swal({
            type: 'error',
            title:$.i18n.prop("factura.alert.sin.detalles"),
            showConfirmButton: false,
            timer: 1500
        })
        $('.precioVenta').val(null)
        $('.codigo').val("")
        $('.codigo').focus()
        return
    }
    if(self.vueltoImprimir == 0){
        $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
        $('#totalTarjeta').val(null)
        $('#totalBanco').val(null)
    }else{
        $('#totalEfectivo').val(null)
        $('#totalTarjeta').val(null)
        $('#totalBanco').val(null)
    }
    getSubTotalGeneral()
    self.totalCambioPagar =0
    self.factura.totalCambioPagar =0
    self.mostarParaCrearNuevaFactura = false
    self.mostrarFormularioPago = true
    self.update()
    $('#totalEfectivo').focus()
    $('#totalEfectivo').select()
    self.factura.cambioMoneda = self.factura.totalVentaNeta / self.tipoCambio.total
    self.update()
}
/**
Lectura de Codigos
**/
function lecturaCodigo(leerCodigo){
    if ($('.codigo').val() == ""){
        if(self.empresa.enterFacturar == 0){
            return
        }
        if(self.cantidadEnterFacturar >= 1){
            self.cantidadEnterFacturar = 0
            self.update() 
             __EnviarFacturar()  
        }else{
            self.cantidadEnterFacturar = self.cantidadEnterFacturar + 1
            self.update()
        }
    }
    var codigo = leerCodigo
    var codigoActual = ""
    var cantidadAct =""
    var existe = false
     var existeMas = false
    for(i=0; i<codigo.length; i++){
         existeMas = codigo.charAt(i) == "+"?true : false
       if(existe == false){
          existe = codigo.charAt(i) == "*"?true : false  
          if(codigo.charAt(i) !="*"){
              codigoActual = codigoActual + codigo.charAt(i)  
          }
       }else{
           cantidadAct = cantidadAct + codigo.charAt(i)
       }
    }
// esto es para cuando un cliente quiere sumar varios productos
    if(existeMas == true){
       __sumarMasArticulo(codigo,0)
       $('.precioVenta').val(null)
       $('.codigo').val("")
       $('.codigo').focus()
       return  
    }
    __buscarcodigo(codigoActual,__valorNumerico(cantidadAct),0);
    if(self.articulo !=null){
        if(self.articulo.tipoCodigo !="04" || self.empresa.tieneLector !="Activo"){
            $('.precioVenta').val(null)
            $('.codigo').val("")
            $('.codigo').select()
            $('.codigo').focus()
        }
    }
}
/**
*  cambiar el precio
**/
__addPrecioDetail(e){
    if (e.keyCode != 13) {
        return;
    } 
    var codigo = $('#codigo').val()
    if(codigo == " "){
       __EnviarFacturar()
    }
    var codigoActual = ""
    var cantidadAct =""
    var existe = false
     var existeMas = false
    for(i=0; i<codigo.length; i++){
         existeMas = codigo.charAt(i) == "+"?true : false
       if(existe == false){
          existe = codigo.charAt(i) == "*"?true : false  
          if(codigo.charAt(i) !="*"){
            codigoActual = codigoActual + codigo.charAt(i)  
          }
       }else{
           cantidadAct = cantidadAct + codigo.charAt(i)
       }
    }
    var valor = __valorNumerico(cantidadAct)
    var precio = e.currentTarget.value
    // esto es para cuando un cliente quiere sumar varios productos
    if(existeMas == true){
       __sumarMasArticulo(codigo,0)
       $('.precioVenta').val(null)
        $('.codigo').val(null)
        $('.codigo').focus()
       return  
    }
    __buscarcodigoPrecio(codigoActual,__valorNumerico(valor),__valorNumerico(precio));
    $('.precioVenta').val(null)
    $('.codigo').val("")
    $('.codigo').focus()
}
/**
*sumar mas cantidad al ultimor articulo ingresado
**/
function __sumarMasArticulo(codigo,precio){
    if(self.articulo == null){
        return;
    }
    if(self.articulo.tipoCodigo =="04" || self.empresa.tieneLector !="Activo"){
       return
    }
    var valorPrecio =  parseFloat(precio)
    var cantidadAct =""
    var existe = false
    for(i=0; i<codigo.length; i++){
       existe = codigo.charAt(i) == "+"?true : false
       if(existe == false){
          cantidadAct = cantidadAct + codigo.charAt(i)
        }
    }
   for (var count = 0; count < self.detail.length; count++) {
        if (self.detail[count].codigo == self.articulo.codigo ){
            self.item          = self.detail[count];
            self.item.cantidad = self.item.cantidad + parseFloat(cantidadAct)
            self.item.precioUnitario = valorPrecio >0?valorPrecio:self.item.precioUnitario
            self.cantidadEnterFacturar = 0
            self.update();
            ActualizarLineaDEtalle()
            self.detail[count] = self.item;
            self.update();
        }
    }
    __calculate(); 
}
/**
* Buscar codigo
**/
__agregarArticuloBotonAgregar(){
   __buscarcodigo($( "#codigo" ).val(),1,0);
   return
}
/**
* consultando por descripcion
**/
__ConsultarProductosDesc(e){
    if (e.keyCode != 13) {
        return;
    } 
    __ListaDeArticulosPorDescripcion($("#codigoArt").val(),e.currentTarget.value)   
}
/**
*Consultando por codigo
**/
__ConsultarProductosCod(e){
    if (e.keyCode != 13) {
        return;
    } 
    __ListaDeArticulosPorDescripcion(e.currentTarget.value,$("#descArticulo").val())   
}
/**
* mostrar la lista de articulos de la empresa
**/
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
/**
*  Muestra la lista de clientes
**/
_EscogerClientes(){
    $('#modalClientes').modal('show')  
 }
/**
*  Muestra la lista de vendedores
**/
_EscogerVendedores(){
    $('#modalVendedor').modal('show')  
 }
/**
*  Lista de los vendedores
**/
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
/**
*  Lista de los clientes
**/
function __ListaDeClientes(){
    $.ajax({
        url: 'ListarClientesActivosAjax.do',
        datatype: "json",
        global: false,
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                __informacionData()
                loadListar(".tableListaCliente",idioma_espanol,self.informacion_tabla_clientes,result.aaData)
                agregarInputsCombos_Clientes()
                ActivarEventoFiltro(".tableListaCliente")
                __seleccionarClientes()
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
*  Lista de los clientes
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
*  Lista de los codigos de uso interno
**/
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
/**
* Buscar el codigo del codigo si esta input de precio aplica esto cuando es codigo de uso interno
**/
function __buscarcodigoPrecio(idArticulo,cantidad,precio){
    if(idArticulo ==null){
        return
    }
    if(idArticulo.length ==0){
        return
    }
  //  buscarCodigoUsoInterno(idArticulo)
//    if(self.articulo !=null){
//        actualizarArticuloPrecio(precio,cantidad)
//        return
 //   }
    $.ajax({
        type: 'GET',
        url: 'findArticuloByCodigojax.do',
        method:"GET",
        data:{codigoArticulo:idArticulo},
        success: function(data){
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    swal('',data.message,'error');
                }
            }else{
                self.articulo  = null
                self.update()
                if (data.message != null && data.message.length > 0) {
                    self.articulo =null
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        //Articulo no puede agregarse si no hay en el inventario
                     //   if(modeloTabla.contable == "Si"){
                     //       if(modeloTabla.cantidad < 0 || modeloTabla.cantidad == 0 ){
                     //           mensajeError($.i18n.prop("error.articulo.sin.existencia.en.inventario"))
                     //           return
                     //       }
                     //       if(modeloTabla.cantidad < cantidad ){
                     //           mensajeError($.i18n.prop("error.articulo.tiene.menor.existencia.en.inventario.a.la.venta"))
                     //           return
                     //       }

                      //  }
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
/**
*Actualiza el Precio del articulo
**/
function actualizarArticuloPrecio(precio,cantidad){
    self.articulo.precioPublico = precio > 0 ?precio:self.articulo.precioPublico
    self.articulo.precioPublico = precio > 0 ?precio:self.articulo.precioPublico
    self.descripcionArticulo = self.articulo.descripcion
   
    self.cantidadEnterFacturar = 0
    self.update()
    __agregarArticulo(cantidad)
}
/**
* Buscar el codigo del codigo  en la base de datos
**/
function __buscarcodigo(idArticulo,cantidad,precio){
    self.articulo = null
    self.update()
    if(idArticulo ==null){
        return
    }
      if(idArticulo.length ==0){
        return
    }
 //   buscarCodigoUsoInterno(idArticulo)
 //   if(self.articulo !=null){
 //       $('#codigo').val(self.articulo.codigo)
 //       $('#precioVenta').val(self.articulo.precioPublico)
 //       $('#precioVenta').select()
 //       $("#precioVenta").focus()
 //      return        
 //  }
    $.ajax({
        type: 'GET',
        url: 'findArticuloByCodigojax.do',
        method:"GET",
        data:{codigoArticulo:idArticulo},
        success: function(data){
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    swal('',data.message,'error');
                }
            }else{
                self.articulo  = null
                self.update()
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        //Articulo no puede agregarse si no hay en el inventario
                     //   if(modeloTabla.contable == "Si"){
                     //       if(modeloTabla.cantidad < 0 || modeloTabla.cantidad == 0 ){
                     //           mensajeError($.i18n.prop("error.articulo.sin.existencia.en.inventario"))
                     //           return
                     //       }
                     //       if(modeloTabla.cantidad < cantidad ){
                     //           mensajeError($.i18n.prop("error.articulo.tiene.menor.existencia.en.inventario.a.la.venta"))
                     //           return
                     //       }
                     //   }
                        self.articulo  = modeloTabla
                        if(modeloTabla.estado  == "Inactivo"){
                            mensajeError($.i18n.prop("error.articulo.inactivo.inventario"))
                            return
                        }
                        self.articulo.precioPublico = getListaPrecio(self.articulo)
                        self.descripcionArticulo = modeloTabla.descripcion
                        self.update()
                        if(self.articulo !=null){
                            if(self.articulo.tipoCodigo =="04" || self.empresa.tieneLector !="Activo"){
                               // $('#codigo').val(self.articulo.codigo)
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
/**
*  Busca el codigo de uso interno
* */
function buscarCodigoUsoInterno(codigo){
    self.articulo = null;
    for (var count = 0; count < self.articulos_uso_interno.length; count++) {
        if (self.articulos_uso_interno[count].codigo == codigo ){
            self.articulo          = self.articulos_uso_interno[count];
            self.update();
        }
    }    
}
/**
*  Agregar un articulo si existe se suma la cantidad y no existe se agrega en el detalle
**/
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
    //uso interno
    //Determinar el precio a incluir
   // var resultadoPrecio = getListaPrecio(self.articulo)
   // self.precioUltimo =formatoDecimales(resultadoPrecio * cantidad,0)    
   // self.update()
    if(self.articulo.tipoCodigo =="04" || self.empresa.tieneLector !="Activo"){
        __nuevoArticuloAlDetalle(cantidad);
        encontrado = true;
    }
  
    if( encontrado ==false){
        if(self.detail[0] == null){ // first element
            __nuevoArticuloAlDetalle(cantidad);
            encontrado = true;
        }else{//Se busca el articulo si existe se incrementa la cantidad
            for (var count = 0; count < self.detail.length; count++) {
                if (self.detail[count].codigo == self.articulo.codigo ){
                self.item          = self.detail[count];
                self.item.cantidad = self.item.cantidad + parseFloat(cantidad)
                self.cantidadEnterFacturar = 0
                self.cantArticulos = self.cantArticulos + 1
                self.update();
                ActualizarLineaDEtalle()
                self.detail[count] = self.item;
                encontrado = true;
                self.update();
                }
            }
        }
        // si no existe se agrega como un codigo nuevo
        if(encontrado == false){ // add elemen
        __nuevoArticuloAlDetalle(cantidad);
        }

    }
    __calculate(); 
    return encontrado
}
/**
* eliminar un detalle factura
**/
__removeProductFromDetail(e) {
    self.autorizarBorrado = 1
    self.itemEliminar = e.item;
    self.update()
    if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        self.rutaAutorizada = '';
        self.update()
        $("#usuarioSistema").val("")
        $("#claveSistema").val("")
        $('#modalRolUsuario').modal({backdrop: 'static', keyboard: true}) 
        $('#modalRolUsuario').modal('show')     
    }else{
        eliminarDetalle()
    }
}
/**
*    Eliminar detalle
**/
function  eliminarDetalle(){
    index = self.detail.indexOf(self.itemEliminar);
    self.detail.splice(index, 1);
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
 }
/**
*   agregar Articulos nuevos en el detalle de la factura
**/
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
    //Determinar el precio a incluir
    var resultadoPrecio = getListaPrecio(self.articulo)
      
    var resultaMontoImpuesto = parseFloat(self.articulo.impuesto)
    var precioUnitario  = getPrecioUnitario(resultadoPrecio,resultaMontoImpuesto)
    resultaMontoImpuesto = parseFloat(self.articulo.impuesto1) 
    precioUnitario      = getPrecioUnitario(precioUnitario,resultaMontoImpuesto)
    var montoTotal      = getMontoTotal(precioUnitario,cantidad)
    var montoDescuento  = 0
    var naturalezaDescuento = ""
    var subTotal        = montoTotal
    var montoImpuesto1  = _calcularImpuesto(subTotal,parseFloat(self.articulo.impuesto1) ==null?0:parseFloat(self.articulo.impuesto1))
    var montoImpuesto   = _calcularImpuesto(subTotal+montoImpuesto1,parseFloat(self.articulo.impuesto) ==null?0:parseFloat(self.articulo.impuesto))
    var montoTotalLinea = subTotal + montoImpuesto + montoImpuesto1  
    self.pesoPrioridad  =  self.pesoPrioridad + 1
    self.numeroLinea    = self.numeroLinea + 1
    self.cantArticulos  = self.cantArticulos + 1
    var costoTotal      = parseFloat(self.articulo.costo) > precioUnitario ?0:parseFloat(self.articulo.costo); 
    var ganancia        = __ObtenerGananciaProductoNuevoIngresado(0,precioUnitario,self.articulo.costo ==null?0:parseFloat(self.articulo.costo),cantidad)
    self.detail.push({
       numeroLinea     : parseFloat(self.numeroLinea),
       pesoPrioridad   : self.pesoPrioridad,  
       tipoImpuesto    : self.articulo.tipoImpuesto ==null?" ":self.articulo.tipoImpuesto,
       tipoImpuesto1   : self.articulo.tipoImpuesto1 ==null?" ":self.articulo.tipoImpuesto1,
       iva             : parseFloat(self.articulo.impuesto),
       iva1            : parseFloat(self.articulo.impuesto1),
       codigo          : self.articulo.codigo,
       descripcion     : self.articulo.descripcion,
       cantidad        : parseFloat(cantidad),
       precioUnitario  : parseFloat(precioUnitario),
       impuesto        : parseFloat(self.articulo.impuesto),
       impuesto1        : parseFloat(self.articulo.impuesto1),
       montoImpuesto   : parseFloat(montoImpuesto),
       montoImpuesto1  : parseFloat(montoImpuesto1),
       montoDescuento  : 0,
       porcentajeDesc  : 0,
       ganancia        : parseFloat(ganancia),
       montoGanancia   : parseFloat(ganancia),
       subTotal        : parseFloat(subTotal),
       montoTotalLinea : parseFloat(montoTotalLinea),
       montoTotal      : parseFloat(montoTotal),
       costo           : costoTotal,
       porcentajeGanancia :   getListaPrecioGanancia(self.articulo) ==null?0:parseFloat(getListaPrecioGanancia(self.articulo)),
       pesoTransporte :  parseFloat(self.articulo.pesoTransporte),
       pesoTransporteTotal :parseFloat(self.articulo.pesoTransporte)
    });
    
    self.detail.sort(function(a,b) {
    if ( a.pesoPrioridad > b.pesoPrioridad )
        return -1;
    if ( a.pesoPrioridad < b.pesoPrioridad )
        return 1;
    return 0;
    } );
    self.cantidadEnterFacturar = 0
  //  self.totalGananciaByProducto += parseFloat(ganancia)
    self.update()
}

function getListaPrecio(articulo){
    //Precio Publico
    if($('.selectListaPrecios').val()==1){
        resultado=  parseFloat(articulo.precioPublico )
    }
    //Precio Mayorista
    if($('.selectListaPrecios').val()==2){
        resultado=  parseFloat(articulo.precioMayorista )
    }
    //Precio Especial
    if($('.selectListaPrecios').val()==3){
        resultado=  parseFloat(articulo.precioEspecial) 
    }
    return resultado > 0 ?resultado:parseFloat(articulo.precioPublico )

}

function getListaPrecioGanancia(articulo){
    //Precio Publico
    if($('.selectListaPrecios').val()==1){
        resultado=  parseFloat(articulo.gananciaPrecioPublico )
    }
    //Precio Mayorista
    if($('.selectListaPrecios').val()==2){
        resultado=  parseFloat(articulo.gananciaPrecioMayorista )
    }
    //Precio Especial
    if($('.selectListaPrecios').val()==3){
        resultado=  parseFloat(articulo.gananciaPrecioEspecial) 
    }
    return resultado > 0 ?resultado:parseFloat(articulo.gananciaPrecioEspecial )

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
 /**
 * Cuando se aplica un cambio de cantidad en un detalle
 * Se aplica una recalculacion de todo el detalle y Factura
 **/ 
 __recalculacionDelDetalle(e){
      var cantidad = $(".cambiarCantidadArticulo").val();
    //Cantidad del detalle se verifica si es null o espacio por defecto se deja en 1
    cantidad =__valorNumerico(cantidad);
    if(cantidad == 0){
       cantidad = 1;
    }
    cantidad = __valorNumerico(redondeoDecimales(cantidad,3))
    __ValidarCantidadArticulo(self.item.codigo,cantidad)
  }
/**
* Buscar el codigo del codigo  en la base de datos
**/
function __ValidarCantidadArticulo(idArticulo,cantidad){
    $.ajax({
        type: 'GET',
        url: 'findArticuloByCodigojax.do',
        method:"GET",
        data:{codigoArticulo:idArticulo},
        success: function(data){
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    swal('',data.message,'error');
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
/**
* Monto de descuento sobre la ganancia
*/
function getMontoDescuento(precioUnitario,cantidad,porcentajeDesc,porcentajeGanancia){
	    if(porcentajeDesc == 0){
	        return 0
	    }
	     if(porcentajeDesc > 100){
	        porcentajeDesc = 100
	    }
	    self.item.porcentajeDesc = porcentajeDesc
	    self.update()
	    var porcentaje =  porcentajeGanancia;
	    if(porcentajeDesc != porcentajeGanancia){
	       porcentaje =  porcentajeDesc;
	    }
	    porcentaje = porcentaje/ 100;
	    if(porcentajeDesc ==100){
	        porcentaje = 0
	    }
	    var totalDescuento =  precioUnitario * cantidad
	    var resultado = porcentaje >0?totalDescuento * porcentaje:totalDescuento;
	    return resultado
}
/**
* Actualiza la linea del detalle de la factura
**/
function ActualizarLineaDEtalle(){
    var montoTotal             = getMontoTotal(self.item.precioUnitario,self.item.cantidad)
    var montoDescuento         = getMontoDescuento(self.item.precioUnitario,self.item.cantidad,self.item.porcentajeDesc,self.item.porcentajeGanancia)
    var subTotal               = montoTotal > montoDescuento?montoTotal - montoDescuento: montoDescuento-montoTotal
    montoImpuesto1             = _calcularImpuesto(subTotal,self.item.impuesto1 ==null?0:self.item.impuesto1)
    var resultadoMontoImpuesto1 = montoImpuesto1 + subTotal;
    var montoImpuesto          = _calcularImpuesto(resultadoMontoImpuesto1,self.item.impuesto ==null?0:self.item.impuesto)
    var montoTotalLinea        = subTotal + montoImpuesto + montoImpuesto1   
    self.item.pesoTransporteTotal = parseFloat(self.item.cantidad) *  parseFloat(self.item.pesoTransporte)
   
    self.item.montoTotal       = montoTotal
    self.item.montoDescuento   = montoDescuento
    self.item.subTotal         = subTotal
    self.item.montoImpuesto    = montoImpuesto
    self.item.montoImpuesto1   = montoImpuesto1
    self.item.montoTotalLinea  = montoTotalLinea
    self.item.ganancia         = __ObtenerGananciaProductoNuevoIngresado(montoDescuento,self.item.precioUnitario,self.item.costo ==null?0:parseFloat(self.item.costo),self.item.cantidad)
    self.item.montoGanancia    = self.item.ganancia 
   // self.totalGananciaByProducto = formatoDecimales(parseFloat(self.item.ganancia),2)
    self.update()
}
/**
* Agregar la cantidad de Venta
**/
function agregarCantidadAlaVenta(cantidad){
    self.item.cantidad = cantidad
    var ganancia        = __ObtenerGananciaProductoNuevoIngresado(0,self.item.precioUnitario,self.item.costo ==null?0:parseFloat(self.item.costo),cantidad)
    self.item.ganancia = ganancia
    self.item.montoGanancia    = self.item.ganancia
   // self.totalGananciaByProducto = formatoDecimales(parseFloat(ganancia),2)
    self.update()
    ActualizarLineaDEtalle()
    aplicarCambioLineaDetalle() 
    cambiarCantidadArticulo.value = 0
    $('#modalCambiarCantidad').modal('hide') 
}
/**
* Aplicar el cambio de linea Detalle
**/
function aplicarCambioLineaDetalle(){
    var index    = self.detail.indexOf(self.item);
    self.detail[index] = self.item;
    self.update()
    __calculate()
}
/**
* Actualizar el descuento del codigo
**/
__actualizarDescuento(e){
    _actualizarDesc(e)
}
/**
* Actualizar el descuento
**/
function _actualizarDesc(e){
    var descuento = $(".aplicarDescuento").val();
    descuento = __valorNumerico(descuento)
    if(self.empresa.aplicaGanancia ==1){
        if(self.item.porcentajeGanancia < descuento ){
            swal('',"No se puede aplicar un descuento mayor a la ganancia",'error');
            descuento  = self.item.porcentajeGanancia
        }
    } 
    var index     = self.detail.indexOf(self.item);
      //Descuento
    if(self.item.porcentajeDesc != descuento){
       self.item.porcentajeDesc =  parseFloat(descuento);  
    }    
    self.update()
    ActualizarLineaDEtalle()  
    aplicarCambioLineaDetalle()
    $('#modalCambiarDescuento').modal('hide') 
   $(".aplicarDescuento").val(null);
}
/**
* calculacion de los detalle de la factura 
**/
function __calculate() {
    self.factura.total            = 0;
    self.factura.totalDescuentos  = 0;
    self.factura.totalImpuesto    = 0;
    self.factura.subTotal         = 0;
    self.update()
    var totalVenta     = 0
    var subTotal       = 0
    var totalDescuento = 0
    var totalImpuesto  = 0
    var totalImpuesto1 = 0
    var totalMercanciasGravadas = 0
    var totalMercanciasExentas  = 0
    var totalServGravados       = 0
    var totalServExentos        = 0
    var totalGravado            = 0
    var totalExento             = 0
    var totalComprobante        = 0
    var totalventaNeta          = 0
    var totalGanancia           = 0
    self.cantArticulos      = 0
    var totalPesoByFactura = 0
    self.detail.forEach(function(e){
        totalMercanciasGravadas += e.montoImpuesto > 0 && e.tipoImpuesto != "07"?e.montoTotal:0
        totalMercanciasGravadas += e.montoImpuesto1 > 0 && e.tipoImpuesto1 != "07"?e.montoTotal:0
        totalMercanciasExentas  += e.impuesto == 0 && e.tipoImpuesto != "07"?e.montoTotal:0
        totalMercanciasExentas  += e.impuesto1 == 0 && e.tipoImpuesto1 != "07"?e.montoTotal:0
        totalServGravados       += e.montoImpuesto > 0 && e.tipoImpuesto == "07"?e.montoTotal:0
        totalServGravados       += e.montoImpuesto1 > 0 && e.tipoImpuesto1 == "07"?e.montoTotal:0
        totalServExentos        += e.impuesto == 0 && e.tipoImpuesto == "07"?e.montoTotal:0
        totalServExentos        += e.impuesto1 == 0 && e.tipoImpuesto1 == "07"?e.montoTotal:0
        totalGravado            += e.impuesto > 0 ?e.montoTotal:0
        totalGravado            += e.impuesto1 > 0 ?e.montoTotal:0
        totalExento             += e.impuesto == 0 && e.impuesto1 == 0?e.montoTotal:0
        totalComprobante        += e.montoTotalLinea
        subTotal                += e.subTotal >0?e.subTotal:0
        totalDescuento          += e.montoDescuento >0?e.montoDescuento:0
        totalImpuesto           += __valorNumerico(e.montoImpuesto)
        totalImpuesto1          += __valorNumerico(e.montoImpuesto1)
        totalVenta              += e.montoTotal
        totalGanancia           +=__valorNumerico(e.ganancia)
        self.cantArticulos      += esEntero(e.cantidad) == true? e.cantidad:1 
        totalPesoByFactura      += parseFloat(e.pesoTransporte) * parseFloat(e.cantidad)
    });
    self.totalGananciaByProducto = formatoDecimales(parseFloat(totalGanancia),2)
    self.totalPesoByFactura = parseFloat(totalPesoByFactura)
    self.totalPesoByFacturaSTR           = formatoDecimales(totalPesoByFactura,2);
    self.factura.totalMercanciasGravadas = __valorNumerico(totalMercanciasGravadas)
    self.factura.totalMercanciasExentas  = __valorNumerico(totalMercanciasExentas)
    self.factura.totalServGravados       = __valorNumerico(totalServGravados)
    self.factura.totalServExentos        = __valorNumerico(totalServExentos)
    self.factura.totalGravado            = __valorNumerico(totalGravado)
    self.factura.totalExento             = __valorNumerico(totalExento)
    //cuando se aplica descuentos
    self.factura.totalVenta              = __valorNumerico(totalVenta)
    self.factura.totalDescuentos         = __valorNumerico(totalDescuento)
    self.factura.subTotal                = __valorNumerico(subTotal)
    self.factura.totalImpuesto           = __valorNumerico(totalImpuesto) + __valorNumerico(totalImpuesto1)
    self.factura.totalVentaNeta          = __valorNumerico(totalVenta-totalDescuento)
    self.factura.totalComprobante        = __valorNumerico(totalComprobante)
    self.totalComprobante                = formatoDecimales(self.factura.totalComprobante,2);
    self.totalDescuentos                 = formatoDecimales(self.factura.totalDescuentos,2);
    self.totalImpuesto                   = formatoDecimales(self.factura.totalImpuesto,2);
    self.update(); 
    $('.precioVenta').val(null)
    $('.codigo').val(null)
    $('.codigo').select()
    $('.codigo').focus()
    getSubTotalGeneral()
    localStorage.setItem('DetallesNueva', JSON.stringify(self.detail));
    localStorage.setItem('facturaNueva', JSON.stringify(self.factura));
}
/**
*  Sub Total Generar
**/
function getSubTotalGeneral(){
    var resultado = __valorNumerico(self.factura.subTotal) + __valorNumerico(self.factura.totalDescuentos)
    self.subTotalGeneral = formatoDecimales(resultado,2)
    self.totalDescuentos = formatoDecimales(self.factura.totalDescuentos,2)
    var resultadoTotalImpuesto = __valorNumerico(self.factura.totalImpuesto) + __valorNumerico(self.factura.totalImpuesto1)
    self.totalImpuesto   = formatoDecimales(resultadoTotalImpuesto,2)
    self.update()
}
/**
* Definicion de la tabla articulos 
**/
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
/**
* Opciones del modal de articulos
*/
function __OpcionesArticulos(){
  var agregar  = '<a href="#"  class="btn btnAgregar btn-success form-control" title="Seleccionar" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;

}
/**
* Agregar codigos a la Factura desde modal de articulos
**/
function __agregarArticulos() {
     $('#tableListarArticulos').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListarArticulos').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
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
                $('.precioVenta').select()
                $(".precioVenta").focus()
                 $('#modalInventario').modal('hide') 
                return
            }
        } 
        if(self.articulo.contable == "si"){
           __buscarcodigo(self.articulo.codigo,1,0)
        }else{
            __agregarArticulo(1)
        }
        $('#modalInventario').modal('hide') 
	    
    });
}
/**
* formato de la tabla de clientes
**/
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
/**
* Opciones del modal de clientes
*/
function __OpcionesVendedores(){
  var agregar  = '<a href="#"  title="Seleccionar Vendedor" class="btn btnVendedor btn-success form-control" title="Seleccione el vendedor" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;
}
/**
* Seleccionar el vendedor de la factura
**/
function __seleccionarVendedor() {
     $('#tableListaVendedor').on('click', '.btnVendedor', function (e) {
         var table = $('#tableListaVendedor').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
         }
        self.vendedor = data
        self.update();
    });
}
/**
* formato de la tabla de clientes
**/
function __informacionData(){
    self.informacion_tabla_clientes = [	
                                        {'data' : 'cedula'           ,"name":"cedula"            ,"title" : $.i18n.prop("cliente.cedula")            ,"autoWidth":false},
                                        
                                        {'data' : 'nombreCompleto'   ,"name":"nombreCompleto"    ,"title" : $.i18n.prop("cliente.nombreCompleto")    ,"autoWidth":false},
                                        {'data' : 'correoElectronico',"name":"correoElectronico" ,"title" : $.i18n.prop("cliente.correoElectronico") ,"autoWidth":false},
                                        {'data' : 'nombreComercial'  ,"name":"nombreComercial"    ,"title" : $.i18n.prop("cliente.nombreComercial")    ,"autoWidth":false},
                                        {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									            "render":function(id,type, row){
										            return __Opcionesclientes(id,type,row);
	 							                }	 
								            },


                                        ];                              
   
}
/**
* Opciones del modal de clientes
*/
function __Opcionesclientes(){
  var agregar  = '<a href="#"  title="Seleccionar Cliente" class="btn btnAgregar btn-success form-control" title="Seleccione el cliente de la factura" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;

}
/**
* Agregar codigos a la factura desde modal de articulos
**/
function __seleccionarClientes() {
     $('#tableListaCliente').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListaCliente').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	     }
        self.cliente = data
        self.update();
         $('#modalClientes').modal('hide') 
        
    });
}
/**
* cargar los estados de la factura
**/
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
/**
* cargar los tipos de Documento de la factura
**/
function __ComboTipoDocumentos(){
    self.comboTipoDocumentos = []
    self.update()
    //Prioridad de orden
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
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Articulo(){
     // Agregar los input de busqueda 
    $('.tableListarArticulos tfoot th').each( function (e) {
        var title = $('.tableListarArticulos thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 0    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Clientes(){
     // Agregar los input de busqueda 
    $('.tableListaCliente tfoot th').each( function (e) {
        var title = $('.tableListaCliente thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 4    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Facturas_Dias(){
     // Agregar los input de busqueda 
    $('.tableListarFacturasDia tfoot th').each( function (e) {
        var title = $('.tableListarFacturasDia thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 6    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Vendedores(){
     // Agregar los input de busqueda 
    $('.tableListaVendedor tfoot th').each( function (e) {
        var title = $('.tableListaVendedor thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 5    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}
/**
* Mostrar el pago con F8 o en blanco
**/
function __EnviarFacturar(){
      if(self.mostrarFormularioPago == false && self.mostarParaCrearNuevaFactura == true){
        if(self.vueltoImprimir == 0){
            self.factura.totalCambioPagar =__valorNumerico(self.factura.totalComprobante)   
            self.totalCambioPagar = redondeoDecimales(self.factura.totalComprobante,2)
            self.primeraVezBilleteClick == false
            self.update()
            $(".totalEfectivo").val(self.totalCambioPagar)
        }  
         mostrarPAgo()     
      }else if (self.mostrarFormularioPago == true && self.mostarParaCrearNuevaFactura == false ){
          self.primeraVezBilleteClick == false
          self.update()
           __OpcionAbrirCajon()
            aplicarFactura(2)   
        } 
}
/**
*  teclas de la pantalla
**/      
function __Teclas(){
    window.addEventListener( "keydown", function(evento){
        var tecla = evento.keyCode; 
    if(tecla ==119){
        __EnviarFacturar()
        
    } 
   // alert(tecla)  
    //F4
    if(tecla ==115){
     ListarCodigosArticulos()
      $('.descArticulo').select()
      $('.descArticulo').focus()
    }
    //Factura en espera
    if(tecla ==120){
      aplicarFactura(1)   
    }
     //Reimprimir Factura f6
    //if(tecla ==117){
    // reimprimirFacturaEnMomento()
    //}
     //Reimprimir Factura End
    if(tecla ==35){
     reimprimirFacturaEnMomento()
     
    }

    //Limpiar F2
    if(tecla ==113){
      __SeguridadLimpiar()
      $('.codigo').val("")
      $('.codigo').select()
      $(".codigo").focus()
    }
     //Insert = abrir Cajon
    if(tecla ==45){
       __OpcionAbrirCajon()
    }
   if(tecla ==27){
      $('.codigo').select()
      $(".codigo").focus()
    }
    }, false );
}
/**
* refrescar una pagina
**/
function refrescarPagina(){
     __Init()
     $('.codigo').select()
      $(".codigo").focus()
}
/**
* Contabilizar los billetes de acuerdo a como se vayan dando click en la pantalla
*/
_sumarBilletes(e){
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
       $('.efectivo').val(self.factura.totalEfectivo)
        self.update()
        var sumaMontosEntregadosParaCambios =__valorNumerico(self.factura.totalTarjeta)
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco) 
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo) 
        self.factura.totalCambioPagar = 0
        self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios - __valorNumerico(self.factura.totalComprobante)
        self.claseCambioDinero  = __valorNumerico(sumaMontosEntregadosParaCambios) > __valorNumerico(self.factura.totalComprobante)?'entregarCambioPositivo':'entregarCambioNegativo'
        self.totalCambioPagar = redondeoDecimales(self.factura.totalCambioPagar,2)
        self.totalCambioPagarSTR =formatoDecimales(self.totalCambioPagar,2)
        $(".totalEfectivo").val(self.factura.totalEfectivo) 
    }
    self.update()
}
/**
*Cargar los billetes
**/
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
/**
*    Incluir a los billetes
**/
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
/**
*abrir el cajon
**/
__AbrirCajon(){
    __OpcionAbrirCajon()
}
/**
*  Opcion para abrir la comanda
**/
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
/**
*Abrir el cajon de dinero sin comanda
**/
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
/**
* Abrir con con comanda
**/
function abrirCajonDineroConComanda(){
//Se forman los detalles a enviar a la comanda
		var informacion = {
			mesa: "Abrir Cajon",        	
			mesero: "abrirCajon",        	
		    nombreImpresora:self.empresa.impresoraFactura,
		    cantidadCaracteresLinea:"40",
		    formatoTiquete:"",
		    detalles:""
		}    
		var JSONData = JSON.stringify(informacion);		
		//Envia a imprimir a la comanda
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
	            mensajeErrorServidor(xhr, status);
	        }
	    });		
}
</script>
</punto-venta>