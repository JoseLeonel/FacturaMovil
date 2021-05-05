<lista-compras>
   <!-- Titulos -->
     <div  id="titulo-encabezado"  >
        <div  >
            <h2><i class="fa fa-calculator"></i>&nbsp Compras Ingresadas al Inventario </h2>
        </div>

       <div id="totalsGenerales"  show={mostrarListado} >
            <div>
             	<div >
	                <span>Impuestos: </span>  
	                <input type="text"  class="totalImpuestos" value="{totalImpuestos}" readonly>
	            </div>
                <div >
                   <span>Total:</span>  
                   <input type="text" class="totalCompra " value="{total}" readonly>
                </div>  
	        </div>
        </div>    
    </div>
    
    <!-- Inicio Filtros-->
    <div>
        <div class="row" show={mostrarListado}>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;  cursor: pointer;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label  >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                    <div  class="form-group input-group date datepickerFechaInicio" data-provide="datepicker"    data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control fechaInicio" id="fechaInicio"  name= "fechaInicio" readonly>
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>	                             
                                </div>  
                            </div>             
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label  >{$.i18n.prop("fecha.final")} <span class="requeridoDato">*</span></label>
                                        <div  class="form-group input-group date datepickerFechaFinal" data-provide="datepicker"    data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaFin" id="fechaFin"  name= "fechaFin" readonly>
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>	                             
                                    </div>
                                </div>  
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>{$.i18n.prop("proveedor.titulo")} </label>  
                                    <select  class="form-control selectCliente" id="idProveedor" name="idProveedor" data-live-search="true">
                                        <option  data-tokens="{$.i18n.prop("todos.select")}"   value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option  data-tokens="{nombreCompleto}" each={proveedores.data}  value="{id}"  >{nombreCompleto}</option>
                                    </select>
                                </div>  
                            </div> 
                                                 
                        </div>
                        <div class="row">
                             <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>Estado</label>  
                                    <select  class="form-control estado" id="estado" name="estado" >
                                        <option  value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option each={estados} value="{codigo}"  >{descripcion}</option>
                                    </select>
                                </div>  
                            </div>
                        </div>    
                    </form>  


                </div>
            </div>
            <div class="col-xs-12 text-right">
                <a   show={hay_datos==true} class=" btn btn-primary btn-bajar"  target="_blank" title="Descargar detalle transacciones" href="DescargarComprasIngresadasAlmacenAjax.do?fechaInicioParam={fechaInicio}&fechaFinParam={fechaFin}&idProveedor={idProveedor}"> Descargar</a>        
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
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.proveedor")}      </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.consecutivo")}    </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.formaPago")}      </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.fecha.compra")}   </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.fecha.ingreso")}  </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.fecha.credito")}  </th>
                                                
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.total")}          </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.estado")}         </th>
                                                <th class = "table-header" >Usuario     </th>
                                                <th class = "table-header" >{$.i18n.prop("listado.acciones")}              </th>
                                            </tr>
                                        </thead>
                                        <tfoot style="display: table-header-group;">
                                            <tr>
                                                <th>{$.i18n.prop("compra.listado.proveedor")}        </th>
                                                <th>{$.i18n.prop("compra.listado.consecutivo")}      </th>
                                                <th>{$.i18n.prop("compra.listado.formaPago")}        </th>
                                                <th>{$.i18n.prop("compra.listado.fecha.compra")}    </th>
                                                <th>{$.i18n.prop("compra.listado.fecha.ingreso")}    </th>
                                                <th>{$.i18n.prop("compra.listado.fecha.credito")}    </th>
                                                
                                                <th>{$.i18n.prop("compra.listado.total")}            </th>
                                                <th>{$.i18n.prop("compra.listado.estado")}           </th>
                                                <th>Usuario       </th>
                                                <th>                                                 </th>
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


 <div class="box box-solid box-primary" show={mostrarCompra}>
        <div class="box-body">
             <div class="box-header with-border">
                 <h3 class="box-title">Modulo para Realizar Compras al Inventario {compra.id>0?' Id#:'+compra.id:'' }</h3>
                  <div class="box-tools pull-right">
                    <a href="#"    onclick = {__Regresar} title="Regresar al Listado"> <span class="label label-primary">Regresar</span></a>
                  </div>
             </div>
            <div  class="contenedor-compra " >
                <div class="cabecera-izquierda">
                    <form id="formularioCompra">
                        <div class="row">
                            <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3">
                                <div class="form-group ">
                                    <input   type="hidden" class="form-control" id="proveedor" name="proveedor" value="{proveedor.id}">
                                    <label>{$.i18n.prop("compra.proveedor")}</label> 
                                    <input  type="text"  class="form-control"  value="{compra.proveedor.nombreCompleto}" readonly>
                                </div>
                            </div>
                            <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.consecutivo")}</label> 
                                    <input type="text" class="form-control "  value="{compra.consecutivo}" readonly>
                                </div>
                            </div>
                            <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.fecha.compra")}</label> 
                                    <input type="text" class="form-control "  value="{compra.fechaCompra}" readonly>
                                </div>
                            </div>
                            <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3">
                                <div class="form-group ">
                                    <label>Total</label> 
                                    <input type="text" class="form-control "  value="{compra.totalCompraSTR}" readonly>
                                </div>
                            </div>

                        </div>    
                       
                    </form>
                   
                </div>
               
                      
            </div><!-- fin contenedor-compra-->
            
        </div><!-- fin box-body-->
	</div><!-- fin box -->


  <div class="row" show={mostrarCompra}  style="overflow: scroll;height: 400px;">        
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12" >
                             <div class="box">
                                        <div class="box-body">
                                            <div class="planel-body" > 
  
                    <table id="tableListarDetalle" class="table table-striped tableListarDetalle display table table-hover  table-condensed">
                        <thead>
                        <tr>
                            <th  style="width:4%;" class = "table-header" >{$.i18n.prop("compra.linea.detalle.linea")}                          </th>
                            <th  style="width:5%;" class = "table-header" >{$.i18n.prop("compra.linea.detalle.codigo")}                         </th>
                            <th  style="width:5%;" class = "table-header" >Codigo Inv                         </th>
                            <th  style="width:25%;"class = "table-header"  >{$.i18n.prop("compra.linea.detalle.descripcion")} </th>
                            <th  style="width:4%;" class = "table-header" >{$.i18n.prop("compra.linea.detalle.cantidad")}                      </th>
                            <th  style="width:5%;" class = "table-header" >Costo Compra                         </th>
                            <th  style="width:5%;" class = "table-header" >Costo Inv                          </th>
                            <th  style="width:5%;" class = "table-header" >Precio                         </th>
                            <th  style="width:5%;" class = "table-header" >Usuario                         </th>
                            <th  style="width:5%;" class = "table-header" >{$.i18n.prop("compra.descuento")}                     </th>
                            <th style="width:5%;" class = "table-header" >{$.i18n.prop("compra.linea.detalle.impuesto")}                      </th>
                            <th style="width:5%;" class = "table-header" >{$.i18n.prop("compra.linea.detalle.total")}                      </th>
                        </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("compra.linea.detalle.linea")}          </th>
                            <th>{$.i18n.prop("compra.linea.detalle.codigo")}     </th>
                            <th>Codigo Inv          </th>
                            <th>{$.i18n.prop("compra.linea.detalle.descripcion")}     </th>
                            <th>{$.i18n.prop("compra.linea.detalle.cantidad")}     </th>
                            <th>     </th>
                            <th>     </th>
                            <th>           </th>
                             <th>           </th>
                            <th > </th>
                            <th ></th>
                            <th ></th>
                              </tfoot>
                    </table>   
                    </div>
                     </div>
                      </div>
                    </div>
                    </div>


