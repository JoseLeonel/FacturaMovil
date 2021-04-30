<articulo-proveedores>
                            <div class="row">
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12 ">
                                    <span>Consulta de productos por proveedor. <strong>Nota:</strong>El rango de fechas es opcional .Es utilizado para tener la cantidad por dia de venta a la semana.(10 % estimacion por semana)</span>
                                </div>
                            </div>    

							<div class="box">
								<div class="box-body" >
									<div class="planel-body">
                                        <div class="row">
                                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 ">
                                                  <span >Proveedores </span>
				                                  <select onchange= {__Busqueda}   class="form-control proveedores"   name="proveedores" id="proveedores"  data-live-search="true">
                                                        <option  data-tokens="Seleccionar"   value="0"  >Seleccionar</option>
                                                  </select>
                                            </div>       
                                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 ">
                                                <span  >Fecha Inicial </span>
                                                <div  class="form-group input-group date" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                                    <input type="text" class="form-control fechaInicial" id="fechaInicial"  name= "fechaInicial" readonly>
                                                    <div class="input-group-addon">
                                                        <span class="glyphicon glyphicon-th"></span>
                                                    </div>
                                                </div>	                             
                                            </div>       
                                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 ">
                                                <span  >Fecha Final </span>
                                               <div  class="form-group input-group date" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                                    <input type="text" class="form-control fechaFinal" id="fechaFinal"  name= "fechaFinal" readonly>
                                                    <div class="input-group-addon">
                                                        <span class="glyphicon glyphicon-th"></span>
                                                    </div>
                                                </div>	                             

                                            </div>       
                                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 ">
                                                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada formatoBoton" title ="Consultar" name="button" style="
    margin-top: 20px;" ><i class="fa fa-refresh"></i></button>
                                            </div>
                                        </div>            
										<div class="row"  style="overflow-y: scroll;height: 200px;">
                                         
                                     		<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12" >
												<table id="tableListar" 
													class="display table responsive table-hover nowrap table-condensed tableListar ">
													<thead>
														<tr>
   															<th style="width: 4%;" class="table-header">Codigo</th>
                                                            <th style="width: 4%;" class="table-header">descripcion</th>
                                                            <th style="width: 4%;" class="table-header">Cant</th>
															<th style="width: 4%;" class="table-header">Costo Prov</th>
															<th style="width: 4%;" class="table-header">Costo Inv </th>
                                                            <th style="width: 4%;" class="table-header">Ganancia </th>
                                                            <th style="width: 4%;" class="table-header">Precio Publico </th>
                                                            
														</tr>
													</thead>
                                                     <tbody>
                                                           <tr each={articuloProveedor.aaData} onclick={__BusquedaOtrosProveedores}>
                                                                 <td  style="width:20%;" >
                                                                    <span>{codigo}</span>
                                                                </td>
                                                                 <td  style="width:30%;">
                                                                    <span>{descripcion}</span>
                                                                </td>
                                                                 <td  style="width:10%;">
                                                                    <span class="{cantidad > 0?'cantidadInventario':'cantidadNegativo'}">{cantidad}</span>
                                                                </td>
                                                                 <td  style="width:10%;">
                                                                    <span class="cantidadInventario">{costoProveedorSTR}</span>
                                                                </td>
                                                                 <td  style="width:10%;">
                                                                    <span>{costoInventarioSTR}</span>
                                                                </td>
                                                                 <td  style="width:10%;">
                                                                    <span>{gananciaSTR}</span>
                                                                </td>
                                                                 <td  style="width:10%;">
                                                                    <span>{precioSTR}</span>
                                                                </td>
                                                            </tr>    
                                                     </tbody>      
												</table>
											</div>
										</div>
                                        <div class="row"  show={mostrarProveedores}>
                                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                                <span><strong>Otros proveedores que venden el producto :{descripcionProducto.length > 25?descripcionProducto.substring(0,25):descripcionProducto}</strong></span>
                                            </div>
                                        </div>
										<div class="row" show={mostrarProveedores}>
											<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12" >
												<table 
													class="display table responsive table-hover nowrap table-condensed tableListar ">
													<thead>
														<tr>
   															<th style="width: 4%;" class="table-header">Cedula</th>
                                                            <th style="width: 4%;" class="table-header">Proveedor </th>
															<th style="width: 4%;" class="table-header">Costo Proveedor </th>
														</tr>
													</thead>
                                                     <tbody>
                                                           <tr each={articuloOtrosProveedor.aaData}>
                                                                 <td  style="width:20%;" >
                                                                    <span>{cedula}</span>
                                                                </td>
                                                                 <td  style="width:30%;">
                                                                    <span>{nombreCompleto}</span>
                                                                </td>
                                                                 <td  style="width:10%;">
                                                                    <span class="{costoProveedorPrincipal > costoProveedor ?'cantidadPositivo':'cantidadNegativo'}">{costoProveedorPrincipal > costoProveedor?costoProveedorSTR + ' Barato':costoProveedorSTR + ' caro'}</span>
                                                                </td>
                                                            </tr>    
                                                     </tbody>      
												</table>
											</div>
										</div>
                                        
                                         <div class="row" show={mostrarVentaSemanal}>
                                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                                <span><strong>Cantidad de ventas por semana :{descripcionProducto.length > 25?descripcionProducto.substring(0,25):descripcionProducto}</strong></span>
                                            </div>
                                        </div>
										<div class="row"  show={mostrarVentaSemanal}>
											<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12" >
												<table 
													class="display table responsive table-hover nowrap table-condensed tableListarVentaSemana ">
													<thead>
														<tr>
   															
                                                            <th style="width: 4%;" class="table-header">Lunes </th>
                                                            <th style="width: 4%;" class="table-header">Martes </th>
                                                            <th style="width: 4%;" class="table-header">Miercoles </th>
                                                            <th style="width: 4%;" class="table-header">Jueves </th>
                                                            <th style="width: 4%;" class="table-header">Viernes </th>
                                                            <th style="width: 4%;" class="table-header">Sabado </th>
                                                            <th style="width: 4%;" class="table-header">Domingo</th>
														</tr>
													</thead>
                                                     <tbody>
                                                           <tr >
                                                                 <td  style="width:14%;">
                                                                    <span class="{estadoLunes}">{totalLunes}</span>
                                                                </td>
                                                                 <td  style="width:14%;">
                                                                    <span class="{estadoMartes}">{totalMartes}</span>
                                                                </td>
                                                                 <td  style="width:14%;">
                                                                    <span class="{estadoMiercoles}">{totalMiercoles}</span>
                                                                </td>
                                                                 <td  style="width:14%;">
                                                                    <span class="{estadoJueves}">{totalJueves}</span>
                                                                </td>
                                                                 <td  style="width:14%;">
                                                                    <span class="{estadoViernes}">{totalViernes}</span>
                                                                </td>
                                                                 <td  style="width:14%;">
                                                                    <span class="{estadoSabado}">{totalSabado}</span>
                                                                </td>
                                                                 <td  style="width:14%;">
                                                                    <span class="{estadoDomingo}">{totalDomingo}</span>
                                                                </td>

                                                            </tr>    
                                                     </tbody>      
												</table>
                                                <div>
                                                    <span><strong>Total Articulo: {totalVendido} Promedio por Dia: {promedioVentaPorDia}  Total Venta: {totalVentaSTR}</strong> <strong class = "{alarmaInventario}">{mensajeAlarma}</strong></span>
                                                </div>
											</div>
										</div>

								    </div>
							    </div>
						    </div>
         



