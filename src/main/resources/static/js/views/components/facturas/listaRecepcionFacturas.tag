<lista-facturas>
   <!-- Titulos -->
    <div  class="row " show="mostrarListado" >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp Compras Aceptadas en Hacienda </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
        </div>
    </div>
    <br>
    <br><br>
    <!-- Inicio Filtros-->
    <div>
        <div class="row" show={mostrarListado}>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid " style="margin-bottom : {valorMarginBottom}; padding : 2px;  cursor: pointer;">
                    <h4> <i class="fa fa-filter " style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left " style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="knob-label" >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                    <div  class="form-group input-group date" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control fechaInicial" id="fechaInicial"  name= "fechaInicial" readonly>
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
                                        <div  class="form-group input-group date" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaFinal" id="fechaFinal"  name= "fechaFinal" readonly>
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>	                             
                                    </div>
                                </div>  
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>{$.i18n.prop("emisor.cedula")} </label>  
	                                <input type="text" class="form-control cedulaEmisor" id="cedulaEmisor" name="cedulaEmisor">
                                </div>  
                            </div>    
                        </div>
                        <div class="row">    
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>Estado </label>  
                                    <select  class="form-control selectEstado estado" id= "estado" name="estado" >
                                    	<option value="0"  >Todos</option>
                                        <option  value="2"  >Pendiente Aceptar</option>
	                                   	<option  value="6"  >Aceptada</option>
                                       	<option  value="7"  >Rechazadas</option>

                                    </select>
                                </div>  
                            </div>  
                             <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>Actividad Economica </label>  
                                    <select  onchange= {__AsignarActividad} class="form-control selectActividadEconocimica" id= "actividadEconomica" name="actividadEconomica" >
                                        <option value="0"  >Todas</option>
										<option  each={empresaActividadComercial}  value="{codigo}"   >{descripcion}</option>
                                    </select>
                                </div>  
                            </div>              
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <label> Tipo Gasto  <span class="requeridoDato">*</span></label>
                                <select class="form-control tipoGasto" id="tipoGasto" name="tipoGasto" >
                                    <option value="0"  >Todos</option>
                                    <option value="1">{$.i18n.prop("tipo.gasto.aceptacion.compra.inventario")}</option>
                                    <option value="2">{$.i18n.prop("tipo.gasto.aceptacion.compra.gasto")}</option>  
                                </select>
                            </div>                     
                        </div>
                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
                <a   show={hay_datos==true} class=" btn btn-primary btn-bajar"  target="_blank" title="Descargar Resumen Compras" href="DescargarComprasAceptadasAjax.do?fechaInicioParam={fechaInicio}&fechaFinParam={fechaFin}&cedulaEmisor={cedula}&estado={estado}&tipoGasto={tipoGasto}"> Descargar</a>        
                <a   show={hay_datos==true} class=" btn btn-primary btn-bajar"  target="_blank" title="Descargar Resumen Compras por detalle" href="DescargarDetalladaAceptadasAjax.do?fechaInicioParam={fechaInicio}&fechaFinParam={fechaFin}&cedulaEmisor={cedula}&estado={estado}&tipoGasto={tipoGasto}"> Descargar</a>        
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
            	<button onclick ={__limpiarFiltros} show={mostrarFiltros} class="btn btn-warning btnLimpiarFiltros" title="LimpiarCampos" type="button"><i id="clear-filters" class="fa fa-eraser clear-filters"></i></button>            
            </div>
        </div>
    </div>    
	<!-- Fin Filtros-->

    <br>
  	<!-- Listado  -->
    <div classs="contenedor-listar "  show={mostrarListado} >
                <div class="box">
                    <div class="box-body">
                        <div class="planel-body" >
                           
                        
                            <div class="listaContainer" >        
                                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar "   cellspacing="0" width="100%">
                                       <thead>
                                            <tr>
                                                <th class = "table-header" >Ingreso    </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.fecha.emision")}     </th>
                                                <th class = "table-header" >{$.i18n.prop("emisor.cedula")}             </th>
                                                <th class = "table-header" >Tipo Aceptacion         </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.totalImpuestos")}    </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.totalComprobante")}  </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.documento")}         </th>
                                                <th class = "table-header" >#Compra        </th>
                                                
                                            </tr>
                                        </thead>
                                        <tfoot style="display: table-header-group;">
                                            <tr>
                                                <th>Ingreso</th>
                                                <th>{$.i18n.prop("factura.fecha.emision")}</th>
                                                <th>{$.i18n.prop("emisor.cedula")}</th>
                                                <th>Tipo Aceptacion</th>
                                                <th>{$.i18n.prop("factura.totalImpuestos")} </th>
                                                <th>{$.i18n.prop("factura.totalComprobante")} </th>
                                                <th>{$.i18n.prop("factura.documento")}</th>
                                                 <th>#Compra</th>
                                                
                                            </tr>
                                        </tfoot>
                                    </table>

                            </div> 
  
                        </div>    
                    </div>
                </div>
        
    </div>
    <!-- Fin del Listado -->

