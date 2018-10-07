<compra-proveedores>
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
                        <th class="table-header">{$.i18n.prop("proveedor.representante")}  </th>
                        <th class="table-header">{$.i18n.prop("proveedor.email")}          </th>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}        </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("proveedor.nombreCompleto")} </th>
                            <th>{$.i18n.prop("proveedor.representante")}  </th>
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
                            <input id="id" name="id" type="hidden" value="{compra.id}">
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-46">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("compra.forma.pago")} </label> 
                                        <select  onchange= {__formaPago} class="form-control formaPago campo" id="formaPago" >
                                            <option each={comboFormaPagos} value="{estado}" selected="{compra.formaPago ==estado?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>
 
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-46">
                                    <div class="form-group ">
                                        <label for="pago_tipoVentaL">{$.i18n.prop("compra.tipo.documento")} </label> 
                                        <select class="form-control tipoDocumento campo" id="tipoDocumento" name="tipoDocumento"   >
                                            <option each={comboTipoDocumentos} value="{estado}" selected="{compra.tipoDocumento ==estado?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>
                                </div>
                            </div>    
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <input   type="hidden" class="form-control campo proveedor" id="proveedor" name="proveedor" value="{proveedor.id}">
                                        <label>{$.i18n.prop("compra.proveedor")}</label> 
                                        <input onclick = {_EscogerProveedores}  type="text" id="nombreProveedor" name="nombreProveedor" class="campo nombreProveedor form-control"  value="{proveedor.nombreCompleto}">
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("compra.consecutivo")}</label> 
                                        <input type="text" class="form-control consecutivo campo" id="consecutivo" name="consecutivo" value="{compra.consecutivo}">
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div  class="form-group">
                                        <label>{$.i18n.prop("compra.fecha.compra")}</label> 
                                        <div  class="form-group input-group date " data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaCompra campo" id="fechaCompra" name = "fechaCompra" value="{compra.fechaCompra}" >
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>    
                                </div>
                            </div>    
                            <div class="row">
                            </div>    
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div show = {!mostrarCamposIngresoContado || compra.fechaCredito} class="form-group has-success">
                                        <label >{$.i18n.prop("compra.fecha.credito")}</label> 
                                        <div  class="form-group input-group date" data-provide="datepicker"  data-date-start-date="0d" data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaCredito campo" id="fechaCredito" value="{compra.fechaCredito}" >
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
                                        <label >{$.i18n.prop("compra.nota")}</label> 
                                        <input type="text" class="form-control nota campo" id="nota" name="nota" value="{compra.nota}">
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
                        </article>
                    </aside>     
                </section>               
            </div><!--fin del cabecera-derecha-->
        </div><!--fin del cabecera-derecha-->
    </div><!--fin del contenedor-compra-->
    <div class="box box-solid box-primary" show={mostarParaCrearNuevaCompra}>
        <div class="box-body">
             <div class="box-header with-border">
                <div class="row">
                  <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">  
                    <div class="box-tools ">
                            <a class="pull-left" href="#"   onclick = {}    title="Modulo de Compras"><span class="label label-limpiar ">Compras a proveedores </span></a>
                            <a class="pull-left" href="#"   onclick = {__MostrarFormularioDePago}       title="Aplicar la compra"> <span class="label label-limpiar">{$.i18n.prop("comprar.f8")}</span></a>
                            <a class="pull-left" href="#"   onclick = {__crearCompraEnEspera}  title="Compra en espera"> <span class="label label-limpiar">{$.i18n.prop("comprar.f9")}</span></a>
                            <a class="pull-left" href="#"   onclick = {__Limpiar} title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f10")}</span></a>
                            <a class="pull-right" href="#"  title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-articulos">{descripcionArticulo}</span></a>
                        </div>
                    </div>
                </div>  
                  <br>

            <div  class="contenedor-compra " >
                <div class="cabecera-izquierda">
                    <div class="row">
                        <div class="col-sx-6 col-sm-6 col-md-6 col-lg-6">
                            <input onkeypress={__addProductToDetail}  id="codigo" class="campo" type="text" placeholder="XXXXXXXXXXX" />
                        </div>
                        <div class="col-sx-2 col-sm-2 col-md-2 col-lg-2">
                            <button    onclick = {__ListaDecodigos} class="btn btn-primary boton-consultar" id="btn-facturar" >
                                <i class="glyphicon glyphicon-plus"></i>Buscar
                            </button>
                        </div>
                    </div>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th style="width:5%;">                                                      </h1></th>
                            <th><h1>{$.i18n.prop("compra.linea.detalle.linea")}                         </h1></th>
                            <th><h1>{$.i18n.prop("compra.linea.detalle.codigo")}                        </h1></th>
                            <th style="width:20%;"><h1>{$.i18n.prop("compra.linea.detalle.descripcion")}</h1></th>
                            <th style="width:8%;"><h1>{$.i18n.prop("compra.linea.detalle.cantidad")}                      </h1></th>
                            <th style="width:8%;"><h1>{$.i18n.prop("compra.linea.detalle.costo")}                         </h1></th>
                            <th style="width:8%;"><h1>{$.i18n.prop("compra.linea.detalle.precio")}                        </h1></th>
                            <th style="width:8%;"><h1>{$.i18n.prop("compra.linea.detalle.descuento")}                     </h1></th>
                            <th><h1>{$.i18n.prop("compra.linea.detalle.impuesto")}                      </h1></th>
                            <th><h1>{$.i18n.prop("compra.linea.detalle.total")}                        </h1></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td>
                                <button  onclick={__removeProductFromDetail} class="btn btn-danger btn-xs btn-block">X</button>
                            </td>
                            <td><h2>{linea}</h2></td>
                            <td><h2>{codigo}</h2></td>
                            <td><h2>{descripcion}</h2></td>
                            <td class="text-right">
                                <input onkeypress={__recalculacionDelDetalle} onBlur={__recalculacionDelDetalle} id= "cantidadDetalle" class="campo" type="number" step="any" placeholder="Cantidad Detalle" value = {cantidad} />
                            </td>
                            <td class="text-right">
                                <input  onkeypress={__actualizarCosto} onBlur={__actualizarCosto} class="campo" type="number" step="any"  value = "{costo}" />
                            </td>
                            <td class="text-right">
                                <input  onkeypress={__actualizarPrecio} onBlur={__actualizarPrecio} class="campo" type="number" step="any"  value = "{precio}" />
                            </td>
                            <td class="text-right">
                                <input  onkeypress={__actualizarDescuento} onBlur={__actualizarDescuento} class="campo" type="number" step="any"  value = "{descuento}" />
                            </td>
                            <td class="text-right">
                                <h2>{totalImpuesto.toFixed(2)} </h2>
                            </td>
                            <td class="text-right">
                                <h2>{montoTotalLinea.toFixed(2)} </h2>
                            </td>
                        </tr>
                        </tbody>
                    </table>          
                </div>
                <section class="cabecera-derecha">
				    <!--right sidebar-->
                    <aside class="left-sidebar">
                            <!--Booking details-->
                        <article class="booking-details clearfix">
                            <div    onclick = {__MostrarFormularioDePago} id="btnGrandePagar" class="head green well" style="color: #fff; font-size: 25px;  padding-top:8px !important; padding-bottom:8px !important; margin-bottom: 8px;">
                                <table id="pagarTable" width="100%">
                                    <tbody>
                                        <tr>
                                            <td width="30%" id="">
                                                <div id="pagarTitulo">{$.i18n.prop("compra.total")}:</div>
                                            </td>
                                            <td width="70%" id="">
                                            
                                                <div id="">
                                                    <span class="label label-info textShadow" id="total-show">{totalGeneralCompra}</span>
                                                </div>
                                            </td>
                                        </tr>                     
                                    </tbody>
                                </table>
                            </div>
                        </article>
                    </aside>
                    <section   class="lista-compras-espera">
                        <div id="botones"  each={compras_espera.data}  onclick={__CargarCompraEspera}>
                            <a href="#" class="compras-espera"  title="{proveedor !=null?proveedor.nombreCompleto:""}">C# {id}</a>
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
                <form id="formularioParametros" name ="formularioParametros" >
                    <div class="row">
                        <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <label  >{$.i18n.prop("articulo.codigo")}  </label>
                            <input type="text" class="form-control" id="codigoArt" name="codigoArt"  onkeypress={__ConsultarProductosCod} >
                        </div>
                        <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <label  >{$.i18n.prop("articulo.descripcion")}</label>
                            <input type="text" class="form-control "   id="descArticulo" name="descArticulo" onkeypress={__ConsultarProductosDesc}>
                        </div>
                    </div> 
                </form>    
                <br>       
                <table id="tableListarArticulos" class="display table responsive table-hover nowrap table-condensed tableListarArticulos " cellspacing="0" width="100%">
                    <thead>
                        <th class="table-header">{$.i18n.prop("articulo.codigo")}      </th>
                        <th class="table-header">{$.i18n.prop("articulo.descripcion")} </th>
                        <th class="table-header">{$.i18n.prop("inventario.cantidad")}  </th>
                        <th class="table-header">{$.i18n.prop("articulo.costo")}       </th>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}     </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th >{$.i18n.prop("articulo.codigo")}      </th>
                            <th >{$.i18n.prop("articulo.descripcion")} </th>
                            <th >{$.i18n.prop("inventario.cantidad")}  </th>
                            <th >{$.i18n.prop("articulo.costo")}       </th>
                            <th >                                      </th>
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
<style type="text/css">
    .boton-consultar {
        display: block;
        display: inline-block;
            margin-bottom: 0;
        width: 100%;
        border-radius: 3px;
        height: 47px;
        padding: 6px 12px;
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
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    }
    /* Lista de facturas en espera*/
    .cabecera-derecha .lista-compras-espera{
        width:100%;
        display:flex;
        flex-wrap:wrap;
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
        background-color: #f2f2f2;
        color: #000;
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
    .campo {
        display: block;
        width: 100%;
        height: 45px;
        padding: 6px 16px;
        font-size: 10px;
        line-height: 1.42857143;
        color: #555;
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
        font: 14px verdana, arial, helvetica, sans-serif;
        margin: 2px 0;
        padding: 1px 2px;
        overflow: visible;
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
    self.articulos             = {data:[]}
    self.proveedores           = {data:[]}
    self.detalleCompra         ={data:[]}
    self.proveedor             = {};
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
    self.on('mount',function(){
        $("#formularioCompra").validate(reglasDeValidacionCompra());
        __informacionData()
        __InicializarTabla('.tableListaProveedor')
        __InicializarTabla('.tableListaInventario')
        agregarInputsCombos_Articulo()
          __ListaComprasEnEspera()
        __comboFormaPagos()
        __ComboTipoDocumentos()
        __Teclas()
        __ListaDeProveedores()
        
    })
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
  $('#modalInventario').modal('show')      
 }
/**
*  Buscar la Compra Pendiente en espera
**/
__CargarCompraEspera(e){
   __CompraEnEspera(e.item)
}

/**
* Crear Compra en espera
**/
__crearCompraEnEspera(){
 crearCompra(1)  
}
/**
** Se aplica o se crea una compra cargada en la pantalla
**/
__AplicarYCrearCompra(){
     if(self.detail.length == 0 ){
        mensajeError($.i18n.prop("compra.alert.sin.detalles"))
        return
    }
    if(formaPago.value == 2  ){
        if(fechaCredito.value == null || fechaCredito.value.length == 0){
           mensajeError($.i18n.prop("compra.alert.fechaCredito"))
            return
        }
    }
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
    $('.nota').val(null)
    $('.consecutivo').val(null)
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
        totalCompra:0,
        nota:""
    }                  
    self.item                  = null;
    self.articulo              = null;
    self.articulos                     = {data:[]}
    self.proveedores                   = {data:[]}
    self.proveedor                     = {};
    self.mostrarFormularioPago = false
    self.mostarParaCrearNuevaCompra = true
    self.mostrarCamposIngresoContado = true;
    self.totalGeneralDescuento = 0;
    self.totalGeneralImpuesto  = 0;
    self.totalGeneralCompra    = 0; 

    self.update();
    // Tipo de Pagos
     __comboFormaPagos()
     //Tipos de Documentos
      __ComboTipoDocumentos()
     
}
/**
*  Compra en espera ,proveedor y sus  detalles desde back end
**/
function __CompraEnEspera(compra){
     __Init()
    self.detail         = []
    self.proveedor      = {}         
    self.update()
    $.ajax({
        url: "MostrarCompraEsperaAjax",
        datatype: "json",
        data: compra,
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.compra = modeloTabla
                       self.compra.fechaCompra = __displayDate_detail(self.compra.fechaCompra)
                       self.compra.fechaCredito = self.compra.fechaCredito !=null?__displayDate_detail(self.compra.fechaCredito):null
                       self.proveedor = modeloTabla.proveedor
                       self.update()
                    });
                }
                cargarDetallesCompraEnEspera()
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
    return moment(dateTime).format('YYYY-MM-DD ');
}