<style type="text/css"  >

.botonCompra{
        cursor: pointer;
    background-color: #3c8dbc!important;
    color: white !important;
    font-size: 14px !important;
    font-weight: 600 !important;
    border-radius: 14px !important;
    flex: 1;
    padding-top: 5px;
    margin-right: 5px;
    height: 32px;
    text-align: center;
        margin-top: 5px;
}
 
 .box{
    color: #000000 !important;
    background: #c2c5c5 !important;
 }
 .table-header {
     background: #c2c5c5 !important;
     color: #000000!important;
 }
 .dataTables_wrapper .dataTables_filter input {
    margin-left: 1.5em !important;
    height: 30px !important;
    border-radius: 10px !important;
    font-size: 16px !important;
}
.alarmaInventarioRed{
    padding: 9px;
    background: red;
    border-radius: 11px;
    color: white;
    font-size: 14px;
    font-weight: 800;
}
.alarmaInventarioGreen{
    padding: 9px;
    background: #6dca42;
    border-radius: 11px;
    color: white;
    font-size: 12px;
    font-weight: 800;

}
.cantidadNegativo{
    color: white;
    background: red;
    padding: 5px;
    text-align: center;
    align-items: center;
    border-radius: 5px;
    font-weight: 800;
    font-size: 12px;
}