<style type="text/css">
    .listaContainer{
        display:flex;
    }
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
    div.dataTables_wrapper {
        width: 100%;
        margin: 0 auto;
    }
    
</style>

<script>
self = this

self.mostrarListado        = true
self.mostrarDetalle        = false
self.hay_datos             = false
self.fechaInicio =null
self.fechaFin =null
self.cedula =""
self.totalGeneral = 0
self.totalImpuestoGeneral = 0
self.estado = 6
self.tipoGasto = 0
self.on('mount',function(){
    $("#filtros").validate(reglasDeValidacion());
    __InformacionDataTable()
     __InicializarTabla('.tableListar')
     __ListaActividadesComercales()
})
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
/**
* Asigna la actividad comercial
**/
__AsignarActividad(e){
    BuscarActividadComercial()
}
/**
*Busca la actividad comercial
**/
function BuscarActividadComercial(){
    var codigo =$('.selectActividadEconocimica').val()
    if(self.empresaActividadComercial == null){
       return    
    }
    if(self.empresaActividadComercial.length == 0){
       return    
    }
    $.each(self.empresaActividadComercial, function( index, modeloTabla ) {
        if(modeloTabla.codigo == codigo  ){
            self.actividadEconomica = codigo
            self.update()
        }

    })
}
/**
* limpiar los filtros
**/
__limpiarFiltros(){
    $('#fechaInicial').val(null)
    $('#fechaFinal').val(null)
    self.totalGeneral = 0
    self.totalImpuestoGeneral = 0
    self.update()
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
                        
		},
		ignore : []

	});
	return validationOptions;
};
/**
*  Busqueda de la informacion por rango de fechas
**/
__Busqueda(){
    self.hay_datos   = false
    self.fechaInicio =$('.fechaInicial').val()
    self.fechaFin    =$('.fechaFinal').val()
    self.cedula      =$('#cedulaEmisor').val()
    self.estado = $("#estado").val()
    self.tipoGasto = $("#tipoGasto").val()
    self.totalGeneral = 0
    self.totalImpuestoGeneral = 0
    self.listaFacturas = []
    self.update()
    
    var inicial  =$('.fechaInicial').val()
     if ($("#filtros").valid()) {
        var parametros = {
        	fechaInicioParam:inicial,
        	fechaFinParam:$('.fechaFinal').val(),
        	cedulaEmisor:$('#cedulaEmisor').val(),
            estado:$('#estado').val(),
            actividadEconomica:$('.selectActividadEconocimica').val(),
            tipoGasto:$('.tipoGasto').val(),
        };
        $("#tableListar").dataTable().fnClearTable(); 
        $.ajax({
            url: "ListarRecepcionFacturasActivasAndAnuladasAjax.do",
            datatype: "json",
            data:parametros ,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable();
                    loadListar(".tableListar",idioma_espanol,self.formato_tabla,result.aaData)
                    self.listaFacturas = result.aaData
                    self.hay_datos             = true
                    self.estado = $('#estado').val(),
                    self.tipoGasto = $("#tipoGasto").val()
                    self.update()
                    TotalesGenerales(result.aaData)
                    agregarInputsCombos()
                    ActivarEventoFiltro(".tableListar")
                }else{
                    __InformacionDataTable();
                    agregarInputsCombos()
                }           
            },
            error: function (xhr, status) {
                mensajeErrorServidor(xhr, status);
            }
        });
     }
}

