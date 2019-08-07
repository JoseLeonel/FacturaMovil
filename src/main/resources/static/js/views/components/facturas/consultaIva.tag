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
					 <td><div value="0">{totales.uno_iva_01.montoImpuestoUno01}</div></td>
					 <td><div value="0">{totales.uno_iva_01.montoVentaUno01}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa reducida 1%</p></td>
					 <td><div value="0">{totales.uno_iva_01.montoImpuestoUno02}</div></td>
					 <td><div value="0">{totales.uno_iva_01.montoVentaUno02}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa reducida 2% </p></td>
					 <td><div value="0">{totales.uno_iva_01.montoImpuestoUno03}</div></td>
					 <td><div value="0">{totales.uno_iva_01.montoVentaUno03}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa reducida 4%</p></td>
					 <td><div value="0">{totales.uno_iva_01.montoImpuestoUno04}</div></td>
					 <td><div value="0">{totales.uno_iva_01.montoVentaUno04}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Transitorio 0%</p></td>
					 <td><div value="0">{totales.uno_iva_01.montoImpuestoUno05}</div></td>
					 <td><div value="0">{totales.uno_iva_01.montoVentaUno05}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Transitorio 4%</p></td>
					 <td><div value="0">{totales.uno_iva_01.montoImpuestoUno06}</div></td>
					 <td><div value="0">{totales.uno_iva_01.montoVentaUno06}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Transitorio 8% </p></td>
					 <td><div value="0">{totales.uno_iva_01.montoImpuestoUno07}</div></td>
					 <td><div value="0">{totales.uno_iva_01.montoVentaUno07}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa general 13%</p></td>
					 <td><div value="0">{totales.uno_iva_01.montoImpuestoUno08}</div></td>
					 <td><div value="0">{totales.uno_iva_01.montoVentaUno08}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Total</p></td>
					 <td><div value="0">{totales.unoTotal.totalImpuestoUno}</div></td>
					 <td><div value="0">{totales.unoTotal.totalVentasUno}</div></td>
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
							<td><div value="0">{totales.siete_iva_01.montoImpuestoSiete01}</div></td>
							<td><div value="0">{totales.siete_iva_01.montoVentaSiete01}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa reducida 1%</p></td>
							<td><div value="0">{totales.siete_iva_02.montoImpuestoSiete02}</div></td>
							<td><div value="0">{totales.siete_iva_02.montoVentaSiete02}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa reducida 2% </p></td>
							<td><div value="0">{totales.siete_iva_03.montoImpuestoSiete03}</div></td>
							<td><div value="0">{totales.siete_iva_03.montoVentaSiete03}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa reducida 4%</p></td>
							<td><div value="0">{totales.siete_iva_04.montoImpuestoSiete04}</div></td>
							<td><div value="0">{totales.siete_iva_04.montoVentaSiete04}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Transitorio 0%</p></td>
							<td><div value="0">{totales.siete_iva_05.montoImpuestoSiete05}</div></td>
							<td><div value="0">{totales.siete_iva_05.montoVentaSiete05}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Transitorio 4%</p></td>
							<td><div value="0">{totales.siete_iva_06.montoImpuestoSiete06}</div></td>
							<td><div value="0">{totales.siete_iva_06.montoVentaSiete06}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Transitorio 8% </p></td>
							<td><div value="0">{totales.siete_iva_07.montoImpuestoSiete07}</div></td>
							<td><div value="0">{totales.siete_iva_07.montoVentaSiete07}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa general 13%</p></td>
							<td><div value="0">{totales.siete_iva_08.montoImpuestoSiete08}</div></td>
							<td><div value="0">{totales.siete_iva_08.montoVentaSiete08}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Total</p></td>
							<td><div value="0">{totales.sieteTotal.totalImpuestoSiete}</div></td>
							<td><div value="0">{totales.sieteTotal.totalVentasSiete}</div></td>
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
			uno_iva_01:{
				montoImpuestoUno01:0,
				montoVentaUno01:0
			},
			uno_iva_02:{
				montoImpuestoUno02:0,
				montoVentaUno02:0
			},
			uno_iva_03:{
				montoImpuestoUno03:0,
				montoVentaUno03:0
			},
			uno_iva_04:{
				montoImpuestoUno04:0,
				montoVentaUno04:0
			},
			uno_iva_05:{
				montoImpuestoUno05:0,
				montoVentaUno05:0
			},
			uno_iva_06:{
				montoImpuestoUno06:0,
				montoVentaUno06:0
			},
			uno_iva_07:{
				montoImpuestoUno07:0,
				montoVentaUno07:0
			},
			uno_iva_08:{
				montoImpuestoUno08:0,
				montoVentaUno08:0
			},
			unoTotal:{
				totalImpuestoUno:0,
				totalVentasUno:0
			},
			siete_iva_01:{
				montoImpuestoSiete01:0,
				montoVentaSiete01:0
			},
			siete_iva_02:{
				montoImpuestoSiete02:0,
				montoVentaSiete02:0
			},
			siete_iva_03:{
				montoImpuestoSiete03:0,
				montoVentaSiete03:0
			},
			siete_iva_04:{
				montoImpuestoSiete04:0,
				montoVentaSiete04:0
			},
			siete_iva_05:{
				montoImpuestoSiete05:0,
				montoVentaSiete05:0
			},
			siete_iva_06:{
				montoImpuestoSiete06:0,
				montoVentaSiete06:0
			},
			siete_iva_07:{
				montoImpuestoSiete07:0,
				montoVentaSiete07:0
			},
			siete_iva_08:{
				montoImpuestoSiete08:0,
				montoVentaSiete08:0
			},
			sieteTotal:{
				totalImpuestoSiete:0,
				totalVentasSiete:0
			},
			otros:{
				selectivoConsumo:{
				   scImpuesto:0,
				   scVentas:0
				},
				cemento:{
				   cVentas:0,
				   cImpuesto:0
				},
				otrosProductos:{
				   oImpuesto:0,
				   oVentas:0
				},
                total:{
				   tImpuesto:0,
				   tVentas:0
				}	
			}
		}

