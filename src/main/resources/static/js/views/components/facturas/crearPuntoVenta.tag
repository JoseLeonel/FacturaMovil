<punto-venta>


    <div class="box" show={mostrarFormularioPago}>
        <div class="box-body">
            <div  class="contenedor-compra " >
                <div class="cabecera-izquierda">
                    <div class="box-header with-border fondoEncabezado">
						<h3 class="box-title">{$.i18n.prop("factura.resumen.formulario")} {factura.id>0?' Id#:'+factura.id:'' } </h3>
					</div>
                    <div class="box-body">
                        <form id="formularioFactura">
                            <input id="id" name="id" type="hidden" value="{compra.id}">
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-46">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("factura.condicion.pago")} </label> 
                                        <select  onchange= {__formaPago} class="form-control" id="condicionVenta" >
                                            <option each={comboCondicionPagos} value="{estado}" selected="{factura.condicionVenta ==estado?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>
 
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-46">
                                    <div class="form-group ">
                                        <label for="pago_tipoVentaL">{$.i18n.prop("factura.tipo.documento")} </label> 
                                        <select class="form-control" id="tipoDoc" name="tipoDoc"   >
                                            <option each={comboTipoDocumentos} value="{estado}" selected="{factura.tipoDoc ==estado?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>
 
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-46">
                                    <div class="form-group ">
                                        <label for="pago_tipoVentaL">{$.i18n.prop("factura.estado")} </label> 
                                        <select class="form-control" id="estado" name="estado"  >
                                            <option each={comboEstados} value="{estado}" selected="{factura.estado ==estado?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>
                                </div>
                            </div>    
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <input   type="hidden" class="form-control" id="cliente" name="cliente" value="{cliente.id}">
                                        <label>{$.i18n.prop("factura.cliente")}</label> 
                                        <input onclick = {_EscogerClientes}  type="text" id="nombreCliente" name="nombreCliente" class="form-control"  value="{cliente.nombreCompleto}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.provincia")}</label> 
                                        <input type="text" class="form-control " value="{cliente.descripcionProvincia}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.celular")}</label> 
                                        <input type="text" class="form-control " value="{cliente.celular}" readonly>
                                    </div>
                                </div>
                            </div>    
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.telefono")}</label> 
                                        <input   type="text"  class="form-control"  value="{cliente.telefono}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.correoElectronico")}</label> 
                                        <input type="text" class="form-control " value="{cliente.correoElectronico}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.descuento")}</label> 
                                        <input type="text" class="form-control " value="{cliente.descuento}" readonly>
                                    </div>
                                </div>
                            </div>                                
                            <div class="row">
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.otraSena")}</label> 
                                        <input type="text" class="form-control " value="{cliente.otraSena}" readonly>
                                    </div>
                                </div>
                            </div> 
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <input   type="hidden" class="form-control vendedor" id="vendedor" name="vendedor" value="{vendedor.id}">
                                        <label>{$.i18n.prop("factura.vendedor")}</label> 
                                        <input onclick = {_EscogerVendedores}  type="text" id="nombreVendedor" name="nombreVendedor" class="form-control"  value="{vendedor.nombreCompleto}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("vendedor.correoElectronico")}</label> 
                                        <input type="text" class="form-control " value="{vendedor.correoElectronico}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("vendedor.celular")}</label> 
                                        <input type="text" class="form-control " value="{vendedor.celular}" readonly>
                                    </div>
                                </div>
                            </div>      
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div show = {!mostrarCamposIngresoContado || factura.fechaCredito} class="form-group has-success">
                                        <label >{$.i18n.prop("compra.fecha.credito")}</label> 
                                        <div  class="form-group input-group date" data-provide="datepicker"  data-date-start-date="0d" data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control" id="fechaCredito" value="{factura.fechaCredito}" >
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>    
                                </div>
                            </div>                             
                            <div class="row">
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <div class="form-group ">
                                        <label >{$.i18n.prop("factura.nota")}</label> 
                                        <input type="text" class="form-control" id="nota" name="nota" value="{factura.direccion}">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <div class="form-group ">
                                        <label >{$.i18n.prop("factura.direccion")}</label> 
                                        <input type="text" class="form-control direccion" id="direccion" name="direccion" value="{factura.direccion}">
                                    </div>
                                </div>
                            </div>

                        </form>   
                    </div>                    
                    <div class="box-footer">
                        <button onclick={_AtrasFacturaFinal} type="button" class="btn-dark-gray btn-back pull-left"  >{$.i18n.prop("btn.volver")}</button>
                        <button  onclick={__AplicarYcrearFactura}  class="btn-green btn-edit pull-right " > &nbsp {$.i18n.prop("btn.aplicar")}</button>
                    </div>
                </div><!--fin del cabecera-izquierda-->
                <section class="cabecera-derecha">
                    <!--right sidebar-->
                    <aside class="right-sidebar">
                        <!--Booking details-->
                        <article class="booking-details clearfix">
                            <h3><span class="booking-info tituloTotal">{$.i18n.prop("factura.resumen.venta")}</span></h3>
                            <div class="booking-info tituloTotal">
                                <p style="text-align:left">{$.i18n.prop("factura.resumen.subTotal")}  <span style="text-align:right"> ₡ {factura.subTotal.toLocaleString('de-DE') }       </span></p>
                                <p style="text-align:left">{$.i18n.prop("factura.resumen.descuento")} <span style="text-align:right"> ₡ {factura.totalDescuento.toLocaleString('de-DE')} </span></p>
                                <p style="text-align:left">{$.i18n.prop("factura.resumen.impuesto")}  <span style="text-align:right"> ₡ {factura.totalImpuesto.toLocaleString('de-DE')}  </span></p>
                            </div>
                            <div class="booking-info tituloTotal">
                                <p class="tituloTotal" style="text-align:left;">{$.i18n.prop("factura.resumen.total")} <span id="lblTotal">₡ {factura.totalVentaNeta.toLocaleString('de-DE')}</span></p>
                                <p class="tituloTotal" style="text-align:left;">{$.i18n.prop("tipoCambio.cambioDolar")} <span id="lblTotal">{tipoCambio.total.toLocaleString('de-DE')}</span></p>
                                <p class="tituloTotal" style="text-align:left;">{$.i18n.prop("factura.totalDolar")} <span id="lblTotal">{factura.cambioMoneda.toLocaleString('de-DE')}</span></p>
                                <br>
                                <div show = {mostrarCamposIngresoContado }>

                                    <p class="tituloTotal from-control" >{$.i18n.prop("factura.resumen.efectivo")}</p> 
                                    <input onkeyup={ __TotalDeEfectivoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control tamanoLetraTotales " id="totalEfectivo" name="totalEfectivo"  value="" >
                                    <p class="tituloTotal from-control" >{$.i18n.prop("factura.resumen.tarjeta")}</p> 
                                    <input onkeyup={ __TotalDeTarjetaAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control tamanoLetraTotales" id="totalTarjeta" name="totalTarjeta"   >
                                    <p class="tituloTotal from-control" >{$.i18n.prop("factura.resumen.banco")}</p> 
                                    <input onkeyup={ __TotalDeBancoAPagar} onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control tamanoLetraTotales" id="totalBanco" name="totalBanco"  value="" >
                                    <p class="tituloTotal" style="text-align:left;">{$.i18n.prop("factura.resumen.cambio")} <span id="lblTotal">₡ {factura.totalCambioPagar.toLocaleString('de-DE')}</span></p>
                                </div>

                            </div>
                        </article>
                    </aside>     
                </section>               
            </div><!--fin del cabecera-derecha-->
        </div><!--fin del cabecera-derecha-->
    </div><!--fin del contenedor-compra-->
    <div class="box box-solid box-primary" show={mostarParaCrearNuevaFactura}>
        <div class="box-body">
             <div class="box-header with-border">
                <div class="row">
                  <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">  
                  <div class="box-tools ">
                    <a class="pull-right" href="#"    onclick = {__Limpiar} title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-limpiar">{$.i18n.prop("btn.limpiar")}</span></a>
                    <a class="pull-right" href="#"    onclick = {__AplicarYcrearFactura} title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("btn.tiquete")}</span></a>
                  </div>
                  </div>
                </div>  
                  <br>
             </div>
            <div  class="contenedor-compra " >
                <div class="cabecera-izquierda">
                    <div class="row">
                            <div class="col-sx-6 col-sm-6 col-md-6 col-lg-10">
                                <input onkeypress={__addProductToDetail}  id="codigo" class="form-control" type="text" placeholder="XXXXXXXXXXX" />
                            </div>
                            <div class="col-sx-6 col-sm-6 col-md-6 col-lg-2">
                                <button    onclick = {__ListaDecodigos} class="btn-green btn-buscar " id="btn-facturar" >
                                    {$.i18n.prop("btn.consultar")} 
                                </button>
                            </div>
                    </div>
                    <br>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th style="width:5%;">                                                   </th>
                            <th>{$.i18n.prop("factura.linea.detalle.linea")}                         </th>
                            <th>{$.i18n.prop("factura.linea.detalle.codigo")}                        </th>
                            <th style="width:20%;">{$.i18n.prop("compra.linea.detalle.descripcion")} </th>
                            <th >{$.i18n.prop("factura.linea.detalle.cantidad")}                     </th>
                            <th >{$.i18n.prop("factura.linea.detalle.precio")}                       </th>
                            <th >{$.i18n.prop("factura.linea.detalle.descuento")}                    </th>
                            <th >{$.i18n.prop("factura.linea.detalle.impuesto")}                     </th>
                            <th >{$.i18n.prop("factura.linea.detalle.subTotal")}                     </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td>
                                <button  onclick={__removeProductFromDetail} class="btn btn-danger btn-xs btn-block">X</button>
                            </td>
                            <td>{linea}</td>
                            <td>{codigo}</td>
                            <td>{descripcion}</td>
                            <td class="text-right">
                                <input onclick={__CambiarCantidad} id= "cantidadDetalle" class="form-control " type="number" placeholder="Cantidad Detalle" value = {cantidad} readonly />
                            </td>
                            <td class="text-right">
                                <input   class="form-control" type="text"  value = "{precioUnitario.toLocaleString('de-DE')}" readonly />
                            </td>
                            <td class="text-right">
                                <input  onclick={__CambiarDescuento} class="form-control" type="text"  value = "{porcentajeDesc.toLocaleString('de-DE')}" readonly/>
                            </td>
                                                        
                            <td class="text-right">
                                <input  class="form-control" type="text"  value = "{impuesto.toLocaleString('de-DE')}" readonly/>
                            </td>

                            <td class="text-right">
                                <input  class="form-control" type="text"  value = "₡ {montoTotalLinea.toLocaleString('de-DE')}" readonly/>
                            </td>
                        </tr>
                        </tbody>
                    </table>          
                </div>
                <section class="cabecera-derecha">
				    <!--right sidebar-->
                     <div class="row">
                            <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">
                    <aside class="left-sidebar">
                            <!--Booking details-->
                        <article class="booking-details clearfix">
                            <div    onclick = {__MostrarFormularioDePago} id="btnGrandePagar" class="head green well" style="color: #fff; font-size: 25px;  padding-top:8px !important; padding-bottom:8px !important; margin-bottom: 8px;">
                                <table id="pagarTable" width="100%">
                                    <tbody>
                                        <tr>
                                            <td width="30%" id="">
                                                <div id="pagarTitulo">{$.i18n.prop("factura.total")}</div>
                                            </td>
                                            <td width="70%" id="">
                                            
                                                <div id="">
                                                    <span class="label label-info textShadow" id="total-show">₡ {factura.totalVentaNeta.toLocaleString('de-DE')}</span>
                                                </div>
                                            </td>
                                        </tr>                     
                                    </tbody>
                                </table>
                            </div>
                        </article>
                    </aside>
                    </div>
                    </div>
                    <section   class="lista-compras-espera">
                        <div id="botones"  each={facturas_espera.data}  onclick={__CargarFacturaEspera}>
                            <a href="#" class="compras-espera"  title="{cliente !=null?cliente.nombreCompleto:""}">C# {id}</a>
                        </div>    
                     </section >

                </section>
                      
            </div><!-- fin contenedor-compra-->
            
        </div><!-- fin box-body-->
	</div><!-- fin box -->
<!--Modal mostrar Articulos de la empresa -->
<div id='modalInventario' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("articulo.listar")} </h4>
            </div>
            <div class="modal-body">
                <table id="tableListarArticulos" class="display table responsive table-hover nowrap table-condensed tableListarArticulos " cellspacing="0" width="100%">
                    <thead>
                        <th class="table-header">{$.i18n.prop("articulo.codigo")}        </th>
                        <th class="table-header">{$.i18n.prop("articulo.descripcion")}   </th>
                        <th class="table-header">{$.i18n.prop("inventario.cantidad")}    </th>
                        <th class="table-header">{$.i18n.prop("articulo.precioPublico")} </th>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}       </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th >{$.i18n.prop("articulo.codigo")}         </th>
                            <th >{$.i18n.prop("articulo.descripcion")}   </th>
                            <th >{$.i18n.prop("inventario.cantidad")}    </th>
                            <th >{$.i18n.prop("articulo.precioPublico")} </th>
                            <th >                                        </th>
                        </tr>
                    </tfoot>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->
