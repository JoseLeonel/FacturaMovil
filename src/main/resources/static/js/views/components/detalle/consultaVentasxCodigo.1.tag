<ventas-detalles>

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
	                <button  onclick={__EnviarCorreoAlternativo}   class="btn-green btn-correo pull-right" >  {$.i18n.prop("btn.enviar.correo")}</button>
	            </div>
	         </div>		       
	      </div>
	    </div>
	  </div>
	</div>
    

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
                            <input type="text" class="form-control codigoArt" id="codigoArt" name="codigoArt"  onkeypress={__ConsultarProductosCod} >
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
   <!-- Titulos -->
    <div  class="row "  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp Ventas por Articulo</h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
        </div>
    </div>
    <br>
    <!-- Inicio Filtros-->
    <div>
        <div >
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="knob-label" >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                    <div  class="form-group input-group date datepickerFechaInicial" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control fechaInicial " id="fechaInicial"  name= "fechaInicial" readonly>
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>	                             
                                </div>  
                            </div>             
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label class="knob-label" >{$.i18n.prop("fecha.final")} <span class="requeridoDato">*</span></label>
                                        <div  class="form-group input-group date datepickerFechaFinal" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaFinal  " id="fechaFinal"  name= "fechaFinal" readonly>
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>	                             
                                    </div>
                                </div>  
                            </div>
                             <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>{$.i18n.prop("articulo.codigo")} </label>  
	                                <input onclick = {__ListaDecodigos} type="text" class="form-control codigo" id="codigo" name="codigo" value="{articulo.codigo}">
                                    <label>{articulo.descripcion} </label>  
                                </div>  
                            </div>     
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>{$.i18n.prop("cliente.titulo")} </label>  
                                    <select  class="form-control selectCliente cliente" id="cliente" name="cliente" data-live-search="true">
                                        <option  data-tokens="{$.i18n.prop("todos.select")}"   value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option  data-tokens="{nombreCompleto}" each={clientes.data}  value="{cedula}"  >{nombreCompleto}</option>
                                    </select>
                                </div>  
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>{$.i18n.prop("factura.tipo.documento")} </label>  
                                    <select  class="form-control tipoDocumento" id="tipoDocumento" name="tipoDocumento" >
                                        <option  value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option each={comboTipoDocumentos} value="{estado}"  >{descripcion}</option>
                                    </select>
                                </div>  
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>{$.i18n.prop("articulo.tipoImpuesto")} </label>  
                                    <select  class="form-control selectTipoImpuesto tipoImpuesto" id="tipoImpuesto" name="tipoImpuesto"   >
                                        <option    value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option  each={impuestos} value="{codigo}" >{descripcion}</option>
                                    </select>
                                </div>  
                            </div>

                        </div>
                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
                <a   show={hay_datos==true} onclick= {__CorreoAlternativo} class=" btn btn-success btn-correo"   title="Enviar Correo" href="#"> Enviar Correo</a>        
                <a   show={hay_datos==true} class=" btn btn-primary btn-bajar"  target="_blank" title="Descargar detalle transacciones" href="DescargarDetallexCodigoAjax.do?fechaInicialParam={fechaInicialParam}&fechaFinalParam={fechaFinalParam}&idClienteParam={idClienteParam}&codigoParam={codigoParam}&tipoDocumentoParam={tipoDocumentoParam}"> Descargar</a>        
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
            	<button onclick ={__limpiarFiltros} show={mostrarFiltros} class="btn btn-warning btnLimpiarFiltros" title="LimpiarCampos" type="button"><i id="clear-filters" class="fa fa-eraser clear-filters"></i></button>            
            </div>
        </div>
    </div>    
