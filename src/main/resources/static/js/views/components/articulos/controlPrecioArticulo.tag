<control-precio>

			

				





    <div class="box" >
	      <div class = "box-body">
           <div class="container-tabla" style="overflow: scroll;height: 500px;">
                <span id="tituloCompra">Productos con cambio de precio</span>
                <div class= "elemento-tabla" >
				<table class="table table-striped" style="width:100%">
                        <thead>
                        <tr>
                            <th style="width:70%;"><div class="tituloFormat">Fecha ingreso </div></th>
                            <th style="width:6%;"><div class="tituloFormat">Responsable </div></th>
							<th style="width:3%;"><div class="tituloFormat">Codigo </div></th>
                            <th style="width:22%;"><div class="tituloFormat">Descripcion </div></th>
                            <th style="width:6%;"><div class="tituloFormat">Costo </div></th>
                             <th style="width:6%;"><div class="tituloFormat">Ganancia </div></th>
                            <th style="width:7%;"><div class="tituloFormat">Precio Anterior</div></th>
                            <th style="width:7%;"><div class="tituloFormat">Precio Nuevo</div></th>
                            <th style="width:10%;"><div class="tituloFormat">Observa</div></th>
                            <th style="width:10%;"><div class="tituloFormat">Cons.Compra </div></th>
                            <th style="width:10%;"><div class="tituloFormat"> </div></th>
                            
						  <th style="width:10%;"><div class="tituloFormat">  </div></th>
                           
                             <th style="width:10%;"><div class="tituloFormat">  </div></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td  style="width:70%;">
                                <span>{created_atSTR}</span>

                                
                            </td>

                            <td  style="width:6%;">
                                <span>{responsableCambioPrecio.nombreUsuario}</span>
                            </td>
                            <td  style="width:6%;">
                                <span>{articulo.codigo}</span>
                            </td>
                            <td style="width:30%;">
                                <span>{descripcion}</span>
                            </td>
                            <td style="width:6%;">
                                <input  onkeyup={__actualizarCostoInventario}  class="campodetalle" type="number" step="any"  value = "{precioPublicoActual}" min="0" pattern="^[0-9]+"/>
                            </td>
                             <td  style="width:7%;">
                                
                                <input   onkeyup={__actualizarGananciaKeyPress}  class="campodetalle" type="number" step="any"  value = "{ganancia}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td  style="width:7%;">
                                <span>{precioPublicoActual}</span>
                            </td>
                            <td  style="width:7%;">
                                
                                <input  onkeyup={__actualizarPrecioKeyPress}  class="campodetalle" type="number" step="any"  value = "{precioPublicoActual}" min="0" pattern="^[0-9]+"/>
                            </td>
                            <td  style="width:10%;">
                                <button id="{id}" name="{id}"  onclick={__IngresarAlInventario} class="botonObservaciones">?</button>
                            </td>
                            <td  style="width:10%;">
                                <span>{consecutivo}</span>
                            </td>
                            <td  style="width:10%;">
                                <span>{consecutivo}</span>
                            </td>
                            <td>
                                <button id="{id}" name="{id}"  onclick={__IngresarAlInventario} class="botonAplicarInventario">Aceptar</button>
                            <td>
                            <td>
                                <button id="{id}" name="{id}"  onclick={__IngresarAlInventario} class="btn-rechazo">Reversar</button>
                            <td>
                    
                            
                        </tr>
                        </tbody>
                    </table>
                    
		  </div>
          </div>
          </div>
	</div>

<style type="text/css"  >
tr > span{
    font-size: .875rem!important;
}
 .botonAplicarInventario {
    background-color: #6dca42 !important;
    color: white !important;
    font-size: 12px !important;
    font-weight: 600 !important;
    border-radius: 14px !important;
    display: block;
    border-right-color: white;
    border-color: white;
    border-right-color: white;
    box-shadow: none!important;
    border: 1px solid transparent !important;
}
.btn-rechazo {
    background-color: #dd4b39;
    border-color: #d73925;
    border-radius: 14px;
    font-size: 12px;
    font-weight: 600;
    color:white;
     box-shadow: none!important;
    border: 1px solid transparent !important;
}
.botonObservaciones {
    box-shadow: none!important;
    border: 1px solid transparent !important;


   background-color: #338edd !important;
    color: white !important;
    font-size: 12px !important;
    font-weight: 600 !important;
    border-radius: 10px !important;
    display: block;
   
    border-right-color: white;
    border-color: white;
    border-right-color: white;
}

