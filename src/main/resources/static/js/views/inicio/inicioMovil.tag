<grafico-inicioMovil>
  
	<div class="row" show="{mostrarVentasXMes == true}">
			<div class="col-md-9 col-sx-12 col-sm-9 col-lg-9">
				<div class="card-box p-t-40"><iframe class="chartjs-hidden-iframe" style="display: block; overflow: hidden; border: 0px none; margin: 0px; inset: 0px; height: 100%; width: 100%; position: absolute; pointer-events: none; z-index: -1;" tabindex="-1"></iframe>
					
					<canvas id="chart-totalventas" ></canvas></div>
			</div>
			<div class="col-md-3 col-sx-12 col-sm-3 col-lg-3" show="{mostrarVentasXMes == false}">	
				<!-- circliful Chart -->
				<div class="row">
					<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
						<div class="widget-simple-chart text-center card-box">
							<h3 class="text-primary"> <span class="counter" id="totalVentasTotal">287357.08</span></h3>
							<p class="text-muted text-nowrap">TOTAL VENTAS DE SEPTIEMBRE</p>
						</div>
					</div>
                    
					<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
						<div class="widget-simple-chart text-center card-box">
							<h3 class="text-success"> <span class="counter" id="totalVentasCosto">33501.46</span></h3>
							<p class="text-muted text-nowrap">COSTO DE SEPTIEMBRE</p>
						</div>
					</div>

					<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
						<div class="widget-simple-chart text-center card-box">
							<h3 class="text-success"> <span class="counter" id="totalVentasCosto">33501.46</span></h3>
							<p class="text-muted text-nowrap">UTILIDAD DE SEPTIEMBRE</p>
						</div>
					</div>

				

				

				</div>
				<!-- end circliful Chart -->
			</div>

		</div>
        <div class="row" show="{mostrarVentasXMes == true}"> 
			<div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
				<div class="card-box">
					<h3 class="portlet-title text-dark">Productos mas Vendidos</h3>
					<div class="row">
			        	<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12" >
						    <table id="tableListarArticulosMasVendidos" class="display table responsive table-hover nowrap table-condensed tableListarArticulosMasVendidos "   style="width:100%">
								<thead>
									<tr>
										<th class = "table-header" style="width:5%;">{$.i18n.prop("articulo.codigo")}</th>
										<th class = "table-header" style="width:5%;">{$.i18n.prop("articulo.descripcion")}</th>
										<th class = "table-header" style="width:5%;">{$.i18n.prop("inventario.cantidad")}</th>
									</tr>
									</thead>
									<tfoot style="display: table-header-group;">
										<tr>
											<th style="width:5%;">{$.i18n.prop("articulo.codigo")}</th>
											<th style="width:5%;">{$.i18n.prop("articulo.descripcion")}</th>
											<th style="width:5%;">{$.i18n.prop("inventario.cantidad")}</th>
										</tr>
									</tfoot>
                            </table>							
    					</div>
					</div>
				</div>
			</div>

			<div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
				<div class="card-box"><iframe class="chartjs-hidden-iframe" style="display: block; overflow: hidden; border: 0px none; margin: 0px; inset: 0px; height: 100%; width: 100%; position: absolute; pointer-events: none; z-index: -1;" tabindex="-1"></iframe>
					<h3 class="portlet-title text-dark">Cuentas por Pagar con mas tiempo del plazo establecido</h3>
					<div class="row">
					    <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12" >
  						    <table id="tableListarCuentaPorPagar" class="display table responsive table-hover nowrap table-condensed tableListarCuentaPorPagar "   style="width:100%">
							<thead>
								<tr>
									<th class = "table-header" style="width:5%;">#Pedido</th>
									<th class = "table-header" style="width:5%;">Proveedor</th>
									<th class = "table-header" style="width:5%;">Fecha</th>
									<th class = "table-header" style="width:5%;">Saldo</th>
									<th class = "table-header" style="width:5%;">Dias Morosos</th>
								</tr>
						        </thead>
								<tfoot style="display: table-header-group;">
									<tr>
										<th style="width:5%;">#Pedido</th>
										<th style="width:5%;">Proveedor</th>
										<th style="width:5%;">Fecha</th>
										<th style="width:5%;">Saldo</th>
										<th style="width:5%;">Dias Morosos</th>
									</tr>
								</tfoot>
                                </table>							
						</div>
                    </div>              
				 </div>
			</div>
		</div>

		<div class="row" show="{mostrarVentasXMes == true}">
			<div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
				<div class="card-box">
					<h3 class="portlet-title text-dark">Productos debajo del stock mínimo</h3>
					<div class="row">
					        	<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12" >
  									    <table id="tableListarDebajoMinimo" class="display table responsive table-hover nowrap table-condensed tableListarDebajoMinimo "   style="width:100%">
											<thead>
												<tr>
													<th class = "table-header" style="width:5%;">{$.i18n.prop("articulo.codigo")}</th>
													<th class = "table-header" style="width:5%;">{$.i18n.prop("articulo.descripcion")}</th>
													<th class = "table-header" style="width:5%;">{$.i18n.prop("inventario.cantidad")}</th>
													<th class = "table-header" style="width:5%;">{$.i18n.prop("inventario.minimo")}</th>
												</tr>
											</thead>
											<tfoot style="display: table-header-group;">
												<tr>
													<th style="width:5%;">{$.i18n.prop("articulo.codigo")}</th>
													<th style="width:5%;">{$.i18n.prop("articulo.descripcion")}</th>
													<th style="width:5%;">{$.i18n.prop("inventario.cantidad")}</th>
													<th style="width:2%;">{$.i18n.prop("inventario.minimo")}</th>
												</tr>
											</tfoot>
                                        </table>							
								</div>
					</div>
				</div>
			</div>
			 <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
				<div class="card-box">
					<h3 class="portlet-title text-dark">Cuentas por Cobrar con mas tiempo del plazo establecido</h3>
					<div class="row">
					    <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12" >
  						    <table id="tableListarCuentaPorCobrar" class="display table responsive table-hover nowrap table-condensed tableListarCuentaPorCobrar "   style="width:100%">
							<thead>
								<tr>
									<th class = "table-header" style="width:5%;">#Factura</th>
									<th class = "table-header" style="width:5%;">Cliente</th>
									<th class = "table-header" style="width:5%;">Fecha</th>
									<th class = "table-header" style="width:5%;">Saldo</th>
									<th class = "table-header" style="width:5%;">Dias Morosos</th>
								</tr>
						        </thead>
								<tfoot style="display: table-header-group;">
									<tr>
										<th style="width:5%;">#Factura</th>
										<th style="width:5%;">Cliente</th>
										<th style="width:5%;">Fecha</th>
										<th style="width:5%;">Saldo</th>
										<th style="width:5%;">Dias Morosos</th>
									</tr>
								</tfoot>
                                </table>							
							</div>
    					</div>
				</div>
			</div>
		</div>