<!--Modal mostrar  -->
<div id="modalClientes" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("cliente.lista")}   </h4>
            </div>
            <div class="modal-body">
                <table id="tableListaCliente" class="table responsive display table-striped table-hover nowrap tableListaCliente " cellspacing="0" width="100%">
                   <thead>
                        <th class="table-header">{$.i18n.prop("cliente.cedula")}            </th>
                        <th class="table-header">{$.i18n.prop("cliente.nombreCompleto")}    </th>
                        <th class="table-header">{$.i18n.prop("cliente.correoElectronico")} </th>
                        <th class="table-header">{$.i18n.prop("cliente.descuento")}         </th>
                        <th class="table-header">{$.i18n.prop("cliente.telefono")}          </th>
                        <th class="table-header">{$.i18n.prop("cliente.celular")}           </th>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("cliente.cedula")}           </th>
                            <th>{$.i18n.prop("cliente.nombreCompleto")}   </th>
                            <th>{$.i18n.prop("cliente.correoElectronico")}</th>
                            <th>{$.i18n.prop("cliente.descuento")}        </th>
                            <th>{$.i18n.prop("cliente.telefono")}         </th>
                            <th>{$.i18n.prop("cliente.celular")}          </th>
                            <th>                                          </th>
                        </tr>
                    </tfoot>                    
                </table>
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
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
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
                            <input  type="number" class="form-control cambiarCantidadArticulo" id="cambiarCantidadArticulo" name = "cambiarCantidadArticulo" autofocus="autofocus">
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
                            <input  type="number" class="form-control aplicarDescuento" id="aplicarDescuento" name = "aplicarDescuento" autofocus="autofocus">
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

