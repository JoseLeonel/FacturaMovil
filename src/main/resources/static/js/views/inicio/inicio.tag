<grafico-inicio>
  
	<div class="row" show="{mostrarVentasXMes == true}">
			<div class="col-sm-9">
				<div class="card-box p-t-40"><iframe class="chartjs-hidden-iframe" style="display: block; overflow: hidden; border: 0px none; margin: 0px; inset: 0px; height: 100%; width: 100%; position: absolute; pointer-events: none; z-index: -1;" tabindex="-1"></iframe>
					<div class="row">
						<h3 class="portlet-title text-dark">
						   Ventas por  Mes
						</h3>
					</div>
					<canvas id="chart-totalventas" width="1011" height="252" style="display: block; width: 1011px; height: 252px;">
				</canvas></div>
			</div>
			<div class="col-sm-3" show="{nodisponible == true}">	
				<!-- circliful Chart -->
				<div class="row">
					<div class="col-sm-12 col-lg-12">
						<div class="widget-simple-chart text-center card-box">
							<h3 class="text-primary"> <span class="counter" id="totalVentasTotal">287357.08</span></h3>
							<p class="text-muted text-nowrap">TOTAL VENTAS DE SEPTIEMBRE</p>
						</div>
					</div>

					<div class="col-sm-12 col-lg-12">
						<div class="widget-simple-chart text-center card-box">
							<h3 class="text-success"> <span class="counter" id="totalVentasCosto">33501.46</span></h3>
							<p class="text-muted text-nowrap">COSTO DE SEPTIEMBRE</p>
						</div>
					</div>

					<div class="col-sm-12 col-lg-12">
						<div class="widget-simple-chart text-center card-box">                                            
							<h3 class="text-warning"> <span class="counter" id="totalVentasUtilidad">181054.54</span></h3>
							<p class="text-muted text-nowrap">UTILIDAD DE SEPTIEMBRE</p>
						</div>
					</div>

					<div class="col-sm-12 col-lg-12">
						<div class="widget-simple-chart text-center card-box">                                            
							<h3 class="text-warning"> <span class="counter" id="totalVentasUtilidad">181054.54</span></h3>
							<p class="text-muted text-nowrap">IVA EN VENTAS</p>
						</div>
					</div>

				</div>
				<!-- end circliful Chart -->
			</div>

		</div>
        <div class="row" show="{nodisponible == true}"> 
			<div class="col-lg-6">
				<div class="card-box"><iframe class="chartjs-hidden-iframe" style="display: block; overflow: hidden; border: 0px none; margin: 0px; inset: 0px; height: 100%; width: 100%; position: absolute; pointer-events: none; z-index: -1;" tabindex="-1"></iframe>
					<div class="row">
						<div class="col-lg-12">
							<h4 class="m-t-0 header-title"><b>Artículos más vendidos</b></h4>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-4">
							<div class="form-group">
								 <div id="masVendidosReportrange" class="form-control pull-left m-r-10">
									<i class="ion-calendar"></i>
									<span>01-09-2019 - 30-09-2019</span>
								</div>
							</div>
						</div>
					</div>
					<canvas id="chart-masvendidos" height="218" style="display: block; width: 654px; height: 218px;" width="654">
				</canvas></div>
			</div>

			<div class="col-lg-6">
				<div class="card-box"><iframe class="chartjs-hidden-iframe" style="display: block; overflow: hidden; border: 0px none; margin: 0px; inset: 0px; height: 100%; width: 100%; position: absolute; pointer-events: none; z-index: -1;" tabindex="-1"></iframe>
					<div class="row">
						<h4 class="m-t-0 header-title"><b>Articulos menos vendidos</b></h4>
					</div>
					<div class="row">
						<div class="col-lg-4">
							<div class="form-group">
								 <div id="vendedoresReportrange" class="form-control pull-left m-r-10">
									<i class="ion-calendar"></i>
									<span>01-09-2019 - 30-09-2019</span>
								</div>
							</div>
						</div>
					</div>
					<canvas id="chart-vendedores" height="218" style="display: block; width: 654px; height: 218px;" width="654">
					
				</canvas></div>
			</div>
		</div>

		<div class="row" show="{nodisponible == true}">
			
			<div class="col-sm-12 col-lg-6">
				<div class="card-box">
					 <h3 class="portlet-title text-dark">Productos debajo del stock mínimo</h3>
					 <div class="row">
					<div id="articulosbajostock_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer"><div class="row"><div class="col-sm-6"><div class="dataTables_length" id="articulosbajostock_length"><label>Mostrar <select name="articulosbajostock_length" aria-controls="articulosbajostock" class="form-control input-sm"><option value="10" selected="selected">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option><option value="250">250</option><option value="500">500</option><option value="1000">1000</option><option value="5000">5000</option></select> por página</label></div></div><div class="col-sm-6"></div></div><div class="row"><div class="col-sm-12"><div class="dataTables_scroll"><div class="dataTables_scrollHead" style="overflow: hidden; position: relative; border: 0px none; width: 100%;"><div class="dataTables_scrollHeadInner" style="box-sizing: content-box; width: 662px; padding-right: 12px;"><table class="table table-striped table-responsive dataTable no-footer" role="grid" style="margin-left: 0px; width: 662px;"><thead>
							<tr role="row"><th class="btn-primary sorting_asc" tabindex="0" aria-controls="articulosbajostock" rowspan="1" colspan="1" style="width: 65px;" aria-sort="ascending" aria-label="Código: activate to sort column descending">Código</th><th class="btn-primary sorting" tabindex="0" aria-controls="articulosbajostock" rowspan="1" colspan="1" style="width: 216px;" aria-label="Descripción: activate to sort column ascending">Descripción</th><th class="btn-primary sorting" tabindex="0" aria-controls="articulosbajostock" rowspan="1" colspan="1" style="width: 98px;" aria-label="Disponible: activate to sort column ascending">Disponible</th><th class="btn-primary sorting" tabindex="0" aria-controls="articulosbajostock" rowspan="1" colspan="1" style="width: 122px;" aria-label="Mínimo stock: activate to sort column ascending">Mínimo stock</th></tr>
						</thead></table></div></div><div class="dataTables_scrollBody" style="position: relative; overflow: auto; height: 200px; width: 100%;"><table class="table table-striped table-responsive dataTable no-footer" id="articulosbajostock" role="grid" aria-describedby="articulosbajostock_info" style="width: 100%;"><thead>
							<tr role="row" style="height: 0px;"><th class="btn-primary sorting_asc" aria-controls="articulosbajostock" rowspan="1" colspan="1" style="width: 65px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px;" aria-sort="ascending" aria-label="Código: activate to sort column descending"><div class="dataTables_sizing" style="height:0;overflow:hidden;">Código</div></th><th class="btn-primary sorting" aria-controls="articulosbajostock" rowspan="1" colspan="1" style="width: 216px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px;" aria-label="Descripción: activate to sort column ascending"><div class="dataTables_sizing" style="height:0;overflow:hidden;">Descripción</div></th><th class="btn-primary sorting" aria-controls="articulosbajostock" rowspan="1" colspan="1" style="width: 98px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px;" aria-label="Disponible: activate to sort column ascending"><div class="dataTables_sizing" style="height:0;overflow:hidden;">Disponible</div></th><th class="btn-primary sorting" aria-controls="articulosbajostock" rowspan="1" colspan="1" style="width: 122px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px;" aria-label="Mínimo stock: activate to sort column ascending"><div class="dataTables_sizing" style="height:0;overflow:hidden;">Mínimo stock</div></th></tr>
						</thead>
						
						<tbody>
							
						<tr id="10" role="row" class="odd"><td class="sorting_1">0006</td><td>PAPAS FRITAS</td><td>-154.00</td><td>0.00</td></tr><tr id="12" role="row" class="even"><td class="sorting_1">0008</td><td>SALSA EXTRA</td><td>-9.00</td><td>0.00</td></tr><tr id="13" role="row" class="odd"><td class="sorting_1">0009</td><td>CHILE EXTRA</td><td>-23.00</td><td>0.00</td></tr><tr id="14" role="row" class="even"><td class="sorting_1">0010</td><td>REFRESCO NATURAL</td><td>-9.00</td><td>1.00</td></tr><tr id="15" role="row" class="odd"><td class="sorting_1">0011</td><td>GASEOSO 600ML</td><td>-9.00</td><td>0.00</td></tr><tr id="19" role="row" class="even"><td class="sorting_1">0015</td><td>AGUA EN BOTELLA</td><td>-5.00</td><td>0.00</td></tr><tr id="20" role="row" class="odd"><td class="sorting_1">0016</td><td>TACO MEXICANO UBER</td><td>-59.00</td><td>0.00</td></tr><tr id="21" role="row" class="even"><td class="sorting_1">0017</td><td>TACO TICO UBER</td><td>-3.00</td><td>0.00</td></tr><tr id="28" role="row" class="odd"><td class="sorting_1">0024</td><td>TACO MEX MEDIO CHILE GLOVO</td><td>-7.00</td><td>0.00</td></tr><tr id="29" role="row" class="even"><td class="sorting_1">0025</td><td>TACO MEX CON CHILE GLOVO</td><td>-18.00</td><td>0.00</td></tr></tbody>
					</table></div></div></div></div><div class="row"><div class="col-sm-5"><div class="dataTables_info" id="articulosbajostock_info" role="status" aria-live="polite">Mostrando 1 de 426</div></div><div class="col-sm-7"><div class="dataTables_paginate paging_simple_numbers" id="articulosbajostock_paginate"><ul class="pagination"><li class="paginate_button previous disabled" id="articulosbajostock_previous"><a href="#" aria-controls="articulosbajostock" data-dt-idx="0" tabindex="0">Previous</a></li><li class="paginate_button active"><a href="#" aria-controls="articulosbajostock" data-dt-idx="1" tabindex="0">1</a></li><li class="paginate_button "><a href="#" aria-controls="articulosbajostock" data-dt-idx="2" tabindex="0">2</a></li><li class="paginate_button "><a href="#" aria-controls="articulosbajostock" data-dt-idx="3" tabindex="0">3</a></li><li class="paginate_button "><a href="#" aria-controls="articulosbajostock" data-dt-idx="4" tabindex="0">4</a></li><li class="paginate_button "><a href="#" aria-controls="articulosbajostock" data-dt-idx="5" tabindex="0">5</a></li><li class="paginate_button disabled" id="articulosbajostock_ellipsis"><a href="#" aria-controls="articulosbajostock" data-dt-idx="6" tabindex="0">…</a></li><li class="paginate_button "><a href="#" aria-controls="articulosbajostock" data-dt-idx="7" tabindex="0">426</a></li><li class="paginate_button next" id="articulosbajostock_next"><a href="#" aria-controls="articulosbajostock" data-dt-idx="8" tabindex="0">Next</a></li></ul></div></div></div></div>
					</div>
				</div>
			</div>
			 <div class="col-sm-12 col-lg-6">
				<div class="card-box">
					<h3 class="portlet-title text-dark">Cuentas por Pagar con mas tiempo del plazo establecido</h3>
					<div class="row">
						<div id="pedidosPendientes_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer"><div class="row"><div class="col-sm-6"><div class="dataTables_length" id="pedidosPendientes_length"><label>Mostrar <select name="pedidosPendientes_length" aria-controls="pedidosPendientes" class="form-control input-sm"><option value="10" selected="selected">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option><option value="250">250</option><option value="500">500</option><option value="1000">1000</option><option value="5000">5000</option></select> por página</label></div></div><div class="col-sm-6"></div></div><div class="row"><div class="col-sm-12"><div class="dataTables_scroll"><div class="dataTables_scrollHead" style="overflow: hidden; position: relative; border: 0px none; width: 100%;"><div class="dataTables_scrollHeadInner" style="box-sizing: content-box; width: 662px; padding-right: 12px;"><table class="table table-striped table-responsive dataTable no-footer" role="grid" style="margin-left: 0px; width: 662px;"><thead>
								<tr role="row"><th class="btn-primary sorting_asc" tabindex="0" aria-controls="pedidosPendientes" rowspan="1" colspan="1" style="width: 161px;" aria-sort="ascending" aria-label="N. Pedido: activate to sort column descending">N. Pedido</th><th class="btn-primary sorting" tabindex="0" aria-controls="pedidosPendientes" rowspan="1" colspan="1" style="width: 142px;" aria-label="Cliente: activate to sort column ascending">Cliente</th><th class="btn-primary sorting" tabindex="0" aria-controls="pedidosPendientes" rowspan="1" colspan="1" style="width: 113px;" aria-label="Fecha: activate to sort column ascending">Fecha</th><th class="btn-primary sorting" tabindex="0" aria-controls="pedidosPendientes" rowspan="1" colspan="1" style="width: 85px;" aria-label="Total: activate to sort column ascending">Total</th></tr>
							</thead></table></div></div><div class="dataTables_scrollBody" style="position: relative; overflow: auto; height: 200px; width: 100%;"><table class="table table-striped table-responsive dataTable no-footer" id="pedidosPendientes" role="grid" aria-describedby="pedidosPendientes_info" style="width: 100%;"><thead>
								<tr role="row" style="height: 0px;"><th class="btn-primary sorting_asc" aria-controls="pedidosPendientes" rowspan="1" colspan="1" style="width: 161px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px;" aria-sort="ascending" aria-label="N. Pedido: activate to sort column descending"><div class="dataTables_sizing" style="height:0;overflow:hidden;">N. Pedido</div></th><th class="btn-primary sorting" aria-controls="pedidosPendientes" rowspan="1" colspan="1" style="width: 142px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px;" aria-label="Cliente: activate to sort column ascending"><div class="dataTables_sizing" style="height:0;overflow:hidden;">Cliente</div></th><th class="btn-primary sorting" aria-controls="pedidosPendientes" rowspan="1" colspan="1" style="width: 113px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px;" aria-label="Fecha: activate to sort column ascending"><div class="dataTables_sizing" style="height:0;overflow:hidden;">Fecha</div></th><th class="btn-primary sorting" aria-controls="pedidosPendientes" rowspan="1" colspan="1" style="width: 85px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px;" aria-label="Total: activate to sort column ascending"><div class="dataTables_sizing" style="height:0;overflow:hidden;">Total</div></th></tr>
							</thead>
							
							<tbody>
								
							<tr id="43" role="row" class="odd"><td class="sorting_1">136-000000043</td><td>CONTADO</td><td>2018-09-17</td><td>570.00</td></tr><tr id="44" role="row" class="even"><td class="sorting_1">136-000000044</td><td>CONTADO</td><td>2018-10-08</td><td>799.09</td></tr><tr id="45" role="row" class="odd"><td class="sorting_1">136-000000045</td><td>YILANIA</td><td>2018-10-15</td><td>550.49</td></tr><tr id="47" role="row" class="even"><td class="sorting_1">136-000000047</td><td>alex2</td><td>2018-10-19</td><td>1120.49</td></tr><tr id="48" role="row" class="odd"><td class="sorting_1">136-000000048</td><td>alex1</td><td>2018-10-19</td><td>855.00</td></tr><tr id="51" role="row" class="even"><td class="sorting_1">136-000000051</td><td>Eugenia</td><td>2018-10-31</td><td>570.00</td></tr><tr id="64" role="row" class="odd"><td class="sorting_1">136-000000064</td><td>CONTADO</td><td>2018-11-20</td><td>835.49</td></tr><tr id="75" role="row" class="even"><td class="sorting_1">136-000000075</td><td>CONTADO</td><td>2019-01-23</td><td>620.00</td></tr><tr id="76" role="row" class="odd"><td class="sorting_1">136-000000076</td><td>ANA SALAZAR</td><td>2019-01-23</td><td>2000.00</td></tr><tr id="85" role="row" class="even"><td class="sorting_1">136-000000085</td><td>RIDS</td><td>2019-03-07</td><td>570.00</td></tr></tbody>
						</table></div></div></div></div><div class="row"><div class="col-sm-5"><div class="dataTables_info" id="pedidosPendientes_info" role="status" aria-live="polite">Mostrando 1 de 7</div></div><div class="col-sm-7"><div class="dataTables_paginate paging_simple_numbers" id="pedidosPendientes_paginate"><ul class="pagination"><li class="paginate_button previous disabled" id="pedidosPendientes_previous"><a href="#" aria-controls="pedidosPendientes" data-dt-idx="0" tabindex="0">Previous</a></li><li class="paginate_button active"><a href="#" aria-controls="pedidosPendientes" data-dt-idx="1" tabindex="0">1</a></li><li class="paginate_button "><a href="#" aria-controls="pedidosPendientes" data-dt-idx="2" tabindex="0">2</a></li><li class="paginate_button "><a href="#" aria-controls="pedidosPendientes" data-dt-idx="3" tabindex="0">3</a></li><li class="paginate_button "><a href="#" aria-controls="pedidosPendientes" data-dt-idx="4" tabindex="0">4</a></li><li class="paginate_button "><a href="#" aria-controls="pedidosPendientes" data-dt-idx="5" tabindex="0">5</a></li><li class="paginate_button "><a href="#" aria-controls="pedidosPendientes" data-dt-idx="6" tabindex="0">6</a></li><li class="paginate_button "><a href="#" aria-controls="pedidosPendientes" data-dt-idx="7" tabindex="0">7</a></li><li class="paginate_button next" id="pedidosPendientes_next"><a href="#" aria-controls="pedidosPendientes" data-dt-idx="8" tabindex="0">Next</a></li></ul></div></div></div></div>
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
self.meses = []
self.colores = []
self.datos = []
self.on('mount',function(){

	__cargaMeses();

});

function __cargaMeses(){
    self.meses = []
    self.colores = []
    self.datos = []
    self.update()
     $.ajax({
         url: "GraficoVentasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                 $.each(result.aaData, function( index, modeloTabla ) {
					self.meses.push(vectorMes(modeloTabla.mes))
					self.colores.push(vectorColores(modeloTabla.mes))
					self.datos.push(modeloTabla.total) 
				 })
				 graficoVentas()
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}
/**
* Grafico de ventas
**/
function graficoVentas(){
	var ctx = document.getElementById("chart-totalventas").getContext("2d");
	var graficoVenta = new Chart(ctx,{
		type:"bar",
		data:{
			labels:self.meses,
			datasets:[{
				label:'Ventas  ',
				data:self.datos,
				backgroundColor: self.colores
			}],
		},
		options:{
			scales:{
				yAxes:[{
					ticks:{
						beginAtZero:true
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
</grafico-inicio>