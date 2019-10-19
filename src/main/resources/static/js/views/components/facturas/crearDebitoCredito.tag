<debito-credito>
  
<!--Fin Ventana de los billetes-->      
<div class="box box-solid box-primary"  show={mostrarnotacredito == true}>
        <div class="box-body" >
             
            <div  class="contenedor-factura " >
                    <div class="cabecera-izquierda">
                        <div class="item-1">
                            <label>Ingrese el Consecutivo</label>
                            <input onkeypress={__agregarArticulosFactura}  id="consecutivoFactura" class="form-control consecutivoFactura" type="text" value="{parametro.consecutivo}" readonly/>
                        </div>
                        <div class="item-1">
                            <label>Cliente</label>
                            <input  class="form-control " type="text" value="{factura.cliente.nombreCompleto}" readonly/>
                        </div>
                        <div class="item-1">
                            <label>Cedula</label>
                            <input  class="form-control " type="text" value="{factura.cliente.cedula}" readonly/>
                        </div>
                    </div>
                    <div class="motivoContainer">
                        <div class="item-motivo">
                            <div class="form-group ">
                                <label>Digite el motivo de la nota de credito</label> 
                                <input type="text" class="form-control referenciaRazon" id"referenciaRazon" name= "referenciaRazon"   >
                            </div>
                        </div>
                        <div class="item-motivo-booton">
                            <button onclick={__AplicarYcrearFactura}  class="btn-green-aplicar btn-add pull-right"> </i> {$.i18n.prop("btn.aplicar")}</button>
                        </div>
                    
                    </div>
                    <div class="listadNotaCredito"> 
                    <br>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th style="width:5%;">                                                   </th>
                            <th><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.linea")}</div></th>
                            <th><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.codigo")}</label></th>
                            <th style="width:20%;"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.descripcion")}</label> </th>
                            <th ><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.cantidad")}</label></th>
                            <th ><div class="tituloDetalle">Cant. Rebajar</label></th>
                            <th ><div class="tituloDetalle">Monto Linea </label></th>
                            
                            
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td>
                                <button  onclick={__removeProductFromDetail} class="btn btn-danger btn-xs btn-block">X</button>
                            </td>
                            <td class="list-item"><label class="text-listado">{numeroLinea}</label></td>
                            <td class="list-item"><label class="text-listado">{codigo}</label></td>
                            <td class="list-item"><label class="text-listado">{descripcion}</label></td>
                            <td class="list-item">
                                <label class="text-listado"> {cantidad.toFixed(3)} </label>
                            </td>

                            <td class="list-item">
                                <input onkeyup={__RebajarCantidad} onBlur={__RebajarCantidad} id= "cantidadAplicadaNotaCredito" class="form-control cantidadDetalle cantidadAplicadaNotaCredito" type="number"  step="any" min="0" pattern="^[0-9]+" placeholder="Cantidad a rebajar" value = {cantidadAplicadaNotaCredito.toFixed(3)}  />
                            </td>
                            <td class="list-item">
                                <label class="text-listado"> {cantidad.toFixed(3)} </label>
                            </td>

                        </tr>
                        </tbody>
                    </table>   
                    <div class="boton">
                        <button onclick={_AtrasFacturaFinal} class="btn-dark-gray btn-back pull-left">  {$.i18n.prop("btn.volver")}</button>
                    </div>        
                </div>
            </div><!-- fin contenedor-factura-->
        </div><!-- fin box-body-->
	</div><!-- fin box -->

