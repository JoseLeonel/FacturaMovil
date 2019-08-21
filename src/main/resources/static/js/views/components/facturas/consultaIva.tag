<consulta-iva>
   <!-- Titulos -->
    
	<div class="principalContainer">
	
	   <div class="encabezado">
			<div class="encabezado1">
			</div>
			<div class="encabezado2">
				<p class="titulo">Reporte mensual de {parametros.titulo } IVA</p>	
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


				<div class="consultarEstado">   
					<label for="pago_tipoVentaL">{$.i18n.prop("factura.estado")} </label> 
					<select class="form-control estado" id="estado" name="estado"  >
						<option each={comboEstados} value="{estado}" selected="{factura.estado ==estado?true:false}" >{descripcion}</option>
					</select>
				</div>


				<div class="actividadEconomica">   
				<label class="titleListaPrecio">Actividades Comerciales </label>  
					<select class="form-control selectActividadComercial"  name="selectActividadComercial" id="selectActividadComercial" >
						<option  each={empresaActividadComercial}  value="{codigo}"   >{descripcion}</option>
					</select>
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
					 <td><p class="formatos">Monto {parametros.titulo }</p></td>
					 <td><p class="formatos">Monto IVA</p></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa 0% (Exento)</p></td>
					 <td><div>{totales.uno_iva_01.montoVentaUno01}</div></td>
					 <td><div>{totales.uno_iva_01.montoImpuestoUno01}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa reducida 1%</p></td>
					 <td><div>{totales.uno_iva_01.montoVentaUno02}</div></td>
					 <td><div>{totales.uno_iva_01.montoImpuestoUno02}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa reducida 2% </p></td>
					 <td><div>{totales.uno_iva_01.montoVentaUno03}</div></td>
					 <td><div>{totales.uno_iva_01.montoImpuestoUno03}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa reducida 4%</p></td>
					 <td><div>{totales.uno_iva_01.montoVentaUno04}</div></td>
					 <td><div>{totales.uno_iva_01.montoImpuestoUno04}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Transitorio 0%</p></td>
					 <td><div>{totales.uno_iva_01.montoVentaUno05}</div></td>
					 <td><div>{totales.uno_iva_01.montoImpuestoUno05}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Transitorio 4%</p></td>
					 <td><div>{totales.uno_iva_01.montoVentaUno06}</div></td>
					 <td><div>{totales.uno_iva_01.montoImpuestoUno06}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Transitorio 8% </p></td>
					 <td><div>{totales.uno_iva_01.montoVentaUno07}</div></td>
					 <td><div>{totales.uno_iva_01.montoImpuestoUno07}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos">Tarifa general 13%</p></td>
					 <td><div>{totales.uno_iva_01.montoVentaUno08}</div></td>
					 <td><div>{totales.uno_iva_01.montoImpuestoUno08}</div></td>
				  </tr>
				  <tr>
				     <td><p class="formatos"><b>Total</b></p></td>
					 <td><div>{totales.unoTotal.totalVentasUno}</div></td>
					 <td><div>{totales.unoTotal.totalImpuestoUno}</div></td>
				  </tr>
			   </table>
			 
			</div>

			<div class="otros">
				<table id="tablaOtros">
				<caption>Otros</caption>
					<tr>
						<td></td>
						<td><p class="formatos">Monto {parametros.titulo }</p></td>
						<td><p class="formatos">Monto IVA</p></td>
					</tr>
					<tr>
						<td><p class="formatos">Selectivo de consumo</p></td>
						<td><div>{totales.otros.selectivoConsumo.scVentas}</div></td>
						<td><div>{totales.otros.selectivoConsumo.scImpuesto}</div></td>
					</tr>
					<tr>
						<td><p class="formatos">Cemento</p></td>
						<td><div>{totales.otros.selectivoConsumo.cVentas}</div></td>
						<td><div>{totales.otros.selectivoConsumo.cImpuesto}</div></td>
					</tr>
					<tr>
						<td><p class="formatos">Otros</p></td>
						<td><div>{totales.otros.selectivoConsumo.oVentas}</div></td>
						<td><div>{totales.otros.selectivoConsumo.oImpuesto}</div></td>
					</tr>
					<tr>
						<td><p class="formatos"><b>Total</b></p></td>
						<td><div>{totales.otros.selectivoConsumo.tVentas}</div></td>
						<td><div>{totales.otros.selectivoConsumo.tImpuesto}</div></td>
					</tr>
				</table>
			</div>

			<div class="VentasSiete">
				<div class="ventasInfoSiete">
					<table id="tablaVentasSiete">
					<caption>IVA (calculo especial) </caption>
						<tr>
							<td></td>
							<td><p class="formatos">Monto {parametros.titulo }</p></td>
							<td><p class="formatos">Monto IVA</p></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa 0% (Exento)</p></td>
							<td><div>{totales.siete_iva_01.montoVentaSiete01}</div></td>
							<td><div>{totales.siete_iva_01.montoImpuestoSiete01}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa reducida 1%</p></td>
							<td><div>{totales.siete_iva_02.montoVentaSiete02}</div></td>
							<td><div>{totales.siete_iva_02.montoImpuestoSiete02}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa reducida 2% </p></td>
							<td><div>{totales.siete_iva_03.montoVentaSiete03}</div></td>
							<td><div>{totales.siete_iva_03.montoImpuestoSiete03}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa reducida 4%</p></td>
							<td><div>{totales.siete_iva_04.montoVentaSiete04}</div></td>
							<td><div>{totales.siete_iva_04.montoImpuestoSiete04}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Transitorio 0%</p></td>
							<td><div>{totales.siete_iva_05.montoVentaSiete05}</div></td>
							<td><div>{totales.siete_iva_05.montoImpuestoSiete05}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Transitorio 4%</p></td>
							<td><div>{totales.siete_iva_06.montoVentaSiete06}</div></td>
							<td><div>{totales.siete_iva_06.montoImpuestoSiete06}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Transitorio 8% </p></td>
							<td><div>{totales.siete_iva_07.montoVentaSiete07}</div></td>
							<td><div>{totales.siete_iva_07.montoImpuestoSiete07}</div></td>
						</tr>
						<tr>
							<td><p class="formatos">Tarifa general 13%</p></td>
							<td><div>{totales.siete_iva_08.montoVentaSiete08}</div></td>
							<td><div>{totales.siete_iva_08.montoImpuestoSiete08}</div></td>
						</tr>
						<tr>
							<td><p class="formatos"><b>Total</b></p></td>
							<td><div>{totales.sieteTotal.totalVentasSiete}</div></td>
							<td><div>{totales.sieteTotal.totalImpuestoSiete}</div></td>
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
					<div class="formulasRotuloFila2Col3"><p class="formatos">{parametros.titulo } con<br/>derecho a<br/>credito</P></div>
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
						<td rowspan="2"><p class="formatos">Tarifa 0% (Exento)</p></td>
						<td><div>{totales.estadistica.tarifaCeroP}</div></td>
					<td rowspan="2"><div>{totales.estadistica.tarifaCeroImp}%</div></td>
					</tr>
					<tr>
						<td><div>{totales.estadistica.totalVentasP}</div></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td rowspan="2"><p class="formatos">Tarifa reducida 1%</p></td>
						<td><div>{totales.estadistica.tarifaReducidaUnoP}</div></td>
						<td rowspan="2"><div>{totales.estadistica.tarifaReducidaUnoPImp}%</div></td>
					</tr>
					<tr>
						<td><div>{totales.estadistica.totalVentasP}</div></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td rowspan="2"><p class="formatos">Tarifa reducida 2%</p></td>
						<td>{totales.estadistica.tarifaReducidaDosP}<div></div></td>
						<td rowspan="2"><div>{totales.estadistica.tarifaReducidaDosPImp}%</div></td>
					</tr>
					<tr>
						<td><div>{totales.estadistica.totalVentasP}</div></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td rowspan="2"><p class="formatos">Tarifa reducida 4%</p></td>
						<td><div>{totales.estadistica.tarifareducidaCuatroP}</div></td>
						<td rowspan="2"><div>{totales.estadistica.tarifareducidaCuatroPImp}%</div></td>
					</tr>
					<tr>
						<td><div>{totales.estadistica.totalVentasP}</div></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td rowspan="2"><p class="formatos">Transitorio 0%</p></td>
						<td><div>{totales.estadistica.transitorioCeroP}</div></td>
						<td rowspan="2"><div>{totales.estadistica.transitorioCeroPImp}%</div></td>
					</tr>
					<tr>
						<td><div>{totales.estadistica.totalVentasP}</div></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td rowspan="2"><p class="formatos">Transitorio 4%</p></td>
						<td><div>{totales.estadistica.transitorioCuatroP}</div></td>
						<td rowspan="2"><div>{totales.estadistica.transitorioCuatroPImp}%</div></td>
					</tr>
					<tr>
						<td><div>{totales.estadistica.totalVentasP}</div></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td rowspan="2"><p class="formatos">Transitorio 8%</p></td>
						<td><div>{totales.estadistica.transitorioOchoP}</div></td>
						<td rowspan="2"><div>{totales.estadistica.transitorioOchoPImp}%</div></td>
					</tr>
					<tr>
						<td><div>{totales.estadistica.totalVentasP}</div></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td rowspan="2"><p class="formatos">Tarifa general 13%</p></td>
						<td>{totales.estadistica.tarifaGeneralP}<div></div></td>
						<td rowspan="2"><div>{totales.estadistica.tarifaGeneralPImp}%</div></td>
					</tr>
					<tr>
						<td><div>{totales.estadistica.totalVentasP}</div></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>  
					<tr>
						<td rowspan="2"><p class="formatos">Otros</p></td>
						<td>{totales.estadistica.otrosP}<div></div></td>
						<td rowspan="2"><div>{totales.estadistica.otrosPImp}%</div></td>
					</tr>
					<tr>
						<td><div>{totales.estadistica.totalVentasP}</div></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>    
					<tr>
						<td rowspan="2"><p class="formatos">Exentos</p></td>
						<td>{totales.estadistica.excentosP}<div></div></td>
						<td rowspan="2"><div>{totales.estadistica.excentosPImp}%</div></td>
					</tr>
					<tr>
						<td><div>{totales.estadistica.totalVentasP}</div></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><p class="formatos"><b>Total</b></p></td>
						<td><div></div></td>
						<td><div>{totales.estadistica.totalVentasImp}%</div></td>
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
		   justify-content: space-between;
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
	       flex: 0.30; 
	   }
       .otros{
		   display: flex;
	       flex: 0.30; 
	   }
	   .VentasSiete{
		   display: flex;
	       flex: 0.30; 
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
        .consultarFecha{
         	display: flex;
			flex: 0.30; 
			flex-direction: row;
			justify-content: space-between;
		}
        .consultarEstado{
           	display: flex;
			flex: 0.20; 
			flex-direction: column;
		}
        .actividadEconomica{
           	display: flex;
			flex: 0.20; 
			flex-direction: column;
		}
		.consultarBoton{
			display: flex;
			flex: 0.05; 
			flex-direction: row-reverse;
			justify-content: space-around;
			align-items: center;
		}
		.fechaInicio{
			display: flex;
			flex: 0.40; 
			flex-direction: column;
		}

        .fechaFinal{
			display: flex;
			flex: 0.40; 
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

	    var _ok                         = 0;
		var montoImpuestoPivot          = 0;
		var montoVentaPivot             = 0;
		var unoImpuestoTotal            = 0;
		var unoVentaTotal               = 0;
		var sieteImpuestoTotal          = 0;
		var sieteVentaTotal             = 0;
		var ventasOtrosTotal            = 0;
		var ImpuesOtrosTotal            = 0;
		var excentosTotalVentas         = 0;
		var excentosTotalimpuesto       = 0;
		var tarifa_1                    = 0;
		var tarifa_2                    = 0;
		var tarifa_3                    = 0;
		var tarifa_4                    = 0;
		var tarifa_5                    = 0;
		var tarifa_6                    = 0;
		var tarifa_7                    = 0;
		var tarifa_8                    = 0;
		var tarifa_9                    = 0;
		var tarifa_10                   = 0;
		var tarifa_1_porCiento          = 0;
		var tarifa_2_porCiento          = 0;
		var tarifa_3_porCiento          = 0;
		var tarifa_4_porCiento          = 0;
		var tarifa_5_porCiento          = 0;
		var tarifa_6_porCiento          = 0;
		var tarifa_7_porCiento          = 0;
		var tarifa_8_porCiento          = 0;
		var tarifa_9_porCiento          = 0;
		var tarifa_10_porCiento         = 0;
		var totalPorCientos             = 0;
		var bd                          = 0;
		var __total                     = 0;
	
		self = this;
		self.parametros            = opts.parametros;  
		self.comboEstados          = []
		self.mostrarFiltros = true;
		self.valorMarginBottom  = '0px'
		self.mostrarDetalle        = false

	self.totales ={
		uno_iva_01:{
			montoImpuestoUno01:"",
			montoVentaUno01:""
		},
		uno_iva_02:{
			montoImpuestoUno02:"",
			montoVentaUno02:""
		},
		uno_iva_03:{
			montoImpuestoUno03:"",
			montoVentaUno03:""
		},
		uno_iva_04:{
			montoImpuestoUno04:"",
			montoVentaUno04:""
		},
		uno_iva_05:{
			montoImpuestoUno05:"",
			montoVentaUno05:""
		},
		uno_iva_06:{
			montoImpuestoUno06:"",
			montoVentaUno06:""
		},
		uno_iva_07:{
			montoImpuestoUno07:"",
			montoVentaUno07:""
		},
		uno_iva_08:{
			montoImpuestoUno08:"",
			montoVentaUno08:""
		},
		unoTotal:{
			totalImpuestoUno:"",
			totalVentasUno:""
		},
		siete_iva_01:{
			montoImpuestoSiete01:"",
			montoVentaSiete01:""
		},
		siete_iva_02:{
			montoImpuestoSiete02:"",
			montoVentaSiete02:""
		},
		siete_iva_03:{
			montoImpuestoSiete03:"",
			montoVentaSiete03:""
		},
		siete_iva_04:{
			montoImpuestoSiete04:"",
			montoVentaSiete04:""
		},
		siete_iva_05:{
			montoImpuestoSiete05:"",
			montoVentaSiete05:""
		},
		siete_iva_06:{
			montoImpuestoSiete06:"",
			montoVentaSiete06:""
		},
		siete_iva_07:{
			montoImpuestoSiete07:"",
			montoVentaSiete07:""
		},
		siete_iva_08:{
			montoImpuestoSiete08:"",
			montoVentaSiete08:""
		},
		sieteTotal:{
			totalImpuestoSiete:"",
			totalVentasSiete:""
		},
		otros:{
			selectivoConsumo:{
				scImpuesto:"",
				scVentas:""
			},
			cemento:{
				cVentas:"",
				cImpuesto:""
			},
			otrosProductos:{
				oImpuesto:"",
				oVentas:""
			},
				total:{
				tImpuesto:"",
				tVentas:""
			}	
		},
		estadistica:
		{
			tarifaCeroP:"",
			tarifaReducidaUnoP:"",
			tarifaReducidaDosP:"",
			tarifareducidaCuatroP:"",
			transitorioCeroP:"",
			transitorioCuatroP:"",
			transitorioOchoP:"",
			tarifaGeneralP:"",
			otrosP:"",
			excentosP:"",
			totalVentasP:"",
			tarifaCeroImp:"",
			tarifaReducidaUnoPImp:"",
			tarifaReducidaDosPImp:"",
			tarifareducidaCuatroPImp:"",
			transitorioCeroPImp:"",
			transitorioCuatroPImp:"",
			transitorioOchoPImp:"",
			tarifaGeneralPImp:"",
			otrosPImp:"",
			excentosPImp:"",
			totalVentasImp:""
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
				url:"ListaEmpresaActividadComercialPorPricipalAjax.do",
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
				estado:2,
				descripcion:$.i18n.prop("tipo.factura.facturado")
			})
			self.comboEstados.push({
				estado:6,
				descripcion:$.i18n.prop("tipo.factura.aceptadas")
			})
			self.comboEstados.push({
				estado:7,
				descripcion:$.i18n.prop("tipo.factura.noaceptadas")
			})
			self.update()
		}
		
/*-----------------------------------------------------------------------------------------*/

		__Busqueda(){
			_InicializarJson();
			limpiarFactura();
			inicializaVariables();
		    self.fechaInicio = $('#fechaInicial').val();
		    self.fechaFin = $('#fechaFinal').val();
		    self.update();
		    if ($("#filtros").valid()) {
		        var parametros = {
		        	fechaInicio:$('#fechaInicial').val(),
		        	fechaFin:$('#fechaFinal').val(),
					    estado: $('#estado').val(),
					    selectActividadComercial: $('#selectActividadComercial').val()
		        };
		        $.ajax({
		            url: self.parametros.url,
		            datatype: "json",
		            data:parametros ,
		            method:"GET",
		            success: function (data) {
						$.each(data.aaData, function( index, modeloTabla ) {
							if(bd==0){inicializaEsta();bd=1;}
							montoImpuestoPivot = __valorNumerico(modeloTabla.totalImpuesto)
							montoVentaPivot    = __valorNumerico(modeloTabla.totalVentas)         
							/*----7----*/
							if (modeloTabla.tipoImpuesto =='01') {
								if(modeloTabla.codTarifa == '01'){
									self.totales.uno_iva_01.montoImpuestoUno01  = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno01     = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_1                                   += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '02'){
									self.totales.uno_iva_01.montoImpuestoUno02  = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno02     = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_2                                      += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '03'){
									self.totales.uno_iva_01.montoImpuestoUno03  = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno03     = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_3                                       += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '04'){
									self.totales.uno_iva_01.montoImpuestoUno04     = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno04        = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_4                                       += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '05'){
									self.totales.uno_iva_01.montoImpuestoUno05     = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno05        = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_5                                       += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '06'){
									self.totales.uno_iva_01.montoImpuestoUno06     = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno06        = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_6                                       += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '07'){
									self.totales.uno_iva_01.montoImpuestoUno07     = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno07        = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_7                                       += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '08'){
									self.totales.uno_iva_01.montoImpuestoUno08     = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.uno_iva_01.montoVentaUno08        = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_8                                       += montoVentaPivot
								}
								unoImpuestoTotal += montoImpuestoPivot
								unoVentaTotal    += montoVentaPivot
							}

							/*----7----*/
                           if (modeloTabla.tipoImpuesto =='07'){
								if(modeloTabla.codTarifa == '01'){
									self.totales.siete_iva_01.montoImpuestoSiete01 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_01.montoVentaSiete01    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_1                                       += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '02'){
									self.totales.siete_iva_02.montoImpuestoSiete02 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_02.montoVentaSiete02    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_2                                       += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '03'){
									self.totales.siete_iva_03.montoImpuestoSiete03 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_03.montoVentaSiete03    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_3                                       += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '04'){
									self.totales.siete_iva_04.montoImpuestoSiete04 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_04.montoVentaSiete04    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_4                                       += montoVentaPivot 
								}
								if(modeloTabla.codTarifa == '05'){
									self.totales.siete_iva_05.montoImpuestoSiete05 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_05.montoVentaSiete05    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_5                                       += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '06'){
									self.totales.siete_iva_06.montoImpuestoSiete06 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_06.montoVentaSiete06    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_6                                       += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '07'){
									self.totales.siete_iva_07.montoImpuestoSiete07 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_07.montoVentaSiete07    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_7                                       += montoVentaPivot
								}
								if(modeloTabla.codTarifa == '08'){
									self.totales.siete_iva_08.montoImpuestoSiete08 = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.siete_iva_08.montoVentaSiete08    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									tarifa_8                                       += montoVentaPivot
								}
								sieteImpuestoTotal += montoImpuestoPivot
								sieteVentaTotal    += montoVentaPivot
                            }
							/*----no tienen----*/
							if(modeloTabla.codTarifa == ''){
								if(modeloTabla.tipoImpuesto =='02'){ //Selectivo de consumo
									self.totales.otros.selectivoConsumo.scImpuesto = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.otros.selectivoConsumo.scVentas   = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									ventasOtrosTotal += montoVentaPivot
									ImpuesOtrosTotal += montoImpuestoPivot
									tarifa_9                                       += montoVentaPivot
								}else if(modeloTabla.tipoImpuesto =='12'){ //cemento
									self.totales.otros.selectivoConsumo.cVentas    = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.otros.selectivoConsumo.cImpuesto  = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 
									ventasOtrosTotal += montoVentaPivot
									ImpuesOtrosTotal += montoImpuestoPivot
									tarifa_9                                          += montoVentaPivot
								}else if(modeloTabla.tipoImpuesto =='99' || modeloTabla.tipoImpuesto =='99'){ //otros
									self.totales.otros.selectivoConsumo.oImpuesto  = formatoDecimales(redondeoDecimales(__valorNumerico(montoImpuestoPivot),5),5)
									self.totales.otros.selectivoConsumo.oVentas    = formatoDecimales(redondeoDecimales(__valorNumerico(montoVentaPivot),5),5) 	
									ventasOtrosTotal += montoVentaPivot
									ImpuesOtrosTotal += montoImpuestoPivot
									tarifa_9                                       += montoVentaPivot
								}
						    }
							//exentos
							if(modeloTabla.codTarifa == ''){
								if(modeloTabla.tipoImpuesto ==''){
									excentosTotalVentas += montoVentaPivot
									excentosTotalimpuesto += montoImpuestoPivot
									tarifa_10                                     += montoVentaPivot
								}
							}
							montoImpuestoPivot = 0
							montoVentaPivot    = 0
							_ok = 1;
						})
						if(_ok == 1){
							realizaCalculos()
                            pintaPantalla(); 
						}
                        self.update()	
			        },
			        error: function (xhr, status) {
			            console.log(xhr);
			            mensajeErrorServidor(xhr, status);
			        }
		        })
		 	}		
		}

/*-----------------------------------------------------------------------------------------*/

function realizaCalculos(){
		__total = unoVentaTotal          +
					sieteVentaTotal      +
					ventasOtrosTotal     +
					excentosTotalVentas;

		tarifa_1_porCiento  = ((tarifa_1 * 100)  / __total);
		tarifa_2_porCiento  = ((tarifa_2 * 100)  / __total);
		tarifa_3_porCiento  = ((tarifa_3 * 100)  / __total);
		tarifa_4_porCiento  = ((tarifa_4 * 100)  / __total);
		tarifa_5_porCiento  = ((tarifa_5 * 100)  / __total);
		tarifa_6_porCiento  = ((tarifa_6 * 100)  / __total);
		tarifa_7_porCiento  = ((tarifa_7 * 100)  / __total);
		tarifa_8_porCiento  = ((tarifa_8 * 100)  / __total);
		tarifa_9_porCiento  = ((tarifa_9 * 100)  / __total);
		tarifa_10_porCiento = ((tarifa_10 * 100) / __total);

		totalPorCientos = tarifa_1_porCiento + tarifa_2_porCiento     + 
							tarifa_3_porCiento + tarifa_4_porCiento   +
							tarifa_5_porCiento + tarifa_6_porCiento   +
							tarifa_7_porCiento + tarifa_8_porCiento   +
							tarifa_9_porCiento + tarifa_10_porCiento;
}

/*-----------------------------------------------------------------------------------------*/

function pintaPantalla(){
	self.totales.estadistica.totalVentasP             = formatoDecimales(redondeoDecimales(__valorNumerico(__total),5),5);
	self.totales.unoTotal.totalImpuestoUno            = formatoDecimales(redondeoDecimales(__valorNumerico(unoImpuestoTotal),5),5);
	self.totales.unoTotal.totalVentasUno              = formatoDecimales(redondeoDecimales(__valorNumerico(unoVentaTotal),5),5); 
	self.totales.sieteTotal.totalImpuestoSiete        = formatoDecimales(redondeoDecimales(__valorNumerico(sieteImpuestoTotal),5),5);
	self.totales.sieteTotal.totalVentasSiete          = formatoDecimales(redondeoDecimales(__valorNumerico(sieteVentaTotal),5),5); 
	self.totales.otros.selectivoConsumo.tVentas       = formatoDecimales(redondeoDecimales(__valorNumerico(ventasOtrosTotal),5),5);
	self.totales.otros.selectivoConsumo.tImpuesto     = formatoDecimales(redondeoDecimales(__valorNumerico(ImpuesOtrosTotal),5),5);
	self.totales.estadistica.tarifaCeroImp            = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_1_porCiento),5),5);
	self.totales.estadistica.tarifaReducidaUnoPImp    = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_2_porCiento),5),5);
	self.totales.estadistica.tarifaReducidaDosPImp    = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_3_porCiento),5),5);
	self.totales.estadistica.tarifareducidaCuatroPImp = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_4_porCiento),5),5);
	self.totales.estadistica.transitorioCeroPImp      = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_5_porCiento),5),5);
	self.totales.estadistica.transitorioCuatroPImp    = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_6_porCiento),5),5);
	self.totales.estadistica.transitorioOchoPImp      = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_7_porCiento),5),5);
	self.totales.estadistica.tarifaGeneralPImp        = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_8_porCiento),5),5);
	self.totales.estadistica.totalVentasImp           = formatoDecimales(redondeoDecimales(__valorNumerico(totalPorCientos),5),5);
	self.totales.estadistica.otrosPImp                = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_9_porCiento),5),5);
	self.totales.estadistica.excentosPImp             = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_10_porCiento),5),5);
	self.totales.estadistica.otrosP                   = formatoDecimales(redondeoDecimales(__valorNumerico(ventasOtrosTotal),5),5);
	self.totales.estadistica.excentosP                = formatoDecimales(redondeoDecimales(__valorNumerico(excentosTotalVentas),5),5);

	self.totales.estadistica.tarifaCeroP	          = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_1),5),5); 
	self.totales.estadistica.tarifaReducidaUnoP       = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_2),5),5);
	self.totales.estadistica.tarifaReducidaDosP       = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_3),5),5);
	self.totales.estadistica.tarifareducidaCuatroP    = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_4),5),5);
	self.totales.estadistica.transitorioCeroP         = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_5),5),5);
	self.totales.estadistica.transitorioCuatroP       = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_6),5),5); 
	self.totales.estadistica.transitorioOchoP         = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_7),5),5); 
	self.totales.estadistica.tarifaGeneralP           = formatoDecimales(redondeoDecimales(__valorNumerico(tarifa_8),5),5); 
}


