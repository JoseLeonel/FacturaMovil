<lista-proformas>
   <!-- Titulos -->
    <div  class="row " show="mostrarListado" >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("facturas.proformas")} </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
        </div>
    </div>
    <br>
  

    <br>
  <!-- Listado  -->
    <div classs="contenedor-listar "  show={mostrarListado} >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                <div class="box">
                    <div class="box-body">
                        <div class="planel-body" >
                        
                            <div class="row" >        
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12" >
                                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar "   cellspacing="0" width="100%">
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

                                    <h2 class="pull-right">{$.i18n.prop("factura.linea.detalle.impuesto")}:{totalImpuestos.toLocaleString('de-DE')} {$.i18n.prop("factura.linea.detalle.descuento")}:{totalDescuentos.toLocaleString('de-DE')} {$.i18n.prop("factura.total")}:{total.toLocaleString('de-DE')}  </h2>
                                </div>   

                            </div> 
  
                        </div>    
                    </div>
                </div>
            </div>
            <div class="col-md-1 col-lg-1 "> </div>
        </div>
    </div>
    <!-- Fin del Listado -->


 <div class="box box-solid box-primary" show={mostrarDetalle}>
        <div class="box-body">
             <div class="box-header with-border">
                <div class="row">
                  <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">  
                  <div class="box-tools ">
                  </div>
                  </div>
                </div>  
                  <br>
             </div>
            <div  class="contenedor-compra " >
                <div class="cabecera-izquierda">
                        <form id="formularioFactura">
                            <input id="id" name="id" type="hidden" value="{compra.id}">
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
                                        <label>{$.i18n.prop("factura.vendedor")}</label> 
                                        <input type="text"  class="form-control"  value="{vendedor.nombreCompleto}" readonly>
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
                            <div class="row">
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <div class="form-group ">
                                        <label >{$.i18n.prop("factura.direccion")}</label> 
                                        <input type="text" class="form-control direccion" id="direccion" name="direccion" value="{factura.direccion}" readonly>
                                    </div>
                                </div>
                            </div>

                        </form>   
                        <br>
                    <table class="table table-striped">
                        <thead>
                        <tr>
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
                            <td>{numeroLinea}</td>
                            <td>{codigo}</td>
                            <td>{descripcion}</td>
                            <td class="text-right">
                                <input  class="form-control " type="number" placeholder="Cantidad Detalle" value = {cantidad} readonly/>
                            </td>
                            <td class="text-right">
                                <input   class="form-control" type="text"  value = "{precioUnitario.toLocaleString('de-DE')}" readonly />
                            </td>
                            <td class="text-right">
                                <input   class="form-control" type="text"  value = "{descuento.toLocaleString('de-DE')}" readonly/>
                            </td>
                                                        
                            <td class="text-right">
                                <input  class="form-control" type="text"  value = "{impuesto.toLocaleString('de-DE')}" readonly/>
                            </td>

                            <td class="text-righ">
                                <input  class="form-control" type="text"  value = "₡ {montoTotalLinea.toLocaleString('de-DE')}" readonly/>
                            </td>
                        </tr>
                        </tbody>
                    </table>     
                    <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
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
                                                    <span class="label label-info textShadow" id="total-show">₡ {factura.totalComprobante.toLocaleString('de-DE')}</span>
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

<!-- Modal correo alternativo-->
<div class="modal fade" id="ModalCorreoAlternativo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
          
                <h1 class="box-title"><i class="btn-correo"></i>&nbsp {$.i18n.prop("hacienda.titulo.correo.alternativo")}     </h1>
          
      </div>
      <div class="modal-body">
        <form id = "formulario" name ="formulario "   class="advanced-search-form">
            <div class="row">   
                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                    <label class="knob-label" >{$.i18n.prop("hacienda.correo")}</label>
                    <input type="email" class="form-control correoAlternativo" placeHolder ="{$.i18n.prop("hacienda.correo.ejemplo")}" id="correoAlternativo" name="correoAlternativo" value=""  >
                </div>
            </div>
        </form>
      </div>
      <div class="modal-footer">
        <div class="row">
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                <button onclick ={__regresarAlListadoAlternativo}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                    {$.i18n.prop("btn.volver")}
                </button>
            </div>
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                <button  onclick={__Enviar}   class="btn-green btn-correo pull-right" >  {$.i18n.prop("btn.enviar.correo")}</button>
            </div>
         </div>
       
      </div>
    </div>
  </div>
</div>


<style type="text/css">
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
</style>
 


<style type ="text/css">
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
self = this
self.detail                = []
self.totalDescuentos       = 0
self.totalImpuestos        = 0
self.total                 = 0
self.mostrarListado        = true
self.mostrarDetalle        = false
self.on('mount',function(){
    $("#formulario").validate(reglasDeValidacionCorreo());
    __InformacionDataTable()
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    sumar()
    listado()
})