<style type="text/css">
	.text-muted {

		color: #98a6ad;

	}
	.text-muted {

		color: #777;

	}
	.text-nowrap {

		white-space: nowrap;

	}
	.text-primary {
		text-align: center;
		color: #00b19d;

	}
	.text-center {

		text-align: center;

	}
	.p-t-40 {
		padding-top: 40px;
	}
	.text-dark {
		color: #797979 !important;
	}
	h3 {
		line-height: 30px;
	}
	h1, h2, h3, h4, h5, h6 {

		color: #505458;
		font-family: 'Roboto', sans-serif;
		margin: 10px 0;

	}
	.card-box {
		padding: 20px;
		border: 1px solid rgba(54, 64, 74, 0.08);
		-webkit-border-radius: 5px;
		border-radius: 5px;
		-moz-border-radius: 5px;
		background-clip: padding-box;
		margin-bottom: 20px;
		background-color: #fff;
	}
  .containerPrincipal{
    display:flex;
    background: white;
  }
  .parteContainer1{
    background: white;
  }
  .parteContainer2{
    background: white;
  }
  .parteContainer3{
    background: white;
  }
  .parteContainer4{
    background: white;
  }
  
</style>

<script>
self = this
self.mostrarVentasXMes = true;
self.nodisponible = false
self.formato_tabla_Strock_minimo =[] 
self.meses = []
self.colores = []
self.datos = []
self.on('mount',function(){
    __InicializarTabla('.tableListarDebajoMinimo')
	__InicializarTabla('.tableListarCuentaPorCobrar')
	__InicializarTabla('.tableListarArticulosMasVendidos')
	__InicializarTabla('.tableListarCuentaPorPagar')
	__cargaMeses();
	__ArticulosMinimo()
	__CuentasXCobrarGrafico()
	__ArticulosMasVendido()
	__CuentasXPagarGrafico()

});

/**
*  Listar la tabla a aplicar el mantenimiento
**/
function __InicializarTabla(nombreTabla){
    $(nombreTabla).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [2, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        
    });    
}

