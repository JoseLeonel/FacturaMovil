<compra-simplificado>
<!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-calculator"></i>&nbsp Compras Simplificadas  </h1>
        </div>
        <div class=" col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
    <br>
<!--Modal mostrar Proveedores de una sucursal -->
<div id="modalProveedores" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("proveedor.lista")}   </h4>
            </div>
            <div class="modal-body">
                <table id="tableListaProveedor" class="table responsive display table-striped table-hover nowrap tableListaProveedor " cellspacing="0" width="100%">
                   <thead>
                        <th class="table-header">{$.i18n.prop("proveedor.nombreCompleto")} </th>
                        <th class="table-header">{$.i18n.prop("proveedor.email")}          </th>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}        </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("proveedor.nombreCompleto")} </th>
                            <th>{$.i18n.prop("proveedor.email")}          </th>
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
 

    <div class="box" show={mostrarFormularioPago}>
        <div class="box-body">
            <div  class="contenedor-compra " >
                <div class="cabecera-izquierda">
                    <div class="box-header with-border fondoEncabezado">
						<h3 class="box-title">Formulario de Compra {compra.id>0?' Id#:'+compra.id:'' } </h3>
					</div>
                    <div class="box-body">
                        <form id="formularioCompra">
                            <input   type="hidden" class="proveedorSimplificado" id="proveedorSimplificado" name="proveedorSimplificado" value="{proveedorSimplificado.id}">
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
                                        <input type="text" class="form-control nota" id="nota" name="nota" value="{factura.nota}">
                                    </div>
                                    <div show = "{mostrarCamposIngresoContado ==false }" class="form-group ">
                                        <label >{$.i18n.prop("factura.fecha.credito")}</label> 
                                        <div  class="form-group input-group date datepickerFechaCredito" data-provide="datepicker"  data-date-start-date="0d" data-date-format="yyyy-mm-dd">
                                            <input  onclick={__ActualizarPlazoCredito} type="text" class="form-control fechaCredito selectFechaCredito" name="fechaCredito" id="fechaCredito" value="{factura.fechaCredito}" >
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>    
                                    <div class="form-group " show = "{mostrarCamposIngresoContado == false}">
                                        <label>{$.i18n.prop("factura.plazoCredito")}</label> 
                                        <input type="number" id = "plazoCreditoL"  name "plazoCreditoL" class="form-control plazoCreditoL" value="{factura.plazoCredito}" >
                                    </div>

                                </div>
                                <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                    <div class="form-group ">
                                        <label class="{labelTotales}">{$.i18n.prop("factura.resumen.efectivo")} </label> 
                                        <input onclick={_SeleccionarEfectivo}   onkeyup={ __TotalDeEfectivoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control totalEfectivo " id="totalEfectivo" name="totalEfectivo" value="{factura.totalEfectivo}" >
                                    </div>
                                    <div  class="form-group ">
                                        <label class="{labelTotales}">{$.i18n.prop("factura.resumen.tarjeta")} </label> 
                                        <input onclick={_SeleccionarTarjeta} onkeyup={ __TotalDeTarjetaAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control totalTarjeta" id="totalTarjeta" name="totalTarjeta"  value="{factura.totalTarjeta}" >
                                    </div> 
                                    <div  class="form-group " >
                                        <label class="{labelTotales} ">{$.i18n.prop("factura.resumen.banco")} </label> 
                                        <input onclick={_SeleccionarBanco} onkeyup={ __TotalDeBancoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number"  onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control totalBanco"  id="totalBanco" name="totalBanco"  value="{factura.totalBanco}" >
                                    </div> 

                                </div>

                            </div>    
                            
                                
                            
                        </form>   
                    </div>                    
                    <div class="box-footer">
                        <button onclick={_AtrasComprasFinal} type="button" class="btn-dark-gray btn-back pull-left"  >{$.i18n.prop("btn.volver")}</button>
                        <button  onclick={__AplicarYCrearCompra}  class="btn-green btn-edit pull-right " > &nbsp Aplicar</button>
                    </div>
                </div><!--fin del cabecera-izquierda-->
                <section class="cabecera-derecha">
                    <!--right sidebar-->
                    <aside class="right-sidebar">
                        <!--Booking details-->
                        <article class="booking-details clearfix">
                            <h3><span id="lblSCS">Resumen de la Compra</span></h3>
                            <div class="booking-info">
                                <p style="text-align:right">Descuento : <span id="lblSubtotal"> {totalGeneralDescuento} </span></p>
                                <p style="text-align:right">Impuesto  : <span id="lblSubtotal"> {totalGeneralImpuesto} </span></p>
                            </div>
                            <div class="precioTotalFactura">
                                <p class="total" style="text-align:right;">Total  : <span id="lblTotal">{totalGeneralCompra}</span></p>
                            </div>
                            <div class="precioTotalFactura" >
                                    <p class="total " style="text-align:right;">{$.i18n.prop("factura.resumen.cambio")} <span id="lblTotal">{totalCambioPagarSTR}</span></p>    
                            </div>
                        </article>
                    </aside>     
                </section>               
            </div><!--fin del cabecera-derecha-->
        </div><!--fin del cabecera-derecha-->
    </div><!--fin del contenedor-compra-->

<div class="box box-solid box-primary" show={mostarParaCrearNuevaCompra}>
        <div class="box-body">
             <div class="box-header with-border">
                

            <div  class="contenedor-compra " >
                <div class="cabecera-izquierda" >
                <div class="row">
                  <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">  
                    <div class="box-tools ">
                            <a class="pull-left" href="#"   onclick = {__Limpiar} title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f10")}</span></a>
                            <a class="pull-left" href="#"   onclick = {__MostrarFormularioDePago}       title="Aplicar la compra"> <span class="label label-limpiar">{$.i18n.prop("comprar.f8")}</span></a>
                            <a class="pull-left" href="#"   onclick = {__crearCompraEnEspera}  title="Compra en espera"> <span class="label label-limpiar">{$.i18n.prop("comprar.f9")}</span></a>
                            
                            <a class="pull-right" href="#"  title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-articulos">{descripcionArticulo}</span></a>
                        </div>
                    </div>
                </div>  
                  <br>                
                    <div class="row">
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <div class="form-group ">
                                <label>{$.i18n.prop("compra.proveedor")}</label> 
                                <input onclick = {_EscogerProveedores}  type="text" class="campo nombreProveedor form-control"  value="{proveedorSimplificado.nombreCompleto}">
                            </div>
                        </div>
                    </div>            
                    <div class="row">
                        <div class="col-sx-2 col-sm-2 col-md-2 col-lg-2">
                            <button    onclick = {__ListaDecodigos} class="btn btn-primary boton-consultar1" id="btn-facturar" >
                                <i class="glyphicon glyphicon-plus"></i>Agregar Linea Detalle
                            </button>
                        </div>
                    </div>
                    
                </div>
                <section class="cabecera-derecha">
				    <!--right sidebar-->
                    <aside class="left-sidebar">
                            <!--Booking details-->
                        <article class=" clearfix">
                            <div onclick = {__MostrarFormularioDePago}  class="precioTotalFacturaContainer"  >
                                <div class="totalesContainer" >
                                    <div class="tituloTotales">SubTotal  :</div> 
                                    <div class="valorTotal"> <p>{totalGeneralSubTotal}</p></div>
                                </div>    
                                <div class="totalesContainer" >
                                  <div class="tituloTotales">Descuento:</div>
                                  <div class="valorTotal">{totalGeneralDescuento}</div>
                                </div>  
                                <div class="totalesContainer" >
                                    <div class="tituloTotales">Impuestos:</div>
                                    <div class="valorTotal">{totalGeneralImpuesto}</div>
                                </div>    
                                <div class="totalesContainer" >
                                   <div class="tituloTotales">Total   :</div> 
                                   <div class="valorTotal"><p> {totalGeneralCompra}</p></div>
                                </div>   
                            </div>
                        </article>
                    </aside>
                    <section   class="lista-compras-espera">
                        <div id="botones"  each={compras_espera.data}  onclick={__CargarCompraEspera}>
                            <a href="#" class="compras-espera"  title="{proveedor !=null?proveedor.nombreCompleto:""}">C# {id}</a>
                        </div>    
                    </section >

                </section>
            
        </div><!-- fin box-body-->
	</div><!-- fin box -->
                      
            </div><!-- fin contenedor-compra-->

                    <div class="encabezadoContainer" style="overflow-x: scroll;overflow-y: scroll; height:100%;">
<table class="table table-striped">
                        <thead>
                        <tr>
                            <th style="width:5%;">                                                      </div></th>
                            <th style="width:2%;"><div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.linea")}                         </div></th>
                            <th style="width:8%;"><div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.codigo")}                        </div></th>
                            <th style="width:18%;"><div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.descripcion")}</div></th>
                            <th style="width:17%;"><div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.cantidad")}   </div></th>
                            <th style="width:17%;"><div class="tituloFormat">Precio Unitario                        </div></th>
                            <th style="width:8%;"><div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.descuento")}
                            <th style="width:8%;"><div class="tituloFormat">Desc                     </div></th>
                            <th style="width:8%;"><div class="tituloFormat">%IVA                      </div></th>
                            <th style="width:8%;"><div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.impuesto")}                      </div></th>
                            <th  style="width:8%;"> <div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.subTotal")}                        </div></th>
                            <th  style="width:8%;"> <div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.total")}                        </div></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td style="width:5%;">
                                <button  onclick={__removeProductFromDetail} class="btn btn-danger btn-xs btn-block">X</button>
                            </td>
                            <td style="width:2%;"><div class="formatDetalle">{numeroLinea}</div></td>
                            <td style="width:8%;"><div class="formatDetalle">{codigo}</div></td>
                            <td style="width:16%;"><div class="formatDetalle">{descripcion}</div></td>
                            <td class="text-right" style="width:17%;">
                                <div class="formatDetalle">{cantidad}</div>
                            </td>
                            <td class="text-right" style="width:14%;">
                                <div class="formatDetalle">{precio}</div>
                            </td>
                            <td class="text-right" style="width:8%;">
                                <div class="formatDetalle">{descuento}</div>
                            </td>
                            <td class="text-right" style="width:14%;">
                                <div class="formatDetalle">{totalDescuento.toFixed(2)} </div>
                            </td>
                            <td class="text-right" style="width:8%;">
                                <div class="formatDetalle">{impuesto*100} </div>
                            </td>
                            <td class="text-right" style="width:14%;">
                                <div class="formatDetalle">{totalImpuesto.toFixed(2)} </div>
                            </td>
                           <td class="text-right" style="width:14%;">
                                <div class="formatDetalle">{(montoTotalLinea - totalImpuesto).toFixed(2) } </div>
                            </td>
 
                            <td class="text-right" style="width:14%;">
                                <div class="formatDetalle">{montoTotalLinea.toFixed(2)} </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>     
                    </div>     
    
<!--Modal mostrar Articulos de la empresa -->
<div id='modalAgregarLineaEnCompra' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> Agregar Linea de Detalle a la Compra Simplificada </h4>
            </div>
            <div class="modal-body">
                <form id="formularioLineaDetalle" name ="formularioLineaDetalle" >
                    <div class="row">
                       
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                            <label class="tamanoLetraSimplificadaDetalle" >{$.i18n.prop("articulo.codigo")}  </label>
                            <input type="text" class="form-control tamanonumeros" id="codigoArt" name="codigoArt"  onkeyup = {__CalculaMontoLinea} >
                        </div>
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                            <label class="tamanoLetraSimplificadaDetalle" >{$.i18n.prop("articulo.descripcion")}</label>
                            <input type="text" class="form-control tamanonumeros"   id="descArticulo" name="descArticulo" onkeyup = {__CalculaMontoLinea}>
                        </div>
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                            <label class="tamanoLetraSimplificadaDetalle">{$.i18n.prop("articulo.tipoCodigo")}</label>
                            <select  class="form-control tipoCodigo " id="tipoCodigo" name="tipoCodigo"  >
                                <option  each={tipoCodigos}  value="{codigo}" selected="{articulo.tipoCodigo ==codigo?true:false}"  >{descripcion}</option>
                            </select>
                        </div>

                    </div> 
                    <div class="row">
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                            <label class="tamanoLetraSimplificadaDetalle"  >Cantidad  </label>
                            <input type="number" step="any" class="form-control cantidad tamanonumeros" id="cantidad" name="cantidad" onkeyup = {__CalculaMontoLinea}>
                        </div>
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                            <label class="tamanoLetraSimplificadaDetalle"  >Precio Unitario </label>
                            <input type="number" step="any" class="form-control precioUnitario  tamanonumeros" id="precioUnitario" name="precioUnitario" onkeyup = {__CalculaMontoLinea} >
                        </div>

                        <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                            <label class="tamanoLetraSimplificadaDetalle">{$.i18n.prop("articulo.tipoImpuesto")} </label>
                            <select onchange= {__asignarImpuesto} class="form-control tipoImpuesto " id="tipoImpuesto" name="tipoImpuesto"  >
                                <option  each={impuestos}  value="{codigo}"  >{descripcion}</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                            <label class="tamanoLetraSimplificadaDetalle">{$.i18n.prop("articulo.codigoTarifa")}</label>
                            <select  onchange= {__AsignarTarifa} class="form-control selectCodigoTarifa " id="codigoTarifa" name="codigoTarifa"  >
                                <option  each={tarifas1.aaData}  value="{tarifaIVAI.codigoTarifa}" selected="{articulo.codigoTarifa ==tarifaIVAI.codigoTarifa && articulo.tipoImpuesto ==tipoImpuesto ?true:false}"  >{tarifaIVAI.descripcion}</option>
                            </select>
                        </div>        
                       
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                            <label class="tamanoLetraSimplificadaDetalle"  >% Descuento </label>
                            <input type="number" step="any" class="form-control porcentajeDescuento  tamanonumeros" id="porcentajeDescuento" name="porcentajeDescuento"  onkeyup = {__CalculaMontoLinea}>
                        </div>

                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                            <label class="tamanoLetraSimplificadaDetalle"  >%{$.i18n.prop("articulo.impuesto")}  </label>
                            <input type="number" step="any" class="form-control impuesto  tamanonumeros" id="impuesto" name="impuesto" onkeyup = {__CalculaMontoLinea}>
                        </div>


                    </div>
                    <div class="row">
                        <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                            <label class="tamanoLetraSimplificadaDetalle"  >Descuento </label>
                            <input type="number" step="any" class="form-control montoDescuento tamanonumeros " id="montoDescuento" name="montoDescuento"  readonly>
                        </div>

                        <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                            <label class="tamanoLetraSimplificadaDetalle"  >IVA  </label>
                            <input type="number" step="any" class="form-control montoImpuesto tamanonumeros " id="montoImpuesto" name="montoImpuesto" readonly>
                        </div>
                        <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                            <label class="tamanoLetraSimplificadaDetalle"  >Sub Total  </label>
                            <input type="number" step="any" class="form-control subTotal tamanonumeros " id="subTotal" name="subTotal" readonly>
                        </div>

                        <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                            <label class="tamanoLetraSimplificadaDetalle"  >Total Linea </label>
                            <input type="number" step="any" class="form-control montoTotalLinea tamanonumeros " id="montoTotalLinea" name="montoTotalLinea" readonly >
                        </div>

                    </div>
                </form>    
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button onclick ={__regresar}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button  onclick={__AplicarAgregarLineaDetalle}   class=" btn-green pull-right" >  Agregar Linea</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->

<style type="text/css">
    .formatDetalle{
        font-size: 25px;
        color: black;
        text-align: center;
    }
    .tamanonumeros{
       font-size: 34px;
       font-weight: 600;
       color: black;
    }
    .tamanoLetraSimplificadaDetalle {
        font-weight: 600 !important;
        font-size: 30px !important;
    }
    .detalle1{
        flex: 0.5;
    
        margin-left: 2%;
        margin-right: 2%;
        margin-bottom: 1%;
    }
    .detalleDescripcion{
     flex: 1.5;
        text-align: center;
        color: black;
        font-weight: 600;
        font-size: 14px;
    
    }
    .detalleDescripcion1{
      flex: 1.5;
        text-align: center;
        color: black;
        font-size: 14px;
    
    }
    .detalleCodigo{
        flex: 0.8;
        text-align: center;
    }
    .detalleLinea{
        flex: 0.1;
        text-align: center;
    }
    .detallesProductos{
        display:flex;

        justify-content: space-around;

    }
    .detalleEliminar{
        flex:0.3;
    }
    .containerUno{
        display: flex;
    justify-content: space-between;
    }
    .tituloDetalle1{
        color: black;
        font-size: 14px;
        font-weight: 600;
            flex: 1;
        
        text-align: center;
    }
    .totalesContainer{
        display: flex;
        flex: 1;
        justify-content: space-around;
    }
    .tituloTotales{
        text-align: left;
        margin-right: 3%;
        color: yellow;
        margin-top: 2%;
        flex: 0.5;
    }
    .valorTotal{
        margin-top: 2%;
    }
    .label-totalesComprobanteChino {
        font-weight: 600 !important;
        font-size: 18px !important;
        font-family: Roboto,sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        border-collapse: separate;
        cursor: pointer;
        margin: 1%!important;
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
    .precioTotalFacturaContainer{
        display:flex;
        flex:1;
        flex-direction: column;
        ont-weight: 600 !important;
        font-size: 14px !important;
        color:yellow !important;
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
    .tituloFormat{
        color: black;
        font-size: 14px;
        font-weight: bold;   
            padding-top: 5%; 
            text-align: center;
    }
    .contenedor-compra {
        display:flex;
        flex-wrap: nowrap;
    }
    .cabecera-derecha{
        flex:0.25;
    }
    .totalLabel {
        color: #333;
        font-size: 18px;
        font-weight: bold;
        margin-top: 18%;
        text-align: center;
    }
    .totalLabelImpuesto {
        color: #333;
        font-size: 18px;
        font-weight: bold;
        margin-top: 31%;
        text-align: center;
    }
    #contenedor {
        width:500px;
        height:200px;
        background: #fff;
        padding:10px;￼    color: blue;
        ￼    font-size: 18px;
        border:10px solid #2c3e50;
        margin:20px;
        display:flex;
        display:-webkit-flex;
        display:-ms-flexbox;
        justify-content:space-between;
    }
    .cabecera-izquierda {
        flex:1;
        margin-right: 1%;

        }

        .cabecera-derecha {
            width:25%;
        }

        .elemento{
        background: #E67E22;
        color:#fff;
        margin:5px;
        flex-basis:150px; 
        
        height:50px;

        
        }

        .item {
        
        width: 50%;
        }

    .boton-consultar1 {
       display: block;
        display: inline-block;
        margin-bottom: 0;
        
        border-radius: 3px;
        height: 30px;
        /* padding: 6px 12px; */
        font-size: 14px;
        line-height: 1.42857143;
        color: #fff;
        background-color: #3c8dbc;
        border-color: #367fa9;
        border: 1px solid #ccc;
        border-radius: 4px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .
    }
    /* Lista de facturas en espera*/
    .cabecera-derecha .lista-compras-espera{
        width:100%;
        display:flex;
        flex-wrap:wrap;
    }
    .box-body{
        padding: 0px !important;
    }
    .label-limpiar{
        font-weight: 600 !important;
        font-size: 16px !important;
        font-family: Roboto,sans-serif !important;
        color: #ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        line-height: 30px;
        border-collapse: separate;
        background-color: #f2f2f2;
        color: #000;
        text-align: center;
        cursor: pointer;
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
    .cabecera-derecha .lista-compras-espera .compras-espera{
        display:block;
        width:90%;
        margin-bottom:4px;
        margin-right:5px;
        background:red;
        text-align:center;
        text-decoration:none;
        color:#ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
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
        background-color: yellow !important;
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
        display: inline-block;
        width: 100%;
    }
    .total{
        font-weight:bold;
        font-size:23px;
    }
    .precioTotalFactura{
        font-weight:bold;
        font-size:23px;
        color:black;
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
    .campo {
        display: block;
        width: 100%;
        height: 30px;
        padding: 6px 16px;
        font-size: 12px;
        line-height: 1.42857143;
        color: #555;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        /* border-radius: 2px; */
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        border: 1px solid #ccc;
        margin: 2px 0;
        padding: 1px 2px;
        overflow: visible;
    }
    .campodetalle {
        width: 170px;
        height: 30px;
        /* padding: 6px 16px; */
        font-size: 14px;
        line-height: 1.42857143;
        color: #333;
        font-weight: bold;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        /* border-radius: 2px; */
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        /* box-shadow: inset 0 1px 1px rgba(0,0,0,.075); */
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        background-color: #fcfcfc;
        /* border: 1px solid #ccc; */
        margin: 2px 0;
        padding: 1px 2px;
        /* overflow: visible; */
    }
    .campodetalleDescuento {
        width: 100%;
        height: 30px;
        padding: 6px 16px;
        font-size: 14px;
        line-height: 1.42857143;
        color:#333;
        font-weight: bold;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        border-radius: 2px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        background-color: #fcfcfc;
        border: 1px solid #ccc;
        margin: 2px 0;
        padding: 1px 2px;
        overflow: visible;
    }

    /*1024x768*/
    @media only screen and (max-width: 1024px) and (min-width:768px)  {
        .campodetalle {
            width: 145px;
            height: 30px;
            /* padding: 6px 16px; */
            font-size: 14px;
            line-height: 1.42857143;
            color: #333;
            font-weight: bold;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            /* border-radius: 2px; */
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            /* box-shadow: inset 0 1px 1px rgba(0,0,0,.075); */
            -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
            background-color: #fcfcfc;
            /* border: 1px solid #ccc; */
            margin: 2px 0;
            padding: 1px 2px;
            /* overflow: visible; */
        }

        .detalle1{
            flex: 0.80 !important;
            margin-left: 2% !important;
            margin-right: 2% !important;
            text-align: center;
            margin-bottom: 1%;
        }
        .detalleEliminar{
            flex:0;
            margin-left: 0.5%;
        }
    .tituloDetalle1 {
        color: black;
        font-size: 14px;
        font-weight: 600;
        flex: 1.3;
        text-align: left;
    }
    .campodetalleDescuento {
        width: 188%;
        height: 30px;
        padding: 6px 16px;
        font-size: 14px;
        line-height: 1.42857143;
        color: #333;
        font-weight: bold;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        border-radius: 2px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        background-color: #fcfcfc;
        border: 1px solid #ccc;
        margin: 2px 0;
        padding: 1px 2px;
        overflow: visible;
    }


}
</style>

<script>
    var self = this;
    // Detalle de la factura es una coleccion de articulos
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.comboEstados          = []
    self.comboFormaPagos        = []
    self.comboTipoDocumentos   = []
    self.impuestos =[]
    self.tarifas1    = {aaData:[]}
    self.compra                = {
        consecutivo:"",
        fechaCredito    : null,
        fechaCompra     : null,
        id : null,
        totalImpuesto: 0,
        totalCompra:0,
        estado:0,
        tipoDocumento:0,
        formaPago:0,
        totalDescuento:0,
        subTotal:0,  
        total:0,
        nota:""
    }                            
    self.item                  = null;
    self.articulo              = null;
    self.tipoCodigos =[]
    self.articulos             = {data:[]}
    self.proveedores           = {data:[]}
    self.detalleCompra         ={data:[]}
    self.proveedor             = null;
    self.compras_espera         = {data:[]}  
    self.informacion_tabla             = []
    self.informacion_tabla_articulo    = []
    self.informacion_tabla_proveedores = []
    self.idiomaDataTable       = {}
    self.mostrarFormularioPago = false
    self.mostarParaCrearNuevaCompra = true
    self.mostrarCamposIngresoContado = true;
    self.totalGeneralDescuento = 0;
    self.totalGeneralImpuesto  = 0;
    self.totalGeneralCompra    = 0; 
    self.numeroLinea =0
    self.pesoPrioridad =  0
    self.detalle ={
         numeroLinea : 0,
         precioUnitario:0,
         cantidad:0,
         montoDescuento:0,
         montoImpuesto:0,
         porcentajeDescuento:0,
         impuesto:0,
         montoTotalLinea:0

     }
    self.on('mount',function(){
        $("#formularioLineaDetalle").validate(reglasDeValidacionDetalleCompra());
           __informacionData()
        __InicializarTabla('.tableListaProveedor')
        agregarInputsCombos_Articulo()
        __comboCondicionPago()
        __ComboTipoDocumentos()
        __Teclas()
        __Impuestos()
        __ListaDeProveedores()
        __tipoCodigo()
        __Eventos()
        $('.datepickerFechaCompra').datepicker(
        {
            format: 'yyyy-mm-dd',
            todayHighlight:true,
        }
    );
    var retrievedObject = JSON.parse(localStorage.getItem('detallesComprasNueva'));
    self.detail = retrievedObject == null?self.detail = []:retrievedObject
    var compraObject = JSON.parse(localStorage.getItem('compraNueva'));
    self.compra = compraObject ==null?self.compra:compraObject
    var proveedorObject = JSON.parse(localStorage.getItem('proveedor'));
    self.proveedor = proveedorObject == null? self.proveedor:proveedorObject
    self.update()
    __calculate()
    //__Init()
})


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
__ActualizarPlazoCredito(){
    actualizaElPlazoDiasCredito();
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
    self.comboTipoDocumentos.push({
        estado:"08",
        descripcion:$.i18n.prop("factura.tipo.documento.compras.simplificada")
    })
    self.update()
}

__CalculaMontoLinea(){
  aplicarMontos()
}

function aplicarMontos(){
 
    var porcentajeDescuento = parseFloat(__valorNumerico($(".porcentajeDescuento").val()));
    porcentajeDescuento = porcentajeDescuento / 100
    var cantidad = parseFloat(__valorNumerico($(".cantidad").val()));
    var impuesto = parseFloat(__valorNumerico($(".impuesto").val()));
    impuesto = impuesto > 0 ? impuesto / 100 : 0
    var precioUnitario = parseFloat(__valorNumerico($(".precioUnitario").val()));
    var subTotal = precioUnitario *  cantidad
    var montoDescuento = subTotal *  porcentajeDescuento
    subTotal = subTotal - montoDescuento
    var montoImpuesto = subTotal * impuesto;
    var montoTotalLinea = subTotal + montoImpuesto
    $("#subTotal").val(redondeoDecimales(subTotal,2))
    $("#montoDescuento").val(redondeoDecimales(montoDescuento,2))
    $("#montoImpuesto").val(redondeoDecimales(montoImpuesto,2))
    $("#montoTotalLinea").val(redondeoDecimales(montoTotalLinea,2))
    self.detalle.porcentajeDescuento = porcentajeDescuento
    self.detalle.descripcion = $("#descArticulo").val()
    self.detalle.codigo = $("#codigoArt").val()
    self.detalle.tipoCodigo = $(".tipoCodigo").val()
    self.detalle.montoDescuento = montoDescuento
    self.detalle.precioUnitario = precioUnitario
    self.detalle.impuesto = impuesto
    self.detalle.subTotal = subTotal
    self.detalle.cantidad = cantidad
    self.detalle.montoDescuento = montoDescuento
    self.detalle.montoImpuesto = montoImpuesto
    self.detalle.montoTotalLinea = montoTotalLinea
    self.update()
   


}

/**
*  Actimpuestor validaciones del formulario
**/
function __Eventos(){
    $("#formularioLineaDetalle").validate(reglasDeValidacionDetalleCompra());
    $("#descArticulo").attr("maxlength", 160);
    $("#codigoArt").attr("maxlength", 20);
    $('#impuesto').mask('00', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
}

__AplicarAgregarLineaDetalle(){
    $("#formularioLineaDetalle").validate(reglasDeValidacionDetalleCompra());
    if ($("#formularioLineaDetalle").valid()) {
         __nuevoArticuloAlDetalle()
    }
}

/**
*  Regresar al listado
**/
__regresar(){
    $('#modalAgregarLineaEnCompra').modal('hide')
}


__AsignarTarifa(){
    $(".impuesto").val(getMontoImpuesto($(".tipoImpuesto").val(),$('#codigoTarifa').val(),self.tarifas1.aaData))
    aplicarMontos()
}
function getMontoTarifa(tipoImpuesto,codigoTarifa,array) {
  return array.filter(
    function(data) {
      return data.tipoImpuesto == tipoImpuesto && data.tarifaIVAI.codigoTarifa == codigoTarifa?data.monto:0
    }
  );
}
function getMontoImpuesto(tipoImpuesto,codigoTarifa,array){
    if(tipoImpuesto.length ==0){
        return 0
    }
    if(tipoImpuesto ==null){
        return 0
    }
    var valor = getMontoTarifa(tipoImpuesto,codigoTarifa,array);
    valor = valor !=null?valor[0]:null
    return valor == null?0:valor.monto
}

/**
* Tipo codigo del producto/servicio del articulo
**/
function __tipoCodigo(){
    self.tipoCodigos =[]
    self.update()
    self.tipoCodigos.push({
        codigo: '01',
        descripcion:$.i18n.prop("articulo.tipo.codigo.vendedor")
     });

    self.tipoCodigos.push({
        codigo: '02',
        descripcion:$.i18n.prop("articulo.tipo.codigo.comprador")
     });
    self.tipoCodigos.push({
        codigo: '03',
        descripcion:$.i18n.prop("articulo.tipo.codigo.asignado.por.industria")
     });
    self.tipoCodigos.push({
        codigo: '04',
        descripcion:$.i18n.prop("articulo.tipo.codigo.uso.interno")
     });
    self.tipoCodigos.push({
        codigo: '99',
        descripcion:$.i18n.prop("articulo.tipo.codigo.otros")
     });
   
     self.update();
}

/**
* Asigna el impuesto 13 cuando es valor igual 01
**/
__asignarImpuesto(){
    $('.impuesto').val(null)
     __listadoTarifasByTipoImpuesto($('#tipoImpuesto').val(),1)
    self.tarifas1  = {aaData:[]}
    self.update()
    aplicarMontos()
}
/**
*  Mostrar listado datatable Categorias Actimpuestos
**/
function __listadoTarifasByTipoImpuesto(tipoImpuesto,indicador){
    if (typeof tipoImpuesto == 'undefined') {
        return
    }
    if (tipoImpuesto == "" ){
        return
    }
    if (tipoImpuesto == " ") {
        return
    }
    var selector = ""
    $.ajax({
         url: "ListarTarifasByTipoImpuestoAjax.do",
        datatype: "json",
         data: {tipoImpuesto:tipoImpuesto},
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.tarifas1 =  result
                self.update()
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}
/**
* Combo para verificar si es contabilizado en el inventario o no
**/
function __Impuestos(){
    self.impuestos =[]
    self.update()
     self.impuestos.push({
        codigo: "",
        descripcion:"Sin impuesto"
     });
     
    self.impuestos.push({
        codigo: '01',
        descripcion:$.i18n.prop("tipo.impuesto.ventas")
     });
      self.impuestos.push({
        codigo: '02',
        descripcion:$.i18n.prop("tipo.impuesto.consumo")
     });
    self.impuestos.push({
        codigo: '07',
        descripcion:$.i18n.prop("tipo.impuesto.servicio")
     });
     self.impuestos.push({
        codigo: '06',
        descripcion:$.i18n.prop("tipo.impuesto.tabaco")
     });
    self.impuestos.push({
        codigo: '12',
        descripcion:$.i18n.prop("tipo.impuesto.cemento")
     });
    self.impuestos.push({
        codigo: '99',
        descripcion:$.i18n.prop("tipo.impuesto.otros")
     });
   
     self.update();
}

var reglasDeValidacionDetalleCompra = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
            cantidad:{
                required : true,
                numeroMayorCero:true
            },
			codigoArt : {
				required : true,
         	},                                   
			descArticulo : {
				required : true,
                maxlength:160,
                minlength:5,
                lettersOnly : true
             },
             precioUnitario:{
                required:true,
                numeroMayorCero:true
             }         
		},
		ignore : []

	});
	return validationOptions;
};
/**
* Camps requeridos
**/
var reglasDeValidacionCompra = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			consecutivo : {
				required : true,
         	},                                   
			fechaCompra : {
				required : true,
             },
             nombreProveedor:{
                 required:true
             },
             nota:{
                 maxlength:255,
             }         
		},
		ignore : []

	});
	return validationOptions;
};
 /**
 * Listar codigos  llamado del modal para presentar los articulos
 **/   
 __ListaDecodigos(){
  $('#modalAgregarLineaEnCompra').modal('show')      
 }



/**
** Se aplica o se crea una compra cargada en la pantalla
**/
__AplicarYCrearCompra(){
   
    if ($("#formularioCompra").valid()) {
        swal({
           title: '',
           text: $.i18n.prop("compra.alert.crear"),
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
               crearCompra(2)  
              
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
    $('.fechaCompra').val(null);
    $('.fechaCredito').val(null)
     $('.datepickerFechaCompra').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-90d',
              todayHighlight:true,
            }
         );
        $('.datepickerFechaCredito').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-0d',
              todayHighlight:true,
            }
         );
    $('.nota').val(null)
    $('.consecutivo').val(null)
    self.numeroLinea =0
     self.pesoPrioridad =  0
    self.detail                = [];
     self.compra                = {
        consecutivo:"",
        fechaCredito    : null,
        fechaCompra     : null,
        id : null,
        totalImpuesto: 0,
        totalCompra:0,
        estado:0,
        tipoDocumento:0,
        formaPago:0,
        totalDescuento:0,
        subTotal:0,  
        total:0,
        nota:""
    }                          
    self.item                  = null;
    self.articulo              = null;
    self.articulos                     = {data:[]}
    self.proveedores                   = {data:[]}
    self.proveedor                     = null;
    self.mostrarFormularioPago = false
    self.mostarParaCrearNuevaCompra = true
    self.mostrarCamposIngresoContado = true;
    self.totalGeneralDescuento = 0;
    self.totalGeneralImpuesto  = 0;
    self.totalGeneralCompra    = 0; 
    self.totalSubTotalGeneral  = 0;

    self.update();
    
     
}

