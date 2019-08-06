<consulta-iva>
   <!-- Titulos -->
    
	<div class="principalContainer">
	
	   <div class="encabezado">
			<div class="encabezado1">
			</div>
			<div class="encabezado2">
				<p class="titulo">Contribuye con ventas de tarifa plana, reducida, canasta basica (1%) y excentas y compras con tarifas
					plenas y reducidas 89</p>	
			</div>
			<div class="encabezado3">
			</div>
	   </div>
	   <br style="color: #0056b2;" />
	   <hr/>
	   <br/>
		<form id="filtros">
		
		<div class="consultas">
				<div class="consultarFecha">
					<div class="fechaInicio">
						<label class="knob-label" >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
						<div  class="form-group input-group date datepickerFechaInicio" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
							<input type="text" class="form-control fechaInicial" id="fechaInicial"  name= "fechaInicial" readonly>
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</div>
						</div>
					</div>
					<div class="espaciofechas">
					</div>
					<div class="fechaFinal">
						<label class="knob-label" >{$.i18n.prop("fecha.final")}<span class="requeridoDato">*</span></label>
						<div  class="form-group input-group date datepickerFechaFinal" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
							<input type="text" class="form-control fechaFinal" id="fechaFinal"  name= "fechaFinal" readonly>
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</div>
						</div>	
					</div>
				</div>
				<div class="espaciofechas">
				</div>	
				<div class="consultarEstado">   
					<label for="pago_tipoVentaL">{$.i18n.prop("factura.estado")} </label> 
					<select class="form-control estado" id="estado" name="estado"  >
						<option each={comboEstados} value="{estado}" selected="{factura.estado ==estado?true:false}" >{descripcion}</option>
					</select>
				</div>
				<div class="espaciofechas">
				</div>
				<div class="actividadEconomica">   
				<label class="titleListaPrecio">Actividades Comerciales </label>  
					<select class="form-control selectActividadComercial"  name="selectActividadComercial" id="selectActividadComercial" >
						<option  each={empresaActividadComercial}  value="{codigo}"   >{descripcion}</option>
					</select>
				</div>
				<div class="espaciofechas">
				</div>
				<div class="consultarBoton">	
					<button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
					<button onclick ={__limpiarFiltros} show={mostrarFiltros} class="btn btn-warning btnLimpiarFiltros" title="LimpiarCampos" type="button"><i id="clear-filters" class="fa fa-eraser clear-filters"></i></button>            
				</div>
		    
		</div>
		</form>	
		<br/>
	    <br/>
	   <div class="reportes">
			<div class="ventasUno">
               <table id="tablaVentasUno">
			      <caption>Impuesto al Valor Agregado</caption>
			      <tr>
				     <td></td>
					 <td><p class="formatos">Monto Venta</p></td>
					 <td><p class="formatos">Monto IVA</p></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa 0% (Exento)</p></td>
					 <td>1<div id="ventasUnoImpExcento0"></div><div/></td>
					 <td>2<div id="ventasUnoIvaExcento0"></div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa reducida 1%</p></td>
					 <td>3<div id="ventasUnoImpReducida1"></div></td>
					 <td>4<div id="ventasUnoIvaReducida1"></div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa reducida 2% </p></td>
					 <td>5<div id="ventasUnoImpReducida2"></div></td>
					 <td>6<div id="ventasUnoIvaReducida2"></div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa reducida 4%</p></td>
					 <td>7<div id="ventasUnoImpReducida4"></div></td>
					 <td>8<div id="ventasUnoIvaReducida4"></div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Transitorio 0%</p></td>
					 <td>11<div id="ventasUnoImpTransitorio0"></div></td>
					 <td>12<div id="ventasUnoIvaTransitorio0"></div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Transitorio 4%</p></td>
					 <td>15<div id="ventasUnoImpTransitorio4"></div></td>
					 <td>16<div id="ventasUnoIvaTransitorio4"></div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Transitorio 8% </p></td>
					 <td>19<div id="ventasUnoImpTransitorio8"></div></td>
					 <td>20<div id="ventasUnoIvaTransitorio8"></div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa general 13%</p></td>
					 <td>{totales.01_iva_13.montoImpuesto}<div id="ventasUnoImpGeneral13"></div></td>
					 <td>24<div id="ventasIvaGeneral13"></div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Total</p></td>
					 <td>25<div id="ventasUnoImpTotal"></div></td>
					 <td>26<div id="ventasUnoIvaTotal"></div></td>
				  </tr>
			   </table>
			</div>
				<div class="espaciofechas">
			</div>
			<div class="otros">
				<table id="tablaOtros">
				<caption>Otros</caption>
					<tr>
						<td></td>
						<td><p class="formatos">Monto Venta</p></td>
						<td><p class="formatos">Monto IVA</p></td>
					</tr>
					<tr>
						<td><p class="formatos">Selectivo de consumo</p></td>
						<td>1<div id="otrosImpExcento0"></div></td>
						<td>2<div id="otrosIvaExcento0"></div></td>
					</tr>
					<tr>
						<td><p class="formatos">Cemento</p></td>
						<td>3<div id="otrosImpReducida1"></div></td>
						<td>4<div id="otrosIvaReducida1"></div></td>
					</tr>
					<tr>
						<td><p class="formatos">Otros</p></td>
						<td>3<div id="otrosImpReducida1"></div></td>
						<td>4<div id="otrosIvaReducida1"></div></td>
					</tr>
					<tr>
						<td><p class="formatos">Total</p></td>
						<td>25<div id="otrosImpTotal"></div></td>
						<td>26<div id="otrosIvaTotal"></div></td>
					</tr>
				</table>
			</div>
			<div class="espaciofechas">
			</div>
			<div class="VentasSiete">
				<div class="ventasInfoSiete">
					<table id="tablaVentasSiete">
					<caption>IVA (calculo especial) </caption>
						<tr>
							<td></td>
							<td><p class="formatos">Monto Venta</p></td>
							<td><p class="formatos">Monto IVA</p></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa 0% (Exento)</p></td>
							<td>1<div id="ventasSieteImpExcento0"></div></td>
							<td>2<div id="ventasSieteIvaExcento0"></div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa reducida 1%</p></td>
							<td>3<div id="ventasSieteImpReducida1"></div></td>
							<td>4<div id="ventasSieteIvaReducida1"></div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa reducida 2% </p></td>
							<td>5<div id="ventasSieteImpReducida2"></div></td>
							<td>6<div id="ventasSieteIvaReducida2"></div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa reducida 4%</p></td>
							<td>7<div id="ventasSieteImpReducida4"></div></td>
							<td>8<div id="ventasSieteIvaReducida4"></div></td>
						</tr>
						<tr>
							<td><p class="formatos">Transitorio 0%</p></td>
							<td>11<div id="ventasSieteImpTransitorio0"></div></td>
							<td>12<div id="ventasSieteIvaTransitorio0"></div></td>
						</tr>
						<tr>
							<td><p class="formatos">Transitorio 4%</p></td>
							<td>15<div id="ventasSieteImpTransitorio4"></div></td>
							<td>16<div id="ventasSieteIvaTransitorio4"></div></td>
						</tr>
						<tr>
							<td><p class="formatos">Transitorio 8% </p></td>
							<td>19<div id="ventasSieteImpTransitorio8"></div></td>
							<td>20<div id="ventasSieteIvaTransitorio8"></div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa general 13%</p></td>
							<td>23<div id="ventasSieteImpGeneral13"></div></td>
							<td>24<div id="ventasSieteIvaGeneral13"></div></td>
						</tr>
						<tr>
							<td><p class="formatos">Total</p></td>
							<td>25<div id="ventasSieteImpTotal"></div></td>
							<td>26<div id="ventasSieteIvaTotal"></div></td>
						</tr>
					</table>
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
					<div class="formulasRotuloFila2Col1"><p class="formatos">Formula<br/>de<br/>Proporcion</p></div>
					<div class="formulasRotuloFila2Col2"></div>
					<div class="formulasRotuloFila2Col3"><p class="formatos">Ventas con<br/>derecho a<br/>credito</P></div>
				</div>
				<div class="formulasRotuloFila3">
					<div class="formulasRotuloFila3Col1"></div>
					<div class="formulasRotuloFila3Col2"><p class="formatos">Ventas sin derecho a credito</p></div>
					<div class="formulasRotuloFila3Col3"></div>
				</div>
			 </div>
			 <div class="formulasInfo">
				<table id="tablaInfo" border="1">
					<tr>
						<td rowspan="2"><p class="formatos">Ventas al 13%</p></td>
						<td>1<div id="info1"></td>
						<td rowspan="2">15.79%</td>
					</tr>
					<tr>
						<td>2<div id="info2"></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td rowspan="2"><p class="formatos">Ventas al 4%</p></td>
						<td>3<div id="info3"></td>
						<td rowspan="2">21.05%</td>
					</tr>
					<tr>
						<td>3<div id="info3"></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td rowspan="2"><p class="formatos">Ventaas al 1%</p></td>
						<td>4<div id="info4"></td>
						<td rowspan="2">5<div id="info5"></td>
					</tr>
					<tr>
						<td>5<div id="info5"></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
						<tr>
						<td rowspan="2"><p class="formatos">Ventas excentas</p></td>
						<td>6<div id="info6"></td>
						<td rowspan="2">21.11%</td>
					</tr>
					<tr>
						<td>7<div id="info7"></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><p class="formatos">Total</p></td>
						<td></td>
						<td>8<div id="info8">%</td>
					</tr>
				</table>
			 </div>
			 <div class="formulasVacio">
			 </div>
	   </div>
    </div>

	<style type="text/css"  >

		.formatos {
			font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
			font-size: medium;
		}

		.titulo{
	        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
			font-weight: 800;
			font-size: medium;
		}

		label {
			display: inline-block;
		    max-width: 100%;
		    margin-bottom: 5px;
		    font-size: large;
		}
		
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
	       flex: 0.10; 
		   flex-direction: row;
	   }
	   .consultas{
		   display: flex;
	       flex: 0.20; 
		   flex-direction: row; 
	   }
	   .reportes{
		   display: flex;
	       flex: 0.35; 
		   flex-direction: row;
		   justify-content: space-between;
	   }
	   .formulas{
		   display: flex;
	       flex: 0.35; 
		   flex-direction: row;
	   }
	   .ventasUno{
		   display: flex;
	       flex: 0.33; 
	   }
       .otros{
		   display: flex;
	       flex: 0.34; 
	   }
	   .VentasSiete{
		   display: flex;
	       flex: 0.33; 
		   flex-direction: column;
	   }
	   .ventasInfoSiete{
           display: flex;
	       flex: 0.80; 
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
        .consultarFecha{
         	display: flex;
			flex: 0.60; 
			flex-direction: row;
		}
        .consultarEstado{
           	display: flex;
			flex: 0.10; 
			flex-direction: column;
		}
        .actividadEconomica{
           	display: flex;
			flex: 0.15; 
			flex-direction: column;
		}
		.consultarBoton{
			display: flex;
			flex: 0.15; 
			flex-direction: row-reverse;
			justify-content: space-around;
			align-items: center;
		}
		.fechaInicio{
			display: flex;
			flex: 0.45; 
			flex-direction: column;
		}
		.espaciofechas{
	        display: flex;
			flex: 0.10; 	
		}
        .fechaFinal{
			display: flex;
			flex: 0.45; 
			flex-direction: column;
		}



/*----------------------------------------------------------------------------------------*/

		#tablaVentasUno {
			font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
			border-collapse: collapse;
			width: 100%;
		}
		#tablaVentasUno td, #tablaVentasUno th {
			border: 1px solid #ddd;
			padding: 8px;
		}
		#tablaVentasUno th {
			padding-top: 12px;
			padding-bottom: 12px;
			text-align: left;
			color: white;
		}
		#tablaVentasUno tr:nth-child(even){background-color: #f2f2f2;}
        #tablaVentasUno tr:hover {background-color: #ddd;}

