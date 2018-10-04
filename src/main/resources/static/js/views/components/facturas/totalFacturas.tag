<total-facturas>
   <!-- Titulos -->
    <div class="row">
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("total.facturas")} </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
        </div>
    </div>
    <br>
    <br>
    <br>    
    <!-- Inicio Filtros-->
    <div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-5 col-md-5 col-lg-5">
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
                            <div class="col-xs-12 col-sm-5 col-md-5 col-lg-5">
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
  	<!-- Detalle  -->
	<div id="formularioDetalle" class="row center" show={mostrarDetalle} >
    	<div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-body">
                    <form id = "formularioDetalle" name="formularioDetalle" class="advanced-search-form">
                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalVentasGravadas")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control totalVentasGravadas" placeHolder ="{$.i18n.prop("factura.totalVentasGravadas")}" id="totalVentasGravadas" name="totalVentasGravadas" value="{factura.totalVentasGravadas}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalVentasExentas")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control totalVentasExentas" placeHolder ="{$.i18n.prop("factura.totalVentasExentas")}" id="totalVentasExentas" name="totalVentasExentas" value="{factura.totalVentasExentas}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalVentasNetas")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control totalVentasNetas" placeHolder ="{$.i18n.prop("factura.totalVentasNetas")}" id="totalVentasNetas" name="totalVentasNetas" value="{factura.totalVentasNetas}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalImpuestos")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control totalImpuestos" placeHolder ="{$.i18n.prop("factura.totalImpuestos")}" id="totalImpuestos" name="totalImpuestos" value="{factura.totalImpuestos}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalDescuentos")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control totalDescuentos" placeHolder ="{$.i18n.prop("factura.totalDescuentos")}" id="totalDescuentos" name="totalDescuentos" value="{factura.totalDescuentos}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.total")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control totalDescuentos" placeHolder ="{$.i18n.prop("factura.total")}" id="total" name="total" value="{factura.total}">
                            </div>
                        </div>
                	</form>
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
        <div class="col-md-2 col-lg-2 col-sm-2">
	        <button onclick ={__EnviarPorCorreo} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-edit"></i></button>
			<a class="fa fa-print" target="_blank" title="Imprimir recibo" href="DescargarDetalleTotalFacturasAjax.do?fechaInicioParam={fechaInicio}&fechaFinParam={fechaFin}"></a>        
        </div>
    </div>

	<script>
	
		self = this;
	
		//Se presentan los filtros
		self.mostrarFiltros = true;
		self.valorMarginBottom  = '0px'

		self.mostrarDetalle        = false

		self.factura ={				
				total:"0",
				totalDescuentos:"0",
				totalImpuestos:"0",
				totalVentasNetas:"0",
				totalVentasExentas:"0",
				totalVentasGravadas:"0",
		}
		
		self.fechaInicio="";
		self.fechaFin="";
		

		//Se cargan al montar el tag
		self.on('mount',function(){
		    $("#filtros").validate(reglasDeValidacion());
		    limpiarFactura();
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
					fechaFinal : {
						required : true,
					}                                   
		                        
				},
				ignore : []
		
			});
			return validationOptions;
		};
		
		function limpiarFactura(){
			self.factura ={				
					total:"0",
					totalDescuentos:"0",
					totalImpuestos:"0",
					totalVentasNetas:"0",
					totalVentasExentas:"0",
					totalVentasGravadas:"0",
			}			
			self.update();
		}
		
		/**
		*  Busqueda de la informacion por rango de fechas
		**/
		__Busqueda(){
			limpiarFactura();
		    self.fechaInicio = $('#fechaInicial').val();
		    self.fechaFin = $('#fechaFin').val();
		    self.update();
		    
		    if ($("#filtros").valid()) {
		        var parametros = {
		        	fechaInicioParam:$('#fechaInicial').val(),
		        	fechaFinParam:$('#fechaFinal').val(),
		        };
		        $.ajax({
		            url: "TotalFacturasAjax.do",
		            datatype: "json",
		            data:parametros ,
		            method:"GET",
		            success: function (data) {
			        	self.factura = data;
			        	self.mostrarDetalle = true;
			        	self.mostrarFiltros = false;
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
		*  Busqueda de la informacion y la envia por correo
		**/
		__EnviarPorCorreo(){
		    if ($("#filtros").valid()) {
		        var parametros = {
		        	fechaInicioParam:$('#fechaInicial').val(),
		        	fechaFinParam:$('#fechaFinal').val(),
		        };
		        $.ajax({
		            url: "EnvioDetalleTotalFacturasAjax.do",
		            datatype: "json",
		            data:parametros ,
		            method:"POST",
		            success: function (data) {
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
		* limpiar los filtros
		**/
		__limpiarFiltros(){
		    $('#fechaInicial').val(null)
		    $('#fechaFinal').val(null)
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
</total-facturas>