function loadListar(table,idioma,formatoTabla,data){
	$(table).DataTable().destroy();
        $(table).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "scrollCollapse": true,
        "order": [0, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
       
        "columns": formatoTabla,
    });  
    $(table).dataTable().fnClearTable();
    $(table).dataTable().fnAddData(data);        
}
/**
*-------------------------------------Articulos mas vendido ---------------------------------------------------------------------
**/

function __ArticulosMasVendido(){
        $("#tableListarArticulosMasVendidos").dataTable().fnClearTable(); 
        $.ajax({
            url: "GraficoArticuloMasVendidoAjax.do",
            datatype: "json",
			global: false,
            method:"POST",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable_Articulo_MAS_VENDDIDO();
                    loadListarGraficoArticuloMas(".tableListarArticulosMasVendidos",idioma_espanol,self.formato_tabla_Strock_Articulo_Mas_Vendido,result.aaData)
                    agregarInputsCombos_Articulo_Mas_Vendido()
                    ActivarEventoFiltro(".tableListarArticulosMasVendidos")
                }else{
                    __InformacionDataTable_Articulo_MAS_VENDDIDO();
                    agregarInputsCombos_Articulo_Mas_Vendido()
                }           
            },
            error: function (xhr, status) {
                console,log(xhr);
            }
        });
}

/**
 *  Carga el listar de los mantenimientos
 */

function loadListarGraficoArticuloMas(table,idioma,formatoTabla,data){
	$(table).DataTable().destroy();
        $(table).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [2, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
       
        "columns": formatoTabla,
    });  
    $(table).dataTable().fnClearTable();
    $(table).dataTable().fnAddData(data);        
}


function agregarInputsCombos_Articulo_Mas_Vendido(){
     // Agregar los input de busqueda 
    $('.tableListarArticulosMasVendidos tfoot th').each( function (e) {
        var title = $('.tableListarArticulosMasVendidos thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
	      	  var name = '<input id = "filtroCampos' + e + '"';
            $(this).html(name + 'type="text" class="form-control"  placeholder="' + title + '" />');
    })
} 

/**
*Formato del listado 
**/

function __InformacionDataTable_Articulo_MAS_VENDDIDO(){
    self.formato_tabla_Strock_Articulo_Mas_Vendido = [ 
			{'data' : 'codigo'           ,"name":"codigo"       ,"title" : $.i18n.prop("articulo.codigo")       , "autoWidth" : true},
 			{'data' : 'descripcion'      ,"name":"descripcion"  ,"title" : $.i18n.prop("articulo.descripcion")  , "autoWidth" : true},
    	    {'data' : 'cantidadVendido'  ,"name":"cantidadVendido"     ,"title" : $.i18n.prop("inventario.cantidad")   , "autoWidth" : true},
   ];
   self.update();
}

/**
*-------------------------------------Articulos al minimo ---------------------------------------------------------------------
**/

function __ArticulosMinimo(){
        $("#tableListarDebajoMinimo").dataTable().fnClearTable(); 
        $.ajax({
            url: "ListarArticuloMinimoAjax.do",
            datatype: "json",
			global: false,
            method:"POST",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable_Minimo_Stock();
                    loadListarArticuloMinimo(".tableListarDebajoMinimo",idioma_espanol,self.formato_tabla_Strock_minimo,result.aaData)
                    agregarInputsCombos_Minimo_Stock()
                    ActivarEventoFiltro(".tableListarDebajoMinimo")
                }else{
                    __InformacionDataTable_Minimo_Stock();
                    agregarInputsCombos_Minimo_Stock()
                }           
            },
            error: function (xhr, status) {
                console,log(xhr);

            }
        });
}
function loadListarArticuloMinimo(table,idioma,formatoTabla,data){
	$(table).DataTable().destroy();
        $(table).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [2, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
       
        "columns": formatoTabla,
    });  
    $(table).dataTable().fnClearTable();
    $(table).dataTable().fnAddData(data);        
}