/*-----------------------------------------------------------------------------------------*/

function inicializaVariables(){
	_ok                         = 0;
	montoImpuestoPivot          = 0;
	montoVentaPivot             = 0;
	unoImpuestoTotal            = 0;
	unoVentaTotal               = 0;
	sieteImpuestoTotal          = 0;
	sieteVentaTotal             = 0;
	ventasOtrosTotal            = 0;
	ImpuesOtrosTotal            = 0;
	excentosTotalVentas         = 0;
	excentosTotalimpuesto       = 0;
	tarifa_1                    = 0;
	tarifa_2                    = 0;
	tarifa_3                    = 0;
	tarifa_4                    = 0;
	tarifa_5                    = 0;
	tarifa_6                    = 0;
	tarifa_7                    = 0;
	tarifa_8                    = 0;
	tarifa_9                    = 0;
	tarifa_10                   = 0;
	tarifa_1_porCiento          = 0;
	tarifa_2_porCiento          = 0;
	tarifa_3_porCiento          = 0;
	tarifa_4_porCiento          = 0;
	tarifa_5_porCiento          = 0;
	tarifa_6_porCiento          = 0;
	tarifa_7_porCiento          = 0;
	tarifa_8_porCiento          = 0;
	tarifa_9_porCiento          = 0;
	tarifa_10_porCiento         = 0;
	totalPorCientos             = 0;
	bd                          = 0;
	__total                     = 0;
}