.cantidadPositivo{
    color: white;
    background: #3c8dbc;
    padding: 5px;
    text-align: center;
    align-items: center;
    border-radius: 5px;
    font-weight: 800;
    font-size: 12px;
}
.cantidadInventario{
    color: white;
    background: #3c8dbc;
    padding: 5px;
    text-align: center;
    align-items: center;
    border-radius: 5px;
    font-weight: 800;
    font-size: 12px;
}

</style>
	<script>
		var self = this;
        self.mostrarProveedores = false
        self.mostrarVentaSemanal = false
        self.mensajeAlarma = ""
        self.promedioVentaPorDia = 0
        self.comprarArticuloTotal = 0
        
        self.estadoDomingo = ""
        self.estadoSabado = ""
        self.estadoViernes = ""
        self.estadoJueves = ""
        self.estadoMiercoles = ""
        self.estadoMartes = ""
        self.estadoLunes = ""
        self.costoProveedorPrincipal = 0
        self.totalDomingo = 0
        self.totalLunes = 0
         self.totalMartes = 0
        self.totalMiercoles = 0
        self.totalJueves = 0
        self.totalViernes = 0
        self.totalSabado = 0
        
        self.cantidadDomingo = 0
        self.cantidadLunes = 0
         self.cantidadMartes = 0
        self.cantidadMiercoles = 0
        self.cantidadJueves = 0
        self.cantidadViernes = 0
        self.cantidadSabado = 0


        self.descripcionProducto = ""
       
        self.totalVendido = 0
        self.totalVentaSTR = 0
        self.articuloProveedor = {aaData:[]}
        self.articuloOtrosProveedor = {aaData:[]}
        self.articuloCantidadVendido = {aaData:[]}
        self.detail = []
        self.articulos = {data:[]}
        self.detalleCompra  = null
        self.consecutivo = null
        self.item = null;
        self.articulo = null;
        self.mostrarDetalles = false;

		//Se cargan al montar el tag
		self.on('mount',function(){
	        __agregarArticulos()
            cargarProveedores() 
         

		});

/**
*busqueda de articulos de un proveedor
**/
__Busqueda(e){
  
  DatosArticuloProveedor($('#proveedores').val())
}

function DatosArticuloProveedor(id){
    self.articuloProveedor = {aaData:[]}
    self.mostrarProveedores = false
    self.mostrarVentaSemanal = false

    self.update()
    $.ajax({
        url: "ListarArticulosPorProveedor.do",
       data :{idProveedor: id},
       datatype: "json",
       method:"GET",
       success: function (result) {
           self.articuloProveedor.aaData = result.aaData
           self.update()
           
       },
       error: function (xhr, status) {
           console.log(xhr);
            mensajeErrorServidor(xhr, status);
       }
    })

}

