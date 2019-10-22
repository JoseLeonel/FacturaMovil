<credito-completa>
<!-- Modal correo alternativo-->
<div class="modal fade" id="ModalAnularDocumento" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="box-title"><i class=" btn-anular"></i>&nbsp {$.i18n.prop("referencia.anula")} {parametro.consecutivo}     </h2>
      </div>
      <div class="modal-body">
           <form id = "formularioFactura" name ="formularioFactura "   class="advanced-search-form">
                <input type="hidden" id='tipoDoc' name='tipoDoc'  value="03" >
                <input type="hidden" id='referenciaCodigo' name='referenciaCodigo'  value="01" >
                <input type="hidden" id='referenciaNumero' name='referenciaNumero'  value="{parametro.consecutivo}" >
                <input type="hidden" id='estado'                  name='estado'                  value="2" >
                <input type="hidden" id='subTotal'                name='subTotal'                value="{factura.subTotal}" >
                <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
                <input type="hidden" id='totalComprobante'        name='totalComprobante'        value="{factura.totalComprobante}" >
                <input type="hidden" id='totalMercanciasGravadas' name='totalMercanciasGravadas' value="{factura.totalMercanciasGravadas}" >
                <input type="hidden" id='totalMercanciasExentas'  name='totalMercanciasExentas'  value="{factura.totalMercanciasExentas}" >
                <input type="hidden" id='totalServGravados'       name='totalServGravados'       value="{factura.totalServGravados}" >
                <input type="hidden" id='totalServExentos'        name='totalServExentos'        value="{factura.totalServExentos}" >
                <input type="hidden" id='totalGravado'            name='totalGravado'            value="{factura.totalGravado}" >
                <input type="hidden" id='totalExento'             name='totalExento'             value="{factura.totalExento}" >
                <input type="hidden" id='totalVenta'              name='totalVenta'              value="{factura.totalVenta}" >
                <input type="hidden" id='totalDescuentos'         name='totalDescuentos'         value="{factura.totalDescuentos}" >
                <input type="hidden" id='totalVentaNeta'          name='totalVentaNeta'          value="{factura.totalVentaNeta}" >
                <input type="hidden" id='totalImpuesto'           name='totalImpuesto'           value="{factura.totalImpuesto}" >
                <input type="hidden" id='totalCambioPagar'        name='totalCambioPagar'        value="{factura.totalCambioPagar}" >
                <input type="hidden" id='totalEfectivo'           name='totalEfectivo'        value="{factura.totalEfectivo}" >
                <input type="hidden" id='totalTarjeta'            name='totalTarjeta'        value="{factura.totalTarjeta}" >
                <input type="hidden" id='totalBanco'              name='totalBanco'        value="{factura.totalBanco}" >
                <input type="hidden" id='detalleFactura'          name='detalleFactura'          value="{factura.detalleFactura}" >

            <div class="row">   
                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                    <label class="knob-label" >Motivo</label>
                    <input type="text" class="form-control referenciaRazon" id="referenciaRazon" name= "referenciaRazon" >
                </div>
            </div>
            <div class="row">   
                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                <label for="pago_tipoVentaL">Aplica Inventario </label> 
                <select class="form-control rebajaInventario" id="rebajaInventario" name="rebajaInventario"   >
                    <option each={comboRebajaInventario} value="{estado}"  >{descripcion}</option>
                </select>
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
    $("#formularioFactura").validate(reglasDeValidacionAnular());
    __Init()
    __FacturaEnEspera()
    __ComboRebajoInventario()
})

function __ComboRebajoInventario(){
    self.comboRebajaInventario = []
    self.update()
    self.comboRebajaInventario.push({
        estado:0,
        descripcion:"Seleccionar"
    })
        self.comboRebajaInventario.push({
            estado:3,
            descripcion:"Sumar al Inventario"
        })
        self.comboRebajaInventario.push({
            estado:4,
            descripcion:"No sumar al Inventario"
        })
   self.update()
}
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
    if($(".rebajaInventario").val() == 0){
        mensajeError("Seleccione Si actualiza el inventario")
        return
   }
     if ($("#formularioFactura").valid()) {
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
     self.factura.totalEfectivo =__valorNumerico($('#totalEfectivo').val())
    self.factura.totalTarjeta = __valorNumerico($('#totalTarjeta').val()) 
    self.factura.totalBanco = __valorNumerico($('#totalBanco').val())
    self.factura.detalleFactura =JSONDetalles
    self.factura.estado = 2
    self.factura.tipoDoc = "03"
    self.update();
    var formulario = $("#formularioFactura").serialize();
    
   $.ajax({
        type : "POST",
        dataType : "json",
        data : formulario,
        url : "CrearNotaCreditoAjax.do",
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                    mensajeError(data.message)
                }
            } else {
               
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
        });
       $('#ModalAnularDocumento').modal('hide')

          swal({
  	        title: "",
   	        text: "Cons:" + self.facturaImprimir.numeroConsecutivo,
   	        type: 'success',
   	        showCancelButton: false,
   	        confirmButtonText: 'Aceptar',
        })
          
        var parametros = {
            factura:self.facturaReimprimir,
            facturaDia:1
        }
   
        riot.mount('ptv-imprimir',{parametros:parametros}); 
        $('.imprimirModal').modal('show');   
            __MostrarListado()
        
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