/**
*  Listar la tabla a aplicar el mantenimiento
**/
function __InicializarTabla(nombreTabla){
    $(nombreTabla).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [0, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        
    });    
}

function loadListar(table,idioma,formatoTabla,data){
	$(table).DataTable().destroy();
        $(table).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "scrollCollapse": true,
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
*  Suma de totales de cuenta por cobrar 
**/
function TotalesGenerales(data){
    self.totalGeneral = 0
    self.totalImpuestoGeneral = 0
    self.update()
    for(var i in data) { 
        self.totalGeneral         += data[i].totalFactura;
        self.totalImpuestoGeneral += data[i].totalImpuestos;
     }
     self.totalGeneral = formatoDecimales(self.totalGeneral,2)
     self.totalImpuestoGeneral = formatoDecimales(self.totalImpuestoGeneral,2)
     self.update()
}

function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
    })
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
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY ');
}
/**
*Formato del listado 
**/
function __InformacionDataTable(){
    self.formato_tabla = [ 
			{'data' : 'created_at'  ,"name":"created_at"    ,"title" : "Ingreso"     , "autoWidth" : true},
            {"data" : "fechaEmision"	,"name":"fechaEmision"    ,"title" : $.i18n.prop("factura.fecha.emision")      ,"autoWidth":true, 
				"render" : function(fechaEmision){
					return formatearFechaDT(fechaEmision); 
				}
			},
			
			{'data' : 'cedulaEmisor'  ,"name":"cedulaEmisor"    ,"title" : $.i18n.prop("emisor.cedula")     , "autoWidth" : true},
    	    {'data' : 'mensaje'  ,"name":"mensaje"    ,"title" : "Tipo Aceptacion"     , "autoWidth" : true},
            {'data' :'totalImpuestos'       ,"name":"totalImpuestos"        ,"title" : $.i18n.prop("factura.totalImpuestos")     ,"autoWidth" :true,
                "render":function(totalImpuestos,type, row){
				    return  totalImpuestos.toFixed(2);
				    }
           },
           {'data' :'totalFactura'       ,"name":"totalFactura"        ,"title" : $.i18n.prop("factura.totalComprobante")     ,"autoWidth" :true ,
               "render":function(totalFactura,type, row){
				    return  totalFactura.toFixed(2);
				    }
          },
    	  {'data' :'numeroConsecutivoReceptor'                    ,"name":"numeroConsecutivoReceptor"                     ,"title" : $.i18n.prop("factura.documento")   ,"autoWidth" :true ,
            		 "render":function(numeroConsecutivoReceptor,type, row){
								return __TipoDocumentos(numeroConsecutivoReceptor,row)
	 						  }

          }
          ,
    	  {'data' :'facturaConsecutivo'                    ,"name":"facturaConsecutivo"                     ,"title" : "#Compra"   ,"autoWidth" :true ,
            		 "render":function(facturaConsecutivo,type, row){
								return __TipoDocumentos(facturaConsecutivo,row)
	 						  }
                               
          }
   ];
   self.update();
}
//Formato de fecha
function formatearFechaDT(fecha){	
	if(fecha == null){
		return " ";	
	}else{
		return moment(fecha).format('DD/MM/YYYY');		
	}	
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

    default:
        return  numeroConsecutivo
}
}
</script>
</lista-facturas>