<!--Fin Cambiar Cantidad-->
<style type="text/css"  >
    .btn-dark-gray {
        background-color: #3D3E42;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-left: 20px;
        padding-right: 20px;
        font-size: 30px!important;
        font-weight: bold;
        margin-right: 15px;
        border: none;
        float: right;
        cursor: pointer;
    }
    .motivoContainer{
        display:flex;
    }
    .item-motivo{
       flex: 1;
       margin-right: 1%
    }
    .item-motivo-booton{

    }
    .listadNotaCredito{

    } 
    .item-1{
        margin: 2%;

    }
    .item-2{

    }
    .containerCliente{
        display:flex;
    }
    .clienteItem{
        margin: 5%;
        flex: 1;
    }
    .cantidadDetalle{
        font-size: 16px;
        font-weight: 900;
        color: #ff0080;
    }
    .text-listado{
    font-size: 16px;
    font-weight: 900;
    }
    .list-item{
    text-align: center;
    }
    .btn-green-aplicar {
       background-color: #4cae4c;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 10px;
        padding-top: 5px;
        padding-left: 10px;
        padding-right: 13px;
        font-size: 30px!important;
        font-weight: bold;
        margin-right: 15px;
        margin-top: 9%;
        border: none;
        float: right;
        cursor: pointer;
    }
    #pagarTitulo {
        font-weight: 600 !important;
        font-size: 20px !important;
        font-family: Roboto,sans-serif !important;
        color: #ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        line-height: 30px;
    }

    .contenedor-factura {
        display:flex;
        flex-direction: column;
    }
    .campoDetalle {
        display: block;
        width: 100%;
        height: 34px;
        padding: 8px 18px;
        font-size: 10px;
        line-height: 1.42857143;
        color: #555;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        border-radius: 2px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        background-color: #fcfcfc;
        border: 1px solid #ccc;
        font: 20px verdana, arial, helvetica, sans-serif;
        /* margin: 2px 0; */
        padding: 1px 2px;
        overflow: visible;
    }
    .campoLabel{
        font-size: 18px;
        font-weight: 600;
        text-align: center;
    }
    .teclashift {
        font-weight: 700;
        font-size: 27px !important;
        text-align: center;
        color: red;

    }

    .ssCambioCentral{
        color: #0c3f65 !important;
        display: block;
        text-align: center;
            font-weight: 900;

    }
    .ssCambio{
        color: #0c3f65 !important;
        display: block;
        text-align: left;
    }
    @media (min-width: 992px){
    .modal-lg {
        width: 1024px !important;
    }
    }
    .facturaDiaContainer{
    display:flex;
    }
    .tamanoClienteNuevo{
        font-size: 30px;
        font-weight: 600;
        color: black;
        height: 10%;

    }
    .tituloClienteNuevo{
        display: inline-block;
        max-width: 100%;
        margin-bottom: 5px;
        font-weight: 600;
        font-size: 30px;
        font-weight: 600;
        color: black;
    }
  .btn-dark-gray {
    background-color: #3D3E42;
    color: #FFF;
    border-radius: 5px;
    padding-bottom: 10px;
    padding-top: 10px;
    padding-left: 20px;
    padding-right: 20px;
    font-size: 30px!important;
    font-weight: bold;
    margin-right: 15px;
    border: none;
    float: right;
    cursor: pointer;
   }
   .campoLabel{
    font-size: 18px;
    font-weight: 600;
    text-align: center;
    }
   .btn-green {
        background-color: #4cae4c;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 5px;
        padding-top: 5px;
        padding-left: 20px;
        padding-right: 20px;
        font-size: 22px !important;
        font-weight: bold;
        margin-right: 15px;
        border: none;
        float: right;
        margin-top: 26px;
        cursor: pointer;
    }
    .botonesContainer{
       display:flex;
    }
    .boton{
       flex: 1;
    }
    .imagenesBilletes{
      height: 90px;
      width: 170px;
    }
   .pantallaBilletes{
       display:flex;

   }
   .billeteContainer{
       display:flex;
       flex-flow: wrap;
       flex-direction: row;
   }
   .billete{
    cursor: pointer;
    margin-right: 4%;
    margin-top: 2%;

   }
    .input-group {
        position: relative;
        display: flex;
        border-collapse: separate;
    }
    .input-group-addon.btnClientes {
       color: #66b12f;
        cursor: pointer;
    }
    .elementoTotalesChino{
        font-weight: 600 !important;
        font-size: 32px !important;
        font-family: Roboto,sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        margin-left: 4%;
        margin-bottom: 2%;
        margin-top: 2%;
        margin-right: 2%;
    }
    .elementoTotales{
        font-weight: 600 !important;
        font-size: 20px !important;
        font-family: Roboto,sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        align-items: center;
        text-align: left;
        margin-left: 2%;
    }
    .TotalesContainer{
        display:flex;
        flex-direction: column;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
    }
    .contenedorExoneracion{

    }
    .label-totalesComprobanteChino {
        display: flex;
        flex: 1;
        font-weight: 600 !important;
        font-size: 37px !important;
        font-family: Roboto,sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        border-collapse: separate;
        cursor: pointer;
        margin: 2%!important;
        text-align: center !important;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 25px !important;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
    }
    .titleListaPrecio{
        color:#0c3f65;
       
    }
    .tituloDetalle{
        text-align: center;
        text-decoration: none;
        font-style: italic;
        color: black;
        font-weight: 600;
        font-size: 20px;
    }
    .btn-block {
        display: block;
        width: 100%;
        margin-top: 30%;
        }    

    .fondoVentaEspera{
        background: black;
        text-align: center;
        text-decoration: none;
        text-shadow: rgb(255, 255, 255) 0px 0px 1px;
        font-style: italic;
        color: #e2f312 !important;
        font-weight: 600;
        font-size: 14px;
        
    }
    .ventaEsperaSeleccionada{
        display: flex;
        padding-bottom: 0.2%;
    }  
    .ventaEsperaSeleccionada .tituloVentaEspera{
        color: yellow;
        background: black;
        font-weight: 700;
    }                           
    .contenedorFactura{
        display: flex;
        flex: 1;
        border: 1px solid #3c8dbc;
        background: #ffffff;
    

    }

    .precioTotalFacturaContainer{
        display:flex;
        flex:1;
    }

    .codigoBarraPrecioContainer{
        display:flex;
        flex:1;
    }
    .codigoBarraPrecioContainer .inputCodigoPrecio{
        flex:1;
        padding-left: 2%;
        padding-right: 2%;
        padding-bottom: 1%;
    }

    .labelBotones {
        font-weight: 600 !important;
        font-size: 19px !important;
        font-family: Roboto,sans-serif !important;
        color: #ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        line-height: 30px;
        border-collapse: separate;
        text-align: center;
        cursor: pointer;
        text-align: center !important;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
    }
    @media screen and (max-width: 1024px) {
    .labelBotones {
        font-size: 14px !important;
    }
    .label-totalesComprobante{
        font-size: 18px !important;
    }
    .cantidadArticulosTitulo{
        font-size: 10px !important;
    }
    .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_1{
        font-size: 14px !important;
    }
    .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_2{
        font-size: 14px !important;
    }
    }

    .botonesFuncionalContainer{
        display:flex;
        flex:1;

    }
    .cabecera-izquierda{
      display:flex;
    }
   

    .contenedorFactura .cabecera-izquierda .botonesFuncionalContainer .botonesFuncional{
    flex:1;
    padding-right: 1%;
    padding-bottom: 2%;
    }
    .gananciaContainer{
        display:flex;
        flex:1;
    }
    .gananciaContainer .formatoTituloGanancia{
        flex:1;
        color: black;
        font-size: 15px;
        font-weight: bolder;
    }
    .tituloCantidadArticulos{
        display:flex;
    }
    .contenedorFactura .cabecera-derecha .tituloCantidadArticulos .cantidadArticulosTitulo{
    font-weight: 600 !important;
        font-size: 30px !important;
        font-family: Roboto,sans-serif !important;
        color: #edea17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        line-height: 30px;
        border-collapse: separate;
        text-align: center;
        cursor: pointer;
        text-align: center !important;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;

    }
    .tituloProductoIngresadoContainer{
        display:flex;
        flex:1;
    }
    .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado{
        flex: 1;
        display: flex;
        padding-left: 2%;
        padding-right: 2%;
    
    }
    .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_1{
        flex: 0.15;
        font-weight: 600 !important;
        font-size: 36px !important;
        font-family: Roboto,sans-serif !important;
        color: yellow !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        text-align: center;
        border: none;
        text-align: center !important;
        background-color: black !important;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
    }

    .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_2{
        flex: 1;
        font-weight: 600 !important;
        font-size: 36px !important;
        font-family: Roboto,sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        text-align: center;
        border: none;
        text-align: center !important;
        background-color: black !important;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
   }
