<cerrada-caja>
    <!-- Titulos -->
    <div  class="row titulo-encabezado" show={mostrarTitulo}  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-lock"></i>&nbsp {$.i18n.prop("usuarioCaja.titulo.cajas.cerradas")} {mostrarListadoFacturasXCaja ==true?"--Facturas" :""} {mostrarDetalleDeFactura ==true?"-->" + factura.tipoDoc  :""} </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
    




<!-- Listado  -->
<div classs="contenedor-listar container" id="container"  show={mostrarListadoFacturasXCaja}  >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                    <table id="tableListarFacturasXCaja" class="display table responsive table-hover nowrap table-condensed tableListarFacturasXCaja"   cellspacing="0" width="100%">
                         <thead>
                            <tr>
                                <th class = "table-header" >{$.i18n.prop("usuario.nombreUsuario")}            </th>
                                <th class = "table-header" >{$.i18n.prop("factura.fecha.emision")}            </th>
                                <th class = "table-header" >{$.i18n.prop("factura.documento")}                </th>
                                <th class = "table-header" >{$.i18n.prop("factura.cliente")}                  </th>
                                <th class = "table-header" >{$.i18n.prop("factura.resumen.efectivo")}         </th>
                                <th class = "table-header" >{$.i18n.prop("factura.resumen.tarjeta")}          </th>
                                <th class = "table-header" >{$.i18n.prop("factura.resumen.banco")}            </th>
                                <th class = "table-header" >{$.i18n.prop("factura.total")}                    </th>
                                <th class = "table-header" >{$.i18n.prop("listado.acciones")}                 </th>
                            </tr>
                            </thead>
                            <tfoot style="display: table-header-group;">
                                <tr>
                                    <th>{$.i18n.prop("usuario.nombreUsuario")}            </th>
                                    <th>{$.i18n.prop("factura.fecha.emision")}            </th>
                                    <th>{$.i18n.prop("factura.documento")}                </th>
                                    <th>{$.i18n.prop("factura.cliente")}                  </th>
                                    <th>{$.i18n.prop("factura.resumen.efectivo")}         </th>
                                    <th>{$.i18n.prop("factura.resumen.tarjeta")}          </th>
                                    <th>{$.i18n.prop("factura.resumen.banco")}            </th>
                                    <th>{$.i18n.prop("factura.total")}                    </th>
                                    <th>              </th>
                                </tr>
                            </tfoot>
                    </table>
                    <button onclick ={__regresarAlListadoCajaCerradas}  type="button" class="btn-dark-gray btn-back pull-left" >{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>    
</div>
<!-- Fin del Listado -->



<div class="box box-solid box-primary scrollerT" show={mostrarDetalleDeFactura}>
        <div class="box-body">
            <div  class="contenedor-compra " >
                <div class="cabecera-izquierda">
                        <form id="formularioFacturaFactura">
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("factura.condicion.pago")} </label> 
                                        <input type="text" class="form-control"  value="{factura.condicionVenta}"  readonly>
                                    </div>
 
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label for="pago_tipoVentaL">{$.i18n.prop("factura.tipo.documento")} </label> 
                                       <input type="text" class="form-control"  value="{factura.tipoDoc}" readonly >
                                    </div>
 
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label for="pago_tipoVentaL">{$.i18n.prop("factura.estado")} </label> 
                                        <input type="text" class="form-control"  value="{factura.estado}" readonly >
                                    </div>
                                </div>
                            </div>    
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("factura.cliente")}</label> 
                                        <input type="text"  class="form-control"  value="{cliente.nombreCompleto}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.cedula")}</label> 
                                        <input type="text" class="form-control " value="{cliente.cedula}" readonly>
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
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div show = {factura.fechaCredito} class="form-group has-success">
                                        <label >{$.i18n.prop("compra.fecha.credito")}</label> 
                                        <div  class="form-group input-group date" data-provide="datepicker"  data-date-start-date="0d" data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control" id="fechaCredito" value="{factura.fechaCredito}" readonly >
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
                                        <input type="text" class="form-control" id="nota" name="nota" value="{factura.direccion}" readonly>
                                    </div>
                                </div>
                            </div>

                        </form>   
                <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">

                    <table class="table table-striped ">
                        <thead>
                        <tr>
                            <th>{$.i18n.prop("factura.linea.detalle.linea")}                         </th>
                            <th>{$.i18n.prop("factura.linea.detalle.codigo")}                        </th>
                            <th style="width:20%;">{$.i18n.prop("compra.linea.detalle.descripcion")} </th>
                            <th >{$.i18n.prop("factura.linea.detalle.cantidad")}                     </th>
                            <th >{$.i18n.prop("factura.linea.detalle.precio")}                       </th>
                            <th >{$.i18n.prop("factura.linea.detalle.descuento")}                    </th>
                            <th >{$.i18n.prop("factura.linea.detalle.impuesto")}                     </th>
                            <th >{$.i18n.prop("factura.linea.detalle.impuesto1")}                     </th>
                            <th >{$.i18n.prop("factura.linea.detalle.subTotal")}                     </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td>{numeroLinea}</td>
                            <td>{codigo}</td>
                            <td>{descripcion}</td>
                            <td class="text-right">
                                <input  class="form-control " type="number" placeholder="Cantidad Detalle" value = {cantidad} readonly/>
                            </td>
                            <td class="text-right">
                                <input   class="form-control" type="text"  value = "{precioUnitario}" readonly />
                            </td>
                            <td class="text-right">
                                <input   class="form-control" type="text"  value = "{montoDescuento}" readonly/>
                            </td>
                                                        
                            <td class="text-right">
                                <input  class="form-control" type="text"  value = "{montoImpuesto}" readonly/>
                            </td>
                            <td class="text-right">
                                <input  class="form-control" type="text"  value = "{montoImpuesto1}" readonly/>
                            </td>

                            <td class="text-righ">
                                <input  class="form-control" type="text"  value = "{montoTotalLinea}" readonly/>
                            </td>
                        </tr>
                        </tbody>
                    </table>     
                 </div>
                 </div>   
                    <button onclick ={__regresarAlListadoFacturaEspecifica}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
     
                </div>
                <section class="cabecera-derecha">
				    <!--right sidebar-->
                     <div class="row">
                            <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">
                    <aside class="left-sidebar">
                            <!--Booking details-->
                        <article class="booking-details clearfix">
                            <div  id="btnGrandePagar" class="head green well" style="color: #fff; font-size: 25px;  padding-top:8px !important; padding-bottom:8px !important; margin-bottom: 8px;">
                                <table id="pagarTable" width="100%">
                                    <tbody>
                                        <tr>
                                            <td width="30%" id="">
                                                <div id="pagarTitulo">{$.i18n.prop("factura.total")}</div>
                                            </td>
                                            <td width="70%" id="">
                                            
                                                <div id="">
                                                    <span class="label label-info textShadow" id="total-show"> {factura.totalComprobanteSTR}</span>
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
                    
                </section>
                      
            </div><!-- fin contenedor-compra-->
            
        </div><!-- fin box-body-->
	</div><!-- fin box -->