/*-----------------------------------------------------------------------------------------*/

function inicializaEsta(){
	self.totales.uno_iva_01.montoImpuestoUno01        = "0.00000";
	self.totales.uno_iva_01.montoVentaUno02           = "0.00000";
	self.totales.uno_iva_01.montoVentaUno01           = "0.00000";
	self.totales.uno_iva_01.montoImpuestoUno02        = "0.00000";
	self.totales.uno_iva_01.montoVentaUno03           = "0.00000";
	self.totales.uno_iva_01.montoImpuestoUno03        = "0.00000";
	self.totales.uno_iva_01.montoVentaUno04           = "0.00000";
	self.totales.uno_iva_01.montoImpuestoUno04        = "0.00000";
	self.totales.uno_iva_01.montoVentaUno05           = "0.00000";
	self.totales.uno_iva_01.montoImpuestoUno05        = "0.00000";
	self.totales.uno_iva_01.montoVentaUno06           = "0.00000";
	self.totales.uno_iva_01.montoImpuestoUno06        = "0.00000";
	self.totales.uno_iva_01.montoVentaUno07           = "0.00000";
	self.totales.uno_iva_01.montoImpuestoUno07        = "0.00000";
	self.totales.uno_iva_01.montoVentaUno08           = "0.00000";
	self.totales.uno_iva_01.montoImpuestoUno08        = "0.00000";
	self.totales.unoTotal.totalVentasUno              = "0.00000";
	self.totales.unoTotal.totalImpuestoUno            = "0.00000";
	self.totales.otros.selectivoConsumo.scVentas      = "0.00000";
	self.totales.otros.selectivoConsumo.scImpuesto    = "0.00000";
	self.totales.otros.selectivoConsumo.cVentas       = "0.00000";
	self.totales.otros.selectivoConsumo.cImpuesto     = "0.00000";
	self.totales.otros.selectivoConsumo.oVentas       = "0.00000";
	self.totales.otros.selectivoConsumo.oImpuesto     = "0.00000";
	self.totales.otros.selectivoConsumo.tVentas       = "0.00000";
	self.totales.otros.selectivoConsumo.tImpuesto     = "0.00000";
	self.totales.siete_iva_01.montoVentaSiete01       = "0.00000";
	self.totales.siete_iva_01.montoImpuestoSiete01    = "0.00000";
	self.totales.siete_iva_02.montoVentaSiete02       = "0.00000";
	self.totales.siete_iva_02.montoImpuestoSiete02    = "0.00000";
	self.totales.siete_iva_03.montoVentaSiete03       = "0.00000";
	self.totales.siete_iva_03.montoImpuestoSiete03    = "0.00000";
	self.totales.siete_iva_04.montoVentaSiete04       = "0.00000";
	self.totales.siete_iva_04.montoImpuestoSiete04    = "0.00000";
	self.totales.siete_iva_05.montoVentaSiete05       = "0.00000";
	self.totales.siete_iva_05.montoImpuestoSiete05    = "0.00000";
	self.totales.siete_iva_06.montoVentaSiete06       = "0.00000";
	self.totales.siete_iva_06.montoImpuestoSiete06    = "0.00000";
	self.totales.siete_iva_07.montoVentaSiete07       = "0.00000";
	self.totales.siete_iva_07.montoImpuestoSiete07    = "0.00000";
	self.totales.siete_iva_08.montoVentaSiete08       = "0.00000";
	self.totales.siete_iva_08.montoImpuestoSiete08    = "0.00000";
	self.totales.sieteTotal.totalVentasSiete          = "0.00000";
	self.totales.sieteTotal.totalImpuestoSiete        = "0.00000";
	self.totales.estadistica.tarifaCeroP              = "0.00000";
	self.totales.estadistica.tarifaCeroImp            = "0.00000";
	self.totales.estadistica.totalVentasP             = "0.00000";
	self.totales.estadistica.tarifaReducidaUnoP       = "0.00000";
	self.totales.estadistica.tarifaReducidaUnoPImp    = "0.00000";
	self.totales.estadistica.tarifaReducidaDosP       = "0.00000";
	self.totales.estadistica.tarifaReducidaDosPImp    = "0.00000";
	self.totales.estadistica.tarifareducidaCuatroP    = "0.00000";
	self.totales.estadistica.tarifareducidaCuatroPImp = "0.00000";
	self.totales.estadistica.transitorioCeroP         = "0.00000";
	self.totales.estadistica.transitorioCeroPImp      = "0.00000";
	self.totales.estadistica.transitorioCuatroP       = "0.00000";
	self.totales.estadistica.transitorioCuatroPImp    = "0.00000";
	self.totales.estadistica.transitorioOchoP         = "0.00000";
	self.totales.estadistica.transitorioOchoPImp      = "0.00000";
	self.totales.estadistica.tarifaGeneralP           = "0.00000";
	self.totales.estadistica.tarifaGeneralPImp        = "0.00000";
	self.totales.estadistica.otrosP                   = "0.00000";
	self.totales.estadistica.otrosPImp                = "0.00000";
	self.totales.estadistica.excentosP                = "0.00000";
	self.totales.estadistica.excentosPImp             = "0.00000";
	self.totales.estadistica.totalVentasImp           = "0.00000";
}