/**
*  Regresar al listado
**/
__regresarAlListadoAlternativo(){
    $('#ModalCorreoAlternativo').modal('hide')
    
}

/**
* Camps requeridos
**/
var reglasDeValidacionCorreo = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			correoAlternativo : {
				required : true,
                email:true,
                maxlength:240,
                minlength:1,
			}                                   
                        
		},
		ignore : []

	});
	return validationOptions;
};

/**
* Enviar el correo
**/
__Enviar(){

     if ($("#formulario").valid()) {
         enviarCorreoAlternativo()
     }

}

function listado(){
    __InformacionDataTable();
    self.listaFacturas = []
    self.update()
         
        $("#tableListar").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListar')  
        $.ajax({
            url: "ListarProformasActivasAjax.do",
            datatype: "json",
              method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable();
                    loadListar(".tableListar",idioma_espanol,self.formato_tabla,result.aaData)
                    self.listaFacturas = result.aaData
                    self.update()
                    agregarInputsCombos();
                    ActivarEventoFiltro(".tableListar")
                    __VerDetalle()
                    __BajarPDF()
                    __CorreoAlternativo()
                    __CambiarEstado()
                    sumar()
                    __EnviarCorreos()
                }else{
                    __InformacionDataTable();
                     agregarInputsCombos();

                }           
            },
            error: function (xhr, status) {
                mensajeErrorServidor(xhr, status);
                console.log(xhr);
            }
        });
}

function sumar(){
          self.totalImpuestos = 0
          self.total = 0
          self.totalDescuentos = 0
          self.update()

    $.each(self.listaFacturas, function( index, modeloTabla ) {
          self.totalImpuestos += modeloTabla.totalImpuesto
          self.total += modeloTabla.totalComprobante
          self.totalDescuentos += modeloTabla.totalDescuentos
          

    })
    self.totalImpuestos  = redondearDecimales(self.totalImpuestos,2)
    self.total           = redondearDecimales(self.total,2)
    self.totalDescuentos = redondearDecimales(self.totalDescuentos,2)
    
    self.update()
}




/*
 * Muestra los filtros avanzados
 */
 __mostrarFiltros(){
    if(self.mostrarFiltros){
        self.mostrarFiltros = false;
        self.valorMarginBottom  = '10px'
    }else{
        self.mostrarFiltros = true;
        self.valorMarginBottom  = '0px'
    }
    self.update();
}