function __FacturaEnEspera(){
    var numero  = self.parametro.consecutivo
    self.detail = [];
    self.factura = null
    self.update()
     __Init()
    $.ajax({
        url: "ListarDetlleByFacturaConsecutivoAjax.do",
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
    $('.referenciaRazon').val(null);
    self.detail = [];
    self.mostrarModal   = false
    self.update()
    $.each(data, function( index, modeloTabla ) {
        self.factura = modeloTabla.factura
        $('.referenciaRazon').val(null);
        self.cliente  = modeloTabla.factura.cliente
        self.vendedor = modeloTabla.factura.vendedor
        self.update()
        self.descripcionArticulo = modeloTabla.descripcion
        self.detail.push({
            numeroLinea     : modeloTabla.numeroLinea,
            pesoPrioridad   :modeloTabla.numeroLinea,
            codigo          : modeloTabla.codigo,
            tipoImpuesto    : modeloTabla.tipoImpuesto,
            tipoImpuesto1   : modeloTabla.tipoImpuesto1,
            descripcion     : modeloTabla.descripcion,
            cantidad        : parseFloat(modeloTabla.cantidad),
            precioUnitario  : parseFloat(modeloTabla.precioUnitario),
            impuesto        : parseFloat(modeloTabla.impuesto),
            impuesto1       : parseFloat(modeloTabla.impuesto1),
            montoImpuesto   : parseFloat(modeloTabla.montoImpuesto),
            montoImpuesto1  : parseFloat(modeloTabla.montoImpuesto1),
            montoDescuento  : parseFloat(modeloTabla.montoDescuento),
            porcentajeDesc  : parseFloat(modeloTabla.porcentajeDesc),
            subTotal        : parseFloat(modeloTabla.subTotal),
            montoTotalLinea : parseFloat(modeloTabla.montoTotalLinea),
            montoTotal      : parseFloat(modeloTabla.montoTotal),
            costo           : parseFloat(modeloTabla.costo),
            porcentajeGanancia :parseFloat(modeloTabla.porcentajeGanancia),
            montoGanancia :parseFloat(modeloTabla.montoGanancia),
            ganancia :parseFloat(__valorNumerico(modeloTabla.ganancia)),
            pesoTransporte :  parseFloat(modeloTabla.pesoTransporte),
            pesoTransporteTotal :parseFloat(modeloTabla.pesoTransporteTotal),
            montoExoneracion:parseFloat(modeloTabla.montoExoneracion),
            montoExoneracion1:parseFloat(modeloTabla.montoExoneracion1),
            porcentajeExoneracion:parseFloat(modeloTabla.porcentajeExoneracion),
            fechaEmisionExoneracion:modeloTabla.fechaEmisionExoneracion,
            nombreInstitucionExoneracion:modeloTabla.nombreInstitucionExoneracion,
            numeroDocumentoExoneracion:modeloTabla.numeroDocumentoExoneracion,
            tipoDocumentoExoneracion:modeloTabla.tipoDocumentoExoneracion
        });
    
        self.update()
    })

    __aplicarExoneracionPorCliente()
    
    $('#ModalAnularDocumento').modal({
         backdrop: 'static',
         keyboard: false
    })
    $('#ModalAnularDocumento').modal('show')      

   
    
}

/**
* Aplicar la exoneracion de detalles
**/
function __aplicarExoneracionPorCliente(){
    var aplicaExo = false
    var porcentaje = self.cliente.porcentajeExoneracion / 100
    var valorTotal = 0
    for (var count = 0; count < self.detail.length; count++) {
        self.item          = self.detail[count];
        self.cliente.porcentajeExoneracion = parseFloat(self.cliente.porcentajeExoneracion)
            if(self.item.montoImpuesto > 0 || self.item.montoImpuesto1 > 0 ){
                if(self.cliente.porcentajeExoneracion > 0  ){
                    self.item.porcentajeExoneracion = parseFloat(self.cliente.porcentajeExoneracion)
                    self.item.fechaEmisionExoneracion = self.cliente.fechaEmisionExoneracion
                    self.item.nombreInstitucionExoneracion = self.cliente.nombreInstitucionExoneracion
                    self.item.numeroDocumentoExoneracion = self.cliente.numeroDocumentoExoneracion
                    self.item.tipoDocumentoExoneracion = self.cliente.tipoDocumentoExoneracion
                    valorTotal = parseFloat(self.item.montoImpuesto1) * parseFloat(porcentaje)  
                    self.item.montoExoneracion1 = valorTotal
                     valorTotal = parseFloat(self.item.montoImpuesto) * parseFloat(porcentaje)  
                    self.item.montoExoneracion = valorTotal
                    self.item.ImpuestoNeto = self.item.montoImpuesto + self.item.montoImpuesto1
                    self.item.ImpuestoNeto = self.item.ImpuestoNeto - self.item.montoExoneracion1
                    self.item.ImpuestoNeto = self.item.ImpuestoNeto - self.item.montoExoneracion  
                    self.item.montoTotalLinea = self.item.subTotal +  self.item.ImpuestoNeto
                    self.detail[count] = self.item;
                    self.update();
                    aplicaExo= true
                }else{
                    //Cliente no tiene exoneracion
                    self.item.porcentajeExoneracion = 0
                    self.item.fechaEmisionExoneracion = null
                    self.item.nombreInstitucionExoneracion = ""
                    self.item.numeroDocumentoExoneracion = ""
                    self.item.tipoDocumentoExoneracion = ""
                    self.item.montoExoneracion = 0
                    self.item.montoExoneracion1 = 0
                    self.item.ImpuestoNeto = parseFloat(self.item.montoImpuesto) + parseFloat(self.item.montoImpuesto1) 
                    self.item.montoTotalLinea = self.item.subTotal +  self.item.ImpuestoNeto
                    self.detail[count] = self.item;
                    self.factura.totalEfectivo =0
                    self.factura.totalTarjeta =0
                    self.factura.totalBanco =0
                    self.factura.totalCambioPagar = self.factura.totalComprobante
                    self.update();
                 
                    
                }
               
            }
    }
}




</script>
</credito-completa>