<div class="row center " show ={mostrarVerDetalle} >
    <div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-8 col-lg-8 col-sx-12 col-sm-8">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-search"></i>&nbsp {$.i18n.prop("titulo.mostrar.usuarioCaja")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formularioUsuarioCaja" name ="formularioUsuarioCaja"   class="advanced-search-form ">
                        <input type="hidden" name="id" id="id" value="{usuarioCaja.id}">
                        <input type="hidden" name="caja" id="caja" value="{usuarioCaja.caja.id}">
                        
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.caja")}  </label>
                                <input type="text"  class="form-control"  value="{usuarioCaja.caja.descripcion}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.usuario")}  </label>
                                <input type="text"  class="form-control"  value="{usuarioCaja.usuario.nombre}"  readonly>
                            </div>

                        </div>

                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.fondoIncial")}  </label>
                                <input type="text" class="form-control "  value="{usuarioCaja.totalFondoInicialSTR}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalEfectivo")}  </label>
                                <input type="text" class="form-control "  value="{usuarioCaja.totalEfectivoSTR}" readonly >
                            </div>

                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalTarjeta")}  </label>
                                <input type="text" class="form-control "  value="{usuarioCaja.totalTarjetaSTR}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalBanco")}  </label>
                                <input type="text" class="form-control "  value="{usuarioCaja.totalBancoSTR}" readonly >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalServicio")}  </label>
                                <input type="text" class="form-control "  value=" {usuarioCaja.totalServicioSTR}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                               <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalAbono")}  </label>
                               <input type="text" class="form-control "  value=" {usuarioCaja.totalAbonoSTR}" readonly >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6 ">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalNeto")}  </label>
                                <input type="text" class="form-control "  value=" {usuarioCaja.totalNetoSTR}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                               <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalDolares")}  </label>
                               <input type="text" class="form-control "  value=" {usuarioCaja.totalDolaresSTR}" readonly >
                            </div>                            
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left "  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>
</div>