<style type="text/css">
    /* Lista de facturas en espera*/
    .cabecera-derecha .lista-compras-espera{
        width:100%;
        display:flex;
        flex-wrap:wrap;
    }
    .cabecera-derecha .lista-compras-espera .compras-espera{
        display:block;
        width:90%;
        padding:6px 0;
        margin-bottom:20px;
        margin-left:15px;
        margin-right:5px;
        background:red;
        text-align:center;
        text-decoration:none;
        color:#ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
    }
    .label-limpiar{
        font-weight: 600 !important;
        font-size: 20px !important;
        font-family: Roboto,sans-serif !important;
        color: #ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        padding-left: 20px;
        line-height: 30px;
        border-collapse: separate;
        text-align: center;
        cursor: pointer;
        padding: 5px;
        margin: 10px;
        border: none;
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
    #pagarTitulo{
        font-weight: 600 !important;
        font-size: 30px !important;
        font-family: Roboto,sans-serif !important;
        color: #ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        padding-left: 20px;
        line-height: 30px;
    }
    .tamanoLetraTotales{
        font-weight: 600 !important;
        font-size: 30px !important;

    }
    #pagarTable,#pagarTableInfo{
        border-collapse: separate;
    }
    #pagarTableInfo{
        background-color: #f2f2f2;
        color: #000;
        text-align: center;
    }
    #total-show {
        padding: 0px;
        font-weight: 400;
        background: none!important;
        font-size: 30px;
        color: #ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        padding-top: 0px;
        line-height: 40px;
    }
    #btnGrandePagar,#btnGrandePagar2{
        cursor: pointer;
        padding: 0px;
        margin: 10px;
        border: none;
        text-align: center !important;
        background-color: #6dca42 !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
    } 
    #pagarInfo,#iva-total,#subtotal,#sigPeso{
        font-weight: 100 !important;
        font-size: 14px !important;
    }
    #pagarInfo{
        font-size: 12px !important;
    }
    *{
       margin:0;
       padding:0;
       box-sizing:border-box;
    }
    body{
        background:white;
    }
    .wrap{
        max-width:1100px;
        width:100%;
        margin:auto;
    }
    .contenedor-compra {
        display:flex;
        width:100%;
        margin :auto;
    }
    .cabecera-izquierda {
       margin-right:5%;
       width:85%;
    }

    .cabecera-derecha {
        width:25%;
    }
    .contenedor-detalle   {
        display:flex;
        width:100%;
        margin :auto;
    }

    .booking-details h1 {
        font-size: 1.5em;
        color: #666;
        text-shadow: none;
    }
    .booking-details .booking-info {
        border-top: 1px solid #DFDCD1;
        padding: 15px 0 0;
        margin: 15px 0 0;
        font-size:25px;
        display: inline-block;
        width: 100%;
    }
    .total{
        font-weight:bold;
        font-size:20px;
    }
    .precioTotalFactura{
        font-weight:bold;
        font-size:30px;
        color: #0C9C22;
        border-top: 1px solid #DFDCD1;
        padding: 0 0 5px;
        padding: 15px 0 0;
        margin: 10px 0 0;
    }
    label {
        display: inline-block;
        max-width: 100%;
        margin-bottom: 5px;
        font-weight: 600;
    }
    .tituloTotal{
        font-size:30px;
    }
