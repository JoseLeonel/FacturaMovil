<etiqueta-imprimir>
 <!-- Titulos -->
    <div  class="row "  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp Imprimir Etiquetas de Precios </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
        </div>
    </div>
    <!-- Titulos -->
<div class="contenedorFactura">
	<div id="parametros">
        <input type="hidden" name="listaArticuloEtiquetas" id="listaArticuloEtiquetas" >
        <div>
            <span>Digite el Codigo</span>
            <input type="text" class="form-control codigo campo" id="codigo"  name= "codigo" >
        </div>
        <div>
            <span>Cantidad Etiquetas</span>
            <input onkeypress={__addDetail}  type="number" class="form-control cantidadEtiquetas campo" id="cantidadEtiquetas"  name= "cantidadEtiquetas" >
        </div>
        <div id="botones">
            <div>
                <button type="button" class="btn-boton-agregar"  onkeypress={__addDetail} >Agregar</button>
            </div>
            <div>
                <button type="button" class="btn-boton-generar"  onclick={__generarPDF}>Generar Etiquetas</button>
            </div>
        </div>
    </div>

    <div id="listaEtiquetas">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th style="width:5%;"> </th>
                    <th ><div class="tituloDetalle">Codigo</div></th>
                    <th><div class="tituloDetalle">Descripcion</div></th>
                    <th ><div class="tituloDetalle">Precio</div></th>
                    <th ><div class="tituloDetalle">Cantidad Etiquetas</div> </th>
                </tr>
            </thead>
            <tbody>
                <tr each={detail}>
                    <td>
                        <button id="{numeroLinea}" name="{numeroLinea}"  onclick={__removeProductFromDetail} class="btn btn-danger btn-xs btn-block">X</button>
                    </td>
                    <td style="width:5%;"  class="campoLabel"><span>{codigo}</span> </td>
                    <td  class="campoLabel"> <span>{descripcion}</span></td>
                    <td class="campoLabel"><span>{precio}</span></td>
                    <td class="campoLabel"><span>{cantidadEtiqueta}</span></td>
                </tr>
            </tbody>
        </table>          
    </div>
</div>    


<script>
    var self = this
    self.idiomaDataTable      = []         // idioma de la datatable nuevo
    self.formato_tabla        = []         // Formato del Listado de la Tabla 
    self.etiquetas            = {data:[]}
    self.detail                = []
    self.pesoPrioridad = 0;
    self.numeroLinea =0
    self.on('mount',function(){
         getPosicionInputCodigo()
        var xTriggered = 0;
        $( "#codigo" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
                $('.cantidadEtiquetas').val(null)
                $('.cantidadEtiquetas').focus()
                $('.cantidadEtiquetas').select()
           }
           
        });
        window.addEventListener( "keydown", function(evento){
                $(".errorServerSideJgrid").remove();
            }, false );
    })

__addDetail(e){
    if (e.keyCode != 13) {
        return;
    }
    var codigo = $('#codigo').val()
    var cantidad = e.currentTarget.value
    if(cantidad == null || cantidad == 0 ){
        return
    }
    __buscarcodigoCantidad(codigo,cantidad);
    getPosicionInputCodigo()
}
function getPosicionInputCodigo(){
    $('.cantidadEtiquetas').val(null)
    $('.codigo').val(null)
    $('.codigo').focus()
}

function __buscarcodigoCantidad(idArticulo,cantidad){
    if(idArticulo ==null){
        return
    }
    if(idArticulo.length ==0){
        return
    }
    $.ajax({
        type: 'GET',
        url: 'findArticuloByCodigojax.do',
        method:"GET",
        data:{codigoArticulo:idArticulo},
        success: function(data){
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message);
                }
            }else{
                self.articulo  = null
                self.update()
                if (data.message != null && data.message.length > 0) {
                    self.articulo =null
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        if(modeloTabla.estado  == "Inactivo"){
                            mensajeError($.i18n.prop("error.articulo.inactivo.inventario"))
                            return
                        }
                        self.articulo  = modeloTabla
                        self.update()
                        __agregarArticulo(cantidad)
                    });
                }
            }
        },
	    error : function(xhr, status) {
            console.log(xhr);
          mensajeErrorServidor(xhr, status);
        }
    });
   return
}


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
    if(self.detail[0] == null){
        __nuevoArticuloAlDetalle(cantidad);
        encontrado = true;
    }else{
        for (var count = 0; count < self.detail.length; count++) {
            if (self.detail[count].codigo == self.articulo.codigo ){
                self.item          = self.detail[count];
                self.item.cantidadEtiqueta = self.item.cantidadEtiqueta + __valorNumerico(cantidad)
                self.update();
                self.detail[count] = self.item;
                encontrado = true;
                self.update();

            }
        }
    }

    if(encontrado == false){
        __nuevoArticuloAlDetalle(cantidad);
    }

}

/**
nuevo articulo
**/
function __nuevoArticuloAlDetalle(cantidad){
    if(self.articulo.descripcion == null){
        return;
    }
    if(self.articulo.descripcion == ""){
        return;
    }
    var itemNuevo = setItemNuevo(cantidad)
    self.detail.push(itemNuevo);
    self.detail.sort(function(a,b) {
    if ( a.pesoPrioridad > b.pesoPrioridad )
        return -1;
    if ( a.pesoPrioridad < b.pesoPrioridad )
        return 1;
    return 0;
    } );
    self.update()
}

function setItemNuevo(cantidad){
    self.pesoPrioridad  =  self.pesoPrioridad + 1
    self.numeroLinea    = self.numeroLinea + 1
    self.update()
   var item = {
       numeroLinea     : __valorNumerico(self.numeroLinea),
       pesoPrioridad   : self.pesoPrioridad,
       codigo          : self.articulo.codigo,
       descripcion     : self.articulo.descripcion,
       cantidadEtiqueta: __valorNumerico(cantidad),
       precio          : __valorNumerico(self.articulo.precioPublico),
    }
    return item;
}

__removeProductFromDetail(e) {
    self.itemEliminar = e.item;
    self.update()
     eliminarDetalle()

}

function buscarItemEliminar(item){
    for (var count = 0; count < self.detail.length; count++) {
        if (self.detail[count].codigo == item.codigo && self.detail[count].numeroLinea == item.numeroLinea ){
             self.detail.splice(count, 1);
             self.update()

        }
    }
}

function  eliminarDetalle(){
    buscarItemEliminar(self.itemEliminar)
    self.cantArticulos = self.cantArticulos > 0?self.cantArticulos - 1:0
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
    }else{
      self.numeroLinea =  0
    }
    self.update()
 }

__generarPDF(){
  enviarGenerarPDF();

}

function enviarGenerarPDF(){

    if(self.detail.length == 0 ){
        mensajeAdvertencia("No hay lista para generar etiquetas")
        getPosicionInputCodigo()
        return
    }
    self.etiquetas.data =self.detail
    var JSONDetalles = JSON.stringify( self.etiquetas );
    $('#listaArticuloEtiquetas').val(JSONDetalles)
    $.ajax({
        type : "GET",
        dataType : "json",
        data : {listaArticuloEtiquetas:JSONDetalles},
        url : "GenerarEtiquetasPrecios.do",
        success : function(data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                     mensajeAdvertencia(data.message);
                }
                self.update()
            } else {
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });

}

</script>
</etiqueta-imprimir>