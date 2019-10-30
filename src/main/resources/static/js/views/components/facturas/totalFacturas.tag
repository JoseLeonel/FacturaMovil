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
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="knob-label" >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                    <div  class="form-group input-group date datepickerFechaInicio" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
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
                                        <div  class="form-group input-group date datepickerFechaFinal" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
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
                                    <label>Estado </label>  
                                    <select  class="form-control selectEstado estado" id= "estado" name="estado" >
                                        <option value="6">{$.i18n.prop("estado.factura.aceptada")}</option>
                                        <option value="2">{$.i18n.prop("estado.factura.facturada")}</option>
                                    	<option  value="7">{$.i18n.prop("estado.factura.rechazada")}</option>
                                    </select>
                                </div>  
                            </div> 
							 <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>Actividad Economica </label>  
                                    <select  onchange= {__AsignarActividad} class="form-control selectActividadEconocimica" id= "actividadEconomica" name="actividadEconomica" >
										 <option    value="0"  >{$.i18n.prop("todos.select")}</option>
										<option  each={empresaActividadComercial}  value="{codigo}"   >{descripcion}</option>
                                    </select>
                                </div>  
                            </div>                                                    
                        </div>
                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
     			<a show ="{mostrarBotones == true?true:false}" class="fa fa-download" target="_blank" title="Descargar detalle transacciones" href="DescargarDetalleTotalFacturasAjax.do?fechaInicioParam={fechaInicio}&fechaFinParam={fechaFin}&estado={estado}&actividadEconomica={actividadEconomica}"> Descargar</a>        
		        <button show ="{mostrarBotones == true?true:false}" onclick ={__EnviarCorreoEmpresa}   type="button" class="btn btn-success btnBusquedaAvanzada" title="Enviar corre de la empresa" name="button"> Empresa  <i class="fa fa-envelope"></i></button>
		        <button show ="{mostrarBotones == true?true:false}" onclick ={__CorreoAlternativo} type="button" class="btn btn-success btnBusquedaAvanzada" title="Correo alternativo" name="button" >  Alternativo  <i class="fa fa-envelope"></i></button>

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
				    <h1>Ventas </h1>
                    <form id = "formularioDetalle" name="formularioDetalle" class="advanced-search-form">
                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalVentasGravadas")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalVentasGravadasSTR}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalVentasExentas")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalVentasExentasSTR}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalVentasNetas")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalVentasNetasSTR}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalImpuestos")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalImpuestosSTR}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalDescuentos")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalDescuentosSTR}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.total.otros.cargos")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalOtrosCargosSTR}">
                            </div>

                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.total")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalSTR}">
                            </div>
							<div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> Total Ventas - Notas de Credito  </label>
                                <input type="text" readonly="readonly" class="form-control "   value="{factura.totalGeneralSTR}">
                            </div>
                        </div>
                	</form>
                </div>
            </div>   
	        <div class="col-md-12 col-lg-12 col-sm-12">
		        </div>
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>

<div id="formularioDetalle" class="row center" show={mostrarDetalle} >
    	<div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-body">
				    <h1>Ventas </h1>  
                    <form id = "formularioDetalle" name="formularioDetalle" class="advanced-search-form">
                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.resumen.efectivo")}  </label>
                                <input type="text" readonly="readonly" class="form-control " value="{factura.totalEfectivoSTR}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.resumen.tarjeta")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalTarjetaSTR}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.resumen.banco")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalBancoSTR}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.resumen.total.credito")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalCreditoSTR}">
                            </div>
                            

                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.total")}  </label>
                                <input type="text" readonly="readonly" class="form-control "   value="{factura.totalPagosSTR}">
                            </div>
							
                        </div>
                	</form>
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>

<div id="formularioDetalle" class="row center" show={mostrarDetalle} >
    	<div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-body">
				    <h1>Notas de Credito </h1>
                    <form id = "formularioDetalle" name="formularioDetalle" class="advanced-search-form">
                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalVentasGravadas")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalVentasGravadasNC}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalVentasExentas")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalVentasExentasNC}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalVentasNetas")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalVentasNetasNC}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalImpuestos")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalImpuestosNC}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.totalDescuentos")}  </label>
                                <input type="text" readonly="readonly" class="form-control " value="{factura.totalDescuentosNC}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.total.otros.cargos")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalOtrosCargosNC}">
                            </div>

                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.total")}  <span class="requeridoDato">*</span></label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalNC}">
                            </div>
                        </div>
                	</form>
                </div>
            </div>   
	        <div class="col-md-12 col-lg-12 col-sm-12">
		        </div>
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>