/*----------------------------------------------------------------------------------*/


		#tablaOtros {
			font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
			border-collapse: collapse;
			width: 100%;
		}
		#tablaOtros td, #tablaOtros th {
			border: 1px solid #ddd;
			padding: 8px;
		}
		#tablaOtros th {
			padding-top: 12px;
			padding-bottom: 12px;
			text-align: left;
			background-color: #4CAF50;
			color: white;
		}
		#tablaOtros tr:nth-child(even){background-color: #f2f2f2;}
        #tablaOtros tr:hover {background-color: #ddd;}

/*----------------------------------------------------------------------------------*/
		#tablaVentasSiete {
			font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
			border-collapse: collapse;
			width: 100%;
		}
		#tablaVentasSiete td, #tablaVentasSiete th {
			border: 1px solid #ddd;
			padding: 8px;
		}
		#tablaVentasSiete th {
			padding-top: 12px;
			padding-bottom: 12px;
			text-align: left;
			background-color: #4CAF50;
			color: white;
		}
		#tablaVentasSiete tr:nth-child(even){background-color: #f2f2f2;}
        #tablaVentasSiete tr:hover {background-color: #ddd;}
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
		#tablaInfo tr:nth-child(even){background-color: #f2f2f2;}
        #tablaInfo tr:hover {background-color: #ddd;}
