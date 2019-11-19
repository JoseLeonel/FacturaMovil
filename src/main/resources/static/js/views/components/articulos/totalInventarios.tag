<total-inventarios>
   <!-- Titulos -->
    <div class="row">
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("titulo.inventario.totales")} </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
        </div>
    </div>
    <br>

    
    <!-- Inicio Filtros-->
    <div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;">
                <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
            </div>  
            <div class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
              <form id="filtros" name="filtros">       
                <div class= "row">
                    <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label class="knob-label" >Fecha <span class="requeridoDato">*</span></label>
                            <div  class="form-group input-group date datepickerFechaInicial" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                <input type="text" class="form-control fechaInicial " id="fechaInicial"  name= "fechaInicial" readonly>
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-th"></span>
                                </div>
                            </div>	                             
                        </div>  
                    </div>             
                </div>    


              </form>
            </div>
        </div>      
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-right">
            <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada formatoBoton" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
        </div>
    </div>    
	<!-- Fin Filtros-->
  	<!-- Detalle  -->
	<div id="formularioDetalle" class="row center"  >
    	<div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-body">
                    <form id = "formularioDetalle" name="formularioDetalle" class="advanced-search-form">
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> Total Costo  </label>
                                <input type="text" readonly="readonly" class="form-control" value="{inventario.totalCostoSTR}">
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> Total Venta Publico  </label>
                                <input type="text" readonly="readonly" class="form-control"  value="{inventario.totalPublicoSTR}">
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> Total Venta Especial  </label>
                                <input type="text" readonly="readonly" class="form-control" value="{inventario.totalEspecialSTR}">
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> Total Venta Mayorista </label>
                                <input type="text" readonly="readonly" class="form-control"  value="{inventario.totalMayoristaSTR}">
                            </div>

                        </div>
                	</form>
                </div>
            </div>   
	        <div class="col-xs-12 col-md-12 col-lg-12 col-sm-12" show={mostrarDescarga == true}>
				<a show = {mostrarDescargas == true} class="fa fa-download btn btn-success" target="_blank" title="Descargar el inventario existente" href="DescargarInventarioAjax.do"> Todo el Inventario</a> &nbsp       
				<a show = {mostrarDescargas == true} class="fa fa-download btn btn-primary" target="_blank" title="Descargar el inventario con las existencias para contabilizar contra el inventario fisico" href="DescargarInventarioExistenciasAjax.do"> Inventario con Existencias</a>        
	        </div>
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>
<style type="text/css">
  .formatoBoton{
      margin:2%;
  }
</style>

<script>
	self = this;
	self.mostrarDetalle        = false
	self.inventario ={				
		totalCostoSTR:"0",
		totalVentasSTR:"0",
	}
    self.valorMarginBottom  = '10px'		

	self.mostrarDescarga = false
		self.empresa = {
			id:0
	}
    self.mostrarDescargas = false
	//Se cargan al montar el tag
	self.on('mount',function(){
         $("#filtros").validate(reglasDeValidacion());
         $('.datepickerFechaInicial').datepicker(
        {
            format: 'yyyy-mm-dd',
            todayHighlight:true,
        }
        );
		_Empresa();
	})
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			fechaInicial : {
				required : true,
			},
		},ignore : []

	});
	return validationOptions;
};

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
*  Busqueda de la informacion por rango de fechas
**/
__Busqueda(){
    self.mostrarDescargas = true   
    self.update();
    var parametros = {
       	fechaInicio:$('#fechaInicial').val(),
    };
     if ($("#filtros").valid()) {
        $.ajax({
                url: "TotalInventarioAjax.do",
                datatype: "json",
                data:parametros ,
                method:"GET",
                success: function (data) {
                    self.inventario = data;
                    self.mostrarDescargas = true   
                    self.update();
                },
                error: function (xhr, status) {
                    console.log(xhr);
                    mensajeErrorServidor(xhr, status);
                }
        });
     }
}
/**
* Consultar la empresa
**/
function _Empresa(){
     $.ajax({
        url: "ParametrosEmpresaAjax.do",
        datatype: "json",
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.empresa =   modeloTabla
					   if (self.empresa.descargarInventario == 1) {
                          self.mostrarDescarga = true
					   }
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
		
	</script>
</total-inventarios>