<style type="text/css">
.clickable {
        cursor: pointer;
    }
    .btn-success {
        color: #e7e7e7;
        background-color: #00a65a !important;
        border-color: #008d4c;
    }
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
    .wrap{
        max-width:100%;
        width:100%;
    }
    body {
        overflow: hidden;
        background:white;
        font-size: 14px !important;
        margin:0; 
        padding:0; 
        height:100%
    }
    .contenedor-listar{
        width:100%;
        max-width:1100px;
        width:100%;
    }
     .input-table-search{
        margin-left: 15px;
        margin-right: 15px;
        width:100%;
    }
    .botonConsulta{
        margin-top:28px;
    }
    
    table td{ 
        text-align: center;
        font-size: 12px;
        
            }
    table th {
            text-align: center;
            font-size: 12px;
    }

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
    #pagarTitulo{
        font-weight: 600 !important;
        font-size: 16px !important;
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
        font-size: 16px;
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
        font-size: 16px !important;
    }
    #pagarInfo{
        font-size: 16px !important;
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
    .contenedor-detalle  {
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
        font-size:20px;
    }
    .precioTotalFactura{
        font-weight:bold;
        font-size:20px;
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
</style>

<script>
    var self = this;
    self.parametros          = opts.parametros;  
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del Listado de la Tabla 
    self.cajas                  = {aaData:[]}
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarVerDetalle         = false
    self.mostrarListadoFacturasXCaja = false
    self.mostrarDetalleDeFactura  = false
    self.mostrarTitulo = false
    self.caja = {
        id:null,
        descripcion:"",
        estado:""
    }
    self.vendedor = {
        nombreCompleto:"",
        correoElectronico:"",
        celular:"",

    }
    self.cliente = {
        nombreCompleto:"",
        cedula:"",
        celular:"",
        correoElectronico:"",
        descuento:0,
        otraSena:""

    }
    self.usuarioCaja ={
        id:0,
        totalFondoInicial:0,
        caja:{
            nombre:"",
           descripcion:"",
           id:0 
        }


    }
    self.factura = {
        fechaCredito:"",
        direccion:"",
        totalComprobanteSTR:0,
        condicionVenta:"",
        tipoDoc:"",
        

    }
self.on('mount',function(){
    
    __InicializarTabla('.tableListarFacturasXCaja')
    agregarInputsCombos()
   
    ActivarEventoFiltro('.tableListarFacturasXCaja')
   
    __Eventos()
    __listadoCajasActivas()
    //__VerDetalleFacturaXCaja
    if(self.parametros.tipoEjecucion == 1){
        __facturasXCajas() 
    }
    //__VerDetalle
    if(self.parametros.tipoEjecucion == 2){
        __verdetalle()
    }

})
function __facturasXCajas(){
        self.usuarioCaja  = self.parametros.data
        self.mostrarListado            = false
        self.botonModificar            = false
        self.botonAgregar              = false
        self.mostrarVerDetalle         = false
        self.mostrarListadoFacturasXCaja = true
        self.mostrarTitulo = true
        self.update()
        __listarFacturasXCajas()
}
function __verdetalle(){
    self.mostrarVerDetalle         = true
    self.usuarioCaja  = self.parametros.data
    self.mostrarTitulo = true
    self.update()
}
/**
*Regresar al listado de la caja cerrada
**/
__regresarAlListadoCajaCerradas(){
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarVerDetalle         = false
    self.mostrarListadoFacturasXCaja = false
    self.mostrarDetalleDeFactura  = false
    self.mostrarTitulo = false
    self.update()
    mostrarListadoPrincipal()
}
/**
Factura especifica 
**/
__regresarAlListadoFacturaEspecifica(){
    self.mostrarListado            = false
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarVerDetalle         = false
    self.mostrarListadoFacturasXCaja = true
    self.mostrarDetalleDeFactura  = false
    self.mostrarTitulo = false
    self.update()
    
}

/**
*  Regresar al listado
**/
__regresarAlListado(){
    self.mostrarListado     = true;
    self.botonAgregar       = false;
    self.botonModificar     = false;
    self.mostrarFormulario  = false 
    self.mostrarVerDetalle  = false
     self.mostrarTitulo = false
    self.update()
    mostrarListadoPrincipal()
   
}
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			descripcion : {
				required : true,
                maxlength:80,
                minlength:1,
			},                                   
            terminal : {
				required : true,
                maxlength:3,
                minlength:3,
			}                             
		},
		ignore : []
	});
	return validationOptions;
};
/**
*  Mostrar listado datatable Cajas Activas
**/
function __listadoCajasActivas(){
    self.cajas                  = {aaData:[]}
    $.ajax({
         url: "ListarCajasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.cajas.aaData =  result.aaData
                self.update();
            }            
        }, 
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}
/**
*  Activar Eventos
**/
function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#descripcion").attr("maxlength", 80);
    $('#terminal').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
}