</style>    
<script>
    var self = this;
    self.parametro   = opts.parametros;  
    self.mostrarnotacredito = false;
    // Detalle de la factura es una coleccion de articulos
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.factura                = {
        id:null,
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
	    codigoMoneda:"",
	    estado:0,
	    cliente:{
            id:null,
            nombreCompleto:""
        },
	    vendedor:{
            id:null,
            nombreCompleto:""
        }

    }                   
    self.item                  = null;
    self.detalleFactura        = {data:[]}
    self.on('mount',function(){

        verificarConsecutivo()
        _INIT()
       __Eventos()
         window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
     
    })

function _INIT(){
     self.factura                = {
        id:null,
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
	    codigoMoneda:"",
	    estado:0,
	    cliente:{
            id:null,
            nombreCompleto:""
        },
	    vendedor:{
            id:null,
            nombreCompleto:""
        }
    }      
    self.update()
    __Eventos()
     self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.factura                = {
        id:null,
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
	    codigoMoneda:"",
	    estado:0,
	    cliente:{
            id:null,
            nombreCompleto:""
        },
	    vendedor:{
            id:null,
            nombreCompleto:""
        }

    }                   
    self.item                  = null;
    self.detalleFactura        = {data:[]}
}
/**
*  Consecutivo a consultar
**/
function verificarConsecutivo(){
    self.mostrarnotacredito = false;
    var numero  = self.parametro.consecutivo
    self.detail = [];
    self.factura = null
    self.update()
    $.ajax({
        url: "ListarDetlleByConsecutivoNotaCreditoAjax.do",
        datatype: "json",
        data: {consecutivo:numero},
        method:"POST",
        success: function (data) {
             if(data.aaData.length > 0){
                 self.mostrarnotacredito = true;
                 self.update()
                cargarDetallesFacturaEnEspera(data.aaData)
            }else{
                 self.mostrarnotacredito = false;
                __MostrarListadoActualizado()
                
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}
_AtrasFacturaFinal(){
     self.mostrarnotacredito = false;
     self.update();
     __MostrarListado()
}
/**
*  Cargar detalles Factura en espera
**/
function cargarDetallesFacturaEnEspera(data){
   
     $.each(data, function( index, modeloTabla ) {
        self.factura = modeloTabla.factura
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
}
/**
*  Actimpuestor validaciones del formulario
**/
function __Eventos(){
    $("#referenciaRazon").attr("maxlength", 240);
    $('#consecutivoFactura').mask('00000000000000000000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
    });
}
/**
*Cambiar Cantidad del Articulo
**/
__RebajarCantidad(e){
   var cantidad = __valorNumerico(e.currentTarget.value);
   var index = self.detail.indexOf(e.item);
   self.item = e.item; 
   self.item.cantidadAplicadaNotaCredito = cantidad
   self.detail[index] = self.item;
   self.update()
}
/**
** Se aplica o se crea una Factura cargada en la pantalla
**/
__AplicarYcrearFactura(){
 aplicarFactura()
}
/**
* Aplicar la factura
**/
function aplicarFactura(){
    if(self.detail.length == 0 ){
        mensajeError($.i18n.prop("factura.alert.sin.detalles"))
        return
    }
    swal({
        title: '',
        text: $.i18n.prop("crear.notaCredito"),
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: '#00539B',
        cancelButtonColor: '#d33',
        confirmButtonText:$.i18n.prop("confirmacion.si"),
        cancelButtonText: $.i18n.prop("confirmacion.no"),
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
    }).then(function (isConfirm) {
        if(isConfirm){
            crearFactura()  
        }
    });
  
}
/**
* Limpiar Pantalla
**/
__Limpiar(){
    _INIT()
}
/**
*  Crear Factura nueva
**/
function crearFactura(){
    self.detalleFactura.data =self.detail
    self.update() 
    var JSONDetalles = JSON.stringify( self.detalleFactura );
    self.factura.detalleFactura =JSONDetalles
    self.update();
    var  parametros = {
        consecutivo:$('.consecutivoFactura').val(),
        detalleFactura:self.factura.detalleFactura,
        referenciaRazon:$('.referenciaRazon').val(),
        completa:2
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
               	serverMessageJsonClase(data);
                self.mostrarnotacredito = false;
                self.update()
               __MostrarListadoActualizado()
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
* Consultar el consecutivo de una factura para agregar los articulos relacionados
**/
__agregarArticulosFactura(e){
     if (e.keyCode != 13) {
        return;
    } 
    verificarConsecutivo()
}

/**
* eliminar un detalle factura
**/
__removeProductFromDetail(e) {
    var item = e.item;
    index = this.detail.indexOf(item);
    this.detail.splice(index, 1);
    var cont = 0 ;
    self.detail.forEach(function(elemen){
        elemen.numeroLinea = cont + 1
        cont = elemen.numeroLinea
    })
    self.update()
 }
</script>
</debito-credito>