__BusquedaOtrosProveedores(e){
  self.item = e.item
  self.mostrarProveedores = false
  self.mostrarVentaSemanal = false
  self.costoProveedorPrincipal = 0
  self.update()

  DatosArticuloOtrosProveedor($('#proveedores').val(),self.item)
  DatosArticuloCantidadVendido($('#proveedores').val(),self.item)
}

/**
 * 
 */
function DatosArticuloCantidadVendido(id,articulo){
    self.totalDomingo = 0
    self.totalLunes = 0
    self.totalMartes = 0
    self.totalMiercoles = 0
    self.totalJueves = 0
    self.totalViernes = 0
    self.totalSabado = 0
    self.totalVendido = 0
    self.totalVentaSTR = 0
    self.comprarArticuloTotal = 0
     self.promedioVentaPorDia = 0
    self.mensajeAlarma = ""
    self.articuloCantidadVendido = {aaData:[]}
    self.estadoDomingo = ""
    self.estadoSabado = ""
    self.estadoViernes = ""
    self.estadoJueves = ""
    self.estadoMiercoles = ""
    self.estadoMartes = ""
    self.estadoLunes = ""

    self.update()
      

    var fechaInicial = $('.fechaInicial').val()
    var fechaFinal = $('.fechaFinal').val()

    if (typeof fechaInicial === 'undefined') {
       return
    }
    if (typeof fechaFinal === 'undefined') {
       return  
    }
    if (fechaInicial === null) {
       return
    }
    if (fechaFinal === null) {
       return  
    }
    if (fechaInicial.length == 0) {
       return
    }
    if (fechaFinal.length == 0) {
       return  
    }
    obtenerCantidadesPorDiaSemana($('.fechaInicial').val(),$('.fechaFinal').val())
    $.ajax({
        url: "ArticulosCantidadVendido.do",
       data :{idCodigo:articulo.codigo,fechaInicial:$('.fechaInicial').val(),fechaFinal:$('.fechaFinal').val()},
       datatype: "json",
       method:"GET",
       success: function (result) {
           self.articuloCantidadVendido.aaData = result.aaData
           self.descripcionProducto =articulo.descripcion
           self.update()
            $.each(self.articuloCantidadVendido.aaData, function( index, modeloTabla ) {
                switch (modeloTabla.diaSemana) {
                    case 1:
                          self.totalDomingo = self.totalDomingo + modeloTabla.cantidad
                          self.totalVendido = self.totalVendido + modeloTabla.cantidad
                          self.totalVentaSTR = self.totalVentaSTR + modeloTabla.totalVenta
                          
                          self.mostrarVentaSemanal = true
                     break;
                    case 2:
                          self.totalLunes = self.totalLunes + modeloTabla.cantidad
                          self.totalVendido = self.totalVendido + modeloTabla.cantidad
                          self.totalVentaSTR = self.totalVentaSTR + modeloTabla.totalVenta
                          self.mostrarVentaSemanal = true
                     break;
                    case 3:
                          self.totalMartes = self.totalMartes + modeloTabla.cantidad
                          self.totalVendido = self.totalVendido + modeloTabla.cantidad
                          self.totalVentaSTR = self.totalVentaSTR + modeloTabla.totalVenta
                          self.mostrarVentaSemanal = true
                     break;
                    case 4:
                          self.totalMiercoles = self.totalMiercoles + modeloTabla.cantidad
                          self.totalVendido = self.totalVendido + modeloTabla.cantidad
                          self.totalVentaSTR = self.totalVentaSTR + modeloTabla.totalVenta
                          self.mostrarVentaSemanal = true
                     break;
                    case 5:
                          self.totalJueves = self.totalJueves + modeloTabla.cantidad
                          self.totalVendido = self.totalVendido + modeloTabla.cantidad
                          self.totalVentaSTR = self.totalVentaSTR + modeloTabla.totalVenta
                          self.mostrarVentaSemanal = true
                     break;
                    case 6:
                          self.totalViernes = self.totalViernes + modeloTabla.cantidad
                          self.totalVendido = self.totalVendido + modeloTabla.cantidad
                          self.totalVentaSTR = self.totalVentaSTR + modeloTabla.totalVenta
                          self.mostrarVentaSemanal = true
                     break;
                    case 7:
                          self.totalSabado = self.totalSabado + modeloTabla.cantidad
                          self.totalVendido = self.totalVendido + modeloTabla.cantidad
                          self.totalVentaSTR = self.totalVentaSTR + modeloTabla.totalVenta
                          self.mostrarVentaSemanal = true
                     break;

                }
            });
            var promedioSemanal = 0
            self.estadoDomingo = self.totalDomingo > 0? "cantidadPositivo":"cantidadNegativo"
            self.totalDomingo = self.totalDomingo > 0?  self.totalDomingo / self.cantidadDomingo:0
            promedioSemanal = promedioSemanal + self.totalDomingo
            self.totalDomingo = formatoDecimales(self.totalDomingo,2)
            self.estadoLunes = self.totalLunes > 0? "cantidadPositivo":"cantidadNegativo"
            self.totalLunes = self.totalLunes > 0? self.totalLunes / self.cantidadLunes:0
            promedioSemanal = promedioSemanal + self.totalLunes
            self.totalLunes = formatoDecimales(self.totalLunes,2)
            self.estadoMartes = self.totalMartes > 0? "cantidadPositivo":"cantidadNegativo"
            self.totalMartes = self.totalMartes > 0? self.totalMartes / self.cantidadMartes:0
            promedioSemanal = promedioSemanal + self.totalMartes
            self.totalMartes = formatoDecimales(self.totalMartes,2)
            self.estadoMiercoles = self.totalMiercoles > 0? "cantidadPositivo":"cantidadNegativo"
            self.totalMiercoles =self.totalMiercoles > 0? self.totalMiercoles / self.cantidadMiercoles:0
            promedioSemanal = promedioSemanal + self.totalMiercoles
            self.totalMiercoles = formatoDecimales(self.totalMiercoles,2)
            self.estadoJueves = self.totalJueves > 0? "cantidadPositivo":"cantidadNegativo"
            self.totalJueves = self.totalJueves > 0?self.totalJueves / self.cantidadJueves:0
            promedioSemanal = promedioSemanal + self.totalJueves
            self.totalJueves = formatoDecimales(self.totalJueves,2)
            self.estadoViernes = self.totalViernes > 0? "cantidadPositivo":"cantidadNegativo"
            self.totalViernes = self.totalViernes > 0? self.totalViernes / self.cantidadViernes:0
            promedioSemanal = promedioSemanal + self.totalViernes
            self.totalViernes = formatoDecimales(self.totalViernes,2)
            self.estadoSabado = self.totalSabado > 0? "cantidadPositivo":"cantidadNegativo"
            self.totalSabado = self.totalSabado >  0?self.totalSabado / self.cantidadSabado:0
            promedioSemanal = promedioSemanal + self.totalSabado
            self.totalSabado = formatoDecimales(self.totalSabado,2)
            self.promedioVentaPorDia = promedioSemanal > 0?promedioSemanal / 7:0
            self.promedioVentaPorDia = self.promedioVentaPorDia  * 1,10 // Promedio colchon por dia 
            var cantidadPorSemana = self.promedioVentaPorDia  * 7
            self.promedioVentaPorDia = formatoDecimales(self.promedioVentaPorDia,2) 
            var resultado = articulo.cantidad  - cantidadPorSemana

            self.comprarArticuloTotal = resultado;
            if (self.comprarArticuloTotal >= 0) {
                self.mensajeAlarma = "Inventario suficiente"
                self.alarmaInventario = 'alarmaInventarioGreen'
            }else{
                self.comprarArticuloTotal = self.comprarArticuloTotal < 0 ?self.comprarArticuloTotal * -1:self.comprarArticuloTotal
                self.mensajeAlarma = "Comprar al Proveedor:" +formatoDecimales(self.comprarArticuloTotal,2)
                self.alarmaInventario = 'alarmaInventarioRed'

            }

            self.totalVendido = formatoDecimales(self.totalVendido,2)
            self.totalVentaSTR = formatoDecimales(self.totalVentaSTR,2)
            self.update()
           
       },
       error: function (xhr, status) {
           console.log(xhr);
            mensajeErrorServidor(xhr, status);
       }
    })

}