<!-- Fin Filtros-->

    <br>
  <!-- Listado  -->
    <div classs="contenedor-listar "  show={mostrarListado} >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                <div class="box">
                    <div class="box-body">
                        <div class="planel-body" >
                         <div class= "row">
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <label  >{$.i18n.prop("factura.resumen.descuento")} </label>
                                    <input type="text" class="form-control totalDescuentoGeneral " value="{totalDescuentoGeneral}" readonly>
                                </div>  
                            </div>                             
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <label  >{$.i18n.prop("factura.resumen.impuesto")} </label>
                                    <input type="text" class="form-control totalImpuestoGeneral" value="{totalImpuestoGeneral}" readonly>
                                </div>  
                            </div>                             
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <label  >{$.i18n.prop("factura.total")} </label>
                                    <input type="text" class="form-control totalGeneral " value="{totalGeneral}" readonly>
                                </div>  
                            </div>                             
                        </div>
                        
                            <div class="row" >        
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12" >
                                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar "   cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th class = "table-header" >{$.i18n.prop("usuario.nombreUsuario")}            </th>
                                                <th class = "table-header" >{$.i18n.prop("articulo.codigo")}                  </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.fecha.emision")}            </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.documento")}                </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.linea.detalle.cantidad")}   </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.linea.detalle.precio")}     </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.linea.detalle.descuento")}  </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.linea.detalle.impuesto")}   </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.total")}                    </th>
                                                <th class = "table-header" >                 </th>
                                            </tr>
                                        </thead>
                                        <tfoot style="display: table-header-group;">
                                            <tr>
                                                <th>{$.i18n.prop("usuario.nombreUsuario")}            </th>
                                                <th>{$.i18n.prop("articulo.codigo")}                  </th>
                                                <th>{$.i18n.prop("factura.fecha.emision")}            </th>
                                                <th>{$.i18n.prop("factura.documento")}                </th>
                                                <th>{$.i18n.prop("factura.linea.detalle.cantidad")}   </th>
                                                <th>{$.i18n.prop("factura.linea.detalle.precio")}     </th>
                                                <th>{$.i18n.prop("factura.linea.detalle.descuento")}  </th>
                                                <th>{$.i18n.prop("factura.linea.detalle.impuesto")}   </th>
                                                <th>{$.i18n.prop("factura.total")}                    </th>
                                                <th>                                                  </th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                   
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
    }AnteriorSiguiente
    .AnteriorSiguiente
     AnteriorSiguiente
     AnteriorSiguiente
     AnteriorSiguiente
    }AnteriorSiguiente

    .AnteriorSiguiente
     AnteriorSiguiente
     AnteriorSiguiente
     AnteriorSiguiente
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
self.impuestos             =[]
self.totalDescuentos       = 0
self.totalImpuestos        = 0
self.totalImpuestoServicio        = 0
self.total                 = 0
self.mostrarListado        = true
self.articulo = {
    id:null,
    descripcion:"",
    codigo:""
}

self.articulos             = {data:[]}
 self.clientes              = {data:[]}
self.informacion_tabla_articulo    = []
self.hay_datos             = false
self.tipoDocumentoParam =""
self.idClienteParam=null
self.codigoParam = ""
self.fechaFinalParam = null
self.fechaInicialParam=null
self.totalDescuentoGeneral = 0
self.totalImpuestoGeneral = 0
self.totalGeneral = 0