/**
*  Regresar al listado
**/
__regresarAlListado(){
    self.detail                = []
    self.mostrarListado        = true
    self.mostrarDetalle        = false
    self.update()
    __listado();

}
/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(factura){

 
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
    self.factura.tipoDoc = __TipoDocumentos(self.factura.id,self.factura)
    self.detail                = []
    self.factura.detalles.forEach(function(e){
        self.detail.push({
            numeroLinea     : e.numeroLinea,
            codigo          : e.codigo,
            descripcion     : e.descripcion,
            cantidad        : redondearDecimales(parseFloat(e.cantidad),5),
            precioUnitario  : redondearDecimales(parseFloat(e.precioUnitario),5),
            impuesto        : redondearDecimales(parseFloat(e.impuesto),5),
            montoImpuesto   : redondearDecimales(parseFloat(e.montoImpuesto),5),
            montoDescuento  : redondearDecimales(parseFloat(e.montoDescuento),5),
            porcentajeDesc  : redondearDecimales(parseFloat(e.porcentajeDesc),5),
            subTotal        : redondearDecimales(parseFloat(e.subTotal),5),
            montoTotalLinea : redondearDecimales(parseFloat(e.montoTotalLinea),5),
            montoTotal      : redondearDecimales(parseFloat(e.montoTotal),5)
        });
    })
    
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
    case 3:
         self.factura.estado=  $.i18n.prop("factura.estado.proforma")
        break;

    default:
        self.factura.condicionVenta
    }
    self.update()
}
/**
*
**/
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
/**
*Formato del listado 
**/
function __InformacionDataTable(){
    self.formato_tabla = [ 
                               {'data' :'fechaEmisionSTR'   ,"name":"fechaEmisionSTR"    ,"title" : $.i18n.prop("factura.fecha.emision")     ,"autoWidth" :true },
                             
                               {'data' :'id'                    ,"name":"id"                     ,"title" : $.i18n.prop("factura.documento")   ,"autoWidth" :true ,
                                   "render":function(id,type, row){
									    return __TipoDocumentos(id,row)
	 							    }
                               },
                               {'data' :'cliente'                    ,"name":"cliente"                     ,"title" : $.i18n.prop("factura.cliente")   ,"autoWidth" :true ,
                                   "render":function(cliente,type, row){
									    return cliente ==null?"":cliente.nombreCompleto;
	 							    }
                               },
                               {'data' :'totalImpuesto'       ,"name":"totalImpuesto"        ,"title" : $.i18n.prop("factura.linea.detalle.impuesto")     ,"autoWidth" :true ,
                                    "render":function(totalImpuesto,type, row){
                                         var resultado = formatoDecimales(__valorNumerico(totalImpuesto))
									    return  resultado;
	 							    }
                               },
                               {'data' :'totalDescuentos'                ,"name":"totalDescuentos"                 ,"title" : $.i18n.prop("factura.linea.detalle.descuento")  ,"autoWidth" :true ,
                                    "render":function(totalDescuentos,type, row){
                                        var resultado = formatoDecimales(__valorNumerico(totalDescuentos))
									    return  resultado;
	 							    }
                               },
                               {'data' :'totalComprobante'               ,"name":"totalComprobante"                ,"title" : $.i18n.prop("factura.total") ,"autoWidth" :true ,
                                    "render":function(totalComprobante,type, row){
                                        var resultado = formatoDecimales(__valorNumerico(totalComprobante))
									    return  resultado;
	 							    }
                               },
                               {'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}

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
        return  "Proforma:"+numeroConsecutivo
        break;
    default:
        return  numeroConsecutivo
}
}

    
/**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
    let menu = '<div class="dropdown">' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    
    menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
    menu += '<li><a href="#"  title="Cambia el Estado Proforma a Venta en espera" class="  btnPendiente" >Cambiar a venta en espera</a></li>'
    menu += '<li><a href="#"  title="Envio del correo al cliente" class="  btnEnvioCorreoCliente" >Envio Correo</a></li>'
    menu += '<li><a href="#"  title="Bajar PDF" class="  btnPDF" >Bajar PDF</a></li>'
    menu += '<li><a href="#"  title="Envio de correo Alternativo" class="  btnEnvioCorreoAlternativo" >Envio de correo Alternativo</a></li>'
    menu += "</ul></div>"  

     return menu;          
}


/**
*  Cambiar Estado de Proforma a venta en espera
**/
function __CambiarEstado(){
	$('.tableListar').on('click','.btnPendiente',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.factura = data
        self.update()
        _actualizarEstado()
	});
}

function _actualizarEstado(){
    $.ajax({
        url: "CambiarEstadoProformaAPedienteAjax.do",
        datatype: "json",
        data: {idFactura:self.factura.id},
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                sweetAlert("", data.message, "info");
                listado()
            }
            
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
}

/**
*  Enviar a correo alternativo
**/
function __CorreoAlternativo(){
	$('.tableListar').on('click','.btnEnvioCorreoAlternativo',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.factura = data
        self.update()
        $('.correoAlternativo').val(null)
        $('#ModalCorreoAlternativo').modal('show')      
	});
}

/**
 * Envio del correo al emisor y receptor
 */
function __EnviarCorreos(){
	$('.tableListar').on('click','.btnEnvioCorreoCliente',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.factura = data
        self.update()
        enviarCorreoAlternativo();
        
	});
}

/**
* Enviar correo
**/
function enviarCorreoAlternativo(){
    $.ajax({
        url: "EnviarCorreoAlternativoProformaAjax.do",
        datatype: "json",
        data: {idFactura:self.factura.id,correo:$('.correoAlternativo').val()},
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                sweetAlert("", data.message, "info");
            }
            
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
}


/**
 * mostrar la abono
 */
function __VerDetalle(){
	$('.tableListar').on('click','.btnMostrar',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.factura = data
        self.mostrarListado        = false
        self.mostrarDetalle        = true
        self.update()
        __FacturaEnEspera(self.factura)
	});
}

/**
 * mostrar la abono
 */
function __BajarPDF(){
	$('.tableListar').on('click','.btnPDF',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        location.href = "generaProformasPDF.do?idFactura=" + data.id
	});
}


/**
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY ');
}

function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 9    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 

function redondearDecimales(numero, decimales) {
    numeroRegexp = new RegExp('\\d\\.(\\d){' + decimales + ',}');   // Expresion regular para numeros con un cierto numero de decimales o mas
    if (numeroRegexp.test(numero)) {         // Ya que el numero tiene el numero de decimales requeridos o mas, se realiza el redondeo
        return Number(numero.toFixed(decimales));
    } else {
        return Number(numero.toFixed(decimales)) === 0 ? 0 : numero;  // En valores muy bajos, se comprueba si el numero es 0 (con el redondeo deseado), si no lo es se devuelve el numero otra vez.
    }
}

</script>
</lista-proformas>