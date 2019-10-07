<credito-completa>
<!-- Modal correo alternativo-->
<div class="modal fade" id="ModalAnularDocumento" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="box-title"><i class=" btn-anular"></i>&nbsp {$.i18n.prop("referencia.anula")} {parametro.consecutivo}     </h2>
      </div>
      <div class="modal-body">
        <form id = "formulario" name ="formulario "   class="advanced-search-form">
            <div class="row">   
                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                    <label class="knob-label" >{$.i18n.prop("informacion.razon")}</label>
                    <input type="text" class="form-control referenciaRazon" id="referenciaRazon" name= "referenciaRazon" >
                </div>
            </div>
        </form>
      </div>
      <div class="modal-footer">
        <div class="row">
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                <button onclick ={__regresarAlListadoAnulacion}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                    {$.i18n.prop("btn.volver")}
                </button>
            </div>
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                <button  onclick={__Anular}   class=" btn-red pull-right" >  {$.i18n.prop("btn.anular")}</button>
            </div>
         </div>
      </div>
    </div>
  </div>
</div>
<style type="text/css">
.clickable {
        cursor: pointer;
    }
    .btn-success {
        color: #e7e7e7;
        background-color: #00a65a !important;
        border-color: #008d4c;
    }
    
</style>
<script>
   var self = this;
   self.parametro   = opts.parametros;  
   self.on('mount',function(){
    $("#formulario").validate(reglasDeValidacionAnular());
    __Init()
    verificarConsecutivo()
})
/**
*  Regresar al listado
**/
__regresarAlListadoAnulacion(){
    $('#ModalAnularDocumento').modal('hide')
}
/**
* Anular Documento
**/
__Anular(){
     if ($("#formulario").valid()) {
         crearFactura()
     }
}
/**
*  Crear Factura nueva
**/
function crearFactura(){
    self.detalleFactura.data =self.detail
    self.update() 
    var JSONDetalles = JSON.stringify( self.detalleFactura );
    self.update();
    var  parametros = {
      consecutivo:self.parametro.consecutivo,
      detalleFactura:JSONDetalles,
      referenciaRazon:$('.referenciaRazon').val(),
      completa:1
    }
   $.ajax({
        type : "POST",
        dataType : "json",
        data : parametros,
        url : "crearNotaCreditoEspecificaAjax.do",
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                    mensajeError(data.message)
                }
            } else {
                $('#ModalAnularDocumento').modal('hide')
               	serverMessageJsonClase(data);
                swal({
	                title: '',
	                text: "Nota Credito completa Exitosamente",
	                type: 'success',
	                showCancelButton: false,
	                confirmButtonText: 'Aceptar',
	            })   
                evaluarFactura(data)
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
/**
*Si fue facturada o tiquete
**/
function evaluarFactura(data){
   if (data.message != null && data.message.length > 0) {
        $.each(data.listaObjetos, function( index, modeloTabla ) {
            self.facturaImprimir   = modeloTabla
            self.update()
            __Init()
                //Envia a la pantalla de impresion
            self.facturaReimprimir = modeloTabla
            self.update()
            var parametros = {
                factura:modeloTabla,
                facturaDia:0
            }
            riot.mount('ptv-imprimir',{parametros:parametros}); 
            __MostrarListadoActualizados()
        });
    }
}
/**
* Camps requeridos
**/
var reglasDeValidacionAnular = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			referenciaRazon : {
				required : true,
                maxlength:180,
                minlength:1,
			}                                   
		},
		ignore : []

	});
	return validationOptions;
};
/**
*  Inicializar las variables de trabajos
**/
function __Init(){
    
    self.detail                = []
    self.facturas_espera       = {data:[]}  
    self.factura                = {
        id:null,
        estado :1,
	    fechaCredito:null,
	    fechaEmision:null,
	    condicionVenta:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalComprobante:0,
	    totalEfectivo:0,
        totalTarjeta:0,
        totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
        totalCambioPagar:0,
	    codigoMoneda:"",
	    estado:0,
	    cliente:{
            id:0,
            nombreCompleto:"",
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        }
    }                            
    self.item                  = null;
    self.articulo              = null;
    self.detalleFactura        ={data:[]}
    self.update();
 
}
/**
*  Consecutivo a consultar
**/
function verificarConsecutivo(){
    self.mostrarnotacredito = false;
    var numero  = self.parametro.consecutivo
    self.detail = [];
    self.update()
    $.ajax({
        url: "ListarDetlleByConsecutivoNotaCreditoAjax.do",
        datatype: "json",
        data: {consecutivo:numero},
        method:"POST",
        success: function (data) {
             if(data.aaData.length > 0){
                cargarDetallesFacturaEnEspera(data.aaData)
            }
        },
        error: function (xhr, status) {
        mensajeErrorServidor(xhr, status);
        }
    });
}
/**
*  Cargar detalles Factura en espera
**/
function cargarDetallesFacturaEnEspera(data){
     $.each(data, function( index, modeloTabla ) {
        self.detail.push({
            numeroLinea     : modeloTabla.numeroLinea,
            id              : modeloTabla.id,
            codigo          : modeloTabla.codigo,
            descripcion     : modeloTabla.descripcion,
            cantidad        : __valorNumerico(modeloTabla.cantidad) - __valorNumerico(modeloTabla.cantidadAplicadaNotaCredito) ,
            cantidadAplicadaNotaCredito     :  __valorNumerico(modeloTabla.cantidad) - __valorNumerico(modeloTabla.cantidadAplicadaNotaCredito) 
        });
        self.update()
    })
    self.update()
    $('#ModalAnularDocumento').modal({
         backdrop: 'static',
         keyboard: false
    })
    $('#ModalAnularDocumento').modal('show')      
}
</script>
</credito-completa>