self.on('mount',function(){
    $("#filtros").validate(reglasDeValidacion());
    __InformacionDataTable()
    __InicializarTabla('.tableListar')
    __InicializarTabla('.tableListaInventario')
     agregarInputsCombos_Articulo()
    agregarInputsCombos()
    listaClientesActivos()
    __ComboTipoDocumentos()
    __Impuestos()
     $('.datepickerFechaFinal').datepicker(
       	{
            format: 'yyyy-mm-dd',
            todayHighlight:true,
       	}
         );
        $('.datepickerFechaInicial').datepicker(
        {
            format: 'yyyy-mm-dd',
            todayHighlight:true,
        }
        );
      window.addEventListener( "keydown", function(evento){
                $(".errorServerSideJgrid").remove();
            }, false );
   
})
/**
* Enviar el correo
**/
__EnviarCorreoAlternativo(){
    if ($("#formulario").valid()) {
    	 __EnviarPorCorreo()
     }
}
/**
*  Regresar al listado
**/
__regresarAlListadoAlternativo(){
    $('#ModalCorreoAlternativo').modal('hide')
}
/**
*  Busqueda de la informacion y la envia por correo
**/
function __EnviarPorCorreo(){
    if ($("#filtros").valid()) {
        var parametros = {
           	correoAlternativo:$('#correoAlternativo').val(),		
           	fechaInicialParam:$('.fechaInicial').val(),
            fechaFinalParam:$('.fechaFinal').val(),
            codigoParam:$('.codigo').val(),
            tipoDocumentoParam:$('.tipoDocumento').val(),
            idClienteParam:$('.cliente').val(),
            totalDescuentoGeneral:self.totalDescuentoGeneral,
            totalImpuestoGeneral:self.totalImpuestoGeneral,
            totalGeneral:self.totalGeneral,
            descripcion:self.articulo.descripcion,
            tipoImpuesto:$('.selectTipoImpuesto').val(),
		};
		$.ajax({
		    url: "EnvioDetalleFacturasXCodigoCorreoAjax.do",
		        datatype: "json",
		        data:parametros ,
		        method:"GET",
		    success: function (data) {
                if (data.status != 200) {
                   	serverMessageJson(data);
                    if (data.message != null && data.message.length > 0) {
                       	swal({
      	                    title: '',
      	                    text: data.message,
      	                    type: 'error',
      	                    showCancelButton: false,
      	                    confirmButtonText: 'Aceptar',
      	                })
                        }
                } else {
                   swal({
	                    title: '',
	                    text: "Enviado el correo Exitosamente",
	                    type: 'success',
	                    showCancelButton: false,
	                    confirmButtonText: 'Aceptar',
	                })
                }
	        },
	        error: function (xhr, status) {
	            console.log(xhr);
	            mensajeErrorServidor(xhr, status);
	        }
        });
 	}		
}
/**
*correoalternativo
**/
__CorreoAlternativo(){
	$('#correoAlternativo').val(null);
    $('#ModalCorreoAlternativo').modal({
	    backdrop: 'static',
        keyboard: false
    });
    $('#ModalCorreoAlternativo').modal('show');      
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
* Definicion de la tabla articulos 
**/
function _informacionData_Articulo(){
   self.informacion_tabla_articulo = [	{'data' : 'codigo'         ,"name":"codigo"          ,"title" : $.i18n.prop("articulo.codigo")       ,"autoWidth":false},
                                        {'data' : 'descripcion'    ,"name":"descripcion"     ,"title" : $.i18n.prop("articulo.descripcion")  ,"autoWidth":false},
                                        {'data' : 'cantidad'       ,"name":"cantidad"        ,"title" : $.i18n.prop("inventario.cantidad")   ,"autoWidth":false},
                                        {'data' : 'precioPublico'  ,"name":"precioPublico"   ,"title" : $.i18n.prop("articulo.precioPublico"),"autoWidth":false,
                                          "render":function(precioPublico,type, row){
                                              var resultado = formatoDecimales(__valorNumerico(precioPublico))
                                               return  resultado;
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
        $('#modalInventario').modal('hide') 
	    
    });
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
    $(".tableListarArticulos").DataTable().destroy();
    $('#descArticulo').select()
    $('#descArticulo').focus()
    $('#modalInventario').modal('show')    

 }
/**
* cargar los tipos de Documento de la factura
**/
function __ComboTipoDocumentos(){
    self.comboTipoDocumentos = []
    self.update()
    self.comboTipoDocumentos.push({
        estado:"04",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
    })
    self.comboTipoDocumentos.push({
         estado:"01",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
    })
    
     self.comboTipoDocumentos.push({
         estado:"02",
        descripcion:$.i18n.prop("referencia.tipo.documento.nota.debito")
    })
    self.comboTipoDocumentos.push({
         estado:"03",
        descripcion:$.i18n.prop("referencia.tipo.documento.nota.credito")
    })
    self.update()
}
/**
*  Obtiene la lista de los clientes activos
**/
function listaClientesActivos(){
    self.clientes                  = {data:[]}
    self.update()
    $.ajax({
        url: "ListarClientesActivosAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                 self.clientes.data = result.aaData
                 self.update()
                   $('.selectCliente').selectpicker();
             } 
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
}
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			fechaInicial : {
				required : true,
			},
			fechaFinal : {
				required : true,
			}
		},ignore : []

	});
	return validationOptions;
};
/**
* limpiar los filtros
**/
__limpiarFiltros(){
    $('#fechaInicial').val(null)
    $('#fechaFinal').val(null)
    $('#codigo').val(null)
    self.hay_datos             = false
    self.tipoDocumentoParam =""
    self.idClienteParam=null
    self.codigoParam = ""
    self.fechaFinalParam = null
    self.fechaInicialParam=null
    self.totalDescuentoGeneral = 0
    self.totalImpuestoGeneral = 0
    self.totalGeneral = 0

    self.update()
      $('.datepickerFechaFinal').datepicker(
       	{
            format: 'yyyy-mm-dd',
            todayHighlight:true,
       	}
         );
        $('.datepickerFechaInicial').datepicker(
        {
            format: 'yyyy-mm-dd',
            todayHighlight:true,
        }
        );

}

/**
* Combo para verificar si es contabilizado en el inventario o no
**/
function __Impuestos(){
    self.impuestos =[]
    self.update()
     self.impuestos.push({
        codigo: ' ',
        descripcion:"Sin impuesto"
     });

    self.impuestos.push({
        codigo: '01',
        descripcion:$.i18n.prop("tipo.impuesto.ventas")
     });
    self.impuestos.push({
        codigo: '07',
        descripcion:$.i18n.prop("tipo.impuesto.servicio")
     });
    self.impuestos.push({
        codigo: '12',
        descripcion:$.i18n.prop("tipo.impuesto.cemento")
     });
    self.impuestos.push({
        codigo: '98',
        descripcion:$.i18n.prop("tipo.impuesto.otros")
     });
   
     self.update();
     
}


