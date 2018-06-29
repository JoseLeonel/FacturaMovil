<mostrar-kardex>
 <!-- Titulos -->
    <div  class="row "  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("kardex.titulo")} </h1>
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
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
                                <div class="form-group">
                                    <label class="knob-label" >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                    <div  class="form-group input-group date" data-provide="datepicker"   data-date-format="dd/mm/yyyy">
                                        <input type="text" class="form-control fechaInicio" id="fechaInicio"  name= "fechaInicio" readonly>
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>	                             
                                </div>  
                            </div>             
                            <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label class="knob-label" >{$.i18n.prop("fecha.final")} <span class="requeridoDato">*</span></label>
                                        <div  class="form-group input-group date" data-provide="datepicker"   data-date-format="dd/mm/yyyy">
                                            <input type="text" class="form-control fechaFinal" id="fechaFinal"  name= "fechaFinal" readonly>
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>	                             
                                    </div>
                                </div>  
                            </div>
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-2">
                                <div class="form-group">
                                    <label>{$.i18n.prop("proveedor.titulo")} </label>  
                                    <select  class="form-control selectCliente" id="proveedor" name="proveedor" data-live-search="true">
                                        <option  data-tokens="{$.i18n.prop("todos.select")}"   value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option  data-tokens="{nombreCompleto}" each={proveedores.data}  value="{id}"  >{nombreCompleto}</option>
                                    </select>
                                </div>  
                            </div>                      
                        </div>
                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
                <a onclick ={__crearArchivoExcel} id="btnDownload" class="btn btn-success" title ="Descargar" > <i class="fa fa-download"></i></a>
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
                                                <th class = "table-header" >{$.i18n.prop("kardez.listado.fecha")}            </th>
                                                <th class = "table-header" >{$.i18n.prop("kardez.listado.tipo")}             </th>
                                                <th class = "table-header" >{$.i18n.prop("kardez.listado.cantidad.nueva")}   </th>
                                                <th class = "table-header" >{$.i18n.prop("kardez.listado.costo.nuevo")}      </th>
                                                <th class = "table-header" >{$.i18n.prop("kardez.listado.totalCosto.nuevo")} </th>
                                                <th class = "table-header" >{$.i18n.prop("kardez.listado.cantidad.nueva")}   </th>
                                                <th class = "table-header" >{$.i18n.prop("kardez.listado.costo.nuevo")}      </th>
                                                <th class = "table-header" >{$.i18n.prop("kardez.listado.totalCosto.nuevo")} </th>

                                            </tr>
                                        </thead>
                                        <tfoot style="display: table-header-group;">
                                            <tr>
                                                <th>{$.i18n.prop("kardez.listado.fecha")}            </th>
                                                <th>{$.i18n.prop("kardez.listado.tipo")}             </th>
                                                <th>{$.i18n.prop("kardez.listado.cantidad.nueva")}   </th>
                                                <th>{$.i18n.prop("kardez.listado.costo.nuevo")}      </th>
                                                <th>{$.i18n.prop("kardez.listado.totalCosto.nuevo")} </th>
                                                <th>{$.i18n.prop("kardez.listado.cantidad.nueva")}   </th>
                                                <th>{$.i18n.prop("kardez.listado.costo.nuevo")}      </th>
                                                <th>{$.i18n.prop("kardez.listado.totalCosto.nuevo")} </th>
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
<style type ="text/css">
        .fondoEncabezado {
            background: #00539B;
            color: #f9fafc;
        }
        .requeridoDato {
                color: red;
                text-align: left;
                font-weight: 500;
                font-size: 13px;
        }
        .fondoFacturacion {
            background: rgb(247, 244, 244);
            color: #f9fafc;
            border-style: solid;
            border-width: 5px;cliente
        }
        .wrap{
            max-width:1100px;
            width:100%;
        }
        body {
            overflow: hidden;
            background:white;
            font-size: 12px !important;
        }
        .contenedor-listar{
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
        th, td {
            white-space: nowrap;
        }

</style>

<script>
    var self = this
    self.idiomaDataTable      = []         // idioma de la datatable nuevo
    self.formato_tabla        = []         // Formato del Listado de la Tabla 
    self.kardex               = {data:[]}
    self.mostrarListado       =true

    self.on('mount',function(){
  
        window.addEventListener( "keydown", function(evento){
                $(".errorServerSideJgrid").remove();
            }, false );
    })
</script>

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

/*
 * Deja en blanco los valores de filtros avanzados
 */
__limpiarFiltros(){
	    $('#idRangoFechas').val(null)
	    $('#idUsuario').val(null)
	    $('#idNumeroOST').val(null)
	    $('#idServicioVoipVoz').val(null)
	    $('#idServicioInternet').val(null)
	    $('#idServicioIptv').val(null) 
}



</mostrar-kardex>