/*--------------------------------------------------------------------------------------*/

		.formulasRotuloFila2Col2{
			position:relative;
			margin: 20px;
			width:48px;
			height: 38px;
			background: #d1d1d1;
			/*border:solid 1px #999;*/
			float: left;
			background-color: #6495ED;
		}
		.formulasRotuloFila2Col2:before, .formulasRotuloFila2Col2:after {
			border-bottom: 20px solid transparent;
			border-top: 20px solid transparent;
			top:-1px;
			content: " ";
			position: absolute;
			display: block;
			width: 0;
			height: 0;  
		}
		.formulasRotuloFila2Col2:after {
			border-left: 16px solid #6495ED;
			right: -15px;
			z-index: 2;
		}
		.formulasRotuloFila2Col2:before {
			border-left: 16px solid #999;
			right: -16px;
			z-index: 1;
		}


	</style> 

	<script>
	
		self = this;

		self.comboEstados          = []
	
		//Se presentan los filtros
		self.mostrarFiltros = true;
		self.valorMarginBottom  = '0px'

		self.mostrarDetalle        = false

		self.totales ={
			01_iva_13:{
				montoImpuesto:0,
				montoVenta:0
			}
		}
		
		self.fechaInicio="";
		self.fechaFin="";
		//Se cargan al montar el tag
		self.on('mount',function(){
			__ComboEstados()
			__ListaActividadesComercales()
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

		
		

		function __ListaActividadesComercales(){
			$.ajax({
				url: 'ListaEmpresaActividadComercialPorPricipalAjax.do',
				datatype: "json",
				method:"GET",
				success: function (result) {
					if(result.aaData.length > 0){
						self.empresaActividadComercial   = result.aaData
						self.update()
					

					}
				},
				error: function (xhr, status) {
					console.log(xhr);
					mensajeErrorServidor(xhr, status);
				}
			});
			return
		}


		function __ComboEstados(){
			self.comboEstados = []
			self.comboEstados.push({
				estado:1,
				descripcion:$.i18n.prop("factura.estado.pendiente")
			})
			self.comboEstados.push({
				estado:2,
				descripcion:$.i18n.prop("factura.estado.facturado")
			})
			
			self.update()
		}
		
		/**
		*  Busqueda de la informacion por rango de fechas
		**/
		__Busqueda(){
			limpiarFactura();
			var 01_iva_13 = 0
		    self.fechaInicio = $('#fechaInicial').val();
		    self.fechaFin = $('#fechaFinal').val();
		    self.update();
		    
		    if ($("#filtros").valid()) {
		        var parametros = {
		        	fechaInicio:$('#fechaInicial').val(),
		        	fechaFin:$('#fechaFinal').val(),
					estado: 6,
					selectActividadComercial: 7
		        };
		        $.ajax({
		            url: "listarConsutaIvaAjax.do",
		            datatype: "json",
		            data:parametros ,
		            method:"GET",
		            success: function (data) {
						$.each(data, function( index, modeloTabla ) {
                            if(modeloTabla.impuesto == 13 && modeloTabla.tipoImpuesto =='01'){
                                01_iva_13 = 01_iva_13 + modeloTabla.montoImpuesto
							}
						}
						self.totales.01_iva_13.montoImpuesto = 01_iva_13 
						self.update()
			        	
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
		

	</script>
</consulta-iva>