/*----------------------------------------------------------------------------------------*/

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

function _InicializarJson(){
	self.totales ={
		uno_iva_01:{
			montoImpuestoUno01:"",
			montoVentaUno01:""
		},
		uno_iva_02:{
			montoImpuestoUno02:"",
			montoVentaUno02:""
		},
		uno_iva_03:{
			montoImpuestoUno03:"",
			montoVentaUno03:""
		},
		uno_iva_04:{
			montoImpuestoUno04:"",
			montoVentaUno04:""
		},
		uno_iva_05:{
			montoImpuestoUno05:"",
			montoVentaUno05:""
		},
		uno_iva_06:{
			montoImpuestoUno06:"",
			montoVentaUno06:""
		},
		uno_iva_07:{
			montoImpuestoUno07:"",
			montoVentaUno07:""
		},
		uno_iva_08:{
			montoImpuestoUno08:"",
			montoVentaUno08:""
		},
		unoTotal:{
			totalImpuestoUno:"",
			totalVentasUno:""
		},
		siete_iva_01:{
			montoImpuestoSiete01:"",
			montoVentaSiete01:""
		},
		siete_iva_02:{
			montoImpuestoSiete02:"",
			montoVentaSiete02:""
		},
		siete_iva_03:{
			montoImpuestoSiete03:"",
			montoVentaSiete03:""
		},
		siete_iva_04:{
			montoImpuestoSiete04:"",
			montoVentaSiete04:""
		},
		siete_iva_05:{
			montoImpuestoSiete05:"",
			montoVentaSiete05:""
		},
		siete_iva_06:{
			montoImpuestoSiete06:"",
			montoVentaSiete06:""
		},
		siete_iva_07:{
			montoImpuestoSiete07:"",
			montoVentaSiete07:""
		},
		siete_iva_08:{
			montoImpuestoSiete08:"",
			montoVentaSiete08:""
		},
		sieteTotal:{
			totalImpuestoSiete:"",
			totalVentasSiete:""
		},
		otros:{
			selectivoConsumo:{
				scImpuesto:"",
				scVentas:""
			},
			cemento:{
				cVentas:"",
				cImpuesto:""
			},
			otrosProductos:{
				oImpuesto:"",
				oVentas:""
			},
							total:{
				tImpuesto:"",
				tVentas:""
			}	
		},
		estadistica:
		{
			tarifaCeroP:"",
			tarifaReducidaUnoP:"",
			tarifaReducidaDosP:"",
			tarifareducidaCuatroP:"",
			transitorioCeroP:"",
			transitorioCuatroP:"",
			transitorioOchoP:"",
			tarifaGeneralP:"",
			otrosP:"",
			excentosP:"",
			totalVentasP:"",
			tarifaCeroImp:"",
			tarifaReducidaUnoPImp:"",
			tarifaReducidaDosPImp:"",
			tarifareducidaCuatroPImp:"",
			transitorioCeroPImp:"",
			transitorioCuatroPImp:"",
			transitorioOchoPImp:"",
			tarifaGeneralPImp:"",
			otrosPImp:"",
			excentosPImp:"",
			totalVentasImp:""
		}
	}
} 
		

	</script>
</consulta-iva>




