<style type ="text/css">
.table-header {
     background: #c2c5c5 !important;
     color: #000000!important;
 }
 .dataTables_wrapper .dataTables_filter input {
    margin-left: 1.5em !important;
    height: 30px !important;
    border-radius: 10px !important;
    font-size: 16px !important;
}
    .wrap{
        max-width:100%;
        width:100%;
    }
    body {
        
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
    .table-header {
    background: #3097D1;
    color: #ffffff;
}
.form-cantidad {
    display: block;
    width: 69px;
    height: 26px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgb(0 0 0 / 8%);
    box-shadow: inset 0 1px 1px rgb(0 0 0 / 8%);
    -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
.form-otros {
    display: block;
    width: 100px;
    height: 26px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgb(0 0 0 / 8%);
    box-shadow: inset 0 1px 1px rgb(0 0 0 / 8%);
    -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
.form-lineas {
    display: block;
    width: 60px;
    height: 26px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgb(0 0 0 / 8%);
    box-shadow: inset 0 1px 1px rgb(0 0 0 / 8%);
    -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
.costoStyle{
        background: red;
    padding-left: 20px;
    padding-right: 20px;
    font-weight: 800;
    color: white;
    font-size: 14px;
}

</style>

<script>
self = this
self.formato_tabla         = []
self.detail                = {data:[]} 
self.hay_datos             = false   
self.proveedores           = {data:[]}   
self.proveedor = {
    id:null
} 
self.compra                = {
        consecutivo:"",
        fechaCredito    : null,
        fechaCompra     : null,
        id : 0,
        totalImpuesto: 0,
        totalCompra:0,
        estado:0,
        tipoDocumento:0,
        formaPago:0,
        totalDescuento:0,
        total:0,
        nota:"",
        proveedor:{
            id:null,

        }
    }              
self.detalleCompra         ={data:[]}    
self.comboEstados          = []
self.mostrarCompra         = false
self.mostrarListado        = true
self.fechaInicio =null
self.fechaFin =null
self.idProveedor =0
self.on('mount',function(){
    $("#filtros").validate(reglasDeValidacion());
    __InformacionDataTable()
    __InformacionDataTableDetalle()
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
   __ComboEstados()
    __ListaProveedores()
    $('.datepickerFechaFinal').datepicker(
            {
              format: 'yyyy-mm-dd',
              todayHighlight:true,
            }
    );
    $('.datepickerFechaInicio').datepicker(
            {
              format: 'yyyy-mm-dd',
              todayHighlight:true,
            }
    );
    

})
 /**
*  Crear el combo de estados
**/
function __ComboEstados(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: 2,
        descripcion:$.i18n.prop("compra.estado.ingreso.inventario")
     });
    self.estados.push({
        codigo: 4,
        descripcion:$.i18n.prop("compra.estado.anulado")
     });
  
    self.update();
}
 __regresarAlListado(){
     self.mostrarCompra         = false
     self.mostrarListado        = true
     self.update()
 }
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			fechaFin : {
				required : true,
			},
			fechaFin : {
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
    $('#fechaFin').val(null)
    $('#fechaFin').val(null)
}




/**
*  Busqueda de la informacion por rango de fechas
**/
__Busqueda(){
    self.fechaInicio =$('.fechaInicio').val()
    self.fechaFin    =$('.fechaFin').val()
    self.idProveedor =$('#idProveedor').val()
    self.hay_datos  = false
    self.total          = 0
    self.totalImpuestos = 0
    self.update()

     if ($("#filtros").valid()) {

       var formulario = $("#filtros").serialize();
        $("#tableListar").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListar')  
        $.ajax({
            url: "ListarComprasAjax",
            datatype: "json",
            data:formulario ,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable();
                    loadListar(".tableListar",idioma_espanol,self.formato_tabla,result.aaData)
                    agregarInputsCombos();
                    ActivarEventoFiltro(".tableListar")
                    __MostrarCompra()
                    self.hay_datos  = true
                    self.update()
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
*  Suma de totales de compras
**/
function TotalesGenerales(data){
    self.total          = 0
    self.totalImpuestos = 0
    self.update()
    for(var i in data) { 
        self.total      += data[i].totalCompra;
        self.totalImpuestos += data[i].totalImpuesto;
     }
     self.total = formatoDecimales(self.total,2)
     self.totalImpuestos = formatoDecimales(self.totalImpuestos,2)
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
*  Lista de los Proveedores
**/
function __ListaProveedores(){
    $.ajax({
        url: 'ListarProveedoresAjax.do',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
               self.proveedores.data = result.aaData
               self.update()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
/**
 * Regresar al listado
 * **/
__Regresar(){
    self.detail                = {data:[]} 
    self.compra                = {
            consecutivo:"",
            fechaCredito    : null,
            fechaCompra     : null,
            id : 0,
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

    self.mostrarCompra         = false
   self.mostrarListado        = true
   self.update()
}



  
/**
*Formato del listado 
**/
function __InformacionDataTable(){
    self.formato_tabla = [ 
                               {'data' :'proveedor'   ,"name":"proveedor"    ,"title" : $.i18n.prop("compra.listado.proveedor")     ,"autoWidth" :true ,
                                "render":function(proveedor,type, row){
                                        return row.proveedor !=null?row.proveedor.nombreCompleto:"Sin Asociar";
                                    }
                               },
                               {'data' :'consecutivo'                ,"name":"consecutivo"                 ,"title" : $.i18n.prop("compra.listado.consecutivo")   ,"autoWidth" :true },
                               {'data' :'descripcionFormaPago'       ,"name":"descripcionFormaPago"        ,"title" : $.i18n.prop("compra.listado.formaPago")     ,"autoWidth" :true },
                               {'data' :'fechaCompra'                ,"name":"fechaCompra"                 ,"title" : $.i18n.prop("compra.listado.fecha.compra")  ,"autoWidth" :true ,
                                    "render":function(fechaCompra,type, row){
									    return __displayDate_detail(fechaCompra);
	 							    }
                               },
                               {'data' :'fechaIngreso'               ,"name":"fechaIngreso"                ,"title" : $.i18n.prop("compra.listado.fecha.ingreso") ,"autoWidth" :true ,
                                    "render":function(fechaIngreso,type, row){
									    return fechaIngreso !=null?formatoFechaHora(fechaIngreso):null;
	 							    }
                               
                               },
                               {'data' :'fechaCredito'               ,"name":"fechaCredito"                ,"title" : $.i18n.prop("compra.listado.fecha.credito") ,"autoWidth" :true ,
                                    "render":function(fechaCredito,type, row){
									    return fechaCredito !=null? __displayDate_detail(fechaCredito):null;
	 							    }
                               
                               },
                               
                               {'data' :'totalCompraSTR'             ,"name":"totalCompraSTR"              ,"title" : $.i18n.prop("compra.listado.total")         ,"autoWidth" :true },
                               {'data' :'descripcionEstado'          ,"name":"descripcionEstado"           ,"title" : $.i18n.prop("compra.listado.estado")        ,"autoWidth" :true },
                               {'data' :'usuarioIngresoInventario.nombreUsuario'   ,"name":"usuarioIngresoInventario.nombreUsuario"    ,"title" : "Usuario"    ,"autoWidth" :true },
                               {'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}

                           
/**
*Formato del listado Detalle
**/
function __InformacionDataTableDetalle(){
    self.formato_tabla_Detalle = [ 
                               {'data' :'numeroLinea'   ,"name":"numeroLinea"    ,"title" : 'Linea'    ,"autoWidth" :true },
                               {'data' :'codigo'                ,"name":"codigo"                 ,"title" : 'Codigo'   ,"autoWidth" :true },
                               {'data' :'codigoArticulo'       ,"name":"codigoArticulo"        ,"title" : 'Codigo Inv'     ,"autoWidth" :true 
                               },
                               {'data' :'descripcion'                ,"name":"descripcion" ,"title" : "Descripcion"  ,"autoWidth" :true ,
                                    "render":function(descripcion,type, row){
									    return descripcion.length > 30?descripcion.substring(0, 30):descripcion;
	 							    }
                               },
                               {'data' :'cantidad'               ,"name":"cantidad"     ,"title" : 'Cant' ,"autoWidth" :true },
                               {'data' :'costoCompra'               ,"name":"costoCompra"     ,"title" : 'Costo Compra' ,"autoWidth" :true },
                               {'data' :'costo'               ,"name":"costo"     ,"title" : 'Costo Inv' ,"autoWidth" :true ,
                                    "render":function(costo,type, row){
									    return row.precio != null?row.costoT >= row.precio?'<span class="costoStyle">'+costo+'</span>':costo:costo;
	 							    }
                               
                               },
                               {'data' :'precio'               ,"name":"precio"                ,"title" : 'Precio' ,"autoWidth" :true ,
                                    "render":function(precio,type, row){
									    return precio != null?row.costoT >= precio?'<span class="costoStyle">'+precio+'</span>':row.precio:'';
	 							    }

                               },
                               
                               {'data' :'usuario',"name":"usuario" ,"title" : 'Usuario'      ,"autoWidth" :true 

                               },
                               {'data' :'totalDescuento'          ,"name":"totalDescuento" ,"title" : 'Desc'        ,"autoWidth" :true },
                               {'data' :'totalImpuesto'          ,"name":"totalImpuesto" ,"title" : 'IVA'        ,"autoWidth" :true },
                            {'data' :'montoTotalLinea'          ,"name":"montoTotalLinea" ,"title" : 'Total '        ,"autoWidth" :true },

	      		            ];
    self.update();
   
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
     menu += "</ul></div>"  

     return menu;          
}


function __MostrarCompra(){
	$('#tableListar').on('click','.btnMostrar',function(e){
        var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        
        self.compra  = data;
        self.compra.fechaPago = self.compra.fechaPago !=null?__displayDate_detail(self.compra.fechaPago):null;
        self.compra.fechaCredito = self.compra.fechaCredito !=null?__displayDate_detail(self.compra.fechaCredito):null;
        self.compra.fechaCompra = self.compra.fechaCompra !=null?__displayDate_detail(self.compra.fechaCompra):null;
        self.compra.fechaIngreso  =self.compra.fechaIngresoSTR !=null?self.compra.fechaIngresoSTR:null;
        self.update()
        __ListaDetallesCompras(data.id)
        //cargarDetallesCompra()
	});
}

/**
*  Lista de los Proveedores
**/
function __ListaDetallesCompras(id){
    __InicializarTabla('.tableListarDetalle')
    $.ajax({
        url: 'ListarDetlleByCompraAjax.do',
        datatype: "json",
          data:{idCompra:id} ,
        method:"POST",
        success: function (result) {
            if(result.aaData.length > 0){
               self.compra.detalleCompras = result.aaData
               self.update()
               cargarDetallesCompra(result.aaData)
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

/**
*  Cargar detalles Compra Espera
**/
function cargarDetallesCompra(data){
      
    self.detail = {data:[]} 
    self.compra.detalleCompras.forEach(function(e){
        self.detail.data.push({
            numeroLinea     : e.numeroLinea,
            pesoPrioridad    :e.numeroLinea,
            articulo_id     : e.articulo !=null?e.articulo.id:null,
            codigo          : e.codigo,
            codigoArticulo          : e.articulo != null?e.articulo.codigo:'',
            descripcion     : e.descripcion,
            cantidad        : e.cantidadSTR,
            costo           : e.costoInventarioSTR,
            costoCompra     : e.costoSTR,
            costoT           : e.costoInventario,
            impuesto        : e.impuesto,
            descuento       : e.descuento,
            totalImpuesto   : e.totalImpuestoSTR,
            totalDescuento  : e.totalDescuentoSTR,
            montoTotalLinea : e.montoTotalLineaSTR,
            usuario         : e.usuarioActualizacion != null?e.usuarioActualizacion.nombreUsuario :'' ,
            precio          : e.articulo !=null?e.articulo.precioPublico:null,
        });
    })
    self.mostrarCompra         = true
    self.mostrarListado        = false
   
    self.update()
    __InformacionDataTableDetalle();
    loadListar(".tableListarDetalle",idioma_espanol,self.formato_tabla_Detalle,self.detail.data)
    agregarInputsCombos_detalle();
    ActivarEventoFiltro(".tableListarDetalle")
    
    
}
function __InicializarTabla(nombreTabla){
	$(nombreTabla).dataTable().fnClearTable();
	$(nombreTabla).DataTable().destroy();
    $(nombreTabla).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lfrtip',
        "order": [0, 'asc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        
    });
	var table = $(nombreTabla).DataTable();
 	table
    .clear()
    .draw();

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

function agregarInputsCombos_detalle(){
     // Agregar los input de busqueda 
    $('.tableListarDetalle tfoot th').each( function (e) {
        var title = $('.v thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if (   $(this).index() == 1  ||   $(this).index() == 2  ||  $(this).index() == 3    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-otros"  placeholder="'+title+'" />' );
        }
        if ( $(this).index() == 4 ){
            $(this).html( '<input id = "filtroCampos" type="text" class="form-cantidad"  placeholder="'+title+'" />' );
        }
        if ( $(this).index() == 0 ){
            $(this).html( '<input id = "filtroCampos" type="text" class="form-lineas"  placeholder="'+title+'" />' );
        }
        
    })
} 

</script>
</lista-compras>