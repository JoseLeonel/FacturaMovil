<compra-movil>

<div id='modalCambiarCantidad' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border encabezado-pantalla" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Cambiar Cantidad</h1>
            </div>
            <div class="modal-body">
               

                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Cantidad </label>
                            <input type="text" class="form-control cambiarCantidadArticulo tamanoClienteNuevo modalInputCambioPrecio"  id="cambiarCantidadArticulo" name="cambiarCantidadArticulo"    min="0" autocomplete="off">
                        </div>
                    </div>
 
                   
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__recalcularCantidad}   class=" btn-green pull-right modalCambioPrecioBotones" > Aplicar </button>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
            <h1 style="font-size:24px;" ><i class="fa fa-calculator" ></i>&nbsp Recepcion del Proveedor  </h1>
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
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                <button onclick={__RegresarProductos}   type="button" class="btn-dark-gray btn-back  pull-left modalCambioPrecioBotones"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                    {$.i18n.prop("btn.volver")}
                </button>
            </div>
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                   <button  onclick={__SiguienteRegistroProveedor}   class=" btn-green modalCambioPrecioBotones" > Siguiente</button>                </div>
            </div>
        </div>
        
    </div>                  

    <div class="containerBotones" show="{mostrarCapturaProveedor == false}">
        <div class="boton-1">  
                <a class="pull-left" href="#"   onclick = {__Limpiar} title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f10")}</span></a>
        </div>
    
        <div class="boton-1">  
      
                <a class="pull-left " href="#"   onclick = {__AplicarYCrearCompra}       title="Siguiente"><span class="label label-limpiar ">Crear Boleta</span></a>
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
                 
                    <input autocomplete="off" class="campo codigo"   id="codigo"  type="text" placeholder="XXXXXXXXXXX" >  </input>
                </div>   
                <div class="containerBotonesSumarRestarContar">
                           <div class="BotonesSumarRestar">
                               <span onclick = {__SumarConMouse} title="Sumar +" class="fontSumarRestar input-group-addon btnsumarrestar" id="botonSumar"> 
                                   <small class="fa " style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                   <span class="fa fa-plus" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"/>
                                      Sumar
                                </span> 
                            </div>                     
                            <div class="BotonesSumarRestar">
                                <span title="Cantidad" class="fontSumarRestar input-group-addon btnsumarrestar" > 
                                    <small class="fa " style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                    <span class="fa " aria-hidden="true" style="margin-left:5px; margin-top: 3px;"/>
                                     Cant:{totalCantidadSTR}
                                </span> 
                            </div>
                            <div class="BotonesSumarRestar">
                                <span onclick = {__RestarConMouse} title="Restar -" class="fontSumarRestar input-group-addon btnsumarrestar" id="botonRestar"> 
                                    <small class="fa " style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                    <span class="fa fa-minus" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"/>
                                     Restar
                                </span> 
                            </div>       
                </div> 
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
            <div class="BotonesSumarRestar">
                <span onclick={__CambiarCantidad}  title="Ingresar Cantidad " class="fontSumarRestar input-group-addon btnsumarrestar" > 
                <small class="fa " style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                <span class="fa fa-plus" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"/>
                    Mas cantidad
                </span> 
            </div>       
       </div>
      
</div>



  




<script>
    var self = this;
    // Detalle de la factura es una coleccion de articulos
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.mostrarCapturaProveedor = false
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
    var xTriggered = 0;
        $( "#codigo" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
                lecturaCodigo($('.codigo').val())
           }
           if(event.which == 107){
            __SumarConTecla(event)
            }
             if(event.which == 109){
            __RestarConTecla(event)
            }
            if(event.which == 111){
                if(!$('#modalCambiarCantidad').is(':visible')){
                    
                    $(".codigo").val("")
                    return
                }
            }
        });

        $.fn.delayPasteKeyUp = function(fn, ms)
        {
            var timer = 0;
            $(this).on("propertychange input", function()
            {
                clearTimeout(timer);
                timer = setTimeout(fn, ms);
            });
        };
      
        
       
         window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
            
             __Teclas(evento.keyCode,evento)
           
        }, false );
        $( "#cambiarCantidadArticulo" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
               cambiarCantidadModal()
           }
        });




    })

__RegresarProductos(){
   self.mostrarCapturaProveedor = false
   self.update() 
}    
__CambiarCantidad(e){
   var cantidad = e.currentTarget.value;
   self.item = e.item;
   self.update()
        $('#modalCambiarCantidad').modal({backdrop: 'static', keyboard: false})
        $('#modalCambiarCantidad').on('shown.bs.modal', function () {
            $( "#cambiarCantidadArticulo" ).val(cantidad)
            $( "#cambiarCantidadArticulo" ).focus()
            $( "#cambiarCantidadArticulo" ).select()
        })
   
 }


this.__recalcularCantidad = function(e){
    cambiarCantidadModal()
  }.bind(this)

 function cambiarCantidadModal(){
      var cantidad = $(".cambiarCantidadArticulo").val();

    cantidad =__valorNumerico(cantidad);
    if(cantidad == 0){
       cantidad = 1;
    }
    cantidad = __valorNumerico(redondeoDecimales(cantidad,3))
    self.item = __getUltimoItemIngresado()
    self.mostrarCapturaProveedor = false
    self.update()
    __buscarcodigo(self.item.codigo,cantidad)

    //aplicarCambioLineaDetalle()
    $('#modalCambiarCantidad').modal('hide')
   // getPosicionInputCodigo()

 }




