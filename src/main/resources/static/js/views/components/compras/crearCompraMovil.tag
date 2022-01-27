<compra-movil>
<!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-calculator"></i>&nbsp Ingreso de Compra  </h1>
        </div>
        <div class=" col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>

    <div  class="box " show="{mostrarCapturaProveedor == true}" >
        <div class="box-body">
            <form id="formularioEncabezadoFactura" name="formularioEncabezadoFactura">
            <div class="row">
                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                    <div class="form-group ">
                        <label>Digite proveedor</label> 
                        <select  class=" selecProveedor has-success" name="proveedor" id="proveedor" ></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                    <div class="form-group ">
                        <label>{$.i18n.prop("compra.consecutivo")}</label> 
                        <input type="text" class="form-control consecutivo campo" id="consecutivo" name="consecutivo" >
                    </div>
                </div>
                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                    <div  class="form-group">
                        <label>{$.i18n.prop("compra.fecha.compra")}</label> 
                        <div  class="form-group input-group date datepickerFechaCompra " data-provide="datepickerFechaCompra"   data-date-format="yyyy-mm-dd">
                            <input type="text" class="form-control fechaCompra campo" id="fechaCompra" name = "fechaCompra"  >
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-th"></span>
                            </div>
                        </div>
                    </div>    
                </div>
            </div>   
            </form>
        </div>
        <div class="modal-footer">
            <div  >
                <button  onclick={__SiguienteRegistroProveedor}   class=" btn-green modalCambioPrecioBotones" > Siguiente</button>
            </div>
        </div>
        
    </div>                  

    <div class="containerBotones" show="{mostrarCapturaProveedor == false}">
        <div class="boton-1">  
                <a class="pull-left" href="#"   onclick = {__Limpiar} title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f10")}</span></a>
        </div>
    
        <div class="boton-1">  
                <a class="pull-left" href="#"   onclick = {__AplicarYCrearCompra}       title="Aplicar la compra"> <span class="label label-limpiar">Crear Compra</span></a>
        </div>
    
    </div>  
    
   

<div class="containerPrincipal" show="{mostrarCapturaProveedor == false}">

       <div class= "containerHeader">
            <div class="containerHeaderInfo">
                <div> <span>{proveedor.nombreCompleto}</div> 
                <div><span class="titulo-1">{compra.consecutivo}</span><span >{compra.fechaCompra}</span></div>
            </div>    
            <div class="containerDigiteCodigo">
                <div>
                    <span>Digite el codigo</span>
                </div>
                <div>   
                 
                    <input autocomplete="off" class="campo codigo" onkeypress={__addProductToDetail}  id="codigo"  type="text" placeholder="XXXXXXXXXXX" >  </input>
                </div>   
                <div><span class="titulo-resumen">Cant:{totalCantidadSTR}</span></div> 
           </div>  
        </div>
       <div class= "containerDetalle">
            
            <div class="containerTable">
              <div class="containerItem">

              <table class="table ">
                    <thead>
                        <tr>
                            <th style="width:2%;"><div class="tituloFormat"> </div></th>
                            <th style="width:8%;"><div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.codigo")}                        </div></th>
                            <th style="width:18%;"><div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.descripcion")}</div></th>
                            <th style="width:17%;"><div class="tituloFormat">{$.i18n.prop("compra.linea.detalle.cantidad")}   </div></th>
                            
                   
                        </tr>
                    </thead>
                    <tbody>
                        <tr  class="colorDetalleTable"  each={detail}>
                            <td style="width:5%;">
                                <button  onclick={__removeProductFromDetail} class="btn btn-danger btn-xs btn-block">X</button>
                            </td>
                            <td style="width:8%;"><span class="tabla-codigo">{codigo}</span></td>
                            <td style="width:16%;"><span class="tabla-descripcion">{descripcion}</span></td>
                            <td style="width:16%;" ><span class="tabla-cantidad">{cantidad}</span></td>
                           
                        </tr>
                    </tbody>
                </table>     
                </div>
               

            </div>
       
       </div>
       <div class="containerDetalleResumenCantidades">
            
            <div><span class="titulo-resumen">Total:{totalCostoSTR}</span></div>
            
       </div>
      
