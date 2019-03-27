<total-inventarios>
   <!-- Titulos -->
    <div class="row">
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("titulo.inventario.totales")} </h1>
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
            <div class="col-xs-12 text-right">
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
            </div>
        </div>
    </div>    
	<!-- Fin Filtros-->
    <br>
  	<!-- Detalle  -->
	<div id="formularioDetalle" class="row center"  >
    	<div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-body">
                    <form id = "formularioDetalle" name="formularioDetalle" class="advanced-search-form">
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> {$.i18n.prop("inventario.total.costo")}  </label>
                                <input type="text" readonly="readonly" class="form-control" placeHolder ="{$.i18n.prop("inventario.total.costo")}"  value="{inventario.totalCostoSTR}">
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label> {$.i18n.prop("inventario.total.venta.esperada")}  </label>
                                <input type="text" readonly="readonly" class="form-control" placeHolder ="{$.i18n.prop("inventario.total.venta.esperada")}"  value="{inventario.totalVentasSTR}">
                            </div>
                        </div>
                	</form>
                </div>
            </div>   
	        <div class="col-xs-12 col-md-12 col-lg-12 col-sm-12" show={mostrarDescarga == true}>
				<a class="fa fa-download" target="_blank" title="Descargar detalle transacciones" href="DescargarInventarioAjax.do"> Descargar</a> &nbsp       
				<a class="fa fa-download" target="_blank" title="Descargar detalle transacciones" href="DescargarInventarioExistenciasAjax.do">Existencias</a>        
	        </div>
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>

	<script>
	
		self = this;
		self.mostrarDetalle        = false
		self.inventario ={				
				totalCostoSTR:"0",
				totalVentasSTR:"0",
		}
		self.mostrarDescarga = false
		self.empresa = {
			id:0
		}
		//Se cargan al montar el tag
		self.on('mount',function(){
			_Empresa();
		})

		/**
		*  Busqueda de la informacion por rango de fechas
		**/
		__Busqueda(){
		    
		        $.ajax({
		            url: "TotalInventarioAjax.do",
		            datatype: "json",
		            method:"GET",
		            success: function (data) {
			        	self.inventario = data;
					    self.update();
			        },
			        error: function (xhr, status) {
			            console.log(xhr);
			            mensajeErrorServidor(xhr, status);
			        }
		        });
		}


/**
* Consultar la empresa
**/
function _Empresa(){
     $.ajax({
        url: "ParametrosEmpresaAjax.do",
        datatype: "json",
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.empresa =   modeloTabla
					   if (self.empresa.descargarInventario == 1) {
                          self.mostrarDescarga = true
						  
					   }
                       self.update()
                    });
                }
            }
            
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });

}
		
	</script>
</total-inventarios>