input, select {
    border-radius: 8px !important;
    border: 1px solid var(--lightGrey3) !important;
    color: var(--Darkgrey) !important;
    font-weight: 400 !important;
    height: 31px;
    font-size: 28px;
    line-height: 38px !important;
    -webkit-box-sizing: border-box !important;
    box-sizing: border-box !important;
    padding: 0 0 0 20px !important;
    border-radius: 3px;
    -webkit-box-shadow: none;
    box-shadow: none;
}
.botoneras{
    display:flex;

}
.container-tabla{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
}
.elemen-tabla{
    display: flex;
}
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
.botonAplicarInventario{
    background-color: #6dca42 !important;
    color: white !important;
    font-size: 14px !important;
    font-weight: 600 !important;
    border-radius: 14px !important;
}
 .tituloFormat{
     text-align: center;
     font-weight: 600;
 }
 .campodetalle{
    font-size: 14px;
    width: 125px!important;
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
#tituloCompra{
	font-size: 18px;
    font-weight: 600;

}
</style>
	<script>
		var self = this;
	 	self.empresaActividadComercial= {}
		self.compras = {aaData:[]}
        self.detail = []
        self.articulos = {data:[]}
        self.detalleCompra  = null
        self.consecutivo = null
        self.tituloCompra = "Factura Electronica"
        self.item = null;
        self.articulo = null;
        self.mostrarDetalles = false;

		//Se cargan al montar el tag
		self.on('mount',function(){
			listadoControlPrecios();
           
         

		});
var itemDetalle = null;
__IngresarAlInventario(e){
    self.detalleCompra = e.item;
    self.update()
     swal({
           title: '',
           text: "Desea ingresarlo al inventario?",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
        }).then(function (isConfirm) {
            //Ajax__inicializarTabla();
            if(isConfirm){
                if(validar()){

                   actualizarDetalleAlInventario()
                }
            }
        })    
}