/** 
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('YYYY-MM-DD ');
}

/**
*  Cargar detalles Compra Espera
**/
function cargarDetallesCompraEnEspera(data){
    self.detail = [];
    self.numeroLinea =  0
    self.pesoPrioridad = 0
    self.compra = null
    self.update()
     $.each(data, function( index, modeloTabla ) {
        if(self.compra == null){
            self.compra = modeloTabla.compra
            self.compra.fechaCompra = __displayDate_detail(self.compra.fechaCompra)
            self.compra.fechaCredito = self.compra.fechaCredito !=null?__displayDate_detail(self.compra.fechaCredito):null
            self.proveedor = modeloTabla.compra.proveedor
            self.update()
        }
        self.detail.push({
            numeroLinea     : modeloTabla.numeroLinea,
            pesoPrioridad    :modeloTabla.numeroLinea,
            articulo_id     : modeloTabla.articulo.id,
            codigo          : modeloTabla.articulo.codigo,
            descripcion     : modeloTabla.articulo.descripcion,
            cantidad        : parseFloat(modeloTabla.cantidad),
            costo           : parseFloat(modeloTabla.costo),
            precio          : parseFloat(modeloTabla.precio),
            impuesto        : modeloTabla.impuesto,
            descuento       : modeloTabla.descuento,
            totalDescuento  : modeloTabla.totalDescuento,
            totalImpuesto   : modeloTabla.totalImpuesto,
            subTotal        : parseFloat(modeloTabla.subTotal),
            montoTotalLinea : modeloTabla.montoTotalLinea
        });
        self.numeroLinea = self.numeroLinea + 1
        self.cantArticulos = self.cantArticulos + 1
        self.pesoPrioridad = self.numeroLinea

    })
   self.detail.sort(function(a,b) {
    if ( a.pesoPrioridad > b.pesoPrioridad )
        return -1;
    if ( a.pesoPrioridad < b.pesoPrioridad )
        return 1;
    return 0;
    } );
    
    self.update()
     __calculate(); 
}