</style>

<script>
    var self = this;
    // Detalle de la factura es una coleccion de articulos
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.comboEstados          = []
    self.comboCondicionPagos        = []
    self.comboTipoDocumentos   = []
    self.factura                = {
        id:0,
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
	    estado:0,
	    cliente:{
            id:0,
            nombreCompleto:""
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        }

    }                   
    self.item                  = null;
    self.articulo              = null;
    self.articulos             = {data:[]}
    self.clientes              = {data:[]}
    self.detalleFactura        = {data:[]}
    self.cliente               = {};
    self.vendedor              = {
        id:0,
        nombreCompleto:""
    };
    self.facturas_espera       = {data:[]}  
    self.informacion_tabla             = []
    self.informacion_tabla_articulo    = []
    self.informacion_tabla_clientes    = []
    self.idiomaDataTable               = {}
    self.mostrarFormularioPago         = false
    self.mostarParaCrearNuevaFactura    = true
    self.mostrarCamposIngresoContado   = true;

    self.on('mount',function(){
         
         
        $("#formularioFactura").validate(reglasDeValidacionCompra());
        __informacionData()
        __informacionData_vendedores()
        __InicializarTabla('.tableListaCliente')
        __InicializarTabla('.tableListaInventario')
        __InicializarTabla('.tableListaVendedor')
        agregarInputsCombos_Articulo()
        __ListaFacturasEnEspera()
       // setInterval(function() {
            // triggering the "ready" event will resolve the promise
        //    __ListaFacturasEnEspera()
        //}.bind(this), 10000)
          
        __comboCondicionPago()
        __ComboTipoDocumentos()
        __ComboEstados()
        __ListaDeClientes()
       __ListaDeVendedores()
       __Teclas()
       __TipoCambio()
    })

    
/**
* Camps requeridos
**/
var reglasDeValidacionCompra = function() {
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
* Aplicar el descuento
**/
__CambiarDescuento(e){
    self.item = e.item; 
    self.update()
    $('#modalCambiarDescuento').modal('show')      
}

/**
*Cambiar Cantidad del Articulo
**/
__CambiarCantidad(e){
   var cantidad = e.currentTarget.value;
   self.item = e.item; 
   self.update()
   $( "#cambiarCantidadArticulo" ).focus()
   $( "#cambiarCantidadArticulo" ).val(cantidad)
   $('#modalCambiarCantidad').modal('show')      
}

/**
* Tipo Cambio de moneda
**/
function __TipoCambio(){
    self.tipoCambio = {}
    self.update()
    $.ajax({
        url: "MostrarTipoCambioActivoAjax.do",
        datatype: "json",
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

__Imprimir(){
    var factura = self.factura
    riot.mount('ptv-imprimir',{factura:factura});
}
/**
*  Obtiene el valor de lo digitado en el campo de efectivo
**/
__TotalDeEfectivoAPagar(e){
    self.factura.totalEfectivo = __valorNumerico(e.target.value) 
    self.update()
}
/**
*  Obtiene el valor de lo digitado en el campo de Tarjeta
**/
__TotalDeTarjetaAPagar(e){
    self.factura.totalTarjeta = __valorNumerico(e.target.value) 
    self.update()
}
/**
*  Obtiene el valor de lo digitado en el campo de Banco
**/
__TotalDeBancoAPagar(e){
    self.factura.totalBanco = __valorNumerico(e.target.value) 
    self.update()
}
/**
*   Calculo del cambio entregar en el evento onblur
**/
__CalculaCambioAEntregarOnblur(e){
    var sumaMontosEntregadosParaCambios =__valorNumerico(self.factura.totalTarjeta)
    sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco) 
    sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo) 
    //Si no ingresado montos no realiza las operaciones de calculos
    if(sumaMontosEntregadosParaCambios == 0){
        return
    }
    self.factura.totalCambioPagar = 0
    self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios > self.factura.totalVenta ? sumaMontosEntregadosParaCambios - self.factura.totalVenta:sumaMontosEntregadosParaCambios - self.factura.totalVenta    
    self.update()
}
/**
*   Calculo del cambio entregar en el evento keyPress
**/
__CalculaCambioAEntregarKeyPress(e){
    var sumaMontosEntregadosParaCambios =0
    if (e.keyCode == 13) {
        sumaMontosEntregadosParaCambios  = __valorNumerico(self.factura.totalTarjeta)
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco) 
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo) 
        if(sumaMontosEntregadosParaCambios == 0){
            return
        }
        self.factura.totalCambioPagar = 0
        self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios > self.factura.totalVenta ? sumaMontosEntregadosParaCambios - self.factura.totalVenta:sumaMontosEntregadosParaCambios - self.factura.totalVenta    
        self.update()
    }
}
/**
 * Listar codigos  llamado del modal para presentar los articulos
 **/   
 __ListaDecodigos(){
     __ListaDeArticulosPorEmpresa();
 }
/**
*  Buscar la Compra Pendiente en espera
**/
__CargarFacturaEspera(e){
   __FacturaEnEspera(e.item)
}
/**
** Se aplica o se crea una compra cargada en la pantalla
**/
__AplicarYcrearFactura(){
 aplicarFactura()
}