function  obtenerCantidadesPorDiaSemana(fechaInicial,fechaFinal){
    self.cantidadDomingo = 0
    self.cantidadLunes = 0
    self.cantidadMartes = 0
    self.cantidadMiercoles = 0
    self.cantidadJueves = 0
    self.cantidadViernes = 0
    self.cantidadSabado = 0
    self.update()
    console.log(fechaInicial)
    var inicio = moment(fechaInicial,'YYYY-MM-DD'); //Fecha inicial
    var fin = moment(fechaFinal,'YYYY-MM-DD'); //Fecha final
    var diffDays = fin.diff(inicio, 'days');
    var day = 0

    for (var i=0; i <= diffDays; i++) {
        day = moment(inicio, 'YYYY-MM-DD').weekday() 
        console.log(day)
        day = __valorNumerico(day)
        //0 => Domingo
        self.cantidadDomingo = day === 0? self.cantidadDomingo + 1:self.cantidadDomingo
        //1 => Lunes
        self.cantidadLunes = day  === 1? self.cantidadLunes + 1:self.cantidadLunes
        //2 => Martes
        self.cantidadMartes = day === 2? self.cantidadMartes + 1:self.cantidadMartes
        //3 => Miercoles
        self.cantidadMiercoles = day === 3? self.cantidadMiercoles + 1:self.cantidadMiercoles
        //4 => Jueves
        self.cantidadJueves = day === 4? self.cantidadJueves + 1:self.cantidadJueves
        //5 => Viernes
        self.cantidadViernes = day === 5? self.cantidadViernes + 1:self.cantidadViernes
        //6 => Sabados
        self.cantidadSabado = day === 6? self.cantidadSabado + 1:self.cantidadSabado

        inicio = moment(inicio, "YYYY-MM-DD").add(1, 'days').format('YYYY-MM-DD');
    }

   self.update()

}