/*-----------------------------------------------------------------------------------------*/

		self.fechaInicio="";
		self.fechaFin="";
		//Se cargan al montar el tag
		self.on('mount',function(){
			__ComboEstados()
			__ListaActividadesComercales()
		    $("#filtros").validate(reglasDeValidacion());
		   
		    limpiarFactura();
		})

/*-----------------------------------------------------------------------------------------*/

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

/*-----------------------------------------------------------------------------------------*/

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

/*-----------------------------------------------------------------------------------------*/		

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

/*-----------------------------------------------------------------------------------------*/
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
		
/*-----------------------------------------------------------------------------------------*/

		__Busqueda(){
			limpiarFactura();
			var montoImpuestoPivot          = 0;
            var montoVentaPivot             = 0;

			var unoImpuestoTotal            = 0;
            var unoVentaTotal               = 0;

			var sieteImpuestoTotal          = 0;
            var sieteVentaTotal             = 0;

			var selecConsumoImpuestoTotal   = 0;
			var selecConsumoVentaTotal      = 0;

			var cementoImpuestoTotal        = 0;
			var cementoVentaTotal           = 0;
			
			var otrosProductosImpuestoTotal = 0;
			var otrosProductosVentaTotal    = 0; 

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
						$.each(data.aaData, function( index, modeloTabla ) {
							
							montoImpuestoPivot = __valorNumerico(modeloTabla.totalImpuesto)
							montoVentaPivot    = __valorNumerico(modeloTabla.totalVentas)
                            
							/*----7----*/
							if (modeloTabla.tipoImpuesto =='01') {
								if(modeloTabla.codTarifa == '01'){
									self.totales.uno_iva_01.montoImpuestoUno01 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno01    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '02'){
									self.totales.uno_iva_01.montoImpuestoUno02 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno02    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '03'){
									self.totales.uno_iva_01.montoImpuestoUno03 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno03    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '04'){
									self.totales.uno_iva_01.montoImpuestoUno04 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno04    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '05'){
									self.totales.uno_iva_01.montoImpuestoUno05 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno05    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '06'){
									self.totales.uno_iva_01.montoImpuestoUno06 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno06    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '07'){
									self.totales.uno_iva_01.montoImpuestoUno07 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno07    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '08'){
									self.totales.uno_iva_01.montoImpuestoUno08 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno08    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								unoImpuestoTotal += formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
			                    unoVentaTotal    += formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5)
							}

							/*----7----*/
                            if (modeloTabla.tipoImpuesto =='07'){
								if(modeloTabla.codTarifa == '01'){
									self.totales.siete_iva_01.montoImpuestoSiete01 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_01.montoVentaSiete01    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '02'){
									self.totales.siete_iva_01.montoImpuestoSiete02 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_01.montoVentaSiete02    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '03'){
									self.totales.siete_iva_02.montoImpuestoSiete03 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_02.montoVentaSiete03    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '04'){
									self.totales.siete_iva_03.montoImpuestoSiete04 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_03.montoVentaSiete04    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '05'){
									self.totales.siete_iva_04.montoImpuestoSiete05 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_04.montoVentaSiete05    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '06'){
									self.totales.siete_iva_05.montoImpuestoSiete06 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_05.montoVentaSiete06    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '07'){
									self.totales.siete_iva_06.montoImpuestoSiete07 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_06.montoVentaSiete07    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								if(modeloTabla.codTarifa == '08'){
									self.totales.siete_iva_08.montoImpuestoSiete08 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_08.montoVentaSiete08    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
								}
								sieteImpuestoTotal += formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
								sieteVentaTotal    += formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5)
                            }
							/*----no tienen----*/

							if(modeloTabla.tipoImpuesto =='02'){ //Selectivo de consumo
                                self.totales.otros.selectivoConsumo.scImpuesto = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
								self.totales.otros.selectivoConsumo.scVentas   = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
							    sieteImpuestoTotal += formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
								sieteVentaTotal    += formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5)
							}else if(modeloTabla.tipoImpuesto =='12'){ //cemento
                                self.totales.otros.selectivoConsumo.cVentas    = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
								self.totales.otros.selectivoConsumo.cImpuesto  = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
							
							}else if(modeloTabla.tipoImpuesto =='02'){ //otros
                                self.totales.otros.selectivoConsumo.oImpuesto  = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
								self.totales.otros.selectivoConsumo.oVentas    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 	
							}
							
							montoImpuestoPivot = 0
							montoVentaPivot    = 0
							
						})
						self.totales.unoTotal.totalImpuestoUno  = formatoDecimales(redondeoDecimales(__valorNumerico(unoImpuestoTotal),5),5)
						self.totales.unoTotal.totalVentasUno    = formatoDecimales(redondeoDecimales(__valorNumerico(unoVentaTotal),5),5) 

						self.totales.sieteTotal.totalImpuestoSiete  = formatoDecimales(redondeoDecimales(__valorNumerico(sieteImpuestoTotal),5),5)
						self.totales.sieteTotal.totalVentasSiete    = formatoDecimales(redondeoDecimales(__valorNumerico(sieteVentaTotal),5),5) 
						unoImpuestoTotal   = 0;
						unoVentaTotal      = 0;
						sieteImpuestoTotal = 0;
						sieteVentaTotal    = 0;

                        self.update()
			        },
			        error: function (xhr, status) {
			            console.log(xhr);
			            mensajeErrorServidor(xhr, status);
			        }
		        });
		 	}		
		}
/*-----------------------------------------------------------------------------------------*/

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