/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(){
    var formulario = $('#formularioUsuarioCaja').serialize();
    $.ajax({
        url: "MostrarUsuarioCajaAjax.do",
        datatype: "json",
        data: formulario,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.mostrarVerDetalle = true
                        self.usuarioCaja  = modeloTabla
                        self.update()
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
* Formato de montos
**/
function FormatoMontos(valor){
    var resultado = __valorNumerico(valor)
    return resultado;
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
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
      var dateTime = new Date(fecha);
      return moment(dateTime).format('DD/MM/YYYY h:mm:ss');
}
/**                                  LISTADO DE FACTURAS POR CAJA                                               **/

/**
*  Busqueda de la informacion por rango de fechas
**/
function __listarFacturasXCajas(){
    self.listaFacturas = []
    self.update()
    var parametros = {
        id:self.usuarioCaja.id
    };
    $("#tableListarFacturasXCaja").dataTable().fnClearTable(); 
    __InicializarTabla('.tableListarFacturasXCaja')  
    $.ajax({
            url: "ListarUsuariosCajasFacturasNoAnuladasAjax.do",
            datatype: "json",
            data:parametros ,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTableFacturasXCaja();
                    loadListar(".tableListarFacturasXCaja",idioma_espanol,self.formato_tabla_facturaXCaja,result.aaData)
                    self.listaFacturas = result.aaData
                    self.update()
                    agregarInputsCombosXCaja();
                    ActivarEventoFiltro(".tableListarFacturasXCaja")
                    __VerDetalleXFactura()
                    __BajarPDF()
                    __imprimirPTV()
                }else{
                    __InformacionDataTableFacturasXCaja();
                     agregarInputsCombosXCaja();
                }           
            },
            error: function (xhr, status) {
                mensajeErrorServidor(xhr, status);
                console.log(xhr);
            }
    });
}
/**
*Formato del listado 
**/
function __InformacionDataTableFacturasXCaja(){
    self.formato_tabla_facturaXCaja = [ 
                               {'data' :'nombreUsuario' ,"name":"nombreUsuario" ,"title" : $.i18n.prop("usuario.nombreUsuario")     ,"autoWidth" :true },
                               {'data' :'fechaEmision'  ,"name":"fechaEmision"  ,"title" : $.i18n.prop("factura.fecha.emision")     ,"autoWidth" :true },
                               {'data' :'numeroFactura' ,"name":"numeroFactura" ,"title" : $.i18n.prop("factura.documento")         ,"autoWidth" :true ,
                                "render":function(numeroFactura,type, row){
                                      return __TipoDocumentos(numeroFactura,row);
                                 }
                               
                               },
                               {'data' :'nombreCliente' ,"name":"nombreCliente" ,"title" : $.i18n.prop("factura.cliente")           ,"autoWidth" :true },
                               {'data' :'totalEfectivo' ,"name":"totalEfectivo" ,"title" : $.i18n.prop("factura.resumen.efectivo")  ,"autoWidth" :true  },
                               {'data' :'totalTarjeta'  ,"name":"totalTarjeta"  ,"title" : $.i18n.prop("factura.resumen.tarjeta")   ,"autoWidth" :true },
                               {'data' :'totalBanco'    ,"name":"totalBanco"    ,"title" : $.i18n.prop("factura.resumen.banco")     ,"autoWidth" :true },
                               {'data' :'total'         ,"name":"total"         ,"title" : $.i18n.prop("factura.total")  ,"autoWidth" :true },
                               {'data' : 'id'           ,"name":"id"       ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __OpcionesFacturas(id,type,row);
                                 }
	      		            }];
    self.update();
   
}
/**
Opcions del menu
**/
function __OpcionesFacturas(id,type,row){
  let menu = '<div class="dropdown">' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
    menu += '<li><a href="#"  title="Imprimir" class="  btnImprimir" >Imprimir</a></li>'
    menu += '<li><a href="#"  title="Bajar PDF" class="  btnPDF" >Bajar PDF</a></li>'
    menu += "</ul></div>"  
     return menu;          
}
 /**
*  imprimir impresora punto de venta
**/
function __imprimirPTV(){
	$('.tableListarFacturasXCaja').on('click','.btnImprimir',function(e){
		var table = $('#tableListarFacturasXCaja').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
           var parametros = {
                factura:data,
                facturaDia:1
            }
        riot.mount('ptv-imprimir',{parametros:parametros}); 
        
	});
}