/**
*  Cargar detalles Compra Espera
**/
function cargarDetallesCompraEnEspera(){
    self.detail = []
    self.update()
    self.compra.detalleCompras.forEach(function(e){
        self.detail.push({
            linea           : e.numeroLinea,
            porcentajeImpuesto :e.iva,
            articulo_id     : e.articulo.id,
            codigo          : e.articulo.codigo,
            descripcion     : e.articulo.descripcion,
            cantidad        : parseFloat(e.cantidad),
            costo           : parseFloat(e.costo),
            precio          : parseFloat(e.precio),
            impuesto        : e.impuesto,
            descuento       : e.descuento,
            totalDescuento  : e.totalDescuento,
            totalImpuesto   : e.totalImpuesto,
            subTotal        : parseFloat(e.subTotal),
            montoTotalLinea : e.montoTotalLinea
        });
    })
    self.update()
     __calculate(); 
}
/**
*  Crear Compra nueva
**/
function crearCompra(estadoCompra){
    self.detalleCompra.data =self.detail
    self.update()
      var JSONDetalles = JSON.stringify( self.detalleCompra );
    var informacion = {
        id:self.compra.id,
        nota:$('.nota').val(),
        subTotal:__valorNumerico(self.compra.subTotal),
        totalDescuento:__valorNumerico(self.totalDescuentoDetalle),
        totalImpuesto:__valorNumerico(self.compra.totalImpuesto),
        totalCompra:__valorNumerico(self.compra.totalCompra),
        formaPago:$('.formaPago').val(),
        tipoDocumento:$('.tipoDocumento').val(),
        proveedor:$('.proveedor').val(),
        consecutivo:$('.consecutivo').val(),
        estado:estadoCompra,
        fechaCredito:$('.formaPago').val() == 2?$('.fechaCredito').val():new Date(),
        fechaCompra:$('.fechaCompra').val(),
        detalleCompra :JSONDetalles
     }
    $.ajax({
        type : "POST",
        dataType : "json",
        data : informacion,
        url : "CrearCompraAjax.do",
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                    mensajeError(data.message)
                }
            } else {
               	serverMessageJsonClase(data);
                swal({
	                title: '',
	                text: data.message,
	                type: 'success',
	                showCancelButton: false,
	                confirmButtonText: 'Aceptar',
                })
                __Init()
                __ListaComprasEnEspera()
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
/**
*  Lista de las compras pendientes pendiente por el usuario
**/
function __ListaComprasEnEspera(){
    self.compras_espera         = {data:[]}  
    self.update()
    $.ajax({
        url: 'ListarComprasEsperaActivasAjax',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
               self.compras_espera.data =  result.aaData;  
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
*
*Agregar codigos al detalle de la Compra
*
*/
__addProductToDetail(e){
    if (e.keyCode != 13) {
        return;
    } 
    __buscarcodigo(e.currentTarget.value,1);
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
function __ListaDeArticulosPorDescripcion(cod,desc){
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
              __agregarArticulos()
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
        url: 'ListarProveedoresAjax.do',
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
* Buscar el codigo del codigo  en la base de datos
**/
function __buscarcodigo(idArticulo,cantidad){
    self.articulo = null;
    self.update()
    $.ajax({
         datatype: "json",
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
                        self.articulo  = modeloTabla
                        __agregarArticulo(cantidad)
                        self.update()
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
               self.item  = self.detail[count];
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
    self.articulo = null;
    self.update()

    
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
    var iva =  __valorNumerico(self.articulo.impuesto/100)
    var montoImpuestoV    =__valorNumerico(self.articulo.costo * iva)
    var totalImpuesto     = montoImpuestoV * cantidad 
    var montoTotalLinea   = __valorNumerico(self.articulo.costo * cantidad) +  totalImpuesto
    self.descuento      = 0;
    self.detail.push({
       linea           : 0,
       porcentajeImpuesto :iva,
       articulo_id     : self.articulo.id,
       codigo          : self.articulo.codigo,
       descripcion     : self.articulo.descripcion,
       cantidad        : parseFloat(cantidad),
       costo           : self.articulo.costo,
       precio          : self.articulo.precioPublico,
       totalImpuesto   : totalImpuesto,
       totalDescuento  :0,
       impuesto        : montoImpuestoV,
       descuento       : 0,
       montoTotalLinea : montoTotalLinea
    });
    var cont = 0;
    self.detail.forEach(function(elemen){
            elemen.linea = cont + 1
            cont = elemen.linea
        }
    )
    self.update()
    self.detail.sort(function(a,b) {
    if ( a.linea > b.linea )
        return -1;
    if ( a.linea < b.linea )
        return 1;
    return 0;
    } );
    self.update()
}
/**
*   Actualizar el costo del codigo y recalcular la compra
**/
__actualizarCosto(e){
    if (e.keyCode != 13) {
        return;
    } 
    var costo = e.currentTarget.value;
    self.item = e.item; 
    var input = e.input;
    var index = self.detail.indexOf(self.item);
    //Cantidad del detalle se verifica si es null o espacio por defecto se deja en 1
    costo =__valorNumerico(costo);
    self.item.costo = parseFloat(costo);  
    _cambiaImpuesto()
    __actualizarItemArray();
    self.detail[index] = self.item;
    self.update();
    __calculate();
}
/**
* Actualiza el impuesto si cambia el costo
**/
function _cambiaImpuesto(){
    if(self.item.impuesto ==0){
        return 0
    }
    var costoProducto = 0
    if(self.item.descuento > 0){
        var valor = self.item.descuento /100
        var resultado = self.item.costo * valor
        costoProducto = self.item.costo - resultado
    }else{
        costoProducto = self.item.costo
    }
    var valor = costoProducto * self.item.porcentajeImpuesto
    self.item.impuesto      = __valorNumerico(valor)
    self.item.totalImpuesto = __valorNumerico(self.item.impuesto *  self.item.cantidad)
    self.update()
}
/**
* Actualizar item en el array
**/
function __actualizarItemArray(){
    //Subtotal del Detalle
    self.item.montoTotalLinea = __valorNumerico(self.item.costo * self.item.cantidad);
    self.item.montoTotalLinea = __valorNumerico(self.item.montoTotalLinea-self.item.totalDescuento)
    self.item.montoTotalLinea = __valorNumerico(self.item.montoTotalLinea + self.item.totalImpuesto);
    self.update()
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
 * Cuando se aplica un cambio de cantidad en un detalle
 * Se aplica una recalculacion de todo el detalle y Compra
 **/ 
 __recalculacionDelDetalle(e){
    if (e.keyCode != 13) {
        return;
    } 
    var cantidad = e.currentTarget.value;
    self.item = e.item; 
    var input = e.input;
    var index = self.detail.indexOf(self.item);
    
    //Cantidad del detalle se verifica si es null o espacio por defecto se deja en 1
    cantidad =__valorNumerico(cantidad);
    if(cantidad == 0){
       cantidad = 1;
    }
    self.item.cantidad = parseFloat(cantidad);  
    _cambiaImpuesto()
    __actualizarItemArray();
    self.detail[index] = self.item;
    self.update();
    __calculate();
 }
/**
*   Actualizar el costo del codigo y recalcular la compra
**/
__actualizarPrecio(e){
    if (e.keyCode != 13) {
        return;
    } 
    var precio = e.currentTarget.value;
    self.item = e.item; 
    var input = e.input;
    var index = self.detail.indexOf(self.item);
    //Cantidad del detalle se verifica si es null o espacio por defecto se deja en 1
    precio =__valorNumerico(precio);
    self.item.precio = parseFloat(precio);  
    __actualizarItemArray();
    self.detail[index] = self.item;
    self.update();
    __calculate();
}
/**
* Actualizar el descuento del codigo
**/
__actualizarDescuento(e){
    if (e.keyCode != 13) {
        return;
    } 
    self.item     = e.item; 
    var index     = self.detail.indexOf(self.item);
    var descuento = e.currentTarget.value;
    //Descuento se verifica si es null o espacios por defecto se deja en cero
     descuento =__valorNumerico(descuento);
      //Descuento
    if(self.item.descuento != descuento){
       self.item.descuento =  parseFloat(descuento);  
    } 
    var valor = self.item.descuento /100
    var resultado = self.item.costo * valor
    self.item.totalDescuento =  __valorNumerico(resultado * self.item.cantidad)
    self.update()   
    _cambiaImpuesto()
    __actualizarItemArray();
    self.detail[index] = self.item;
    self.update();
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
    self.detail.forEach(function(e){
        totalCompra      += e.montoTotalLinea >0?e.montoTotalLinea:0
        totalDescuento   += e.descuento >0?e.descuento:0
        totalImpuesto    += e.totalImpuesto >0?e.totalImpuesto:0
    });
    self.compra.totalCompra    = totalCompra
    self.compra.totalDescuento = totalDescuento
    self.compra.totalImpuesto  = totalImpuesto
    self.totalGeneralDescuento = formatoDecimales(totalDescuento,2)
    self.totalGeneralImpuesto  = formatoDecimales(totalImpuesto,2)
    self.totalGeneralCompra    = formatoDecimales(totalCompra,2)
    self.articulo              = null;
    self.update(); 
    $( "#codigo" ).val(null);
    $('.codigo').select()
    $('.codigo').focus()
}
/**
* Definicion de la tabla articulos 
**/
function _informacionData_Articulo(){
   self.informacion_tabla_articulo = [	{'data' : 'codigo'         ,"name":"codigo"          ,"title" : $.i18n.prop("articulo.codigo")       ,"autoWidth":false},
                                        {'data' : 'descripcion'    ,"name":"descripcion"     ,"title" : $.i18n.prop("articulo.descripcion")  ,"autoWidth":false},
                                        {'data' : 'cantidad'       ,"name":"cantidad"        ,"title" : $.i18n.prop("inventario.cantidad")   ,"autoWidth":false},
                                        {'data' : 'costo'          ,"name":"costo"           ,"title" : $.i18n.prop("articulo.costo")        ,"autoWidth":false},
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
  var agregar  = '<a href="#"  title="Agregar" class="btn btnAgregar btn-success form-control" title="Modificar" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
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
* formato de la tabla de proveedores
**/
function __informacionData(){

    self.informacion_tabla_proveedores = [	{'data' : 'nombreCompleto'  ,"name":"nombreCompleto" ,"title" : $.i18n.prop("proveedor.nombreCompleto")         ,"autoWidth":false},
                                            {'data' : 'representante'   ,"name":"representante"  ,"title" : $.i18n.prop("proveedor.representante")  ,"autoWidth":false},
                                            {'data' : 'email'           ,"name":"email"          ,"title" : $.i18n.prop("proveedor.email")          ,"autoWidth":false},                                
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
	    self.proveedor = data
        self.update();
    });
}
/**
* cargar los estados de la compra
**/
function __comboFormaPagos(){
    self.comboFormaPagos = []
    self.comboFormaPagos.push({
        estado:1,
        descripcion:$.i18n.prop("combos.formaPago.contado")
    })
    self.comboFormaPagos.push({
        estado:2,
        descripcion:$.i18n.prop("combos.formaPago.credito")
    })
     self.comboFormaPagos.push({
        estado:3,
        descripcion:$.i18n.prop("combos.formaPago.sinCobro")
    })
    self.update()
}
/**
* cargar los tipos de Documento de la compra
**/
function __ComboTipoDocumentos(){
    self.comboTipoDocumentos = []
    self.comboTipoDocumentos.push({
        estado:1,
        descripcion:$.i18n.prop("combos.tipoDocumento.factura")
    })
    self.comboTipoDocumentos.push({
        estado:2,
        descripcion:$.i18n.prop("combos.tipoDocumento.boleta")
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
    
    //Limpiar
    if(tecla ==121){
      __Init()
    }

  if(tecla ==27){
      $('.codigo').select()
      $('.codigo').focus()
    }
    }, false );
}                         
</script>
</compra-proveedores>