<div id="formularioDetalle" class="row center" show={mostrarDetalle} >
    	<div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-body">
				    <h1>Notas de Credito </h1>  
                    <form id = "formularioDetalle" name="formularioDetalle" class="advanced-search-form">
                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.resumen.efectivo")}  </label>
                                <input type="text" readonly="readonly" class="form-control "   value="{factura.totalEfectivoNC}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.resumen.tarjeta")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalTarjetaNC}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.resumen.banco")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalBancoNC}">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.resumen.total.credito")}  </label>
                                <input type="text" readonly="readonly" class="form-control "  value="{factura.totalCreditoNC}">
                            </div>
                            

                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label> {$.i18n.prop("factura.total")}  </label>
                                <input type="text" readonly="readonly" class="form-control totalPagos"   value="{factura.totalPagosNC}">
                            </div>
                        </div>
                	</form>
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
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
				totalOtrosCargos:"0",
				totalEfectivo:"0",
				totalTarjeta:"0",
				totalBanco:"0",
				totalPagos:"0",
				totalCredito:"0"
		}
		self.mostrarBotones=false
		self.actividadEconomica = ""
		self.estado = 0
		self.fechaInicio="";
		self.fechaFin="";
		//Se cargan al montar el tag
		self.on('mount',function(){
		    $("#filtros").validate(reglasDeValidacion());
		    $("#formulario").validate(reglasDeValidacionCorreo());	
		    limpiarFactura();
			__ListaActividadesComercales()
		})
__AsignarActividad(e){
    BuscarActividadComercial()
}

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
		totalOtrosCargos:"0",
		totalEfectivo:"0",
		totalTarjeta:"0",
	    totalBanco:"0",
		totalPagos:"0",
		totalCredito:"0"
	}		
	self.update();
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

		}
		
		/**
		*  Busqueda de la informacion por rango de fechas
		**/
		__Busqueda(){
			limpiarFactura();
		    self.fechaInicio = $('#fechaInicial').val();
		    self.fechaFin = $('#fechaFinal').val();
			self.estado = $('.estado').val()
			self.mostrarBotones=false
		    self.update();
		    
		    if ($("#filtros").valid()) {
		        var parametros = {
		        	fechaInicioParam:$('#fechaInicial').val(),
		        	fechaFinParam:$('#fechaFinal').val(),
					estado:$('.estado').val(),
					actividadEconomica:$('.selectActividadEconocimica').val()
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
						//if(self.factura.total >0){
                          self.mostrarBotones=true
						  self.actividadEconomica =   $('.selectActividadEconocimica').val()
						//}
						//Se presentan los filtros
		               self.mostrarFiltros = true;
		               self.valorMarginBottom  = '0px'

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
		function __EnviarPorCorreo(){
		    if ($("#filtros").valid()) {
		        var parametros = {
		        	correoAlternativo:$('#correoAlternativo').val(),		
		        	fechaInicioParam:$('#fechaInicial').val(),
		        	fechaFinParam:$('#fechaFinal').val(),
					estado:$('.estado').val(),
					actividadEconomica:$('.selectActividadEconocimica').val()
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

		
		
		__CorreoAlternativo(){
			$('#correoAlternativo').val(null);
		    $('#ModalCorreoAlternativo').modal({
			    backdrop: 'static',
		        keyboard: false
		    });
		    $('#ModalCorreoAlternativo').modal('show');      
		}

		/**
		* Enviar el correo
		**/
		__EnviarCorreoAlternativo(){
		     if ($("#formulario").valid()) {
		    	 __EnviarPorCorreo()
		     }
		}
		
		__EnviarCorreoEmpresa(){
		   	 __EnviarPorCorreo()
		}
		
		
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
		
	</script>
</total-facturas>
