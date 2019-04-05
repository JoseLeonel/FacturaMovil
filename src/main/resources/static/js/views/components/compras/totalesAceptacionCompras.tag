<totales-compras>
   <!-- Titulos -->
    <div  class="row "  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("compras.comprasAceptas")} </h1>
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
                        </div>
                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
                <a   show={hay_datos==true} class=" btn btn-primary btn-bajar"  target="_blank" title="Descargar detalle transacciones" href="DescargarComprasAceptadasAjax.do?fechaInicioParam={fechaInicio}&fechaFinParam={fechaFin}&cedulaEmisor={cedula}"> Descargar</a>        
                        
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
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
                                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label  >Impuestos </label>
                                        <input type="text" class="form-control totalImpuestos" value="{totalImpuestos}" readonly>
                                    </div>  
                                </div>                             
                                <div class="col-xs-12 col-sm-4 col-md-3 col-lg-4">
                                    <div class="form-group">
                                        <label  >{$.i18n.prop("compra.listado.total")} </label>
                                        <input type="text" class="form-control totalCompra " value="{total}" readonly>
                                    </div>  
                                </div>                             
                            </div>

                        </div>    
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Fin del Listado -->
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
</style>
<script>
    self = this
    self.hay_datos             = false   
    self.mostrarListado        = true
    self.fechaInicio =null
    self.fechaFin =null
    self.on('mount',function(){
        $("#filtros").validate(reglasDeValidacion());
    
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
    self.hay_datos= false
    self.hay_datos  = false
    self.total          = 0
    self.totalImpuestos = 0
    self.update()
     if ($("#filtros").valid()) {
      var formulario = $("#filtros").serialize();
        $.ajax({
            url: "TotalComprasAceptadasAjax.do",
            datatype: "json",
            data:formulario ,
            method:"GET",
            success: function (result) {
                if(result.total > 0){
                    TotalesGenerales(result)

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
    self.total          = data.totalSTR
    self.totalImpuestos = data.totalImpuestoSTR
    self.hay_datos=true
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
</script>
</totales-compras>