function lecturaCodigo(valor){
    if (valor == "" || valor.length == 0){
        if(self.cantidadEnterFacturar >= 1){
            self.cantidadEnterFacturar = 0
            self.update()
             __EnviarFacturar()
             return
        }else{
            self.cantidadEnterFacturar = self.cantidadEnterFacturar + 1
            self.update()
        }
    }
    var objetos = getCantidadAdnCodigo_PV();
    var codigoActual = objetos.codigo
    var cantidadAct =objetos.cantidad

    if(valor.indexOf("+") != -1){

       __sumarMasArticulo(objetos.codigo,0,cantidadAct)
       getPosicionInputCodigo()
       return
    }
    __buscarcodigo(codigoActual,__valorNumerico(cantidadAct));
}


function __Teclas(tecla,event){
    if (tecla === undefined) {
        return 
    }
    if (event === undefined) {
        return 
    }

    if(event.id == 'nota' || event.id == 'correoAlternativo' || event.id == 'nombreFactura' ||
       event.target.id == 'nota' ||  event.target.id == 'correoAlternativo' || event.target.id == 'nombreFactura'){
        return
    }
  
    
    if(tecla ==111){
       
        return
    }

  

    if(tecla ==113){
      
      getPosicionInputCodigo()
      return
    }

  
   if(tecla ==27){
      getPosicionInputCodigo()
      return
    }

}
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


  
  

__SumarConMouse(){
    aplicarSumaAlCodigo(0,1,true)
 

}
__RestarConMouse(){
    aplicarSumaAlCodigo(0,1,false)
   // getPosicionInputCodigo()
}

function __SumarConTecla(e){
    if(verificaSiSuma()){
        aplicarSumaAlCodigo(0,1,true)
        getPosicionInputCodigo()
        e.preventDefault()
         return
    }

}

function __RestarConTecla(e){
    if(verificaSiSuma()){
        aplicarSumaAlCodigo(0,1,false)
        getPosicionInputCodigo()
        e.preventDefault()
         return
    }

}

function verificaSiSuma(){
   var objetos =  getCantidadAdnCodigo_PV();
    for(i=0; i<objetos.codigo.length; i++){
        if(isNumber(objetos.codigo)){
          return false        }
    }
    return true
}
function aplicarSumaAlCodigo(valorPrecio,cantidadAct,siSuma){
    var temItem = __getUltimoItemIngresado()
    if(temItem == null){
        getPosicionInputCodigo()
        return
    }
   for (var count = 0; count < self.detail.length; count++) {
        if (self.detail[count].codigo == temItem.codigo  && temItem.numeroLinea == self.detail[count].numeroLinea){
            self.item          = self.detail[count];
            var restarValores = self.item.cantidad - __valorNumerico(cantidadAct)
            self.item.cantidad = siSuma  == true?self.item.cantidad + __valorNumerico(cantidadAct):restarValores <= 0 ? 1 : self.item.cantidad - __valorNumerico(cantidadAct)
            self.update();
            __actualizarItemArray()  
            self.detail[count] = self.item;
            self.update();
        }
    }
    __calculate();
    
}
function getCantidadAdnCodigo_PV(){
    var objeto ={
        codigo:'',
        cantidad:0
    }

    var valor = $('.codigo').val()
    if(valor == "" | valor.length == 0){
        return objeto
    }
    var existe = false
    var existeMas = false
    for(i=0; i<valor.length; i++){
        existeMas = valor.indexOf("+") !=-1?true : false
       if(existe == false && existeMas  == false ){
          existe = valor.charAt(i) == "*"?true : false
         if(valor.charAt(i) !="*" && valor.charAt(i) != "+"){
              objeto.codigo = objeto.codigo + valor.charAt(i)
          }
       }else{
           if(valor.charAt(i) != "+" && valor.charAt(i) != "*"){
              objeto.cantidad = objeto.cantidad + valor.charAt(i)
           }

       }
    }
    return objeto;

}
function getPosicionInputCodigo(){
    $('.precioPublico').val(null)
    $('.codigo').val(null)
    $('.codigo').focus()
}
function __SetUltimoArticuloIngresado(){
   localStorage.setItem('ultimoArticulo', JSON.stringify(self.articulo));
}

function __getUltimoArticuloIngresado(){
    return JSON.parse(localStorage.getItem('ultimoArticulo'));
}

function __DeleteUltimoArticuloIngresado(){
    localStorage.removeItem('ultimoArticulo');
}

function __SetUltimoItemIngresado(item){
   localStorage.setItem('ultimoItem', JSON.stringify(item));
}

function __getUltimoItemIngresado(){
    return JSON.parse(localStorage.getItem('ultimoItem'));
}

function __DeleteUltimoItemIngresado(){
    localStorage.removeItem('ultimoItem');
}
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
            self.update()

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

   var item ={
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
    } 
    self.detail.push(item); 
    self.detail.sort(function(a,b) {
    if ( a.pesoPrioridad > b.pesoPrioridad )
        return -1;
    if ( a.pesoPrioridad < b.pesoPrioridad )
        return 1;
    return 0;
    } );
    self.update()
     __SetUltimoItemIngresado(item);
}

/**
* Actualizar item en el array
**/
function __actualizarItemArray(){
    //Subtotal del Detalle
    self.item.montoTotalLinea = __valorNumerico(self.item.costo * self.item.cantidad);
    self.item.montoTotalLinea = __valorNumerico(self.item.montoTotalLinea-self.item.totalDescuento)
    self.item.montoTotalLinea = __valorNumerico(self.item.montoTotalLinea + self.item.totalImpuesto);
    self.item.costoTotal = __valorNumerico(self.item.costoTotal) * self.item.cantidad
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

                  
</script>
</compra-movil>