/**
 * mostrar la abono
 */
function __BajarPDF(){
	$('.tableListarFacturasXCaja').on('click','.btnPDF',function(e){
		var table = $('#tableListarFacturasXCaja').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        location.href = "generaFacturaPDF?idFactura=" + data.id
	});
}
/**
Tipos de documentos
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

    default:
        return  numeroConsecutivo
}
}
/**
 * mostrar la abono
 */
function __VerDetalleXFactura(){
	$('.tableListarFacturasXCaja').on('click','.btnMostrar',function(e){
		var table = $('#tableListarFacturasXCaja').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.factura = data
        self.mostrarListado            = false 
        self.botonModificar            = false
        self.botonAgregar              = false
        self.mostrarVerDetalle         = false
        self.mostrarListadoFacturasXCaja = false
        self.mostrarDetalleDeFactura  = true
        self.update()
        __FacturaEnEspera(self.factura)
	});
}
/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(factura){

  self.factura = {
        fechaCredito:"",
        direccion:"",
        totalComprobanteSTR:0,
        condicionVenta:"",
        tipoDoc:"",
        

    }
    self.update()
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
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY ');
}

/**
*  Cargar detalles Factura en espera
**/
function cargarDetallesFacturaEnEspera(data){
    
    self.detail                = []
    self.numeroLinea =  0
    self.pesoPrioridad = 0
    self.factura = null
    self.update()
     $.each(data, function( index, modeloTabla ) {
        if(self.factura == null){
            self.factura = modeloTabla
            self.factura.tipoDoc = __TipoDocumentos(self.factura.numeroConsecutivo,self.factura)
            self.factura = modeloTabla.factura
            self.factura.fechaCredito = self.factura.fechaCredito !=null?__displayDate_detail(self.factura.fechaCredito):null
            self.cliente  = modeloTabla.factura.cliente
            self.vendedor = modeloTabla.factura.vendedor
            self.update()
        }
        self.detail.push({
            numeroLinea     : modeloTabla.numeroLinea,
            pesoPrioridad    :modeloTabla.numeroLinea,
            codigo          : modeloTabla.codigo,
            descripcion     : modeloTabla.descripcion,
            cantidad        : modeloTabla.cantidadSTR,
            precioUnitario  : modeloTabla.precioUnitarioSTR,
            impuesto        : modeloTabla.impuesto,
            montoImpuesto   : modeloTabla.montoImpuestoSTR,
            montoImpuesto1  : modeloTabla.montoImpuesto1STR,
            montoDescuento  : modeloTabla.montoDescuentoSTR,
            porcentajeDesc  : modeloTabla.porcentajeDesc,
            subTotal        : modeloTabla.subTotalSTR,
            montoTotalLinea : modeloTabla.montoTotalLineaSTR,
            montoTotal      : modeloTabla.montoTotalSTR
        });
    })
   self.update()
   self.detail.sort(function(a,b) {
    if ( a.pesoPrioridad > b.pesoPrioridad )
        return -1;
    if ( a.pesoPrioridad < b.pesoPrioridad )
        return 1;
    return 0;
    } );
    self.factura.tipoDoc = __TipoDocumentos(self.factura.numeroConsecutivo,self.factura)
    self.update()
    
    __comboCondicionPago()
    __ComboEstados()
}
/**
* cargar los estados de la compra
**/
function __ComboEstados(){
 switch(self.factura.estado) {
    case 1:
          self.factura.estado=  $.i18n.prop("factura.estado.pendiente")
        break;
    case 2:
         self.factura.estado=  $.i18n.prop("factura.estado.facturado")
        break;
    case 6:
         self.factura.estado=  "Aceptada"
        break;
    case 7:
         self.factura.estado=  "Rechazada"
        break;

    default:
        self.factura.condicionVenta
    }
    self.update()
}

function __comboCondicionPago(){
    switch(self.factura.condicionVenta) {
    case "01":
          self.factura.condicionVenta =  $.i18n.prop("factura.codicion.venta.contado")
        break;
    case "02":
         self.factura.condicionVenta=  $.i18n.prop("factura.codicion.venta.credito")
        break;

    default:
        self.factura.condicionVenta
    }
    self.update()
} 
/**                                  FIN LISTADO DE FACTURAS POR CAJA                                               **/
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 7    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
 
    })
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombosXCaja(){
     // Agregar los input de busqueda 
    $('.tableListarFacturasXCaja tfoot th').each( function (e) {
        var title = $('.tableListarFacturasXCaja thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 8    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}
</script>
</cerrada-caja>