/**
*  Busqueda de la informacion por rango de fechas
**/
__Busqueda(){
    var fechaini = new Date($('.fechaInicial').val());
	var fechafin = new Date($('.fechaFinal').val());
	var diasdif= fechafin.getTime()-fechaini.getTime();
	var contdias = Math.round(diasdif/(1000*60*60*24));
    if(contdias > 31){
        swal({
      	    title: '',
      	    text: $.i18n.prop("detalle.error.consulta.articulo"),
      	    type: 'error',
      	    showCancelButton: false,
      	    confirmButtonText: 'Aceptar',
      	})
        return
    }
    self.listaFacturas = []
    self.hay_datos             = false
    self.hay_datos             = false
    self.tipoDocumentoParam =$('.tipoDocumento').val()
    self.idClienteParam=$('#cliente').val()
    self.codigoParam = $('.codigo').val()
    self.fechaInicialParam=$('.fechaInicial').val()
    self.fechaFinalParam = $('.fechaFinal').val()
    self.update()
     if ($("#filtros").valid()) {
        var parametros = {
            fechaInicio:$('.fechaInicial').val(),
            fechaFin:$('.fechaFinal').val(),
            codigo:$('.codigo').val(),
            tipoDocumento:$('.tipoDocumento').val(),
            idCliente:$('#cliente').val(),
            tipoImpuesto:$('.tipoImpuesto').val(),

        };
        $("#tableListar").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListar')  
        $.ajax({
            url: "ListaDetallesxCodigoAjax.do",
            datatype: "json",
            data:parametros ,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable();
                    loadListar(".tableListar",idioma_espanol,self.formato_tabla,result.aaData)
                    self.listaFacturas = result.aaData
                    self.hay_datos             = true
                    self.update()
                    agregarInputsCombos();
                    ActivarEventoFiltro(".tableListar")
                    TotalesGenerales(result.aaData)
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

}
/**
*  Suma de totales de cuenta por cobrar 
**/
function TotalesGenerales(data){
    self.totalDescuentoGeneral = 0
    self.totalImpuestoGeneral = 0
    self.totalGeneral = 0
    self.update()
    
    for(var i in data) { 
        self.totalDescuentoGeneral      += data[i].montoDescuento;
        self.totalImpuestoGeneral += data[i].montoImpuesto;
        self.totalGeneral += data[i].montoTotalLinea;
     }
     self.totalImpuestoGeneral = formatoDecimales(self.totalImpuestoGeneral,2)
     self.totalDescuentoGeneral = formatoDecimales(self.totalDescuentoGeneral,2)
     self.totalGeneral = formatoDecimales(self.totalGeneral,2)
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
*Formato del listado 
**/
function __InformacionDataTable(){
    self.formato_tabla = [ 
                               {'data' :'nombreUsuario'          ,"name":"nombreUsuario"      ,"title" : $.i18n.prop("usuario.nombreUsuario")     ,"autoWidth" :true },
                               {'data' :'codigo'                 ,"name":"codigo"             ,"title" : $.i18n.prop("articulo.codigo")           ,"autoWidth" :true },
                               {'data' :'fechaEmisionSTR'        ,"name":"fechaEmisionSTR"    ,"title" : $.i18n.prop("factura.fecha.emision")     ,"autoWidth" :true },
                               {'data' :'consecutivo'            ,"name":"consecutivo"        ,"title" : $.i18n.prop("factura.documento")   ,"autoWidth" :true ,
                                   "render":function(consecutivo,type, row){
									    return __TipoDocumentos(consecutivo,row)
	 							    }
                               },
                               {'data' :'cantidad'                ,"name":"cantidad"           ,"title" : $.i18n.prop("factura.linea.detalle.cantidad"),"autoWidth" :true },
                               {'data' :'precioUnitarioSTR'       ,"name":"precioUnitarioSTR"  ,"title" : $.i18n.prop("factura.linea.detalle.precio")     ,"autoWidth" :true },
                               {'data' :'montoDescuentoSTR'       ,"name":"montoDescuentoSTR"  ,"title" : $.i18n.prop("factura.linea.detalle.descuento"),"autoWidth" :true },
                               {'data' :'montoImpuestoSTR'        ,"name":"montoImpuestoSTR"   ,"title" : $.i18n.prop("factura.linea.detalle.impuesto") ,"autoWidth" :true },
                               {'data' :'montoTotalLineaSTR'      ,"name":"montoTotalLineaSTR" ,"title" : $.i18n.prop("factura.total") ,"autoWidth" :true },
                               {'data' : 'id'                     ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}
/**
Documentos por consecutivo
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
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
    return "";
}
/**
filtro de los impues
**/
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
</script>
</ventas-detalles>