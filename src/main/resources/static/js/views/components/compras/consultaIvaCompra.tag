<compras-iva>
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
					<label for="pago_tipoVentaL">{$.i18n.prop("factura.estado.combo")} </label> 
					<select class="form-control estado" id="estado" name="estado"  >
						<option each={comboEstados} value="{estado}" selected="{factura.estado ==estado?true:false}" >{descripcion}</option>
					</select>
				</div>


				<div class="actividadEconomica">   
				<label class="titleListaPrecio">Actividades Comerciales </label>  
					<select class="form-control selectActividadComercial"  name="selectActividadComercial" id="selectActividadComercial" >
					    <option    value="0"  >{$.i18n.prop("todos.select")}</option> 
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
			      <caption>IVA(Compras) </caption>
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

			

			<div class="VentasSiete">
				<div class="ventasInfoSiete">
					<table id="tablaVentasSiete">
					<caption>IVA (Ventas) </caption>
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
					<div class="formulasRotuloFila2Col1"><p class="formatos">Otros<br/>Montos<br/></p></div>
					<div class="formulasRotuloFila2Col2"></div>
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
						<td rowspan="2"><p class="formatos">Exentos Ventas</p></td>
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
						<td rowspan="2"><p class="formatos">Exentos compras</p></td>
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
						<td rowspan="2"><p class="formatos">Exoneraciones ventas</p></td>
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
						<td rowspan="2"><p class="formatos">Exoneracion compras</p></td>
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
						<td rowspan="2"><p class="formatos">Otros gastos de ventas</p></td>
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
						<td rowspan="2"><p class="formatos">Otros gastos de compras</p></td>
						<td><div>{totales.estadistica.transitorioCuatroP}</div></td>
						<td rowspan="2"><div>{totales.estadistica.transitorioCuatroPImp}%</div></td>
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
		   flex-direction: column;
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

/*-----------------------------------------------------------------------------------------*/
	var self = this;
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
				 global: false,
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
				estado:6,
				descripcion:$.i18n.prop("tipo.factura.aceptadas")
			})
			self.comboEstados.push({
				estado:2,
				descripcion:$.i18n.prop("tipo.factura.facturado")
			})
			
			self.comboEstados.push({
				estado:7,
				descripcion:$.i18n.prop("tipo.factura.noaceptadas")
			})
			self.update()
		}
		
/*-----------------------------------------------------------------------------------------*/

		__Busqueda(){
			var totalVentaSinImpuesto = 0
			if(_validarDiasAConsultar()){
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
								
							})
							self.update()	
						},
						error: function (xhr, status) {
							console.log(xhr);
							mensajeErrorServidor(xhr, status);
						}
					})
				}	
			}
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



/**
* Validar rango de fechas 
**/
function _validarDiasAConsultar(){
	var fechaInicio = new Date($('#fechaInicial').val()).getTime();
	var fechaFin    = new Date($('#fechaFinal').val()).getTime();
	var diff = fechaFin - fechaInicio;
	var diff =diff/(1000*60*60*24)

	if(diff > 31){
		swal({
			title: '',
			text:  $.i18n.prop("maximo.rango.fechas"),
			type: 'error',
			showCancelButton: false,
			confirmButtonText:$.i18n.prop("btn.aceptar"),
		})
		_InicializarJson();
		self.update();
		return false;
	}
	return true; 
}
		

	</script>
</compras-iva>




































