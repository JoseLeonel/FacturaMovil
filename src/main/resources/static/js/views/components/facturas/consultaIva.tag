<consulta-iva>
   <!-- Titulos -->
    
	<div class="principalContainer">
	   <div class="">
    </div>

	<style type="text/css"  >
       .principalContainer{
            background: red;
            display: flex;
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