function agregarInputsCombos_Minimo_Stock(){
     // Agregar los input de busqueda 
    $('.tableListarDebajoMinimo tfoot th').each( function (e) {
        var title = $('.tableListarDebajoMinimo thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
	        var name = '<input id = "filtroCampos' + e + '"';
            $(this).html(name + 'type="text" class="form-control"  placeholder="' + title + '" />');
    })
} 

/**
*Formato del listado 
**/

function __InformacionDataTable_Minimo_Stock(){
    self.formato_tabla_Strock_minimo = [ 
			{'data' : 'codigo'        ,"name":"codigo"       ,"title" : $.i18n.prop("articulo.codigo")       , "autoWidth" : true},
 			{'data' : 'descripcion'   ,"name":"descripcion"  ,"title" : $.i18n.prop("articulo.descripcion")  , "autoWidth" : true},
    	    {'data' : 'cantidad1'     ,"name":"cantidad1"     ,"title" : $.i18n.prop("inventario.cantidad")   , "autoWidth" : true},
			{'data' : 'minimo'        ,"name":"minimo"       ,"title" : $.i18n.prop("inventario.minimo")     , "autoWidth" : true},
   ];
   self.update();
}

/**
*-------------------------------------Cuentas X Cobrar ---------------------------------------------------------------------
**/
function __CuentasXCobrarGrafico(){
        $("#tableListarCuentaPorCobrar").dataTable().fnClearTable(); 
        $.ajax({
            url: "GraficoCuentasXCobrarAjax.do",
            datatype: "json",
			global: false,
            method:"POST",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable_CuentaPorCobrar();
                    loadListarCuentaCobrar(".tableListarCuentaPorCobrar",idioma_espanol,self.formato_tabla_CuentaPorCobrar,result.aaData)
                    agregarInputsCombos_CuentaPorCobrar()
                    ActivarEventoFiltro(".tableListarCuentaPorCobrar")
                }else{
                    __InformacionDataTable_CuentaPorCobrar();
                    agregarInputsCombos_CuentaPorCobrar()
                }           
            },
            error: function (xhr, status) {
                console,log(xhr);

            }
        });
}
function loadListarCuentaCobrar(table,idioma,formatoTabla,data){
	$(table).DataTable().destroy();
        $(table).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [4, 'asc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
       
        "columns": formatoTabla,
    });  
    $(table).dataTable().fnClearTable();
    $(table).dataTable().fnAddData(data);        
}


function agregarInputsCombos_CuentaPorCobrar(){
     // Agregar los input de busqueda 
    $('.tableListarCuentaPorCobrar tfoot th').each( function (e) {
        var title = $('.tableListarCuentaPorCobrar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
    })
} 

/**
*Formato del listado 
**/


function __InformacionDataTable_CuentaPorCobrar(){
    self.formato_tabla_CuentaPorCobrar = [ 
			{'data' : 'numeroFactura'    ,"name":"numeroFactura"   ,"title" : "#Factura/Tiquete"  , "autoWidth" : true},
 			{'data' : 'nombreCompleto'   ,"name":"nombreCompleto"  ,"title" : "Cliente"             , "autoWidth" : true,
			    "render":function(nombreCompleto,type, row){
					return  row.nombreCompleto.length >35?row.nombreCompleto.substring(0, 35):row.nombreCompleto;
                }
			 },
			{'data' : 'fechaPlazoSTR'    ,"name":"fechaPlazoSTR"    ,"title" : "Fecha"             , "autoWidth" : true},
    	    {'data' : 'totalSaldo'       ,"name":"totalSaldo"       ,"title" : "Saldo"             , "autoWidth" : true},
			{'data' : 'totalDiasMoroso'  ,"name":"totalDiasMoroso"  ,"title" : "Dias Morosos"      , "autoWidth" : true},
   ];
   self.update();
}


/**
*-------------------------------------Cuentas X Pagar ---------------------------------------------------------------------
**/
function __CuentasXPagarGrafico(){
        $("#tableListarCuentaPorPagar").dataTable().fnClearTable(); 
        $.ajax({
            url: "GraficoCuentasXPagarAjax.do",
            datatype: "json",
			global: false,
            method:"POST",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable_CuentaPorPagar();
                    loadListarCuentaCobrar(".tableListarCuentaPorPagar",idioma_espanol,self.formato_tabla_CuentaPorPagar,result.aaData)
                    agregarInputsCombos_CuentaPorPagar()
                    ActivarEventoFiltro(".tableListarCuentaPorPagar")
                }else{
                    __InformacionDataTable_CuentaPorPagar();
                    agregarInputsCombos_CuentaPorPagar()
                }           
            },
            error: function (xhr, status) {
                console,log(xhr);

            }
        });
}