</div>



  





<script>
    var self = this;
    // Detalle de la factura es una coleccion de articulos
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.mostrarCapturaProveedor = true
    self.compra                = {
        consecutivo:"",
        fechaCredito    : null,
        fechaCompra     : null,
        id : null,
        totalImpuesto: 0,
        totalCompra:0,
        estado:0,
        tipoDocumento:0,
        formaPago:0,
        totalDescuento:0,
        subTotal:0,  
        total:0,
        nota:""
    }       
    self.proveedor = {
        id:null,
        nombreCompleto:'',
    }                     
    self.item                  = null;
    self.articulo              = null;
    self.articulos             = {data:[]}
    self.proveedores           = {data:[]}
    
    self.detalleCompra         ={data:[]}
   
  
    
    self.numeroLinea =0
     self.pesoPrioridad =  0
     self.totalCantidad = 0
     self.totalCosto = 0
     self.totalCantidadSTR = 0
     self.totalCostoSTR = 0
    self.on('mount',function(){

        __Init()

        $("#formularioEncabezadoFactura").validate(reglasDeValidacionCompra());

        __Teclas()
     
        $('.datepickerFechaCompra').datepicker(
        {
            format: 'yyyy-mm-dd',
            todayHighlight:true,
        }
        
    );
   
    var retrievedObject = JSON.parse(localStorage.getItem('detallesComprasNueva'));
    if (retrievedObject != 'undefined') {
       self.detail = retrievedObject == null? self.detail = []:retrievedObject  
    }
    if (compraObject != 'undefined') {
       var compraObject = JSON.parse(localStorage.getItem('compraNueva'));
    }
    var proveedorObject = JSON.parse(localStorage.getItem('proveedor'));
    if (compraObject != 'undefined') {
       self.proveedor = proveedorObject == null? self.proveedor:proveedorObject  
    }
    self.update()
    __calculate()
    })



/**
* Camps requeridos
**/
var reglasDeValidacionCompra = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			consecutivo : {
				required : true,
                maxlength:20,
         	},                                   
			fechaCompra : {
				required : true,
             },
             proveedor:{
                 required:true,
                 maxlength:250,
             }         
		},
		ignore : []

	});
	return validationOptions;
};

__SiguienteRegistroProveedor(){
      if ($("#formularioEncabezadoFactura").valid()) {
            var idProveedor = $('.selecProveedor').val()
            self.proveedor =null
            self.compra.fechaCompra = $(".fechaCompra").val()
            self.compra.consecutivo = $(".consecutivo").val()
            self.update()
            $.each(self.proveedores , function( index, modeloTabla ) {
                if(modeloTabla.id == idProveedor){
                    self.proveedor = modeloTabla
                    self.update()

                }
            });
            self.mostrarCapturaProveedor = false

      }
}

 


/**
** Se aplica o se crea una compra cargada en la pantalla
**/
__AplicarYCrearCompra(){
   
   
        swal({
           title: '',
           text: $.i18n.prop("compra.alert.crear"),
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
               crearCompra(2)  
              
            }
        });
    
}
/**
* Limpiar Pantalla
**/
__Limpiar(){
    __Init()
}
/**
*  Inicializar las variables de trabajos
**/
function __Init(){
       self.totalCantidadSTR = 0
     self.totalCostoSTR = 0
  
       self.totalCantidad = 0
     self.totalCosto = 0
  
    self.mostrarCapturaProveedor = true
    $('.fechaCompra').val(null);
  
     $('.datepickerFechaCompra').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-90d',
              todayHighlight:true,
            }
         );
        $('.datepickerFechaCredito').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-0d',
              todayHighlight:true,
            }
         );
   
    $('.consecutivo').val(null)
    self.numeroLinea =0
    
    self.detail                = [];
     self.compra                = {
        consecutivo:"",
        fechaCredito    : null,
        fechaCompra     : null,
        id : null,
        totalImpuesto: 0,
        totalCompra:0,
        estado:0,
        tipoDocumento:0,
        formaPago:0,
        totalDescuento:0,
        subTotal:0,  
        total:0,
        nota:""
    }                          
    self.item                  = null;
    self.articulo              = null;
 
    
      __ListaDeProveedores()

    self.update();
    
    localStorage.setItem('detallesComprasNueva', JSON.stringify(self.detail));
    localStorage.setItem('compraNueva', JSON.stringify(self.compra));
    localStorage.setItem('proveedor', JSON.stringify(self.proveedor));
     
}