function aplicarFactura(){
    if(self.detail.length == 0 ){
        mensajeError($.i18n.prop("factura.alert.sin.detalles"))
        return
    }
    if(condicionVenta.value == "02"  ){
        if(fechaCredito.value == null || fechaCredito.value.length == 0){
           mensajeError($.i18n.prop("factura.alert.fechaCredito"))
            return
        }
    }else{
        // Si no es credito y el estado no es pendiente se debe verificar si ingresaron el monto a pagar
        if(estado.value !=1){
            if(self.factura.totalTarjeta == 0 && self.factura.totalBanco == 0 && self.factura.totalEfectivo == 0){
                mensajeError($.i18n.prop("error.factura.monto.ingresado"))
                return
            }
            var montoEntregado = self.factura.totalTarjeta + self.factura.totalBanco + self.factura.totalEfectivo
            montoEntregado = __valorNumerico(montoEntregado)
            if(self.factura.totalVentaNeta > montoEntregado  ){
                mensajeError($.i18n.prop("error.factura.monto.ingresado.es.menor.ala.venta"))
                return
            }
            //Si el cliente esta pagando con tajeta, banco debe ser igual a la venta
            if(self.factura.totalTarjeta != 0 || self.factura.totalBanco !=0){
                if(self.factura.totalVentaNeta != montoEntregado  ){
                    mensajeError($.i18n.prop("error.factura.monto.tarjeta.banco.igual.venta"))
                return
                    
                }
            }
            
        }
    } 
    if ($("#formularioFactura").valid()) {
        swal({
           title: '',
           text: $.i18n.prop("factura.alert.crear"),
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
               crearFactura()  
              
            }
        });
    }
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
    
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.comboEstados          = []
    self.comboCondicionPagos        = []
    self.comboTipoDocumentos   = []
    self.facturas_espera       = {data:[]}  
    self.factura                = {
        id:0,
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
	    estado:0,
	    cliente:{
            id:0,
            nombreCompleto:"",
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        }
    }                            
    self.item                  = null;
    self.articulo              = null;
    self.articulos             = {data:[]}
    self.clientes              = {data:[]}
    self.detalleFactura        ={data:[]}
    self.cliente               = {};
    self.vendedor              = {
        id:0,
        nombreCompleto:""
    }
    self.tipoCambio                    = {}
    self.informacion_tabla             = []
    self.informacion_tabla_articulo    = []
    self.informacion_tabla_clientes    = []
    self.idiomaDataTable               = {}
    self.mostrarFormularioPago         = false
    self.mostarParaCrearNuevaFactura    = true
    self.mostrarCamposIngresoContado   = true;
    self.update();
    // Tipo de Pagos
     __comboCondicionPago()
     //Tipos de Documentos
      __ComboTipoDocumentos()
      //Estados
      __ComboEstados()
     __ListaFacturasEnEspera()
    
}