function agregarInputsCombos_CuentaPorPagar(){
     // Agregar los input de busqueda 
    $('.tableListarCuentaPorPagar tfoot th').each( function (e) {
        var title = $('.tableListarCuentaPorPagar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
    })
} 

/**
*Formato del listado 
**/


function __InformacionDataTable_CuentaPorPagar(){
    self.formato_tabla_CuentaPorPagar	 = [ 
			{'data' : 'consecutivo'       ,"name":"consecutivo"   ,"title" : "#Pedido"  , "autoWidth" : true},
 			{'data' : 'nombreCompleto'   ,"name":"nombreCompleto"  ,"title" : "Proveedor"             , "autoWidth" : true,
			    "render":function(nombreCompleto,type, row){
					return  row.nombreCompleto.length >35?row.nombreCompleto.substring(0, 35):row.nombreCompleto;
                }
			 },
			{'data' : 'fechaCreditoSTR'  ,"name":"fechaCreditoSTR"  ,"title" : "Fecha"             , "autoWidth" : true},
    	    {'data' : 'totalSaldo'       ,"name":"totalSaldo"       ,"title" : "Saldo"             , "autoWidth" : true},
			{'data' : 'totalDiasMoroso'  ,"name":"totalDiasMoroso"  ,"title" : "Dias Morosos"      , "autoWidth" : true},
   ];
   self.update();
}




function __cargaMeses(){
    self.meses = []
    self.colores = []
    self.datos = []
	self.mostrarVentasXMes = false;
    self.update()
     $.ajax({
         url: "GraficoVentasAjax.do",
        datatype: "json",
		global: false,
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                 $.each(result.aaData, function( index, modeloTabla ) {
					self.meses.push(vectorMes(modeloTabla.mes))
					self.colores.push(vectorColores(modeloTabla.mes))
					self.datos.push(modeloTabla.total) 
				 })
				 self.mostrarVentasXMes = true;
				 self.update()
				 graficoVentas()
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
            console,log(xhr);

        }
    })
}

/**
* Grafico de ventas
**/
function graficoVentas(){
	if(typeof(graficoVenta) != "undefined"){
		graficoVenta.destroy();
		graficoVenta.removeData();
		window.graficoVenta.destroy();
	}
   if(self.datos.length == 0){
      self.mostrarVentasXMes = false;
      self.update()
	  return
    }
	var ctx = document.getElementById("chart-totalventas").getContext("2d");
	var max = Math.max.apply(null,self.datos);
    max = max >= 300 ? max+300: max;
    var minData = Math.min.apply(null,self.datos);
    minData = minData >= 50 ? 300 : minData;
	var graficoVenta = new Chart(ctx,{
		type:"bar",
		data:{
			labels:self.meses,
			datasets:[{
				label:'Ventas  ',
				data:self.datos,
				backgroundColor: self.colores,
				borderColor: 'rgb(279,89,50)',
				borderWidth: 0.5,
			}],
		},
		options:{
			responsive: true,
					legend: {
						position: 'top',
					},
					title: {
						display: true,
						text: 'Ventas por mes'
					},
			scales:{
				yAxes:[{
					
					ticks:{
						beginAtZero:true,
						 min: minData
					}
				}]
			}
		}
	})
}

function vectorMes(mes){
   if(mes == 1){
	   return 'Enero'
   }else if(mes == 2){
	   return 'Febrero'
   }else if(mes == 3){
	   return 'Marzo'
   }else if(mes == 4){
	   return 'Abril'
   }else if(mes == 5){
	   return 'Mayo'
   }else if(mes == 6){
	   return 'Junio'
   }else if(mes == 7){
	   return 'Julio'
   }else if(mes == 8){
	   return 'Agosto'
   }else if(mes == 9){
	   return 'Septiembre'
   }else if(mes== 10){
	   return 'Octubre'
   }else if(mes == 11){
	   return 'Noviembre'
   }else if (mes == 12){
        return 'Diciembre'
   }
   return 'Sin definir'
}

function vectorColores(mes){
   if(mes == 1){
	   return 'rgb(66,134,244)'
   }else if(mes == 2){
	   return 'rgb(74,135,72)'
   }else if(mes == 3){
	   return 'rgb(279,89,50)'
   }else if(mes == 4){
	   return 'rgb(66,134,244)'
   }else if(mes == 5){
	   return 'rgb(74,135,72)'
   }else if(mes == 6){
	   return 'rgb(279,89,50)'
   }else if(mes == 7){
	   return 'rgb(66,134,244)'
   }else if(mes == 8){
	   return 'rgb(74,135,72)'
   }else if(mes == 9){
	   return 'rgb(279,89,50)'
   }else if(mes== 10){
	   return 'rgb(66,134,244)'
   }else if(mes == 11){
	   return 'rgb(74,135,72)'
   }else if (mes == 12){
        return 'rgb(279,89,50)'
   }
   return 'rgb(279,89,50)'
}

</script>
</grafico-inicioMovil>