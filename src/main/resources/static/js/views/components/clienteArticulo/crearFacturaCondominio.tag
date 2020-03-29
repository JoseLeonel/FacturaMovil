<factura-condominio>
<!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-calculator"></i>&nbsp Facturas a Condominios  </h1>
        </div>
        <div class=" col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
    <br>
<!--Modal mostrar Proveedores de una sucursal -->
<div id="modalClientes" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> Clientes   </h4>
            </div>
            <div class="modal-body">
                <table id="tableListaCliente" class="table responsive display table-striped table-hover nowrap tableListaCliente " cellspacing="0" width="100%">
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
                            <input   type="hidden" class="cliente" id="cliente" name="cliente" value="{cliente.id}">
                            <div class="row">
                                <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                    <div class="row">
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group ">
                                                <label>{$.i18n.prop("factura.condicion.pago")} </label> 
                                                <select  onchange= {__formaPago} class="form-control condicionVenta campo" id="condicionVenta" name="condicionVenta">
                                                    <option each={comboCondicionPagos} value="{estado}" selected="{factura.condicionVenta ==estado?true:false}" >{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>    
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group ">
                                                <label for="pago_tipoVentaL">{$.i18n.prop("factura.tipo.documento")} </label> 
                                                <select class="form-control tipoDoc campo" id="tipoDoc" name="tipoDoc"   >
                                                    <option each={comboTipoDocumentos} value="{estado}" selected="{factura.tipoDoc ==estado?true:false}" >{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                     <div class="row">
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group ">
                                                <label >{$.i18n.prop("factura.tipo.moneda")}</label> 
                                                <select class="form-control has-success codigoMoneda campo" id="codigoMoneda" name="codigoMoneda" >
                                                    <option each={monedas}  value="{estado}" selected="{factura.codigoMoneda ==estado?true:false}" >{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group ">
                                                <label>{$.i18n.prop("factura.nombreFactura")}</label> 
                                                <input type="text" id="nombreFactura" name="nombreFactura" class="campo nombreFactura "  value="{factura.nombreFactura}" > 
                                            </div>
                                        </div>
                                    </div>        
         
                                    <div class="form-group ">
                                        <label >{$.i18n.prop("factura.nota")}</label> 
                                        <input type="text" class="form-control nota campo" id="nota" name="nota" value="{factura.nota}">
                                    </div>
                                    <div show = "{mostrarCamposIngresoContado ==false }" class="form-group ">
                                        <label >{$.i18n.prop("factura.fecha.credito")}</label> 
                                        <div  class="form-group input-group date datepickerFechaCredito" data-provide="datepicker"  data-date-start-date="0d" data-date-format="yyyy-mm-dd">
                                            <input  onkeyup={__ActualizarPlazoCredito} onBlur={__ActualizarPlazoCredito} onclick={__ActualizarPlazoCredito} type="text" class="form-control fechaCredito selectFechaCredito" name="fechaCredito" id="fechaCredito" value="{factura.fechaCredito}" >
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>    
                                    <div class="form-group " show = "{mostrarCamposIngresoContado == false}">
                                        <label>{$.i18n.prop("factura.plazoCredito")}</label> 
                                        <input type="number" id = "plazoCreditoL"  name "plazoCreditoL" class="form-control plazoCreditoL campo" value="{factura.plazoCredito}" >
                                    </div>

                                </div>
                                <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                    <div class="form-group ">
                                        <label class="{labelTotales}">{$.i18n.prop("factura.resumen.efectivo")} </label> 
                                        <input onclick={_SeleccionarEfectivo}   onkeyup={ __TotalDeEfectivoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control totalEfectivo campo" id="totalEfectivo" name="totalEfectivo" value="{factura.totalEfectivo}" >
                                    </div>
                                    <div  class="form-group ">
                                        <label class="{labelTotales}">{$.i18n.prop("factura.resumen.tarjeta")} </label> 
                                        <input  onkeyup={ __TotalDeTarjetaAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control totalTarjeta campo" id="totalTarjeta" name="totalTarjeta"  value="{factura.totalTarjeta}" >
                                    </div> 
                                    <div  class="form-group " >
                                        <label class="{labelTotales} ">{$.i18n.prop("factura.resumen.banco")} </label> 
                                        <input  onkeyup={ __TotalDeBancoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number"  onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control totalBanco campo"  id="totalBanco" name="totalBanco"  value="{factura.totalBanco}" >
                                    </div> 

                                </div>

                            </div>    
                            <input type="hidden" id='codigoActividad'         name='codigoActividad'        value="{factura.codigoActividad}" >
                            <input type="hidden" id='referenciaTipoDoc'       name='referenciaTipoDoc'      value="{factura.referenciaTipoDoc}" >
                            <input type="hidden" id='referenciaCodigo'        name='referenciaCodigo'       value="{factura.referenciaCodigo}" >
                            <input type="hidden" id='referenciaRazon'         name='referenciaRazon'        value="Compra a proveedores Simplificados" >
                            
                            <input type="hidden" id='pesoTransporteTotal'     name='pesoTransporteTotal'    value="{totalPesoByFactura}" >
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
                            <input type="hidden" id='tipoCambioMoneda'        name='tipoCambioMoneda'        value="{tipoCambio.total}" >
                            
                            
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
                            <h1><span id="lblSCS">Resumen </span></h1>
                            <div id="totalesCierreContainer" >
                                <div >{$.i18n.prop("factura.resumen.subTotal")}  <span > {subTotalGeneral}   </span> </div> 
                                <div >{$.i18n.prop("factura.resumen.total")}     <span >{totalComprobante}         </span> </div> 
                                <div >{$.i18n.prop("factura.resumen.cambio")}    <span >{totalCambioPagarSTR}</span> </div> 
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
                            <a class="pull-left" href="#"   onclick = {_ListaFacturasDia} title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f5")}</span></a>
                            <a class="pull-left" href="#"   onclick = {__Limpiar} title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f10")}</span></a>
                            <a class="pull-left" href="#"   onclick = {__MostrarFormularioDePago} title="Aplicar la compra"> <span class="label label-limpiar">F8=Facturar</span></a>
                            <a class="pull-left" href="#"   onclick = {_ReimprimirFactura}       title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f6")}</span></a>

                            
                            
                            <a class="pull-right" href="#"  title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-articulos">{descripcionArticulo}</span></a>

                        </div>
                    </div>
                </div>  
                  <br>                
                    <div class="row">
                        <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <div class="form-group ">
                                <label>Clientes</label> 
                                <input onclick = {_EscogerClientes}  type="text" class="campo campodetalle form-control"  value="{cliente.nombreCompleto}">
                            </div>
                        </div>
                        <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <div class="form-group ">
                                <label class="titleListaPrecio">Actividades Economicas </label>  
                                <select onchange= {__AsignarActividad} class="form-control selectActividadComercial campodetalle"  name="selectActividadComercial" id="selectActividadComercial" >
                                    <option  each={empresaActividadComercial}  value="{codigo}"   >{codigo}-{descripcion}</option>
                                </select>                    
                            </div>
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
                                   <div class="tituloTotales">Total   :</div> 
                                   <div class="tituloTotales"><p> {totalComprobante}</p></div>
                                </div>   
                            </div>
                        </article>
                    </aside>
                    <div id="tituloMostrarTipoCambio">
                        <span >Tipo Cambio del Banco Central</span>
                    </div>    
                    <div id="mostrarTipoCambio">
                        <div>
                           <span>Compra USD $</span>
                           <span>{tipoCambio.totalCompra}</span>
                        </div>   
                        <div>
                           <span>Venta USD $</span>
                           <span>{tipoCambio.total}</span>
                        </div>   
                    </div>
                </section>
            
        </div>
  

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
                            <th  style="width:8%;"> <div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.total")}                        </div></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td style="width:5%;">
                                <button  onclick={__removeProductFromDetail} class="btn btn-danger btn-xs btn-block campoEliminar">X</button>
                            </td>
                            <td style="width:2%;"><div class="formatDetalle">{numeroLinea}</div></td>
                            <td style="width:8%;"><div class="formatDetalle">{codigo}</div></td>
                            <td class="text-right" style="width:14%;">
                                <input  onkeyup={__actualizarDescripcion} onBlur={__actualizarDescripcion} class="campodetalle" type="text" step="any"  value = "{descripcion}" />
                            </td>

                            <td class="text-right" style="width:17%;">
                                <input onkeyup={__recalculacionDelDetalle} onBlur={__recalculacionDelDetalle} id= "cantidadDetalle" class="campodetalle" type="number" step="any" placeholder="Cantidad Detalle" value = {cantidad} min="1" pattern="^[0-9]+"/>
                            </td>
                            <td class="text-right" style="width:14%;">
                                <input  onkeyup={__actualizarPrecio} onBlur={__actualizarPrecio} class="campodetalle" type="number" step="any"  value = "{precioUnitario}" min="0" pattern="^[0-9]+"/>
                            </td>
 
                            <td class="text-right" style="width:14%;">
                                <div class="formatDetalle">{montoTotalLinea.toFixed(2)} </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>     
                    </div>     
    
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

<style type="text/css">
    #tituloMostrarTipoCambio{
        font-size: 16px;
        font-weight: 700;
        text-align: center;
    }
    #mostrarTipoCambio{
        display: flex;
        justify-content: space-around;
    }

    .campoEliminar{
        font-size: 30px;
        height: 50px;
        border-radius: 16px !important;
        font-weight: 900;
    }
    .campodetalle{
       font-size: 18px !important;
        color: #808084 !important;
        border-radius: 16px !important;
        width: 100% !important;
        height: 50px!important;
        text-align: center;
    }
    
    .TotalesContainer{
        display:flex;
        flex-direction: column;
        flex: 1;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
        justify-content: space-around;
    }
    #totalesCierreContainer {
        display:flex;
        flex-direction: column;
        flex: 1;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
        justify-content: space-around;
    }
    #totalesCierreContainer > div {
      color: #30ed17;
      font-size: 22px;
      font-weight: 800;
      margin-left: 5px;
    }
    .formatDetalle{
        font-size: 25px;
        color: black;
        text-align: center;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        border-collapse: separate;
    }
  
 
    .tituloTotales{
        text-align: left;
        margin-right: 3%;
        color: #30ed17;
        margin-top: 2%;
        flex: 0.5;
        height: 50px;
        font-size: 25px;
        margin-left: 5px;
    }
  
    .precioTotalFacturaContainer{
        display:flex;
        flex:1;
        flex-direction: column;
        font-weight: 600 !important;
        font-size: 14px !important;
        color:#30ed17  !important;
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
   
    .cabecera-izquierda {
        flex:1;
        margin-right: 1%;

    }
    .cabecera-derecha {
            width:25%;
    }
  
    .item {
       width: 50%;
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
  
    .booking-details h1 {
        font-size: 1.5em;
        color: #666;
        text-shadow: none;
    }
    .booking-details  {
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
   

    /*1024x768*/
    @media only screen and (max-width: 1024px) and (min-width:768px)  {
        .campodetalle {
           font-size: 18px !important;
           color: #808084 !important;
           border-radius: 16px !important;
           width: 100% !important;
           height: 50px!important;
            text-align: center;
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
    self.totalComprobante  = 0
    self.comboTipoDocumentos   = []
    self.tarifas1    = {aaData:[]}
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
        referenciaTipoDoc:'14',
        referenciaCodigo:'04',
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

    }                            
    self.item                  = null;
    self.tipoCambio = {
        total:0,
        totalCompra:0,
        id:null
    }
    self.tipoCodigos =[]
    self.proveedores           = {data:[]}
    self.detalleCompra         ={data:[]}
    self.proveedor             = null;
    self.informacion_tabla             = []
    self.informacion_tabla_clientes = []
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
         codigoTarifa:'',
         precioUnitario:0,
         cantidad:0,
         montoDescuento:0,
         porcentajeDescuento:0,
         subTotal:0,
         montoTotalLinea:0

     }
     self.actividadComercial = {
        codigo:"",
        descripcion:""
    }
    self.detalleFactura        = {data:[]}   
    self.on('mount',function(){
        __ListaActividadesComercales()
        __comboMonedas()
        __ObtengoTipoCambio()
         self.tipoCambio.total = __getTipoCambioTotal()
         self.tipoCambio.totalCompra = __getTipoCambioCompra()
         self.update()
             __informacionData()
        __InicializarTabla('.tableListaCliente')
        __comboCondicionPago()
        __ComboTipoDocumentos()
        __Teclas()
        __ListaDeClientes()
        __tipoCodigo()
        $('.selectFechaEmision').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-30d',
              todayHighlight:true,
            }
         );
   
})
/**
*  Obtiene el valor de lo digitado en el campo de efectivo
**/
__TotalDeEfectivoAPagar(e){
    self.totalCambioPagarSTR = 0
    self.factura.totalEfectivo = __valorNumerico(e.target.value) 
    self.update()
    _calculoEnterPago()
}
/**
*  Obtiene el valor de lo digitado en el campo de Tarjeta
**/
__TotalDeTarjetaAPagar(e){
    self.factura.totalTarjeta = __valorNumerico(e.target.value) 
    self.update()
    _calculoEnterPago()
}
/**
*  Obtiene el valor de lo digitado en el campo de Banco
**/
__TotalDeBancoAPagar(e){
    self.factura.totalBanco = __valorNumerico(e.target.value) 
    self.update()
    _calculoEnterPago()
}
/**
* cargar los codigos de monedas
**/
function __comboMonedas(){
    self.monedas = []
    self.update()
    self.monedas.push({
        estado:"CRC",
        descripcion:$.i18n.prop("factura.moneda.cr")
    })
    self.monedas.push({
        estado:"USD",
        descripcion:$.i18n.prop("factura.moneda.dollar")
    })    
    self.update()
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
    var parametros = {
        factura:data,
        facturaDia:tipoImpresion
    }
    riot.mount('ptv-imprimir',{parametros:parametros});
    return 
}

/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Facturas_Dias(){
     // Agregar los input de busqueda 
    $('.tableListarFacturasDia tfoot th').each( function (e) {
        var title = $('.tableListarFacturasDia thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 0    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
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
/**
* formato del listado de facturas del dia
**/
function __InformacionDataTableDia(){
    self.formato_tabla_dias = [ 
                               {'data' : 'id' ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones_facturaDia(id,type,row);
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
/**
*Opciones del listado de las facturas por dia
**/
function __Opciones_facturaDia(){
  var agregar  = '<a href="#"  class="btn btnReimprimir btn-primary form-control" title="Imprimir" role="button"> <i class="glyphicon glyphicon glyphicon-print"></i></a>';
  return  agregar;
}

/**
*  Lista de los clientes
**/
function __ListaActividadesComercales(){
    $.ajax({
        url: 'ListaEmpresaActividadComercialPorPricipalAjax.do',
        datatype: "json",
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
           self.actividadComercial.descripcion = modeloTabla.codigo +"-" + modeloTabla.descripcion
            self.actividadComercial.codigo =  codigo
            self.factura.codigoActividad = codigo
            self.update()
        }

    })
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
        sumaMontosEntregadosParaCambios = __valorNumerico($('#totalBanco').val()) + sumaMontosEntregadosParaCambios
        sumaMontosEntregadosParaCambios = __valorNumerico($('.totalEfectivo').val()) + sumaMontosEntregadosParaCambios   
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
    swal({
           title: '',
           text: $.i18n.prop("compraSimplificada.mensaje.alert.enter"),
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
        estado:"01",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
    })
    self.update()
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
** Se aplica o se crea una Factura
**/
__AplicarYCrearCompra(){
   
    if ($("#formularioCompra").valid()) {
        swal({
           title: '',
           text: $.i18n.prop("compraSimplificada.alert.crear"),
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
               aplicarFactura(2)  
              
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

function aplicarFactura(estado){
    if($("#tipoDoc").val() ==null){
        mensajeAdvertencia($.i18n.prop("Se presento inconveniente ,vuelva a presiona F8 para Facturar"))
        return

    }
    if(self.detail.length == 0 ){
        mensajeAdvertencia($.i18n.prop("compraSimplificada.alert.sin.detalles"))
        return
    }
    if($('#condicionVenta').val() == "02"  ){
        if($('#fechaCredito').val() == null || $('#fechaCredito').val() == 0){
           mensajeAdvertencia($.i18n.prop("compraSimplificada.alert.fechaCredito"))
            return
        }
        if($('#plazoCreditoL').val() < 0 || $('#plazoCreditoL').val() == null || $('#plazoCreditoL').val() == 0){
           mensajeAdvertencia($.i18n.prop("compraSimplificada.alert.plazoCredito"))
            return
        }
    }else{
        // Si no es credito y el estado no es pendiente se debe verificar si ingresaron el monto a pagar
        if($("#tipoDoc").val() !="88"){
            if(estado == 2){
                if(__valorNumerico($('#totalTarjeta').val()) == 0 && __valorNumerico($('#totalBanco').val()) == 0 && __valorNumerico($('#totalEfectivo').val()) == 0){
                    mensajeAdvertencia($.i18n.prop("error.compraSimplificada.monto.ingresado"))
                    return
                }
                var montoEntregado = __valorNumerico($('#totalTarjeta').val())  + __valorNumerico($('#totalBanco').val()) + __valorNumerico($('#totalEfectivo').val())
                montoEntregado = redondeoDecimales(__valorNumerico(montoEntregado),2)
                if(montoEntregado > 20000000){
                    mensajeAdvertencia("Monto entregado es muy alto")
                    return
                }
                var resultado  = redondeoDecimales( __valorNumerico(self.factura.totalComprobante),2)
                if(__valorNumerico(resultado) > __valorNumerico(montoEntregado)  ){
                    mensajeAdvertencia($.i18n.prop("error.compraSimplificada.monto.ingresado.es.menor.ala.venta"))
                    return
                }
                //Si el cliente esta pagando con tajeta, banco debe ser igual a la venta
                var tarjeta = __valorNumerico(self.factura.totalTarjeta)
                var banco = __valorNumerico(self.factura.totalBanco)
                if(tarjeta != 0 || banco !=0){
                    if(resultado != montoEntregado  ){
                        mensajeAdvertencia($.i18n.prop("error.compraSimplificada.monto.tarjeta.banco.igual.venta"))
                    return
                        
                    }
                }

            }
       }
            
    } 
    crearFactura(estado)  
}
/**
*  Crear Factura nueva
**/
function crearFactura(estado){
    if (self.transaccion == true ){
        return false
    }
    __ObtengoTipoCambio()
    self.transaccion = true 
    self.update()
    
    self.detalleFactura.data =self.detail
    self.update() 
    var fechaCreditoTemporal =condicionVenta.value == "02"?fechaCredito.value:new Date() 
    var fechaReferencia =$('#referenciaFechaEmision').val() !=null?referenciaFechaEmision.value:new Date() 
    var JSONDetalles = JSON.stringify( self.detalleFactura );
    self.factura.id = self.factura.id
    self.factura.condicionVenta = $('#condicionVenta').val()
    self.factura.codigoMoneda = $('#codigoMoneda').val()
    self.factura.fechaCredito =fechaCreditoTemporal.toString()
    self.factura.referenciaFechaEmision =fechaReferencia
    self.factura.totalEfectivo =__valorNumerico($('#totalEfectivo').val())
    self.factura.totalTarjeta = __valorNumerico($('#totalTarjeta').val()) 
    self.factura.totalBanco = __valorNumerico($('#totalBanco').val())
    self.factura.plazoCredito = __valorNumerico($('#plazoCreditoL').val())
    self.factura.detalleFactura =JSONDetalles
    self.factura.estado = estado
    //self.factura.codigoMoneda = "CRC"
//    self.factura.codigoMoneda = self.parametros.codigoMoneda
    self.factura.tipoCambio = self.tipoCambio.total ==null?__getTipoCambioCompra():self.tipoCambio.total
    if(self.factura.tipoCambio ==null){
        self.factura.tipoCambio =575
        
    }
    self.update();
    var dataTemporal = null

    var formulario = $("#formularioCompra").serialize();
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
                     mensajeAlertErrorOConfirmacion('error',data.message);    	
                }
                self.transaccion = false
                self.update()
            } else {
               	self.cantidadEnterFacturar =0
                self.update()
                serverMessageJsonClase(data);
                dataTemporal = data
                evaluarFactura(data)
                self.transaccion = false
                self.update()
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
        referenciaTipoDoc:'14',
        referenciaCodigo:'04',
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
        __Init()
        //Envia a la pantalla de impresion
        self.facturaReimprimir = self.facturaImprimir
        localStorage.setItem('facturaReimprimir', JSON.stringify(self.facturaReimprimir));
        self.update()
        var mensaje = self.facturaImprimir.numeroConsecutivo !=null ?"Cons# :"+   self.facturaImprimir.numeroConsecutivo:"Tiquete# :"+   self.facturaImprimir.id        
        swal({
            type: 'success',
            title: mensaje,
            showConfirmButton: false,
            timer: 1500
        })
    }
}
/**
*  Inicializar las variables de trabajos
**/
function __Init(){
    __comboMonedas()
    __ListaActividadesComercales()

    $('.fechaCompra').val(null);
    $('.fechaCredito').val(null)
     $('.selectFechaEmision').datepicker(
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
   
    self.item                  = null;
    self.proveedores                   = {data:[]}
    self.proveedor                     = null;
    self.mostrarFormularioPago = false
    self.mostarParaCrearNuevaCompra = true
    self.mostrarCamposIngresoContado = true;
    self.totalGeneralDescuento = 0;
    self.totalGeneralImpuesto  = 0;
    self.totalGeneralCompra    = 0; 
    self.totalSubTotalGeneral  = 0;
    self.detalleFactura        = {data:[]}
    self.update();
    localStorage.setItem('cliente', JSON.stringify(self.cliente));
}

/** 
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('YYYY-MM-DD ');
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
_EscogerClientes(){
    $('#modalClientes').modal('show')  
}
/**
*  Lista de los Proveedores
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
                agregarInputsCombos_Proveedores()
                ActivarEventoFiltro(".tableListaCliente")
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
**/ 
function __calculate() {
    self.factura.total            = 0;
    self.factura.totalDescuentos  = 0;
    self.factura.totalImpuesto    = 0;
    self.factura.subTotal         = 0;
    self.factura.totalComprobante = 0
    self.update()
    var totalVenta     = 0
    var subTotal       = 0
    var totalDescuento = 0
    var totalComprobante        = 0
    self.detail.forEach(function(e){
        totalComprobante += e.montoTotalLinea
        subTotal         += e.subTotal >0?e.subTotal:0
        totalDescuento   += e.montoDescuento >0?e.montoDescuento:0
    });
    self.factura.totalDescuentos   = __valorNumerico(totalDescuento)
    self.factura.subTotal          = __valorNumerico(subTotal)
    self.factura.totalComprobante  = __valorNumerico(totalComprobante)
    self.totalComprobante          = formatoDecimales(self.factura.totalComprobante,2);
    self.totalDescuentos           = formatoDecimales(self.factura.totalDescuentos,2);
    self.update(); 
    getSubTotalGeneral()
    $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    $('#totalEfectivo').focus()
    $('#totalEfectivo').select()
    $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
    localStorage.setItem('cliente', JSON.stringify(self.factura.cliente));
}
/**
*  Sub Total Generar
**/
function getSubTotalGeneral(){
    var resultado = __valorNumerico(self.factura.subTotal) + __valorNumerico(self.factura.totalDescuentos)
    self.subTotalGeneral = formatoDecimales(resultado,2)
    self.totalDescuentos = formatoDecimales(self.factura.totalDescuentos,2)
    self.update()
}
/**
* formato de la tabla de proveedores
**/
function __informacionData(){
    self.informacion_tabla_clientes = [	{'data' : 'nombreCompleto'  ,"name":"nombreCompleto" ,"title" : $.i18n.prop("cliente.nombreCompleto") ,"autoWidth":false},
                                            {'data' : 'correoElectronico'           ,"name":"correoElectronico"          ,"title" : $.i18n.prop("cliente.email")          ,"autoWidth":false},                                
                                            {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									            "render":function(id,type, row){
										            return __Opciones(id,type,row);
	 							                }	 
								            },
                                        ];                              
   
}

function __Opciones(){
  var agregar  = '<a href="#"  title="Seleccionar Cliente" class="btn btnAgregar btn-success form-control" title="Seleccione el cliente" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;
}
/**
* Agregar codigos a la compra desde modal de articulos
**/
function __seleccionarProveedores() {
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
        agregarDetallesPorCliente()
        $('#modalClientes').modal('hide') 

    });
}

function agregarDetallesPorCliente(){
    __Init()
    $('.nota').val(null);
    $('.correoAlternativo').val(null);
    $('.nombreFactura').val(null);
    self.detail = [];
    self.numeroLinea =  0
    self.cantArticulos =  0
    self.pesoPrioridad = 0
    self.update()
    $.ajax({
        url: "ListarDetlleByClienteArticuloAjax.do", 
        datatype: "json",
        data: {idCliente:self.cliente.id},
        method:"POST",
        success: function (data) {
            if(data.aaData.length > 0){
                $.each(data.aaData, function( index, modeloTabla ) {
                   cargarDetallesEnEspera(modeloTabla)   
                })
                __calculate()
               
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}

function cargarDetallesEnEspera(data){
    
    var itemNuevo = setItemNuevo(data)
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

function setItemNuevo(data){
    //Determinar el precio a incluir
    var resultadoPrecio = (data.precio)
    var resultaMontoImpuesto = __valorNumerico(data.articulo.impuesto)
    var precioUnitario  = getPrecioUnitario(resultadoPrecio,resultaMontoImpuesto)
    var montoTotal      = getMontoTotal(precioUnitario,1)
    var montoDescuento  = 0
    var naturalezaDescuento = ""
    var subTotal        = montoTotal
    var montoImpuesto1  = 0
    var montoImpuesto   = _calcularImpuesto(subTotal+montoImpuesto1,__valorNumerico(data.articulo.impuesto) ==null?0:__valorNumerico(data.articulo.impuesto))
    var montoTotalLinea = subTotal + montoImpuesto + montoImpuesto1  
    self.pesoPrioridad  =  self.pesoPrioridad + 1
    self.numeroLinea    = self.numeroLinea + 1
    self.cantArticulos  = self.cantArticulos + 1
    var costoTotal      = __valorNumerico(data.costo) > precioUnitario ?0:__valorNumerico(data.costo); 
    var ganancia        = __ObtenerGananciaProductoNuevoIngresado(0,precioUnitario,data.costo ==null?0:__valorNumerico(data.costo),1)
   
   var item = {
       numeroLinea     : __valorNumerico(self.numeroLinea),
       pesoPrioridad   : self.pesoPrioridad,  
       tipoImpuesto    : data.articulo.tipoImpuesto,
       tipoImpuesto1   : "",
       iva             : __valorNumerico(data.articulo.impuesto),
       iva1            : 0,
       codigo          : data.codigo,
       descripcion     : data.descripcion,
       cantidad        : __valorNumerico(1),
       precioUnitario  : __valorNumerico(precioUnitario),
       impuesto        : __valorNumerico(data.articulo.impuesto),
       impuesto1        : 0,
       montoImpuesto   : __valorNumerico(montoImpuesto),
       montoImpuesto1  : 0,
       impuestoNeto    : __valorNumerico(montoImpuesto)  ,
       montoDescuento  : 0,
       porcentajeDesc  : 0,
       ganancia        : __valorNumerico(ganancia),
       montoGanancia   : __valorNumerico(ganancia),
       subTotal        : __valorNumerico(subTotal),
       montoTotalLinea : __valorNumerico(montoTotalLinea),
       montoTotal      : __valorNumerico(montoTotal),
       costo           : costoTotal,
       porcentajeGanancia :  data.articulo.gananciaPrecioPublico ==null?0:__valorNumerico(data.articulo.gananciaPrecioPublico),
       pesoTransporte :  0,
       pesoTransporteTotal :0,
       montoExoneracion:0,
       montoExoneracion1:0,
       porcentajeExoneracion:0,
       fechaEmisionExoneracion:null,
       nombreInstitucionExoneracion:"",
       numeroDocumentoExoneracion:"",
       tipoDocumentoExoneracion:""
    }
   // __SetUltimoItemIngresado(item);
    return item;
}

__actualizarDescripcion(e){
    self.item = e.item; 
    self.item.descripcion = e.currentTarget.value
    self.update()
    aplicarCambios() 
}

/**
 * Cuando se aplica un cambio de cantidad en un detalle
 * Se aplica una recalculacion de todo el detalle y Factura
 **/
 __actualizarPrecio(e){
     self.item = e.item; 
     self.item.precioUnitario = __valorNumerico(e.currentTarget.value)
    self.update()
    aplicarCambios()

 } 
 __recalculacionDelDetalle(e){
     self.item = e.item; 
     self.item.cantidad = __valorNumerico(e.currentTarget.value);
    self.update()
    aplicarCambios()
  }

  function aplicarCambios(){
    ActualizarLineaDEtalle()
    aplicarCambioLineaDetalle() 

  }

function agregarCantidadAlaVenta(cantidad){
    self.item.cantidad = cantidad
    self.update()
    ActualizarLineaDEtalle()
    aplicarCambioLineaDetalle() 
 
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
* Actualiza la linea del detalle de la factura
**/
function ActualizarLineaDEtalle(){
    var montoTotal             = getMontoTotal(self.item.precioUnitario,self.item.cantidad)
    var montoDescuento         = 0
    var subTotal               = montoTotal > montoDescuento?montoTotal - montoDescuento: montoDescuento-montoTotal
    montoImpuesto1             = _calcularImpuesto(subTotal,self.item.impuesto1 ==null?0:self.item.impuesto1)
    var resultadoMontoImpuesto1 = montoImpuesto1 + subTotal;
    var montoImpuesto          = _calcularImpuesto(resultadoMontoImpuesto1,self.item.impuesto ==null?0:self.item.impuesto)
    var montoTotalLinea        = subTotal + montoImpuesto + montoImpuesto1   
    self.item.pesoTransporteTotal = __valorNumerico(self.item.cantidad) *  __valorNumerico(self.item.pesoTransporte)
   
    self.item.montoTotal       = montoTotal
    self.item.montoDescuento   = montoDescuento
    self.item.subTotal         = subTotal
    self.item.montoImpuesto    = montoImpuesto
    self.item.montoImpuesto1   = montoImpuesto1
    
    self.item.montoTotalLinea  = montoTotalLinea
    self.item.ganancia         = 0
    self.item.montoGanancia    = 0 
    self.update()
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Proveedores(){
     // Agregar los input de busqueda 
    $('.tableListaCliente tfoot th').each( function (e) {
        var title = $('.tableListaCliente thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 2    ){
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
</factura-condominio>