/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(factura){
     __Init()
    $.ajax({
        url: "MostrarFacturaAjax",
        datatype: "json",
        data: {idFactura:factura.id},
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.factura = modeloTabla
                       self.factura.fechaCredito = self.factura.fechaCredito !=null?__displayDate_detail(self.factura.fechaCredito):null
                       self.cliente  = modeloTabla.cliente
                       self.vendedor = modeloTabla.vendedor
                       self.update()
                    });
                }
                cargarDetallesFacturaEnEspera()
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
function cargarDetallesFacturaEnEspera(){
    self.detail = [];
    self.update()
    self.factura.detalles.forEach(function(e){
        self.detail.push({
            numeroLinea     : e.numeroLinea,
            articulo_id     : e.articulo.id,
            codigo          : e.articulo.codigo,
            descripcion     : e.articulo.descripcion,
            cantidad        : redondearDecimales(parseFloat(e.cantidad),5),
            precioUnitario  : redondearDecimales(parseFloat(e.precioUnitario),5),
            impuesto        : redondearDecimales(parseFloat(e.impuesto),5),
            montoImpuesto   : redondearDecimales(parseFloat(e.montoImpuesto),5),
            montoDescuento  : redondearDecimales(parseFloat(e.montoDescuento),5),
            totalImpuesto   : e.montoImpuesto * e.cantidad,
            totalDescuento  : e.montoDescuento * e.cantidad,
            porcentajeDesc  : redondearDecimales(parseFloat(e.porcentajeDesc),5),
            subTotal        : redondearDecimales(parseFloat(e.subTotal),5),
            montoTotalLinea : redondearDecimales(parseFloat(e.montoTotalLinea),5),
            montoTotal      : redondearDecimales(parseFloat(e.montoTotal),5)
        });
    })
    self.update()
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
*  Crear Compra nueva
**/
function crearFactura(){
    self.detalleFactura.data =self.detail
    self.update() 
    var fechaCreditoTemporal =condicionVenta.value == "02"?fechaCredito.value:new Date() 
     var JSONDetalles = JSON.stringify( self.detalleFactura );
    var informacion = {
        id:self.factura.id,
	    fechaCredito:fechaCreditoTemporal.toString(),
	    condicionVenta:condicionVenta.value,
	    plazoCredito:self.factura.plazoCredito,
	    tipoDoc:tipoDoc.value,
	    medioPago:self.factura.medioPago,
	    nombreFactura:self.factura.nombreFactura,
	    direccion:direccion.value,
	    nota:nota.value,
	    comanda:self.factura.comanda,
	    subTotal:self.factura.subTotal,
	    totalTransporte:self.factura.totalTransporte,
	    total:self.factura.total,
	    totalServGravados:self.factura.totalServGravados,
	    totalServExentos:self.factura.totalServExentos,
	    totalMercanciasGravadas:self.factura.totalMercanciasGravadas,
	    totalMercanciasExentas:self.factura.totalMercanciasExentas,
	    totalGravado:self.factura.totalGravado,
	    totalExento:self.factura.totalExento,
	    totalVenta:self.factura.totalVenta,
	    totalDescuentos:self.factura.totalDescuento,
	    totalVentaNeta:self.factura.totalVentaNeta,
	    totalImpuesto:self.factura.totalImpuesto,
	    totalComprobante:self.factura.totalComprobante,
	    totalEfectivo:totalEfectivo.value ==null || totalEfectivo.value ==0?0:totalEfectivo.value,
	    totalTarjeta:totalTarjeta.value ==null   || totalTarjeta.value ==0?0: totalTarjeta.value,
	    totalBanco:totalBanco.value == null      || totalBanco.value == 0?0:totalBanco.value ,
	    totalCredito:0,
        totalCambioPagar:self.factura.totalCambioPagar,
	    montoCambio:0,
	    totalCambio:0,
	    estado:estado.value,
	    cliente:cliente.value,
        vendedor:vendedor.value == null?0:vendedor.value,
        detalleFactura :JSONDetalles,

    }                  
    $.ajax({
        type : "POST",
        dataType : "json",
        data : informacion,
        url : "CrearFacturaAjax",
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                    mensajeError(data.message)
                }
            } else {
               	serverMessageJsonClase(data);
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
   if (data.message != null && data.message.length > 0) {
        $.each(data.listaObjetos, function( index, modeloTabla ) {
            self.facturaImprimir   = modeloTabla
            self.update()
            if(self.facturaImprimir.estado == 2){
                __Init()
                //Envia a la pantalla de impresion
                 riot.mount('ptv-imprimir',{factura:self.facturaImprimir});
                 
            }else{
                swal({
	                title: '',
	                text: data.message,
	                type: 'success',
	                showCancelButton: false,
	                confirmButtonText: $.i18n.prop("btn.aceptar"),
                })
                __Init()
                __ListaFacturasEnEspera()
            }
        });
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
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
               self.facturas_espera.data =  result.aaData;  
               self.update(); 
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
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
   self.error = false
   self.update()
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
}
/**
*   funcion para grabar la compra en el back end
**/
__MostrarFormularioDePago(){
    mostrarPAgo()
}


function mostrarPAgo(){
     //No hay detalles registrados en la compra
    if(self.detail.length == 0 ){
        swal("Verificar","No hay detalles en la compra ", "info")
        return
    }
    
    $('#totalEfectivo').val(null)
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    self.factura.totalCambioPagar =0
    self.mostarParaCrearNuevaFactura = false
    self.mostrarFormularioPago = true
    self.update()
    $('#totalEfectivo').focus()
    self.factura.cambioMoneda = self.factura.totalVentaNeta / self.tipoCambio.total
    self.update()

}
/** 
*
*Agregar codigos al detalle de la Compra
*
*/
__addProductToDetail(e){
    if (e.keyCode != 13) {
        return;
    } 
    __buscarcodigo(e.currentTarget.value);
}
/**
* Buscar codigo
**/
__agregarArticuloBotonAgregar(){
   __buscarcodigo($( "#codigo" ).val(),$( "#quantty" ).val());
}
/**
* mostrar la lista de articulos de la empresa
**/
function __ListaDeArticulosPorEmpresa(){
    $.ajax({
        url: 'ListarArticuloAjax.do',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                _informacionData_Articulo()
                self.articulos.data           = result.aaData
                self.update()
                loadListar(".tableListarArticulos",idioma_espanol,self.informacion_tabla_articulo,self.articulos.data)
                agregarInputsCombos_Articulo()
                __agregarArticulos()
                ActivarEventoFiltro(".tableListarArticulos")
                $('#modalInventario').modal('show')    
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
}
/**
*  Lista de los clientes
**/
function __ListaDeClientes(){
    $.ajax({
        url: 'ListarClientesActivosAjax.do',
        datatype: "json",
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
}
/**
* Buscar el codigo del codigo  en la base de datos
**/
function __buscarcodigo(idArticulo,cantidad){
    self.articulo = null;
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
                        //Articulo no puede agregarse si no hay en el inventario
                        if(modeloTabla.cantidad < 0 || modeloTabla.cantidad == 0 ){
                            mensajeError($.i18n.prop("error.articulo.sin.existencia.en.inventario"))
                            return
                        }
                        if(modeloTabla.cantidad < cantidad ){
                            mensajeError($.i18n.prop("error.articulo.tiene.menor.existencia.en.inventario.a.la.venta"))
                            return
                        }
                        self.articulo  = modeloTabla
                        self.update()
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
     if(self.detail[0] == null){ // first element
        __nuevoArticuloAlDetalle(cantidad);
        encontrado = true;
    }else{//Se busca el articulo si existe se incrementa la cantidad
        for (var count = 0; count < self.detail.length; count++) {
            if (self.detail[count].articulo_id == self.articulo.id ){
               self.item          = self.detail[count];
               self.item.cantidad = self.item.cantidad + parseFloat(cantidad)
               self.update();
               __actualizarItemArray();
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
    __calculate(); 
}
/**
* eliminar un detalle Compra
**/
__removeProductFromDetail(e) {
    var item = e.item;
    index = this.detail.indexOf(item);
    this.detail.splice(index, 1);
    var cont = 0 ;
    self.detail.forEach(function(elemen){
            elemen.linea = cont + 1
            cont = elemen.linea
        }
    )
    self.update()
     __calculate();
 }

 /**
*   agregar Articulos nuevos en el detalle de la Compra
**/
function __nuevoArticuloAlDetalle(cantidad){
    if(self.articulo.descripcion == null){
        return;
    }
    if(self.articulo.descripcion == ""){
        return;
    }
    var precioUnitario  = getPrecioUnitario(self.articulo.precioPublico,self.articulo.impuesto ==null?0:self.articulo.impuesto)
    var montoImpuesto   = _calcularImpuesto(self.articulo.precioPublico,self.articulo.impuesto ==null?0:self.articulo.impuesto)
    var totalImpuesto   = montoImpuesto * cantidad
    var subTotal        = getSubTotal(precioUnitario,cantidad)
    self.descuento      = 0;
    self.detail.push({
       numeroLinea     : 0,
       iva             : self.articulo.impuesto,
       articulo_id     : self.articulo.id,
       codigo          : self.articulo.codigo,
       descripcion     : self.articulo.descripcion,
       cantidad        : parseFloat(cantidad),
       precioUnitario  : precioUnitario,
       impuesto        : self.articulo.impuesto,
       montoImpuesto   : montoImpuesto,
       montoDescuento  : 0,
       porcentajeDesc  : 0,
       totalImpuesto   : totalImpuesto,
       totalDescuento  : 0,
       subTotal        : subTotal,
       montoTotalLinea : subTotal + totalImpuesto,
       montoTotal      :0
    });
    var cont = 0;
    self.detail.forEach(function(elemen){
          elemen.linea = cont + 1
          cont = elemen.linea
        }
    )
    self.update()
}



/**
* Obtiene el precio unitario sin descuento sin impuesto
**/
function getPrecioUnitario(precio,iva){
    if(iva > 0){
        valorImpuesto = iva/100
    }else{
      return redondearDecimales(precio,5)     
    }
   var valorIva      = parseFloat(1+valorImpuesto)
   return redondearDecimales(precio /valorIva,5)
}
/**
 * calculo del impuesto iva
 * */
function _calcularImpuesto(precio,iva){
    if(iva == 0){
        return 0;
    }
    var impuesto = iva > 0 ?parseFloat(iva)/100:0
    impuesto = impuesto > 0 ?impuesto + 1:0
    var precioSinImpuesto  = parseFloat(precio) / impuesto
    var total = precio - precioSinImpuesto
    return redondearDecimales(total ,5)
}
   
 /**
 * Cuando se aplica un cambio de cantidad en un detalle
 * Se aplica una recalculacion de todo el detalle y Factura
 **/ 
 __recalculacionDelDetalle(e){
   // if (e.keyCode != 13) {
   //     return;
   // } 
    var cantidad = $(".cambiarCantidadArticulo").val();
  //  self.item    = e.item; 
//    var input    = e.input;
   
    
    //Cantidad del detalle se verifica si es null o espacio por defecto se deja en 1
    cantidad =__valorNumerico(cantidad);
    if(cantidad == 0){
       cantidad = 1;
    }
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
                        //Articulo no puede agregarse si no hay en el inventario
                        if(modeloTabla.cantidad < 0 || modeloTabla.cantidad == 0 ){
                            mensajeError($.i18n.prop("error.articulo.sin.existencia.en.inventario"))
                            return 
                        }
                        if(modeloTabla.cantidad < cantidad ){
                            mensajeError($.i18n.prop("error.articulo.tiene.menor.existencia.en.inventario.a.la.venta"))
                            return 
                        }
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
    self.item.cantidad = parseFloat(cantidad); 
    self.item.totalImpuesto = self.item.cantidad * self.item.montoImpuesto; 
    self.item.totalDescuento = self.item.cantidad * self.item.montoDescuento; 
    self.update()
    var index    = self.detail.indexOf(self.item);
    __actualizarItemArray();
    self.detail[index] = self.item;
    self.update()
    __calculate()
    cambiarCantidadArticulo.value = 0
    $('#modalCambiarCantidad').modal('hide') 
}

/**
* Actualizar el descuento del codigo
**/
__actualizarDescuento(e){
    //if (e.keyCode != 13) {
    //    return;
    //} 
    _actualizarDesc(e)

}



function _actualizarDesc(e){
//    self.item     = e.item; 
    var index     = self.detail.indexOf(self.item);
    var descuento = $(".aplicarDescuento").val();
    //Descuento se verifica si es null o espacios por defecto se deja en cero
     descuento =__valorNumerico(descuento);
      //Descuento
    if(self.item.porcentajeDesc != descuento){
       self.item.porcentajeDesc =  parseFloat(descuento);  
    }    
    
    //Total del descuento
    var montoDescuento        =  self.item.precioUnitario  * (self.item.porcentajeDesc/100)
    self.item.montoDescuento  = montoDescuento 
    self.item.totalDescuento  = montoDescuento * self.item.cantidad
    var porcentaje =  self.item.impuesto /100
    var montoImpuesto   = self.item.precioUnitario * porcentaje
    var totalImpuesto   = montoImpuesto * self.item.cantidad
    self.item.montoImpuesto = montoImpuesto
    self.item.totalImpuesto = totalImpuesto
    self.update()

    __actualizarItemArray();
    self.detail[index] = self.item;
    self.update();
    __calculate();
    $('#modalCambiarDescuento').modal('hide') 
    aplicarDescuento.value = 0
}
/**
* Actualizar item en el array
**/
function __actualizarItemArray(){
    var subTotal  = getSubTotal(self.item.precioUnitario,self.item.cantidad)
    self.item.totalDescuento = self.item.montoDescuento * self.item.cantidad
    subTotal = subTotal 
    self.item.subTotal        = subTotal
    self.item.totalImpuesto   = self.item.montoImpuesto * self.item.cantidad
    self.update()
    var montoTotalLinea       = getMontoTotalLinea(self.item.subTotal - self.item.totalDescuento,self.item.totalImpuesto)    

    self.item.montoTotalLinea = montoTotalLinea
    self.item.montoImpuesto   = montoTotalLinea == 0?0:self.item.montoImpuesto
    self.item.totalImpuesto   = montoTotalLinea == 0?0:self.item.totalImpuesto
    self.update()
}
/**
* Monto a pagar en la linea el cliente
**/
function getMontoTotalLinea(subTotal,totalImpuesto){
  return subTotal == 0?0:redondearDecimales(subTotal + totalImpuesto,5)
}
/**
*  Obtener el subtotal sin el impuesto
**/
function getSubTotal(precio,cantidad){
    var valor = precio * cantidad
    return redondearDecimales(valor,5) 
}
/**
* calcular el descuento
**/
function getTotalDescuento(precio,cantidad,porcentajeDesc){
    var porcentaje = porcentajeDesc/100
    var valor =  precio * porcentaje
    return redondearDecimales(valor * cantidad,5)
}
/**
* calculacion de los detalle de la compra 
**/
function __calculate() {
    self.factura.total           = 0;
    self.factura.totalDescuento  = 0;
    self.factura.totalImpuesto   = 0;
    self.factura.subTotal        = 0;
    self.update()
    totalVenta     = 0
    subTotal       = 0
    totalDescuento = 0
    totalImpuesto  = 0
    totalMercanciasGravadas = 0
    totalMercanciasExentas  = 0
    totalGravado            = 0
    totalExento             = 0
    totalComprobante        = 0
    self.detail.forEach(function(e){
        totalMercanciasGravadas += e.montoImpuesto > 0?e.montoTotalLinea:0
        totalMercanciasExentas  += e.impuesto == 0?e.montoTotalLinea:0
        totalGravado            += e.impuesto > 0 ?e.montoTotalLinea:0
        totalExento             += e.impuesto == 0?e.montoTotalLinea:0
        totalComprobante        += e.montoTotalLinea
        totalVenta              += e.montoTotalLinea >0?e.montoTotalLinea:0
        subTotal                += e.subTotal >0?e.subTotal:0
        totalDescuento          += e.totalDescuento >0?e.totalDescuento:0
        totalImpuesto           += e.totalImpuesto >0?e.totalImpuesto:0
    });
    self.factura.totalMercanciasGravadas = redondearDecimales(__valorNumerico(totalMercanciasGravadas),5)
    self.factura.totalMercanciasExentas  = redondearDecimales(__valorNumerico(totalMercanciasExentas),5)
    self.factura.totalGravado            = redondearDecimales(__valorNumerico(totalGravado),5)
    self.factura.totalExento             = redondearDecimales(__valorNumerico(totalExento),5)
    self.factura.totalVenta              = redondearDecimales(__valorNumerico(totalVenta),5) 
    self.factura.totalDescuento          = redondearDecimales(__valorNumerico(totalDescuento),5)
    self.factura.subTotal                = redondearDecimales(__valorNumerico(subTotal),5)
    self.factura.totalImpuesto           = redondearDecimales(__valorNumerico(totalImpuesto),5)
    self.factura.totalVentaNeta          = redondearDecimales(__valorNumerico(totalVenta),5) 
    self.factura.totalComprobante        = redondearDecimales(__valorNumerico(totalComprobante),5)

    self.articulo              = null;
    self.update(); 
    $( "#codigo" ).val(null);
    $( "#quantity" ).val(null);
}

function redondearDecimales(numero, decimales) {
    numeroRegexp = new RegExp('\\d\\.(\\d){' + decimales + ',}');   // Expresion regular para numeros con un cierto numero de decimales o mas
    if (numeroRegexp.test(numero)) {         // Ya que el numero tiene el numero de decimales requeridos o mas, se realiza el redondeo
        return Number(numero.toFixed(decimales));
    } else {
        return Number(numero.toFixed(decimales)) === 0 ? 0 : numero;  // En valores muy bajos, se comprueba si el numero es 0 (con el redondeo deseado), si no lo es se devuelve el numero otra vez.
    }
}
/**
* Definicion de la tabla articulos 
**/
function _informacionData_Articulo(){
   self.informacion_tabla_articulo = [	{'data' : 'codigo'         ,"name":"codigo"          ,"title" : $.i18n.prop("articulo.codigo")       ,"autoWidth":false},
                                        {'data' : 'descripcion'    ,"name":"descripcion"     ,"title" : $.i18n.prop("articulo.descripcion")  ,"autoWidth":false},
                                        {'data' : 'cantidad'       ,"name":"cantidad"        ,"title" : $.i18n.prop("inventario.cantidad")   ,"autoWidth":false},
                                        {'data' : 'precioPublico'  ,"name":"precioPublico"   ,"title" : $.i18n.prop("articulo.precioPublico"),"autoWidth":false,
                                          "render":function(precioPublico,type, row){
                                               return  "₡" + precioPublico.toLocaleString('de-DE');
                                            }
                                        },
                                        {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
                                            "render":function(id,type, row){
                                                    return __OpcionesArticulos(id,type,row);
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
* Agregar codigos a la compra desde modal de articulos
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
	    __buscarcodigo(self.articulo.codigo,1)
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
                                        {'data' : 'descuento'        ,"name":"descuento"         ,"title" : $.i18n.prop("cliente.descuento")         ,"autoWidth":false},                                
                                        {'data' : 'telefono'         ,"name":"telefono"          ,"title" : $.i18n.prop("cliente.telefono")          ,"autoWidth":false},                                
                                        {'data' : 'celular'          ,"name":"celular"           ,"title" : $.i18n.prop("cliente.celular")           ,"autoWidth":false},                                
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
* Agregar codigos a la compra desde modal de articulos
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
        self.cliente.descripcionProvincia = __cargaProvincias(self.cliente.provincia)
        self.update();
    });
}
/**
*  retorna el valor numerico o cero sino es numerico
**/
function __valorNumerico(valor){
    return isNumber(valor)?parseFloat(valor):0 ;
}
/**
*  Validar si es numero
**/
function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}
/**
* cargar los estados de la compra
**/
function __comboCondicionPago(){
    self.comboCondicionPagos = []
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
* cargar los tipos de Documento de la compra
**/
function __ComboTipoDocumentos(){
    self.comboTipoDocumentos = []
    self.comboTipoDocumentos.push({
        estado:"00",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
    })
    self.comboTipoDocumentos.push({
         estado:"01",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
    })
    self.comboTipoDocumentos.push({
         estado:"02",
        descripcion:$.i18n.prop("factura.tipo.documento.nota.debito")
    })
    self.comboTipoDocumentos.push({
         estado:"03",
        descripcion:$.i18n.prop("factura.tipo.documento.nota.credito")
    })
    self.update()
}
/**
* cargar los estados de la compra
**/
function __ComboEstados(){
    self.comboEstados = []
    self.comboEstados.push({
        estado:1,
        descripcion:$.i18n.prop("factura.estado.pendiente")
    })
    self.comboEstados.push({
        estado:2,
        descripcion:$.i18n.prop("factura.estado.facturado")
    })
    
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
        if ( $(this).index() != 4    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
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
        if ( $(this).index() != 5    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
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
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
/**
 * Obtener la provincia 
 * */
function __cargaProvincias(provincia){
    if(provincia =="1"){
      return $.i18n.prop("provincia.sanjose")
    }
    if(provincia =="2"){
      return $.i18n.prop("provincia.alajuela")
    }    
    if(provincia =="3"){
      return $.i18n.prop("provincia.cartago")
    }    
    if(provincia =="4"){
      return $.i18n.prop("provincia.heredia")
    }    
    if(provincia =="5"){
      return $.i18n.prop("provincia.guanacaste")
    }    
    if(provincia =="6"){
      return $.i18n.prop("provincia.puntarenas")
    }    

   if(provincia =="7"){
      return $.i18n.prop("provincia.limon")
    }    
    return "No Registrada"
}



/**
*  teclas de la pantalla
**/      
function __Teclas(){
    window.addEventListener( "keydown", function(evento){
        var tecla = evento.keyCode; 
    if(tecla ==119){
      mostrarPAgo()     
   
    }   
    //aplicar crear Factura
    if (self.mostrarFormularioPago == true ){
   //    aplicarFactura()   
    } 

   
    }, false );
  
   

}

</script>

</punto-venta>