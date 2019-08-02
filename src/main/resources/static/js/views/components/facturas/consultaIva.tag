<consulta-iva>
   <!-- Titulos -->
    
	<div class="principalContainer">
	   <div class="encabezado">
			<div class="encabezado1">
			</div>
			<div class="encabezado2">
				<p>Contribuye con ventas de tarifa plana, reducida, canasta basica (1%) y excentas y compras con tarifas
					plenas y reducidas</p>	
			</div>
			<div class="encabezado3">
			</div>
	   </div>
	   <div class="reportes">
			<div class="ventas">

               <table id="tablaVentas">
			      <tr>
				     <td class="celeste"></td>
					 <td class="celeste">Monto Venta</td>
					 <td class="celeste">Monto IVA</td>
				  </tr>
				  <tr>
				     <td class="azul">Ventas al 13%</td>
					 <td class="azul">150.0000</td>
					 <td class="azul">160.0000</td>
				  </tr>
				  <tr>
				     <td class="celeste">Ventas al 4%</td>
					 <td class="celeste">195.0000</td>
					 <td class="celeste">200.0000</td>
				  </tr>
				  <tr>
				     <td class="azul">Ventas al 1%</td>
					 <td class="azul">146.0000</td>
					 <td class="azul">752.0000</td>
				  </tr>
				  <tr>
				     <td class="celeste">Ventas excentas</td>
					 <td class="celeste">199.0000</td>
					 <td class="celeste">234.0000</td>
				  </tr>
				  <tr>
				     <td class="azul">Total</td>
					 <td class="azul">190.0000</td>
					 <td class="azul">124.0000</td>
				  </tr>
			   </table>
			</div>
			<div class="separador">
			</div>
			<div class="compras">
				<div class="comprasInfo">
               <table id="tablaCompras">
			      <tr>
				     <td class="celeste"></td>
					 <td class="celeste">Monto Venta</td>
					 <td class="celeste">Monto IVA</td>
				  </tr>
				  <tr>
				     <td class="azul">Compras al 2%</td>
					 <td class="azul">150.0000</td>
					 <td class="azul">160.0000</td>
				  </tr>
				  <tr>
				     <td class="celeste">Compras 4%</td>
					 <td class="celeste">195.0000</td>
					 <td class="celeste">200.0000</td>
				  </tr>
				  <tr>
				     <td class="azul">Compras al 13%</td>
					 <td class="azul">146.0000</td>
					 <td class="azul">752.0000</td>
				  </tr>
				  <tr>
				     <td class="celeste">Total</td>
					 <td class="celeste">199.0000</td>
					 <td class="celeste">234.0000</td>
				  </tr>

			   </table>

			   </div>
			   <div class="comprasVacio">
			   </div>
			</div>
	   </div>
	   <br/>
	   <br/>
	   <div class="formulas">
             <div class="formulasRotulo">
				<div class="formulasRotuloFila1">
					<div class="formulasRotuloFila1Col1"></div>
					<div class="formulasRotuloFila1Col2"></div>
					<div class="formulasRotuloFila1Col3"></div>
				</div>
				<div class="formulasRotuloFila2">
					<div class="formulasRotuloFila2Col1">Formula<br/>de<br/>Proporcion</div>
					<div class="formulasRotuloFila2Col2"></div>
					<div class="formulasRotuloFila2Col3">Ventas<br/>con<br/>derecho a<br/>credito</div>
				</div>
				<div class="formulasRotuloFila3">
					<div class="formulasRotuloFila3Col1"></div>
					<div class="formulasRotuloFila3Col2"></div>
					<div class="formulasRotuloFila3Col3"></div>
				</div>
			 </div>
			 <div class="formulasInfo">
				<table id="tablaInfo" border="1">
					<tr>
						<td rowspan="2">Ventas al 13%</td>
						<td>15.000</td>
						<td rowspan="2">15.79%</td>
					</tr>
					<tr>
						<td>95.000</td>
					</tr>
					<tr>
						<td class="azul"></td>
						<td class="azul"></td>
						<td class="azul"></td>
					</tr>
					<tr>
						<td rowspan="2">Ventas al 4%</td>
						<td>20000</td>
						<td rowspan="2">21.05%</td>
					</tr>
					<tr>
						<td>900000</td>
					</tr>
					<tr>
						<td class="azul"></td>
						<td class="azul"></td>
						<td class="azul"></td>
					</tr>
					<tr>
						<td rowspan="2">Ventaas al 1%</td>
						<td>400000</td>
						<td rowspan="2">42.11%</td>
					</tr>
					<tr>
						<td>950000</td>
					</tr>
					<tr>
						<td class="azul"></td>
						<td class="azul"></td>
						<td class="azul"></td>
					</tr>
						<tr>
						<td rowspan="2">Ventas excentas</td>
						<td>20000</td>
						<td rowspan="2">21.11%</td>
					</tr>
					<tr>
						<td>95000</td>
					</tr>
					<tr>
						<td class="azul"></td>
						<td class="azul"></td>
						<td class="azul"></td>
					</tr>
					<tr>
						<td>Total</td>
						<td></td>
						<td>100%</td>
					</tr>
				</table>

			 </div>
			 <div class="formulasVacio">
			 </div>
	   </div>
    </div>

	<style type="text/css"  >
       .principalContainer{
            display: flex;
			flex-direction: column;
			background-color: white;
			padding-top: 10px;
			padding-right: 10px;
			padding-bottom: 10px;
			padding-left: 10px;
	   }
	   .encabezado{
		   display: flex;
	       flex: 0.20; 
		   flex-direction: row;
	   }
	   .reportes{
		   display: flex;
	       flex: 0.40; 
		   flex-direction: row;
	   }
	   .formulas{
		   display: flex;
	       flex: 0.40; 
		   flex-direction: row;
	   }
	   .ventas{
		   display: flex;
	       flex: 0.40; 
	   }
       .separador{
		   display: flex;
	       flex: 0.20; 
	   }
	   .compras{
		   display: flex;
	       flex: 0.40; 
		   flex-direction: column;
	   }
	   .comprasInfo{
           display: flex;
	       flex: 0.80; 
	   }
	   .comprasVacio{
           display: flex;
	       flex: 0.20;
	   }
	   .formulasRotulo{ 
		   display: flex;
	       flex: 0.30; 
		   flex-direction: column;
	   }
	   .formulasInfo{
           display: flex;
	       flex: 0.50; 

	    }
	   .formulasVacio{ 
           display: flex;	
	       flex: 0.20; 
	   }
		.formulasRotuloFila1{
			display: flex;
			flex: 0.30; 
			flex-direction: row;
		}
		.formulasRotuloFila1Col1{
				display: flex;
				flex: 0.33; 
		}
		.formulasRotuloFila1Col2{
				display: flex;
				flex: 0.34; 
		}
		.formulasRotuloFila1Col3{
				display: flex;
				flex: 0.33; 
		}

		.formulasRotuloFila2{
			display: flex;
			flex: 0.40; 
			flex-direction: row;
		}
		.formulasRotuloFila2Col1{
				display: flex;
				flex: 0.33; 
		}
		.formulasRotuloFila2Col2{
				display: flex;
				flex: 0.34; 
		}
		.formulasRotuloFila2Col3{
				display: flex;
				flex: 0.33; 
		}

		.formulasRotuloFila3{
			display: flex;
			flex: 0.30; 
			flex-direction: row;
		}
		.formulasRotuloFila3Col1{
				display: flex;
				flex: 0.33; 
		}
		.formulasRotuloFila3Col2{
				display: flex;
				flex: 0.34; 
		}
		.formulasRotuloFila3Col3{
				display: flex;
				flex: 0.33; 
		}

		.encabezado1{
				display: flex;
				flex: 0.20; 
		}
		.encabezado2{
				display: flex;
				flex: 0.60; 
				justify-content: center;
		}
		.encabezado3{
				display: flex;
				flex: 0.20; 
		}

       .ventasFila1{
			display: flex;
			flex: 0.33; 
			flex-direction: column;
           justify-items: center;
	   }

       .ventasFila2{
			display: flex;
			flex: 0.33; 
			flex-direction: column;
           justify-items: center;
	   }

       .ventasFila3{
			display: flex;
			flex: 0.34; 
			flex-direction: column;
			    flex: 0.40;
            justify-items: center;
	   }

	   	.comprasInfoFila1{
			display: flex;
			flex: 0.33; 
			flex-direction: column;
			justify-content: center;
		   }

		.comprasInfoFila2{
			display: flex;
			flex: 0.33; 
			flex-direction: column;
			justify-content: center;
		}

		.comprasInfoFila3{
			display: flex;
			flex: 0.34; 
			flex-direction: column;
			justify-content: center;
		}

		.azul{
            background-color: #6495ED;
		}

		.celeste{
            background-color: #00FFFF;
		}