/**
 * Lista de proveedores
 */
function DatosArticuloOtrosProveedor(id,articulo){
    self.articuloOtrosProveedor = {aaData:[]}
    self.costoProveedorPrincipal  = articulo.costoProveedor
    self.descripcionProducto = ""
    self.update()
    $.ajax({
        url: "ArticulosPorOtrosProveedorCodigo.do",
       data :{idProveedor: id,codigo:articulo.codigo},
       datatype: "json",
       method:"GET",
       success: function (result) {
            if(result.aaData.length > 0){
                self.descripcionProducto =articulo.descripcion
                self.articuloOtrosProveedor.aaData = result.aaData
                self.mostrarProveedores = true
                self.update()
            }
           
       },
       error: function (xhr, status) {
           console.log(xhr);
            mensajeErrorServidor(xhr, status);
       }
    })

}

/**
 * Lista de proveedores
 */
function cargarProveedores(){
    $.ajax({
        url: "ListarProveedoresActivosAjax.do",
       datatype: "json",
       method:"GET",
       success: function (result) {
            if(result.aaData.length > 0){
                $.each(result.aaData, function( index, modeloTabla ) {
                   var linea =  '<option value="' + modeloTabla.id +'"'+"data-tokens='" + modeloTabla.nombreCompleto + "' >"+modeloTabla.nombreCompleto+'</option>';
                   $('#proveedores').append(linea);  
                })
                $('.proveedores').selectpicker();
            }            
       },
       error: function (xhr, status) {
           console.log(xhr);
            mensajeErrorServidor(xhr, status);
       }
    })

}





