<consulta-impuestoServicio>
   <!-- Titulos -->
    <div  class="row "  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp Consulta del Impuesto de Servicio 10%</h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
        </div>
    </div>
    <br>
    <br><br>
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
                                    <label class="knob-label" >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control fechaInicial date" id="fechaInicial"  name= "fechaInicial" >
                                   
                            </div>             
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                        <label class="knob-label" >{$.i18n.prop("fecha.final")} <span class="requeridoDato">*</span></label>
                                         <input type="text" class="form-control fechaFinal date" id="fechaFinal"  name= "fechaFinal" >
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label>Total Imp.Serv(10%) </label>
                                <input type="text" readonly="readonly" class="form-control "   value={totalImpuestoServicio}>
                            </div>



                        </div>
                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
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
                                                <th class = "table-header" >Total I.S                    </th>
                                                <th class = "table-header" >                 </th>
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
                                                 <th>Total I.S                    </th>
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
self.totalImpuestoServicio        = 0
self.total                 = 0

self.mostrarListado        = true
self.on('mount',function(){
    $("#filtros").validate(reglasDeValidacion());
    __InformacionDataTable()
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
$('#example').datetimepicker();

    _init()
    sumar()
      window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );

})


function _init(){

 
 
    $('#fechaInicial').datetimepicker(
    {
       
        format: 'YYYY-MM-DD hh:mm A',
        keyBinds: {
            'delete': function () {
                    return false;
                    }
                },
        }
    );
        $('#fechaFinal').datetimepicker(
    {
        format: 'YYYY-MM-DD hh:mm A',
        keyBinds: {
            'delete': function () {
                    return false;
                    }
                }
        }
    );
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
* limpiar los filtros
**/
__limpiarFiltros(){
    $('#fechaInicial').val(null)
    $('#fechaFinal').val(null)
}

/**
*  Busqueda de la informacion por rango de fechas
**/
__Busqueda(){
    self.listaFacturas = []
    self.update()
    var inicial  =$('.fechaInicial').val()
     if ($("#filtros").valid()) {
        var parametros = {
            fechaInicio:inicial,
            fechaFin:$('.fechaFinal').val(),
          
        };
        $("#tableListar").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListar')  
        $.ajax({
            url: "ListaDetallesImpuestoServicioAjax.do",
            datatype: "json",
            data:parametros ,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable();
                    loadListar(".tableListar",idioma_espanol,self.formato_tabla,result.aaData)
                    self.listaFacturas = result.aaData
                    self.update()
                    agregarInputsCombos();
                    ActivarEventoFiltro(".tableListar")
                
                    sumar()
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
function sumar(){
    self.totalImpuestos = 0
    self.total = 0
    self.totalDescuentos = 0
    self.totalImpuestoServicio = 0
    self.update()
    $.each(self.listaFacturas, function( index, modeloTabla ) {
          self.totalImpuestos += modeloTabla.totalImpuesto
          self.totalImpuestoServicio += modeloTabla.impuestoServicioIS
          self.total += modeloTabla.totalComprobante
          self.totalDescuentos += modeloTabla.totalDescuentos
          self.update()
    })
    self.totalImpuestoServicio = formatoDecimales(__valorNumerico(self.totalImpuestoServicio))
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
*Formato del listado 
**/
function __InformacionDataTable(){
    self.formato_tabla = [ 
                               {'data' :'fechaEmisionSTR'   ,"name":"fechaEmisionSTR"    ,"title" : $.i18n.prop("factura.fecha.emision")     ,"autoWidth" :true },
                             
                               {'data' :'numeroConsecutivo'                    ,"name":"numeroConsecutivo"                     ,"title" : $.i18n.prop("factura.documento")   ,"autoWidth" :true ,
                                   "render":function(numeroConsecutivo,type, row){
									    return __TipoDocumentos(numeroConsecutivo,row)
	 							    }
                               },
                               {'data' :'cliente'                    ,"name":"cliente"                     ,"title" : $.i18n.prop("factura.cliente")   ,"autoWidth" :true ,
                                   "render":function(cliente,type, row){
									    return cliente ==null?"":cliente.nombreCompleto;
	 							    }
                               },
                               {'data' :'totalImpuestoSTR'       ,"name":"totalImpuestoSTR"        ,"title" : $.i18n.prop("factura.linea.detalle.impuesto")     ,"autoWidth" :true },
                               {'data' :'totalDescuentosSTR'     ,"name":"totalDescuentosSTR"      ,"title" : $.i18n.prop("factura.linea.detalle.descuento")  ,"autoWidth" :true },
                               {'data' :'totalComprobanteSTR'       ,"name":"totalComprobanteSTR"                ,"title" : $.i18n.prop("factura.total") ,"autoWidth" :true ,
                                    "render":function(totalComprobante,type, row){
                                        var resultado = formatoDecimales(__valorNumerico(totalComprobante))
									    return  resultado;
	 							    }
                               },
                               
                               {'data' :'impuestoServicioISSTR'               ,"name":"impuestoServicioISSTR"                ,"title" : "Imp.Serv" ,"autoWidth" :true 
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


</script>
</consulta-impuestoServicio>