/*----------------------------------------------------------------------------------------*/

		#tablaVentas {
			font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
			border-collapse: collapse;
			width: 100%;
		}

		#tablaVentas td, #tablaVentas th {
			border: 1px solid #ddd;
			padding: 8px;
		}

		#tablaVentas th {
			padding-top: 12px;
			padding-bottom: 12px;
			text-align: left;
			color: white;
		}

/*----------------------------------------------------------------------------------*/

		#tablaCompras {
			font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
			border-collapse: collapse;
			width: 100%;
		}

		#tablaCompras td, #tablaCompras th {
			border: 1px solid #ddd;
			padding: 8px;
		}

		#tablaCompras th {
			padding-top: 12px;
			padding-bottom: 12px;
			text-align: left;
			background-color: #4CAF50;
			color: white;
		}

/*----------------------------------------------------------------------------------*/

		#tablaInfo {
			font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
			border-collapse: collapse;
			width: 100%;
		}

		#tablaInfo td, #tablaInfo th {
			border: 1px solid #ddd;
			padding: 8px;
		}

		#tablaInfo th {
			padding-top: 12px;
			padding-bottom: 12px;
			text-align: left;
			background-color: #4CAF50;
			color: white;
		}





		.formulasRotuloFila2Col2:before, .formulasRotuloFila2Col2:after {
		border-bottom: 25px solid transparent;
		border-top: 25px solid transparent;
		top:-1px;
		}
		.formulasRotuloFila2Col2:after {
		border-left: 16px solid #C7E0E5;
		right: -15px;
		z-index: 2;
		}
		
		.formulasRotuloFila2Col2:before {
		border-left: 16px solid #0cceff;
		right: -16px;
		z-index: 1;
		}


	</style> 

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
		    $("#formulario").validate(reglasDeValidacionCorreo());	
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
		function __EnviarPorCorreo(){
		    if ($("#filtros").valid()) {
		        var parametros = {
		        	correoAlternativo:$('#correoAlternativo').val(),		
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
</consulta-iva>