__agregarArticuloInventario(e){
    detalleEscogido(e)
}

function __agregarArticulos() {
     $('#tableListarArticulos').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListarArticulos').DataTable();
		if(table.row(this).child.isShown()){

	       var data = table.row(this).data();
	    }else{
	       var data = table.row($(this).parents("tr")).data();
	     }
        if(data !=null){
            $('#codigo').val(data.codigo)
            $('#modalInventario').modal('hide')
            return
        }
    });
}


/**
* consultar producto
**/
__ConsultarProductosCod = function(e){
    if (e.keyCode != 13) {
        return;
    }
    __ListaDeArticulosPorDescripcion()
}

__ConsultarProductosDesc = function(e){
    if (e.keyCode != 13) {
        return;
    }
__ListaDeArticulosPorDescripcion()
}

function __ListaDeArticulosPorDescripcion(){
    if($('#codigoArt').val() =='' && $('#descArticulo').val() =='' ){
        return
    }
    $(".tableListarArticulos").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
    var formulario = $('#formularioParametros').serialize();
    $.ajax({
        url: 'ListarPorDescripcionCodigoArticuloAjax.do',
        datatype: "json",
        method:"GET",
        data :formulario,
        success: function (result) {
            if(result.aaData.length > 0){
                _informacionData_Articulo()
                self.articulos.data = result.aaData
                self.update()
                loadListar(".tableListarArticulos",idioma_espanol,self.informacion_tabla_articulo,self.articulos.data)
                agregarInputsCombos_Articulo()
                ActivarEventoFiltro(".tableListarArticulos")

            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
function agregarInputsCombos_Articulo(){
    $('.tableListarArticulos tfoot th').each( function (e) {
        var title = $('.tableListarArticulos thead th').eq($(this).index()).text();

        if ( $(this).index() != 0    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}

function teclamodal(e){
    if ($('#modalInventario').is(':visible')) {
        $('.precioventa').focus()
    }
    
}
function _informacionData_Articulo(){
   self.informacion_tabla_articulo = [
                                        {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
                                            "render":function(id,type, row){
                                                    return __OpcionesArticulos(id,type,row);
                                                }
                                        },
                                       {'data' : 'codigo'         ,"name":"codigo"          ,"title" : $.i18n.prop("articulo.codigo")       ,"autoWidth":false},
                                        {'data' : 'descripcion'    ,"name":"descripcion"     ,"title" : $.i18n.prop("articulo.descripcion")  ,"autoWidth":false},
                                        {'data' : 'cantidad'       ,"name":"cantidad"        ,"title" : $.i18n.prop("inventario.cantidad")   ,"autoWidth":false},
                                        {'data' : 'precioPublico'  ,"name":"precioPublico"   ,"title" : $.i18n.prop("articulo.precioPublico"),"autoWidth":false,
                                          "render":function(precioPublico,type, row){
                                              var resultado = formatoDecimales(__valorNumerico(precioPublico))
                                               return  resultado;
                                            }
                                        },
                              ];

 self.update()
}

function __OpcionesArticulos(){
  var agregar  = '<a href="#"  class="btn btnAgregar btn-success form-control" title="Seleccionar" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;

}


function ListarCodigosArticulos(){
  
    $('.descArticulo').val(null)
    $('.codigoArt').val(null)
    $(".tableListarArticulos").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
     $('#modalInventario').modal('show')
    $('#modalInventario').on('shown.bs.modal', function () {
        $('.codigoArt').select()
        $('.codigoArt').focus()

    })

 }
 


</script>
</articulo-proveedores>