/**
*   Retrocer a los ingresos de los codigos desde el formulario de ingresar el valor dinero a pagar
**/
_AtrasComprasFinal(){
   self.mostrarFormularioPago = false
   self.mostarParaCrearNuevaCompra = true
   self.mensajesBackEnd = []
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
    mostrarFormaPago()
}

function mostrarFormaPago(){
     //No hay detalles registrados en la compra
    if(self.detail.length == 0 ){
        swal("Verificar","No hay detalles en la compra ", "info")
        return
    }
    self.mostarParaCrearNuevaCompra = false
    self.mostrarFormularioPago = true
    self.update()

}


/**
*  Muestra la lista de proveedores
**/
_EscogerProveedores(){
    $('#modalProveedores').modal('show')  
}
/**
*  Lista de los Proveedores
**/
function __ListaDeProveedores(){
    $.ajax({
        url: 'ListarProveedorSimplificadoActivosAjax.do',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                __informacionData()
                loadListar(".tableListaProveedor",idioma_espanol,self.informacion_tabla_proveedores,result.aaData)
                agregarInputsCombos_Proveedores()
                ActivarEventoFiltro(".tableListaProveedor")
                __seleccionarProveedores()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}



/**
*   agregar Articulos nuevos en el detalle de la Compra
**/
function __nuevoArticuloAlDetalle(){
    if(self.detalle.descripcion == null){
        return;
    }
    if(self.detalle.descripcion == ""){
        return;
    }
    var cont = 0
    self.detail.forEach(function(elemen){
        cont =  cont + 1
    })  
    self.numeroLinea = cont > 0?cont+1:1
    self.pesoPrioridad =  cont > 0? cont+1:1
    self.update()
    self.detail.push({
       numeroLinea     : self.numeroLinea,
       pesoPrioridad   :self.pesoPrioridad,  
       codigo          : self.detalle.codigo,
       descripcion     : self.detalle.descripcion,
       tipoCodigo      : self.detalle.tipoCodigo,
       cantidad        : self.detalle.cantidad,
       costo           :0,
       precio          : self.detalle.precioUnitario,
       totalImpuesto   : self.detalle.montoImpuesto,
       totalDescuento  : self.detalle.montoDescuento,
       impuesto        : self.detalle.impuesto,
       descuento       : self.detalle.porcentajeDescuento,
       montoTotalLinea : self.detalle.montoTotalLinea
    }); 
    self.detail.sort(function(a,b) {
    if ( a.pesoPrioridad > b.pesoPrioridad )
        return -1;
    if ( a.pesoPrioridad < b.pesoPrioridad )
        return 1;
    return 0;
    } );
    self.update()
    __calculate();
}

/**
* eliminar un detalle Compra
**/
__removeProductFromDetail(e) {
    var item = e.item;
    index = this.detail.indexOf(item);
    this.detail.splice(index, 1);
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
    }
     

    self.update()
     __calculate();
 }
   
 
/**
* calculacion de los detalle de la compra 
**/
function __calculate() {
    self.compra.totalCompra     = 0;
    self.compra.totalDescuento  = 0;
    self.compra.totalImpuesto   = 0;
    self.compra.subTotal        = 0;
    self.update()
    var totalCompra    = 0
    var montoTotalLinea= 0
    var totalDescuento = 0
    var totalImpuesto  = 0
    var subTotal = 0
    self.detail.forEach(function(e){
        totalCompra      += e.montoTotalLinea >0?e.montoTotalLinea:0
        totalDescuento   += e.totalDescuento >0?e.totalDescuento:0
        totalImpuesto    += e.totalImpuesto >0?e.totalImpuesto:0

    });
    self.compra.totalCompra    = totalCompra
    self.compra.totalDescuento = totalDescuento
    self.compra.totalImpuesto  = totalImpuesto
    self.compra.subTotal  = totalCompra - totalImpuesto
    self.compra.subTotal = self.compra.subTotal + totalDescuento
    self.totalGeneralSubTotal = formatoDecimales(self.compra.subTotal,2)
    self.totalGeneralDescuento = formatoDecimales(totalDescuento,2)
    self.totalGeneralImpuesto  = formatoDecimales(totalImpuesto,2)
    self.totalGeneralCompra    = formatoDecimales(totalCompra,2)
    self.articulo              = null;
    self.update(); 
    $( "#codigo" ).val(null);
    $('.codigo').select()
    $('.codigo').focus()
    localStorage.setItem('detallesComprasNueva', JSON.stringify(self.detail));
    localStorage.setItem('compraNueva', JSON.stringify(self.compra));
    localStorage.setItem('proveedor', JSON.stringify(self.proveedor));

}

/**
* formato de la tabla de proveedores
**/
function __informacionData(){

    self.informacion_tabla_proveedores = [	{'data' : 'nombreCompleto'  ,"name":"nombreCompleto" ,"title" : $.i18n.prop("proveedor.nombreCompleto") ,"autoWidth":false},
                                            {'data' : 'correoElectronico'           ,"name":"correoElectronico"          ,"title" : $.i18n.prop("proveedor.email")          ,"autoWidth":false},                                
                                            {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									            "render":function(id,type, row){
										            return __OpcionesProveedores(id,type,row);
	 							                }	 
								            },
                                        ];                              
   
}
/**
* Opciones del modal de Proveedores
*/
function __OpcionesProveedores(){
  var agregar  = '<a href="#"  title="Seleccionar Proveedor" class="btn btnAgregar btn-success form-control" title="Seleccione el Proveedor de la compra" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;

}
/**
* Agregar codigos a la compra desde modal de articulos
**/
function __seleccionarProveedores() {
     $('#tableListaProveedor').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListaProveedor').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	     }
	    self.proveedorSimplificado = data
        self.update();
        $('#modalProveedores').modal('hide') 

    });
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
function agregarInputsCombos_Proveedores(){
     // Agregar los input de busqueda 
    $('.tableListaProveedor tfoot th').each( function (e) {
        var title = $('.tableListaProveedor thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 3    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}     

/**
*  teclas de la pantalla
**/      
function __Teclas(){
    window.addEventListener( "keydown", function(evento){
    var tecla = evento.keyCode; 
    if(tecla ==119){
        self.mostrarFormularioPago = true
        mostrarFormaPago()     
    }   
    //Compra en espera
    if(tecla ==120){
      crearCompra(1)   
    }
    
    //Limpiar f2
    if(tecla ==113){
      __Init()
    }

  if(tecla ==27){
      $('.codigo').select()
      $('.codigo').focus()
    }
    }, false );
}                         
</script>
</compra-simplificado>