function validar(){
    if(self.detalleCompra.cod_invet == null ){
        mensajeAlertErrorOConfirmacion('error',"Error: Ingresar el codigo del articulo. 輸入商品編號")
        return false
    }
    if(self.detalleCompra.cod_invet.length == 0 ){
        mensajeAlertErrorOConfirmacion('error',"Error: Ingresar el codigo del articulo 輸入商品編號")
        
        return false
    }
    return true
}
/**
 @param idCompra
 * @param idDetalleCompra
 * @param codigoInventario
 * @param gananciaPrecioPublico
 * @param precioPublico
 * @param codigoProveedor
**/
function actualizarDetalleAlInventario(){
   var parametros = {
        idCompra:self.detalleCompra.idCompra,
        idDetalleCompra:self.detalleCompra.id,
        codigoInventario:self.detalleCompra.cod_invet,
        gananciaPrecioPublico: self.detalleCompra.ganancia,
        precioPublico:self.detalleCompra.precioPublicoNuevo,
        costo:self.detalleCompra.costo,
      
        codigoProveedor:self.detalleCompra.cod_proveedor,
   }
    $.ajax({
        url: 'actualizarDetalleCompraPorAutomatica.do',
        datatype: "json",
        method:"GET",
        data :parametros,
        success: function (result) {
            if (result.status != 200) {
                if (result.message != null && result.message.length > 0) {
                     mensajeAlertErrorOConfirmacion('error',result.message)
                }
            } else {
                __DeleteArticuloIngresadoInventario()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });

}

_AnularAlInventario(e){
    self.detalleCompra = e.item;
    self.update()
     swal({
           title: '',
           text: "Desea Anular el ingresarlo al inventario?",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
        }).then(function (isConfirm) {
            //Ajax__inicializarTabla();
            if(isConfirm){

                   anularDetalle(function(resultado){
                       console.log(resultado)
                       
                   })
            }
        })    
}


function anularDetalle(callback){
    
   var parametros = {
        idDetalleCompra:self.detalleCompra.id,
        idCompra:self.detalleCompra.idCompra,
   }
    $.ajax({
        url: 'anularDetalleCompra.do',
        datatype: "json",
        method:"GET",
        data :parametros,
        success: function (result) {
            if (result.status != 200) {
                if (result.message != null && result.message.length > 0) {
                     mensajeAlertErrorOConfirmacion('error',result.message)
                }
            } else {
                __DeleteArticuloIngresadoInventario()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
   callback("Resultado exitoso")

}

function __DeleteArticuloIngresadoInventario(){
     var index = self.controlPrecios.aaData .indexOf(self.detalleCompra);
     self.controlPrecios.aaData .splice(index,1);
     self.detail.splice(index,1);
     self.update()
     if(self.detail.length == 0){
         self.update()
         listadoDetallesCompras(self.detalleCompra.idCompra,function(resultado){
             
             console.log(resultado)
         })
         
     }
}


/**
*   Actualizar el costo del codigo y recalcular la compra
**/
__actualizarGananciaKeyPress(e){
    if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    aplicarGananciaCompra(e)
}

__actualizarCostoInventario(e){
    if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    var costo = __valorNumerico(e.currentTarget.value);
    self.item = e.item; 
    var index = self.controlPrecios.aaData .indexOf(self.item);
    var impuesto  =  __valorNumerico(self.item.articulo.impuesto)
    var precioPublico    =  __valorNumerico(self.item.precioPublicoNuevo)
    self.item.costo = costo
    self.item.ganancia = __CalcularGanancia(impuesto,costo,precioPublico);
    self.item.ganancia = __valorNumerico(redondeoDecimales(self.item.articulo.ganancia,aplicarRedondeo()))
    if(self.item.precioPublico == 0){
        self.item.precioPublicoNuevo =_ObtenerPrecio(self.item.articulo.costo,self.item.articulo.impuesto * 100,0,self.item.ganancia)
        self.item.precioPublicoNuevo =  __valorNumerico(redondeoDecimales(self.item.precioPublicoNuevo,aplicarRedondeo()))
    }
    
    self.detail[index] = self.item
    self.update()
}



function aplicarGananciaCompra(e){
    var ganancia = e.currentTarget.value;
    self.item = e.item; 
    var index = self.controlPrecios.aaData .indexOf(self.item);
    var impuesto  =  __valorNumerico(self.item.imp_art)
    var costo     =  __valorNumerico(self.item.articulo.costo)
    var precioPublico    =  __valorNumerico(self.item.precioPublicoNuevo)
    self.item.costo = costo
    self.item.ganancia = __valorNumerico(ganancia);
    self.item.precioPublicoNuevo =_ObtenerPrecio(self.item.costo,self.item.imp_art ,0,self.item.ganancia)
    self.item.ganancia = __valorNumerico(redondeoDecimales(self.item.ganancia,aplicarRedondeo()))
    self.item.precioPublicoNuevo =  __valorNumerico(redondeoDecimales(self.item.precioPublicoNuevo,aplicarRedondeo()))
    self.detail[index] = self.item
    self.update()

}
/**
*   Actualizar el costo del codigo y recalcular la compra
**/
__actualizarPrecioKeyPress(e){
    
    __ActualizarPrecioDetalle(e)
}



function __ActualizarPrecioDetalle(e){
    var precio = e.currentTarget.value;
    self.item = e.item; 
    var index = self.detail.indexOf(self.item);
    var impuesto = __valorNumerico(self.item.impuesto)
    var costo = __valorNumerico(self.item.costo)
    var precioPublico =  __valorNumerico(precio)
    self.item.ganancia = __CalcularGanancia(impuesto,costo,precioPublico);
    self.item.ganancia = __valorNumerico(redondeoDecimales(self.item.ganancia,aplicarRedondeo()))
    self.item.precioPublicoNuevo = precio
    self.detail[index] = self.item;
    self.update()
}



 
/**
Listado de recepcion de compras
**/		
function listadoControlPrecios() {
    self.controlPrecios = {aaData:[]}
    self.detail = []
    self.update()
	obtenerControlPrecio()
    .then(res => {
        self.controlPrecios.aaData = res
        $.each(self.controlPrecios.aaData , function( index, modeloTabla ) {
            self.detail.push(modeloTabla);
            self.mostrarDetalles = true;
        })
        
        self.update()

        unBlockUIStop();
      
     })
     .catch(err=>{
         unBlockUIStop();
         console.error(err)
     })
	



    
}






   


__MostrarPDF(e) {
     var data = e.item;
      	var parametros = {
            direccion: "bajarArchivo.do?filename=" + data.factura_pdf,
            stylemodal: "modal-xl"
        }
        riot.mount('view-pdf', { datos: parametros });
}


</script>
</control-precio>