/** 
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('YYYY-MM-DD ');
}


/**
*  Crear Compra nueva
**/
function crearCompra(estadoCompra){
    if(self.detail.length == 0 ){
        mensajeError($.i18n.prop("compra.alert.sin.detalles"))
        return
    }
   
    self.detalleCompra.data =self.detail
    self.update()
      var JSONDetalles = JSON.stringify( self.detalleCompra );
    var informacion = {
        id:self.compra.id,
        nota:$('.nota').val(),
        subTotal:__valorNumerico(self.compra.subTotal),
        totalDescuento:__valorNumerico(self.compra.totalDescuento),
        totalImpuesto:__valorNumerico(self.compra.totalImpuesto),
        totalCompra:__valorNumerico(self.compra.totalCompra),
        formaPago:$('.formaPago').val(),
        
        proveedor:$('.proveedor').val(),
        consecutivo:$('.consecutivo').val(),
        estado:estadoCompra,
        fechaCompra:$('.fechaCompra').val() == null ? new Date():$('.fechaCompra').val(),
        detalleCompra :JSONDetalles
     }
    $.ajax({
        type : "POST",
        dataType : "json",
        data : informacion,
        url : "CrearRecibirCompraAjax.do",
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                    mensajeError(data.message)
                }
            } else {
               	serverMessageJsonClase(data);
                swal({
	                title: '',
	                text: data.message,
	                type: 'success',
	                showCancelButton: false,
	                confirmButtonText: 'Aceptar',
                })
                __Init()
                
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}




/** 
*
*Agregar codigos al detalle de la Compra
*
*/
__addProductToDetail(e){
    if (e.keyCode != 13) {
        return;
    } 
    __buscarcodigo(e.currentTarget.value,1);
}
 



/**
*  Lista de los Proveedores
**/
function __ListaDeProveedores(){
    $.ajax({
        url: 'ListarProveedoresAjax.do',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.proveedores                = result.aaData
                comboCargaProveedores(self.proveedores,null)
                _evento_refrescar_proveedores(self.proveedores)
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
/**
* Buscar el codigo del codigo  en la base de datos
**/
function __buscarcodigo(idArticulo,cantidad){

obtenerArticulo(idArticulo,self.proveedor.id).then(r =>{
       unBlockUIStop();
     self.articulo =  r;
     self.update()
     if(self.articulo !=null){
        if(self.articulo.estado  == "Inactivo"){
            mensajeAdvertencia($.i18n.prop("error.articulo.inactivo.inventario"))
            self.articulo = null
        }

        if(self.articulo !=null){
           
            self.descripcionArticulo = self.articulo.descripcion
            
            self.update()
            __agregarArticulo(cantidad)
        }
        getPosicionInputCodigo();
    }else{
        mensajeAdvertencia($.i18n.prop("error.articulo.codigo.no.existe"))
    }

    }).catch(() => {
        unBlockUIStop();
     console.log('Algo salió mal');
         
    });

   
}

function getPosicionInputCodigo(){
    $('.codigo').val(null)
    $('.codigo').focus()
}
/**
*  Agregar un articulo si existe se suma la cantidad y no existe se agrega en el detalle
**/
function __agregarArticulo(cantidad){
  
    if(self.articulo == null){
        return;
    }
    if(cantidad == null){
        cantidad = 1
    }
    if(cantidad == 0){
        cantidad = 1
    }
    var encontrado = false;
     if(self.detail[0] == null){ // first element
        __nuevoArticuloAlDetalle(cantidad);
        encontrado = true;
    }else{//Se busca el articulo si existe se incrementa la cantidad
        for (var count = 0; count < self.detail.length; count++) {
            if (self.detail[count].articulo_id == self.articulo.id ){
               self.item  = self.detail[count];
               self.item.cantidad = self.item.cantidad + parseFloat(cantidad)
               self.update();
               __actualizarItemArray();
               self.detail[count] = self.item;
               encontrado = true;
              self.update();
            }
        }
    
    }
    // si no existe se agrega como un codigo nuevo
    if(encontrado == false){ // add elemen
      __nuevoArticuloAlDetalle(cantidad);
    }
    __calculate(); 
    self.articulo = null;
    self.update()

    
}

/**
*   agregar Articulos nuevos en el detalle de la Compra
**/
function __nuevoArticuloAlDetalle(cantidad){
    if(self.articulo.descripcion == null){
        return;
    }
    if(self.articulo.descripcion == ""){
        return;
    }
    var iva =  __valorNumerico(self.articulo.impuesto/100)
    var montoImpuestoV    =__valorNumerico(self.articulo.costo * iva)
    var totalImpuesto     = __valorNumerico(montoImpuestoV) * __valorNumerico(cantidad) 
    var montoTotalLinea   = __valorNumerico(self.articulo.costo * cantidad) +  totalImpuesto
    self.descuento      = 0;
    self.pesoPrioridad =  self.pesoPrioridad + 1
    self.numeroLinea = self.numeroLinea + 1

    self.detail.push({
       numeroLinea     : self.numeroLinea,
       pesoPrioridad   :self.pesoPrioridad,  
       articulo_id     : self.articulo.id,
       codigo          : self.articulo.codigo,
       descripcion     : self.articulo.descripcion,
       cantidad        : __valorNumerico(cantidad),
       costo           : __valorNumerico(self.articulo.costo),
       ganancia          : __valorNumerico(self.articulo.gananciaPrecioPublico),
       precio          : __valorNumerico(self.articulo.precioPublico),
       totalImpuesto   : __valorNumerico(totalImpuesto),
       totalDescuento  :0,
       impuesto        : __valorNumerico(iva),
       descuento       : 0,
       montoTotalLinea : __valorNumerico(montoTotalLinea)
    }); 
    self.detail.sort(function(a,b) {
    if ( a.pesoPrioridad > b.pesoPrioridad )
        return -1;
    if ( a.pesoPrioridad < b.pesoPrioridad )
        return 1;
    return 0;
    } );
    self.update()
}

/**
* Actualizar item en el array
**/
function __actualizarItemArray(){
    //Subtotal del Detalle
    self.item.montoTotalLinea = __valorNumerico(self.item.costo * self.item.cantidad);
    self.item.montoTotalLinea = __valorNumerico(self.item.montoTotalLinea-self.item.totalDescuento)
    self.item.montoTotalLinea = __valorNumerico(self.item.montoTotalLinea + self.item.totalImpuesto);
    self.update()
}
/**
* eliminar un detalle Compra
**/
__removeProductFromDetail(e) {
    var item = e.item;
    index = this.detail.indexOf(item);
    this.detail.splice(index, 1);
    var num = 0
    for (var count = 0; count < self.detail.length; count++) {
         num = num + 1 
    }
    if(num > 0){
        var cont  = 0
       self.detail.forEach(function(elemen){
            elemen.numeroLinea = num 
            num = num > 0?num -1:1
            cont =  cont + 1
        })  
        self.numeroLinea =  cont
    }
     

    self.update()
     __calculate();
 }
   
 /**
 * Cuando se aplica un cambio de cantidad en un detalle
 * Se aplica una recalculacion de todo el detalle y Compra
 **/ 
 __recalculacionDelDetalle(e){
   // if (e.keyCode != 13) {
   //     return;
   // } 
    var cantidad = e.currentTarget.value;
    self.item = e.item; 
    self.update()
     __ActualizaCantidad(cantidad)
 }

  /**
 * Cuando se aplica un cambio de cantidad en un detalle
 * Se aplica una recalculacion de todo el detalle y Compra
 **/ 
 __recalculacionDelDetalleBlur(e){
    var cantidad = e.currentTarget.value;
    self.item = e.item; 
    self.update()
    __ActualizaCantidad(cantidad)
    
 }

 function __ActualizaCantidad(cantidad){
    var index = self.detail.indexOf(self.item);
    
    //Cantidad del detalle se verifica si es null o espacio por defecto se deja en 1
    cantidad =__valorNumerico(cantidad);
   // if(cantidad == 0){
   //    cantidad = 1;
   // }
    self.item.cantidad = parseFloat(cantidad);  
    _cambiaImpuesto()
    __actualizarItemArray();
    self.detail[index] = self.item;
    self.update();
    __calculate();
 }

/**
* calculacion de los detalle de la compra 
**/
function __calculate() {
    self.compra.totalCompra     = 0;
    self.compra.totalDescuento  = 0;
    self.compra.totalImpuesto   = 0;
    self.compra.subTotal        = 0;
    self.update()
    var totalCompra    = 0
    var montoTotalLinea= 0
    var totalDescuento = 0
    var totalImpuesto  = 0
    var subTotal = 0
    self.totalCantidad = 0
    self.totalCosto = 0
    self.detail.forEach(function(e){
        totalCompra      += e.montoTotalLinea >0?e.montoTotalLinea:0
        totalDescuento   += e.totalDescuento >0?e.totalDescuento:0
        totalImpuesto    += e.totalImpuesto >0?e.totalImpuesto:0
        self.totalCantidad = self.totalCantidad + e.cantidad
        self.totalCosto = self.totalCosto + e.costo    
    });
    self.compra.totalCompra    = totalCompra
    self.compra.totalDescuento = totalDescuento
    self.compra.totalImpuesto  = totalImpuesto
    self.compra.subTotal  = totalCompra - totalImpuesto
    self.compra.subTotal = self.compra.subTotal + totalDescuento
    self.totalGeneralSubTotal = formatoDecimales(self.compra.subTotal,2)
    self.totalGeneralDescuento = formatoDecimales(totalDescuento,2)
    self.totalGeneralImpuesto  = formatoDecimales(totalImpuesto,2)
    self.totalGeneralCompra    = formatoDecimales(totalCompra,2)
    self.totalCostoSTR    = formatoDecimales(self.totalCosto,2)
    self.totalCantidadSTR    = formatoDecimales(self.totalCantidad,2)
    self.articulo              = null;
    self.update(); 
    $( "#codigo" ).val(null);
    $('.codigo').select()
    $('.codigo').focus()
    localStorage.setItem('detallesComprasNueva', JSON.stringify(self.detail));
    localStorage.setItem('compraNueva', JSON.stringify(self.compra));
    localStorage.setItem('proveedor', JSON.stringify(self.proveedor));

}


/**
*  teclas de la pantalla
**/      
function __Teclas(){
    window.addEventListener( "keydown", function(evento){
    var tecla = evento.keyCode; 
    if(tecla ==119){
        self.mostrarFormularioPago = true
        mostrarFormaPago()     
    }   
    //Compra en espera
    if(tecla ==120){
      crearCompra(1)   
    }
    
    //Limpiar f2
    if(tecla ==113){
      __Init()
    }

  if(tecla ==27){
      $('.codigo').select()
      $('.codigo').focus()
    }
    }, false );
}                         
</script>
</compra-movil>